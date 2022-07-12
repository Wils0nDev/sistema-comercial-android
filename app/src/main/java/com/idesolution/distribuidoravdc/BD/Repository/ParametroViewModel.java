package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.Parametro;
import com.idesolution.distribuidoravdc.BD.Entity.PrecioEntity;
import com.idesolution.distribuidoravdc.IO.Response.ListParametro;
import com.idesolution.distribuidoravdc.IO.Response.ListPrecio;

import java.util.ArrayList;
import java.util.List;

public class ParametroViewModel extends AndroidViewModel {
    private ParametroRepository Repository;
    private LiveData<List<Parametro>> mAllParametros;

    public ParametroViewModel (Application application) {
        super(application);
        Repository = new ParametroRepository(application);
        mAllParametros = Repository.getmAllParametro();
    }
    public LiveData<List<Parametro>> getmAllParametros() { return mAllParametros; }

    public LiveData<Double> obtenerDouble(Integer codigo) {
        return Repository.obtenerDouble(codigo);
    }

    public LiveData<Integer> obtenerEntero(Integer codigo) {
        return Repository.obtenerEntero(codigo);
    }

    public void InsertAll(List<ListParametro> listParametros) {
        List<Parametro> lista = new ArrayList<>();
        Parametro parametro;
        for (ListParametro param: listParametros) {
            parametro = new Parametro(param.getCodParametro(),param.getTipo(),param.getValorEntero1(),
                    param.getValorEntero2(),param.getValorCaneda1(),param.getValorCaneda2(),
                    param.getValorMoneda1(),param.getValorMoneda2(),param.getValorFloat1(),
                    param.getValorFloat2(),param.getValorFecha1(),param.getValorFecha2());
            lista.add(parametro);

        }
        Repository.insertList(lista);
    }
}
