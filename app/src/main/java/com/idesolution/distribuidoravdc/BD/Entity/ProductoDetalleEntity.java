package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ProductoDetalleEntity {
    @Embedded
    public ProductoEntity producto;

    @Relation(parentColumn = "codProducto", entityColumn = "codProducto", entity = PrecioEntity.class)
    public List<PrecioEntity> listPrecios;

    @Relation(parentColumn = "codProducto", entityColumn = "codProducto", entity = InventarioEntity.class)
    public List<InventarioAndAlmacenEntity> listInventarios;

    @Relation(parentColumn = "codProducto", entityColumn = "codProductoInicio", entity = PromocionEntity.class)
    public List<PromocionAndAllDetalleEntity> listPromociones;

    @Relation(parentColumn = "codProducto", entityColumn = "valorInicial", entity = DescuentoEntity.class)
    public List<DescuentoAndAllDetalleEntity> listDescuentos;



    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public List<PrecioEntity> getListPrecios() {
        return listPrecios;
    }

    public void setListPrecios(List<PrecioEntity> listPrecios) {
        this.listPrecios = listPrecios;
    }

    public List<InventarioAndAlmacenEntity> getListInventarios() {
        return listInventarios;
    }

    public void setListInventarios(List<InventarioAndAlmacenEntity> listInventarios) {
        this.listInventarios = listInventarios;
    }

    public List<PromocionAndAllDetalleEntity> getListPromociones() {
        return listPromociones;
    }

    public void setListPromociones(List<PromocionAndAllDetalleEntity> listPromociones) {
        this.listPromociones = listPromociones;
    }

    public List<DescuentoAndAllDetalleEntity> getListDescuentos() {
        return listDescuentos;
    }

    public void setListDescuentos(List<DescuentoAndAllDetalleEntity> listDescuentos) {
        this.listDescuentos = listDescuentos;
    }
}
