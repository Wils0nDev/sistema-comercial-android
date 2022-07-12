package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.CabeceraDao;
import com.idesolution.distribuidoravdc.BD.Dao.DescuentoDao;
import com.idesolution.distribuidoravdc.BD.Dao.DetalleDao;
import com.idesolution.distribuidoravdc.BD.Dao.DetallePromocionDao;
import com.idesolution.distribuidoravdc.BD.Dao.FilaDescuentoDao;
import com.idesolution.distribuidoravdc.BD.Dao.FilaPromocionDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaDescuentoDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaDescuentoTempDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaDetalleDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaPromocionDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaPromocionTempDao;
import com.idesolution.distribuidoravdc.BD.Dao.ProductoDao;
import com.idesolution.distribuidoravdc.BD.Dao.PromocionDao;
import com.idesolution.distribuidoravdc.BD.Dao.SessionManager;
import com.idesolution.distribuidoravdc.BD.Entity.Cabecera;
import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.DescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Detalle;
import com.idesolution.distribuidoravdc.BD.Entity.DetallePromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCarrito;
import com.idesolution.distribuidoravdc.BD.Entity.FilaDireccion;
import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDescuento;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDetalleEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaPromocion;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaPromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ProductoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionEntity;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.Entidad.LoginLocal;

import java.util.ArrayList;
import java.util.List;

public class CabeceraRepository {
    private CabeceraDao cabeceraDao;
    private DetalleDao detalleDao;
    private FilaDescuentoDao filaDescuentoDao;
    private FilaPromocionDao filaPromocionDao;
    private LiveData<List<Cabecera>> AllCabeceras;
    private List<DetallePromocionEntity> listaDetallePromocion;
    private DetallePromocionEntity detallePromocion;
    private DetallePromocionDao detallePromocionDao;
    private DescuentoDao descuentoDao;
    private ProductoEntity producto;
    private ProductoDao productoDao;
    private Detalle d;
    private PreventaPromocion p;
    private PreventaDescuento ds;
    private List<Detalle> listaDetalles;
    private short itemPromo;
    private PreventaPromocionTempDao preventaPromocionTempDao;
    private PreventaDescuentoTempDao preventaDescuentoTempDao;
    private int cont;
    private int cont2;
    private int cont3;
    private DescuentoEntity descuentoEntity;
    private double importe;

    //PRORRATEO
    private List<Detalle> listaDetallesDos;
    private double porcentaje;
    private double importeDetalles;
    private double importeDescuentos;
    private int contador;
    private double importeDesc;
    private double importeItem;
    private double importeFinal;

    //EDITAR PREVENTA
    private Preventa preventa;
    private List<PreventaDetalleEntity> listPreventaDetalle;
    private List<PreventaPromocionEntity> listPreventaPromocionEntities;
    private List<PreventaDescuentoEntity> listPreventaDescuentoEntities;
    private PreventaDao preventaDao;
    private PreventaDetalleDao preventaDetalleDao;
    private PreventaPromocionDao preventaPromocionDao;
    private PreventaDescuentoDao preventaDescuentoDao;
    private Cabecera cabecera;
    private List<Detalle> listaDetalle;
    private Detalle detalle;
    private List<PreventaPromocion> listPreventaPromocion;
    private PreventaPromocion preventaPromocion;
    private List<PreventaDescuento> listPreventaDescuento;
    private PreventaDescuento preventaDescuento;

    SessionManager sessionManager;
    LoginLocal loginLocal = new LoginLocal();

    CabeceraRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        cabeceraDao = db.cabeceraDao();
        detalleDao = db.detalleDao();
        detallePromocionDao = db.detallePromocionDao();
        productoDao = db.ProductoDao();
        descuentoDao = db.descuentoDao();
        filaDescuentoDao = db.filaDescuentoDao();
        filaPromocionDao = db.filaPromocionDao();

        preventaDao = db.preventaDao();
        preventaDetalleDao = db.preventaDetalleDao();
        preventaPromocionDao = db.preventaPromocionDao();
        preventaDescuentoDao = db.preventaDescuentoDao();

        preventaPromocionTempDao = db.preventaPromocionTempDao();
        preventaDescuentoTempDao = db.preventaDescuentoTempDao();
        AllCabeceras = cabeceraDao.getCabeceras();

