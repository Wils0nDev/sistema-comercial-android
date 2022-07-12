package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName =  Constante.NAME_TABLE_PREVENTA_PROMOCION)
public class PreventaPromocionEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "codPreventa")
    @NonNull
    public int codPreventa;

    @ColumnInfo(name = "codPromocion")
    @NonNull
    public int codPromocion;

    @ColumnInfo(name = "itemPreventa")
    @NonNull
    public short itemPreventa;

    @ColumnInfo(name = "itemPromocionado")
    @NonNull
    public short itemPromocionado;

    @ColumnInfo(name = "flag")
    @NonNull
    public int flag;

    /*
    public PreventaPromocionEntity(int codPreventa, int codPromocion, short itemPreventa, short itemPromocionado) {
        this.codPreventa = codPreventa;
        this.codPromocion = codPromocion;
        this.itemPreventa = itemPreventa;
        this.itemPromocionado = itemPromocionado;
    }
     */

    //@Ignore
    public PreventaPromocionEntity(int codPreventa, int codPromocion, short itemPreventa, short itemPromocionado, int flag) {
        this.codPreventa = codPreventa;
        this.codPromocion = codPromocion;
        this.itemPreventa = itemPreventa;
        this.itemPromocionado = itemPromocionado;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
