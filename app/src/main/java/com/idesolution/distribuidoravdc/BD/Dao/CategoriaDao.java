package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.CategoriaEntity;

import java.util.List;

@Dao
public interface CategoriaDao {
    @Query("SELECT * FROM tblCategoria WHERE ntraCategoria = :id ")
    CategoriaEntity findCategoriaId(int id);

    @Query("SELECT * FROM tblCategoria ")
    LiveData<List<CategoriaEntity>> getAllCategorias();

    @Query("DELETE FROM tblCategoria")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblCategoria'")
    void deleteSequence();

    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert
    void insert(CategoriaEntity... categorias);

    @Insert
    void insertList(List<CategoriaEntity> categorias);
}
