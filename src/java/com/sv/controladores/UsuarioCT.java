/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.controladores;

import com.sv.clases.LeerArchivoDeExcel;
import com.sv.clases.Sesion;
import com.sv.clases.Upload;
import com.sv.clases.UtilPath;
import com.sv.dao.CorreoDao;
import com.sv.dao.UsuarioDao;
import com.sv.modelos.Empresa;
import com.sv.modelos.Inventario;
import com.sv.modelos.Tipousuario;
import com.sv.modelos.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jxl.read.biff.BiffException;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author VaioDevelopment
 */
public class UsuarioCT implements Serializable{

    private Integer progress;
    private Usuario usuario;
    private List<Usuario> usuarios;
    private List<Usuario> usuariosTipoEmpleado;
    private List<Tipousuario> tipoUsuarios;
    private String buscar;
    private int idTipoUsuario;
    private UploadedFile excel;
    private String archivoExcel;
    private boolean deshabilitar;

    private int operacion;
    private String nombreOperacion;

    public UsuarioCT() {
        usuario = new Usuario();
        usuarios = new ArrayList<>();
        usuariosTipoEmpleado = new ArrayList<>();
        tipoUsuarios = new ArrayList<>();
        buscar = "";
        idTipoUsuario = 0;
        deshabilitar = false;
        nombreOperacion = "Registrar";
    }

