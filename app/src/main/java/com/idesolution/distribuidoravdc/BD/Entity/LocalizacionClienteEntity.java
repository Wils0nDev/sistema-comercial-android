package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class LocalizacionClienteEntity {


    @ColumnInfo(name = "nombreCompleto")
    private String nombreCompleto; //nombres


    @ColumnInfo(name = "coordenadaX")
    private  String coordenadaX;

    @ColumnInfo(name = "coordenadaY")
    private  String coordenadaY;

    public LocalizacionClienteEntity(String nombreCompleto,String coordenadaX,  String coordenadaY ){


        this.nombreCompleto = nombreCompleto;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public String getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }
}
