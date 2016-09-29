/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dao;

import com.sv.modelos.Tipousuario;
import com.sv.webservices.clientes.ClienteConsultarTipoUsuario;

/**
 *
 * @author VaioDevelopment
 */
public class TipoUsuarioDao {
    
    public Tipousuario consultarTipoUsuario(Tipousuario tipo){
        ClienteConsultarTipoUsuario cliente = new ClienteConsultarTipoUsuario();
        return cliente.consultaTipoUsuario(Tipousuario.class, "" + tipo.getIdTipoUsuario(), "" + tipo.getNombre());
    }
}
