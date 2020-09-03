package org.jboss.simple.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ping")
public class Ping {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response doGet() {
        System.out.println("===================> received ping...");
        return Response.status(200).entity("{\"ping\":\"pong\"}" ).build();
    }
}