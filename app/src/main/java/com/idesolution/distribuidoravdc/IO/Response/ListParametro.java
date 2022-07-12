
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListParametro {

    @SerializedName("codParametro")
    @Expose
    private Integer codParametro;
    @SerializedName("tipo")
    @Expose
    private Integer tipo;
    @SerializedName("valorEntero1")
    @Expose
    private Integer valorEntero1;
    @SerializedName("valorEntero2")
    @Expose
    private Integer valorEntero2;
    @SerializedName("valorCaneda1")
    @Expose
    private String valorCaneda1;
    @SerializedName("valorCaneda2")
    @Expose
    private String valorCaneda2;
    @SerializedName("valorMoneda1")
    @Expose
    private Double valorMoneda1;
    @SerializedName("valorMoneda2")
    @Expose
    private Double valorMoneda2;
    @SerializedName("valorFloat1")
    @Expose
    private Float valorFloat1;
    @SerializedName("valorFloat2")
    @Expose
    private Float valorFloat2;
    @SerializedName("valorFecha1")
    @Expose
    private String valorFecha1;
    @SerializedName("valorFecha2")
    @Expose
    private String valorFecha2;

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
