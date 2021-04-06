package org.jboss.simple.rest;

import java.util.Set;

public class UserInfo {
    private String name;
    private String email;
    private Set<String> roles;
    private Set<String> attributes;
    private String accessToken;

    public String getName() {
        return name;
    }

    public UserInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public UserInfo setRoles(Set<String> roles) {
        this.roles = roles;
        return this;
    }

    public Set<String> getAttributes() {
        return attributes;
    }

    public UserInfo setAttributes(Set<String> attributes) {
        this.attributes = attributes;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public UserInfo setAccessToken(String token) {
        this.accessToken = token;
        return this;
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append("\"name\":\"").append(name).append("\",");
        buffer.append("\"email\":\"").append(email).append("\",");
        buffer.append("\"accessToken\":\"").append(accessToken).append("\",");
        buffer.append("\"roles\":\"").append(getRoleString()).append("\"");
        if(attributes != null){
            buffer.append(",\"attributes\":\"").append(getAttributesString()).append("\"");
        }
        buffer.append("}");
        return buffer.toString();
    }

    private String getAttributesString() {
        boolean isFirst = true;
        StringBuffer buffer = new StringBuffer();
        for (String attribute: attributes) {
            if (isFirst){
                buffer.append(attribute);
                isFirst = false;
            } else {
                buffer.append(", ").append(attribute);
            }
        }
        return buffer.toString();
    }

    private String getRoleString() {
        boolean isFirst = true;
        StringBuffer buffer = new StringBuffer();
        for (String role: roles) {
            if (isFirst){
                buffer.append(role);
                isFirst = false;
            } else {
                buffer.append(", ").append(role);
            }
        }
        return buffer.toString();
    }
}