/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.controladores;

import com.sv.clases.Sesion;
import com.sv.dao.PedidoDao;
import com.sv.dao.UsuarioDao;
import com.sv.modelos.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Gustavo Cardenas
 */
public class PedidoCT {

    private Pedido pedido;
    private List<Pedido> pedidos;
    private String informacion;

    public PedidoCT() {
        pedido = new Pedido();
        pedidos = new ArrayList<>();
        informacion = "";
    }

    @PostConstruct
    public void init() {
        PedidoDao pedidoDao = new PedidoDao();
        //   pedido.getIdUsuario().setIdUsuario(3);
        
        pedidos = pedidoDao.ConsultarPedidoPorId(Sesion.obtenerSesion().getIdUsuario());

        if (pedidos.isEmpty()) {

            quitarAccesos();
        }

    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
    
    
    

//        //Metodos
//    public void registrar(){
//        PedidoDao pedidoDao = new PedidoDao();
//        pedido.setIdUsuario(new Usuario(3));
//        usuarioDao.registrarUsuario(usuario);
//        
//        usuario = new Usuario();
//        usuarios = usuarioDao.consultarUsuarios();
//        
//    }
    public void consultarPedido(int idPedido) {
        PedidoDao pedidoDao = new PedidoDao();
        pedido = pedidoDao.ConsultarPedido(idPedido);
    }

    public String editarPedido(int idUsuario, int idPedido, int idInventario) {
        String link = "";
        int valor = 0;
        PedidoDao pedidoDao = new PedidoDao();
        Pedido ped = new Pedido();
        ped.setIdInventario(idInventario);
        ped.setIdPedido(idPedido);
        valor = pedidoDao.EditarPedido(ped);

//        if (valor == 1) {
//            UsuarioDao usuarioDao = new UsuarioDao();
//            usuarioDao.quitarAcceso(idUsuario);
//        }
        link = "Login";

        return link;

    }

    public void quitarAccesos() {


        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.quitarAcceso(Sesion.obtenerSesion().getIdUsuario());
//        
        informacion = "Ya usted a seleccionado lo(s) Articulo para su(s) hijo(s). Por favor cierre Sesion";

    }

}
