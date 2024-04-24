package com.group8.diy4rent.Security.dto;

public class UsuarioLogin {
    private String username;
    private String password;

    public UsuarioLogin() {
    }

    public UsuarioLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
