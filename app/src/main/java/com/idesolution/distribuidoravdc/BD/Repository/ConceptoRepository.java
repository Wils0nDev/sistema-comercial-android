package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.ConceptoDao;
import com.idesolution.distribuidoravdc.BD.Entity.Concepto;

import java.util.ArrayList;
import java.util.List;

public class ConceptoRepository {
    private ConceptoDao conceptoDao;
    private LiveData<List<Concepto>> AllConceptos;

    ConceptoRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        conceptoDao = db.conceptoDao();
        AllConceptos = conceptoDao.getConceptos();
    }

    LiveData<List<Concepto>> getAllConceptos() {
        return AllConceptos;
    }

    LiveData<List<String>> obtenerConceptos(Integer codigo) {
        return conceptoDao.obtenerConceptos(codigo);
    }

    LiveData<String> obtenerConcepto(Integer codigo, short correlativo) {
        return conceptoDao.obtenerConcepto(codigo, correlativo);
    }

    void insert(Concepto concepto) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            conceptoDao.insert(concepto);
        });
    }

    void insertList(List<Concepto> conceptos) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            conceptoDao.deleteAll();
            conceptoDao.deleteSequence();
            conceptoDao.insertList(conceptos);
        });
    }

    public LiveData<List<Concepto>> getConceptoEntidad(int codConcepto) {
        return conceptoDao.getConceptoEntidad(codConcepto);
    }
}
