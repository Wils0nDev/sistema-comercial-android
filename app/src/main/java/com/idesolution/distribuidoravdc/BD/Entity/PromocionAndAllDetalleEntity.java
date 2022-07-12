package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PromocionAndAllDetalleEntity {
    @Embedded
    public PromocionEntity promocionEntity;

    @Relation(parentColumn = "ntraPromocion", entityColumn = "ntraPromocion", entity = DetallePromocionEntity.class)
    public List<DetallePromocionEntity> listDetallePromocion;

    public PromocionEntity getPromocionEntity() {
        return promocionEntity;
    }

    public void setPromocionEntity(PromocionEntity promocionEntity) {
        this.promocionEntity = promocionEntity;
    }

    public List<DetallePromocionEntity> getListDetallePromocion() {
        return listDetallePromocion;
    }

    public void setListDetallePromocion(List<DetallePromocionEntity> listDetallePromocion) {
        this.listDetallePromocion = listDetallePromocion;
    }
}
