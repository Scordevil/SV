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
import com.sv.modelos.Tipousuario;
import com.sv.modelos.Usuario;
import com.sv.webservices.clientes.ClienteBuscarUsuariosPorTipo;
import com.sv.webservices.clientes.ClienteConsultarTipoUsuarios;
import com.sv.webservices.clientes.ClienteConsultarTipoUsuariosRestringidos;
import com.sv.webservices.clientes.ClienteConsultarUsuarioPorCC;
import com.sv.webservices.clientes.ClienteConsultarUsuarioPorLogin;
import com.sv.webservices.clientes.ClienteConsultarUsuarios;
import com.sv.webservices.clientes.ClienteConsultarUsuariosEmpresa;
import com.sv.webservices.clientes.ClienteConsultarUsuariosSegunTipo;
import com.sv.webservices.clientes.ClienteEditarUsuario;
import com.sv.webservices.clientes.ClienteEliminarUsuario;
import com.sv.webservices.clientes.ClienteQuitarAcceso;
import com.sv.webservices.clientes.ClienteRegistrarUsuario;
import com.sv.webservices.clientes.ClienteRegistrarUsuarioComite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class UsuarioDao {

    public int registrarUsuario(Usuario usuario) {
        ClienteRegistrarUsuario cliente = new ClienteRegistrarUsuario();

        CiudadDao cd = new CiudadDao();
        DepartamentoDao dd = new DepartamentoDao();
        TipoUsuarioDao td = new TipoUsuarioDao();
        EmpresaDao ed = new EmpresaDao();

        usuario.setIdCiudad(cd.consultarCiudad(usuario.getIdCiudad()));
        usuario.setIdDepartamento(dd.consultarDepartamento(usuario.getIdDepartamento()));
        usuario.setIdTipoUsuario(td.consultarTipoUsuario(usuario.getIdTipoUsuario()));
        usuario.setIdEmpresa(ed.consultarEmpresa(usuario.getIdEmpresa()));

        return cliente.registrarUsuario(Integer.class,
                "" + usuario.getIdEmpresa().getIdEmpresa(),
                "" + usuario.getIdTipoUsuario().getIdTipoUsuario(),
                "" + usuario.getIdCiudad().getIdCiudad(),
                "" + usuario.getIdDepartamento().getIdDepartamento(),
                usuario.getNombre(),
                "" + usuario.getCodigoEmpleado(),
                "" + usuario.getCc(),
                usuario.getTelefono(),
                usuario.getEmail(),
                usuario.getUsuario(),
                usuario.getContrasena(),
                usuario.getOficina());
    }

    public int editarUsuario(Usuario usuario) {
        ClienteEditarUsuario cliente = new ClienteEditarUsuario();

        CiudadDao cd = new CiudadDao();
        DepartamentoDao dd = new DepartamentoDao();
        TipoUsuarioDao td = new TipoUsuarioDao();
        EmpresaDao ed = new EmpresaDao();

        usuario.setIdCiudad(cd.consultarCiudad(usuario.getIdCiudad()));
        usuario.setIdDepartamento(dd.consultarDepartamento(usuario.getIdDepartamento()));
        usuario.setIdTipoUsuario(td.consultarTipoUsuario(usuario.getIdTipoUsuario()));
        usuario.setIdEmpresa(ed.consultarEmpresa(usuario.getIdEmpresa()));

        return cliente.editarUsuario(Integer.class,
                "" + usuario.getIdUsuario(),
                "" + usuario.getIdEmpresa().getIdEmpresa(),
                "" + usuario.getIdTipoUsuario().getIdTipoUsuario(),
                "" + usuario.getIdCiudad().getIdCiudad(),
                "" + usuario.getIdDepartamento().getIdDepartamento(),
                usuario.getNombre(),
                "" + usuario.getCodigoEmpleado(),
                "" + usuario.getCc(),
                usuario.getTelefono(),
                usuario.getEmail(),
                usuario.getContrasena(),
                usuario.getUsuario(),
                usuario.getOficina(),
                usuario.getAreaTrabajo());

    }

    public int registrarUsuarioComite(Usuario usuario, Comite comite) {
        ClienteRegistrarUsuarioComite cliente = new ClienteRegistrarUsuarioComite();
        return cliente.registrarUsuariosComite(Integer.class,
                "" + usuario.getIdUsuario(),
                "" + comite.getIdComite());

    }

    public List<Usuario> consultarUsuarios() {
        ClienteConsultarUsuarios cliente = new ClienteConsultarUsuarios();
        List<HashMap> datos = cliente.consultarUsuarios(List.class);
        List<Usuario> usuarios = new ArrayList<>();

        CiudadDao cd = new CiudadDao();
        DepartamentoDao dd = new DepartamentoDao();
        TipoUsuarioDao td = new TipoUsuarioDao();
        EmpresaDao ed = new EmpresaDao();
        UsuarioDao ud = new UsuarioDao();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map1 = (HashMap) datos.get(i).get("idEmpresa");
            HashMap map2 = (HashMap) datos.get(i).get("idCiudad");
            HashMap map3 = (HashMap) datos.get(i).get("idDepartamento");
            HashMap map4 = (HashMap) datos.get(i).get("idTipoUsuario");
            usuarios.add(new Usuario((int) datos.get(i).get("idUsuario"),
                    (String) datos.get(i).get("nombre"),
                    (int) datos.get(i).get("codigoEmpleado"),
                    (String) datos.get(i).get("cc"),
                    (String) datos.get(i).get("telefono"),
                    (String) datos.get(i).get("email"),
                    (String) datos.get(i).get("usuario"),
                    (String) datos.get(i).get("contrasena"),
                    (String) datos.get(i).get("oficina"),
                    (String) datos.get(i).get("areaTrabajo"),
                    td.consultarTipoUsuario(new Tipousuario((int) map4.get(("idTipoUsuario")))),
                    ed.consultarEmpresa(new Empresa((int) map1.get("idEmpresa"))),
                    dd.consultarDepartamento(new Departamento((int) map3.get("idDepartamento"))),
                    cd.consultarCiudad(new Ciudad((int) map2.get("idCiudad")))));
        }

        return usuarios;
    }

    public Usuario consultarUsuarioPorLogin(Usuario usuario) {
        ClienteConsultarUsuarioPorLogin cliente = new ClienteConsultarUsuarioPorLogin();
        Usuario nuevo = cliente.consultarUsuarioPorLogin(Usuario.class, usuario.getUsuario());
        EmpresaDao empresaDao = new EmpresaDao();
        DepartamentoDao departamentoDao = new DepartamentoDao();
        CiudadDao ciudadDao = new CiudadDao();
        TipoUsuarioDao tipoUsuarioDao = new TipoUsuarioDao();

        nuevo.setIdEmpresa(empresaDao.consultarEmpresa(nuevo.getIdEmpresa()));
        nuevo.setIdCiudad(ciudadDao.consultarCiudad(nuevo.getIdCiudad()));
        nuevo.setIdDepartamento(departamentoDao.consultarDepartamento(nuevo.getIdDepartamento()));
        nuevo.setIdTipoUsuario(tipoUsuarioDao.consultarTipoUsuario(nuevo.getIdTipoUsuario()));

        return nuevo;

    }

    public Usuario consultarUsuarioPorCC(String Cc) {
        ClienteConsultarUsuarioPorCC cliente = new ClienteConsultarUsuarioPorCC();
        return cliente.consultarUsuarioPorCC(Usuario.class, Cc);
    }

    public List<Usuario> consultarUsuariosSegunTipoUsuario(Tipousuario tipo) {
        ClienteConsultarUsuariosSegunTipo cliente = new ClienteConsultarUsuariosSegunTipo();
        List<HashMap> datos = cliente.consultarUsuariosPorTipoUsuario(List.class, "" + tipo.getIdTipoUsuario());
        List<Usuario> usuarios = new ArrayList<>();

        CiudadDao cd = new CiudadDao();
        DepartamentoDao dd = new DepartamentoDao();
        TipoUsuarioDao td = new TipoUsuarioDao();
        EmpresaDao ed = new EmpresaDao();
        UsuarioDao ud = new UsuarioDao();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map1 = (HashMap) datos.get(i).get("idEmpresa");
            HashMap map2 = (HashMap) datos.get(i).get("idCiudad");
            HashMap map3 = (HashMap) datos.get(i).get("idDepartamento");
            HashMap map4 = (HashMap) datos.get(i).get("idTipoUsuario");
            usuarios.add(new Usuario((int) datos.get(i).get("idUsuario"),
                    (String) datos.get(i).get("nombre"),
                    (int) datos.get(i).get("codigoEmpleado"),
                    (String) datos.get(i).get("cc"),
                    (String) datos.get(i).get("telefono"),
                    (String) datos.get(i).get("email"),
                    (String) datos.get(i).get("usuario"),
                    (String) datos.get(i).get("contrasena"),
                    (String) datos.get(i).get("oficina"),
                    (String) datos.get(i).get("areaTrabajo"),
                    td.consultarTipoUsuario(new Tipousuario((int) map4.get(("idTipoUsuario")))),
                    ed.consultarEmpresa(new Empresa((int) map1.get("idEmpresa"))),
                    dd.consultarDepartamento(new Departamento((int) map3.get("idDepartamento"))),
                    cd.consultarCiudad(new Ciudad((int) map2.get("idCiudad")))));
        }

        return usuarios;
    }

    public List<Usuario> BuscarUsuariosSegunTipoUsuario(String valor, Tipousuario tipo) {
        ClienteBuscarUsuariosPorTipo cliente = new ClienteBuscarUsuariosPorTipo();
        List<HashMap> datos = cliente.buscarUsuarioPorTipo(List.class, valor, "" + tipo.getIdTipoUsuario());
        List<Usuario> usuarios = new ArrayList<>();

        CiudadDao cd = new CiudadDao();
        DepartamentoDao dd = new DepartamentoDao();
        TipoUsuarioDao td = new TipoUsuarioDao();
        EmpresaDao ed = new EmpresaDao();
        UsuarioDao ud = new UsuarioDao();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map1 = (HashMap) datos.get(i).get("idEmpresa");
            HashMap map2 = (HashMap) datos.get(i).get("idCiudad");
            HashMap map3 = (HashMap) datos.get(i).get("idDepartamento");
            HashMap map4 = (HashMap) datos.get(i).get("idTipoUsuario");
            usuarios.add(new Usuario((int) datos.get(i).get("idUsuario"),
                    (String) datos.get(i).get("nombre"),
                    (int) datos.get(i).get("codigoEmpleado"),
                    (String) datos.get(i).get("cc"),
                    (String) datos.get(i).get("telefono"),
                    (String) datos.get(i).get("email"),
                    (String) datos.get(i).get("usuario"),
                    (String) datos.get(i).get("contrasena"),
                    (String) datos.get(i).get("oficina"),
                    (String) datos.get(i).get("areaTrabajo"),
                    td.consultarTipoUsuario(new Tipousuario((int) map4.get(("idTipoUsuario")))),
                    ed.consultarEmpresa(new Empresa((int) map1.get("idEmpresa"))),
                    dd.consultarDepartamento(new Departamento((int) map3.get("idDepartamento"))),
                    cd.consultarCiudad(new Ciudad((int) map2.get("idCiudad")))));
        }
        return usuarios;
    }

    public List<Tipousuario> consultarTipoUsuarios() {
        ClienteConsultarTipoUsuarios cliente = new ClienteConsultarTipoUsuarios();
        List<HashMap> datos = cliente.consultaTipoUsuarios(List.class);
        List<Tipousuario> tipoUsuarios = new ArrayList<>();

        TipoUsuarioDao td = new TipoUsuarioDao();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map1 = (HashMap) datos.get(i).get("tipoUsuario");
            tipoUsuarios.add(new Tipousuario((int) datos.get(i).get("idTipoUsuario"),
                    (String) datos.get(i).get("nombre"), (String) datos.get(i).get("descripcion")));

        }
        return tipoUsuarios;
    }

    public List<Tipousuario> consultarTipoUsuariosRestringidos() {
        ClienteConsultarTipoUsuariosRestringidos cliente = new ClienteConsultarTipoUsuariosRestringidos();
        List<HashMap> datos = cliente.consultaTipoUsuarios(List.class);
        List<Tipousuario> tipoUsuarios = new ArrayList<>();

        TipoUsuarioDao td = new TipoUsuarioDao();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map1 = (HashMap) datos.get(i).get("tipoUsuario");
            tipoUsuarios.add(new Tipousuario((int) datos.get(i).get("idTipoUsuario"),
                    (String) datos.get(i).get("nombre"), (String) datos.get(i).get("descripcion")));

        }

        return tipoUsuarios;
    }

    public List<Usuario> ConsultarUsuariosSegunEmpresa(Empresa empresa) {
        ClienteConsultarUsuariosEmpresa cliente = new ClienteConsultarUsuariosEmpresa();
        List<HashMap> datos = cliente.consultarUsuariosPorEmpresa(List.class, "" + empresa.getIdEmpresa());
        List<Usuario> usuarios = new ArrayList<>();

        CiudadDao cd = new CiudadDao();
        DepartamentoDao dd = new DepartamentoDao();
        TipoUsuarioDao td = new TipoUsuarioDao();
        EmpresaDao ed = new EmpresaDao();
        UsuarioDao ud = new UsuarioDao();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map1 = (HashMap) datos.get(i).get("idEmpresa");
            HashMap map2 = (HashMap) datos.get(i).get("idCiudad");
            HashMap map3 = (HashMap) datos.get(i).get("idDepartamento");
            HashMap map4 = (HashMap) datos.get(i).get("idTipoUsuario");
            usuarios.add(new Usuario((int) datos.get(i).get("idUsuario"),
                    (String) datos.get(i).get("nombre"),
                    (int) datos.get(i).get("codigoEmpleado"),
                    (String) datos.get(i).get("cc"),
                    (String) datos.get(i).get("telefono"),
                    (String) datos.get(i).get("email"),
                    (String) datos.get(i).get("usuario"),
                    (String) datos.get(i).get("contrasena"),
                    (String) datos.get(i).get("oficina"),
                    (String) datos.get(i).get("areaTrabajo"),
                    td.consultarTipoUsuario(new Tipousuario((int) map4.get(("idTipoUsuario")))),
                    ed.consultarEmpresa(new Empresa((int) map1.get("idEmpresa"))),
                    dd.consultarDepartamento(new Departamento((int) map3.get("idDepartamento"))),
                    cd.consultarCiudad(new Ciudad((int) map2.get("idCiudad")))));
        }

        return usuarios;
    }

    public int eliminarUsuario(Usuario usuario) {
        ClienteEliminarUsuario cliente = new ClienteEliminarUsuario();
        return cliente.eliminarUsuario(int.class, "" + usuario.getIdUsuario());
    }

    public int quitarAcceso(int idUsuario) {
        ClienteQuitarAcceso cliente = new ClienteQuitarAcceso();
        return cliente.quitarAcceso(int.class, "" + idUsuario);
    }

}
