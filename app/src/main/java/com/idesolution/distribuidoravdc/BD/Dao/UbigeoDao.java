package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.DepartamentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DistritoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ProvinciaEntity;

import java.util.List;

@Dao
public interface UbigeoDao {

    //Listar ubigeo
    @Query("SELECT * FROM tblDepartamento")
    LiveData<List<DepartamentoEntity>> findAllDepartamentos();

    @Query("SELECT * FROM tblProvincia WHERE  codDepartamento = :cod ")
    LiveData<List<ProvinciaEntity>> findAllProvByCodDep(String cod);

    @Query("SELECT * FROM tblDistrito WHERE  codDepartamento = :codDep AND codProvincia = :codPro ")
    LiveData<List<DistritoEntity>> findAllDitsByCodDepProv(String codDep, String codPro);

    //Insertar ubigeo
    @Insert
    void insertListDep(List<DepartamentoEntity> departamentos);
    @Insert
    void insertListProv(List<ProvinciaEntity> provincias);
    @Insert
    void insertListDist(List<DistritoEntity> distritos);

    //Eliminar ubigeo
    @Query("DELETE FROM tblDepartamento")
    void deleteAllDep();
    @Query("DELETE FROM sqlite_sequence WHERE name='tblDepartamento'")
    void deleteSequenceDep();
    @Query("DELETE FROM tblProvincia")
    void deleteAllProv();
    @Query("DELETE FROM sqlite_sequence WHERE name='tblProvincia'")
    void deleteSequenceProv();
    @Query("DELETE FROM tblDistrito")
    void deleteAllDist();
    @Query("DELETE FROM sqlite_sequence WHERE name='tblDistrito'")
    void deleteSequenceDist();

}
