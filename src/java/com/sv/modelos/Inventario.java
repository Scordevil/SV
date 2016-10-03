/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.modelos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author CristianCamilo
 */
@Entity
@Table(name = "inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i"),
    @NamedQuery(name = "Inventario.findByIdInventario", query = "SELECT i FROM Inventario i WHERE i.idInventario = :idInventario"),
    @NamedQuery(name = "Inventario.findByCodigo", query = "SELECT i FROM Inventario i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "Inventario.findByNombre", query = "SELECT i FROM Inventario i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Inventario.findByEdadDesde", query = "SELECT i FROM Inventario i WHERE i.edadDesde = :edadDesde"),
    @NamedQuery(name = "Inventario.findByEdadHasta", query = "SELECT i FROM Inventario i WHERE i.edadHasta = :edadHasta"),
    @NamedQuery(name = "Inventario.findByGenero", query = "SELECT i FROM Inventario i WHERE i.genero = :genero"),
    @NamedQuery(name = "Inventario.findByCantidad", query = "SELECT i FROM Inventario i WHERE i.cantidad = :cantidad"),
    @NamedQuery(name = "Inventario.findByIdEmpresa", query = "SELECT i FROM Inventario i WHERE i.idEmpresa = :idEmpresa")})
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInventario")
    private Integer idInventario;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "edadDesde")
    private Integer edadDesde;
    @Column(name = "edadHasta")
    private Integer edadHasta;
    @Column(name = "genero")
    private String genero;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Lob
    @Column(name = "url1")
    private String url1;
    @Lob
    @Column(name = "url2")
    private String url2;
    @Lob
    @Column(name = "url3")
    private String url3;
    @Lob
    @Column(name = "url4")
    private String url4;
    @Lob
    @Column(name = "url5")
    private String url5;
    @Lob
    @Column(name = "url6")
    private String url6;
    @Lob
    @Column(name = "url7")
    private String url7;
    @Lob
    @Column(name = "url8")
    private String url8;
    @Lob
    @Column(name = "url9")
    private String url9;
    @Lob
    @Column(name = "url10")
    private String url10;
    @Lob
    @Column(name = "url11")
    private String url11;
    @Lob
    @Column(name = "url12")
    private String url12;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Lob
    @Column(name = "sumatoria")
    private Integer sumatoria;
    @ManyToMany(mappedBy = "inventarioCollection")
    private Collection<Comite> comiteCollection;
    @JoinColumn(name = "idEmpresa", referencedColumnName = "idEmpresa")
    @ManyToOne(optional = false)
    private Empresa idEmpresa;
    @JoinColumn(name = "pedido", referencedColumnName = "pedido")
    @ManyToOne(optional = false)
    private Pedido pedido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInventario")
    private Collection<Votacion> votacionCollection;

    public Inventario() {
        this.idEmpresa = new Empresa();
        this.idInventario = 0;
    }

    public Inventario(Integer idInventario, String codigo, String nombre, String descripcion, Integer edadDesde, Integer edadHasta, String genero, Integer cantidad, String url1, String url2, String url3, String url4, String url5, String url6, String url7, String url8, String url9, String url10, String url11, String url12, String observacion, Empresa idEmpresa) {
        this.idInventario = idInventario;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.edadDesde = edadDesde;
        this.edadHasta = edadHasta;
        this.genero = genero;
        this.cantidad = cantidad;
        this.url1 = url1;
        this.url2 = url2;
        this.url3 = url3;
        this.url4 = url4;
        this.url5 = url5;
        this.url6 = url6;
        this.url7 = url7;
        this.url8 = url8;
        this.url9 = url9;
        this.url10 = url10;
        this.url11 = url11;
        this.url12 = url12;
        this.observacion = observacion;
        this.idEmpresa = idEmpresa;
    }

    public Inventario(Integer idInventario, String codigo, String nombre, String descripcion, Integer edadDesde, Integer edadHasta, String genero, Integer cantidad, String url1, String url2, String url3, String url4, String url5, String url6, String url7, String url8, String url9, String url10, String url11, String url12, String observacion, Integer sumatoria, Empresa idEmpresa) {
        this.idInventario = idInventario;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.edadDesde = edadDesde;
        this.edadHasta = edadHasta;
        this.genero = genero;
        this.cantidad = cantidad;
        this.url1 = url1;
        this.url2 = url2;
        this.url3 = url3;
        this.url4 = url4;
        this.url5 = url5;
        this.url6 = url6;
        this.url7 = url7;
        this.url8 = url8;
        this.url9 = url9;
        this.url10 = url10;
        this.url11 = url11;
        this.url12 = url12;
        this.observacion = observacion;
        this.sumatoria = sumatoria;
        this.idEmpresa = idEmpresa;
    }

    public Inventario(Integer idInventario, String nombre, String genero, Empresa idEmpresa, Pedido pedido) {
        this.idInventario = idInventario;
        this.nombre = nombre;
        this.genero = genero;
        this.idEmpresa = idEmpresa;
        this.pedido = pedido;
    }

    public Inventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Integer getEdadDesde() {
        return edadDesde;
    }

    public void setEdadDesde(Integer edadDesde) {
        this.edadDesde = edadDesde;
    }

    public Integer getEdadHasta() {
        return edadHasta;
    }

    public void setEdadHasta(Integer edadHasta) {
        this.edadHasta = edadHasta;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getUrl4() {
        return url4;
    }

    public void setUrl4(String url4) {
        this.url4 = url4;
    }

    public String getUrl5() {
        return url5;
    }

    public void setUrl5(String url5) {
        this.url5 = url5;
    }

    public String getUrl6() {
        return url6;
    }

    public void setUrl6(String url6) {
        this.url6 = url6;
    }

    public String getUrl7() {
        return url7;
    }

    public void setUrl7(String url7) {
        this.url7 = url7;
    }

    public String getUrl8() {
        return url8;
    }

    public void setUrl8(String url8) {
        this.url8 = url8;
    }

    public String getUrl9() {
        return url9;
    }

    public void setUrl9(String url9) {
        this.url9 = url9;
    }

    public String getUrl10() {
        return url10;
    }

    public void setUrl10(String url10) {
        this.url10 = url10;
    }

    public String getUrl11() {
        return url11;
    }

    public void setUrl11(String url11) {
        this.url11 = url11;
    }

    public String getUrl12() {
        return url12;
    }

    public void setUrl12(String url12) {
        this.url12 = url12;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getSumatoria() {
        return sumatoria;
    }

    public void setSumatoria(int sumatoria) {
        this.sumatoria = sumatoria;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Comite> getComiteCollection() {
        return comiteCollection;
    }

    public void setComiteCollection(Collection<Comite> comiteCollection) {
        this.comiteCollection = comiteCollection;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
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
        hash += (idInventario != null ? idInventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.idInventario == null && other.idInventario != null) || (this.idInventario != null && !this.idInventario.equals(other.idInventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.modelos.Inventario[ idInventario=" + idInventario + " ]";
    }

}
