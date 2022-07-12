package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.Concepto;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ConceptoDao {
    @Insert
    void insert(Concepto concepto);

    @Query("SELECT * from table_concepto")
    LiveData<List<Concepto>> getConceptos();

    @Query("SELECT descripcion from table_concepto where codConcepto = :codigo order by correlativo asc")
    LiveData<List<String>> obtenerConceptos(Integer codigo);

    @Query("SELECT descripcion from table_concepto where codConcepto = :codigo and correlativo = :correlativo")
    LiveData<String> obtenerConcepto(Integer codigo, short correlativo);

    @Query("DELETE FROM table_concepto")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='table_concepto'")
    void deleteSequence();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Concepto... concepto);

    @Insert
    void insertList(List<Concepto> conceptos);

    @Query("SELECT * from table_concepto where codConcepto = :codConcepto order by correlativo asc")
    LiveData<List<Concepto>> getConceptoEntidad(int codConcepto);
}
