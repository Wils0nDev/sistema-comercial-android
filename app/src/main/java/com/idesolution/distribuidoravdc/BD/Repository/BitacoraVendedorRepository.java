package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.BitacoraVendedorDao;
import com.idesolution.distribuidoravdc.BD.Entity.BitacoraVendedorEntity;

import java.util.List;

public class BitacoraVendedorRepository {

    private BitacoraVendedorDao bitacoraVendedorDao;
    private LiveData<List<BitacoraVendedorEntity>> mAllBitacoraVendedor;

    public BitacoraVendedorRepository(Application application) {

        AppDataBase db = AppDataBase.getDatabase(application);
        bitacoraVendedorDao = db.bitacoraVendedorDao();
        mAllBitacoraVendedor = bitacoraVendedorDao.getmAllBitacorasVendedorSin();

    }

   public LiveData<List<BitacoraVendedorEntity>> getmAllBitacorasVendedorSin() {
        return mAllBitacoraVendedor;
    }


    void insert(BitacoraVendedorEntity bitacoraVendedorEntity) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            bitacoraVendedorDao.insert(bitacoraVendedorEntity);
        });
    }

    public LiveData<Integer> obtenerVisita(int codRuta){ return  bitacoraVendedorDao.obtenerVisita(codRuta); }

    void update(int codRuta, String horaFinal){
        AppDataBase.databaseWriteExecutor.execute(() -> {

            bitacoraVendedorDao.update(codRuta,horaFinal);
        });
    }

    void deleteAll(){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            bitacoraVendedorDao.deleteAll();
        });
    }

    void updateSincroBi(){
        AppDataBase.databaseWriteExecutor.execute(() -> {

            bitacoraVendedorDao.updateSincroBi();
        });
    }


}
