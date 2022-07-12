package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.BitacoraVendedorDao;
import com.idesolution.distribuidoravdc.BD.Dao.CategoriaDao;
import com.idesolution.distribuidoravdc.BD.Dao.ClienteDao;
import com.idesolution.distribuidoravdc.BD.Dao.EntregaDao;
import com.idesolution.distribuidoravdc.BD.Dao.LocalizacionDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaDescuentoDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaDetalleDao;
import com.idesolution.distribuidoravdc.BD.Dao.PreventaPromocionDao;
import com.idesolution.distribuidoravdc.BD.Dao.SessionManager;
import com.idesolution.distribuidoravdc.BD.Entity.AlmacenEntity;
import com.idesolution.distribuidoravdc.BD.Entity.BitacoraVendedorEntity;
import com.idesolution.distribuidoravdc.BD.Entity.CategoriaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.Concepto;
import com.idesolution.distribuidoravdc.BD.Entity.DataSincronizacionLocal;
import com.idesolution.distribuidoravdc.BD.Entity.DepartamentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DetalleDescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DetallePromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DistritoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.EntregaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.InventarioEntity;
import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Parametro;
import com.idesolution.distribuidoravdc.BD.Entity.PrecioEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PresentacionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDetalleEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaPromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaTotalEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ProductoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionDescEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ProvinciaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.VendedorEntity;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.Entidad.LoginLocal;
import com.idesolution.distribuidoravdc.IO.ApiAdapter;
import com.idesolution.distribuidoravdc.IO.Request.RequestClienteSinc;
import com.idesolution.distribuidoravdc.IO.Request.RequestEntrega;
import com.idesolution.distribuidoravdc.IO.Request.RequestSesion;
import com.idesolution.distribuidoravdc.IO.Request.RequestSincronizacion;
import com.idesolution.distribuidoravdc.IO.Request.RequestUbigeo;
import com.idesolution.distribuidoravdc.IO.Request.ResquestRutasBitacora;
import com.idesolution.distribuidoravdc.IO.Response.ListAlmacene;
import com.idesolution.distribuidoravdc.IO.Response.ListCategoria;
import com.idesolution.distribuidoravdc.IO.Response.ListCliente;
import com.idesolution.distribuidoravdc.IO.Response.ListConcepto;
import com.idesolution.distribuidoravdc.IO.Response.ListDepartamento;
import com.idesolution.distribuidoravdc.IO.Response.ListDescuento;
import com.idesolution.distribuidoravdc.IO.Response.ListDetPreventum;
import com.idesolution.distribuidoravdc.IO.Response.ListDetalleDescuento;
import com.idesolution.distribuidoravdc.IO.Response.ListDetallePromocion;
import com.idesolution.distribuidoravdc.IO.Response.ListDistrito;
import com.idesolution.distribuidoravdc.IO.Response.ListEntrega;
import com.idesolution.distribuidoravdc.IO.Response.ListInventario;
import com.idesolution.distribuidoravdc.IO.Response.ListLocalizacion;
import com.idesolution.distribuidoravdc.IO.Response.ListParametro;
import com.idesolution.distribuidoravdc.IO.Response.ListPrecio;
import com.idesolution.distribuidoravdc.IO.Response.ListPresentacione;
import com.idesolution.distribuidoravdc.IO.Response.ListPrevDescuento;
import com.idesolution.distribuidoravdc.IO.Response.ListPrevPromocion;
import com.idesolution.distribuidoravdc.IO.Response.ListPreventa;
import com.idesolution.distribuidoravdc.IO.Response.ListProducto;
import com.idesolution.distribuidoravdc.IO.Response.ListPromocione;
import com.idesolution.distribuidoravdc.IO.Response.ListProvincia;
import com.idesolution.distribuidoravdc.IO.Response.ResponseSesion;
import com.idesolution.distribuidoravdc.IO.Response.ResponseUbigeoSucur;
import com.idesolution.distribuidoravdc.IO.Response.SincronizacionResponse;
import com.idesolution.distribuidoravdc.IO.Response.Vendedor;
import com.idesolution.distribuidoravdc.R;
import com.idesolution.distribuidoravdc.UI.principal;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
//import es.dmoral.toasty.Toasty;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SincronizacionRepository {

    //Cliente
    private ClienteDao clienteDao;
    List<Cliente> listClienteEntity;
    ArrayList<RequestClienteSinc> listResClientes = new ArrayList<>();
    RequestClienteSinc cli;

    //Punto de entrega
    private EntregaDao entregaDao;
    List<EntregaEntity> listEntregaEntity;
    ArrayList<RequestEntrega> listReqEntregas= new ArrayList<>();
    RequestEntrega req_ent;

    //Bitacoras
    private BitacoraVendedorDao bitacoraVendedorDao;
    List<BitacoraVendedorEntity> listBitacoraEntity;
    ArrayList<ResquestRutasBitacora> listReqBitacoras;
    ResquestRutasBitacora rbv;

    //Preventa
    private PreventaDao preventaDao;
    private PreventaDetalleDao detallePreventaDao;
    private PreventaPromocionDao preventaPromocionDao;
    private PreventaDescuentoDao preventaDescuentoDao;
    List<PreventaTotalEntity> totalPreventaEmtity = new ArrayList<>();
    List<ListPreventa> listPreventas = new ArrayList<>();
    ListPreventa ent_preventa ;
    List<ListDetPreventum> listPreventaDetalles = new ArrayList<>();
    List<ListPrevPromocion> listPrevPromocions = new ArrayList<>();
    List<ListPrevDescuento> listPrevDescuentos = new ArrayList<>();
    String ip = "";
    String mac = "";
    //Ubigeo


    private CategoriaDao categoriaDao;

    //CARGAR DATA LOCAL
    private CategoriaRepository categoriaRepository;
    private ClienteRepository clienteRepository;
    private VendedorRepository vendedorRepository;
    private ProductoRepository productoRepository;
    private PresentacionRepository presentacionRepository;
    private PromocionRepository promocionRepository;
    private DescuentoRepository descuentoRepository;
    private AlmacenRepository almacenRepository;
    private InventarioRepository inventarioRepository;
    private EntregaRepository entregaRepository;
    //private PreventaRepository preventaRepository;
    private PrecioRepository precioRepository;
    private ConceptoRepository conceptoRepository;
    private ParametroRepository parametroRepository;
    private BitacoraVendedorRepository bitacoraVendedorRepository;
    private UbigeoRepository ubigeoRepository;
    private LocalizacionRepository localizacionRepository;
    private PromocionDescRepository promocionDescRepository;
    ProgressDialog progressDialog;
    SweetAlertDialog pDialog;
    SessionManager sessionManager;
    LoginLocal loginLocal;
    private SesionRepository sesionRepository;

    //Localizacion
    private LocalizacionDao localizacionDao;
    private LocalizacionEntity localizacionEntity;

    AppDataBase db;
    public SincronizacionRepository(Application application) {
        db = AppDataBase.getDatabase(application);
        categoriaDao = db.CategoriaDao();
        clienteDao = db.clienteDao();
        entregaDao = db.entregaDao();
        preventaDao = db.preventaDao();
        detallePreventaDao = db.preventaDetalleDao();
        preventaPromocionDao = db.preventaPromocionDao();
        preventaDescuentoDao = db.preventaDescuentoDao();
        bitacoraVendedorDao = db.bitacoraVendedorDao();
        localizacionDao = db.localizacionDao();

        categoriaRepository = new CategoriaRepository(application);
        clienteRepository = new ClienteRepository(application);
        vendedorRepository = new VendedorRepository(application);
        productoRepository = new ProductoRepository(application);
        presentacionRepository = new PresentacionRepository(application);
        promocionRepository = new PromocionRepository(application);
        descuentoRepository = new DescuentoRepository(application);
        almacenRepository = new AlmacenRepository(application);
        inventarioRepository = new InventarioRepository(application);
        entregaRepository = new EntregaRepository(application);
        //preventaRepository = new PreventaRepository(application);
        precioRepository = new PrecioRepository(application);
        conceptoRepository = new ConceptoRepository(application);
        parametroRepository = new ParametroRepository(application);
        bitacoraVendedorRepository = new BitacoraVendedorRepository(application);
        ubigeoRepository = new UbigeoRepository(application);
        localizacionRepository = new LocalizacionRepository(application);
        sesionRepository = new SesionRepository(application);
        promocionDescRepository = new PromocionDescRepository(application);
    }
    /*
    int LlenarBaseDatos_2(SincronizacionResponse data){
        List<ListCategoria> listCategorias = null ;
        int dato = data.getErrorWebSer().getCodigoErr();
        listCategorias = data.getResultadoSincro().getDataSincronizacion().getListCategorias();

        List<ListCategoria> finalListCategorias = listCategorias;
        CategoriaEntity catt = new CategoriaEntity(listCategorias.get(0).getNtraCategoria(),listCategorias.get(0).getDescripcion());

        Completable deleteAllCompletable = Completable.fromAction(categoriaDao::deleteAll);
        deleteAllCompletable
                .doOnError(throwable -> System.out.println("throwable: "+throwable.getMessage()))
                .subscribe();
        return  dato;

    }

     */

    private void deleteAllCategorias(List<ListCategoria> finalListCategorias) {

    }

    private void insertRows(List<ListCategoria> listCategorias) {
        CategoriaEntity categoriaEntity;
        for (ListCategoria categoria: listCategorias) {
            categoriaEntity = new CategoriaEntity(categoria.getNtraCategoria(),categoria.getDescripcion());
            categoriaDao.insert(categoriaEntity);
            //categoriaEntity = null;
        }
    }
    public void SincronizarDataCompleta(int codVendedor,String codUbigeo, Context context,int respuesta) {
        DataSincronizacionLocal data;
        ip = MetodosGlobales.obtenerIP(context);
        mac = MetodosGlobales.obtenerMAC(context);
        // si respuesta es 4 - no se mostrara el loading, para evitar problemas en registro de prevetna
        if(respuesta != 4){
            progressDialog = new ProgressDialog(context);
            ShowProgressSweetAlert(context);
        }

        sessionManager = new SessionManager(context);
        loginLocal = sessionManager.getUserDetail();

        //ShowDialog();
        AppDataBase.databaseWriteExecutor.execute(() -> {

            // ShowDialog();
            //Cliente
            listClienteEntity = new ArrayList<>();
            listClienteEntity = clienteDao.getClientesSinRep();
            listResClientes = ObtenerDataCliente(listClienteEntity); //Lista que se retornara para enviar al servicio

            //Punto de entrega
            listEntregaEntity = new ArrayList<>();
            listEntregaEntity = entregaDao.getEntregaSincRepo();
            listReqEntregas = ObtenerDataEntregas(listEntregaEntity); //Lista que se retornara para enviar al servicio

            //Bitacoras
            listBitacoraEntity = new ArrayList<>();
            listBitacoraEntity = bitacoraVendedorDao.getmAllBitacorasVendedorSinRepo();
            listReqBitacoras = ObtenerDataBitacora(listBitacoraEntity);

            //Preventa
            totalPreventaEmtity = new ArrayList<>();
            totalPreventaEmtity = preventaDao.totalPreventasSincroRepo();
            listPreventas = ObtenerDataPreventas(totalPreventaEmtity); //Lista que se retornara para enviar al servicio

            //API CONSULTA
            RequestSincronizacion requestSincronizacion =
                    new RequestSincronizacion(codVendedor,codUbigeo,listResClientes,listPreventas,listReqEntregas,listReqBitacoras);
            Gson gson = new Gson();
            Call<SincronizacionResponse> call = ApiAdapter.getApiService().sincronizacion(requestSincronizacion);
            call.enqueue(new Callback<SincronizacionResponse>() {
                @Override
                public void onResponse(Call<SincronizacionResponse> call, Response<SincronizacionResponse> response) {
                    if(response.isSuccessful()){
                        SincronizacionResponse data_sincronizacion = response.body();
                        if(data_sincronizacion.getErrorWebSer().getCodigoErr() == Constante.g_const_2000) {
                            //CAMBIAMOS DE ESTADO LAS BITACORAS
                            bitacoraVendedorRepository.updateSincroBi();
                            if(data_sincronizacion.getResultadoSincro().getDataSincronizacion().getEstadoUsuario() == Constante.g_const_1){
                                //LLENAR BASE DE DATOS LOCAL
                                int dato = LlenarBaseDatos(data_sincronizacion);

                                if(dato == Constante.g_const_1){
                                    Toasty.success(context, Constante.g_const_msj_sincro, Toast.LENGTH_SHORT, true).show();
                                    //Toasty.success(context, data_sincronizacion.getResultadoSincro().getDataSincronizacion().getDescripcionResp(), Toast.LENGTH_SHORT, true).show();
                                    //Toast.makeText(context, Constante.g_const_msj_sincro, Toast.LENGTH_SHORT).show();
                                    // Cuando la respuesta es 0, no realiza nada, se mantiene en su mismo contexto
                                    // 4 - tampoc hace nada
                                    if(respuesta == Constante.g_const_1){
                                        //Representa que regresara al login
                                        RequestSesion requestSesion = new RequestSesion();
                                        requestSesion.setCodUsuario(codVendedor);
                                        requestSesion.setTipoLogueo(Constante.g_const_2);
                                        requestSesion.setTipoRegistro(Constante.g_const_2);
                                        RegistrarSesionInicio(requestSesion,Constante.g_const_1,context);

                                    }
                                    else if(respuesta == Constante.g_const_2){
                                        progressDialog.dismiss();
                                        //Representa que entra a aplicacion (Inicio de sesion)
                                        //Consulta y registro de ubigeo
                                        RequestUbigeo requestUbigeo = new RequestUbigeo(codUbigeo);
                                        RegistrarUbigeo(requestUbigeo,context);

                                        Intent intent = new Intent(context, principal.class);
                                        context.startActivity(intent);

                                    }
                                    else if(respuesta == Constante.g_const_3){
                                        //Salir de home y entrar al login, cerrar sesion
                                        RequestSesion requestSesion = new RequestSesion();
                                        requestSesion.setCodUsuario(codVendedor);
                                        requestSesion.setTipoLogueo(Constante.g_const_2);
                                        requestSesion.setTipoRegistro(Constante.g_const_2);
                                        RegistrarSesionInicio(requestSesion,Constante.g_const_3,context);


                                    }

                                }
                            }
                            else{
                                //USUARIO NO TIENE ACCESO PARA CONTINUAR EN LA APLICACION
                                progressDialog.dismiss();
                                sessionManager.logout();
                            }


                        }else if(data_sincronizacion.getErrorWebSer().getCodigoErr() == Constante.g_const_1045) {
                            //Toast.makeText(context, data_sincronizacion.getErrorWebSer().getDescripcionErr(), Toast.LENGTH_SHORT).show();
                            //Toasty.error(context, data_sincronizacion.getErrorWebSer().getDescripcionErr(), Toast.LENGTH_SHORT, true).show();

                            RequestSesion requestSesion = new RequestSesion();
                            requestSesion.setCodUsuario(codVendedor);
                            requestSesion.setTipoLogueo(Constante.g_const_2);
                            requestSesion.setTipoRegistro(Constante.g_const_2);
                            sesionRepository.RegistrarSesionInicio(requestSesion,Constante.g_const_3,context);

                        }
                        else{
                            Toast.makeText(context, "Error en la sincronizacion", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(context, "Hubo un error al realizar la sincronizacion", Toast.LENGTH_SHORT).show();
                    }
                    if(respuesta != 4){
                        FinishProgressSweetAlert();
                    }

                    //progressDialog.dismiss();
                    //finishDialog();
                }

                @Override
                public void onFailure(Call<SincronizacionResponse> call, Throwable t) {
                    Toast.makeText(context, Constante.g_const_error_conexion , Toast.LENGTH_SHORT).show();
                    // finishDialog();
                    FinishProgressSweetAlert();
                    //progressDialog.dismiss();
                }
            });


        });
    }



    public void RegistrarSesionInicio(RequestSesion requestSesion, int tipo,Context context) {
        Call<ResponseSesion> call = ApiAdapter.getApiService().ControlSesionUsu(requestSesion);
        call.enqueue(new Callback<ResponseSesion>() {
            @Override
            public void onResponse(Call<ResponseSesion> call, Response<ResponseSesion> response) {
                if(response.isSuccessful()){
                    ResponseSesion rb = response.body();
                    if(rb.getErrorWebSer().getCodigoErr() == Constante.g_const_2000 ) {
                        if(tipo == Constante.g_const_1){
                            progressDialog.dismiss();
                            sessionManager.logout();
                            //((principal)context).finish();
                        }
                        if(tipo == Constante.g_const_3){
                            progressDialog.dismiss();
                            sessionManager.logout();
                        }

                    }
                    else{
                        // Toast.makeText(MainActivity.this, rb.getDescripcionResp(), Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    // Toast.makeText(getApplicationContext(), "Hubo un error en el Servicio", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSesion> call, Throwable t) {
                // Toast.makeText(getApplicationContext(), Constante.g_const_error_conexion, Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void RegistrarUbigeo(RequestUbigeo requestUbigeo, Context context) {
        Call<ResponseUbigeoSucur> call = ApiAdapter.getApiService().ObtenerDataUbigeo(requestUbigeo);
        call.enqueue(new Callback<ResponseUbigeoSucur>() {
            @Override
            public void onResponse(Call<ResponseUbigeoSucur> call, Response<ResponseUbigeoSucur> response) {
                if(response.isSuccessful()){
                    ResponseUbigeoSucur rb = response.body();
                    if(rb.getErrorWebSer().getCodigoErr() == Constante.g_const_2000 ) {
                        LLenarDepartamentoLocal(rb.getListDepartamentos());
                        LLenarProvinciaLocal(rb.getListProvincias());
                        LLenarDistritoLocal(rb.getListDistritos());
                    }
                    else{
                        // Toast.makeText(MainActivity.this, rb.getDescripcionResp(), Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    // Toast.makeText(getApplicationContext(), "Hubo un error en el Servicio", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUbigeoSucur> call, Throwable t) {
                Toast.makeText(context, Constante.g_const_error_conexion, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void LLenarDistritoLocal(List<ListDistrito> listDistritos) {
        List<DistritoEntity> listDistLocal = new ArrayList<>();
        DistritoEntity distrito;

        for (ListDistrito distServ: listDistritos
        ) {
            distrito = new DistritoEntity(distServ.getCodDepartamento(),distServ.getCodProvincia(),distServ.getCodDistrito(),
                    distServ.getNombre(),distServ.getUbigeo());
            listDistLocal.add(distrito);
        }
        ubigeoRepository.insertListDist(listDistLocal);
    }

    private void LLenarProvinciaLocal(List<ListProvincia> listProvincias) {
        List<ProvinciaEntity> listProvLocal = new ArrayList<>();
        ProvinciaEntity provincia;

        for (ListProvincia provServ: listProvincias
        ) {
            provincia = new ProvinciaEntity(provServ.getCodDepartamento(),provServ.getCodProvincia(),provServ.getNombre());
            listProvLocal.add(provincia);

        }
        ubigeoRepository.insertListProv(listProvLocal);
    }

    private void LLenarDepartamentoLocal(List<ListDepartamento> listDepartamentos) {
        List<DepartamentoEntity> listDepLocal = new ArrayList<>();
        DepartamentoEntity departamento;

        for (ListDepartamento depServ: listDepartamentos) {
            departamento = new DepartamentoEntity(depServ.getCodDepartamento(),depServ.getNombre());
            listDepLocal.add(departamento);
        }
        ubigeoRepository.insertListDep(listDepLocal);
    }

    /*
        private void ShowDialog(){
            progressDialog.setCancelable(false);
            progressDialog.setTitle(R.string.txt_tit_prog_sinc);
            progressDialog.setMessage("Sincronizando datos");
            progressDialog.show();
        }
    */
    private void ShowProgressSweetAlert(Context context){
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Sincronizando datos");
        pDialog.setCustomImage(R.drawable.ic_person);
        pDialog.setCancelable(false);
        pDialog.show();
    }
    private void FinishProgressSweetAlert(){pDialog.dismiss();}

    private void finishDialog(){
        progressDialog.dismiss();
    }




    public int LlenarBaseDatos(SincronizacionResponse data2)
    {
        if(data2 != null){
            //CATEGORIAS
            List<CategoriaEntity> lista = new ArrayList<>();
            CategoriaEntity categoriaEntity;
            for (ListCategoria categoria: data2.getResultadoSincro().getDataSincronizacion().getListCategorias()) {
                categoriaEntity = new CategoriaEntity(categoria.getNtraCategoria(),categoria.getDescripcion());
                lista.add(categoriaEntity);

            }
            categoriaRepository.insertList(lista);
            //CLIENTES
            List<Cliente> listaClientes = new ArrayList<>();
            Cliente clienteEntity;

            for (ListCliente cliente: data2.getResultadoSincro().getDataSincronizacion().getListClientes()) {
                clienteEntity = new Cliente(cliente.getCodPersona(),Short.parseShort(cliente.getTipoPersona().toString()),Short.parseShort(cliente.getTipoDocumento().toString()),
                        cliente.getNumeroDocumento(),cliente.getRuc(),cliente.getRazonSocial(),cliente.getNombres(),
                        cliente.getApellidoPaterno(),cliente.getApellidoMaterno(),cliente.getDireccion(),
                        cliente.getCorreo(),cliente.getTelefono(),cliente.getCelular(),Short.parseShort(cliente.getFrecuenciaCliente().toString()),
                        Short.parseShort(cliente.getTipoListaPrecio().toString()),Short.parseShort(cliente.getCodRuta().toString()),
                        cliente.getNombreCodigo(), Constante.g_const_1,cliente.getCodUbigeo(),cliente.getClasificacionCliente());
                listaClientes.add(clienteEntity);

            }

            clienteRepository.insertList(listaClientes);

            //DATA DE VENDEDOR
            Vendedor vendedor = data2.getResultadoSincro().getDataSincronizacion().getVendedor();
            VendedorEntity vendedorEntity;
            vendedorEntity = new VendedorEntity(vendedor.getNtraUsuario(),vendedor.getCodPerfil(),Short.parseShort(vendedor.getTipoDocumento().toString()),
                    vendedor.getNumeroDocumento(),vendedor.getApellidoPaterno(),vendedor.getApellidoMaterno(),vendedor.getNombres(),
                    vendedor.getNombreCompleto(),vendedor.getDireccion(),vendedor.getCorreo(),vendedor.getTelefono(),
                    vendedor.getCelular(),vendedor.getCodRuta(),vendedor.getDescRuta());
            vendedorRepository.insertVendedor(vendedorEntity);

            //DATA DE PRODUCTO
            List<ProductoEntity> listaProducto = new ArrayList<>();
            ProductoEntity productoEntity;
            for (ListProducto producto: data2.getResultadoSincro().getDataSincronizacion().getListProductos()) {
                productoEntity = new ProductoEntity(producto.getCodProducto(),producto.getDescripcion(),producto.getCodCategoria(),
                        producto.getCodUnidadBaseventa(),producto.getCodDes(), Constante.g_const_0,
                        Constante.g_const_0,Constante.g_const_0,producto.getTipoProducto());
                listaProducto.add(productoEntity);

            }
            productoRepository.insertList(listaProducto);

            //DATA DE PRESENTACIONES
            List<PresentacionEntity> listaPresentacion = new ArrayList<>();
            PresentacionEntity presentacionEntity;
            for (ListPresentacione presentacion: data2.getResultadoSincro().getDataSincronizacion().getListPresentaciones()) {
                presentacionEntity = new PresentacionEntity(presentacion.getCodProducto(),presentacion.getCodPresentancion(),
                        presentacion.getCantidadUnidadBase());
                listaPresentacion.add(presentacionEntity);
            }
            presentacionRepository.insertList(listaPresentacion);

            //DATA DE PROMOCIONES
            List<PromocionEntity> listaPromociones = new ArrayList<>();
            List<DetallePromocionEntity> listaDetallePromociones = new ArrayList<>();
            PromocionEntity promocionEntity;
            DetallePromocionEntity detallePromocionEntity;
            for (ListPromocione promocion: data2.getResultadoSincro().getDataSincronizacion().getListPromociones()) {
                promocionEntity = new PromocionEntity(promocion.getNtraPromocion(),promocion.getNtraFlag(),promocion.getFlag(),promocion.getDescPromo(),
                        promocion.getValorInicial(),promocion.getValorFinal(),
                        promocion.getDetalle(),Short.parseShort(promocion.getEstado().toString()));

                for (ListDetallePromocion detPromo: promocion.getListDetallePromocion()) {
                    detallePromocionEntity = new DetallePromocionEntity(promocion.getNtraPromocion(),detPromo.getNtraFlag(),
                            detPromo.getFlag(),detPromo.getDescripcion(),detPromo.getValorEntero1(),
                            detPromo.getValorEntero2(),detPromo.getValorMoneda1(),detPromo.getValorMoneda2(),
                            detPromo.getValorCadena1(),detPromo.getValorCadena2(),detPromo.getValorFecha1(),
                            detPromo.getValorFecha2(),Short.parseShort(detPromo.getEstado().toString()));
                    listaDetallePromociones.add(detallePromocionEntity);

                }
                listaPromociones.add(promocionEntity);

            }
            promocionRepository.insertList(listaPromociones,listaDetallePromociones);

            //DATA DE DESCRIPCION DE PROMOCIONES
            List<PromocionDescEntity> listaPromocionesDesc = new ArrayList<>();
            PromocionDescEntity promocionDescEntity;
            if(data2.getResultadoSincro().getDataSincronizacion().getListPromocionesDesc() != null){
                for (ListPromocione promocion: data2.getResultadoSincro().getDataSincronizacion().getListPromocionesDesc()) {
                    promocionDescEntity = new PromocionDescEntity(promocion.getNtraPromocion(),promocion.getDescPromo());

                    listaPromocionesDesc.add(promocionDescEntity);

                }
                promocionDescRepository.insertList(listaPromocionesDesc);
            }


            //DATA DE DESCUENTOS
            List<DescuentoEntity> listDescuentosEntity = new ArrayList<>();
            List<DetalleDescuentoEntity> listaDetalleDescuentos = new ArrayList<>();
            DescuentoEntity descuentoEntity;
            DetalleDescuentoEntity detalleDescuentoEntity;
            for (ListDescuento descuento: data2.getResultadoSincro().getDataSincronizacion().getListDescuentos()) {
                descuentoEntity = new DescuentoEntity(descuento.getNtraDescuento(),descuento.getNtraFlag(),descuento.getFlag(),
                        descuento.getDescDesc(),descuento.getValorInicial(),descuento.getValorFinal(),
                        descuento.getDetalle(),Short.parseShort(descuento.getEstado().toString()),Short.parseShort(descuento.getTipoDescuento().toString()));

                for (ListDetalleDescuento detDesc: descuento.getListDetalleDescuento()) {

                    detalleDescuentoEntity = new DetalleDescuentoEntity(descuento.getNtraDescuento(),detDesc.getNtraFlag(),
                            detDesc.getFlag(),detDesc.getDescripcion(),detDesc.getValorEntero1(),
                            detDesc.getValorEntero2(),detDesc.getValorMoneda1(),detDesc.getValorMoneda2(),
                            detDesc.getValorCadena1(),detDesc.getValorCadena2(),detDesc.getValorFecha1(),
                            detDesc.getValorFecha2(),Short.parseShort(detDesc.getEstado().toString()));
                    listaDetalleDescuentos.add(detalleDescuentoEntity);

                }
                listDescuentosEntity.add(descuentoEntity);

            }
            descuentoRepository.insertList(listDescuentosEntity,listaDetalleDescuentos);

            //DATA DE ALMACENES
            List<AlmacenEntity> listaAlmacenes = new ArrayList<>();
            AlmacenEntity almacenEntity;
            for (ListAlmacene almacen: data2.getResultadoSincro().getDataSincronizacion().getListAlmacenes()) {
                almacenEntity = new AlmacenEntity(almacen.getNtraAlmacen(),almacen.getDescripcion(),almacen.getAbreviatura());

                listaAlmacenes.add(almacenEntity);
            }
            almacenRepository.insertList(listaAlmacenes);

            //DATA DE INVENTARIOS
            List<InventarioEntity> listaInventarios = new ArrayList<>();
            InventarioEntity inventarioEntity;
            for (ListInventario inventario: data2.getResultadoSincro().getDataSincronizacion().getListInventarios()) {
                inventarioEntity = new InventarioEntity(inventario.getNtraInventario(),inventario.getCodAlmacen(),
                        inventario.getCodProducto(),inventario.getStock());
                listaInventarios.add(inventarioEntity);

            }
            inventarioRepository.insertList(listaInventarios);

            //DATA DE PUNTOS DE ENTREGA
            List<EntregaEntity> listaEntrega = new ArrayList<>();
            EntregaEntity entregaEntity;
            for (ListEntrega entrega: data2.getResultadoSincro().getDataSincronizacion().getListEntregas()) {
                entregaEntity = new EntregaEntity(entrega.getNtraPuntoEntrega(),entrega.getCoordenadaX(),entrega.getCoordenadaY(),
                        entrega.getDireccion(),entrega.getReferencia(),entrega.getOrdenEntrega(), entrega.getTipoDocumento(), entrega.getNumeroDocumento(),entrega.getCodPersona(), Constante.g_const_1);
                listaEntrega.add(entregaEntity);

            }
            entregaRepository.insertList(listaEntrega);

            //DATA DE LOCALIZACIONES
            List<LocalizacionEntity> listLocalizacion = new ArrayList<>();
            LocalizacionEntity localizacionEntity;
            for (ListLocalizacion local: data2.getResultadoSincro().getDataSincronizacion().getListLocalizacion()) {
                localizacionEntity = new LocalizacionEntity(local.getCodPersona(),local.getNumDocumento(),
                                        local.getNombreCompleto(),local.getCoordenadaX(),local.getCoordenadaY(),Constante.g_const_1);
                listLocalizacion.add(localizacionEntity);
            }
            localizacionRepository.insertList(listLocalizacion);

            //DATA DE PREVENTA
            List<Preventa> listaPreventas = new ArrayList<>();
            List<PreventaDetalleEntity> listDetalle = new ArrayList<>();
            List<PreventaPromocionEntity> listPromo = new ArrayList<>();
            List<PreventaDescuentoEntity> listDesc = new ArrayList<>();
            Preventa preventaEntity;
            PreventaDetalleEntity DetalleEntity;
            PreventaPromocionEntity preventaPromocionEntity;
            PreventaDescuentoEntity preventaDescuentoEntity;
            for (ListPreventa preventa: data2.getResultadoSincro().getDataSincronizacion().getListPreventas()) {
                preventaEntity = new Preventa(preventa.getNtraPreventa(),preventa.getCodCliente(), preventa.getTipoDocumento(),preventa.getNumeroDocumento(),
                        preventa.getCodUsuario(),preventa.getCodPuntoEntrega(),Short.parseShort(preventa.getTipoMoneda().toString()),
                        Short.parseShort(preventa.getTipoVenta().toString()),Short.parseShort(preventa.getTipoDocumentoVenta().toString()),
                        preventa.getFecha(), preventa.getFlagRecargo(),preventa.getRecargo(),preventa.getIgv(),preventa.getIsc(),
                        preventa.getTotal(),Short.parseShort(preventa.getEstado().toString()), Short.parseShort(String.valueOf(Constante.g_const_1)),
                        preventa.getOrigenVenta(),preventa.getFechaEntrega(),preventa.getHoraEntrega(),preventa.getCodSucursal());

                for (ListDetPreventum detalle: preventa.getListDetPreventa()) {

                    DetalleEntity = new PreventaDetalleEntity(preventa.getNtraPreventa(),Short.parseShort(detalle.getItemPreventa().toString()),
                            detalle.getCodPresentacion(),detalle.getCodProducto(),detalle.getCodAlmacen(),detalle.getCantidadPresentacion(),
                            detalle.getCantidadUnidadBase(),detalle.getPrecioVenta(),
                            Short.parseShort(detalle.getTipoProducto().toString()),
                            Short.parseShort(String.valueOf(Constante.g_const_1)));

                    listDetalle.add(DetalleEntity);
                }
                for (ListPrevPromocion promo: preventa.getListPrevPromocion()) {

                    preventaPromocionEntity = new PreventaPromocionEntity(promo.getCodPreventa(),promo.getCodPromocion(),
                            Short.parseShort(promo.getItemPreventa().toString()),
                            Short.parseShort(promo.getItemPromocionado().toString())
                            ,0) ; //wsanchez

                    listPromo.add(preventaPromocionEntity);
                }
                for (ListPrevDescuento desc: preventa.getListPrevDescuento()) {

                    preventaDescuentoEntity = new PreventaDescuentoEntity(desc.getCodPreventa(),desc.getCodDescuento(),
                            Short.parseShort(desc.getItemPreventa().toString()),desc.getImporte()
                            ,0) ; //wsanchez

                    listDesc.add(preventaDescuentoEntity);
                }

                listaPreventas.add(preventaEntity);

            }
            //preventaRepository.insertList(listaPreventas,listDetalle,listPromo,listDesc);
            InsertarListaPreventas(listaPreventas,listDetalle,listPromo,listDesc);

            //DATA DE PRECIOS
            List<PrecioEntity> listaPrecio = new ArrayList<>();
            PrecioEntity precioEntity;
            for (ListPrecio precio: data2.getResultadoSincro().getDataSincronizacion().getListPrecios()) {
                precioEntity = new PrecioEntity(precio.getNtraPrecio(),precio.getCodProducto(),precio.getTipoListaPrecio(),
                        precio.getPrecioVenta(),precio.getDescripcion());
                listaPrecio.add(precioEntity);

            }
            precioRepository.insertList(listaPrecio);

            //DATA DE CONCEPTOS
            List<Concepto> listaConceptos = new ArrayList<>();
            Concepto conceptoEntity;
            for (ListConcepto concepto: data2.getResultadoSincro().getDataSincronizacion().getListConceptos()) {
                conceptoEntity = new Concepto(concepto.getCodConcepto(),concepto.getCorrelativo(),concepto.getDescripcion());
                listaConceptos.add(conceptoEntity);
            }
            conceptoRepository.insertList(listaConceptos);

            //DATA DE PARAMETROS

            List<Parametro> listaParametro = new ArrayList<>();
            Parametro parametro;
            for (ListParametro param: data2.getResultadoSincro().getDataSincronizacion().getListParametros()) {
                parametro = new Parametro(param.getCodParametro(),param.getTipo(),param.getValorEntero1(),
                        param.getValorEntero2(),param.getValorCaneda1(),param.getValorCaneda2(),
                        param.getValorMoneda1(),param.getValorMoneda2(),param.getValorFloat1(),
                        param.getValorFloat2(),param.getValorFecha1(),param.getValorFecha2());
                listaParametro.add(parametro);

            }
            parametroRepository.insertList(listaParametro);



        }

        int dato = Constante.g_const_1;
        return  dato;
    }

    private void InsertarListaPreventas(List<Preventa> preventas, List<PreventaDetalleEntity> detalles, List<PreventaPromocionEntity> promocioes, List<PreventaDescuentoEntity> descuentos) {
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

    private List<ListPreventa> ObtenerDataPreventas(List<PreventaTotalEntity> totalPreventaT) {
        List<ListPreventa> listPreventas = new ArrayList<>();
        for (int i = Constante.g_const_0; i <totalPreventaT.size();i++){


            ent_preventa = new ListPreventa();
            ent_preventa.setCodCliente(totalPreventaT.get(i).preventa.getCodCliente());
            ent_preventa.setTipoMoneda(1);
            ent_preventa.setTipoVenta(Integer.parseInt( String.valueOf(totalPreventaT.get(i).preventa.getTipoVenta())));
            if(totalPreventaT.get(i).punto_entrega != null) {
                if(totalPreventaT.get(i).punto_entrega.ntraPuntoEntrega > Constante.g_const_0){
                    ent_preventa.setCodPuntoEntrega(totalPreventaT.get(i).punto_entrega.ntraPuntoEntrega);
                }else{
                    ent_preventa.setCodPuntoEntrega(totalPreventaT.get(i).preventa.getCodPuntoEntrega());
                }

            }else {
                ent_preventa.setCodPuntoEntrega(totalPreventaT.get(i).preventa.getCodPuntoEntrega());
            }

            ent_preventa.setCodUsuario(totalPreventaT.get(i).preventa.getCodUsuario());
            ent_preventa.setUsuario(Constante.G_CONST_VACIO+loginLocal.ntraUsuario);
            ent_preventa.setEstado((int) totalPreventaT.get(i).preventa.getEstado());
            ent_preventa.setFecha(totalPreventaT.get(i).preventa.getFecha());
            //ent_preventa.setFechaEntrega(totalPreventaT.get(i).preventa.getFecha());
            //ent_preventa.setFechaPago(totalPreventaT.get(i).preventa.getFecha());
            ent_preventa.setIgv(totalPreventaT.get(i).preventa.getIgv());
            ent_preventa.setIsc(totalPreventaT.get(i).preventa.getIsc());
            ent_preventa.setRecargo(totalPreventaT.get(i).preventa.getRecargo());
            ent_preventa.setNtraPreventa(totalPreventaT.get(i).preventa.getNtraPreventa());
            ent_preventa.setTipoDocumentoVenta((int) totalPreventaT.get(i).preventa.getTipoDocumentoVenta());
            ent_preventa.setTotal(totalPreventaT.get(i).preventa.getTotal());
            ent_preventa.setTipoDocumento(totalPreventaT.get(i).preventa.getTipoDocumento());
            ent_preventa.setNumeroDocumento(totalPreventaT.get(i).preventa.getNumeroDocumento());
            ent_preventa.setFlagRecargo(totalPreventaT.get(i).preventa.getFlagRecargo());
            //ent_preventa.setCodSucursal(totalPreventaT.get(i).preventa.getCodSucursal());

            if(totalPreventaT.get(i).preventa.getFlag() == Constante.g_const_0){
                ent_preventa.setProceso(Constante.g_s_const_1);
            }else{
                ent_preventa.setProceso(totalPreventaT.get(i).preventa.getFlag());
            }
            ent_preventa.setOrigenVenta(totalPreventaT.get(i).preventa.getOrigenVenta());
            ent_preventa.setHoraEntrega(totalPreventaT.get(i).preventa.getHoraEntrega());
            ent_preventa.setCodSucursal(loginLocal.getCodSucursal());
            ent_preventa.setFechaEntrega(totalPreventaT.get(i).preventa.getFechaEntrega());

            listPreventaDetalles = new ArrayList<>();
            for (int y=Constante.g_const_0;y<totalPreventaT.get(i).listPreventaDetalle.size();y++){
                if(totalPreventaT.get(i).listPreventaDetalle.get(y).getFlag() != Constante.g_const_1){
                    ListDetPreventum detalle = new ListDetPreventum();
                    detalle.setCantidadPresentacion(totalPreventaT.get(i).listPreventaDetalle.get(y).getCantidadPresentacion());
                    detalle.setCantidadUnidadBase(totalPreventaT.get(i).listPreventaDetalle.get(y).getCantidadUnidadBase());
                    detalle.setCodAlmacen(totalPreventaT.get(i).listPreventaDetalle.get(y).getCodAlmacen());
                    detalle.setCodPresentacion(totalPreventaT.get(i).listPreventaDetalle.get(y).getCodPresentacion());
                    detalle.setCodPreventa(totalPreventaT.get(i).listPreventaDetalle.get(y).getCodPreventa());
                    detalle.setCodProducto(totalPreventaT.get(i).listPreventaDetalle.get(y).getCodProducto());
                    detalle.setItemPreventa((int) totalPreventaT.get(i).listPreventaDetalle.get(y).getItemPreventa());
                    detalle.setPrecioVenta(totalPreventaT.get(i).listPreventaDetalle.get(y).getPrecioVenta());
                    detalle.setTipoProducto((int) totalPreventaT.get(i).listPreventaDetalle.get(y).getTipoProducto());
                    listPreventaDetalles.add(detalle);
                }

            }
            ent_preventa.setListDetPreventa(listPreventaDetalles);

            listPrevPromocions = new ArrayList<>();
            for (int z = Constante.g_const_0; z<totalPreventaT.get(i).listPreventaPromocion.size();z++){
                if(totalPreventaT.get(i).listPreventaPromocion.get(i).getFlag() != Constante.g_const_1) {
                    ListPrevPromocion promocion = new ListPrevPromocion();
                    promocion.setCodPreventa(totalPreventaT.get(i).listPreventaPromocion.get(z).getCodPreventa());
                    promocion.setCodPromocion(totalPreventaT.get(i).listPreventaPromocion.get(z).getCodPromocion());
                    promocion.setItemPreventa((int) totalPreventaT.get(i).listPreventaPromocion.get(z).getItemPreventa());
                    promocion.setItemPromocionado((int) totalPreventaT.get(i).listPreventaPromocion.get(z).getItemPromocionado());
                    listPrevPromocions.add(promocion);
                }
            }
            ent_preventa.setListPrevPromocion(listPrevPromocions);

            listPrevDescuentos = new ArrayList<>();
            for (int a= Constante.g_const_0; a < totalPreventaT.get(i).listPreventaDescuento.size();a++){
                if(totalPreventaT.get(i).listPreventaDescuento.get(i).getFlag() != Constante.g_const_1) {
                    ListPrevDescuento descuento = new ListPrevDescuento();
                    descuento.setCodDescuento(totalPreventaT.get(i).listPreventaDescuento.get(a).getCodDescuento());
                    descuento.setCodPreventa(totalPreventaT.get(i).listPreventaDescuento.get(a).getCodPreventa());
                    descuento.setImporte(totalPreventaT.get(i).listPreventaDescuento.get(a).getImporte());
                    descuento.setItemPreventa((int) totalPreventaT.get(i).listPreventaDescuento.get(a).getItemPreventa());
                    listPrevDescuentos.add(descuento);
                }
            }
            ent_preventa.setListPrevDescuento(listPrevDescuentos);
            listPreventas.add(ent_preventa);
        }

        return listPreventas;

    }

    private ArrayList<RequestEntrega> ObtenerDataEntregas(List<EntregaEntity> listEntregaEntity) {
        ArrayList<RequestEntrega> listReqEntregas= new ArrayList<>();
        for (EntregaEntity entrega: listEntregaEntity){
            req_ent = new RequestEntrega(Constante.g_s_const_1,
                    entrega.getId(),entrega.getNtraPuntoEntrega(),entrega.getCoordenadaX(),
                    entrega.getCoordenadaY(),null,entrega.getDireccion(),entrega.getReferencia(),
                    entrega.getOrdenEntrega(),entrega.getCodPersona(),Constante.G_CONST_VACIO+loginLocal.ntraUsuario,ip, mac,
                    entrega.getTipoDocumento(),entrega.getNumeroDocumento());
            listReqEntregas.add(req_ent);
        }
        return listReqEntregas;
    }

    private ArrayList<RequestClienteSinc> ObtenerDataCliente(List<Cliente> listClienteEntity) {
        ArrayList<RequestClienteSinc> listResClientes = new ArrayList<>();
        for (Cliente cliente: listClienteEntity){
            //Buscar localizacion
            if(cliente.getTipoPersona() == Constante.g_const_2){
                localizacionEntity = localizacionDao.obtenerLocalizacion(cliente.getRuc());
            }else{
                localizacionEntity = localizacionDao.obtenerLocalizacion(cliente.getNumeroDocumento());
            }

            cli = new RequestClienteSinc(Short.parseShort(String.valueOf(Constante.g_const_1)),1,cliente.getTipoPersona(),cliente.getTipoDocumento(),
                    cliente.getNumeroDocumento(),cliente.getRuc(),cliente.getRazonSocial(),
                    cliente.getNombres(),cliente.getApellidoPaterno(),cliente.getApellidoMaterno(),
                    cliente.getDireccion(),cliente.getCorreo(),cliente.getTelefono(),
                    cliente.getCelular(),cliente.getUbigeo(),null,null,
                    cliente.getClasificacionCliente(),cliente.getFrecuencia(),cliente.getTipoListaPrecio(),
                    cliente.getCodRuta(),localizacionEntity.getCoordenadaX(),localizacionEntity.getCoordenadaY(),Constante.G_CONST_VACIO+loginLocal.ntraUsuario,ip,mac);
            listResClientes.add(cli);
        }
        return listResClientes;
    }

    private ArrayList<ResquestRutasBitacora> ObtenerDataBitacora(List<BitacoraVendedorEntity> listaBitacoras) {
        ArrayList<ResquestRutasBitacora> listResBitacora = new ArrayList<>();
        for (BitacoraVendedorEntity bitacoraVendedorEntity: listaBitacoras){

            rbv = new ResquestRutasBitacora(bitacoraVendedorEntity.getCodRuta(),bitacoraVendedorEntity.getCodCliente(),bitacoraVendedorEntity.getFecha(),
                    bitacoraVendedorEntity.getVisita(),bitacoraVendedorEntity.getMotivo(),bitacoraVendedorEntity.getUsuario(),bitacoraVendedorEntity.getIp(),bitacoraVendedorEntity.getMac(),bitacoraVendedorEntity.getCordenadaX(),
                    bitacoraVendedorEntity.getCordenadaY(),bitacoraVendedorEntity.getEstado());
            listResBitacora.add(rbv);
        }
        return listResBitacora;
    }


}
