package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class FilaPresentacion {
    @NonNull
    @ColumnInfo(name = "ntra")
    private Integer ntra;               //numero de transaccion
    @ColumnInfo(name = "codConcepto")
    private Integer codigo;             //codigo de concepto
    @ColumnInfo(name = "correlativo")
    private Integer correlativo;        //codigo de concepto
    @ColumnInfo(name = "descripcion")
    private String descripcion;         //descripcion de concepto
    @ColumnInfo(name = "cantidadUnidadBase")
    private Integer cantidadUnidadBase;         //descripcion de concepto

    public FilaPresentacion(@NonNull Integer ntra, Integer codigo, Integer correlativo, String descripcion, Integer cantidadUnidadBase) {
        this.ntra = ntra;
        this.codigo = codigo;
        this.correlativo = correlativo;
        this.descripcion = descripcion;
        this.cantidadUnidadBase = cantidadUnidadBase;
    }

    @NonNull
    public Integer getNtra() {
        return ntra;
    }

    public void setNtra(@NonNull Integer ntra) {
        this.ntra = ntra;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidadUnidadBase() {
        return cantidadUnidadBase;
    }

    public void setCantidadUnidadBase(Integer cantidadUnidadBase) {
        this.cantidadUnidadBase = cantidadUnidadBase;
    }
}
