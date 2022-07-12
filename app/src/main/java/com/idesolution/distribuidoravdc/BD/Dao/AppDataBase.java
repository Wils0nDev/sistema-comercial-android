package com.idesolution.distribuidoravdc.BD.Dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.idesolution.distribuidoravdc.BD.Entity.AlmacenEntity;
import com.idesolution.distribuidoravdc.BD.Entity.BitacoraVendedorEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ClienteSpinnerEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Cabecera;
import com.idesolution.distribuidoravdc.BD.Entity.CategoriaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.Concepto;
import com.idesolution.distribuidoravdc.BD.Entity.DepartamentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Detalle;
import com.idesolution.distribuidoravdc.BD.Entity.DetalleDescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DetallePromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DistritoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.EntregaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaDescuento;
import com.idesolution.distribuidoravdc.BD.Entity.FilaPromocion;
import com.idesolution.distribuidoravdc.BD.Entity.InventarioEntity;
import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Parametro;
import com.idesolution.distribuidoravdc.BD.Entity.PrecioEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PresentacionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDescuento;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDetalleEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaPromocion;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaPromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ProductoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionDescEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ProvinciaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Ruta;
import com.idesolution.distribuidoravdc.BD.Entity.SucursalEntity;
import com.idesolution.distribuidoravdc.BD.Entity.VendedorEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CategoriaEntity.class,Cliente.class, ProductoEntity.class, PrecioEntity.class,
        AlmacenEntity.class, InventarioEntity.class, PromocionEntity.class, DetallePromocionEntity.class,
        DescuentoEntity.class, DetalleDescuentoEntity.class, Concepto.class, Ruta.class, Preventa.class, PreventaDetalleEntity.class,
        PresentacionEntity.class, Cabecera.class, Detalle.class,
        EntregaEntity.class, VendedorEntity.class, PreventaPromocionEntity.class, PreventaDescuentoEntity.class,
        Parametro.class,BitacoraVendedorEntity.class, FilaPromocion.class, PreventaPromocion.class, FilaDescuento.class, PreventaDescuento.class,
        SucursalEntity.class, DepartamentoEntity.class, ProvinciaEntity.class, DistritoEntity.class,
        LocalizacionEntity.class, PromocionDescEntity.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ClienteDao clienteDao();
    public abstract CategoriaDao CategoriaDao();
    public abstract ProductoDao ProductoDao();
    public abstract PrecioDao PrecioDao();
    public abstract AlmacenDao almacenDao();
    public abstract InventarioDao inventarioDao();
    public abstract ProductoDetalleDao productoDetalleDao();
    public abstract PromocionDao promocionDao();
    public abstract DetallePromocionDao detallePromocionDao();
    public abstract DescuentoDao descuentoDao();
    public abstract DetalleDescuentoDao detalleDescuentoDao();
    public abstract ConceptoDao conceptoDao();
    public abstract RutaDao rutaDao();
    public abstract EntregaDao entregaDao();
    public abstract VendedorDao vendedorDao();
    public abstract PreventaPromocionDao preventaPromocionDao();
    public abstract PreventaDescuentoDao preventaDescuentoDao();
    public abstract PreventaDao preventaDao();
    public abstract PreventaDetalleDao preventaDetalleDao();
    public abstract PresentacionDao presentacionDao();
    public abstract CabeceraDao cabeceraDao();
    public abstract DetalleDao detalleDao();
    public abstract ParametroDao parametroDao();
    public abstract  BitacoraVendedorDao bitacoraVendedorDao();
    public abstract  FilaPromocionDao filaPromocionDao();
    public abstract  FilaDescuentoDao filaDescuentoDao();
    public abstract  PreventaPromocionTempDao preventaPromocionTempDao();
    public abstract  PreventaDescuentoTempDao preventaDescuentoTempDao();
    public abstract SucursalDao sucursalDao();
    public abstract UbigeoDao ubigeoDao();
    public abstract LocalizacionDao localizacionDao();
    public abstract PromocionDescDao promocionDescDao();

    private static final String DB_NAME = "BD_Distribuidora_72";

    private static volatile AppDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, DB_NAME)
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    /*
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                ClienteDao cliDao = INSTANCE.clienteDao();
                ConceptoDao conDao = INSTANCE.conceptoDao();
                RutaDao rutDao = INSTANCE.rutaDao();
                PresentacionDao preDao = INSTANCE.presentacionDao();
                ParametroDao parDao = INSTANCE.parametroDao();
                conDao.deleteAll();
                rutDao.deleteAll();
                preDao.deleteAll();

                parDao.deleteAll();

                //PARAMETROS
                //porcentaje recargo
                Parametro par = new Parametro(1, null, null, null, null, null,2.0,null,null, null, null, null);
                parDao.insert(par);
                //igv
                par = new Parametro(2, null, null, null, null, null,1.18,null,null, null, null, null);
                parDao.insert(par);
                //flag uso de recargo
                par = new Parametro(3, null, 1, null, null, null,null,null,null, null, null, null);
                parDao.insert(par);

                //Tipo persona
                Concepto c = new Concepto(1, 1, "NATURAL");
                conDao.insert(c);
                c = new Concepto( 1, 2, "JURIDICA");
                conDao.insert(c);

                //tipos documentos
                c = new Concepto( 2, 1, "DNI");
                conDao.insert(c);
                c = new Concepto( 2, 2, "CARNET EXTRANJERIA");
                conDao.insert(c);
                c = new Concepto( 2, 3, "RUC");
                conDao.insert(c);

                //Frecuencia
                c = new Concepto( 5, 1, "SEMANAL");
                conDao.insert(c);
                c = new Concepto( 5, 2, "QUINCENAL");
                conDao.insert(c);
                c = new Concepto( 5, 3, "MENSUAL");
                conDao.insert(c);

                //Precios
                c = new Concepto( 7, 1, "PRECIO MAYORISTA");
                conDao.insert(c);
                c = new Concepto( 7, 2, "PRECIO COBERTURA");
                conDao.insert(c);
                c = new Concepto( 7, 3, "PRECIO SUPERMERCADO");
                conDao.insert(c);

                //PRESENTACIONES PRODUCTO
                c = new Concepto( 12, 1, "UNIDAD");
                conDao.insert(c);
                c = new Concepto( 12, 2, "BOLSA");
                conDao.insert(c);
                c = new Concepto( 12, 3, "PAQUETE");
                conDao.insert(c);
                c = new Concepto( 12, 4, "CAJA");
                conDao.insert(c);

                Ruta r = new Ruta(15, "LA VICTORIA");
                rutDao.insert(r);
                r = new Ruta(20, "MONSEFU");
                rutDao.insert(r);
                r = new Ruta(89, "CHONGOYAPE");
                rutDao.insert(r);

                //PRESENTACIONES
                PresentacionEntity p = new PresentacionEntity("P01", 1, 1);
                preDao.insert(p);
                p = new PresentacionEntity("P01", 4, 20);
                preDao.insert(p);
                p = new PresentacionEntity("P05", 2, 3);
                preDao.insert(p);
                p = new PresentacionEntity("P05", 3, 12);
                preDao.insert(p);
                p = new PresentacionEntity("P06", 2, 6);
                preDao.insert(p);
                p = new PresentacionEntity("P06", 4, 30);
                preDao.insert(p);

                CategoriaDao categoriaDao = INSTANCE.CategoriaDao();
                categoriaDao.deleteAll();
                categoriaDao.deleteSequence();
                //, Short.parseShort("0")

                CategoriaEntity categoria = new CategoriaEntity(1,"Liquidos");
                categoriaDao.insert(categoria);

                CategoriaEntity categoria2 = new CategoriaEntity(2,"Detergentes");
                categoriaDao.insert(categoria2);

                CategoriaEntity categoria3 = new CategoriaEntity(3,"Escobas");
                categoriaDao.insert(categoria3);

                CategoriaEntity categoria4 = new CategoriaEntity(4,"Sillas");
                categoriaDao.insert(categoria4);


                //Producto
                ProductoDao productoDao = INSTANCE.ProductoDao();
                productoDao.deleteAll();
                productoDao.deleteSequence();
                ProductoEntity producto = new ProductoEntity("P01","Producto 01",1,1,"P01-Producto 01",28,0,1);
                productoDao.insert(producto);

                ProductoEntity producto2 = new ProductoEntity("P02","Producto 02",1,1,"P02-Producto 02",30,0,1);
                productoDao.insert(producto2);

                ProductoEntity producto3 = new ProductoEntity("P03","Producto 03",2,1,"P03-Producto 03",10,0,1);
                productoDao.insert(producto3);

                ProductoEntity producto4 = new ProductoEntity("P04","Producto 04",1,1,"P04-Producto 04",20,0,1);
                productoDao.insert(producto4);

                ProductoEntity producto5 = new ProductoEntity("P05","Producto 05",1,1,"P05-Producto 05",35,0,1);
                productoDao.insert(producto5);

                ProductoEntity producto6 = new ProductoEntity("P06","Producto 06",1,1,"P06-Producto 06",140,0,1);
                productoDao.insert(producto6);

                ProductoEntity producto7 = new ProductoEntity("P07","Producto 07",1,1,"P07-Producto 07",78,0,1);
                productoDao.insert(producto7);

                ProductoEntity producto8 = new ProductoEntity("P08","Producto 08",1,1,"P08-Producto 08",520,0,1);
                productoDao.insert(producto8);


                PrecioDao precioDao = INSTANCE.PrecioDao();
                precioDao.deleteAll();
                precioDao.deleteSequence();

                //int ntraPrecio, int codProducto, int tipoListaPrecio, double precioVenta

                PrecioEntity precio1 = new PrecioEntity(1,"P01",1,31.50,"TIENDAS");
                precioDao.insert(precio1);

                PrecioEntity precio2 = new PrecioEntity(2,"P01",2,36.50,"SUPERMERCADOS");
                precioDao.insert(precio2);

                PrecioEntity precio3 = new PrecioEntity(3,"P04",1,25.30,"SUPERMERCADOS");
                precioDao.insert(precio3);

                PrecioEntity precio4 = new PrecioEntity(4,"P05",1,54.30,"SUPERMERCADOS");
                precioDao.insert(precio4);

                PrecioEntity precio5 = new PrecioEntity(5,"P06",1,42.30,"SUPERMERCADOS");
                precioDao.insert(precio5);

                PrecioEntity precio6 = new PrecioEntity(6,"P07",1,12.30,"SUPERMERCADOS");
                precioDao.insert(precio6);

                PrecioEntity precio7 = new PrecioEntity(7,"P08",1,32.30,"SUPERMERCADOS");
                precioDao.insert(precio7);

                //Registro almacen
                AlmacenDao almacenDao = INSTANCE.almacenDao();
                almacenDao.deleteAll();
                almacenDao.deleteSequence();

                AlmacenEntity almacen1 = new AlmacenEntity(1,"Almacen principal","ALMP");
                almacenDao.insert(almacen1);

                //Registro de inventario
                InventarioDao inventarioDao = INSTANCE.inventarioDao();
                inventarioDao.deleteAll();
                inventarioDao.deleteSequence();

                InventarioEntity invetario1 = new InventarioEntity(1,1,"P01",158);
                inventarioDao.insert(invetario1);

                InventarioEntity invetario2 = new InventarioEntity(2,1,"P04",130);
                inventarioDao.insert(invetario2);

                InventarioEntity invetario3 = new InventarioEntity(3,1,"P05",130);
                inventarioDao.insert(invetario3);

                InventarioEntity invetario4 = new InventarioEntity(4,1,"P06",130);
                inventarioDao.insert(invetario4);

                InventarioEntity invetario5 = new InventarioEntity(5,1,"P07",150);
                inventarioDao.insert(invetario5);

                InventarioEntity invetario6 = new InventarioEntity(6,1,"P08",52);
                inventarioDao.insert(invetario6);

                //Promocion
                PromocionDao promocionDao = INSTANCE.promocionDao();



                //Detalle promocion

                DetallePromocionDao detallePromocionDao = INSTANCE.detallePromocionDao();
                detallePromocionDao.deleteAll();
                detallePromocionDao.deleteSequence();


                //Descuento
                DescuentoDao descuentoDao = INSTANCE.descuentoDao();


                //Detalle descuento

                DetalleDescuentoDao detalleDescuentoDao = INSTANCE.detalleDescuentoDao();
                detalleDescuentoDao.deleteAll();
                detalleDescuentoDao.deleteSequence();
            });
        }
    };

    */
}
