package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.CabeceraDao;
import com.idesolution.distribuidoravdc.BD.Dao.DescuentoDao;
import com.idesolution.distribuidoravdc.BD.Dao.DetalleDescuentoDao;
import com.idesolution.distribuidoravdc.BD.Dao.FilaDescuentoDao;
import com.idesolution.distribuidoravdc.BD.Dao.FilaPromocionDao;
import com.idesolution.distribuidoravdc.BD.Dao.VendedorDao;
import com.idesolution.distribuidoravdc.BD.Entity.DescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DetalleDescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaDescuento;
import com.idesolution.distribuidoravdc.Entidad.Constante;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DescuentoRepository {
    private DescuentoDao mDescuentoDao;
    private CabeceraDao cabeceraDao;
    private FilaDescuentoDao filaDescuentoDao;
    private DetalleDescuentoDao mDetalleDescuentoDao;
    private VendedorDao vendedorDao;

    private LiveData<List<DescuentoEntity>> mAllDescuento;
    private LiveData<List<DetalleDescuentoEntity>> mAllDetalleDescuento;

    //private List<DescuentoEntity> listaDescuentos;
    //private List<FilaDescuento> listaNtraDescuentos;
    private String codProducto;
    private int cantidad;
    private double importe;


    private List<DescuentoEntity> listaDescuentos;
    private List<FilaDescuento> listaNtraDescuentos;
    private int codPersona;
    private int cont = Constante.g_const_0;
    private FilaDescuento filaDescuento;
    private DescuentoEntity descuento;
    private int ntraUsuario;
    private int cantidades;

    DescuentoRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        mDescuentoDao = db.descuentoDao();
        mDetalleDescuentoDao = db.detalleDescuentoDao();
        cabeceraDao = db.cabeceraDao();
        filaDescuentoDao = db.filaDescuentoDao();
        vendedorDao = db.vendedorDao();
        mAllDescuento = mDescuentoDao.getAllDescuento();
        mAllDetalleDescuento = mDetalleDescuentoDao.getAllDetalleDescuento();
    }

    LiveData<List<DescuentoEntity>> getAllDescuento() {
        return mAllDescuento;
    }
    LiveData<List<DetalleDescuentoEntity>> getAllDetalleDescuento() {
        return mAllDetalleDescuento;
    }

    void insertList(List<DescuentoEntity> listDescuentos, List<DetalleDescuentoEntity> listDetalleDescuentos) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            mDescuentoDao.deleteAll();
            mDetalleDescuentoDao.deleteAll();
            mDescuentoDao.deleteSequence();
            mDetalleDescuentoDao.deleteSequence();
            mDescuentoDao.insertList(listDescuentos);
            mDetalleDescuentoDao.insertList(listDetalleDescuentos);
        });
    }

    //#DESCUENTOS
    /*public void obtenerDescuentos(String codProducto, int cantidad, double importe){
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

    //############DESCUENTO
    public void obtenerDescuentos(String codProducto, int flag){
        this.codProducto = codProducto;
        //this.cantidad = cantidad;
        //this.importe = importe;

        listaDescuentos = new ArrayList<DescuentoEntity>();
        listaNtraDescuentos = new ArrayList<FilaDescuento>();

        AppDataBase.databaseWriteExecutor.execute(() -> {
            this.codPersona = cabeceraDao.obtenerCodCliente();

            //flag = Constante.g_const_0;
            filaDescuentoDao.deleteAll();
            if(flag == Constante.g_const_1)
                cont = mDescuentoDao.verificarDescuento(this.codProducto);
            else
                cont = mDescuentoDao.verificarDescuentoTotal();
            if(cont != Constante.g_const_0) {
                if(flag == Constante.g_const_1)
                    listaDescuentos = mDescuentoDao.obtenerDescuentos(this.codProducto);
                else
                    listaDescuentos = mDescuentoDao.obtenerDescuentosTotales();
                for(DescuentoEntity d : listaDescuentos){
                    filaDescuento = null;

                    //FLAG 4 VENDEDOR QUE APLICA AL USO DEL DESCUENTO
                    descuento = null;
                    cont = Constante.g_const_0;
                    cont = mDescuentoDao.validar_exitencia_flag(d.getNtra(), Constante.g_const_4);
                    if(cont != Constante.g_const_0){
                        descuento = mDescuentoDao.obtener_flag(d.getNtra(), Constante.g_const_4);
                        ntraUsuario = vendedorDao.obtenerNtraUsuario();
                        if(ntraUsuario != Integer.parseInt(descuento.getValorInicial().trim()))
                            continue;
                    }

                    //FLAG 5 CLIENTE QUE APLICA AL USO DEL DESCUENTO
                    descuento = null;
                    cont = Constante.g_const_0;
                    cont = mDescuentoDao.validar_exitencia_flag(d.getNtra(), Constante.g_const_5);
                    if(cont != Constante.g_const_0){
                        descuento = mDescuentoDao.obtener_flag(d.getNtra(), Constante.g_const_5);
                        if(this.codPersona != Integer.parseInt(descuento.getValorInicial().trim()))
                            continue;
                    }

                    //FLAG 6 VECES QUE SE PUEDE USAR EL DESCUENTO
                    descuento = null;
                    cont = Constante.g_const_0;
                    cont = mDescuentoDao.validar_exitencia_flag(d.getNtra(), Constante.g_const_6);
                    if(cont != Constante.g_const_0){
                        descuento = mDescuentoDao.obtener_flag(d.getNtra(), Constante.g_const_6);
                        cantidades = mDescuentoDao.cantidad_preventas_usan_descuento(d.getNtra());
                        if(cantidades >= Integer.parseInt(descuento.getValorInicial().trim()))
                            continue;
                    }

                    //FLAG 7 VECES QUE UN VENDEDOR PUEDE USAR EL DESCUENTO
                    descuento = null;
                    cont = Constante.g_const_0;
                    cont = mDescuentoDao.validar_exitencia_flag(d.getNtra(), Constante.g_const_7);
                    if(cont != Constante.g_const_0){
                        descuento = mDescuentoDao.obtener_flag(d.getNtra(), Constante.g_const_7);
                        ntraUsuario = vendedorDao.obtenerNtraUsuario();
                        cantidades = mDescuentoDao.cantidad_preventas_usan_descuento_vendedor(d.getNtra(), ntraUsuario);
                        if(cantidades >= Integer.parseInt(descuento.getValorInicial().trim()))
                            continue;
                    }

                    //FLAG 8 VECES QUE UN CLIENTE PUEDE USAR LA PROMOCION
                    descuento = null;
                    cont = Constante.g_const_0;
                    cont = mDescuentoDao.validar_exitencia_flag(d.getNtra(), Constante.g_const_8);
                    if(cont != Constante.g_const_0){
                        descuento = mDescuentoDao.obtener_flag(d.getNtra(), Constante.g_const_8);
                        cantidades = mDescuentoDao.cantidad_preventas_usan_descuento_cliente(d.getNtra(), this.codPersona);
                        if(cantidades >= Integer.parseInt(descuento.getValorInicial().trim()))
                            continue;
                    }

                    //FLAG 10 DESCUENTO PARA VENTA AL CONTADO O CREDITO
                    descuento = null;
                    cont = Constante.g_const_0;
                    cont = mDescuentoDao.validar_exitencia_flag(d.getNtra(), Constante.g_const_10);
                    cantidades = Constante.g_const_0;
                    if(cont != Constante.g_const_0){
                        descuento = mDescuentoDao.obtener_flag(d.getNtra(), Constante.g_const_10);
                        cantidades = cabeceraDao.obtenerTipoVenta();
                        if(cantidades != Integer.parseInt(descuento.getValorInicial().trim()))
                            continue;
                    }

                    //FLAG 101 VALIDAR RANGO DE HORA
                    descuento = null;
                    cont = Constante.g_const_0;
                    cont = mDescuentoDao.validar_exitencia_flag(d.getNtra(), Constante.g_const_101);
                    if(cont != Constante.g_const_0){
                        descuento = mDescuentoDao.obtener_flag(d.getNtra(), Constante.g_const_101);
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
                        if(descuento != null){
                            horaCompletaMin = descuento.getValorInicial();
                            horaCompletaMax = descuento.getValorFinal();

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

                    filaDescuento = new FilaDescuento(d.getNtra());
                    listaNtraDescuentos.add(filaDescuento);

                }
                if(listaNtraDescuentos.size() > Constante.g_const_0)
                    filaDescuentoDao.insertLista(listaNtraDescuentos);

            }
        });
    }

    void deleteAllFilaDescuento(){
        AppDataBase.databaseWriteExecutor.execute(()-> {
            filaDescuentoDao.deleteAll();
        });

    }

    public LiveData<List<DescuentoEntity>> descuentosHabilitados(){
        return filaDescuentoDao.descuentosHabilitados();
    }

    public LiveData<List<Integer>> descuentosHabilitadosDos(){
        return filaDescuentoDao.descuentosHabilitadosDos();
    }

    public LiveData<DescuentoEntity> obtener_flag_descuento(int ntra, int flag){
        return mDescuentoDao.obtener_flag_descuento(ntra, flag);
    }

    //DETALLE PREVENTA
    public LiveData<Double> obtenerDescuentosPreventa(int ntra){
        return mDescuentoDao.obtenerDescuentosPreventa(ntra);
    }

    public LiveData<Double> obtenerDescuentoPreventaItem(int ntra, int item){
        return mDescuentoDao.obtenerDescuentoPreventaItem(ntra, item);
    }
}
