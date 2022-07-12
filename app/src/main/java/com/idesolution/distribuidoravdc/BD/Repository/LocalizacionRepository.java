package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.LocalizacionDao;
import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionClienteEntity;
import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionEntity;

import java.util.List;

public class LocalizacionRepository {
    private LocalizacionDao localDao;
    public LocalizacionRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        localDao = db.localizacionDao();
    }

    LiveData<List<LocalizacionClienteEntity>> obtenerCoordenadas() { return localDao.obtenerCoordenadas(); }
    void insertList(List<LocalizacionEntity> localizaciones) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            localDao.deleteAll();
            localDao.deleteSequence();
            localDao.insertList(localizaciones);
        });
    }
}
