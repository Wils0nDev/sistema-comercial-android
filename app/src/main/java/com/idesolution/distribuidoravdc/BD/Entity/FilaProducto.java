package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class FilaProducto {
    @ColumnInfo(name = "ntraProducto")
    public int ntra;
    @ColumnInfo(name = "codProducto")
    @NonNull
    public String codProducto;
    @ColumnInfo(name = "descripcion")
    @NonNull
    public String descripcion;
    @ColumnInfo(name = "codCategoria")
    public int codCategoria;
    @ColumnInfo(name = "codUnidadBaseVenta")
    public int codUnidadBaseVenta;
    //Datos extras que perntenecen a otras tablas
    @ColumnInfo(name = "stock")
    public int stock;
    @ColumnInfo(name = "precioVenta")
    public double precio;
    @ColumnInfo(name = "tipoListaPrecio")
    public int tipoListaPrecio;
    @ColumnInfo(name = "ntraAlmacen")
    public int codAlmacen;

    public FilaProducto(int ntra, @NonNull String codProducto, @NonNull String descripcion, int codCategoria, int codUnidadBaseVenta, int stock, double precio, int tipoListaPrecio, int codAlmacen) {
        this.ntra = ntra;
        this.codProducto = codProducto;
        this.descripcion = descripcion;
        this.codCategoria = codCategoria;
        this.codUnidadBaseVenta = codUnidadBaseVenta;
        this.stock = stock;
        this.precio = precio;
        this.tipoListaPrecio = tipoListaPrecio;
        this.codAlmacen = codAlmacen;
    }

    public int getNtra() {
        return ntra;
    }

    public void setNtra(int ntra) {
        this.ntra = ntra;
    }

    @NonNull
    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(@NonNull String codProducto) {
        this.codProducto = codProducto;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public int getCodUnidadBaseVenta() {
        return codUnidadBaseVenta;
    }

    public void setCodUnidadBaseVenta(int codUnidadBaseVenta) {
        this.codUnidadBaseVenta = codUnidadBaseVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTipoListaPrecio() {
        return tipoListaPrecio;
    }

    public void setTipoListaPrecio(int tipoListaPrecio) {
        this.tipoListaPrecio = tipoListaPrecio;
    }

    public int getCodAlmacen() {
        return codAlmacen;
    }

    public void setCodAlmacen(int codAlmacen) {
        this.codAlmacen = codAlmacen;
    }
}
