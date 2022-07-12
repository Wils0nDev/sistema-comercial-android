package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.PromocionDescEntity;

import java.util.List;

@Dao
public interface PromocionDescDao {

    @Query("DELETE FROM tblPromocionesDesc")
    void deleteAll();

    @Insert
    void insertList(List<PromocionDescEntity> promociones);

}
