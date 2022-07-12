package com.idesolution.distribuidoravdc.IO.Request;

public class RequestPreventaPromocion {
    private int codPreventa;
    private int codPromocion;
    private short itemPreventa;
    private short itemPromocionado;

    public RequestPreventaPromocion(int codPreventa, int codPromocion, short itemPreventa, short itemPromocionado) {
        this.codPreventa = codPreventa;
        this.codPromocion = codPromocion;
        this.itemPreventa = itemPreventa;
        this.itemPromocionado = itemPromocionado;
    }

    public int getCodPreventa() {
        return codPreventa;
    }

    public void setCodPreventa(int codPreventa) {
        this.codPreventa = codPreventa;
    }

    public int getCodPromocion() {
        return codPromocion;
    }

    public void setCodPromocion(int codPromocion) {
        this.codPromocion = codPromocion;
    }

    public short getItemPreventa() {
        return itemPreventa;
    }

    public void setItemPreventa(short itemPreventa) {
        this.itemPreventa = itemPreventa;
    }

    public short getItemPromocionado() {
        return itemPromocionado;
    }

    public void setItemPromocionado(short itemPromocionado) {
        this.itemPromocionado = itemPromocionado;
    }
}
