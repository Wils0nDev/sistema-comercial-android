package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.RutaDao;
import com.idesolution.distribuidoravdc.BD.Entity.Concepto;
import com.idesolution.distribuidoravdc.BD.Entity.Ruta;

import java.util.List;

public class RutaRepository {
    private RutaDao rutaDao;

    RutaRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        rutaDao = db.rutaDao();
    }

    LiveData<List<Ruta>> obtenerRutas() {
        return rutaDao.obtenerRutas();
    }

}
