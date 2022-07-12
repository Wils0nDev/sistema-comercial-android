package com.idesolution.distribuidoravdc.BD.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.ClienteSpinnerEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCliente;
import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionClienteEntity;
import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionEntity;

import java.util.List;
@Dao
public interface LocalizacionDao {

    @Insert
    void insert(LocalizacionEntity localizacion);

    @Query("UPDATE table_localizacion SET flag = 1, codPersona = :codPersona WHERE ntra = :ntra and flag = 0 ")
    void actualizarDatosLocalizacion(Integer codPersona, Integer ntra);

    @Query("SELECT MAX(ntra) as 'ntra' FROM table_localizacion LIMIT 1")
    Integer ntraLocalizacion();

    @Query("SELECT * FROM table_localizacion WHERE numeroDocumento = :documento")
    LocalizacionEntity obtenerLocalizacion(String documento);

    @Query("SELECT  nombreCompleto, coordenadaX, coordenadaY " +
            "FROM table_localizacion "
            )
    LiveData<List<LocalizacionClienteEntity>> obtenerCoordenadas();

    @Insert
    void insertList(List<LocalizacionEntity> localizaciones);

    @Query("DELETE FROM table_localizacion")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='table_localizacion'")
    void deleteSequence();

}
