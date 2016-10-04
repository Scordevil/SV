/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dao;

import com.sv.modelos.Ciudad;
import com.sv.modelos.Comite;
import com.sv.modelos.Departamento;
import com.sv.modelos.Empresa;
import com.sv.modelos.Estado;
import com.sv.modelos.Tipousuario;
import com.sv.modelos.Usuario;
import com.sv.webservices.clientes.ClienteConsultarComite;
import com.sv.webservices.clientes.ClienteConsultarComitePorUsuario;
import com.sv.webservices.clientes.ClienteConsultarComites;
import com.sv.webservices.clientes.ClienteConsultarComitesPorUsuario;
import com.sv.webservices.clientes.ClienteConsultarUsuarios;
import com.sv.webservices.clientes.ClienteEliminarComite;
import com.sv.webservices.clientes.ClienteModificarComite;
import com.sv.webservices.clientes.ClienteRegistrarComite;
import com.sv.webservices.clientes.ClienteValidarVotacionPorUsuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    public List<Comite> consultarComites() {
        ClienteConsultarComites cliente = new ClienteConsultarComites();
        List<HashMap> datos = cliente.consultarComite(List.class);
        List<Comite> comites = new ArrayList<>();

        EmpresaDao ed = new EmpresaDao();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map1 = (HashMap) datos.get(i).get("idEmpresa");
            comites.add(new Comite((int) datos.get(i).get("idComite"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("descripcion"),
                    (String) datos.get(i).get("fechaAperturaString"),
                    (String) datos.get(i).get("fechaAperturaString"),
                    ed.consultarEmpresa(new Empresa((int) map1.get("idEmpresa")))
                  ));

        }

        return comites;
    }

    public List<Comite> consultarComitesPorUsuario(int idUsuario) {
        ClienteConsultarComitesPorUsuario cliente = new ClienteConsultarComitesPorUsuario();
        List<HashMap> datos = cliente.consultarComitesPorUsuario(List.class, idUsuario+"");
        List<Comite> comites = new ArrayList<>();

        EmpresaDao ed = new EmpresaDao();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map1 = (HashMap) datos.get(i).get("idEmpresa");
            comites.add(new Comite((int) datos.get(i).get("idComite"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("descripcion"),
                    (String) datos.get(i).get("fechaApertura"),
                    (String) datos.get(i).get("fechaCierre"),
                    ed.consultarEmpresa(new Empresa((int) map1.get("idEmpresa"))),
                    new Estado((int) datos.get(i).get("idEstado"))));

        }

        return comites;
    }

}
