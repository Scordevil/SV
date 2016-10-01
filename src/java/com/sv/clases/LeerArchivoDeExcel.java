/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.clases;

import com.sv.dao.InventarioDao;
import com.sv.dao.PedidoDao;
import com.sv.dao.UsuarioDao;
import com.sv.modelos.Empresa;
import com.sv.modelos.Inventario;
import com.sv.modelos.Pedido;
import com.sv.modelos.Usuario;
import java.io.File;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Gustavo Cardenas
 */
public class LeerArchivoDeExcel {

    static Usuario usuario = new Usuario();
    static Usuario temp = new Usuario();
    static Pedido pedido = new Pedido();
    static Inventario inventario = new Inventario();

    public static void main(String[] args) throws IOException, BiffException {

//        registrarMasivaInventario();
    }

    public static void registrarUsuarioYPedido(String path, int idEmpresa) throws IOException, BiffException {

        Workbook workbook = Workbook.getWorkbook(new File(path)); //Pasamos el excel que vamos a leer
        Sheet sheet = workbook.getSheet(0); //Seleccionamos la hoja que vamos a leer
        String nombre = "", cedula = "", departamento = "", ciudad = "", oficina = "", area = "", telefono = "", email = "", hijo = "", sexo = "", nombreE = "", ciudadE = "", emailE = "", telefonoE = "", fechaE = "", horaE = "", direccionE = "";
        String contrasena = "", user = "";
        int codigo = 0, edad = 0;
//        for (int fila = 1; fila < sheet.getRows(); fila++) { //recorremos las filas
//            for (int columna = 0; columna < sheet.getColumns(); columna++) { //recorremos las columnas
//                nombre = sheet.getCell(columna, fila).getContents(); //setear la celda leida a nombre
//                System.out.println("prueba---------" + nombre);
//            }
//            System.out.println("prueba---------" + nombre);
//
//        }
//        for (int fila = 1; fila < sheet.getRows(); fila++) { //recorremos las filas

        for (int fila = 1; fila < sheet.getRows(); fila++) { //recorremos las filas

            nombre = sheet.getCell(0, fila).getContents(); //setear la celda leida a nombre
            if (!(sheet.getCell(1, fila).getContents().equals(""))) {
                codigo = Integer.parseInt(sheet.getCell(1, fila).getContents());
            }
            cedula = sheet.getCell(2, fila).getContents();
            departamento = sheet.getCell(3, fila).getContents();
            ciudad = sheet.getCell(4, fila).getContents();
            oficina = sheet.getCell(5, fila).getContents();
            area = sheet.getCell(6, fila).getContents();
            telefono = sheet.getCell(7, fila).getContents();
            email = sheet.getCell(8, fila).getContents();
            hijo = sheet.getCell(9, fila).getContents();
            if (sheet.getCell(10, fila).getContents().trim().equals("") || sheet.getCell(10, fila).getContents().trim().equals("0")) {
                edad = 0;
            } else {
                edad = Integer.parseInt(sheet.getCell(10, fila).getContents());
            }
            sexo = sheet.getCell(11, fila).getContents();
            nombreE = sheet.getCell(12, fila).getContents();
            ciudadE = sheet.getCell(13, fila).getContents();
            emailE = sheet.getCell(14, fila).getContents();
            telefonoE = sheet.getCell(15, fila).getContents();
            fechaE = sheet.getCell(16, fila).getContents();
            horaE = sheet.getCell(17, fila).getContents();
            direccionE = sheet.getCell(18, fila).getContents();

            usuario.setNombre(nombre.trim());
            usuario.setAreaTrabajo(area.trim());
            usuario.setCc(cedula.trim());
            usuario.setCodigoEmpleado(codigo);
            usuario.setContrasena(contrasena.trim());
            usuario.setUsuario(user.trim());
            usuario.setTelefono(telefono.trim());
            usuario.setOficina(oficina.trim());
            usuario.setEmail(email.trim());
            usuario.getIdCiudad().setNombre(ciudad);
            usuario.getIdCiudad().setIdCiudad(2);
            usuario.getIdTipoUsuario().setIdTipoUsuario(4);
            usuario.getIdDepartamento().setIdDepartamento(1);
            usuario.getIdEmpresa().setIdEmpresa(idEmpresa);

            pedido.setNombreHijo(hijo.trim());
            pedido.setSexoHijo(sexo.trim());
            pedido.setEdadHijo(edad);
            pedido.setIdInventario(0);

//= new Usuario(nombre, codigo, cedula, telefono, email, user, contrasena, oficina, area,new Tipousuario(4), new Empresa(3), new Departamento(2, departamento), new Ciudad(1, ciudad));
            UsuarioDao usuarioDAO = new UsuarioDao();
            PedidoDao pedidoDAO = new PedidoDao();

            temp = usuarioDAO.consultarUsuarioPorCC(usuario.getCc());
            if (temp.getIdUsuario() != 0) {
                pedidoDAO.registrarPedido(temp, pedido);
            } else {
                usuarioDAO.registrarUsuario(usuario);
                temp = usuarioDAO.consultarUsuarioPorCC(usuario.getCc());
                pedidoDAO.registrarPedido(temp, pedido);
            }
        }
    }

