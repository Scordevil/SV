/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CristianCamilo
 */
@Entity
@Table(name = "votacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Votacion.findAll", query = "SELECT v FROM Votacion v"),
    @NamedQuery(name = "Votacion.findByIdVotacion", query = "SELECT v FROM Votacion v WHERE v.idVotacion = :idVotacion"),
    @NamedQuery(name = "Votacion.findByCalificacion", query = "SELECT v FROM Votacion v WHERE v.calificacion = :calificacion"),
    @NamedQuery(name = "Votacion.findByFechaCalificacion", query = "SELECT v FROM Votacion v WHERE v.fechaCalificacion = :fechaCalificacion")})
public class Votacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVotacion")
    private Integer idVotacion;
    @Column(name = "calificacion")
    private String calificacion;
    @Column(name = "fechaCalificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCalificacion;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "idInventario", referencedColumnName = "idInventario")
    @ManyToOne(optional = false)
    private Inventario idInventario;
    @JoinColumn(name = "idComite", referencedColumnName = "idComite")
    @ManyToOne(optional = false)
    private Comite idComite;

    public Votacion() {        
        this.idComite = new Comite();
        this.idInventario = new Inventario();
        this.idUsuario = new Usuario();
        this.idVotacion = 0;
    }

    public Votacion(Integer idVotacion, String calificacion, Date fechaCalificacion, Usuario idUsuario, Inventario idInventario, Comite idComite) {
        this.idVotacion = idVotacion;
        this.calificacion = calificacion;
        this.fechaCalificacion = fechaCalificacion;
        this.idUsuario = idUsuario;
        this.idInventario = idInventario;
        this.idComite = idComite;
    }
    
    

    public Votacion(Integer idVotacion) {
        this.idVotacion = idVotacion;
    }

    public Integer getIdVotacion() {
        return idVotacion;
    }

    public void setIdVotacion(Integer idVotacion) {
        this.idVotacion = idVotacion;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFechaCalificacion() {
        return fechaCalificacion;
    }

    public void setFechaCalificacion(Date fechaCalificacion) {
        this.fechaCalificacion = fechaCalificacion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Inventario getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Inventario idInventario) {
        this.idInventario = idInventario;
    }

    public Comite getIdComite() {
        return idComite;
    }

    public void setIdComite(Comite idComite) {
        this.idComite = idComite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVotacion != null ? idVotacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Votacion)) {
            return false;
        }
        Votacion other = (Votacion) object;
        if ((this.idVotacion == null && other.idVotacion != null) || (this.idVotacion != null && !this.idVotacion.equals(other.idVotacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.modelos.Votacion[ idVotacion=" + idVotacion + " ]";
    }
    
}
