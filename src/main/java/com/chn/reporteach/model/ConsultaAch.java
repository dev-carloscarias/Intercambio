package com.chn.reporteach.model;

import javax.validation.constraints.NotEmpty;

public class ConsultaAch
{
    @NotEmpty(message = "Ingrese fecha inicial")
    private String fechainicio;

    public String getFechainicio() {
        return this.fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }
}