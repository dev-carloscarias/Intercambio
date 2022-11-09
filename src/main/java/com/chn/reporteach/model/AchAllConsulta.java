package com.chn.reporteach.model;

import javax.validation.constraints.NotEmpty;

public class AchAllConsulta
{
    @NotEmpty(message = "Ingrese fecha")
    private String fecha;

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
