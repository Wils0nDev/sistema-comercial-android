package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.InventarioDao;
import com.idesolution.distribuidoravdc.BD.Entity.InventarioEntity;

import java.util.List;

public class InventarioRepository {
    private InventarioDao mInventarioDao;
    private LiveData<List<InventarioEntity>> mAllInventarios;

    InventarioRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        mInventarioDao = db.inventarioDao();
        mAllInventarios = mInventarioDao.getAllInventarios();
    }

    LiveData<List<InventarioEntity>> getAllInventarios() {
        return mAllInventarios;
    }

    void insertList(List<InventarioEntity> inventarios) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            mInventarioDao.deleteAll();
            mInventarioDao.deleteSequence();
            mInventarioDao.insertList(inventarios);
        });
    }
}
