/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.clases;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author SISTEMAS
 */
public class Upload {

    UploadedFile archivo;

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    /**
     *
     * METODOS PARA MAPEAR ARCHIVOS
     * =========================================================================
     */
    public static String getPath() {
        try {
            ExternalContext tmpEC;

            tmpEC = FacesContext.getCurrentInstance().getExternalContext();
            String realPath = tmpEC.getRealPath("/");
            return realPath;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static HashMap<String, String> getMapPathLogosEmpresa() {
        try {
            HashMap<String, String> map = new HashMap<>();
            String path = getPathLogosEmpresa();
            map.put("path", path);
            map.put("url", "/resources/images/logos/");
            return map;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static String getPathLogosEmpresa() {
        try {
            String path = getPath() + "/resources/images/logos/";
            return path;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static HashMap<String, String> getMapPathBannersEmpresa() {
        try {
            HashMap<String, String> map = new HashMap<>();
            String path = getPathBannersEmpresa();
            map.put("path", path);
            map.put("url", "/resources/images/banner/");
            return map;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static String getPathBannersEmpresa() {
        try {
            String path = getPath() + "/resources/images/banner/";
            return path;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * METODO QUE SUBE EL ARCHIVO =============================================
     */
    public void uploadFile(byte[] b, String nombre, String destino) throws IOException {

        String FilePath = destino;
        if (null != b) {
            byte[] bytes = b;
            String filename = nombre;

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(FilePath + filename)));
            stream.write(bytes);
            stream.close();
        }
    }
}
