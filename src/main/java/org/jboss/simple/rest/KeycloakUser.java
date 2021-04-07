package org.jboss.simple.rest;

import org.keycloak.KeycloakSecurityContext;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Map;
import java.util.Set;

@Path("user")
public class KeycloakUser {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo(@Context HttpServletRequest request) {
        KeycloakSecurityContext securityContext = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        String name = securityContext.getToken().getGivenName() + " " + securityContext.getToken().getFamilyName();
        String email = securityContext.getToken().getEmail();
        Set<String> roles = securityContext.getToken().getRealmAccess().getRoles();
        String token = securityContext.getTokenString();
        Map<String, Object> otherClaims = securityContext.getToken().getOtherClaims();     
        UserInfo userInfo = new UserInfo().setName(name).setEmail(email).setRoles(roles).setAccessToken(token);
        try {
            if(otherClaims != null){
                int loginNumber = Integer.valueOf(otherClaims.get("loginNumber").toString());
                userInfo.setLogins(loginNumber);
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return Response.status(200).entity(userInfo.toString()).build();
    }
}