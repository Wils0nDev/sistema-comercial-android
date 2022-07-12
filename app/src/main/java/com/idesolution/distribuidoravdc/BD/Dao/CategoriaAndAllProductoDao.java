package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.CategoriaAndAllProductoEntity;

@Dao
public interface CategoriaAndAllProductoDao {
    @Query("SELECT * FROM tblCategoria WHERE ntraCategoria = :categoriaId")
    CategoriaAndAllProductoEntity loadCategoriaAllProductos(long categoriaId);
}
