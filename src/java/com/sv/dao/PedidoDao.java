/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dao;

import com.sv.modelos.Ciudad;
import com.sv.modelos.Empresa;
import com.sv.modelos.Inventario;
import com.sv.modelos.Pedido;
import com.sv.modelos.Usuario;
import com.sv.webservices.clientes.ClienteConsultarPedido;
import com.sv.webservices.clientes.ClienteConsultarPedidosPorId;
import com.sv.webservices.clientes.ClienteEditarPedido;
import com.sv.webservices.clientes.ClienteRegistrarPedido;
import com.sv.webservices.clientes.ConsultarJuguetesEdadGeneroCiudadEmpresa;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Gustavo Cardenas
 */
public class PedidoDao {

    public List<Pedido> ConsultarPedidoPorId(int idUsuario) {
        ClienteConsultarPedidosPorId pedido = new ClienteConsultarPedidosPorId();
        List<HashMap> datos = pedido.consultarPedidosPorId(List.class, idUsuario + "");
        List<Pedido> pedidos = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            LinkedHashMap usuario = (LinkedHashMap) datos.get(i).get("idUsuario");

            pedidos.add(new Pedido((int) datos.get(i).get("idPedido"),
                    (String) datos.get(i).get("nombreHijo"),
                    (int) datos.get(i).get("edadHijo"),
                    (String) datos.get(i).get("sexoHijo"),
                    (String) datos.get(i).get("nombreEncargado"),
                    (String) datos.get(i).get("ciudadEncargado"),
                    (String) datos.get(i).get("emailEncargado"),
                    (String) datos.get(i).get("telefonoEncargado"),
                    (Date) datos.get(i).get("fechaEntrega"),
                    (Date) datos.get(i).get("horaEntrega"),
                    (String) datos.get(i).get("direccionEntrega"),
                    new Usuario((int) usuario.get("idUsuario"))));

        }
        return pedidos;
    }

    public Pedido ConsultarPedido(int idPedido) {
        ClienteConsultarPedido pedido = new ClienteConsultarPedido();
        Pedido ped = new Pedido();
        ped = pedido.consultarPedido(Pedido.class, idPedido + "");

        return ped;
    }

    public void registrarPedido(Usuario usuario, Pedido pedido) {
        ClienteRegistrarPedido cliente = new ClienteRegistrarPedido();

        cliente.registrarPedido(Integer.class,
                "" + usuario.getIdUsuario(),
                "" + pedido.getNombreHijo(),
                "" + pedido.getEdadHijo(),
                "" + pedido.getSexoHijo(),
                "" + pedido.getIdInventario());

    }

    public int EditarPedido(Pedido pedido) {
        ClienteEditarPedido cliente = new ClienteEditarPedido();
        return cliente.editarPedido(int.class, "" + pedido.getIdInventario(), "" + pedido.getIdPedido());
    }

    public List<Inventario> ConsultarJuguetesEdadGeneroCiudadEmpresa(int idEmpresa, int edad, String genero, int idCiudad) {
        ConsultarJuguetesEdadGeneroCiudadEmpresa Inventario = new ConsultarJuguetesEdadGeneroCiudadEmpresa();
        List<HashMap> datos = Inventario.consultarJuguetesEdadGeneroCiudadEmpresa(List.class, idEmpresa + "", edad + "", genero, idCiudad + "");
        List<Inventario> Inventarios = new ArrayList<>();

        CiudadDao cd = new CiudadDao();
        UsuarioDao ud = new UsuarioDao();
        EmpresaDao ed = new EmpresaDao();

        System.err.println("-------------------------- PRUEBA" + datos);

        for (int i = 0; i < datos.size(); i++) {
            LinkedHashMap usuario = (LinkedHashMap) datos.get(i).get("idUsuario");
            HashMap map2 = (HashMap) datos.get(i).get("idCiudad");
            HashMap map3 = (HashMap) datos.get(i).get("idUsuario");

            Inventarios.add(new Inventario((int) datos.get(i).get("idInventario"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("genero"),
                    new Empresa(new Usuario(cd.consultarCiudad(new Ciudad((int) map2.get("idCiudad"),
                            (String) map2.get("nombre"))),
                            (String) datos.get(i).get("cc"),
                            (String) datos.get(i).get("nombre"))),
                    new Pedido((String) datos.get(i).get("nombreHijo"),
                            (int) datos.get(i).get("edadHijo"))
            ));
//
//            Inventarios.add(new Inventario((int) datos.get(i).get("idInventario"),
//                    (String) datos.get(i).get("nombre"),
//                    (String) datos.get(i).get("genero"),
//                    new Empresa(new Usuario(cd.consultarCiudad(new Ciudad((int) map2.get("idCiudad"),
//                            (String) map2.get("nombre"))),
//                            (String) datos.get(i).get("cc"),
//                            (String) datos.get(i).get("nombre"))),
//                    new Pedido((String) datos.get(i).get("nombreHijo"),
//                            (int) datos.get(i).get("edadHijo"))
//            ));

        }
        return Inventarios;
    }
}
