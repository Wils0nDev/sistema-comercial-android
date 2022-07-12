package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class filaDescuentoDos {
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "ntra")
    @NonNull
    public int ntra;

    @ColumnInfo(name = "ntraDescuento")
    @NonNull
    public String ntraDescuento;

    @ColumnInfo(name = "flag")
    @NonNull
    public int flag;

    @ColumnInfo(name = "descripcion")
    public String descripcion;

    @ColumnInfo(name = "valorInicial")
    public String valorInicial;

    @ColumnInfo(name = "valorFinal")
    public String valorFinal;

    @ColumnInfo(name = "detalle")
    public int detalle;

    @ColumnInfo(name = "estado")
    public short estado;

    @ColumnInfo(name = "tipoDescuento")
    public short tipoDescuento;

    @ColumnInfo(name = "flagCombo")
    private Integer flagCombo;

    public filaDescuentoDos(int id, int ntra, @NonNull String ntraDescuento, int flag, String descripcion, String valorInicial, String valorFinal, int detalle, short estado, short tipoDescuento, Integer flagCombo) {
        this.id = id;
        this.ntra = ntra;
        this.ntraDescuento = ntraDescuento;
        this.flag = flag;
        this.descripcion = descripcion;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
        this.detalle = detalle;
        this.estado = estado;
        this.tipoDescuento = tipoDescuento;
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
    public String getNtraDescuento() {
        return ntraDescuento;
    }

    public void setNtraDescuento(@NonNull String ntraDescuento) {
        this.ntraDescuento = ntraDescuento;
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

    public String getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(String valorInicial) {
        this.valorInicial = valorInicial;
    }

    public String getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(String valorFinal) {
        this.valorFinal = valorFinal;
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

    public short getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(short tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public Integer getFlagCombo() {
        return flagCombo;
    }

    public void setFlagCombo(Integer flagCombo) {
        this.flagCombo = flagCombo;
    }
}
