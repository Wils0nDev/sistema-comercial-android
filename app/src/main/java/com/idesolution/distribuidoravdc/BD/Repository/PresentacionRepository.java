package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.PresentacionDao;
import com.idesolution.distribuidoravdc.BD.Entity.PresentacionEntity;

import java.util.List;

public class PresentacionRepository {
    private PresentacionDao mPresentacionDao;
    private LiveData<List<PresentacionEntity>> mAllPresentaciones;

    PresentacionRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        mPresentacionDao = db.presentacionDao();
        mAllPresentaciones = mPresentacionDao.getAllPresentacion();
    }

    LiveData<List<PresentacionEntity>> getAllPresentaciones() {
        return mAllPresentaciones;
    }

    void insertList(List<PresentacionEntity> presentaciones) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            mPresentacionDao.deleteAll();
            mPresentacionDao.deleteSequence();
            mPresentacionDao.insertList(presentaciones);
        });
    }

    void deleteAll(){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            mPresentacionDao.deleteAll();
        });
    }

    LiveData<Integer> obtenerCantidadEnUnidadesBase(String codProducto, Integer codPresentacion) {
        return mPresentacionDao.obtenerCantidadEnUnidadesBase(codProducto, codPresentacion);
    }
}
