package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.DetallePromocionEntity;

import java.util.List;

@Dao
public interface DetallePromocionDao {
    @Query("SELECT * FROM tblDetallePromociones")
    LiveData<List<DetallePromocionEntity>> getAllDetallePromocion();

    @Query("DELETE FROM tblDetallePromociones")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblDetallePromociones'")
    void deleteSequence();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DetallePromocionEntity... detallePromocion);

    @Insert
    void insertList(List<DetallePromocionEntity> detallePromociones);

    @Query("SELECT * FROM tblDetallePromociones WHERE ntra = :ntra and flag = :flag ")
    List<DetallePromocionEntity> obtener_flag(int ntra, int flag);
}
