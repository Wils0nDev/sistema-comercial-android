package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.CabeceraDao;
import com.idesolution.distribuidoravdc.BD.Dao.ClienteDao;
import com.idesolution.distribuidoravdc.BD.Dao.DetalleDao;
import com.idesolution.distribuidoravdc.BD.Dao.EntregaDao;
import com.idesolution.distribuidoravdc.BD.Dao.LocalizacionDao;
import com.idesolution.distribuidoravdc.BD.Dao.InventarioDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaDescuentoDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaDescuentoTempDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaDetalleDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaPromocionDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaPromocionTempDao;
import com.idesolution.distribuidoravdc.BD.Dao.SessionManager;
import com.idesolution.distribuidoravdc.BD.Dao.VendedorDao;
import com.idesolution.distribuidoravdc.BD.Entity.Cabecera;
import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.Detalle;
import com.idesolution.distribuidoravdc.BD.Entity.EntregaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaDetallePreventa;
import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDescuento;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDetalleEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaPromocion;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaPromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaTotalEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionDescEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionEntity;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.Entidad.LoginLocal;
import com.idesolution.distribuidoravdc.IO.ApiAdapter;
import com.idesolution.distribuidoravdc.IO.Request.RequestAnularPreventa;
import com.idesolution.distribuidoravdc.IO.Request.RequestDetallePreventa;
import com.idesolution.distribuidoravdc.IO.Request.RequestEntrega;
import com.idesolution.distribuidoravdc.IO.Request.RequestPreventa;
import com.idesolution.distribuidoravdc.IO.Request.RequestPreventaDescuento;
import com.idesolution.distribuidoravdc.IO.Request.RequestPreventaPromocion;
import com.idesolution.distribuidoravdc.IO.Request.RequestSesion;
import com.idesolution.distribuidoravdc.IO.Request.ResquestCliente;
import com.idesolution.distribuidoravdc.IO.Response.ResponseAnularPreventa;
import com.idesolution.distribuidoravdc.IO.Response.ResponseRegistroPreventa;
import com.idesolution.distribuidoravdc.UI.principal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import io.reactivex.CompletableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreventaRepository {
    private PreventaDao preventaDao;
    private PreventaDetalleDao detallePreventaDao;
    private PreventaPromocionTempDao preventaPromocionTempDao;
    private PreventaDescuentoTempDao preventaDescuentoTempDao;
    private InventarioDao inventarioDao;
    private LiveData<List<Preventa>> AllPreventas;
    private  LiveData<List<PreventaTotalEntity>> preventasTotal;

    //private PreventaDetalleDao preventaDetalleDao;
    private LiveData<List<PreventaDetalleEntity>> AllPreventaDetalles;

    private PreventaPromocionDao preventaPromocionDao;
    private LiveData<List<PreventaPromocionEntity>> AllPreventaPromociones;

    private PreventaDescuentoDao preventaDescuentoDao;
    private LiveData<List<PreventaDescuentoEntity>> AllPreventaDescuentos;

    private DetalleDao detalleDao;
    private EntregaDao entregaDao;
    private Integer ntra;
    private List<Detalle> AllDetalle;
    private List<PreventaPromocion> AllPrePromo;
    private List<PreventaDescuento> AllPreDescu;
    private List<PreventaDetalleEntity> listaDetallePreventa;
    private List<PreventaPromocionEntity> listaPreventaPromocion;
    private List<PreventaDescuentoEntity> listaPreventaDescuento;
    private PreventaDetalleEntity detalle;
    private PreventaPromocionEntity promocion;
    private PreventaDescuentoEntity descuento;
    private RequestPreventa preventaPost;
    private RequestDetallePreventa detallePost;
    private RequestEntrega entregaPost;
    private RequestPreventaPromocion preventaPromocionPost;
    private List<RequestPreventaPromocion> listaPreventaPromocionPost;
    private RequestPreventaDescuento preventaDescuentoPost;
    private List<RequestPreventaDescuento> listaPreventaDescuentoPost;

    private List<RequestDetallePreventa> listaDetallePost;
    public ResquestCliente clientePost;
    private CabeceraDao cabeceraDao;
    private LocalizacionDao localizacionDao;
    private ClienteDao clienteDao;
    private VendedorDao vendedorDao;
    //private Integer flagProceso;
    private Integer ntraUsuario;

    //EDITAR PREVENTA
    private Cabecera cabecera;
    private Preventa preventa;
    private int flagPuntoEntrega;
    private int ntraPuntoEntrega;
    private int codCliente;
    private int ntraPreventa;
    private short flagProceso;
    private RequestAnularPreventa anularPreventa;

    private SesionRepository sesionRepository;
    private SincronizacionRepository sincronizacionRepository;
    SessionManager sessionManager;
    LoginLocal loginLocal = new LoginLocal();


    PreventaRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        preventaDao = db.preventaDao();
        detallePreventaDao = db.preventaDetalleDao();
        vendedorDao = db.vendedorDao();
        AllPreventas = preventaDao.getAllPreventas();
        preventasTotal = preventaDao.totalPreventasSincro();

        //preventaDetalleDao = db.preventaDetalleDao();
        AllPreventaDetalles = detallePreventaDao.getAllPreventaDetalle();

        preventaPromocionDao = db.preventaPromocionDao();
        AllPreventaPromociones = preventaPromocionDao.getAllPreventaPromocion();

        preventaDescuentoDao = db.preventaDescuentoDao();
        AllPreventaDescuentos = preventaDescuentoDao.getAllPreventaDescuentos();

        detalleDao = db.detalleDao();
        cabeceraDao = db.cabeceraDao();
        entregaDao = db.entregaDao();
        clienteDao = db.clienteDao();
        inventarioDao = db.inventarioDao();
        localizacionDao = db.localizacionDao();
        preventaPromocionTempDao = db.preventaPromocionTempDao();
        preventaDescuentoTempDao = db.preventaDescuentoTempDao();
        flagProceso = Constante.g_const_0;

        sessionManager = new SessionManager(application);
        sesionRepository = new SesionRepository(application);
        sincronizacionRepository = new SincronizacionRepository(application);

    }

    void modificar(int idPreventa, int idPuntoEntrega, Context context){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            //obtener datos de preventa
            preventa = preventaDao.obtenerPreventaPorNtra(idPreventa);
            Cliente cli = cabeceraDao.obtenerClienteCabecera();

            ntraPuntoEntrega = idPuntoEntrega;

            //verificamos el ntraPreventa dependiendo de si es local o no
            ntraPreventa = idPreventa;
            flagProceso = Constante.g_const_0;
            if(preventa.getFlag() != Constante.g_const_0){
                ntraPreventa = preventa.getNtraPreventa();
                flagProceso = Constante.g_const_2;

                //verificamos el ntraPuntoEntrega dependiendo de si es local o no
                flagPuntoEntrega = entregaDao.obtenerFlagPuntoEntrega(idPuntoEntrega);
                if(flagPuntoEntrega != Constante.g_const_0)
                    ntraPuntoEntrega = entregaDao.obtenerNtraPuntoEntrega(idPuntoEntrega);
            }

            //actualizar preventa
            cabecera = cabeceraDao.obtenerCabecera();
            preventaDao.actualizarPreventa(flagProceso, idPreventa, cabecera.getTipoVenta(), cabecera.getTipoDocumentoVenta(),
                    cabecera.getFlagRecargo(), cabecera.getRecargo(), cabecera.getFechaEntrega(), cabecera.getIgv(),
                    cabecera.getTotal(), ntraPuntoEntrega, cabecera.getHoraEntrega());

            //REVERSION DE STOCK
            listaDetallePreventa = new ArrayList<PreventaDetalleEntity>();
            listaDetallePreventa = detallePreventaDao.obtenerDetallePreventaNtra(preventa.getNtraPreventa());
            for(PreventaDetalleEntity p : listaDetallePreventa){
                inventarioDao.revertirStockProducto(p.getCantidadUnidadBase(), p.getCodAlmacen(), p.getCodProducto());
            }
            listaDetallePreventa = null;

            //eliminamos registros anteriores
            preventaDao.eliminarDetallePreventa(preventa.getNtraPreventa());
            preventaDescuentoDao.eliminarPreventaDescuento(preventa.getNtraPreventa());
            preventaPromocionDao.eliminarPreventaPromocion(preventa.getNtraPreventa());

            //replicamos logica
            AllDetalle = detalleDao.AllDetalles();
            listaDetallePreventa = new ArrayList<PreventaDetalleEntity>();
            listaDetallePost = new ArrayList<RequestDetallePreventa>();
            for(Detalle d : AllDetalle){
                detalle = new PreventaDetalleEntity(
                        ntraPreventa,
                        d.getItemPreventa(),
                        d.getCodPresentacion(),
                        d.getCodProducto(),
                        d.getCodAlmacen(),
                        d.getCantidadPresentacion(),
                        d.getCantidadUnidadBase(),
                        d.getPrecioVenta(),
                        d.getTipoProducto(),
                        flagProceso
                );
                listaDetallePreventa.add(detalle);
                inventarioDao.actualizarStockProducto(d.getCantidadUnidadBase(), d.getCodAlmacen(), d.getCodProducto());
                detallePost = new RequestDetallePreventa(
                        ntraPreventa,
                        d.getItemPreventa(),
                        d.getCodPresentacion(),
                        d.getCodProducto(),
                        d.getCodAlmacen(),
                        d.getCantidadPresentacion(),
                        d.getCantidadUnidadBase(),
                        d.getPrecioVenta(),
                        d.getTipoProducto()
                );
                listaDetallePost.add(detallePost);
                detalle = null;
                detallePost = null;
            }
            detallePreventaDao.insertList(listaDetallePreventa);

            //PROMOCIONES-DESCUENTOS
            AllPrePromo = new ArrayList<PreventaPromocion>();
            AllPreDescu = new ArrayList<PreventaDescuento>();
            listaPreventaPromocion = new ArrayList<PreventaPromocionEntity>();
            listaPreventaDescuento = new ArrayList<PreventaDescuentoEntity>();

            listaPreventaPromocionPost = new ArrayList<RequestPreventaPromocion>();
            listaPreventaDescuentoPost = new ArrayList<RequestPreventaDescuento>();

            AllPrePromo = preventaPromocionTempDao.AllPreventaPromocion();
            if(AllPrePromo != null){
                if(AllPrePromo.size() > Constante.g_s_const_0){
                    for(PreventaPromocion pp : AllPrePromo){
                        promocion = new PreventaPromocionEntity(
                                ntraPreventa,
                                pp.getCodPromocion(),
                                pp.getItemPreventa(),
                                pp.getItemPromocionado(),
                                flagProceso
                        );
                        preventaPromocionPost = new RequestPreventaPromocion(
                                ntraPreventa,
                                pp.getCodPromocion(),
                                pp.getItemPreventa(),
                                pp.getItemPromocionado()
                        );
                        listaPreventaPromocion.add(promocion);
                        listaPreventaPromocionPost.add(preventaPromocionPost);
                        promocion = null;
                        preventaPromocionPost = null;
                    }
                    if (listaPreventaPromocion.size() > Constante.g_s_const_0)
                        preventaPromocionDao.insertList(listaPreventaPromocion);
                }
            }

            AllPreDescu = preventaDescuentoTempDao.AllPreventaDescuento();
            if(AllPreDescu != null){
                if(AllPreDescu.size() > Constante.g_s_const_0){
                    for(PreventaDescuento pd : AllPreDescu){
                        descuento = new PreventaDescuentoEntity(
                                ntraPreventa,
                                pd.getCodDescuento(),
                                pd.getItemPreventa(),
                                pd.getImporte(),
                                flagProceso
                        );
                        preventaDescuentoPost = new RequestPreventaDescuento(
                                ntraPreventa,
                                pd.getCodDescuento(),
                                pd.getItemPreventa(),
                                pd.getImporte()
                        );
                        listaPreventaDescuento.add(descuento);
                        listaPreventaDescuentoPost.add(preventaDescuentoPost);
                        descuento = null;
                        preventaDescuentoPost = null;
                    }
                    if(listaPreventaDescuento.size() > Constante.g_s_const_0)
                        preventaDescuentoDao.insertList(listaPreventaDescuento);
                }
            }

            //preventa actualizada
            preventa = preventaDao.obtenerPreventaPorNtra(idPreventa);
            ntra = idPreventa;
            if(flagProceso == Constante.g_const_0){ //REGISTRO
                registroPreventaPost(context, preventa, Constante.g_s_const_1);
            }else //MODIFICACION
            {
                registroPreventaPost(context, preventa, Constante.g_s_const_2);
            }


        });
    }

    void insert(Preventa preventa, Context context) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            ntraUsuario = vendedorDao.obtenerNtraUsuario();
            loginLocal = sessionManager.getUserDetail();
            preventa.setCodUsuario(ntraUsuario);
            preventa.setCodSucursal(loginLocal.getCodSucursal());
            preventaDao.insert(preventa);

            ntra = preventaDao.ntraPreventa();
            preventaDao.actualizarNtraPreventaLocal(ntra);
            AllDetalle = detalleDao.AllDetalles();
            listaDetallePreventa = new ArrayList<PreventaDetalleEntity>();
            listaDetallePost = new ArrayList<RequestDetallePreventa>();
            for(Detalle d : AllDetalle){
                detalle = new PreventaDetalleEntity(
                        ntra,
                        d.getItemPreventa(),
                        d.getCodPresentacion(),
                        d.getCodProducto(),
                        d.getCodAlmacen(),
                        d.getCantidadPresentacion(),
                        d.getCantidadUnidadBase(),
                        d.getPrecioVenta(),
                        d.getTipoProducto(),
                        Constante.g_s_const_0
                );
                listaDetallePreventa.add(detalle);
                inventarioDao.actualizarStockProducto(d.cantidadUnidadBase, d.getCodAlmacen(), d.getCodProducto());
                detallePost = new RequestDetallePreventa(
                        Constante.g_const_0,
                        d.getItemPreventa(),
                        d.getCodPresentacion(),
                        d.getCodProducto(),
                        d.getCodAlmacen(),
                        d.getCantidadPresentacion(),
                        d.getCantidadUnidadBase(),
                        d.getPrecioVenta(),
                        d.getTipoProducto()
                );
                listaDetallePost.add(detallePost);
                detalle = null;
                detallePost = null;
            }
            detallePreventaDao.insertList(listaDetallePreventa);

            //PROMOCIONES-DESCUENTOS
            AllPrePromo = new ArrayList<PreventaPromocion>();
            AllPreDescu = new ArrayList<PreventaDescuento>();
            listaPreventaPromocion = new ArrayList<PreventaPromocionEntity>();
            listaPreventaDescuento = new ArrayList<PreventaDescuentoEntity>();

            listaPreventaPromocionPost = new ArrayList<RequestPreventaPromocion>();
            listaPreventaDescuentoPost = new ArrayList<RequestPreventaDescuento>();

            AllPrePromo = preventaPromocionTempDao.AllPreventaPromocion();
            if(AllPrePromo != null){
                if(AllPrePromo.size() > Constante.g_s_const_0){
                    for(PreventaPromocion pp : AllPrePromo){
                        promocion = new PreventaPromocionEntity(
                                ntra,
                                pp.getCodPromocion(),
                                pp.getItemPreventa(),
                                pp.getItemPromocionado(),
                                Constante.g_const_0
                        );
                        preventaPromocionPost = new RequestPreventaPromocion(
                                Constante.g_const_0,
                                pp.getCodPromocion(),
                                pp.getItemPreventa(),
                                pp.getItemPromocionado()
                        );
                        listaPreventaPromocion.add(promocion);
                        listaPreventaPromocionPost.add(preventaPromocionPost);
                        promocion = null;
                        preventaPromocionPost = null;
                    }
                    if (listaPreventaPromocion.size() > Constante.g_s_const_0)
                        preventaPromocionDao.insertList(listaPreventaPromocion);
                }
            }

            AllPreDescu = preventaDescuentoTempDao.AllPreventaDescuento();
            if(AllPreDescu != null){
                if(AllPreDescu.size() > Constante.g_s_const_0){
                    for(PreventaDescuento pd : AllPreDescu){
                        descuento = new PreventaDescuentoEntity(
                                ntra,
                                pd.getCodDescuento(),
                                pd.getItemPreventa(),
                                pd.getImporte(),
                                Constante.g_const_0
                        );
                        preventaDescuentoPost = new RequestPreventaDescuento(
                                Constante.g_const_0,
                                pd.getCodDescuento(),
                                pd.getItemPreventa(),
                                pd.getImporte()
                        );
                        listaPreventaDescuento.add(descuento);
                        listaPreventaDescuentoPost.add(preventaDescuentoPost);
                        descuento = null;
                        preventaDescuentoPost = null;
                    }
                    if(listaPreventaDescuento.size() > Constante.g_s_const_0)
                        preventaDescuentoDao.insertList(listaPreventaDescuento);
                }
            }

            registroPreventaPost(context, preventa, Constante.g_s_const_1);
        });
    }

    void insertDetalle(PreventaDetalleEntity detalle) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            detallePreventaDao.insert(detalle);
        });
    }

    void deletePreventaInconclusa() {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            preventaDao.deletePreventaInconclusa();
            detallePreventaDao.deleteDetallePreventaInconclusa();
        });
    }

    LiveData<Integer> obtenerNtraPreventa(){
        return preventaDao.obtenerNtraPreventa();
    }

    LiveData<Short> identificarItem(){
        return detallePreventaDao.identificarItem();
    }

    LiveData<List<Preventa>> getAllPrevetas() {
        return AllPreventas;
    }

    LiveData<List<PreventaTotalEntity>> totalPrevetasSincro() {
        return preventasTotal;
    }

    void insertList(List<Preventa> preventas, List<PreventaDetalleEntity> detalles, List<PreventaPromocionEntity> promocioes, List<PreventaDescuentoEntity> descuentos) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            preventaDao.deleteAll();
            preventaDao.deleteSequence();
            preventaDao.insertList(preventas);

            detallePreventaDao.deleteAll();
            detallePreventaDao.deleteSequence();
            detallePreventaDao.insertList(detalles);

            preventaPromocionDao.deleteAll();
            preventaPromocionDao.deleteSequence();
            preventaPromocionDao.insertList(promocioes);

            preventaDescuentoDao.deleteAll();
            preventaDescuentoDao.deleteSequence();
            preventaDescuentoDao.insertList(descuentos);


        });
    }

    LiveData<List<Preventa>> obtenerPreventas(){
        return preventaDao.obtenerPreventas();
    }
    LiveData<List<PreventaDetalleEntity>> detallePreventa(){
        return detalleDao.detallePreventa();
    }

    LiveData<List<Preventa>> obtenerPreventasPorFecha(String fecha){
        return preventaDao.obtenerPreventasPorFecha(fecha);
    }

    public void registroPreventaPost(Context context, Preventa preventa, short proceso){
        Integer conexion = MetodosGlobales.verificarConexionInternet(context);
        String ip = "";
        String mac = "";
        sessionManager = new SessionManager(context);
        loginLocal = sessionManager.getUserDetail();

        short flag = Constante.g_s_const_0;
        if(conexion == Constante.g_const_1){
            Cliente cli = cabeceraDao.obtenerClienteCabecera();
            Cabecera cab = cabeceraDao.obtenerCabecera();
            EntregaEntity ent = entregaDao.obtenerEntregaPreventa(preventa.getCodPuntoEntrega());
            ntraUsuario = vendedorDao.obtenerNtraUsuario();
            LocalizacionEntity loc;
            ip = MetodosGlobales.obtenerIP(context);
            mac = MetodosGlobales.obtenerMAC(context);

            if (cli.getNumeroDocumento().length() > 0){
                loc = localizacionDao.obtenerLocalizacion(cli.getNumeroDocumento());
            }else{
                loc = localizacionDao.obtenerLocalizacion(cli.getRuc());
            }

            if(cli.getFlag() == Constante.g_s_const_0){
                flag = Constante.g_const_1;
            }
            //clientePreventa
            clientePost = new ResquestCliente(
                    flag,
                    cli.getCodCliente(),
                    cli.getTipoPersona(),
                    cli.getTipoDocumento(),
                    cli.getNumeroDocumento(),
                    cli.getRuc(),
                    cli.getRazonSocial(),
                    cli.getNombres(),
                    cli.getApellidoPaterno(),
                    cli.getApellidoMaterno(),
                    cli.getDireccion(),
                    cli.getCorreo(),
                    cli.getTelefono(),
                    cli.getCelular(),
                    null,
                    null,
                    null,
                    null,
                    cli.getFrecuencia(),
                    cli.getTipoListaPrecio(),
                    cli.getCodRuta(),
                    loc.getCoordenadaX(),
                    loc.getCoordenadaY(),
                    //ntraUsuario.toString(),
                    loginLocal.getNtraUsuario()+"",
                    ip,
                    mac
            );

            /*flag = Constante.g_s_const_0;
            if(ent.getFlag() == Constante.g_s_const_0){
                flag = Constante.g_const_1;
            }

            entregaPost = new RequestEntrega(
                    flag,
                    ent.getId(),
                    ent.getNtraPuntoEntrega(),
                    ent.getCoordenadaX(),
                    ent.getCoordenadaY(),
                    null,
                    ent.getDireccion(),
                    ent.getReferencia(),
                    ent.getOrdenEntrega(),
                    ent.getCodPersona(),
                    ntraUsuario.toString(),
                    "ip",
                    "mac",
                    ent.getTipoDocumento(),
                    ent.getNumeroDocumento()
            );*/

            if(proceso == Constante.g_s_const_1) {
                preventaPost = new RequestPreventa(
                        proceso,
                        clientePost,
                        //entregaPost,
                        Constante.g_const_0,
                        cab.getCodCliente(),
                        ntraUsuario,
                        ent.getNtraPuntoEntrega(),
                        cab.getMoneda(),
                        cab.getTipoVenta(),
                        cab.getTipoDocumentoVenta(),
                        MetodosGlobales.obtenerFechaActual(), //Date.valueOf(cab.getFecha()),
                        cab.getFechaEntrega(),
                        cab.getHoraEntrega(),
                        loginLocal.getCodSucursal(),
                        null,
                        cab.getFlagRecargo(),
                        cab.getRecargo(),
                        cab.getIgv(),
                        cab.getIsc(),
                        cab.getTotal(),
                        cab.getEstado(),
                        //ntraUsuario.toString(),
                        loginLocal.getNtraUsuario()+"",
                        ip,
                        mac,
                        cab.getOrigenVenta(),
                        listaDetallePost,
                        listaPreventaPromocionPost,
                        listaPreventaDescuentoPost
                );
            }else{
                preventaPost = new RequestPreventa(
                        proceso,
                        clientePost,
                        preventa.getNtraPreventa(),
                        preventa.getCodCliente(),
                        ntraUsuario,
                        preventa.getCodPuntoEntrega(),
                        preventa.getMoneda(),
                        preventa.getTipoVenta(),
                        preventa.getTipoDocumentoVenta(),
                        preventa.getFecha(),
                        preventa.getFechaEntrega(),
                        preventa.getHoraEntrega(),
                        loginLocal.getCodSucursal(),
                        null,
                        preventa.getFlagRecargo(),
                        preventa.getRecargo(),
                        preventa.getIgv(),
                        preventa.getIsc(),
                        preventa.getTotal(),
                        preventa.getEstado(),
                        //ntraUsuario.toString(),
                        loginLocal.getNtraUsuario()+"",
                        ip,
                        mac,
                        preventa.getOrigenVenta(),
                        listaDetallePost,
                        listaPreventaPromocionPost,
                        listaPreventaDescuentoPost
                );
            }

            Call<ResponseRegistroPreventa> call = ApiAdapter.getApiService().registrarPreventa(preventaPost);
            call.enqueue(new Callback<ResponseRegistroPreventa>() {
                @Override
                public void onResponse(Call<ResponseRegistroPreventa> call, Response<ResponseRegistroPreventa> response) {
                    if(response.isSuccessful()){
                        ResponseRegistroPreventa c = response.body();
                        if(c.getErrorWebSer().getCodigoErr() == 2000) {
                            AppDataBase.databaseWriteExecutor.execute(() -> {
                                //actualizamos datos de preventa local
                                preventaDao.actualizarNtraPreventa(response.body().getResultado().getRp().getNtraPreventa(), ntra);

                                //actualizar datos de detalle preventa
                                preventaDao.actualizarNtraDetallePreventa(response.body().getResultado().getRp().getNtraPreventa(), ntra);

                                //actualizar datos de preventa promocion
                                preventaDao.actualizarNtraPreventaPromocion(response.body().getResultado().getRp().getNtraPreventa(), ntra);

                                //actualizar datos de preventa descuento
                                preventaDao.actualizarNtraPreventaDescuento(response.body().getResultado().getRp().getNtraPreventa(), ntra);

                                if(proceso == Constante.g_s_const_1){
                                    //actualizamos datos de cliente si es que es nuevo
                                    if(cli.getFlag() == Constante.g_s_const_0){
                                        clienteDao.actualizarDatosPreventa(response.body().getResultado().getRp().getCodPersona(), cli.getNtra());
                                    }

                                    //actualizamos datos de puntos de entrega de cliente
                                    if(ent.flag == Constante.g_s_const_0){
                                        entregaDao.actualizarDireccionPreventa(response.body().getResultado().getRp().getNtraPuntoEntrega(), response.body().getResultado().getRp().getCodPersona(), preventa.getCodPuntoEntrega());
                                    }
                                }

                                //SINCRONIZAR DATA

                                sincronizacionRepository.SincronizarDataCompleta(loginLocal.getNtraUsuario(),
                                        loginLocal.getCodUbigeo(),context,4);





                            });
                        }else if(c.getErrorWebSer().getCodigoErr() == Constante.g_const_1045) {
                            RequestSesion requestSesion = new RequestSesion();
                            requestSesion.setCodUsuario(loginLocal.getNtraUsuario());
                            requestSesion.setTipoLogueo(Constante.g_const_2);
                            requestSesion.setTipoRegistro(Constante.g_const_2);
                            sesionRepository.RegistrarSesionInicio(requestSesion,Constante.g_const_5,context);

                        }
                        else{
                            if(c.getErrorWebSer().getTipoErr() == 0){
                                MetodosGlobales.mostrarMensaje(context, c.getErrorWebSer().getDescripcionErr());
                            }
                            else{
                                if (proceso == Constante.g_const_1)
                                    MetodosGlobales.mostrarMensaje(context, "Error en el registro de la preventa");
                                else
                                    MetodosGlobales.mostrarMensaje(context, "Error en la modificaion de la preventa");
                            }
                        }
                    }
                    else{
                        if (proceso == Constante.g_const_1)
                            MetodosGlobales.mostrarMensaje(context, "Hubo un error en el registro de la preventa");
                        else
                            MetodosGlobales.mostrarMensaje(context, "Hubo un error en la modificacion de la preventa");
                    }
                }

                @Override
                public void onFailure(Call<ResponseRegistroPreventa> call, Throwable t) {
                    MetodosGlobales.mostrarMensaje(context, "Error de conexion");
                }
            });
        }
    }

    void insert(Preventa preventa) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            preventaDao.insert(preventa);
        });
    }

    LiveData<Preventa> obtenerPreventasPorNtra(int ntra){
        return preventaDao.obtenerPreventasPorNtra(ntra);
    }

    //DETALLE PREVENTA
    public LiveData<List<FilaDetallePreventa>> obtenerDetallePreventa(int ntra){
        return detallePreventaDao.obtenerDetallePreventa(ntra);
    }

    public LiveData<Integer> cantidadDeProductosPreventa(int ntra){
        return preventaDao.cantidadDeProductosPreventa(ntra);
    }

    public LiveData<Integer> cantidadDePromocionesPreventa(int ntra){
        return preventaDao.cantidadDePromocionesPreventa(ntra);
    }

    public LiveData<List<PromocionEntity>> obtenerDescripcionPromos(int ntra){
        return preventaDao.obtenerDescripcionPromos(ntra);
    }

    public LiveData<List<PromocionDescEntity>> obtenerDescripcionPromosDesc(int ntra){
        return preventaDao.obtenerDescripcionPromosDesc(ntra);
    }

    public LiveData<Integer> obtenerEstadoPreventaNtra(int ntra){
        return preventaDao.obtenerEstadoPreventaNtra(ntra);
    }

    void anularPreventaNtra(int ntra, Context context){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            preventa = preventaDao.obtenerPreventaPorNtra(ntra);

            if(preventa.getFlag() != Constante.g_const_0 ){
                flagProceso = Constante.g_s_const_3;
            }else{
                flagProceso = Constante.g_s_const_1;
            }

            //REVERSION DE STOCK
            listaDetallePreventa = new ArrayList<PreventaDetalleEntity>();
            listaDetallePreventa = detallePreventaDao.obtenerDetallePreventaNtra(preventa.getNtraPreventa());
            for(PreventaDetalleEntity p : listaDetallePreventa){
                detallePreventaDao.anularDetallePreventa(flagProceso, preventa.getNtraPreventa());
                inventarioDao.revertirStockProducto(p.getCantidadUnidadBase(), p.getCodAlmacen(), p.getCodProducto());
            }
            preventaDao.anularPreventaNtra(ntra);

            if(preventa.getFlag() != Constante.g_const_0){
                anularPreventaPost(context, preventa);
            }
        });
    }

    public void anularPreventaPost(Context context, Preventa preventa){
        Integer conexion = MetodosGlobales.verificarConexionInternet(context);
        sessionManager = new SessionManager(context);
        loginLocal = sessionManager.getUserDetail();
        if(conexion == Constante.g_const_1){
            anularPreventa = new RequestAnularPreventa(
                    preventa.getNtraPreventa(),loginLocal.getNtraUsuario()
            );
            Call<ResponseAnularPreventa> call = ApiAdapter.getApiService().anularPreventa(anularPreventa);
            call.enqueue(new Callback<ResponseAnularPreventa>() {
                @Override
                public void onResponse(Call<ResponseAnularPreventa> call, Response<ResponseAnularPreventa> response) {
                    if(response.isSuccessful()){
                        ResponseAnularPreventa c = response.body();
                        if(c.getErrorWebSer().getCodigoErr() == Constante.g_const_2000){
                            AppDataBase.databaseWriteExecutor.execute(() -> {
                                preventaDao.actualizarPreventaAnulada(preventa.getNtra());
                                //Toasty.success((principal) context, "¡PREVENTA ANULADA!", Toast.LENGTH_SHORT, true).show();

                            });
                            //MetodosGlobales.mostrarMensaje(context, "PREVENTA ANULADA");
                        }else if(c.getErrorWebSer().getCodigoErr() == Constante.g_const_1045) {
                            RequestSesion requestSesion = new RequestSesion();
                            requestSesion.setCodUsuario(loginLocal.getNtraUsuario());
                            requestSesion.setTipoLogueo(Constante.g_const_2);
                            requestSesion.setTipoRegistro(Constante.g_const_2);
                            sesionRepository.RegistrarSesionInicio(requestSesion,Constante.g_const_3,context);

                        }
                        else{
                            if(c.getErrorWebSer().getTipoErr() == 0){
                                MetodosGlobales.mostrarMensaje(context, c.getErrorWebSer().getDescripcionErr());
                            }
                            else{
                                MetodosGlobales.mostrarMensaje(context, "Error en la anulacion de la preventa");
                            }
                        }
                    }
                    else{
                        MetodosGlobales.mostrarMensaje(context, "Hubo un error en la anulacion de la preventa");
                    }
                }

                @Override
                public void onFailure(Call<ResponseAnularPreventa> call, Throwable t) {
                    MetodosGlobales.mostrarMensaje(context, "Error de conexion");
                }
            });

        }
    }
}
