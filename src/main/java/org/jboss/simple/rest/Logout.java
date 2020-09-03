package org.jboss.simple.rest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("logout")
public class Logout {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response doLogOff(@Context HttpServletRequest request) {
        System.out.println("===================> Log Off - closing current HTTP Session.");
        HttpSession session = request.getSession();
        if (session != null){
            session.invalidate();//remove session.
            try {
                request.logout();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        return Response.status(200).entity("{\"status\":\"logged out\"}" ).build();
    }
}