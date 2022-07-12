package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName =  Constante.NAME_TABLE_PREVENTA_DETALLE)
public class PreventaDetalleEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "codPreventa")
    @NonNull
    public int codPreventa;

    @ColumnInfo(name = "itemPreventa")
    @NonNull
    public short itemPreventa;

    @ColumnInfo(name = "codPresentacion")
    @NonNull
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

    @ColumnInfo(name = "flag")
    public short flag;

    public PreventaDetalleEntity(int codPreventa, short itemPreventa, int codPresentacion, String codProducto, int codAlmacen, int cantidadPresentacion, int cantidadUnidadBase, double precioVenta, short tipoProducto, short flag) {
        this.codPreventa = codPreventa;
        this.itemPreventa = itemPreventa;
        this.codPresentacion = codPresentacion;
        this.codProducto = codProducto;
        this.codAlmacen = codAlmacen;
        this.cantidadPresentacion = cantidadPresentacion;
        this.cantidadUnidadBase = cantidadUnidadBase;
        this.precioVenta = precioVenta;
        this.tipoProducto = tipoProducto;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public short getFlag() {
        return flag;
    }

    public void setFlag(short flag) {
        this.flag = flag;
    }
}