    public static void registrarMasivaInventario(String path, int idEmpresa) throws IOException, BiffException {

        Workbook workbook = Workbook.getWorkbook(new File(path)); //Pasamos el excel que vamos a leer
        Sheet sheet = workbook.getSheet(0); //Seleccionamos la hoja que vamos a leer
        String sku = "", nombre = "", descripcion = "", genero = "", url1 = "", url2 = "", url3 = "", url4 = "", url5 = "", url6 = "", url7 = "", url8 = "", url9 = "", url10 = "", url11 = "", url12 = "", observacion = "";
        int cantidad = 0, rangoD = 0, rangoH = 0;

        for (int fila = 1; fila < sheet.getRows(); fila++) { //recorremos las filas

            sku = sheet.getCell(0, fila).getContents(); //setear la celda leida a nombre
            nombre = sheet.getCell(1, fila).getContents();
            descripcion = sheet.getCell(2, fila).getContents();
            rangoD = Integer.parseInt(sheet.getCell(3, fila).getContents());
            rangoH = Integer.parseInt(sheet.getCell(4, fila).getContents());
            genero = sheet.getCell(5, fila).getContents();
            url1 = sheet.getCell(6, fila).getContents();
            url2 = sheet.getCell(7, fila).getContents();
            url3 = sheet.getCell(8, fila).getContents();
            url4 = sheet.getCell(9, fila).getContents();
            url5 = sheet.getCell(10, fila).getContents();
            url6 = sheet.getCell(11, fila).getContents();
            url7 = sheet.getCell(12, fila).getContents();
            url8 = sheet.getCell(13, fila).getContents();
            url9 = sheet.getCell(14, fila).getContents();
            url10 = sheet.getCell(15, fila).getContents();
            url11 = sheet.getCell(16, fila).getContents();
            url12 = sheet.getCell(17, fila).getContents();
            if (!(sheet.getCell(18, fila).getContents().equals(""))) {
                cantidad = Integer.parseInt(sheet.getCell(18, fila).getContents());
            }
            observacion = sheet.getCell(19, fila).getContents();

            inventario.setNombre(nombre.trim());
            inventario.setCodigo(sku.trim());
            inventario.setDescripcion(descripcion.trim());
            inventario.setEdadDesde(rangoD);
            inventario.setEdadHasta(rangoH);
            if (genero.trim().equals("Unisex")) {
                inventario.setGenero("A");
            } else {
                inventario.setGenero(genero);
            }

            inventario.setUrl1(url1.trim());
            inventario.setUrl2(url2.trim());
            inventario.setUrl3(url3.trim());
            inventario.setUrl4(url4.trim());
            inventario.setUrl5(url5.trim());
            inventario.setUrl6(url6.trim());
            inventario.setUrl7(url7.trim());
            inventario.setUrl8(url8.trim());
            inventario.setUrl9(url9.trim());
            inventario.setUrl10(url10.trim());
            inventario.setUrl11(url11.trim());
            inventario.setUrl12(url12.trim());
            inventario.setCantidad(cantidad);
            inventario.setObservacion(observacion.trim());
            inventario.setIdEmpresa(new Empresa(idEmpresa));

            InventarioDao inventarioDAO = new InventarioDao();

            inventarioDAO.registrarJuguete(inventario);

        }
    }
}
