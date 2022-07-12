package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName = Constante.NAME_TABLE_PRODUCTO)
public class ProductoEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ntraProducto")
    public int ntraProducto;

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

    @ColumnInfo(name = "codDesProducto")
    public String codDesProducto;

    //Datos extras que perntenecen a otras tablas

    @ColumnInfo(name = "stock")
    public int stock;

    //Precio registrado con defecto 0
    @ColumnInfo(name = "precio")
    public double precio;

    @ColumnInfo(name = "tipoListaPrecio")
    public int tipoListaPrecio;

    @ColumnInfo(name = "tipoProducto")
    public int tipoProducto;

    public String getCodProducto() {
        return codProducto;
    }

    public ProductoEntity(@NonNull String codProducto, @NonNull String descripcion, int codCategoria, int codUnidadBaseVenta, String codDesProducto, int stock, double precio, int tipoListaPrecio, int tipoProducto) {
        this.codProducto = codProducto;
        this.descripcion = descripcion;
        this.codCategoria = codCategoria;
        this.codUnidadBaseVenta = codUnidadBaseVenta;
        this.codDesProducto = codDesProducto;
        this.stock = stock;
        this.precio = precio;
        this.tipoListaPrecio = tipoListaPrecio;
        this.tipoProducto = tipoProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    @NonNull
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

    public String getCodDesProducto() {
        return codDesProducto;
    }

    public void setCodDesProducto(String codDesProducto) {
        this.codDesProducto = codDesProducto;
    }

    public int getNtraProducto() {
        return ntraProducto;
    }

    public void setNtraProducto(int ntraProducto) {
        this.ntraProducto = ntraProducto;
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

    public int getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(int tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
}
