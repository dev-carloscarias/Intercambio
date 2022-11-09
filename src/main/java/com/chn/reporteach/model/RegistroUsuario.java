package com.chn.reporteach.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


public class RegistroUsuario
{
    @NotEmpty(message = "Ingrese nombre de Usuario")
    private String username;

    @Column(length = 60)
    @NotEmpty(message = "Ingrese Password")
    private String password;

    private Boolean enabled;

    @NotEmpty(message = "Seleccione Rol")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
