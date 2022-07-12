package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.room.ColumnInfo;

public class FilaDetallePreventa {

    @ColumnInfo(name = "codPreventa")
    public int codPreventa;

    @ColumnInfo(name = "itemPreventa")
    public short itemPreventa;

    @ColumnInfo(name = "codPresentacion")
    public int codPresentacion;

    @ColumnInfo(name = "codProducto")
    public String codProducto;

    @ColumnInfo(name = "codAlmacen")
    public int codAlmacen;

    @ColumnInfo(name = "cantidadPresentacion")
    public int cantidadPresentacion;

    @ColumnInfo(name = "cantidadUnidadBase")
    public int cantidadUnidadBase;

    @ColumnInfo(name = "precioVenta")
    public double precioVenta;

    @ColumnInfo(name = "TipoProducto")
    public short tipoProducto;

    @ColumnInfo(name = "descripcionProducto")
    public String descripcionProducto;

    @ColumnInfo(name = "descripcionUnidad")
    private String descripcionUnidad;

    public int getCodPreventa() {
        return codPreventa;
    }

    public void setCodPreventa(int codPreventa) {
        this.codPreventa = codPreventa;
    }

    public short getItemPreventa() {
        return itemPreventa;
    }

    public void setItemPreventa(short itemPreventa) {
        this.itemPreventa = itemPreventa;
    }

    public int getCodPresentacion() {
        return codPresentacion;
    }

    public void setCodPresentacion(int codPresentacion) {
        this.codPresentacion = codPresentacion;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public int getCodAlmacen() {
        return codAlmacen;
    }

    public void setCodAlmacen(int codAlmacen) {
        this.codAlmacen = codAlmacen;
    }

    public int getCantidadPresentacion() {
        return cantidadPresentacion;
    }

    public void setCantidadPresentacion(int cantidadPresentacion) {
        this.cantidadPresentacion = cantidadPresentacion;
    }

    public int getCantidadUnidadBase() {
        return cantidadUnidadBase;
    }

    public void setCantidadUnidadBase(int cantidadUnidadBase) {
        this.cantidadUnidadBase = cantidadUnidadBase;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public short getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(short tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getDescripcionUnidad() {
        return descripcionUnidad;
    }

    public void setDescripcionUnidad(String descripcionUnidad) {
        this.descripcionUnidad = descripcionUnidad;
    }

}
