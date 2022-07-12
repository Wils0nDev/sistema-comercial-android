package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoriaAndAllProductoEntity {
    @Embedded
    public CategoriaEntity categoria;

    @Relation(parentColumn = "ntraCategoria", entityColumn = "codCategoria", entity = ProductoEntity.class)
    public List<ProductoEntity> listDepartamentos;
}
