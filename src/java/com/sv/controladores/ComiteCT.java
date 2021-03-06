/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.controladores;

import com.sv.clases.Sesion;
import com.sv.dao.ComiteDao;
import com.sv.dao.CorreoDao;
import com.sv.dao.InventarioDao;
import com.sv.dao.UsuarioDao;
import com.sv.modelos.Comite;
import com.sv.modelos.Empresa;
import com.sv.modelos.Estado;
import com.sv.modelos.Inventario;
import com.sv.modelos.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author CristianCamilo
 */
public class ComiteCT {

    private Comite comite;
    private List<Comite> comites;
    private List<Inventario> inventarios;
    private List<Usuario> empleadosSS;
    private List<Usuario> empleados;
    private int valor;
    private String buscar;

    public ComiteCT() {
        comite = new Comite();
        comites = new ArrayList<>();
        inventarios = new ArrayList<>();
        empleados = new ArrayList<>();
        empleadosSS = new ArrayList<>();
        valor = 0;

    }

    @PostConstruct
    public void init() {

        consultarComites();

    }

    public Comite getComite() {
        return comite;
    }

    public void setComite(Comite comite) {
        this.comite = comite;
    }

    public List<Comite> getComites() {
        return comites;
    }

    public void setComites(List<Comite> comites) {
        this.comites = comites;
    }

    public List<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    public List<Usuario> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Usuario> empleados) {
        this.empleados = empleados;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public List<Usuario> getEmpleadosSS() {
        return empleadosSS;
    }

    public void setEmpleadosSS(List<Usuario> empleadosSS) {
        this.empleadosSS = empleadosSS;
    }

    public void registrar() {
        ComiteDao comiteDao = new ComiteDao();
        CorreoDao correoDao = new CorreoDao();
        UsuarioDao usuarioDao = new UsuarioDao();
        InventarioDao inventarioDao = new InventarioDao();
        List<Inventario> inventario = inventarioDao.ConsultarJuguetesPorEmpresa(comite.getIdEmpresa());
        if (!inventario.isEmpty()) {
            if (!empleados.isEmpty()) {
                comite.setIdEstado(new Estado(1));
                int resultado = comiteDao.registrarComite(comite);
                if (resultado == 1) {
                    comite = comiteDao.consultarComite(comite);
                    for (int i = 0; i < empleados.size(); i++) {
                        int r = usuarioDao.registrarUsuarioComite(empleados.get(i), comite);
                        if (r == 1) {
                            correoDao.EnviarCorreoVotacion(empleados.get(i));
                        }
                    }

                    for (int i = 0; i < inventario.size(); i++) {
                        inventarioDao.registrarInventarioComite(inventario.get(i), comite);
                    }
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Comite registrado exitosamente");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Imposible registrar comite");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }

                comite = new Comite();
                inventarios.clear();
                empleados.clear();
                empleadosSS.clear();

            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione empleados para el comite");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No existe inventario para esta empresa");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void consultarComiteUsuario(int idUsuario) {
        ComiteDao comiteDao = new ComiteDao();
        valor = comiteDao.consultarComitePorUsuario(idUsuario);//mayor a 0 pertenece al comite de votacion

    }

    public void consultarComites() {
        ComiteDao comiteDao = new ComiteDao();

        if (Sesion.obtenerSesion().getIdTipoUsuario().getIdTipoUsuario() == 1) {
            comites = comiteDao.consultarComites();
        } else {
            comites = comiteDao.consultarComitesPorUsuario(Sesion.obtenerSesion().getIdUsuario());
        }
    }

    public void buscarSegunEmpresa() {
        empleadosSS = new ArrayList<>();
        UsuarioDao usuarioDao = new UsuarioDao();
        if (buscar.isEmpty()) {
            empleadosSS = usuarioDao.ConsultarUsuariosSegunEmpresa(new Empresa(comite.getIdEmpresa().getIdEmpresa()));
        } else if (buscar.length() >= 2) {
            empleadosSS = usuarioDao.ConsultarUsuariosSegunEmpresaLikeInicioComite(new Empresa(comite.getIdEmpresa().getIdEmpresa()), buscar);
        } else {
            empleadosSS = new ArrayList<>();
        }
    }

    public void buscarSegunEmpresa2() {
        empleadosSS = new ArrayList<>();
        UsuarioDao usuarioDao = new UsuarioDao();

        empleadosSS = usuarioDao.ConsultarUsuariosSegunEmpresa(new Empresa(comite.getIdEmpresa().getIdEmpresa()));

    }

}
