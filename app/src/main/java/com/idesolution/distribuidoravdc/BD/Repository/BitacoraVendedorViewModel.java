package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.BitacoraVendedorEntity;

import java.util.List;

public class BitacoraVendedorViewModel extends AndroidViewModel {

    BitacoraVendedorRepository mRepository;

    private LiveData<List<BitacoraVendedorEntity>> mAllBitacoraVendedor;


    public BitacoraVendedorViewModel (Application application) {
        super(application);
        mRepository = new BitacoraVendedorRepository(application);
        mAllBitacoraVendedor = mRepository.getmAllBitacorasVendedorSin();
    }

    public LiveData<List<BitacoraVendedorEntity>> getmAllBitacorasVendedorSin() { return mAllBitacoraVendedor; }

    public void insert(BitacoraVendedorEntity bitacoraVendedorEntity) { mRepository.insert(bitacoraVendedorEntity); }

    public LiveData<Integer> obtenerVisita(int codRuta){ return  mRepository.obtenerVisita(codRuta); }

    public void update(int codRuta, String horaFinal){   mRepository.update(codRuta, horaFinal); }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void updateSincroBi(){
        mRepository.updateSincroBi();
    }

}
