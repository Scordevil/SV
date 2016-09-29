/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dao;

import com.sv.modelos.Pedido;
import com.sv.modelos.Usuario;
import com.sv.webservices.clientes.ClienteConsultarPedido;
import com.sv.webservices.clientes.ClienteConsultarPedidosPorId;
import com.sv.webservices.clientes.ClienteEditarPedido;
import com.sv.webservices.clientes.ClienteRegistrarPedido;
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
        return cliente.editarPedido(int.class, "" + pedido.getIdInventario(), ""+ pedido.getIdPedido());
    }

}
