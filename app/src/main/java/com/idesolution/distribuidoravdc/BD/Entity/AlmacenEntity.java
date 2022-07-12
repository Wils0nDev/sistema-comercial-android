package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName =  Constante.NAME_TABLE_ALMACEN)
public class AlmacenEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "ntraAlmacen")
    @NonNull
    public int ntraAlmacen;

    @ColumnInfo(name = "descripcion")
    @NonNull
    public String descripcion;

    @ColumnInfo(name = "abreviatura")
    @NonNull
    public String abreviatura;

    public AlmacenEntity(int ntraAlmacen, @NonNull String descripcion, @NonNull String abreviatura) {
        this.ntraAlmacen = ntraAlmacen;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNtraAlmacen() {
        return ntraAlmacen;
    }

    public void setNtraAlmacen(int ntraAlmacen) {
        this.ntraAlmacen = ntraAlmacen;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    @NonNull
    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(@NonNull String abreviatura) {
        this.abreviatura = abreviatura;
    }
}
