/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.controladores;

import com.sv.clases.MD5;
import com.sv.clases.Sesion;
import static com.sv.clases.Sesion.cerrarHttpSesion;
import static com.sv.clases.Sesion.iniciarHttpSesion;
import com.sv.dao.ComiteDao;
import com.sv.dao.UsuarioDao;
import com.sv.modelos.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author CristianCamilo
 */
public class SesionCT {

    //variables
    private Usuario usuario;
    protected UsuarioDao usuarioDao;
    private int menu;
    

    //Constructores
    public SesionCT() {
        usuario = new Usuario();
        usuarioDao = new UsuarioDao();
        menu = 0;
    }

    //Getter & Setter
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = 6;
    }
    
    
    //Metodos
    public String iniciarSesion() {

        FacesMessage message = new FacesMessage();
        String ruta = "";
//        MD5 md = new MD5();
//        String pass = md.getMD5(usuario.getContrasena());
        usuario = usuarioDao.consultarUsuarioPorLogin(usuario);

        if (usuario.getIdUsuario() != 0) {
            if (usuario.getContrasena().equalsIgnoreCase(usuario.getContrasena())) {

                iniciarHttpSesion(usuario);
               
                switch (usuario.getIdTipoUsuario().getIdTipoUsuario()) {
                    case 1:
                        ruta = "Dashboard";
                        break;
                    case 2:
                        ruta = "Dashboard";
                        break;
                    case 3:
                        ruta = "Dashboard";
                        break;
                    case 4:
                        int valor = 0;
                        ComiteDao comiteDao = new ComiteDao();
                        valor = comiteDao.consultarComitePorUsuario(usuario.getIdUsuario());
                        if (valor > 0) {
                            valor = comiteDao.validarVotacionPorUsuario(usuario.getIdUsuario()); //mayor a 0, ya voto
                            if (valor > 0) {
                                ruta = "Seleccion de Juguetes";
                            } else {
                                ruta = "Votacion de Juguetes";
                            }
                        } else {
                            ruta = "Seleccion de Juguetes";
                        }

                        break;
                }

                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "" + usuario.getNombre());
                FacesContext.getCurrentInstance().addMessage(null, message);

            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Contraseña incorrecta", "Digite de nuevo su contraseña");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario no existente", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return ruta;
    }

    public String cerrarSesion() {
        cerrarHttpSesion();
        return "Login";
    }

    public Usuario obtenerSesion() {
        return (Usuario) Sesion.obtenerSesion();
    }
    
        public int obtenerCuatro() {
            menu = 4;
        return menu;
    }
    
    public String obtenerImagenLogo(){
        if(usuario.getIdEmpresa().getUrlLogo().isEmpty()){
            return "/resources/images/logos/default-user.png";
        }else{
           return usuario.getIdEmpresa().getUrlLogo();
        }
    }
    

    
}
