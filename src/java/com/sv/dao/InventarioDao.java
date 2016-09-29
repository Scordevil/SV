/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.dao;

import com.sv.modelos.Comite;
import com.sv.modelos.Empresa;
import com.sv.modelos.Inventario;
import com.sv.webservices.clientes.ClienteBuscarJuguetes;
import com.sv.webservices.clientes.ClienteConsultarJuguetePorComite;
import com.sv.webservices.clientes.ClienteConsultarJuguetePorEdadYGenero;
import com.sv.webservices.clientes.ClienteConsultarJuguetePorId;
import com.sv.webservices.clientes.ClienteConsultarJuguetes;
import com.sv.webservices.clientes.ClienteConsultarJuguetesMasSeleccionados;
import com.sv.webservices.clientes.ClienteConsultarJuguetesMasVotados;
import com.sv.webservices.clientes.ClienteConsultarJuguetesPorEmpresa;
import com.sv.webservices.clientes.ClienteConsultarJuguetesRangoGenero;
import com.sv.webservices.clientes.ClienteEditarInventario;
import com.sv.webservices.clientes.ClienteEliminarInventario;
import com.sv.webservices.clientes.ClienteRegistrarInventarioComite;
import com.sv.webservices.clientes.ClienteRegistrarJuguete;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author CristianCamilo
 */
public class InventarioDao {

    public int registrarJuguete(Inventario inventario) {
        ClienteRegistrarJuguete cliente = new ClienteRegistrarJuguete();
        return cliente.Crearjuguete(Integer.class,
                inventario.getCodigo(),
                inventario.getNombre(),
                inventario.getDescripcion(),
                "" + inventario.getEdadDesde(),
                "" + inventario.getEdadHasta(),
                inventario.getGenero(),
                "" + inventario.getCantidad(),
                inventario.getUrl1(),
                inventario.getUrl2(),
                inventario.getUrl3(),
                inventario.getUrl4(),
                inventario.getUrl5(),
                inventario.getUrl6(),
                inventario.getUrl7(),
                inventario.getUrl8(),
                inventario.getUrl9(),
                inventario.getUrl10(),
                inventario.getUrl11(),
                inventario.getUrl12(),
                inventario.getObservacion(),
                "" + inventario.getIdEmpresa().getIdEmpresa());
    }

    public int editarJuguete(Inventario inventario) {
        ClienteEditarInventario cliente = new ClienteEditarInventario();
        return cliente.Editarjuguete(Integer.class,
                "" + inventario.getIdInventario(),
                inventario.getCodigo(),
                inventario.getNombre(),
                inventario.getDescripcion(),
                "" + inventario.getEdadDesde(),
                "" + inventario.getEdadHasta(),
                inventario.getGenero(),
                "" + inventario.getCantidad(),
                inventario.getUrl1(),
                inventario.getUrl2(),
                inventario.getUrl3(),
                inventario.getUrl4(),
                inventario.getUrl5(),
                inventario.getUrl6(),
                inventario.getUrl7(),
                inventario.getUrl8(),
                inventario.getUrl9(),
                inventario.getUrl10(),
                inventario.getUrl11(),
                inventario.getUrl12(),
                inventario.getObservacion(),
                "" + inventario.getIdEmpresa().getIdEmpresa());
    }

    public int eliminarInventario(Inventario inventario) {
        ClienteEliminarInventario cliente = new ClienteEliminarInventario();
        return cliente.Eliminarjuguete(int.class, "" + inventario.getIdInventario());
    }

    public int registrarInventarioComite(Inventario inventario, Comite comite) {
        ClienteRegistrarInventarioComite cliente = new ClienteRegistrarInventarioComite();
        return cliente.registrarJuguetesComite(int.class, "" + comite.getIdComite(), "" + inventario.getIdInventario());
    }

