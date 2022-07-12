package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.ParametroDao;
import com.idesolution.distribuidoravdc.BD.Entity.Parametro;
import com.idesolution.distribuidoravdc.BD.Entity.PresentacionEntity;

import java.util.List;

public class ParametroRepository {
    private ParametroDao parametroDao;
    private LiveData<List<Parametro>> mAllParametro;
    private LiveData<List<Parametro>> mAllParametroSinc;

    ParametroRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        parametroDao = db.parametroDao();
        mAllParametro = parametroDao.getAllParametros();
        mAllParametroSinc = parametroDao.getAllParametros();
    }
    LiveData<List<Parametro>> getmAllParametro() {
        return mAllParametro;
    }

    LiveData<Double> obtenerDouble(Integer codigo) {
        return parametroDao.obtenerDouble(codigo);
    }

    LiveData<Integer> obtenerEntero(Integer codigo) {
        return parametroDao.obtenerEntero(codigo);
    }

    void insertList(List<Parametro> parametros) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            parametroDao.deleteAll();
            parametroDao.deleteSequence();
            parametroDao.insertList(parametros);
        });
    }
}
