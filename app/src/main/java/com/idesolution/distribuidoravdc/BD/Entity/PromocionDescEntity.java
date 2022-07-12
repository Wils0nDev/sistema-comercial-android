package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName = Constante.NAME_TABLE_PROMOCION_DESC)
public class PromocionDescEntity {
    @PrimaryKey
    @ColumnInfo(name = "ntraPromocion")
    @NonNull
    public int ntraPromocion;

    @ColumnInfo(name = "descripcion")
    public String descripcion;

    @NonNull
    public int getNtraPromocion() {
        return ntraPromocion;
    }

    public void setNtraPromocion(@NonNull int ntraPromocion) {
        this.ntraPromocion = ntraPromocion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PromocionDescEntity(@NonNull int ntraPromocion, String descripcion) {


        this.ntraPromocion = ntraPromocion;
        this.descripcion = descripcion;
    }
}
