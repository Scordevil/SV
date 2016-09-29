/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.clases;

import com.sv.modelos.Usuario;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Desarrollo_Planit
 */
public class Sesion {

    private static HttpServletRequest httpServletRequest;
    private static FacesContext faceContext;

    public Sesion() {
    }

    public static void iniciarHttpSesion(Usuario usuario) {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        httpServletRequest.getSession().setAttribute("Sesion", usuario);
    }

    public static void cerrarHttpSesion() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        httpServletRequest.getSession().invalidate();
    }

    public static Usuario obtenerSesion() {
        Usuario usuarioLogueado = new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        usuarioLogueado = (Usuario) httpServletRequest.getSession().getAttribute("Sesion");
        return usuarioLogueado;
    }
}
