package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName =  Constante.NAME_TABLE_PREVENTA_DESCUENTO)
public class PreventaDescuentoEntity {
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

    @ColumnInfo(name = "flag")
    @NonNull
    public int flag;
/*
    public PreventaDescuentoEntity(int codPreventa, int codDescuento, short itemPreventa, double importe) {
        this.codPreventa = codPreventa;
        this.codDescuento = codDescuento;
        this.itemPreventa = itemPreventa;
        this.importe = importe;
    }
*/
    //@Ignore
    public PreventaDescuentoEntity(int codPreventa, int codDescuento, short itemPreventa, double importe, int flag) {
        this.codPreventa = codPreventa;
        this.codDescuento = codDescuento;
        this.itemPreventa = itemPreventa;
        this.importe = importe;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
