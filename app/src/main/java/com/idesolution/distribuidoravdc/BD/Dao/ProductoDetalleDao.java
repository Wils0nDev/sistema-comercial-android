package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.idesolution.distribuidoravdc.BD.Entity.ProductoDetalleEntity;

@Dao
public interface ProductoDetalleDao {
    @Transaction
    @Query("SELECT * FROM tblProducto WHERE codProducto = :codProducto LIMIT 1")
    LiveData<ProductoDetalleEntity> loadProductoDetalleFindCodProd(String codProducto);
}
