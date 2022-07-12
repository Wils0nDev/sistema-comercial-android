package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PreventaTotalEntity {
    @Embedded
    public Preventa preventa;

    @Relation(parentColumn = "ntraPreventa", entityColumn = "codPreventa", entity = PreventaDetalleEntity.class)
    public List<PreventaDetalleEntity> listPreventaDetalle;

    @Relation(parentColumn = "ntraPreventa", entityColumn = "codPreventa", entity = PreventaPromocionEntity.class)
    public List<PreventaPromocionEntity> listPreventaPromocion;

    @Relation(parentColumn = "ntraPreventa", entityColumn = "codPreventa", entity = PreventaDescuentoEntity.class)
    public List<PreventaDescuentoEntity> listPreventaDescuento;

    @Relation(parentColumn = "codPuntoEntrega", entityColumn = "id", entity = EntregaEntity.class)
    public EntregaEntity punto_entrega;

}
