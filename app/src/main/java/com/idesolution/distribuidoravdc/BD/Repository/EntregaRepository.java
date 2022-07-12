package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.EntregaDao;
import com.idesolution.distribuidoravdc.BD.Entity.EntregaEntity;

import java.util.List;

public class EntregaRepository {
    private EntregaDao mEntregaDao;
    private LiveData<List<EntregaEntity>> mAllEntregas;
    private LiveData<List<EntregaEntity>> mAllEntregasSinc;

    EntregaRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        mEntregaDao = db.entregaDao();
        mAllEntregas = mEntregaDao.getAllEntregas();
        mAllEntregasSinc = mEntregaDao.getEntregaSinc();
    }

    LiveData<List<EntregaEntity>> getAllEntregas() {
        return mAllEntregas;
    }
    LiveData<List<EntregaEntity>> getAllEntregasSinc() {
        return mAllEntregasSinc;
    }

    void insertList(List<EntregaEntity> entregas) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            mEntregaDao.deleteAll();
            mEntregaDao.deleteSequence();
            mEntregaDao.insertList(entregas);
        });
    }

    void deleteAll(){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            mEntregaDao.deleteAll();
        });
    }

    void insert(EntregaEntity entregaEntity){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            mEntregaDao.insert(entregaEntity);
        });
    }
}
