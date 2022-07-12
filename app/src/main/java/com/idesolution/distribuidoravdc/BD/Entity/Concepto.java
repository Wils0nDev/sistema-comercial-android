package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_concepto")
public class Concepto {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ntra")
    private Integer ntra;               //numero de transaccion
    @NonNull
    @ColumnInfo(name = "codConcepto")
    private Integer codigo;             //codigo de concepto
    @NonNull
    @ColumnInfo(name = "correlativo")
    private Integer correlativo;        //codigo de concepto
    @NonNull
    @ColumnInfo(name = "descripcion")
    private String descripcion;         //descripcion de concepto

    public Concepto(@NonNull Integer codigo, @NonNull Integer correlativo, @NonNull String descripcion) {
        this.codigo = codigo;
        this.correlativo = correlativo;
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
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(@NonNull Integer codigo) {
        this.codigo = codigo;
    }

    @NonNull
    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(@NonNull Integer correlativo) {
        this.correlativo = correlativo;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }
}
