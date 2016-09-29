/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.controladores;

import com.sv.dao.DepartamentoDao;
import com.sv.modelos.Departamento;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author VaioDevelopment
 */
public class DepartamentoCT {

    private Departamento departamento;
    private List<Departamento> departamentos;
        
    public DepartamentoCT() {
        departamento = new Departamento();
        departamentos = new ArrayList<>();
    }
    
    @PostConstruct
    public void init(){
        DepartamentoDao departamentoDao = new DepartamentoDao();
        departamentos = departamentoDao.ConsultarDepartamentos();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
    
    
    
    
}
