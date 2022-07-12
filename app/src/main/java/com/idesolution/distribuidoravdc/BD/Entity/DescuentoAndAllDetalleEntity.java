package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class DescuentoAndAllDetalleEntity {
    @Embedded
    public DescuentoEntity descuentoEntity;

    @Relation(parentColumn = "ntraDescuento", entityColumn = "ntraDescuento", entity = DetalleDescuentoEntity.class)
    public List<DetalleDescuentoEntity> listDetalleDescuento;

    public DescuentoEntity getDescuentoEntity() {
        return descuentoEntity;
    }

    public void setDescuentoEntity(DescuentoEntity descuentoEntity) {
        this.descuentoEntity = descuentoEntity;
    }

    public List<DetalleDescuentoEntity> getListDetalleDescuento() {
        return listDetalleDescuento;
    }

    public void setListDetalleDescuento(List<DetalleDescuentoEntity> listDetalleDescuento) {
        this.listDetalleDescuento = listDetalleDescuento;
    }
}