    public List<Inventario> consultarJuguetes() {
        ClienteConsultarJuguetes cliente = new ClienteConsultarJuguetes();
        List<HashMap> datos = cliente.ConsultarJuguetes(List.class);
        List<Inventario> juguetes = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idEmpresa");

            juguetes.add(new Inventario((int) datos.get(i).get("idInventario"),
                    (String) datos.get(i).get("codigo"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("descripcion"),
                    (int) datos.get(i).get("edadDesde"),
                    (int) datos.get(i).get("edadHasta"),
                    (String) datos.get(i).get("genero"),
                    (int) datos.get(i).get("cantidad"),
                    (String) datos.get(i).get("url1"),
                    (String) datos.get(i).get("url2"),
                    (String) datos.get(i).get("url3"),
                    (String) datos.get(i).get("url4"),
                    (String) datos.get(i).get("url5"),
                    (String) datos.get(i).get("url6"),
                    (String) datos.get(i).get("url7"),
                    (String) datos.get(i).get("url8"),
                    (String) datos.get(i).get("url9"),
                    (String) datos.get(i).get("url10"),
                    (String) datos.get(i).get("url11"),
                    (String) datos.get(i).get("url12"),
                    (String) datos.get(i).get("observacion"),
                    new Empresa((int) map.get("idEmpresa"))));
        }
        return juguetes;
    }

    public List<Inventario> buscarJuguetes(String valor) {
        ClienteBuscarJuguetes cliente = new ClienteBuscarJuguetes();
        List<HashMap> datos = cliente.buscarJuguetes(List.class, valor);
        List<Inventario> juguetes = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idEmpresa");

            juguetes.add(new Inventario((int) datos.get(i).get("idInventario"),
                    (String) datos.get(i).get("codigo"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("descripcion"),
                    (int) datos.get(i).get("edadDesde"),
                    (int) datos.get(i).get("edadHasta"),
                    (String) datos.get(i).get("genero"),
                    (int) datos.get(i).get("cantidad"),
                    (String) datos.get(i).get("url1"),
                    (String) datos.get(i).get("url2"),
                    (String) datos.get(i).get("url3"),
                    (String) datos.get(i).get("url4"),
                    (String) datos.get(i).get("url5"),
                    (String) datos.get(i).get("url6"),
                    (String) datos.get(i).get("url7"),
                    (String) datos.get(i).get("url8"),
                    (String) datos.get(i).get("url9"),
                    (String) datos.get(i).get("url10"),
                    (String) datos.get(i).get("url11"),
                    (String) datos.get(i).get("url12"),
                    (String) datos.get(i).get("observacion"),
                    new Empresa((int) map.get("idEmpresa"))));
        }
        return juguetes;
    }

    public List<Inventario> ConsultarJuguetesEdadGenero(int edad, String genero) {
        ClienteConsultarJuguetePorEdadYGenero cliente = new ClienteConsultarJuguetePorEdadYGenero();
        List<HashMap> datos = cliente.consultarJuguetesEdadGenero(List.class, edad + "", genero);
        List<Inventario> juguetes = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idEmpresa");

            juguetes.add(new Inventario((int) datos.get(i).get("idInventario"),
                    (String) datos.get(i).get("codigo"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("descripcion"),
                    (int) datos.get(i).get("edadDesde"),
                    (int) datos.get(i).get("edadHasta"),
                    (String) datos.get(i).get("genero"),
                    (int) datos.get(i).get("cantidad"),
                    (String) datos.get(i).get("url1"),
                    (String) datos.get(i).get("url2"),
                    (String) datos.get(i).get("url3"),
                    (String) datos.get(i).get("url4"),
                    (String) datos.get(i).get("url5"),
                    (String) datos.get(i).get("url6"),
                    (String) datos.get(i).get("url7"),
                    (String) datos.get(i).get("url8"),
                    (String) datos.get(i).get("url9"),
                    (String) datos.get(i).get("url10"),
                    (String) datos.get(i).get("url11"),
                    (String) datos.get(i).get("url12"),
                    (String) datos.get(i).get("observacion"),
                    new Empresa((int) map.get("idEmpresa"))));
        }
        return juguetes;
    }

