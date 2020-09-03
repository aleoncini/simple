package org.jboss.simple.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("instance")
public class Instance {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response doGet() {
        String instanceName = System.getenv("INSTANCE_NAME");
        if(instanceName == null){
            instanceName = "Instance name not configured";
        }
        return Response.status(200).entity("{\"name\":\"" + instanceName + "\"}" ).build();
    }
}