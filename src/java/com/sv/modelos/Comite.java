/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.modelos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author CristianCamilo
 */
@Entity
@Table(name = "comite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comite.findAll", query = "SELECT c FROM Comite c"),
    @NamedQuery(name = "Comite.findByIdComite", query = "SELECT c FROM Comite c WHERE c.idComite = :idComite"),
    @NamedQuery(name = "Comite.findByNombre", query = "SELECT c FROM Comite c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Comite.findByDescripcion", query = "SELECT c FROM Comite c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Comite.findByFechaApertura", query = "SELECT c FROM Comite c WHERE c.fechaApertura = :fechaApertura"),
    @NamedQuery(name = "Comite.findByFechaCierre", query = "SELECT c FROM Comite c WHERE c.fechaCierre = :fechaCierre")})
public class Comite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idComite")
    private Integer idComite;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fechaApertura")
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;
    @Column(name = "fechaCierre")
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    @JoinTable(name = "comite_inventario", joinColumns = {
        @JoinColumn(name = "idComite", referencedColumnName = "idComite")}, inverseJoinColumns = {
        @JoinColumn(name = "idInventario", referencedColumnName = "idInventario")})
    @ManyToMany
    private Collection<Inventario> inventarioCollection;
    @JoinTable(name = "comite_usuario", joinColumns = {
        @JoinColumn(name = "idComite", referencedColumnName = "idComite")}, inverseJoinColumns = {
        @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")})
    @ManyToMany
    private Collection<Usuario> usuarioCollection;
    @JoinColumn(name = "idEmpresa", referencedColumnName = "idEmpresa")
    @ManyToOne(optional = false)
    private Empresa idEmpresa;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne(optional = false)
    private Estado idEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComite")
    private Collection<Votacion> votacionCollection;

    public Comite(){
        this.idEmpresa = new Empresa();
        this.idEstado = new Estado();
        this.idComite = 0;
    }

    public Comite(Integer idComite) {
        this.idComite = idComite;
    }

    public Comite(Integer idComite, String nombre, String descripcion, Date fechaApertura, Date fechaCierre, Empresa idEmpresa, Estado idEstado) {
        this.idComite = idComite;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.idEmpresa = idEmpresa;
        this.idEstado = idEstado;
    }
    
    
        public Comite(Integer idComite, String nombre, String descripcion, Date fechaApertura, Date fechaCierre, Empresa idEmpresa) {
        this.idComite = idComite;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.idEmpresa = idEmpresa;
    }
    

    public Integer getIdComite() {
        return idComite;
    }

    public void setIdComite(Integer idComite) {
        this.idComite = idComite;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Inventario> getInventarioCollection() {
        return inventarioCollection;
    }

    public void setInventarioCollection(Collection<Inventario> inventarioCollection) {
        this.inventarioCollection = inventarioCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Votacion> getVotacionCollection() {
        return votacionCollection;
    }

    public void setVotacionCollection(Collection<Votacion> votacionCollection) {
        this.votacionCollection = votacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComite != null ? idComite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comite)) {
            return false;
        }
        Comite other = (Comite) object;
        if ((this.idComite == null && other.idComite != null) || (this.idComite != null && !this.idComite.equals(other.idComite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.modelos.Comite[ idComite=" + idComite + " ]";
    }
    
}