    public List<Inventario> ConsultarJuguetesRangoGenero(int desde, int hasta, String genero) {
        ClienteConsultarJuguetesRangoGenero cliente = new ClienteConsultarJuguetesRangoGenero();
        List<HashMap> datos = cliente.consultarJuguetesRangoGenero(List.class, desde + "", hasta + "", genero);
        List<Inventario> juguetes = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idEmpresa");

            juguetes.add(new Inventario((int) datos.get(i).get("idInventario"),
                    (String) datos.get(i).get("codigo"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("descripcion"),
                    (int) datos.get(i).get("edadDesde"),
                    (int) datos.get(i).get("edadHasta"),
                    (String) datos.get(i).get("genero"),
                    (int) datos.get(i).get("cantidad"),
                    (String) datos.get(i).get("url1"),
                    (String) datos.get(i).get("url2"),
                    (String) datos.get(i).get("url3"),
                    (String) datos.get(i).get("url4"),
                    (String) datos.get(i).get("url5"),
                    (String) datos.get(i).get("url6"),
                    (String) datos.get(i).get("url7"),
                    (String) datos.get(i).get("url8"),
                    (String) datos.get(i).get("url9"),
                    (String) datos.get(i).get("url10"),
                    (String) datos.get(i).get("url11"),
                    (String) datos.get(i).get("url12"),
                    (String) datos.get(i).get("observacion"),
                    new Empresa((int) map.get("idEmpresa"))));
        }
        return juguetes;
    }

    public List<Inventario> ConsultarJuguetesComite(int idComite) {
        ClienteConsultarJuguetePorComite cliente = new ClienteConsultarJuguetePorComite();
        List<HashMap> datos = cliente.consultarJuguetesPorComite(List.class, idComite + "");
        List<Inventario> juguetes = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idEmpresa");

            juguetes.add(new Inventario((int) datos.get(i).get("idInventario"),
                    (String) datos.get(i).get("codigo"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("descripcion"),
                    (int) datos.get(i).get("edadDesde"),
                    (int) datos.get(i).get("edadHasta"),
                    (String) datos.get(i).get("genero"),
                    (int) datos.get(i).get("cantidad"),
                    (String) datos.get(i).get("url1"),
                    (String) datos.get(i).get("url2"),
                    (String) datos.get(i).get("url3"),
                    (String) datos.get(i).get("url4"),
                    (String) datos.get(i).get("url5"),
                    (String) datos.get(i).get("url6"),
                    (String) datos.get(i).get("url7"),
                    (String) datos.get(i).get("url8"),
                    (String) datos.get(i).get("url9"),
                    (String) datos.get(i).get("url10"),
                    (String) datos.get(i).get("url11"),
                    (String) datos.get(i).get("url12"),
                    (String) datos.get(i).get("observacion"),
                    new Empresa((int) map.get("idEmpresa"))));
        }
        return juguetes;
    }

    public Inventario ConsultarJuguetePorId(int idInventario) {
        ClienteConsultarJuguetePorId cliente = new ClienteConsultarJuguetePorId();
        Inventario juguete = cliente.consultarJuguete(Inventario.class, idInventario + "");

        return juguete;
    }

