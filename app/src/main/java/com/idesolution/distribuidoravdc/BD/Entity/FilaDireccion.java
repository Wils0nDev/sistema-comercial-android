package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.room.ColumnInfo;

public class FilaDireccion {
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "ntraPuntoEntrega")
    public Integer ntraPuntoEntrega;
    @ColumnInfo(name = "direccion")
    public String direccion;

    public FilaDireccion(int id, Integer ntraPuntoEntrega, String direccion) {
        this.id = id;
        this.ntraPuntoEntrega = ntraPuntoEntrega;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNtraPuntoEntrega() {
        return ntraPuntoEntrega;
    }

    public void setNtraPuntoEntrega(Integer ntraPuntoEntrega) {
        this.ntraPuntoEntrega = ntraPuntoEntrega;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
