package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.Ruta;

import java.util.List;

@Dao
public interface RutaDao {
    @Insert
    void insert(Ruta ruta);

    @Query("SELECT ntra, codigo, descripcion from table_ruta order by ntra asc")
    LiveData<List<Ruta>> obtenerRutas();

    @Query("DELETE FROM table_ruta")
    void deleteAll();
}
