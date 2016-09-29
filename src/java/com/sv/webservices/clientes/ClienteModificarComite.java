/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.webservices.clientes;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 * Jersey REST client generated for REST resource:we [editarComite]<br>
 * USAGE:
 * <pre>
 *        ClienteModificarComite client = new ClienteModificarComite();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author CristianCamilo
 */
public class ClienteModificarComite {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/SalaVirtualService/webresources/";

    public ClienteModificarComite() {
        client = ResteasyClientBuilder.newBuilder().build();
        webTarget = client.target(BASE_URI).path("editarComite");
    }

    /**
     * @param responseType Class representing the response
     * @param idComite query parameter
     * @param idEstado query parameter
     * @param nombre query parameter
     * @param descripcion query parameter
     * @param fechaApertura query parameter
     * @param fechaCierre query parameter
     * @return response object (instance of responseType class)
     */
    public <T> T editarComite(Class<T> responseType, String idComite, String idEstado, String nombre, String descripcion, String fechaApertura, String fechaCierre) throws ClientErrorException {
        String[] queryParamNames = new String[]{"idComite", "idEstado", "nombre", "descripcion", "fechaApertura", "fechaCierre"};
        String[] queryParamValues = new String[]{idComite, idEstado, nombre, descripcion, fechaApertura, fechaCierre};
        ;
        javax.ws.rs.core.Form form = getQueryOrFormParams(queryParamNames, queryParamValues);
        javax.ws.rs.core.MultivaluedMap<String, String> map = form.asMap();
        for (java.util.Map.Entry<String, java.util.List<String>> entry : map.entrySet()) {
            java.util.List<String> list = entry.getValue();
            String[] values = list.toArray(new String[list.size()]);
            webTarget = webTarget.queryParam(entry.getKey(), (Object[]) values);
        }
        return webTarget.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }

    private Form getQueryOrFormParams(String[] paramNames, String[] paramValues) {
        Form form = new javax.ws.rs.core.Form();
        for (int i = 0; i < paramNames.length; i++) {
            if (paramValues[i] != null) {
                form = form.param(paramNames[i], paramValues[i]);
            }
        }
        return form;
    }

    public void close() {
        client.close();
    }
    
}
