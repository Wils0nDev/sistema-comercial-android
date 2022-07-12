package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.InventarioEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Parametro;

import java.util.List;

@Dao
public interface ParametroDao {
    @Insert
    void insert(Parametro parametro);

    @Query("SELECT valorMoneda1 from tblParametro where codParametro = :codigo ")
    LiveData<Double> obtenerDouble(Integer codigo);

    @Query("SELECT valorEntero1 from tblParametro where codParametro = :codigo ")
    LiveData<Integer> obtenerEntero(Integer codigo);

    @Query("DELETE FROM tblParametro")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblParametro'")
    void deleteSequence();

    @Query("SELECT * FROM tblParametro")
    LiveData<List<Parametro>> getAllParametros();

    @Insert
    void insertList(List<Parametro> parametros);
}