        sessionManager = new SessionManager(application);
    }

    LiveData<List<Cabecera>> getAllCabeceras() {
        return AllCabeceras;
    }
    LiveData<List<Detalle>> getAllDetalles() {
        return detalleDao.getAllDetalles();
    }

    void insert(Cabecera cabecera) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            cabeceraDao.deleteAll();
            detalleDao.deleteAll();
            preventaDescuentoTempDao.deleteAll();
            preventaPromocionTempDao.deleteAll();
            filaDescuentoDao.deleteAll();
            filaPromocionDao.deleteAll();
            loginLocal = sessionManager.getUserDetail();
            cabecera.setCodSucursal(loginLocal.getCodSucursal());
            cabeceraDao.insert(cabecera);
        });
    }

    void insertDetalle(Detalle detalle, int ntraPromo, int ntraDesc) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            listaDetalles = new ArrayList<Detalle>();
            itemPromo = Constante.g_const_0;

            //APLICA DESCUENTO
            if(ntraDesc != Constante.g_const_0){
                importe = Constante.g_const_0;
                descuentoEntity = descuentoDao.obtener_flag(ntraDesc, Constante.g_const_3);
                if(Integer.parseInt(descuentoEntity.getValorFinal().trim()) == Constante.g_s_const_1){ //IMPORTE
                    importe = Double.parseDouble(descuentoEntity.getValorInicial().trim());
                }else{ //PORCENTAJE
                    importe = (detalle.getCantidadUnidadBase() * detalle.getPrecioVenta() * (Double.parseDouble(descuentoEntity.getValorInicial().trim()) / 100));
                }
                if(importe < Constante.g_s_const_0)
                    importe = Constante.g_s_const_0;
                //detalle.setPrecioVenta(importe);

                ds = new PreventaDescuento(
                        detalle.getNtraPreventa(),
                        ntraDesc,
                        detalle.getItemPreventa(),
                        importe,
                        Constante.g_const_1
                );
                preventaDescuentoTempDao.insertPreventaDescuento(ds);
                detalle.setTipoProducto(Constante.g_s_const_2);
            }
            listaDetalles.add(detalle);

            //APLICA PROMOCION
            if(ntraPromo != Constante.g_const_0){
                listaDetallePromocion = new ArrayList<DetallePromocionEntity>();
                listaDetallePromocion = detallePromocionDao.obtener_flag(ntraPromo, Constante.g_const_3);
                itemPromo = detalle.getItemPreventa();
                for(DetallePromocionEntity detallePromocion : listaDetallePromocion){
                    producto = productoDao.obtenerProducto(detallePromocion.getValorCadena1().trim());
                    itemPromo = (short)(itemPromo + Constante.g_s_const_1);
                    d = new Detalle (
                            detalle.getNtraPreventa(),
                            itemPromo,
                            producto.getCodUnidadBaseVenta(),
                            detallePromocion.getValorCadena1().trim(),
                            detallePromocion.getValorEntero2(),
                            detallePromocion.getValorEntero1(),
                            detallePromocion.getValorEntero1(),
                            detallePromocion.getValorMoneda1(),
                            Constante.g_s_const_3
                    );

                    p = new PreventaPromocion(
                            detalle.getNtraPreventa(),
                            ntraPromo,
                            detalle.getItemPreventa(),
                            itemPromo
                    );

                    listaDetalles.add(d);
                    preventaPromocionTempDao.insertPreventaPromocion(p);
                    d = null;
                    p = null;
                }


                /*detallePromocion = detallePromocionDao.obtener_flag(ntraPromo, Constante.g_const_3);
                producto = productoDao.obtenerProducto(detallePromocion.getValorCadena1().trim());
                itemPromo = (short) (detalle.getItemPreventa() + Constante.g_s_const_1);
                d = new Detalle (
                        detalle.getNtraPreventa(),
                        itemPromo,
                        producto.getCodUnidadBaseVenta(),
                        detallePromocion.getValorCadena1().trim(),
                        detallePromocion.getValorEntero2(),
                        detallePromocion.getValorEntero1(),
                        detallePromocion.getValorEntero1(),
                        detallePromocion.getValorMoneda1(),
                        Constante.g_s_const_3
                );
                listaDetalles.add(d);

                p = new PreventaPromocion(
                        detalle.getNtraPreventa(),
                        ntraPromo,
                        detalle.getItemPreventa(),
                        itemPromo
                );

                preventaPromocionTempDao.insertPreventaPromocion(p);*/
            }
            detalleDao.insertList(listaDetalles);
            //detalleDao.insert(detalle);
        });
    }

    void deletePreventaInconclusa() {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            cabeceraDao.deleteAll();
            detalleDao.deleteAll();
            preventaDescuentoTempDao.deleteAll();
            preventaPromocionTempDao.deleteAll();
            filaDescuentoDao.deleteAll();
            filaPromocionDao.deleteAll();
        });
    }

    void quitarProductoCarrito(Integer item) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            cont = Constante.g_const_0;
            cont2 = Constante.g_const_0;
            cont3 = Constante.g_const_0;
            //item es producto que genera promocion
            cont = preventaPromocionTempDao.buscarItemPromocion(item);
            //item es producto promocionado (regalo)
            cont2 = preventaPromocionTempDao.buscarItemPromocionado(item);

            if (cont == Constante.g_const_0){ //ITEM NO TIENE PROMOCION
                if(cont2 == Constante.g_const_0){ //item NO es promocion (regalo)
                    detalleDao.quitarProductoCarrito(item);
                    detalleDao.ordenarItems(item);
                    preventaPromocionTempDao.ordenarItemsPreventaPromocion(item);

                    cont3 = preventaDescuentoTempDao.countDescuentoXItem(item);
                    if(cont3 > Constante.g_s_const_0){ //ITEM TIENE DESCUENTO
                        preventaDescuentoTempDao.quitarPreventaDescuentoPorItem(item);
                    }
                    preventaDescuentoTempDao.ordenarItemsPreventaDescu(item, Constante.g_const_1);
                }else{ // item ES promocion
                    detalleDao.quitarProductoCarrito(item);
                    detalleDao.ordenarItems(item);
                    preventaPromocionTempDao.quitarPreventaPromocionCarrito(item);
                    preventaPromocionTempDao.ordenarItemsPreventaPromocion(item);

                    cont3 = preventaDescuentoTempDao.countDescuentoXItem(item);
                    if(cont3 > Constante.g_s_const_0){ //ITEM TIENE DESCUENTO
                        preventaDescuentoTempDao.quitarPreventaDescuentoPorItem(item);
                    }
                    preventaDescuentoTempDao.ordenarItemsPreventaDescu(item, Constante.g_const_1);
                }
            }else{ //ITEM TIENE PROMOCION
                //elimino item
                detalleDao.quitarProductoCarrito(item);
                //sumo 1 para tener en cuenta el primer item eliminado
                cont = cont + Constante.g_s_const_1;
                //elimino productos regalo
                detalleDao.quitarProductoConPromocionCarrito(item);
                //ordeno los items segun cantidad de registros eliminados
                detalleDao.ordenarItemsPromocion(item, cont);

                //elimino detalle preventa descuento de todos los regalos
                preventaDescuentoTempDao.quitarPreventaDescuentoPorItem(item);
                preventaDescuentoTempDao.quitarProductoConDescuentoCarrito(item);
                //ordeno los detalle preventa descuento segun los registros eliminados
                preventaDescuentoTempDao.ordenarItemsPreventaDescu(item, cont);

                //elimino detalle preventa promocion de todos los regalos
                preventaPromocionTempDao.quitarPreventaPromocionPorItem(item);
                //ordeno los detalles preventa promocion segun los registros eliminados
                preventaPromocionTempDao.ordenarItemsPreventaPromo(item, cont);

                /*detalleDao.quitarProductoCarrito(item);
                detalleDao.ordenarItems(item);*/

            }

            //item es producto que genera descuento
            /*cont3 = Constante.g_const_0;
            cont3 = preventaDescuentoTempDao.countDescuentoXItem(item);
            if(cont3 > Constante.g_s_const_0){
                preventaDescuentoTempDao.quitarPreventaDescuentoPorItem(item);
                preventaDescuentoTempDao.ordenarItemsPreventaDescu(item, Constante.g_const_1);
            }*/

            /*detalleDao.quitarProductoCarrito(item);
            detalleDao.ordenarItems(item);*/


            /*if(cont > Constante.g_s_const_0){
                preventaPromocionTempDao.quitarPreventaPromocionCarrito(item);
                preventaPromocionTempDao.ordenarItemsPreventaPromocion(item);

                detalleDao.quitarProductoCarrito(item);
                detalleDao.ordenarItems(item);
                preventaPromocionTempDao.ordenarItemsPreventaPromocion(item);
            }*/

        });
    }

    LiveData<Integer> obtenerNtraPreventa(){
        return cabeceraDao.obtenerNtraCabecera();
    }

    LiveData<Short> identificarItem(){
        return detalleDao.identificarItem();
    }

    LiveData<List<FilaCarrito>> obtenerFilaCarrito(){
        return detalleDao.obtenerFilaCarrito();
    }

    LiveData<Cliente> obtenerNombreCliente(){
        return cabeceraDao.obtenerNombreClientePreventa();
    }

    LiveData<List<FilaDireccion>> obtenerDirecciones(){
        return cabeceraDao.obtenerDireccionesPreventa();
    }

    LiveData<Cabecera> obtenerDatoVenta(){
        return cabeceraDao.obtenerDatoVenta();
    }

    LiveData<Integer> buscarItemPromocionDos(int item){
        return preventaPromocionTempDao.buscarItemPromocionDos(item);
    }

    void salvarCabecera(short tipoVenta, short tipodv, short flagRecargo, double recargo, double igv, double total, String fechaEntrega, String horaEntrega) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            cabeceraDao.salvarCabecera(tipoVenta, tipodv, flagRecargo, recargo, igv, total, fechaEntrega, horaEntrega);
        });
    }

    LiveData<Double> importeDescuentosItem(){
        return preventaDescuentoTempDao.importeDescuentosItem();
    }

    void prorrateoDescuentoTotal(int ntraDesc, double impo){
        importeDetalles = Constante.g_const_0;
        importeDescuentos = Constante.g_const_0;
        AppDataBase.databaseWriteExecutor.execute(() -> {
            preventaDescuentoTempDao.deleteAllPreventaDescuento();
            if(impo > Constante.g_s_const_0){
                listaDetallesDos = new ArrayList<Detalle>();
                listaDetallesDos = cabeceraDao.obtenerDetalles();

                contador = Constante.g_const_0;
                contador = preventaDescuentoTempDao.countDescuentos();
                importeDetalles = cabeceraDao.obtenerImporteTotalDetalles();
                if (contador > Constante.g_s_const_0){
                    importeDescuentos = preventaDescuentoTempDao.importeDescuentosDetalles();
                    importeDetalles = importeDetalles - importeDescuentos;
                }

                //contador = Constante.g_const_0;
                for(Detalle d : listaDetallesDos){
                    importeDesc = Constante.g_const_0;
                    importeItem = Constante.g_const_0;
                    importeFinal = Constante.g_const_0;
                    porcentaje = Constante.g_const_0;
                    contador = Constante.g_const_0;

                    contador = preventaDescuentoTempDao.countDescuentoXItem(d.getItemPreventa());
                    importeItem = d.getPrecioVenta() * d.getCantidadUnidadBase();
                    if (contador > Constante.g_s_const_0){
                        importeDesc = preventaDescuentoTempDao.descuentoXItem(d.getItemPreventa());
                        importeItem = importeItem - importeDesc;
                    }

                    porcentaje = MetodosGlobales.formatearDecimales((importeItem / importeDetalles * 100), Constante.g_const_3);
                    importeFinal = MetodosGlobales.formatearDecimales((porcentaje / 100 * impo), Constante.g_const_2);

                    ds = new PreventaDescuento(
                            d.getNtraPreventa(),
                            ntraDesc,
                            d.getItemPreventa(),
                            importeFinal,
                            Constante.g_const_2
                    );
                    preventaDescuentoTempDao.insertPreventaDescuento(ds);
                }
            }

        });

    }

    LiveData<Detalle> obtenerDetalle(String codProducto){
        return detalleDao.obtenerDetalle(codProducto);
    }

    //EDITAR PREVENTA:
    void cargarPreventa(int ntra){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            cabeceraDao.deleteAll();
            detalleDao.deleteAll();
            preventaDescuentoTempDao.deleteAll();
            preventaPromocionTempDao.deleteAll();
            filaDescuentoDao.deleteAll();
            filaPromocionDao.deleteAll();

            //obtener preventa
            preventa = preventaDao.obtenerPreventaPorNtra(ntra);
            //obtener detalles
            listPreventaDetalle = preventaDetalleDao.obtenerDetallePreventaNtra(preventa.getNtraPreventa());

            cabecera = new Cabecera(
                    preventa.getCodCliente(),
                    preventa.getTipoDocumento(),
                    preventa.getNumeroDocumento(),
                    preventa.getCodUsuario(),
                    preventa.getCodPuntoEntrega(),
                    preventa.getMoneda(),
                    preventa.getTipoVenta(),
                    preventa.getTipoDocumentoVenta(),
                    preventa.getFecha(),
                    preventa.getFlagRecargo(),
                    preventa.getRecargo(),
                    preventa.getIgv(),
                    preventa.getIsc(),
                    preventa.getTotal(),
                    preventa.getEstado(),
                    preventa.getOrigenVenta(),
                    preventa.getFechaEntrega(),
                    preventa.getHoraEntrega(),
                    preventa.getCodSucursal()
                    );

            cabeceraDao.insert(cabecera);

            listaDetalle = new ArrayList<Detalle>();
            for(PreventaDetalleEntity pd : listPreventaDetalle){
                detalle = new Detalle(
                        pd.getCodPreventa(),
                        pd.getItemPreventa(),
                        pd.getCodPresentacion(),
                        pd.getCodProducto(),
                        pd.getCodAlmacen(),
                        pd.getCantidadPresentacion(),
                        pd.getCantidadUnidadBase(),
                        pd.getPrecioVenta(),
                        pd.getTipoProducto()
                );
                listaDetalle.add(detalle);
            }
            detalleDao.insertList(listaDetalle);

            //obtener preventa promociones
            cont = Constante.g_const_0;
            cont = preventaPromocionDao.verificarPromocionesNtra(preventa.getNtraPreventa());
            if(cont > Constante.g_const_0){
                listPreventaPromocionEntities = preventaPromocionDao.obtenterPromocionesNtra(preventa.getNtraPreventa());
                listPreventaPromocion = new ArrayList<PreventaPromocion>();
                for(PreventaPromocionEntity pp : listPreventaPromocionEntities){
                    preventaPromocion = new PreventaPromocion(
                            pp.getCodPreventa(),
                            pp.getCodPromocion(),
                            pp.getItemPreventa(),
                            pp.getItemPromocionado()
                    );
                    listPreventaPromocion.add(preventaPromocion);
                }
                preventaPromocionTempDao.insertPreventaPromocionList(listPreventaPromocion);
            }

            //obtener preventa descuentos
            cont = Constante.g_const_0;
            cont = preventaDescuentoDao.verificarDescuentosNtra(preventa.getNtraPreventa());
            if(cont > Constante.g_const_0){
                listPreventaDescuentoEntities = preventaDescuentoDao.obtenerDescuentosNtra(preventa.getNtraPreventa());
                listPreventaDescuento = new ArrayList<PreventaDescuento>();
                for(PreventaDescuentoEntity pd : listPreventaDescuentoEntities){
                    cont = Constante.g_const_0;
                    cont = descuentoDao.obtenerTipoDescuento(pd.getCodDescuento());
                    preventaDescuento = new PreventaDescuento(
                            pd.getCodPreventa(),
                            pd.getCodDescuento(),
                            pd.getItemPreventa(),
                            pd.getImporte(),
                            cont
                    );
                    listPreventaDescuento.add(preventaDescuento);
                }
                preventaDescuentoTempDao.insertPreventaDescuentoList(listPreventaDescuento);
            }
        });


    }


    public LiveData<Double> obtenerDescuentoPreventaItem(int item){
        return preventaDescuentoTempDao.obtenerDescuentoPreventaItem(item);
    }

    public LiveData<Integer> verificarCantidadPuntosEntrega(){
        return cabeceraDao.verificarCantidadPuntosEntrega();
    }

    public LiveData<FilaDireccion> obtenerDireccionesPreventaDos(){
        return cabeceraDao.obtenerDireccionesPreventaDos();
    }

    public LiveData<Integer> cantidadItems(){
        return detalleDao.cantidadItems();
    }

    public void actualizarTipoVenta(int tipoVenta){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            cabeceraDao.actualizarTipoVenta(tipoVenta);
        });
    }

    public void actualizarTipoDocumentoVenta(int tipoDocumentoVenta){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            cabeceraDao.actualizarTipoDocumentoVenta(tipoDocumentoVenta);
        });
    }

}
