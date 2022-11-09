package com.chn.reporteach.model;

import javax.validation.constraints.NotEmpty;

public class AchEnviada {

    @NotEmpty(message = "Ingrese fecha inicial")
    private String fechainicio;

    @NotEmpty(message = "Ingrese fecha final")
    private String fechafin;

    @NotEmpty(message = "Ingrese No. de Cuenta")
    private String cuenta;

    public String getFechainicio() {
        return this.fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechafin() {
        return this.fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
}
