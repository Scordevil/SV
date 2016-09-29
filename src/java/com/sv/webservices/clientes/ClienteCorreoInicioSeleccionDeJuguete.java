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
 * Jersey REST client generated for REST resource:CorreoSeleccionJugueteImpl
 * [/correoSeleccionJuguete]<br>
 * USAGE:
 * <pre>
 *        ClienteCorreoInicioSeleccionDeJuguete client = new ClienteCorreoInicioSeleccionDeJuguete();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Gustavo
 */
public class ClienteCorreoInicioSeleccionDeJuguete {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/SalaVirtualService/webresources";

    public ClienteCorreoInicioSeleccionDeJuguete() {
        client = ResteasyClientBuilder.newBuilder().build(); 
        webTarget = client.target(BASE_URI).path("correoSeleccionJuguete");
    }

    public <T> T correoSeleccionJuguete(Class<T> responseType, String logo, String usuario, String contrasena, String nombreUsuario, String nombreEmpresa) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (logo != null) {
            resource = resource.queryParam("logo", logo);
        }
        if (usuario != null) {
            resource = resource.queryParam("usuario", usuario);
        }
        if (contrasena != null) {
            resource = resource.queryParam("contrasena", contrasena);
        }
        if (nombreUsuario != null) {
            resource = resource.queryParam("nombreUsuario", nombreUsuario);
        }
        if (nombreEmpresa != null) {
            resource = resource.queryParam("nombreEmpresa", nombreEmpresa);
        }
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
