package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.UbigeoDao;
import com.idesolution.distribuidoravdc.BD.Entity.DepartamentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DistritoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ProvinciaEntity;

import java.util.List;

public class UbigeoRepository {
    private UbigeoDao ubigeoDao;

    private LiveData<List<DepartamentoEntity>> mAllDepartamentos;
    private LiveData<List<ProvinciaEntity>> mAllProvincias;
    private LiveData<List<DistritoEntity>> mAllDistritos;

    UbigeoRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        ubigeoDao = db.ubigeoDao();
        mAllDepartamentos = ubigeoDao.findAllDepartamentos();
    }

    LiveData<List<DepartamentoEntity>> getmAllDepartamentos() {
        return mAllDepartamentos;
    }


    public void insertListDep(List<DepartamentoEntity> lista) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            ubigeoDao.deleteAllDep();
            ubigeoDao.deleteSequenceDep();
            ubigeoDao.insertListDep(lista);
        });
    }

    public void insertListProv(List<ProvinciaEntity> lista) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            ubigeoDao.deleteAllProv();
            ubigeoDao.deleteSequenceProv();
            ubigeoDao.insertListProv(lista);
        });
    }

    public void insertListDist(List<DistritoEntity> lista) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            ubigeoDao.deleteAllDist();
            ubigeoDao.deleteSequenceDist();
            ubigeoDao.insertListDist(lista);
        });
    }

    public LiveData<List<ProvinciaEntity>> getAllProvincias(String codDep) {
        return ubigeoDao.findAllProvByCodDep(codDep);
    }

    public LiveData<List<DistritoEntity>> getAllDistritos(String codDep, String codProv) {
        return ubigeoDao.findAllDitsByCodDepProv(codDep,codProv);
    }
}
