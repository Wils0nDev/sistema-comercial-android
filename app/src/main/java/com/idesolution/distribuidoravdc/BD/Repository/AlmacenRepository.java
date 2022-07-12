package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AlmacenDao;
import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Entity.AlmacenEntity;

import java.util.List;

public class AlmacenRepository {
    private AlmacenDao mAlmacenDao;
    private LiveData<List<AlmacenEntity>> mAllAlmacen;

    AlmacenRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        mAlmacenDao = db.almacenDao();
        mAllAlmacen = mAlmacenDao.getAllAlmacenes();
    }

    LiveData<List<AlmacenEntity>> getAllAlmacenes() {
        return mAllAlmacen;
    }

    void insertList(List<AlmacenEntity> almacenes) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            mAlmacenDao.deleteAll();
            mAlmacenDao.deleteSequence();
            mAlmacenDao.insertList(almacenes);
        });
    }
}
