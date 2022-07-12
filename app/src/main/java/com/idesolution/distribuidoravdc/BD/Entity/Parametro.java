package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tblParametro")
public class Parametro {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ntra")
    private int ntra;
    @ColumnInfo(name = "codParametro")
    private Integer codParametro;
    @ColumnInfo(name = "tipo")
    private Integer tipo;
    @ColumnInfo(name = "valorEntero1")
    private Integer valorEntero1;
    @ColumnInfo(name = "valorEntero2")
    private Integer valorEntero2;
    @ColumnInfo(name = "valorCaneda1")
    private String valorCaneda1;
    @ColumnInfo(name = "valorCaneda2")
    private String valorCaneda2;
    @ColumnInfo(name = "valorMoneda1")
    private Double valorMoneda1;
    @ColumnInfo(name = "valorMoneda2")
    private Double valorMoneda2;
    @ColumnInfo(name = "valorFloat1")
    private Float valorFloat1;
    @ColumnInfo(name = "valorFloat2")
    private Float valorFloat2;
    @ColumnInfo(name = "valorFecha1")
    private String valorFecha1;
    @ColumnInfo(name = "valorFecha2")
    private String valorFecha2;

    public Parametro(Integer codParametro, Integer tipo, Integer valorEntero1, Integer valorEntero2, String valorCaneda1, String valorCaneda2, Double valorMoneda1, Double valorMoneda2, Float valorFloat1, Float valorFloat2, String valorFecha1, String valorFecha2) {
        this.codParametro = codParametro;
        this.tipo = tipo;
        this.valorEntero1 = valorEntero1;
        this.valorEntero2 = valorEntero2;
        this.valorCaneda1 = valorCaneda1;
        this.valorCaneda2 = valorCaneda2;
        this.valorMoneda1 = valorMoneda1;
        this.valorMoneda2 = valorMoneda2;
        this.valorFloat1 = valorFloat1;
        this.valorFloat2 = valorFloat2;
        this.valorFecha1 = valorFecha1;
        this.valorFecha2 = valorFecha2;
    }

    public int getNtra() {
        return ntra;
    }

    public void setNtra(int ntra) {
        this.ntra = ntra;
    }

    public Integer getCodParametro() {
        return codParametro;
    }

    public void setCodParametro(Integer codParametro) {
        this.codParametro = codParametro;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getValorEntero1() {
        return valorEntero1;
    }

    public void setValorEntero1(Integer valorEntero1) {
        this.valorEntero1 = valorEntero1;
    }

    public Integer getValorEntero2() {
        return valorEntero2;
    }

    public void setValorEntero2(Integer valorEntero2) {
        this.valorEntero2 = valorEntero2;
    }

    public String getValorCaneda1() {
        return valorCaneda1;
    }

    public void setValorCaneda1(String valorCaneda1) {
        this.valorCaneda1 = valorCaneda1;
    }

    public String getValorCaneda2() {
        return valorCaneda2;
    }

    public void setValorCaneda2(String valorCaneda2) {
        this.valorCaneda2 = valorCaneda2;
    }

    public Double getValorMoneda1() {
        return valorMoneda1;
    }

    public void setValorMoneda1(Double valorMoneda1) {
        this.valorMoneda1 = valorMoneda1;
    }

    public Double getValorMoneda2() {
        return valorMoneda2;
    }

    public void setValorMoneda2(Double valorMoneda2) {
        this.valorMoneda2 = valorMoneda2;
    }

    public Float getValorFloat1() {
        return valorFloat1;
    }

    public void setValorFloat1(Float valorFloat1) {
        this.valorFloat1 = valorFloat1;
    }

    public Float getValorFloat2() {
        return valorFloat2;
    }

    public void setValorFloat2(Float valorFloat2) {
        this.valorFloat2 = valorFloat2;
    }

    public String getValorFecha1() {
        return valorFecha1;
    }

    public void setValorFecha1(String valorFecha1) {
        this.valorFecha1 = valorFecha1;
    }

    public String getValorFecha2() {
        return valorFecha2;
    }

    public void setValorFecha2(String valorFecha2) {
        this.valorFecha2 = valorFecha2;
    }
}
