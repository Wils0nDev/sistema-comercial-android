package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class FilaCarrito {

    @ColumnInfo(name = "ntra")
    public int ntra;

    @ColumnInfo(name = "ntraPreventa")
    public int ntraPreventa;

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

    public FilaCarrito(int ntra, int ntraPreventa, short itemPreventa, int codPresentacion, String codProducto, int codAlmacen, int cantidadPresentacion, int cantidadUnidadBase, double precioVenta, short tipoProducto, String descripcionProducto, String descripcionUnidad) {
        this.ntra = ntra;
        this.ntraPreventa = ntraPreventa;
        this.itemPreventa = itemPreventa;
        this.codPresentacion = codPresentacion;
        this.codProducto = codProducto;
        this.codAlmacen = codAlmacen;
        this.cantidadPresentacion = cantidadPresentacion;
        this.cantidadUnidadBase = cantidadUnidadBase;
        this.precioVenta = precioVenta;
        this.tipoProducto = tipoProducto;
        this.descripcionProducto = descripcionProducto;
        this.descripcionUnidad = descripcionUnidad;
    }

    public int getNtra() {
        return ntra;
    }

    public void setNtra(int ntra) {
        this.ntra = ntra;
    }

    public int getNtraPreventa() {
        return ntraPreventa;
    }

    public void setNtraPreventa(int ntraPreventa) {
        this.ntraPreventa = ntraPreventa;
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
