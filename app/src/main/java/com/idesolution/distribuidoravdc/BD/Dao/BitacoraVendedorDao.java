package com.idesolution.distribuidoravdc.BD.Dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import  com.idesolution.distribuidoravdc.BD.Entity.BitacoraVendedorEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ClienteSpinnerEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCliente;

import java.util.List;

@Dao
public interface BitacoraVendedorDao {

    @Query("DELETE FROM table_bitacoraVendedor WHERE codRuta != (SELECT codRuta FROM table_cliente)" )
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='table_bitacoraVendedor'")
    void deleteSequence();

    @Insert //(onConflict = OnConflictStrategy.IGNORE)
    void insert(BitacoraVendedorEntity bitacoraVendedorEntity);

    @Query("SELECT estado FROM table_bitacoraVendedor WHERE codRuta = :codRuta ORDER BY ntra DESC LIMIT 1 ")
    LiveData<Integer> obtenerVisita(Integer codRuta);

    @Query("UPDATE table_bitacoraVendedor SET estado = 0 and horaFinal = :horaFinal WHERE codRuta = :codRuta AND ntra = (SELECT ntra FROM  table_bitacoraVendedor ORDER BY  ntra DESC LIMIT 1)")
    void update(Integer codRuta, String horaFinal);



    @Query("SELECT * FROM table_bitacoraVendedor WHERE internet = 0 ")
    LiveData<List<BitacoraVendedorEntity>> getmAllBitacorasVendedorSin();

    @Query("SELECT * FROM table_bitacoraVendedor WHERE internet = 0 ")
    List<BitacoraVendedorEntity> getmAllBitacorasVendedorSinRepo();

    @Query("UPDATE table_bitacoraVendedor SET internet = 1 ")
    void updateSincroBi();




}
