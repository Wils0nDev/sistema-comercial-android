package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.VendedorEntity;

import java.util.List;

@Dao
public interface VendedorDao {
    @Query("SELECT * FROM tblVendedor WHERE  ntraUsuario = :id ")
    LiveData<VendedorEntity> findVendedorByUsuario(int id);

    @Query("SELECT * FROM tblVendedor ")
    LiveData<List<VendedorEntity>> getAllVendedor();

    @Query("DELETE FROM tblVendedor")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblVendedor'")
    void deleteSequence();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(VendedorEntity... vendedor);

    @Insert
    void insertList(List<VendedorEntity> vendedores);
    
     @Query("SELECT descRuta FROM tblVendedor WHERE ntraUsuario = :ntraUsuario ")
    LiveData<String> obtenerDescRuta( int ntraUsuario);

    @Query("SELECT codRuta FROM tblVendedor WHERE ntraUsuario = :ntraUsuario ")
    LiveData<Integer> obtenerCodRuta( int ntraUsuario);

    @Query("SELECT ntraUsuario FROM tblVendedor LIMIT 1")
    Integer obtenerNtraUsuario();

    @Query("SELECT codRuta FROM tblVendedor LIMIT 1")
    Integer obtenerCodigoRuta();
}
