package com.idesolution.distribuidoravdc.IO.Request;

public class RequestPreventaDescuento {
    private int codPreventa;
    private int codDescuento;
    private short itemPreventa;
    private double importe;

    public RequestPreventaDescuento(int codPreventa, int codDescuento, short itemPreventa, double importe) {
        this.codPreventa = codPreventa;
        this.codDescuento = codDescuento;
        this.itemPreventa = itemPreventa;
        this.importe = importe;
    }

    public int getCodPreventa() {
        return codPreventa;
    }

    public void setCodPreventa(int codPreventa) {
        this.codPreventa = codPreventa;
    }

    public int getCodDescuento() {
        return codDescuento;
    }

    public void setCodDescuento(int codDescuento) {
        this.codDescuento = codDescuento;
    }

    public short getItemPreventa() {
        return itemPreventa;
    }

    public void setItemPreventa(short itemPreventa) {
        this.itemPreventa = itemPreventa;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}
