package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class filaPromocionDos {

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

    @ColumnInfo(name = "flagCombo")
    private Integer flagCombo;

    public filaPromocionDos(int id, int ntra, @NonNull String ntraPromocion, int flag, String descripcion, String codProductoInicio, String codProductoFin, int detalle, short estado, Integer flagCombo) {
        this.id = id;
        this.ntra = ntra;
        this.ntraPromocion = ntraPromocion;
        this.flag = flag;
        this.descripcion = descripcion;
        this.codProductoInicio = codProductoInicio;
        this.codProductoFin = codProductoFin;
        this.detalle = detalle;
        this.estado = estado;
        this.flagCombo = flagCombo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNtra() {
        return ntra;
    }

    public void setNtra(int ntra) {
        this.ntra = ntra;
    }

    @NonNull
    public String getNtraPromocion() {
        return ntraPromocion;
    }

    public void setNtraPromocion(@NonNull String ntraPromocion) {
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

    public Integer getFlagCombo() {
        return flagCombo;
    }

    public void setFlagCombo(Integer flagCombo) {
        this.flagCombo = flagCombo;
    }
}
