package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.AlmacenEntity;

import java.util.List;

@Dao
public interface AlmacenDao {
    @Query("DELETE FROM tblAlmacen")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblAlmacen'")
    void deleteSequence();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AlmacenEntity... almacen);

    @Query("SELECT * FROM tblAlmacen ")
    LiveData<List<AlmacenEntity>> getAllAlmacenes();

    @Insert
    void insertList(List<AlmacenEntity> almacenes);
}
