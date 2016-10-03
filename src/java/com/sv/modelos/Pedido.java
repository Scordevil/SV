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
import javax.persistence.Lob;
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
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByIdPedido", query = "SELECT p FROM Pedido p WHERE p.idPedido = :idPedido"),
    @NamedQuery(name = "Pedido.findByNombreHijo", query = "SELECT p FROM Pedido p WHERE p.nombreHijo = :nombreHijo"),
    @NamedQuery(name = "Pedido.findByEdadHijo", query = "SELECT p FROM Pedido p WHERE p.edadHijo = :edadHijo"),
    @NamedQuery(name = "Pedido.findBySexoHijo", query = "SELECT p FROM Pedido p WHERE p.sexoHijo = :sexoHijo"),
    @NamedQuery(name = "Pedido.findByNombreEncargado", query = "SELECT p FROM Pedido p WHERE p.nombreEncargado = :nombreEncargado"),
    @NamedQuery(name = "Pedido.findByCiudadEncargado", query = "SELECT p FROM Pedido p WHERE p.ciudadEncargado = :ciudadEncargado"),
    @NamedQuery(name = "Pedido.findByEmailEncargado", query = "SELECT p FROM Pedido p WHERE p.emailEncargado = :emailEncargado"),
    @NamedQuery(name = "Pedido.findByTelefonoEncargado", query = "SELECT p FROM Pedido p WHERE p.telefonoEncargado = :telefonoEncargado"),
    @NamedQuery(name = "Pedido.findByFechaEntrega", query = "SELECT p FROM Pedido p WHERE p.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Pedido.findByHoraEntrega", query = "SELECT p FROM Pedido p WHERE p.horaEntrega = :horaEntrega")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPedido")
    private Integer idPedido;
    @Column(name = "nombreHijo")
    private String nombreHijo;
    @Column(name = "edadHijo")
    private Integer edadHijo;
    @Column(name = "sexoHijo")
    private String sexoHijo;
    @Column(name = "nombreEncargado")
    private String nombreEncargado;
    @Column(name = "ciudadEncargado")
    private String ciudadEncargado;
    @Column(name = "emailEncargado")
    private String emailEncargado;
    @Column(name = "telefonoEncargado")
    private String telefonoEncargado;
    @Column(name = "fechaEntrega")
    private int idInventario;
    @Column(name = "idInventario")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Column(name = "horaEntrega")
    @Temporal(TemporalType.DATE)
    private Date horaEntrega;
    @Lob
    @Column(name = "DireccionEntrega")
    private String direccionEntrega;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Pedido() {
        this.idUsuario = new Usuario();
        this.idPedido = 0;
    }
    
     public Pedido(String nombreHijo, Integer edadHijo) {
        this.nombreHijo = nombreHijo;
        this.edadHijo = edadHijo;
    }

    public Pedido(Integer idPedido, String nombreHijo, Integer edadHijo, String sexoHijo, String nombreEncargado, String ciudadEncargado, String emailEncargado, String telefonoEncargado, Date fechaEntrega, Date horaEntrega, String direccionEntrega, Usuario idUsuario) {
        this.idPedido = idPedido;
        this.nombreHijo = nombreHijo;
        this.edadHijo = edadHijo;
        this.sexoHijo = sexoHijo;
        this.nombreEncargado = nombreEncargado;
        this.ciudadEncargado = ciudadEncargado;
        this.emailEncargado = emailEncargado;
        this.telefonoEncargado = telefonoEncargado;
        this.fechaEntrega = fechaEntrega;
        this.horaEntrega = horaEntrega;
        this.direccionEntrega = direccionEntrega;
        this.idUsuario = idUsuario;
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombreHijo() {
        return nombreHijo;
    }

    public void setNombreHijo(String nombreHijo) {
        this.nombreHijo = nombreHijo;
    }

    public Integer getEdadHijo() {
        return edadHijo;
    }

    public void setEdadHijo(Integer edadHijo) {
        this.edadHijo = edadHijo;
    }

    public String getSexoHijo() {
        return sexoHijo;
    }

    public void setSexoHijo(String sexoHijo) {
        this.sexoHijo = sexoHijo;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public String getCiudadEncargado() {
        return ciudadEncargado;
    }

    public void setCiudadEncargado(String ciudadEncargado) {
        this.ciudadEncargado = ciudadEncargado;
    }

    public String getEmailEncargado() {
        return emailEncargado;
    }

    public void setEmailEncargado(String emailEncargado) {
        this.emailEncargado = emailEncargado;
    }

    public String getTelefonoEncargado() {
        return telefonoEncargado;
    }

    public void setTelefonoEncargado(String telefonoEncargado) {
        this.telefonoEncargado = telefonoEncargado;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(Date horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.modelos.Pedido[ idPedido=" + idPedido + " ]";
    }

}
