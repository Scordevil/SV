/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dao;

import com.sv.modelos.Estado;
import com.sv.webservices.clientes.ClienteConsultarEstado;

/**
 *
 * @author CristianCamilo
 */
public class EstadoDao {
    
    public Estado consultarEstado(Estado estado){
        ClienteConsultarEstado cliente = new ClienteConsultarEstado();
        return  cliente.consultarEstado(Estado.class ,"" + estado.getIdEstado(), estado.getNombre());
    }
}
