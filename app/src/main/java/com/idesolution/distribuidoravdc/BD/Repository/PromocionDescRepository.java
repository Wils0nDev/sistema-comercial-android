package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.PromocionDescDao;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionDescEntity;

import java.util.List;

public class PromocionDescRepository {
    private PromocionDescDao promocionDescDao;

    PromocionDescRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        promocionDescDao = db.promocionDescDao();
    }

    void insertList(List<PromocionDescEntity> listPromociones) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            promocionDescDao.deleteAll();
            promocionDescDao.insertList(listPromociones);
        });
    }


}
