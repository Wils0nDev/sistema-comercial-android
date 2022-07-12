package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.EntregaEntity;

import java.util.List;

@Dao
public interface EntregaDao {
    @Query("SELECT * FROM tblpuntoentrega WHERE ntraPuntoEntrega = :id ")
    EntregaEntity findEntregaId(int id);

    @Query("SELECT * FROM tblpuntoentrega ")
    LiveData<List<EntregaEntity>> getAllEntregas();

    @Query("DELETE FROM tblpuntoentrega")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblpuntoentrega'")
    void deleteSequence();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(EntregaEntity... entregas);

    @Insert
    void insertList(List<EntregaEntity> entregas);

    @Insert
    void insert(EntregaEntity entrega);

    @Query("SELECT * FROM tblPuntoEntrega WHERE id = :id")
    EntregaEntity obtenerEntregaPreventa(int id);

    @Query("UPDATE tblPuntoEntrega SET flag = 1, ntraPuntoEntrega = :ntraPuntoEntrega, codPersona = :codCliente WHERE id = :ntra ")
    void actualizarDireccionPreventa(Integer ntraPuntoEntrega, Integer codCliente, Integer ntra);

    @Query("SELECT * from tblPuntoEntrega where flag = 0")
    LiveData<List<EntregaEntity>> getEntregaSinc();

    @Query("SELECT * from tblPuntoEntrega where flag = 0")
    List<EntregaEntity> getEntregaSincRepo();

    @Query("SELECT flag from tblPuntoEntrega where id = :idP ")
    Integer obtenerFlagPuntoEntrega(int idP);

    @Query("SELECT ntraPuntoEntrega from tblPuntoEntrega where id = :idP ")
    Integer obtenerNtraPuntoEntrega(int idP);
}
