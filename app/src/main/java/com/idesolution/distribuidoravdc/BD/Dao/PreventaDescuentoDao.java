package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.PreventaDescuentoEntity;

import java.util.List;

@Dao
public interface PreventaDescuentoDao {

    @Query("SELECT * FROM tblPreventaDescuento ")
    LiveData<List<PreventaDescuentoEntity>> getAllPreventaDescuentos();

    @Insert
    void insert(PreventaDescuentoEntity preventaDescuento);

    @Query("DELETE FROM tblPreventaDescuento")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblPreventaDescuento'")
    void deleteSequence();

    @Insert
    void insertList(List<PreventaDescuentoEntity> listpreventaDescuento);

    @Query("SELECT * FROM tblPreventaDescuento WHERE codPreventa = :ntra ")
    List<PreventaDescuentoEntity> obtenerDescuentosNtra(int ntra);

    @Query("SELECT COUNT(codPreventa) FROM tblPreventaDescuento WHERE codPreventa = :ntra ")
    Integer verificarDescuentosNtra(int ntra);

    @Query("DELETE FROM tblPreventaDescuento WHERE codPreventa = :ntra ")
    void eliminarPreventaDescuento(int ntra);
}
