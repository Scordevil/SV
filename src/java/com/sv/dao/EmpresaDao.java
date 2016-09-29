/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dao;

import com.sv.clases.Sesion;
import com.sv.modelos.Empresa;
import com.sv.modelos.Usuario;
import com.sv.webservices.clientes.ClienteConsultarEmpresa;
import com.sv.webservices.clientes.ClienteConsultarEmpresas;
import com.sv.webservices.clientes.ClienteConsultarEmpresasConComite;
import com.sv.webservices.clientes.ClienteConsultarEmpresasPorAdministrador;
import com.sv.webservices.clientes.ClienteEditarEmpresa;
import com.sv.webservices.clientes.ClienteEliminarEmpresa;
import com.sv.webservices.clientes.ClienteRegistrarEmpresa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author CristianCamilo
 */
public class EmpresaDao {

    public int registrarEmpresa(Empresa empresa) {

        ClienteRegistrarEmpresa cliente = new ClienteRegistrarEmpresa();
        return cliente.crearEmpresas(Integer.class,
                empresa.getNombre(),
                empresa.getDireccion(),
                empresa.getNit(),
                empresa.getTelefono(),
                empresa.getCorreo(),
                empresa.getUrlLogo(),
                empresa.getUrlBanner(),
                "" + empresa.getIdUsuario().getIdUsuario(),
                "" + empresa.getComite());
    }

    public int modificarEmpresa(Empresa empresa) {
        ClienteEditarEmpresa cliente = new ClienteEditarEmpresa();
        return cliente.editarEmpresas(Integer.class,
                "" + empresa.getIdEmpresa(),
                empresa.getNombre(),
                empresa.getDireccion(),
                empresa.getNit(),
                empresa.getTelefono(),
                empresa.getCorreo(),
                empresa.getUrlLogo(),
                empresa.getUrlBanner(),
                "" + empresa.getIdUsuario().getIdUsuario(),
                "" + empresa.getComite());
    }

    public int eliminarEmpresa(Empresa empresa) {
        ClienteEliminarEmpresa cliente = new ClienteEliminarEmpresa();
        return cliente.eliminarEmpresas(int.class, "" + empresa.getIdEmpresa());
    }

    public List<Empresa> consultarEmpresas() {
        ClienteConsultarEmpresas cliente = new ClienteConsultarEmpresas();
        List<HashMap> datos = cliente.consultarEmpresas(List.class);
        List<Empresa> empresas = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idUsuario");

            empresas.add(new Empresa((int) datos.get(i).get("idEmpresa"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("direccion"),
                    (String) datos.get(i).get("nit"),
                    (String) datos.get(i).get("telefono"),
                    (String) datos.get(i).get("correo"),
                    (String) datos.get(i).get("urlLogo"),
                    (String) datos.get(i).get("urlBanner"),
                    (int) datos.get(i).get("comite"),
                    new Usuario((int) map.get("idUsuario"))));
        }

        return empresas;
    }

    public List<Empresa> consultarEmpresasPorAdministrador() {
        ClienteConsultarEmpresasPorAdministrador cliente = new ClienteConsultarEmpresasPorAdministrador();
        List<HashMap> datos = cliente.consultarEmpresasPorAdministrador(List.class, Sesion.obtenerSesion().getIdUsuario()+"");
        List<Empresa> empresas = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idUsuario");

            empresas.add(new Empresa((int) datos.get(i).get("idEmpresa"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("direccion"),
                    (String) datos.get(i).get("nit"),
                    (String) datos.get(i).get("telefono"),
                    (String) datos.get(i).get("correo"),
                    (String) datos.get(i).get("urlLogo"),
                    (String) datos.get(i).get("urlBanner"),
                    (int) datos.get(i).get("comite"),
                    new Usuario((int) map.get("idUsuario"))));
        }

        return empresas;
    }

    public List<Empresa> consultarEmpresasConComite() {
        ClienteConsultarEmpresasConComite cliente = new ClienteConsultarEmpresasConComite();
        List<HashMap> datos = cliente.consultarEmpresasConComite(List.class);
        List<Empresa> empresas = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idUsuario");

            empresas.add(new Empresa((int) datos.get(i).get("idEmpresa"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("direccion"),
                    (String) datos.get(i).get("nit"),
                    (String) datos.get(i).get("telefono"),
                    (String) datos.get(i).get("correo"),
                    (String) datos.get(i).get("urlLogo"),
                    (String) datos.get(i).get("urlBanner"),
                    (int) datos.get(i).get("comite"),
                    new Usuario((int) map.get("idUsuario"))));
        }

        return empresas;
    }

    public Empresa consultarEmpresa(Empresa empresa) {
        ClienteConsultarEmpresa cliente = new ClienteConsultarEmpresa();
        return cliente.consultarEmpresa(Empresa.class, "" + empresa.getIdEmpresa(), empresa.getNombre());
    }

}
