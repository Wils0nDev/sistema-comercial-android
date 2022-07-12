package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName = Constante.NAME_TABLE_INVENTARIO)
public class InventarioEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "ntraInventario")
    @NonNull
    public int ntraInventario;

    @ColumnInfo(name = "codAlamacen")
    @NonNull
    public int codAlamacen;

    @ColumnInfo(name = "codProducto")
    @NonNull
    public String codProducto;

    @ColumnInfo(name = "stock")
    @NonNull
    public int stock;

    public InventarioEntity(int ntraInventario, int codAlamacen, String codProducto, int stock) {
        this.ntraInventario = ntraInventario;
        this.codAlamacen = codAlamacen;
        this.codProducto = codProducto;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNtraInventario() {
        return ntraInventario;
    }

    public void setNtraInventario(int ntraInventario) {
        this.ntraInventario = ntraInventario;
    }

    public int getCodAlamacen() {
        return codAlamacen;
    }

    public void setCodAlamacen(int codAlamacen) {
        this.codAlamacen = codAlamacen;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
