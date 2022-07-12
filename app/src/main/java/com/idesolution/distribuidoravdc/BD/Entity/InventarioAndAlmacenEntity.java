package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class InventarioAndAlmacenEntity {
    @Embedded
    public InventarioEntity inventarioEntity;

    @Relation(parentColumn = "codAlamacen", entityColumn = "ntraAlmacen", entity = AlmacenEntity.class)
    public AlmacenEntity InventarioAlmacen;

    public InventarioEntity getInventarioEntity() {
        return inventarioEntity;
    }

    public void setInventarioEntity(InventarioEntity inventarioEntity) {
        this.inventarioEntity = inventarioEntity;
    }

    public AlmacenEntity getInventarioAlmacen() {
        return InventarioAlmacen;
    }

    public void setInventarioAlmacen(AlmacenEntity inventarioAlmacen) {
        InventarioAlmacen = inventarioAlmacen;
    }
}
