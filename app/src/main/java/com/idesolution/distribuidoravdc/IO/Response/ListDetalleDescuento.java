
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListDetalleDescuento {

    @SerializedName("ntra_flag")
    @Expose
    private String ntraFlag;
    @SerializedName("flag")
    @Expose
    private Integer flag;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("valorEntero1")
    @Expose
    private Integer valorEntero1;
    @SerializedName("valorEntero2")
    @Expose
    private Integer valorEntero2;
    @SerializedName("valorMoneda1")
    @Expose
    private Integer valorMoneda1;
    @SerializedName("valorMoneda2")
    @Expose
    private Integer valorMoneda2;
    @SerializedName("valorCadena1")
    @Expose
    private String valorCadena1;
    @SerializedName("valorCadena2")
    @Expose
    private String valorCadena2;
    @SerializedName("valorFecha1")
    @Expose
    private String valorFecha1;
    @SerializedName("valorFecha2")
    @Expose
    private String valorFecha2;
    @SerializedName("estado")
    @Expose
    private Integer estado;

    public String getNtraFlag() {
        return ntraFlag;
    }

    public void setNtraFlag(String ntraFlag) {
        this.ntraFlag = ntraFlag;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Integer getValorMoneda1() {
        return valorMoneda1;
    }

    public void setValorMoneda1(Integer valorMoneda1) {
        this.valorMoneda1 = valorMoneda1;
    }

    public Integer getValorMoneda2() {
        return valorMoneda2;
    }

    public void setValorMoneda2(Integer valorMoneda2) {
        this.valorMoneda2 = valorMoneda2;
    }

    public String getValorCadena1() {
        return valorCadena1;
    }

    public void setValorCadena1(String valorCadena1) {
        this.valorCadena1 = valorCadena1;
    }

    public String getValorCadena2() {
        return valorCadena2;
    }

    public void setValorCadena2(String valorCadena2) {
        this.valorCadena2 = valorCadena2;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

}
