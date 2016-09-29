/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.webservices.clientes;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 * Jersey REST client generated for REST resource:CrearJugueteImpl
 * [/registrarJuguete]<br>
 * USAGE:
 * <pre>
 *        ClienteRegistrarJuguete client = new ClienteRegistrarJuguete();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author CristianCamilo
 */
public class ClienteRegistrarJuguete {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/SalaVirtualService/webresources";

    public ClienteRegistrarJuguete() {
        client = ResteasyClientBuilder.newBuilder().build();
        webTarget = client.target(BASE_URI).path("registrarJuguete");
    }

    public <T> T Crearjuguete(Class<T> responseType, String codigo, String nombre, String descripcion, String edadDesde, String edadHasta,
            String genero, String cantidad, String url1, String url2, String url3, String url4, String url5, String url6,
            String url7, String url8, String url9, String url10, String url11, String url12, String observacion, String idEmpresa) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (url5 != null) {
            resource = resource.queryParam("url5", url5);
        }
        if (url6 != null) {
            resource = resource.queryParam("url6", url6);
        }
        if (url3 != null) {
            resource = resource.queryParam("url3", url3);
        }
        if (edadDesde != null) {
            resource = resource.queryParam("edadDesde", edadDesde);
        }
        if (url4 != null) {
            resource = resource.queryParam("url4", url4);
        }
        if (url1 != null) {
            resource = resource.queryParam("url1", url1);
        }
        if (url2 != null) {
            resource = resource.queryParam("url2", url2);
        }
        if (url12 != null) {
            resource = resource.queryParam("url12", url12);
        }
        if (descripcion != null) {
            resource = resource.queryParam("descripcion", descripcion);
        }
        if (url9 != null) {
            resource = resource.queryParam("url9", url9);
        }
        if (url7 != null) {
            resource = resource.queryParam("url7", url7);
        }
        if (url8 != null) {
            resource = resource.queryParam("url8", url8);
        }
        if (codigo != null) {
            resource = resource.queryParam("codigo", codigo);
        }
        if (nombre != null) {
            resource = resource.queryParam("nombre", nombre);
        }
        if (edadHasta != null) {
            resource = resource.queryParam("edadHasta", edadHasta);
        }
        if (observacion != null) {
            resource = resource.queryParam("observacion", observacion);
        }
        if (genero != null) {
            resource = resource.queryParam("genero", genero);
        }
        if (idEmpresa != null) {
            resource = resource.queryParam("idEmpresa", idEmpresa);
        }
        if (cantidad != null) {
            resource = resource.queryParam("cantidad", cantidad);
        }
        if (url11 != null) {
            resource = resource.queryParam("url11", url11);
        }
        if (url10 != null) {
            resource = resource.queryParam("url10", url10);
        }
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
