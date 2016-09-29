/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dao;

import com.sv.modelos.Usuario;
import com.sv.webservices.clientes.ClienteCorreoCreacionAdministrador;
import com.sv.webservices.clientes.ClienteCorreoVotacion;

/**
 *
 * @author CristianCamilo
 */
public class CorreoDao {
    
    public int EnviarCorreoCreacionAdministrador(Usuario usuario){
        ClienteCorreoCreacionAdministrador cliente = new ClienteCorreoCreacionAdministrador();
        return cliente.correoCreacionAdministrador(int.class, usuario.getNombre(), usuario.getIdEmpresa().getNombre(), usuario.getUsuario(), usuario.getContrasena());
    }
    
    
    public int EnviarCorreoVotacion(Usuario usuario){
        ClienteCorreoVotacion cliente = new ClienteCorreoVotacion();
        return cliente.correoVotacion(int.class, usuario.getNombre(), usuario.getIdEmpresa().getNombre(), usuario.getUsuario(), usuario.getContrasena());
    }
    
}
