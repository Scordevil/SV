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
 * Jersey REST client generated for REST resource:we [editarUsuario]<br>
 * USAGE:
 * <pre>
 *        ClienteEditarUsuario client = new ClienteEditarUsuario();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author CristianCamilo
 */
public class ClienteEditarUsuario {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/SalaVirtualService/webresources/";

    public ClienteEditarUsuario() {
        client = ResteasyClientBuilder.newBuilder().build();
        webTarget = client.target(BASE_URI).path("editarUsuario");
    }

    /**
     * @param responseType Class representing the response
     * @param idUsuario query parameter
     * @param idEmpresa query parameter
     * @param idTipoUsuario query parameter
     * @param idCiudad query parameter
     * @param idDepartamento query parameter
     * @param nombre query parameter
     * @param codigoEmpleado query parameter
     * @param cc query parameter
     * @param telefono query parameter
     * @param email query parameter
     * @param contrasena query parameter
     * @param usuario query parameter
     * @param oficina query parameter
     * @param areaTrabajo query parameter
     * @return response object (instance of responseType class)
     */
    public <T> T editarUsuario(Class<T> responseType, String idUsuario, String idEmpresa, String idTipoUsuario, String idCiudad, String idDepartamento, String nombre, String codigoEmpleado, String cc, String telefono, String email, String contrasena, String usuario, String oficina, String areaTrabajo) throws ClientErrorException {
        String[] queryParamNames = new String[]{"idUsuario", "idEmpresa", "idTipoUsuario", "idCiudad", "idDepartamento", "nombre", "codigoEmpleado", "cc", "telefono", "email", "contrasena", "usuario", "oficina", "areaTrabajo"};
        String[] queryParamValues = new String[]{idUsuario, idEmpresa, idTipoUsuario, idCiudad, idDepartamento, nombre, codigoEmpleado, cc, telefono, email, contrasena, usuario, oficina, areaTrabajo};
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
