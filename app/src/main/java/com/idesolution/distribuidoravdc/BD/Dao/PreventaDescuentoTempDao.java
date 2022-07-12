package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.PreventaDescuento;

import java.util.List;

@Dao
public interface PreventaDescuentoTempDao {
    @Insert
    void insertPreventaDescuento(PreventaDescuento preventaDescuento);

    @Insert
    void insertPreventaDescuentoList(List<PreventaDescuento> listPreventaDescuento);

    @Query("SELECT SUM(importe) FROM preventa_descuento WHERE tipo = 1  ")
    LiveData<Double> importeDescuentosItem();

    @Query("DELETE FROM preventa_descuento WHERE tipo = 2  ")
    void deleteAllPreventaDescuento();

    @Query("SELECT SUM(importe) FROM preventa_descuento WHERE tipo = 1  ")
    Double importeDescuentosDetalles();

    @Query("SELECT COUNT(itemPreventa) FROM preventa_descuento WHERE tipo = 1  ")
    Integer countDescuentos();

    @Query("SELECT importe FROM preventa_descuento WHERE itemPreventa = :item AND tipo = 1  ")
    Double descuentoXItem(int item);

    @Query("SELECT COUNT(itemPreventa) FROM preventa_descuento WHERE itemPreventa = :item AND tipo = 1  ")
    Integer countDescuentoXItem(int item);

    @Query("DELETE FROM preventa_descuento")
    void deleteAll();

    @Query("SELECT * FROM preventa_descuento ")
    List<PreventaDescuento> AllPreventaDescuento();

    @Query("DELETE FROM preventa_descuento WHERE itemPreventa = :item ")
    void quitarPreventaDescuentoPorItem(Integer item);

    @Query("UPDATE preventa_descuento SET itemPreventa = itemPreventa - :cont WHERE itemPreventa > :item ")
    void ordenarItemsPreventaDescu(int item, int cont);

    @Query("DELETE FROM preventa_descuento WHERE itemPreventa IN (SELECT itemPromocionado FROM preventa_promocion WHERE itemPreventa = :item ) ")
    void quitarProductoConDescuentoCarrito(Integer item);

    @Query("SELECT SUM(importe) FROM preventa_descuento WHERE itemPreventa = :item ")
    LiveData<Double> obtenerDescuentoPreventaItem(int item);
}
