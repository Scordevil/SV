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
 * Jersey REST client generated for REST
 * resource:CorreoConfirmacionSeleccionJugueteImpl
 * [/correoConfirmacionSeleccionJuguete]<br>
 * USAGE:
 * <pre>
 *        ClienteCorreoConfirmacionSeleccionJuguete client = new ClienteCorreoConfirmacionSeleccionJuguete();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Gustavo
 */
public class ClienteCorreoConfirmacionSeleccionJuguete {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/SalaVirtualService/webresources";

    public ClienteCorreoConfirmacionSeleccionJuguete() {
        client = ResteasyClientBuilder.newBuilder().build(); 
        webTarget = client.target(BASE_URI).path("correoConfirmacionSeleccionJuguete");
    }

    public <T> T correoConfirmacionSeleccionJuguete(Class<T> responseType, String nombreJuguete, String codigoInventario, String nombreHijo, String usuario, String nombreUsuario, String idPedido) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (nombreJuguete != null) {
            resource = resource.queryParam("nombreJuguete", nombreJuguete);
        }
        if (codigoInventario != null) {
            resource = resource.queryParam("codigoInventario", codigoInventario);
        }
        if (nombreHijo != null) {
            resource = resource.queryParam("nombreHijo", nombreHijo);
        }
        if (usuario != null) {
            resource = resource.queryParam("usuario", usuario);
        }
        if (nombreUsuario != null) {
            resource = resource.queryParam("nombreUsuario", nombreUsuario);
        }
        if (idPedido != null) {
            resource = resource.queryParam("idPedido", idPedido);
        }
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
