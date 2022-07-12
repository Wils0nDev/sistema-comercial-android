package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName =  "preventa_descuento")
public class PreventaDescuento {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "codPreventa")
    @NonNull
    public int codPreventa;

    @ColumnInfo(name = "codDescuento")
    @NonNull
    public int codDescuento;

    @ColumnInfo(name = "itemPreventa")
    @NonNull
    public short itemPreventa;

    @ColumnInfo(name = "importe")
    public double importe;

    @ColumnInfo(name = "tipo")
    public int tipo;

    public PreventaDescuento(int codPreventa, int codDescuento, short itemPreventa, double importe, int tipo) {
        this.codPreventa = codPreventa;
        this.codDescuento = codDescuento;
        this.itemPreventa = itemPreventa;
        this.importe = importe;
        this.tipo = tipo;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
