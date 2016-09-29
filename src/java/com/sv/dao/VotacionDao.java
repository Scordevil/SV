/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dao;

import com.sv.modelos.Votacion;
import com.sv.webservices.clientes.ClienteRegistrarVotacion;
import java.text.SimpleDateFormat;

/**
 *
 * @author CristianCamilo
 */
public class VotacionDao {
    
    public int registrarVotacion(Votacion votacion){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ClienteRegistrarVotacion cliente = new ClienteRegistrarVotacion();        
        return cliente.crearVotacion(int.class,
                "" + votacion.getIdUsuario().getIdUsuario(),
                "" + votacion.getIdInventario().getIdInventario(), 
                "" + votacion.getIdComite().getIdComite(), 
                sdf.format(votacion.getFechaCalificacion()), 
                votacion.getCalificacion());
    }
    
}
