
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListInventario {

    @SerializedName("ntraInventario")
    @Expose
    private Integer ntraInventario;
    @SerializedName("codAlmacen")
    @Expose
    private Integer codAlmacen;
    @SerializedName("codProducto")
    @Expose
    private String codProducto;
    @SerializedName("stock")
    @Expose
    private Integer stock;

    public Integer getNtraInventario() {
        return ntraInventario;
    }

    public void setNtraInventario(Integer ntraInventario) {
        this.ntraInventario = ntraInventario;
    }

    public Integer getCodAlmacen() {
        return codAlmacen;
    }

    public void setCodAlmacen(Integer codAlmacen) {
        this.codAlmacen = codAlmacen;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}
