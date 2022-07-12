package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

/*
@Entity(tableName = "tblPrecio",
        foreignKeys = @ForeignKey(entity = ProductoEntity.class,
                parentColumns = "codProducto",
                childColumns = "codProducto",
                onDelete = CASCADE ))

 */
@Entity(tableName = Constante.NAME_TABLE_PRECIO)
public class PrecioEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "ntraPrecio")
    public int ntraPrecio;

    @ColumnInfo(name = "codProducto")
    public String codProducto;

    @ColumnInfo(name = "tipoListaPrecio")
    public int tipoListaPrecio;

    @ColumnInfo(name = "precioVenta")
    public double precioVenta;

    @ColumnInfo(name = "descListaPrecio")
    public String descListaPrecio;

    public PrecioEntity(int ntraPrecio, String codProducto, int tipoListaPrecio, double precioVenta,String descListaPrecio) {
        this.ntraPrecio = ntraPrecio;
        this.codProducto = codProducto;
        this.tipoListaPrecio = tipoListaPrecio;
        this.precioVenta = precioVenta;
        this.descListaPrecio = descListaPrecio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNtraPrecio() {
        return ntraPrecio;
    }

    public void setNtraPrecio(int ntraPrecio) {
        this.ntraPrecio = ntraPrecio;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public int getTipoListaPrecio() {
        return tipoListaPrecio;
    }

    public void setTipoListaPrecio(int tipoListaPrecio) {
        this.tipoListaPrecio = tipoListaPrecio;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getDescListaPrecio() {
        return descListaPrecio;
    }

    public void setDescListaPrecio(String descListaPrecio) {
        this.descListaPrecio = descListaPrecio;
    }
}
