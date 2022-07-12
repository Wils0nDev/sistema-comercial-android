package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName =  Constante.NAME_TABLE_SUCURSAL)
public class SucursalEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "ntraSucursal")
    public int ntraSucursal;

    @ColumnInfo(name = "descripcion")
    public String descripcion;

    @ColumnInfo(name = "codUbigeo")
    public String codUbigeo;

    @ColumnInfo(name = "factor")
    public Double factor;

    public SucursalEntity(int ntraSucursal, String descripcion, String codUbigeo, Double factor) {
        this.ntraSucursal = ntraSucursal;
        this.descripcion = descripcion;
        this.codUbigeo = codUbigeo;
        this.factor = factor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNtraSucursal() {
        return ntraSucursal;
    }

    public void setNtraSucursal(int ntraSucursal) {
        this.ntraSucursal = ntraSucursal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodUbigeo() {
        return codUbigeo;
    }

    public void setCodUbigeo(String codUbigeo) {
        this.codUbigeo = codUbigeo;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }
}
