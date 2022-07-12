
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListDetPreventum {

    @SerializedName("codPreventa")
    @Expose
    private Integer codPreventa;
    @SerializedName("itemPreventa")
    @Expose
    private Integer itemPreventa;
    @SerializedName("codPresentacion")
    @Expose
    private Integer codPresentacion;
    @SerializedName("codProducto")
    @Expose
    private String codProducto;
    @SerializedName("codAlmacen")
    @Expose
    private Integer codAlmacen;
    @SerializedName("cantidadPresentacion")
    @Expose
    private Integer cantidadPresentacion;
    @SerializedName("cantidadUnidadBase")
    @Expose
    private Integer cantidadUnidadBase;
    @SerializedName("precioVenta")
    @Expose
    private Double precioVenta;
    @SerializedName("TipoProducto")
    @Expose
    private Integer tipoProducto;

    public Integer getCodPreventa() {
        return codPreventa;
    }

    public void setCodPreventa(Integer codPreventa) {
        this.codPreventa = codPreventa;
    }

    public Integer getItemPreventa() {
        return itemPreventa;
    }

    public void setItemPreventa(Integer itemPreventa) {
        this.itemPreventa = itemPreventa;
    }

    public Integer getCodPresentacion() {
        return codPresentacion;
    }

    public void setCodPresentacion(Integer codPresentacion) {
        this.codPresentacion = codPresentacion;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public Integer getCodAlmacen() {
        return codAlmacen;
    }

    public void setCodAlmacen(Integer codAlmacen) {
        this.codAlmacen = codAlmacen;
    }

    public Integer getCantidadPresentacion() {
        return cantidadPresentacion;
    }

    public void setCantidadPresentacion(Integer cantidadPresentacion) {
        this.cantidadPresentacion = cantidadPresentacion;
    }

    public Integer getCantidadUnidadBase() {
        return cantidadUnidadBase;
    }

    public void setCantidadUnidadBase(Integer cantidadUnidadBase) {
        this.cantidadUnidadBase = cantidadUnidadBase;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(Integer tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

}
