package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.PreventaPromocion;

import java.util.List;

@Dao
public interface PreventaPromocionTempDao {
    @Insert
    void insertPreventaPromocion(PreventaPromocion preventaPromocion);

    @Insert
    void insertPreventaPromocionList(List<PreventaPromocion> listPreventaPromocion);

    @Query("DELETE FROM preventa_promocion WHERE itemPromocionado = :item ")
    void quitarPreventaPromocionCarrito(Integer item);

    @Query("UPDATE preventa_promocion SET itemPreventa = itemPreventa - 1, itemPromocionado = itemPromocionado - 1 WHERE itemPromocionado > :item ")
    void ordenarItemsPreventaPromocion(Integer item);

    @Query("SELECT COUNT(itemPreventa) FROM preventa_promocion WHERE itemPreventa = :item ")
    Integer buscarItemPromocion(Integer item);

    @Query("SELECT COUNT(itemPreventa) FROM preventa_promocion WHERE itemPromocionado = :item ")
    Integer buscarItemPromocionado(Integer item);

    @Query("DELETE FROM preventa_promocion WHERE itemPreventa = :item ")
    void quitarPreventaPromocionPorItem(Integer item);

    @Query("UPDATE preventa_promocion SET itemPreventa = itemPreventa - :cont, itemPromocionado = itemPromocionado - :cont WHERE itemPromocionado > :item ")
    void ordenarItemsPreventaPromo(int item, int cont);

    @Query("SELECT COUNT(itemPreventa) FROM preventa_promocion WHERE itemPreventa = :item ")
    LiveData<Integer> buscarItemPromocionDos(Integer item);

    @Query("DELETE FROM preventa_promocion")
    void deleteAll();

    @Query("SELECT * FROM preventa_promocion ")
    List<PreventaPromocion> AllPreventaPromocion();
}
