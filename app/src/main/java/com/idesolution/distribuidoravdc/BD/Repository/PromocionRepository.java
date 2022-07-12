package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.CabeceraDao;
import com.idesolution.distribuidoravdc.BD.Dao.DetallePromocionDao;
import com.idesolution.distribuidoravdc.BD.Dao.FilaDescuentoDao;
import com.idesolution.distribuidoravdc.BD.Dao.FilaPromocionDao;
import com.idesolution.distribuidoravdc.BD.Dao.PromocionDao;
import com.idesolution.distribuidoravdc.BD.Dao.VendedorDao;
import com.idesolution.distribuidoravdc.BD.Entity.DescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DetallePromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaDescuento;
import com.idesolution.distribuidoravdc.BD.Entity.FilaPromocion;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.filaPromocionDos;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.CompletableOnSubscribe;

public class PromocionRepository {
    private PromocionDao mPromocionDao;
    private FilaPromocionDao filaPromocionDao;
    private FilaDescuentoDao filaDescuentoDao;
    private VendedorDao vendedorDao;
    private CabeceraDao cabeceraDao;
    private DetallePromocionDao mDetallePromocionDao;

    private LiveData<List<PromocionEntity>> mAllPromocion;
    private LiveData<List<DetallePromocionEntity>> mAllDetallePromocion;
    private MutableLiveData<List<FilaPromocion>> listaPromocionesLD;
    private LiveData<List<FilaPromocion>> retorno;

    int cont = Constante.g_const_0;
    int flag = Constante.g_const_0;
    private String codProducto;
    private int cantidad;
    private double importe;
    private int ntraUsuario;
    private int codPersona;
    private int cantidades;

    private List<PromocionEntity> listaPromociones;
    private PromocionEntity promocion;
    private List<FilaPromocion> listaNtraPromociones;
    private FilaPromocion filaPromocion;
    //private List<Integer> listaPromociones;
    private List<DescuentoEntity> listaDescuentos;
    private List<FilaDescuento> listaNtraDescuentos;
    private int tipoVenta;

    PromocionRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        mPromocionDao = db.promocionDao();
        filaPromocionDao = db.filaPromocionDao();
        filaDescuentoDao = db.filaDescuentoDao();
        vendedorDao = db.vendedorDao();
        cabeceraDao = db.cabeceraDao();
        mDetallePromocionDao = db.detallePromocionDao();
        mAllPromocion = mPromocionDao.getAllPromocion();
        mAllDetallePromocion = mDetallePromocionDao.getAllDetallePromocion();
    }

    LiveData<List<PromocionEntity>> getAllPromocion() {
        return mAllPromocion;
    }
    LiveData<List<DetallePromocionEntity>> getAllDetallePromocion() {
        return mAllDetallePromocion;
    }

    void insertList(List<PromocionEntity> listPromociones, List<DetallePromocionEntity> listDetallePromociones) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            mPromocionDao.deleteAll();
            mDetallePromocionDao.deleteAll();
            mPromocionDao.deleteSequence();
            mDetallePromocionDao.deleteSequence();
            mPromocionDao.insertList(listPromociones);
            mDetallePromocionDao.insertList(listDetallePromociones);
        });
    }


    /*public MutableLiveData<List<FilaPromocion>> obtenerPromociones(String codProducto, int cantidad, double importe){
        this.codProducto = codProducto;
        this.cantidad = cantidad;
        this.importe = importe;

        listaPromociones = new ArrayList<PromocionEntity>();
        listaNtraPromociones = new ArrayList<FilaPromocion>();

        AppDataBase.databaseWriteExecutor.execute(() -> {
            this.codPersona = cabeceraDao.obtenerCodCliente();
        });
            if (verificarProductoPromocionado()){
                //Traer promociones obtenerPromociones
                AppDataBase.databaseWriteExecutor.execute(() -> {
                    listaPromociones = mPromocionDao.obtenerPromociones(this.codProducto);
                });
                for(PromocionEntity p : listaPromociones){
                    filaPromocion = null;
                    if(validarRestricciones(p.getNtra())){
                        flag = Constante.g_const_0;
                        if(validarCantidadImporteParaCombo(p.getNtra()))
                            flag = Constante.g_const_1;
                        else
                            flag = Constante.g_const_0;

                        filaPromocion = new FilaPromocion(p.getNtra(), flag);
                        listaNtraPromociones.add(filaPromocion);
                    }
                }
            }
        listaPromocionesLD = new MutableLiveData<List<FilaPromocion>>();
        listaPromocionesLD.setValue(listaNtraPromociones);
        //return LiveData<listaNtraPromociones>;
        return listaPromocionesLD;
    }

    public boolean verificarProductoPromocionado(){
        flag = Constante.g_const_0;

        AppDataBase.databaseWriteExecutor.execute(() -> {
            cont = mPromocionDao.verificarPromocion(this.codProducto);
            if(cont != Constante.g_const_0)
                flag = Constante.g_const_1;

        });
        if(flag == Constante.g_const_0 )
            return false;
        else
            return true;
    }

    public boolean validarCantidadImporteParaCombo(Integer ntra){
        flag = Constante.g_const_1;
        AppDataBase.databaseWriteExecutor.execute(() -> {
            promocion = mPromocionDao.obtener_flag(ntra, Constante.g_const_2);
            if(promocion.getCodProductoFin() == String.valueOf(Constante.g_s_const_1)){ //CANTIDAD
                if(this.cantidad < Integer.parseInt(promocion.getCodProductoInicio()))
                    flag = Constante.g_const_0;
            }
            else{ //SOLES (IMPORTE)
                if(this.importe < Double.parseDouble(promocion.getCodProductoInicio()))
                    flag = Constante.g_const_0;
            }
        });
        if(flag == Constante.g_const_0)
            return false;
        else
            return true;
    }

    public boolean validarRestricciones(Integer ntra){
        flag = Constante.g_const_1;
        AppDataBase.databaseWriteExecutor.execute(() -> {

            //FLAG 4 VENDEDOR QUE APLICA A LA PROMOCION
            promocion = null;
            if(flag == Constante.g_const_1){
                cont = Constante.g_const_0;
                cont = mPromocionDao.validar_exitencia_flag(ntra, Constante.g_const_4);
                if(cont != Constante.g_const_0){
                    promocion = mPromocionDao.obtener_flag(ntra, Constante.g_const_4);
                    ntraUsuario = vendedorDao.obtenerNtraUsuario();
                    if(ntraUsuario != Integer.parseInt(promocion.getCodProductoInicio()))
                        flag = Constante.g_const_0;
                }
            }

            //FLAG 5 CLIENTE QUE APLICA A LA PROMOCION
            promocion = null;
            if(flag == Constante.g_const_1){
                cont = Constante.g_const_0;
                cont = mPromocionDao.validar_exitencia_flag(ntra, Constante.g_const_5);
                if(cont != Constante.g_const_0){
                    promocion = mPromocionDao.obtener_flag(ntra, Constante.g_const_5);
                    if(this.codPersona != Integer.parseInt(promocion.getCodProductoInicio()))
                        flag = Constante.g_const_0;
                }
            }

            //FLAG 6 VECES QUE SE PUEDE USAR LA PROMOCION
            promocion = null;
            if(flag == Constante.g_const_1){
                cont = Constante.g_const_0;
                cont = mPromocionDao.validar_exitencia_flag(ntra, Constante.g_const_6);
                if(cont != Constante.g_const_0){
                    promocion = mPromocionDao.obtener_flag(ntra, Constante.g_const_6);
                    cantidades = mPromocionDao.cantidad_preventas_usan_promocion(ntra);
                    if(cantidades >= Integer.parseInt(promocion.getCodProductoInicio()))
                        flag = Constante.g_const_0;
                }
            }

            //FLAG 7 VECES QUE UN VENDEDOR PUEDE USAR LA PROMOCION
            promocion = null;
            if(flag == Constante.g_const_1){
                cont = Constante.g_const_0;
                cont = mPromocionDao.validar_exitencia_flag(ntra, Constante.g_const_7);
                if(cont != Constante.g_const_0){
                    promocion = mPromocionDao.obtener_flag(ntra, Constante.g_const_7);
                    ntraUsuario = vendedorDao.obtenerNtraUsuario();
                    cantidades = mPromocionDao.cantidad_preventas_usan_promocion_vendedor(ntra, ntraUsuario);
                    if(cantidades >= Integer.parseInt(promocion.getCodProductoInicio()))
                        flag = Constante.g_const_0;
                }
            }

            //FLAG 8 VECES QUE UN CLIENTE PUEDE USAR LA PROMOCION
            promocion = null;
            if(flag == Constante.g_const_1){
                cont = Constante.g_const_0;
                cont = mPromocionDao.validar_exitencia_flag(ntra, Constante.g_const_8);
                if(cont != Constante.g_const_0){
                    promocion = mPromocionDao.obtener_flag(ntra, Constante.g_const_8);
                    cantidades = mPromocionDao.cantidad_preventas_usan_promocion_cliente(ntra, this.codPersona);
                    if(cantidades >= Integer.parseInt(promocion.getCodProductoInicio()))
                        flag = Constante.g_const_0;
                }
            }
        });
        if(flag == Constante.g_const_0 )
            return false;
        else
            return true;
    }*/

    //####################
    public void obtenerPromociones(String codProducto, int cantidad, double importe){
        this.codProducto = codProducto;
        this.cantidad = cantidad;
        this.importe = importe;

        listaPromociones = new ArrayList<PromocionEntity>();
        listaNtraPromociones = new ArrayList<FilaPromocion>();

        AppDataBase.databaseWriteExecutor.execute(() -> {
            this.codPersona = cabeceraDao.obtenerCodCliente();

            flag = Constante.g_const_0;
            filaPromocionDao.deleteAll();
            cont = mPromocionDao.verificarPromocion(this.codProducto);
            if(cont != Constante.g_const_0) {
                //flag = Constante.g_const_1;
                listaPromociones = mPromocionDao.obtenerPromociones(this.codProducto);
                for(PromocionEntity p : listaPromociones){
                    filaPromocion = null;

                    //FLAG 4 VENDEDOR QUE APLICA A LA PROMOCION
                    promocion = null;
                    //if(flag == Constante.g_const_1){
                    cont = Constante.g_const_0;
                    cont = mPromocionDao.validar_exitencia_flag(p.getNtra(), Constante.g_const_4);
                    if(cont != Constante.g_const_0){
                        promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_4);
                        ntraUsuario = vendedorDao.obtenerNtraUsuario();
                        if(ntraUsuario != Integer.parseInt(promocion.getCodProductoInicio()))
                            continue; //flag = Constante.g_const_0;
                    }
                    //}

                    //FLAG 5 CLIENTE QUE APLICA A LA PROMOCION
                    promocion = null;
                    //if(flag == Constante.g_const_1){
                    cont = Constante.g_const_0;
                    cont = mPromocionDao.validar_exitencia_flag(p.getNtra(), Constante.g_const_5);
                    if(cont != Constante.g_const_0){
                        promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_5);
                        if(this.codPersona != Integer.parseInt(promocion.getCodProductoInicio()))
                            continue; //flag = Constante.g_const_0;
                    }
                        //}

                    //FLAG 6 VECES QUE SE PUEDE USAR LA PROMOCION
                    promocion = null;
                        //if(flag == Constante.g_const_1){
                    cont = Constante.g_const_0;
                    cont = mPromocionDao.validar_exitencia_flag(p.getNtra(), Constante.g_const_6);
                    if(cont != Constante.g_const_0){
                        promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_6);
                        cantidades = mPromocionDao.cantidad_preventas_usan_promocion(p.getNtra());
                        if(cantidades >= Integer.parseInt(promocion.getCodProductoInicio()))
                            continue; //flag = Constante.g_const_0;
                    }
                        //}

                        //FLAG 7 VECES QUE UN VENDEDOR PUEDE USAR LA PROMOCION
                        promocion = null;
                        //if(flag == Constante.g_const_1){
                    cont = Constante.g_const_0;
                    cont = mPromocionDao.validar_exitencia_flag(p.getNtra(), Constante.g_const_7);
                    if(cont != Constante.g_const_0){
                        promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_7);
                        ntraUsuario = vendedorDao.obtenerNtraUsuario();
                        cantidades = mPromocionDao.cantidad_preventas_usan_promocion_vendedor(p.getNtra(), ntraUsuario);
                        if(cantidades >= Integer.parseInt(promocion.getCodProductoInicio()))
                            continue; //flag = Constante.g_const_0;
                    }
                        //}

                        //FLAG 8 VECES QUE UN CLIENTE PUEDE USAR LA PROMOCION
                        promocion = null;
                        //if(flag == Constante.g_const_1){
                    cont = Constante.g_const_0;
                    cont = mPromocionDao.validar_exitencia_flag(p.getNtra(), Constante.g_const_8);
                    if(cont != Constante.g_const_0){
                        promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_8);
                        cantidades = mPromocionDao.cantidad_preventas_usan_promocion_cliente(p.getNtra(), this.codPersona);
                        if(cantidades >= Integer.parseInt(promocion.getCodProductoInicio()))
                            continue; //flag = Constante.g_const_0;
                    }

                    //FLAG 9 PROMOCION PARA VENTA AL CONTADO O CREDITO
                    promocion = null;
                    tipoVenta = Constante.g_s_const_0;
                    //if(flag == Constante.g_const_1){
                    cont = Constante.g_const_0;
                    cont = mPromocionDao.validar_exitencia_flag(p.getNtra(), Constante.g_const_9);
                    if(cont != Constante.g_const_0){
                        promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_9);
                        tipoVenta = cabeceraDao.obtenerTipoVenta();
                        if(tipoVenta != Integer.parseInt(promocion.getCodProductoInicio()))
                            continue; //flag = Constante.g_const_0;
                    }
                        //}
                    //
                    //FLAG 101 VALIDAR RANGO DE HORA
                    promocion = null;
                    cont = Constante.g_const_0;
                    cont = mPromocionDao.validar_exitencia_flag(p.getNtra(), Constante.g_const_101);
                    if(cont != Constante.g_const_0){
                        promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_101);
                        //Validar fecha
                        //Obtener fecha actual
                        Calendar calendario = Calendar.getInstance();
                        int horaActual;
                        int hora, minutos, segundos;
                        int horaMin,minutosMin,segundosMin;
                        int horaMax,minutosMax,segundosMax;
                        //String horaCompleta;
                        String horaCompletaMin;
                        String horaCompletaMax;
                        hora = calendario.get(Calendar.HOUR_OF_DAY);
                        minutos = calendario.get(Calendar.MINUTE);
                        segundos = calendario.get(Calendar.SECOND);
                        horaActual = 60*hora + minutos;
                        //horaCompleta = hora + ":" + minutos + ":" + segundos;

                        //Llenar datos de hora minima y maxima
                        if(promocion != null){
                            horaCompletaMin = promocion.getCodProductoInicio();
                            horaCompletaMax = promocion.getCodProductoFin();

                            String[] partsHoraInicio = horaCompletaMin.split(":");
                            String[] partsHoraMax = horaCompletaMax.split(":");

                            horaMin = Integer.parseInt(partsHoraInicio[0]);
                            minutosMin = Integer.parseInt(partsHoraInicio[1]);

                            horaMax = Integer.parseInt(partsHoraMax[0]);
                            minutosMax = Integer.parseInt(partsHoraMax[1]);

                            int horatotalMin = 60*horaMin +minutosMin;
                            int horatotalMax = 60*horaMax +minutosMax;

                            if(!(horaActual >=horatotalMin && horaActual <= horatotalMax)){
                                continue; //flag = Constante.g_const_0;
                            }

                        }

                    }

                    flag = Constante.g_const_1;

                    /*promocion = null;
                    promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_2);
                    if(promocion.getCodProductoFin() == String.valueOf(Constante.g_s_const_1)){ //CANTIDAD
                        if(this.cantidad < Integer.parseInt(promocion.getCodProductoInicio()))
                            flag = Constante.g_const_0;
                    }
                    else{ //SOLES (IMPORTE)
                        if(this.importe < Double.parseDouble(promocion.getCodProductoInicio()))
                            flag = Constante.g_const_0;
                    }*/

                    filaPromocion = new FilaPromocion(p.getNtra(), flag);
                    listaNtraPromociones.add(filaPromocion);

                }
                if(listaNtraPromociones.size() > Constante.g_const_0)
                    filaPromocionDao.insertLista(listaNtraPromociones);

            }
        });
        /*retorno = filaPromocionDao.getAllPromocionesHabilitadas();
        return retorno;*/
    }

    void deleteAllFilaPromocion(){
        AppDataBase.databaseWriteExecutor.execute(()-> {
            filaPromocionDao.deleteAll();
            filaDescuentoDao.deleteAll();
        });

    }

    public LiveData<List<FilaPromocion>> obtenerPromos(){
        return filaPromocionDao.getAllPromocionesHabilitadas();
    }

    public LiveData<List<filaPromocionDos>> promocionesHabilitadas(){
        return filaPromocionDao.promocionesHabilitadas();
    }

    public LiveData<PromocionEntity> obtener_flag_promocion(int ntra, int flag){
        return mPromocionDao.obtener_flag_promocion(ntra, flag);
    }


    //################################## DESCUENTOS

    /*public void obtenerDescuentos(String codProducto, int cantidad, double importe){
        this.codProducto = Constante.G_CONST_VACIO;
        this.cantidad = Constante.g_const_0;
        this.importe = Constante.g_const_0;
        this.codProducto = codProducto;
        this.cantidad = cantidad;
        this.importe = importe;

        listaDescuentos = new ArrayList<DescuentoEntity>();
        listaNtraDescuentos = new ArrayList<FilaDescuento>();

        AppDataBase.databaseWriteExecutor.execute(() -> {
            this.codPersona = cabeceraDao.obtenerCodCliente();

            flag = Constante.g_const_0;
            filaPromocionDao.deleteAll();
            cont = mPromocionDao.verificarPromocion(this.codProducto);
            if(cont != Constante.g_const_0) {
                //flag = Constante.g_const_1;
                listaPromociones = mPromocionDao.obtenerPromociones(this.codProducto);
                for(PromocionEntity p : listaPromociones){
                    filaPromocion = null;

                    //FLAG 4 VENDEDOR QUE APLICA A LA PROMOCION
                    promocion = null;
                    //if(flag == Constante.g_const_1){
                    cont = Constante.g_const_0;
                    cont = mPromocionDao.validar_exitencia_flag(p.getNtra(), Constante.g_const_4);
                    if(cont != Constante.g_const_0){
                        promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_4);
                        ntraUsuario = vendedorDao.obtenerNtraUsuario();
                        if(ntraUsuario != Integer.parseInt(promocion.getCodProductoInicio()))
                            continue; //flag = Constante.g_const_0;
                    }
                    //}

                    //FLAG 5 CLIENTE QUE APLICA A LA PROMOCION
                    promocion = null;
                    //if(flag == Constante.g_const_1){
                    cont = Constante.g_const_0;
                    cont = mPromocionDao.validar_exitencia_flag(p.getNtra(), Constante.g_const_5);
                    if(cont != Constante.g_const_0){
                        promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_5);
                        if(this.codPersona != Integer.parseInt(promocion.getCodProductoInicio()))
                            continue; //flag = Constante.g_const_0;
                    }
                    //}

                    //FLAG 6 VECES QUE SE PUEDE USAR LA PROMOCION
                    promocion = null;
                    //if(flag == Constante.g_const_1){
                    cont = Constante.g_const_0;
                    cont = mPromocionDao.validar_exitencia_flag(p.getNtra(), Constante.g_const_6);
                    if(cont != Constante.g_const_0){
                        promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_6);
                        cantidades = mPromocionDao.cantidad_preventas_usan_promocion(p.getNtra());
                        if(cantidades >= Integer.parseInt(promocion.getCodProductoInicio()))
                            continue; //flag = Constante.g_const_0;
                    }
                    //}

                    //FLAG 7 VECES QUE UN VENDEDOR PUEDE USAR LA PROMOCION
                    promocion = null;
                    //if(flag == Constante.g_const_1){
                    cont = Constante.g_const_0;
                    cont = mPromocionDao.validar_exitencia_flag(p.getNtra(), Constante.g_const_7);
                    if(cont != Constante.g_const_0){
                        promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_7);
                        ntraUsuario = vendedorDao.obtenerNtraUsuario();
                        cantidades = mPromocionDao.cantidad_preventas_usan_promocion_vendedor(p.getNtra(), ntraUsuario);
                        if(cantidades >= Integer.parseInt(promocion.getCodProductoInicio()))
                            continue; //flag = Constante.g_const_0;
                    }
                    //}

                    //FLAG 8 VECES QUE UN CLIENTE PUEDE USAR LA PROMOCION
                    promocion = null;
                    //if(flag == Constante.g_const_1){
                    cont = Constante.g_const_0;
                    cont = mPromocionDao.validar_exitencia_flag(p.getNtra(), Constante.g_const_8);
                    if(cont != Constante.g_const_0){
                        promocion = mPromocionDao.obtener_flag(p.getNtra(), Constante.g_const_8);
                        cantidades = mPromocionDao.cantidad_preventas_usan_promocion_cliente(p.getNtra(), this.codPersona);
                        if(cantidades >= Integer.parseInt(promocion.getCodProductoInicio()))
                            continue; //flag = Constante.g_const_0;
                    }
                    //}
                    //

                    flag = Constante.g_const_1;

                    filaPromocion = new FilaPromocion(p.getNtra(), flag);
                    listaNtraPromociones.add(filaPromocion);

                }
                if(listaNtraPromociones.size() > Constante.g_const_0)
                    filaPromocionDao.insertLista(listaNtraPromociones);

            }
        });
    }*/

}

