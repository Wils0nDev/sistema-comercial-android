package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName = Constante.NAME_TABLE_PROMOCION)
public class PromocionEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "ntra")
    @NonNull
    public int ntra;

    @ColumnInfo(name = "ntraPromocion")
    @NonNull
    public String ntraPromocion;

    @ColumnInfo(name = "flag")
    @NonNull
    public int flag;

    @ColumnInfo(name = "descripcion")
    public String descripcion;

    @ColumnInfo(name = "codProductoInicio")
    public String codProductoInicio;

    @ColumnInfo(name = "codProductoFin")
    public String codProductoFin;

    @ColumnInfo(name = "detalle")
    public int detalle;

    @ColumnInfo(name = "estado")
    public short estado;

    public PromocionEntity(int ntra, @NonNull String ntraPromocion, int flag, String descripcion, String codProductoInicio, String codProductoFin, int detalle, short estado) {
        this.ntra = ntra;
        this.ntraPromocion = ntraPromocion;
        this.flag = flag;
        this.descripcion = descripcion;
        this.codProductoInicio = codProductoInicio;
        this.codProductoFin = codProductoFin;
        this.detalle = detalle;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNtraPromocion() {
        return ntraPromocion;
    }

    public void setNtraPromocion(String ntraPromocion) {
        this.ntraPromocion = ntraPromocion;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodProductoInicio() {
        return codProductoInicio;
    }

    public void setCodProductoInicio(String codProductoInicio) {
        this.codProductoInicio = codProductoInicio;
    }

    public String getCodProductoFin() {
        return codProductoFin;
    }

    public void setCodProductoFin(String codProductoFin) {
        this.codProductoFin = codProductoFin;
    }

    public int getDetalle() {
        return detalle;
    }

    public void setDetalle(int detalle) {
        this.detalle = detalle;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public int getNtra() {
        return ntra;
    }

    public void setNtra(int ntra) {
        this.ntra = ntra;
    }
}
