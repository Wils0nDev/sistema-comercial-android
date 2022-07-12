package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.PrecioEntity;

import java.util.List;

@Dao
public interface PrecioDao {

    @Query("SELECT * FROM tblprecio WHERE codProducto = :codProduct ")
    LiveData<List<PrecioEntity>> getCategoriaByCodProd(String codProduct);

    @Query("DELETE FROM tblPrecio")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblPrecio'")
    void deleteSequence();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PrecioEntity... precio);

    @Query("SELECT * FROM tblPrecio ")
    LiveData<List<PrecioEntity>> getAllPrecio();

    @Insert
    void insertList(List<PrecioEntity> precios);
}
