package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.PresentacionEntity;

import java.util.List;

@Dao
public interface PresentacionDao {
    @Query("SELECT * FROM tblDetallePresentacion WHERE id = :id ")
    PresentacionEntity findPresentacionId(int id);

    @Query("SELECT * FROM tblDetallePresentacion ")
    LiveData<List<PresentacionEntity>> getAllPresentacion();

    @Query("DELETE FROM tblDetallePresentacion")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblDetallePresentacion'")
    void deleteSequence();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PresentacionEntity... presentacion);

    @Insert
    void insertList(List<PresentacionEntity> presentaciones);

    @Query("SELECT cantidadUnidadBase FROM tblDetallePresentacion WHERE codProducto = :codProducto and codPresentancion = :codPresentacion ")
    LiveData<Integer> obtenerCantidadEnUnidadesBase(String codProducto, Integer codPresentacion);
}
