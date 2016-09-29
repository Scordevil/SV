/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.controladores;

import com.sv.dao.CiudadDao;
import com.sv.modelos.Ciudad;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author VaioDevelopment
 */
public class CiudadCT {

    private Ciudad ciudad;
    private List<Ciudad> ciudades;

    public CiudadCT() {
        ciudad = new Ciudad();
        ciudades = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        CiudadDao ciudadDao = new CiudadDao();
        ciudades = ciudadDao.consultarCiudades();
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    //Metodos
}
