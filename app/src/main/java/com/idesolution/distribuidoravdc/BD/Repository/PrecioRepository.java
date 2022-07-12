package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.PrecioDao;
import com.idesolution.distribuidoravdc.BD.Entity.PrecioEntity;

import java.util.List;

public class PrecioRepository {
    private PrecioDao mPrecioDao;
    private LiveData<List<PrecioEntity>> mAllPrecios;

    PrecioRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        mPrecioDao = db.PrecioDao();
        mAllPrecios = mPrecioDao.getAllPrecio();
    }

    LiveData<List<PrecioEntity>> getAllPrecios() {
        return mAllPrecios;
    }

    void insertList(List<PrecioEntity> precios) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            mPrecioDao.deleteAll();
            mPrecioDao.deleteSequence();
            mPrecioDao.insertList(precios);
        });
    }

    void deleteAll(){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            mPrecioDao.deleteAll();
        });
    }
}