    public List<Inventario> ConsultarJuguetesPorEmpresa(Empresa empresa) {
        ClienteConsultarJuguetesPorEmpresa cliente = new ClienteConsultarJuguetesPorEmpresa();
        List<HashMap> datos = cliente.consultarJuguetesPorEmpresa(List.class, "" + empresa.getIdEmpresa());
        List<Inventario> juguetes = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idEmpresa");

            juguetes.add(new Inventario((int) datos.get(i).get("idInventario"),
                    (String) datos.get(i).get("codigo"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("descripcion"),
                    (int) datos.get(i).get("edadDesde"),
                    (int) datos.get(i).get("edadHasta"),
                    (String) datos.get(i).get("genero"),
                    (int) datos.get(i).get("cantidad"),
                    (String) datos.get(i).get("url1"),
                    (String) datos.get(i).get("url2"),
                    (String) datos.get(i).get("url3"),
                    (String) datos.get(i).get("url4"),
                    (String) datos.get(i).get("url5"),
                    (String) datos.get(i).get("url6"),
                    (String) datos.get(i).get("url7"),
                    (String) datos.get(i).get("url8"),
                    (String) datos.get(i).get("url9"),
                    (String) datos.get(i).get("url10"),
                    (String) datos.get(i).get("url11"),
                    (String) datos.get(i).get("url12"),
                    (String) datos.get(i).get("observacion"),
                    new Empresa((int) map.get("idEmpresa"))));
        }
        return juguetes;
    }

    public List<Inventario> consultarJuguetesMasSeleccionados(int idEmpresa) {
        ClienteConsultarJuguetesMasSeleccionados cliente = new ClienteConsultarJuguetesMasSeleccionados();
        List<HashMap> datos = cliente.consultarJuguetesMasSeleccionados(List.class, idEmpresa + "");
        List<Inventario> juguetes = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idEmpresa");

            juguetes.add(new Inventario((int) datos.get(i).get("idInventario"),
                    (String) datos.get(i).get("codigo"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("descripcion"),
                    (int) datos.get(i).get("edadDesde"),
                    (int) datos.get(i).get("edadHasta"),
                    (String) datos.get(i).get("genero"),
                    (int) datos.get(i).get("cantidad"),
                    (String) datos.get(i).get("url1"),
                    (String) datos.get(i).get("url2"),
                    (String) datos.get(i).get("url3"),
                    (String) datos.get(i).get("url4"),
                    (String) datos.get(i).get("url5"),
                    (String) datos.get(i).get("url6"),
                    (String) datos.get(i).get("url7"),
                    (String) datos.get(i).get("url8"),
                    (String) datos.get(i).get("url9"),
                    (String) datos.get(i).get("url10"),
                    (String) datos.get(i).get("url11"),
                    (String) datos.get(i).get("url12"),
                    (String) datos.get(i).get("observacion"),
                    new Empresa((int) map.get("idEmpresa"))));
        }
        return juguetes;
    }

    public List<Inventario> consultarJuguetesMasVotados(int idEmpresa) {
        ClienteConsultarJuguetesMasVotados cliente = new ClienteConsultarJuguetesMasVotados();
        List<HashMap> datos = cliente.consultarJuguetesMasVotados(List.class, idEmpresa + "");
        List<Inventario> juguetes = new ArrayList<>();

        for (int i = 0; i < datos.size(); i++) {
            HashMap map = (HashMap) datos.get(i).get("idEmpresa");

            juguetes.add(new Inventario((int) datos.get(i).get("idInventario"),
                    (String) datos.get(i).get("codigo"),
                    (String) datos.get(i).get("nombre"),
                    (String) datos.get(i).get("descripcion"),
                    (int) datos.get(i).get("edadDesde"),
                    (int) datos.get(i).get("edadHasta"),
                    (String) datos.get(i).get("genero"),
                    (int) datos.get(i).get("cantidad"),
                    (String) datos.get(i).get("url1"),
                    (String) datos.get(i).get("url2"),
                    (String) datos.get(i).get("url3"),
                    (String) datos.get(i).get("url4"),
                    (String) datos.get(i).get("url5"),
                    (String) datos.get(i).get("url6"),
                    (String) datos.get(i).get("url7"),
                    (String) datos.get(i).get("url8"),
                    (String) datos.get(i).get("url9"),
                    (String) datos.get(i).get("url10"),
                    (String) datos.get(i).get("url11"),
                    (String) datos.get(i).get("url12"),
                    (String) datos.get(i).get("observacion"),
                    new Empresa((int) map.get("idEmpresa"))));
        }
        return juguetes;
    }

}
