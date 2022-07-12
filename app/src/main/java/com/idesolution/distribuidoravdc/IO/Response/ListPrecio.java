
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPrecio {

    @SerializedName("ntraPrecio")
    @Expose
    private Integer ntraPrecio;
    @SerializedName("codProducto")
    @Expose
    private String codProducto;
    @SerializedName("tipoListaPrecio")
    @Expose
    private Integer tipoListaPrecio;
    @SerializedName("precioVenta")
    @Expose
    private Double precioVenta;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public Integer getNtraPrecio() {
        return ntraPrecio;
    }

    public void setNtraPrecio(Integer ntraPrecio) {
        this.ntraPrecio = ntraPrecio;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public Integer getTipoListaPrecio() {
        return tipoListaPrecio;
    }

    public void setTipoListaPrecio(Integer tipoListaPrecio) {
        this.tipoListaPrecio = tipoListaPrecio;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
