package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.DescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaDescuento;
import com.idesolution.distribuidoravdc.BD.Entity.filaDescuentoDos;

import java.util.List;

@Dao
public interface FilaDescuentoDao {
    @Insert
    void insert(FilaDescuento filaDescuento);

    @Insert
    void insertLista(List<FilaDescuento> filaDescuentos);

    @Query("SELECT * FROM table_descuento_habilitado ")
    LiveData<List<FilaDescuento>> getAllDescuentosHabilitados();

    @Query("DELETE FROM table_descuento_habilitado")
    void deleteAll();

    @Query("SELECT p.id, p.ntra, p.ntraDescuento, p.flag, p.descripcion, p.valorInicial, p.valorFinal, p.detalle, p.estado, p.tipoDescuento " +
            "FROM table_descuento_habilitado h " +
            "INNER JOIN tblDescuentos p ON h.ntra = p.ntra " +
            "WHERE p.flag = 100 ")
    LiveData<List<DescuentoEntity>> descuentosHabilitados();

    @Query("SELECT ntra FROM table_descuento_habilitado ")
    LiveData<List<Integer>> descuentosHabilitadosDos();
}
