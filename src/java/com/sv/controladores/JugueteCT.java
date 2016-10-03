/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.controladores;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sv.clases.LeerArchivoDeExcel;
import com.sv.clases.Sesion;
import com.sv.clases.Upload;
import com.sv.clases.UtilPath;
import com.sv.dao.ComiteDao;
import com.sv.dao.InventarioDao;
import com.sv.dao.PedidoDao;
import com.sv.modelos.Empresa;
import com.sv.modelos.Inventario;
import com.sv.modelos.Pedido;
import com.sv.modelos.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jxl.read.biff.BiffException;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author CristianCamilo
 */
public class JugueteCT {

    private Inventario juguete;
    private Pedido pedido;
    private Usuario usuario;
    private List<Inventario> inventarios;
    private List<Inventario> inventariosMasSeleccionados;
    private List<Inventario> inventariosSeleccionados;
    private List<Inventario> inventariosMasVotados;
    private List<String> images;
    private int vista;
    private String buscar;
    private Empresa empresa;
    private UploadedFile excel;

    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private BarChartModel animatedModel3;
    private PieChartModel pieModel1;

    protected int operacion;
    private String nombreOperacion;
    private int calificacion;

    public JugueteCT() {
        juguete = new Inventario();
        inventarios = new ArrayList<>();
        inventariosMasSeleccionados = new ArrayList<>();
        inventariosSeleccionados = new ArrayList<>();
        inventariosMasVotados = new ArrayList<>();
        images = new ArrayList<>();
        vista = 0;
        buscar = "";
        nombreOperacion = "Registrar";
        operacion = 0;
        empresa = new Empresa();
        calificacion = 0;
        usuario = new Usuario();

    }

