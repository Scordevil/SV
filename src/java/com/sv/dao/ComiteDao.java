/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dao;

import com.sv.modelos.Comite;
import com.sv.webservices.clientes.ClienteConsultarComite;
import com.sv.webservices.clientes.ClienteConsultarComitePorUsuario;
import com.sv.webservices.clientes.ClienteEliminarComite;
import com.sv.webservices.clientes.ClienteModificarComite;
import com.sv.webservices.clientes.ClienteRegistrarComite;
import com.sv.webservices.clientes.ClienteValidarVotacionPorUsuario;
import java.text.SimpleDateFormat;

/**
 *
 * @author CristianCamilo
 */
public class ComiteDao {

    public int registrarComite(Comite comite) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        EstadoDao estadoDao = new EstadoDao();
        EmpresaDao empresaDao = new EmpresaDao();
        comite.setIdEmpresa(empresaDao.consultarEmpresa(comite.getIdEmpresa()));
        comite.setIdEstado(estadoDao.consultarEstado(comite.getIdEstado()));
        ClienteRegistrarComite cliente = new ClienteRegistrarComite();
        return cliente.crearComite(int.class,
                "" + comite.getIdEstado().getIdEstado(),
                comite.getNombre(),
                comite.getDescripcion(),
                sdf.format(comite.getFechaApertura()),
                sdf.format(comite.getFechaCierre()),
                "" + comite.getIdEmpresa().getIdEmpresa());
    }

    public int ModificarComite(Comite comite) {
        ClienteModificarComite cliente = new ClienteModificarComite();
        return cliente.editarComite(int.class,
                "" + comite.getIdComite(),
                "" + comite.getIdEstado().getIdEstado(),
                comite.getNombre(),
                comite.getDescripcion(),
                comite.getFechaApertura().toString(),
                comite.getFechaCierre().toString());
    }

    public int EliminarComite(Comite comite) {
        ClienteEliminarComite cliente = new ClienteEliminarComite();
        return cliente.eliminarComite(int.class, "" + comite.getIdComite());
    }

    public Comite consultarComite(Comite comite) {
        ClienteConsultarComite cliente = new ClienteConsultarComite();
        return cliente.consultarComite(Comite.class, "" + comite.getIdComite(), comite.getNombre());
    }

    public int consultarComitePorUsuario(int idUsuario) {
        ClienteConsultarComitePorUsuario cliente = new ClienteConsultarComitePorUsuario();
        return cliente.consultarComitePorUsuario(int.class, "" + idUsuario);
    }

    public int validarVotacionPorUsuario(int idUsuario) {
        ClienteValidarVotacionPorUsuario cliente = new ClienteValidarVotacionPorUsuario();
        return cliente.validarVotacionPorUsuario(int.class, "" + idUsuario);
    }

}
