package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName =  Constante.NAME_TABLE_PRESENTACION)
public class PresentacionEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "codProducto")
    @NonNull
    public String codProducto;

    @ColumnInfo(name = "codPresentancion")
    public int codPresentancion;

    @ColumnInfo(name = "cantidadUnidadBase")
    public int cantidadUnidadBase;

    public PresentacionEntity(@NonNull String codProducto, int codPresentancion, int cantidadUnidadBase) {
        this.codProducto = codProducto;
        this.codPresentancion = codPresentancion;
        this.cantidadUnidadBase = cantidadUnidadBase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(@NonNull String codProducto) {
        this.codProducto = codProducto;
    }

    public int getCodPresentancion() {
        return codPresentancion;
    }

    public void setCodPresentancion(int codPresentancion) {
        this.codPresentancion = codPresentancion;
    }

    public int getCantidadUnidadBase() {
        return cantidadUnidadBase;
    }

    public void setCantidadUnidadBase(int cantidadUnidadBase) {
        this.cantidadUnidadBase = cantidadUnidadBase;
    }
}
