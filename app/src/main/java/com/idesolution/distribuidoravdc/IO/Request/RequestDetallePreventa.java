package com.idesolution.distribuidoravdc.IO.Request;

public class RequestDetallePreventa {

    public int codPreventa;
    public short itemPreventa;
    public int codPresentacion;
    public String codProducto;
    public int codAlmacen;
    public int cantidadPresentacion;
    public int cantidadUnidadBase;
    public double precioVenta;
    public short tipoProducto;

    public RequestDetallePreventa(int codPreventa, short itemPreventa, int codPresentacion, String codProducto, int codAlmacen, int cantidadPresentacion, int cantidadUnidadBase, double precioVenta, short tipoProducto) {
        this.codPreventa = codPreventa;
        this.itemPreventa = itemPreventa;
        this.codPresentacion = codPresentacion;
        this.codProducto = codProducto;
        this.codAlmacen = codAlmacen;
        this.cantidadPresentacion = cantidadPresentacion;
        this.cantidadUnidadBase = cantidadUnidadBase;
        this.precioVenta = precioVenta;
        this.tipoProducto = tipoProducto;
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
}
