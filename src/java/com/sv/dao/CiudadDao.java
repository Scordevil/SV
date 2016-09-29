/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dao;

import com.sv.modelos.Ciudad;
import com.sv.modelos.Departamento;
import com.sv.webservices.clientes.ClienteConsultarCiudad;
import com.sv.webservices.clientes.ClienteConsultarCiudades;
import com.sv.webservices.clientes.ClienteConsultarCiudadesSegunDepartamento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class CiudadDao {

    public List<Ciudad> consultarCiudades() {
        ClienteConsultarCiudades cliente = new ClienteConsultarCiudades();
        List<HashMap> datos = cliente.consultarCiudades(List.class);
        List<Ciudad> ciudades = new ArrayList<>();
        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idDepartamento");
            DepartamentoDao departamentoDao = new DepartamentoDao();
            
            ciudades.add(new Ciudad((int) datos.get(i).get("idCiudad"),
                    (String) datos.get(i).get("nombre"), 
                    (String) datos.get(i).get("descripcion"), 
                    departamentoDao.consultarDepartamento(new Departamento((int) map.get("idDepartamento")))));
                    
        }   
        return ciudades;
    }

    
    public List<Ciudad> consultarCiudadesSegunDepartamento(Departamento departamento) {
        ClienteConsultarCiudadesSegunDepartamento cliente = new ClienteConsultarCiudadesSegunDepartamento();
        List<HashMap> datos = cliente.consultarCiudadesSegunDepartamento(List.class, "" + departamento.getIdDepartamento());
        List<Ciudad> ciudades = new ArrayList<>();
        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idDepartamento");
            DepartamentoDao departamentoDao = new DepartamentoDao();
            
            ciudades.add(new Ciudad((int) datos.get(i).get("idCiudad"),
                    (String) datos.get(i).get("nombre"), 
                    (String) datos.get(i).get("descripcion"), 
                    departamentoDao.consultarDepartamento(new Departamento((int) map.get("idDepartamento")))));
                    
        }   
        return ciudades;
    }
    
    public Ciudad consultarCiudad(Ciudad ciudad){
        ClienteConsultarCiudad cliente = new ClienteConsultarCiudad();
        return cliente.consultarCiudad(Ciudad.class, "" + ciudad.getIdCiudad(), ciudad.getNombre());
    }
}
