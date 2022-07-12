package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.PreventaPromocionEntity;

import java.util.List;

@Dao
public interface PreventaPromocionDao {

    @Query("SELECT * FROM tblPreventaPromocion ")
    LiveData<List<PreventaPromocionEntity>> getAllPreventaPromocion();

    @Insert
    void insert(PreventaPromocionEntity preventaPromocion);

    @Query("DELETE FROM tblPreventaPromocion")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblPreventaPromocion'")
    void deleteSequence();

    @Insert
    void insertList(List<PreventaPromocionEntity> listpreventaPromocion);

    @Query("SELECT * FROM tblPreventaPromocion WHERE codPreventa = :ntra ")
    List<PreventaPromocionEntity> obtenterPromocionesNtra(int ntra);

    @Query("SELECT COUNT(codPreventa) FROM tblPreventaPromocion WHERE codPreventa = :ntra ")
    Integer verificarPromocionesNtra(int ntra);

    @Query("DELETE FROM tblPreventaPromocion WHERE codPreventa = :ntra ")
    void eliminarPreventaPromocion(int ntra);
}
