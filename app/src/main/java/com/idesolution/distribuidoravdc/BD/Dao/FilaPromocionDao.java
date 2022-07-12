package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.FilaPromocion;
import com.idesolution.distribuidoravdc.BD.Entity.filaPromocionDos;

import java.util.List;

@Dao
public interface FilaPromocionDao {
    @Insert
    void insert(FilaPromocion filaPromocion);

    @Insert //(onConflict = OnConflictStrategy.IGNORE)
    void insertLista(List<FilaPromocion> filaPromocions);

    @Query("SELECT * FROM table_promocion_habilitada ")
    LiveData<List<FilaPromocion>> getAllPromocionesHabilitadas();

    @Query("DELETE FROM table_promocion_habilitada")
    void deleteAll();

    @Query("SELECT p.id, p.ntra, p.ntraPromocion, p.flag, p.descripcion, p.codProductoInicio, p.codProductoFin, p.detalle, p.estado, h.flag as 'flagCombo' " +
            "FROM table_promocion_habilitada h " +
            "INNER JOIN tblPromociones p ON h.ntra = p.ntra " +
            "WHERE p.flag = 100 ")
    LiveData<List<filaPromocionDos>> promocionesHabilitadas();
}
