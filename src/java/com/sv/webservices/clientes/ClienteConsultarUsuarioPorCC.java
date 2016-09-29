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
 * Jersey REST client generated for REST resource:ConsultarUsuarioPorCCImpl
 * [/consultarUsuarioPorCC]<br>
 * USAGE:
 * <pre>
 *        ClienteConsultarUsuarioPorCC client = new ClienteConsultarUsuarioPorCC();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Jose
 */
public class ClienteConsultarUsuarioPorCC {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/SalaVirtualService/webresources";

    public ClienteConsultarUsuarioPorCC() {
        client = ResteasyClientBuilder.newBuilder().build();
        webTarget = client.target(BASE_URI).path("consultarUsuarioPorCC");
    }

    public <T> T consultarUsuarioPorCC(Class<T> responseType, String Cc) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (Cc != null) {
            resource = resource.queryParam("Cc", Cc);
        }
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
