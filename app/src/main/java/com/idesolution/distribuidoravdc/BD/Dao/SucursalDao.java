package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.SucursalEntity;

import java.util.List;

@Dao
public interface SucursalDao {
    @Query("SELECT * FROM tblSucursal ")
    LiveData<List<SucursalEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SucursalEntity... sucursal);
}
