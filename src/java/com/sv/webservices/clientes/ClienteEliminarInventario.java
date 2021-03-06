/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.webservices.clientes;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:EliminarJugueteImpl
 * [/eliminarJuguete]<br>
 * USAGE:
 * <pre>
 *        NewJerseyClient client = new NewJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author CristianCamilo
 */
public class ClienteEliminarInventario {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/SalaVirtualService/webresources";

    public ClienteEliminarInventario() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("eliminarJuguete");
    }

    public <T> T Eliminarjuguete(Class<T> responseType, String idInventario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idInventario != null) {
            resource = resource.queryParam("idInventario", idInventario);
        }
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