    @PostConstruct
    public void init() {

        InventarioDao inventarioDao = new InventarioDao();
        int idComite = 0;
        int valor = 0;
        ComiteDao comiteDao = new ComiteDao();
        idComite = comiteDao.consultarComitePorUsuario(Sesion.obtenerSesion().getIdUsuario());
        if (idComite > 0) {
            valor = comiteDao.validarVotacionPorUsuario(Sesion.obtenerSesion().getIdUsuario()); //mayor a 0, ya voto
            if (valor > 0) {
                inventarios = inventarioDao.consultarJuguetes();
            } else {
                inventarios = inventarioDao.ConsultarJuguetesComite(idComite);
            }
        } else {
            inventarios = inventarioDao.consultarJuguetes();
        }
        inventarios = inventarioDao.consultarJuguetes();

        //-----------------------
        inventariosMasSeleccionados = inventarioDao.consultarJuguetesMasSeleccionados(6);

        inventariosMasVotados = inventarioDao.consultarJuguetesMasVotados(6);

        try {
            createAnimatedModels();
        } catch (IOException ex) {
            Logger.getLogger(JugueteCT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(JugueteCT.class.getName()).log(Level.SEVERE, null, ex);
        }
        createPieModels();
    }

    public Inventario getJuguete() {
        return juguete;
    }

    public void setJuguete(Inventario juguete) {
        this.juguete = juguete;
    }

    public List<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    public int getVista() {
        return vista;
    }

    public void setVista(int vista) {
        this.vista = vista;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public List<Inventario> getInventariosMasSeleccionados() {
        return inventariosMasSeleccionados;
    }

    public void setInventariosMasSeleccionados(List<Inventario> inventariosMasSeleccionados) {
        this.inventariosMasSeleccionados = inventariosMasSeleccionados;
    }

    public List<Inventario> getInventariosMasVotados() {
        return inventariosMasVotados;
    }

    public void setInventariosMasVotados(List<Inventario> inventariosMasVotados) {
        this.inventariosMasVotados = inventariosMasVotados;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    public BarChartModel getAnimatedModel3() {
        return animatedModel3;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public UploadedFile getExcel() {
        return excel;
    }

    public void setExcel(UploadedFile excel) {
        this.excel = excel;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Inventario> getInventariosSeleccionados() {
        return inventariosSeleccionados;
    }

    public void setInventariosSeleccionados(List<Inventario> inventariosSeleccionados) {
        this.inventariosSeleccionados = inventariosSeleccionados;
    }

    //Metodos
    public void metodo() throws IOException {
        if (operacion == 0) {
            registrar();
        } else if (operacion == 1) {
            modificar();
            //Reiniciamos banderas
            nombreOperacion = "Registrar";
            operacion = 0;
        }
    }

    public void seleccionarCRUD(int i) {
        operacion = i;
        if (operacion == 1) {
            nombreOperacion = "Modificar";
        }
    }

    public void cancelar() {
        juguete = new Inventario();
        nombreOperacion = "Registrar";
        operacion = 0;
    }

    public void registrar() {
        InventarioDao inventarioDao = new InventarioDao();
        int resultado = inventarioDao.registrarJuguete(juguete);

        if (resultado == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "El juguete ha sido registrado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Imposible registrar juguete");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        inventarios = inventarioDao.consultarJuguetes();
        juguete = new Inventario();
    }

    public void modificar() {
        InventarioDao inventarioDao = new InventarioDao();
        int resultado = inventarioDao.editarJuguete(juguete);

        if (resultado == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "El juguete ha sido modificada correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Imposible modificar juguete");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        inventarios = inventarioDao.consultarJuguetes();
        juguete = new Inventario();
    }

    public void eliminar() {
        InventarioDao inventarioDao = new InventarioDao();
        int resultado = inventarioDao.eliminarInventario(juguete);

        if (resultado == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "El juguete ha sido eliminado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Imposible eliminar juguete");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        inventarios = inventarioDao.consultarJuguetes();
        juguete = new Inventario();
    }

    public void consultarJuguetePorGeneroYEdad(int edad, String genero, int idPedido) {
        System.out.println("prueba: " + edad + "-" + genero + "-" + idPedido);
        InventarioDao inventarioDao = new InventarioDao();
        inventarios = inventarioDao.ConsultarJuguetesEdadGenero(edad, genero);
        vista++;
        PedidoDao pedidoDao = new PedidoDao();

        pedido = pedidoDao.ConsultarPedido(idPedido);
    }

    public void consultarJuguetePorRangoYEdad() {
        InventarioDao inventarioDao = new InventarioDao();
        inventarios = inventarioDao.ConsultarJuguetesRangoGenero(juguete.getEdadDesde(), juguete.getEdadHasta(), juguete.getGenero());

    }

    public void consultarJuguetePorComite(int idComite) {
        InventarioDao inventarioDao = new InventarioDao();
        inventarios = inventarioDao.ConsultarJuguetesComite(idComite);

    }

    public String consultarJuguetePorId(int idInventario, int valor) {
        images = new ArrayList<String>();

        InventarioDao inventarioDao = new InventarioDao();
        juguete = inventarioDao.ConsultarJuguetePorId(idInventario);
        juguete.setIdInventario(idInventario);
        int urls = 0;

//        if (!juguete.getUrl1().equals("") && juguete.getUrl1() != null) {
//            urls++;
//        }
        if (juguete.getUrl1() != null) {
            if (!juguete.getUrl1().isEmpty()) {
                urls++;
            }
        }
        if (juguete.getUrl2() != null) {
            if (!juguete.getUrl2().isEmpty()) {
                urls++;
            }
        }
        if (juguete.getUrl3() != null) {
            if (!juguete.getUrl3().isEmpty()) {
                urls++;
            }
        }
        if (juguete.getUrl4() != null) {
            if (!juguete.getUrl4().isEmpty()) {
                urls++;
            }
        }
        if (juguete.getUrl5() != null) {
            if (!juguete.getUrl5().isEmpty()) {
                urls++;
            }
        }
        if (juguete.getUrl6() != null) {
            if (!juguete.getUrl6().isEmpty()) {
                urls++;
            }
        }
        if (juguete.getUrl7() != null) {
            if (!juguete.getUrl7().isEmpty()) {
                urls++;
            }
        }
        if (juguete.getUrl8() != null) {
            if (!juguete.getUrl8().isEmpty()) {
                urls++;
            }
        }
        if (juguete.getUrl9() != null) {
            if (!juguete.getUrl9().isEmpty()) {
                urls++;
            }
        }
        if (juguete.getUrl10() != null) {
            if (!juguete.getUrl10().isEmpty()) {
                urls++;
            }
        }
        if (juguete.getUrl11() != null) {
            if (!juguete.getUrl11().isEmpty()) {
                urls++;
            }
        }
        if (juguete.getUrl12() != null) {
            if (!juguete.getUrl12().isEmpty()) {
                urls++;
            }
        }

        for (int i = 1; i <= urls; i++) {
            images.add(juguete.getCodigo() + "-" + i + ".jpg");
        }

        String link = "";
        if (valor != 2) {
            link = "Seleccion";
        } else {
            link = "Votar";
        }
        return link;
    }

    public void atras() {
        vista--;
    }

    public List<String> getImages() {
        return images;
    }

    public void buscarJuguetes() {
        inventarios = new ArrayList<>();
        InventarioDao inventarioDao = new InventarioDao();
        if (buscar.isEmpty()) {
            inventarios = inventarioDao.consultarJuguetes();
        } else {
            inventarios = inventarioDao.buscarJuguetes(buscar);
        }
    }

    public void consultarJuguetesMasSeleccionados(int idEmpresa) {
        inventarios = new ArrayList<>();
        InventarioDao inventarioDao = new InventarioDao();

        inventarios = inventarioDao.consultarJuguetesMasSeleccionados(idEmpresa);

    }

    public void consultarJuguetesMasVotados(int idEmpresa) {
        inventarios = new ArrayList<>();
        InventarioDao inventarioDao = new InventarioDao();

        inventarios = inventarioDao.consultarJuguetesMasVotados(idEmpresa);

    }

    public void consultarJuguetesPorEmpresa() {
        inventarios = new ArrayList<>();
        InventarioDao inventarioDao = new InventarioDao();
        if (empresa.getIdEmpresa() == 0) {
            inventarios = inventarioDao.consultarJuguetes();
        } else {
            inventarios = inventarioDao.ConsultarJuguetesPorEmpresa(empresa);
        }
    }

    private void createAnimatedModels() throws IOException, DocumentException {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Line Chart");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Articulos Mas Rankeados");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        int valor = (200);
        yAxis.setMax(valor);

        animatedModel3 = initBarModel2();
        animatedModel3.setTitle("Articulos Mas Seleccionados");
        animatedModel3.setAnimate(true);
        animatedModel3.setLegendPosition("ne");
        yAxis = animatedModel3.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);

//        generarPDFOnClick();
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries inventario = new ChartSeries();
        inventario.setLabel("Articulos");

        if (inventariosMasSeleccionados.size() >= 3) {
            for (int i = 1; i <= 3; i++) {
                inventario.set(inventariosMasSeleccionados.get(i).getCodigo(), 1);
            }
        } else if (inventariosMasSeleccionados.size() >= 1) {
            for (int i = 0; i < inventariosMasSeleccionados.size(); i++) {
                inventario.set(inventariosMasSeleccionados.get(i).getCodigo(), 1);
            }
        } else {
            inventario.set("", 0);
        }

        model.addSeries(inventario);

        return model;
    }

    private BarChartModel initBarModel2() {
        BarChartModel model = new BarChartModel();

        ChartSeries inventario = new ChartSeries();
        inventario.setLabel("Articulos");

        if (inventariosMasVotados.size() >= 3) {
            for (int i = 1; i <= 3; i++) {
                inventario.set(inventariosMasVotados.get(i).getCodigo(), 1);
            }
        } else if (inventariosMasVotados.size() >= 1) {
            for (int i = 0; i < inventariosMasVotados.size(); i++) {
                inventario.set(inventariosMasVotados.get(i).getCodigo(), 1);
            }
        } else {
            inventario.set("", 0);
        }

        model.addSeries(inventario);

        return model;
    }

    private void createPieModels() {
        createPieModel1();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        pieModel1.set("Votaron", 540);
        pieModel1.set("Faltan por Votar", 325);

        pieModel1.setTitle("Votaciones");
        pieModel1.setLegendPosition("w");
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void uploadExcel(int idEmpresa) throws IOException, BiffException {
        //    String destino;
//        HashMap<String, String> map = Upload.getMapPathLogosEmpresa();
//        destino = map.get("path");
//        if (null != excel) {

        if (null != excel) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

            String path = UtilPath.getUrlDefinida(ec.getRealPath("/"));

            String string = path;
            String quitar1 = "Documents";
            String[] parts = string.split(quitar1);
            String part1 = parts[0]; // 004
            String part2 = parts[1]; // 034556

            String realPath = part1 + "Dropbox" + File.separator + "Cargas" + File.separator + excel.getFileName();
//        if(guardarArchivos(realPath, file)){
//            extraerDatos(realPath);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", path));
//        }

            LeerArchivoDeExcel.registrarMasivaInventario(realPath, idEmpresa);
        }

//        }
    }

    public void consultarJuguetesEdadGeneroCiudadEmpresa() {
        inventariosSeleccionados = new ArrayList<>();
        PedidoDao pedidoDao = new PedidoDao();
        inventariosSeleccionados = pedidoDao.ConsultarJuguetesEdadGeneroCiudadEmpresa(usuario.getIdEmpresa().getIdEmpresa(),
                pedido.getEdadHijo(), juguete.getGenero(), usuario.getIdCiudad().getIdCiudad());
    }

}
