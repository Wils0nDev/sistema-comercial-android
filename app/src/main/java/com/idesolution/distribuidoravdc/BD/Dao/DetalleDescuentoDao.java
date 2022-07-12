package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.DetalleDescuentoEntity;

import java.util.List;

@Dao
public interface DetalleDescuentoDao {
    @Query("SELECT * FROM tblDetalleDescuentos")
    LiveData<List<DetalleDescuentoEntity>> getAllDetalleDescuento();

    @Query("DELETE FROM tblDetalleDescuentos")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblDetalleDescuentos'")
    void deleteSequence();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DetalleDescuentoEntity... detalleDescuento);

    @Insert
    void insertList(List<DetalleDescuentoEntity> descuentos);
}