    @PostConstruct
    public void init() {
        UsuarioDao usuarioDao = new UsuarioDao();
        idTipoUsuario = Sesion.obtenerSesion().getIdTipoUsuario().getIdTipoUsuario();

//        if (Sesion.obtenerSesion().getIdTipoUsuario().getIdTipoUsuario() == 3) {
//            usuarios = usuarioDao.ConsultarUsuariosSegunEmpresa(new Empresa(Sesion.obtenerSesion().getIdEmpresa().getIdEmpresa()));
//        }
        if (Sesion.obtenerSesion().getIdTipoUsuario().getIdTipoUsuario() == 1) {
            tipoUsuarios = usuarioDao.consultarTipoUsuarios();
        } else {
            tipoUsuarios = usuarioDao.consultarTipoUsuariosRestringidos();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuariosTipoEmpleado() {
        return usuariosTipoEmpleado;
    }

    public void setUsuariosTipoEmpleado(List<Usuario> usuariosTipoEmpleado) {
        this.usuariosTipoEmpleado = usuariosTipoEmpleado;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public List<Tipousuario> getTipoUsuarios() {
        return tipoUsuarios;
    }

    public void setTipoUsuarios(List<Tipousuario> tipoUsuarios) {
        this.tipoUsuarios = tipoUsuarios;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public UploadedFile getExcel() {
        return excel;
    }

    public void setExcel(UploadedFile excel) {
        this.excel = excel;
    }

    public boolean isDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(boolean deshabilitar) {
        this.deshabilitar = deshabilitar;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    public String getArchivoExcel() {
        return archivoExcel;
    }

    public void setArchivoExcel(String archivoExcel) {
        this.archivoExcel = archivoExcel;
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
        usuario = new Usuario();
        nombreOperacion = "Registrar";
        operacion = 0;
    }

    public void registrar() {
        UsuarioDao usuarioDao = new UsuarioDao();
        if (usuario.getIdTipoUsuario().getIdTipoUsuario() == 1 || usuario.getIdTipoUsuario().getIdTipoUsuario() == 2) {
            usuario.setIdEmpresa(Sesion.obtenerSesion().getIdEmpresa());
        }
        if (isDeshabilitar() == true) {
            usuario.getIdEmpresa().setIdEmpresa(5); //Faro
        }

        int resultado = usuarioDao.registrarUsuario(usuario);

        if (resultado == 1) {
            if (usuario.getIdTipoUsuario().getIdTipoUsuario() == 1 || usuario.getIdTipoUsuario().getIdTipoUsuario() == 2 || usuario.getIdTipoUsuario().getIdTipoUsuario() == 3) {
                CorreoDao correoDao = new CorreoDao();
                correoDao.EnviarCorreoCreacionAdministrador(usuario);
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "El usuario ha sido registrado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Imposible registrar usuario");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        usuario = new Usuario();
//        if (Sesion.obtenerSesion().getIdTipoUsuario().getIdTipoUsuario() == 1) {
//            usuarios = usuarioDao.consultarUsuarios();
//        } else if (Sesion.obtenerSesion().getIdTipoUsuario().getIdTipoUsuario() == 2) {
//            Empresa emp = new Empresa();
//            emp.setIdEmpresa(usuario.getIdEmpresa().getIdEmpresa());
//            usuarios = usuarioDao.ConsultarUsuariosSegunEmpresa(emp);
//        }
    }

    public void modificar() {
        UsuarioDao usuarioDao = new UsuarioDao();

        int resultado = usuarioDao.editarUsuario(usuario);
        if (resultado == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "El usuario ha sido modificado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Imposible modificar usuario");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        usuario = new Usuario();
        usuarios = usuarioDao.consultarUsuarios();
    }

    public void eliminar() {
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.eliminarUsuario(usuario);
        int resultado = usuarioDao.editarUsuario(usuario);
        if (resultado == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "El usuario ha sido eliminado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Imposible eliminar usuario");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        usuarios = usuarioDao.consultarUsuarios();
        usuario = new Usuario();
    }

    public void buscarSegunTipo() {
        usuariosTipoEmpleado = new ArrayList<>();
        UsuarioDao usuarioDao = new UsuarioDao();
        if (buscar.isEmpty()) {
            usuariosTipoEmpleado = usuarioDao.consultarUsuariosSegunTipoUsuario(new Tipousuario(4));
        } else {
            usuariosTipoEmpleado = usuarioDao.BuscarUsuariosSegunTipoUsuario(buscar, new Tipousuario(4));
        }
    }

    public void consultarUsuariosPorEmpresas() {
        UsuarioDao usuarioDao = new UsuarioDao();
        Empresa empresa = new Empresa(usuario.getIdEmpresa().getIdEmpresa());

        if (usuario.getIdEmpresa().getIdEmpresa() == 999999999) {
            usuarios = usuarioDao.consultarUsuarios();
        } else {
            usuarios = usuarioDao.ConsultarUsuariosSegunEmpresa(empresa);
        }
    }

    public void cargarEmpleadosSegunEmpresa(Empresa empresa) {
        UsuarioDao usuarioDao = new UsuarioDao();
        usuariosTipoEmpleado = usuarioDao.ConsultarUsuariosSegunEmpresa(empresa);
    }

    public void uploadExcel() throws IOException, BiffException {
//        FileUploadEvent event:
//        UploadedFile file = event.getFile();
//            excel = event.getFile();

        if (null != excel) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

            String path = UtilPath.getUrlDefinida(ec.getRealPath("/"));

            String string = path;
            String quitar1 = "NetBeansProjects";
            String[] parts = string.split(quitar1);
            String part1 = parts[0]; // 004
            String part2 = parts[1]; // 034556

            String realPath = part1 + "web" + File.separator + "archivos" + File.separator + excel.getFileName();
//        if(guardarArchivos(realPath, file)){
//            extraerDatos(realPath);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", path));
//        }

            LeerArchivoDeExcel.registrarUsuarioYPedido(realPath, usuario.getIdEmpresa().getIdEmpresa());
        }
    }

//    public void uploadExcel() throws IOException, BiffException {
////        String destino;
////        HashMap<String, String> map = Upload.getMapPathLogosEmpresa();
////        destino = map.get("path");
//        if (null != excel) {
//
//            LeerArchivoDeExcel le = new LeerArchivoDeExcel();
//            le.registrarUsuarioYPedido();
//        }
//    }
    public void deshabilitarEmpresa() {
        if (usuario.getIdTipoUsuario().getIdTipoUsuario() == 1 || usuario.getIdTipoUsuario().getIdTipoUsuario() == 2) {
            deshabilitar = true;
        } else {
            deshabilitar = false;
        }
    }

    public void prueba(String nombre) {
        String name = "";
        name = nombre;
        System.out.println("pruebas--------------***************" + name);
    }

    public Integer getProgress() {
        if (progress == null) {
            progress = 0;
        } else {
            progress = progress + (int) (Math.random() * 35);

            if (progress > 100) {
                progress = 100;
            }
        }

        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Progress Completed"));
    }
}
