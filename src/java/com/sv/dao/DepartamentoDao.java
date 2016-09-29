/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dao;

import com.sv.modelos.Departamento;
import com.sv.webservices.clientes.ClienteConsultarDepartamento;
import com.sv.webservices.clientes.ClienteConsultarDepartamentos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author VaioDevelopment
 */
public class DepartamentoDao {

    public List<Departamento> ConsultarDepartamentos() {
        ClienteConsultarDepartamentos cliente = new ClienteConsultarDepartamentos();
        List<HashMap> datos = cliente.consultarDepartamentos(List.class);
        List<Departamento> departamentos = new ArrayList<>();
        for (int i = 0; i < datos.size(); i++) {
                departamentos.add(new Departamento((int) datos.get(i).get("idDepartamento"),
                        (String) datos.get(i).get("nombre"), 
                        (String) datos.get(i).get("descripcion")));
        }
        return departamentos;
    }

    public Departamento consultarDepartamento(Departamento departamento) {
        ClienteConsultarDepartamento cliente = new ClienteConsultarDepartamento();
        return cliente.consultarDepartamento(Departamento.class, "" + departamento.getIdDepartamento(), departamento.getNombre());
    }

}
