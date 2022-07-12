package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_ruta")
public class Ruta {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ntra")
    private Integer ntra;               //numero de transaccion
    @NonNull
    @ColumnInfo(name = "codigo")
    private Integer codigoRuta;         //codigo de ruta
    @NonNull
    @ColumnInfo(name = "descripcion")
    private String descripcion;        //descripcion

    public Ruta(@NonNull Integer codigoRuta, @NonNull String descripcion) {
        this.codigoRuta = codigoRuta;
        this.descripcion = descripcion;
    }

    @NonNull
    public Integer getNtra() {
        return ntra;
    }

    public void setNtra(@NonNull Integer ntra) {
        this.ntra = ntra;
    }

    @NonNull
    public Integer getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(@NonNull Integer codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }
}
