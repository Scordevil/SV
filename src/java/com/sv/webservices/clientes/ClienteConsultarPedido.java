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
 * Jersey REST client generated for REST resource:ConsultarPedidoImpl
 * [/consultarPedido]<br>
 * USAGE:
 * <pre>
 *        ClienteConsultarPedido client = new ClienteConsultarPedido();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Jose
 */
public class ClienteConsultarPedido {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/SalaVirtualService/webresources";

    public ClienteConsultarPedido() {
        client = ResteasyClientBuilder.newBuilder().build();
        webTarget = client.target(BASE_URI).path("consultarPedido");
    }

    public <T> T consultarPedido(Class<T> responseType, String idPedido) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idPedido != null) {
            resource = resource.queryParam("idPedido", idPedido);
        }
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
