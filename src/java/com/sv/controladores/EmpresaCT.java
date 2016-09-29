/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.controladores;

import com.sv.clases.Sesion;
import com.sv.clases.Upload;
import com.sv.dao.EmpresaDao;
import com.sv.modelos.Empresa;
import com.sv.modelos.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author CristianCamilo
 */
public class EmpresaCT {

    private Empresa empresa;
    private List<Empresa> empresas;
    private List<Empresa> empresasConComite;
    private UploadedFile logo;
    private UploadedFile banner;
    protected Upload archivo;
    protected int operacion;
    private String nombreOperacion;
    private String imagenLogo;
    private String imagenBanner;

    public EmpresaCT() {
        empresa = new Empresa();
        empresas = new ArrayList<>();
        empresasConComite = new ArrayList<>();
        archivo = new Upload();
        operacion = 0;
        nombreOperacion = "Registrar";
    }

    @PostConstruct
    public void init() {
        EmpresaDao empresaDao = new EmpresaDao();
        if (Sesion.obtenerSesion().getIdTipoUsuario().getIdTipoUsuario() == 1) {
            empresas = empresaDao.consultarEmpresas();
        } else if (Sesion.obtenerSesion().getIdTipoUsuario().getIdTipoUsuario() == 2) {
            empresas = empresaDao.consultarEmpresasPorAdministrador();
        }
        empresasConComite = empresaDao.consultarEmpresasConComite();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public UploadedFile getLogo() {
        return logo;
    }

    public void setLogo(UploadedFile logo) {
        this.logo = logo;
    }

    public UploadedFile getBanner() {
        return banner;
    }

    public void setBanner(UploadedFile banner) {
        this.banner = banner;
    }

    public String getImagenLogo() {
        return imagenLogo;
    }

    public void setImagenLogo(String imagenLogo) {
        this.imagenLogo = imagenLogo;
    }

    public String getImagenBanner() {
        return imagenBanner;
    }

    public void setImagenBanner(String imagenBanner) {
        this.imagenBanner = imagenBanner;
    }

    public List<Empresa> getEmpresasConComite() {
        return empresasConComite;
    }

    public void setEmpresasConComite(List<Empresa> empresasConComite) {
        this.empresasConComite = empresasConComite;
    }

    //Metodos 
    public void metodo() throws IOException {
        if (operacion == 0) {
            if (!logo.getFileName().isEmpty() && !banner.getFileName().isEmpty()) {
                uploadBanner();
                uploadLogo();
            } else if (logo.getFileName().isEmpty() && !banner.getFileName().isEmpty()) {
                uploadBanner();
            } else if (banner.getFileName().isEmpty() && !logo.getFileName().isEmpty()) {
                uploadLogo();
            }
            registrar();
        } else if (operacion == 1) {
            if (!logo.getFileName().isEmpty() && !banner.getFileName().isEmpty()) {
                uploadBanner();
                uploadLogo();
            } else if (logo.getFileName().isEmpty() && !banner.getFileName().isEmpty()) {
                uploadBanner();
            } else if (banner.getFileName().isEmpty() && !logo.getFileName().isEmpty()) {
                uploadLogo();
            }
            modificar();
            //Reiniciamos banderas
            nombreOperacion = "Registrar";
            operacion = 0;
            imagenBanner = "";
            imagenLogo = "";
        }
        banner = null;
        logo = null;
    }

    public void seleccionarCRUD(int i) {
        operacion = i;
        if (operacion == 1) {
            nombreOperacion = "Modificar";
            if (empresa.getUrlLogo() != null && empresa.getUrlBanner() != null) {
                imagenBanner = "La empresa ya tiene un imagen de banner seleccionada";
                imagenLogo = "La empresa ya tiene un imagen de logo seleccionado";
            } else if (empresa.getUrlBanner() == null && empresa.getUrlLogo() != null) {
                imagenLogo = "La empresa ya tiene un imagen de logo seleccionado";
                imagenBanner = "La empresa no tiene imagen seleccionada";
            } else if (empresa.getUrlBanner() != null && empresa.getUrlLogo() == null) {
                imagenBanner = "La empresa ya tiene un imagen de banner seleccionada";
                imagenLogo = "La empresa no tiene imagen seleccionada";
            }
        }
        banner = null;
        logo = null;
    }

    public void registrar() {
        EmpresaDao empresaDao = new EmpresaDao();
        empresa.setIdUsuario(new Usuario());
        empresa.setIdUsuario(Sesion.obtenerSesion());
        int resultado = empresaDao.registrarEmpresa(empresa);

        if (resultado == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "La empresa ha sido registrada correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Imposible registrar empresa");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        if (Sesion.obtenerSesion().getIdTipoUsuario().getIdTipoUsuario() == 1) {
            empresas = empresaDao.consultarEmpresas();
        } else if (Sesion.obtenerSesion().getIdTipoUsuario().getIdTipoUsuario() == 2) {
            empresas = empresaDao.consultarEmpresasPorAdministrador();
        }
        empresa = new Empresa();
    }

    public void modificar() {
        EmpresaDao empresaDao = new EmpresaDao();
        int resultado = empresaDao.modificarEmpresa(empresa);

        if (resultado == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "La empresa ha sido modificada correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Imposible modificar empresa");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        if (Sesion.obtenerSesion().getIdTipoUsuario().getIdTipoUsuario() == 1) {
            empresas = empresaDao.consultarEmpresas();
        } else if (Sesion.obtenerSesion().getIdTipoUsuario().getIdTipoUsuario() == 2) {
            empresas = empresaDao.consultarEmpresasPorAdministrador();
        }
        empresa = new Empresa();
    }

    public void eliminar() {
        EmpresaDao empresaDao = new EmpresaDao();
        int resultado = empresaDao.eliminarEmpresa(empresa);

        if (resultado == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "La empresa ha sido eliminada correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (resultado == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Imposible eliminar empresa");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        empresas = empresaDao.consultarEmpresas();
        empresa = new Empresa();
    }

    public void cancelar() {
        empresa = new Empresa();
        operacion = 0;
        nombreOperacion = "Registrar";
        banner = null;
        logo = null;
        imagenBanner = "";
        imagenLogo = "";

    }

    public void uploadLogo() throws IOException {
        try {
            String destino;
            HashMap<String, String> map = Upload.getMapPathLogosEmpresa();
            destino = map.get("path");

            if (null != logo) {

                archivo.uploadFile(IOUtils.toByteArray(logo.getInputstream()), logo.getFileName(), destino);
                empresa.setUrlLogo(map.get("url") + logo.getFileName());
            }
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Su foto (" + logo.getFileName() + ")  se ha guardado con exito.", ""));
        } catch (IOException ex) {
            throw ex;
        }
    }

    public void uploadBanner() throws IOException {
        try {
            String destino;
            HashMap<String, String> map = Upload.getMapPathBannersEmpresa();
            destino = map.get("path");

            if (null != banner) {
                archivo.uploadFile(IOUtils.toByteArray(banner.getInputstream()), banner.getFileName(), destino);
                empresa.setUrlBanner(map.get("url") + banner.getFileName());
            }
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Su foto (" + banner.getFileName() + ")  se ha guardado con exito.", ""));
        } catch (IOException ex) {
            throw ex;
        }
    }

}
