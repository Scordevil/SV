/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.controladores;

import com.sv.clases.Sesion;
import com.sv.dao.ComiteDao;
import com.sv.dao.InventarioDao;
import com.sv.dao.VotacionDao;
import com.sv.modelos.Comite;
import com.sv.modelos.Inventario;
import com.sv.modelos.Votacion;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RateEvent;

/**
 *
 * @author CristianCamilo
 */
public class VotacionCT {

    private List<Inventario> inventarios;
    private Votacion votacion;
    private int calificacion;
    private List<Votacion> votaciones;
    private Inventario juguete;

    public VotacionCT() {
        votacion = new Votacion();
        inventarios = new ArrayList<>();
        calificacion = 0;
        votaciones = new ArrayList<>();
        juguete = new Inventario();
    }
    
    @PostConstruct
    public void init() {

//        InventarioDao inventarioDao = new InventarioDao();
//        int idComite = 0;
//        int valor = 0;
//        ComiteDao comiteDao = new ComiteDao();
//        idComite = comiteDao.consultarComitePorUsuario(Sesion.obtenerSesion().getIdUsuario());
//        if (idComite > 0) {
//            valor = comiteDao.validarVotacionPorUsuario(Sesion.obtenerSesion().getIdUsuario()); //mayor a 0, ya voto
//            if (valor > 0) {
//                inventarios = inventarioDao.consultarJuguetes();
//            } else {
//                inventarios = inventarioDao.ConsultarJuguetesComite(idComite);
//            }
//        } else {
//            inventarios = inventarioDao.consultarJuguetes();
//        }
      


    }

    public Votacion getVotacion() {
        return votacion;
    }

    public void setVotacion(Votacion votacion) {
        this.votacion = votacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Inventario getJuguete() {
        return juguete;
    }

    public void setJuguete(Inventario juguete) {
        this.juguete = juguete;
    }

    public List<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    public void onrate(RateEvent rateEvent) {
//        votacion = new Votacion();
//        votacion.getIdInventario().setIdInventario(juguete.getIdInventario());
//        votacion.setIdUsuario(Sesion.obtenerSesion());
//        votacion.setFechaCalificacion(new Date());
        calificacion = Integer.parseInt(rateEvent.getRating().toString());
//        votacion.setCalificacion("" + calificacion);
//        ComiteDao comiteDao = new ComiteDao();
//        votacion.setIdComite(new Comite(comiteDao.consultarComitePorUsuario(votacion.getIdUsuario().getIdUsuario())));
//
//        boolean existe = false;
//        for (int i = 0; i < votaciones.size(); i++) {
//            if (votaciones.get(i).getIdInventario().getIdInventario().equals(votacion.getIdInventario())) {
//                existe = true;
//            }
//        }
//        if (!existe) {
//            votaciones.add(votacion);
//        } else {
//            for (int i = 0; i < votaciones.size(); i++) {
//                if (votaciones.get(i).getIdInventario().getIdInventario().equals(votacion.getIdInventario())) {
//                    votaciones.set(i, votacion);
//                }
//            }
//        }
    }

//    //Metodos
//    public void registrarVotaciones() {
//        VotacionDao votacionDao = new VotacionDao();
//        int resultado = 0;
//        for (int i = 0; i < votaciones.size(); i++) {
//            resultado = votacionDao.registrarVotacion(votaciones.get(i));
//        }
//
//        if (resultado == 1) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Votacion Registrada");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        } else if (resultado == 0) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ocurrio algun problema en el proceso de votacion");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//        votaciones = new ArrayList<>();
//        calificacion = 0;
//    }
//    //Metodos
    public void registrarVotaciones(int idUsuario, int idInventario) {
        VotacionDao votacionDao = new VotacionDao();
        int resultado = 0;
        Votacion vota = new Votacion();
        vota.setCalificacion(calificacion + "");
        vota.getIdInventario().setIdInventario(idInventario);
        vota.getIdUsuario().setIdUsuario(idUsuario);
        ComiteDao comiteDao = new ComiteDao();
        vota.setIdComite(new Comite(comiteDao.consultarComitePorUsuario(idUsuario)));
        vota.setFechaCalificacion(new Date());

        resultado = votacionDao.registrarVotacion(vota);

        if (resultado == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Votacion Registrada");
            FacesContext.getCurrentInstance().addMessage(null, message);
   
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ocurrio algun problema en el proceso de votacion");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        votaciones = new ArrayList<>();
//        calificacion = 0;
    }

    public void consultarJuguetePorRangoYEdad() {
        InventarioDao inventarioDao = new InventarioDao();
        inventarios = inventarioDao.ConsultarJuguetesRangoGenero(juguete.getEdadDesde(), juguete.getEdadHasta(), juguete.getGenero(), Sesion.obtenerSesion().getIdUsuario());

    }

}
