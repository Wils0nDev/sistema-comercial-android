package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.InventarioEntity;

import java.util.List;

@Dao
public interface InventarioDao {
    @Query("DELETE FROM tblInventario")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblInventario'")
    void deleteSequence();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(InventarioEntity... inventario);

    @Query("SELECT * FROM tblInventario ")
    LiveData<List<InventarioEntity>> getAllInventarios();

    @Insert
    void insertList(List<InventarioEntity> inventarios);

    @Query("UPDATE tblInventario SET stock = stock - :cantidad WHERE codAlamacen = :codAlmacen AND codProducto = :codProducto ")
    void actualizarStockProducto(int cantidad, int codAlmacen, String codProducto);

    @Query("UPDATE tblInventario SET stock = stock + :cantidad WHERE codAlamacen = :codAlmacen AND codProducto = :codProducto ")
    void revertirStockProducto(int cantidad, int codAlmacen, String codProducto);
}
