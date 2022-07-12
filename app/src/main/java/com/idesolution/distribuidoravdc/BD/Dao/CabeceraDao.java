package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.Cabecera;
import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.Detalle;
import com.idesolution.distribuidoravdc.BD.Entity.FilaDireccion;

import java.util.List;

@Dao
public interface CabeceraDao {
    @Insert //(onConflict = OnConflictStrategy.IGNORE)
    void insert(Cabecera cabecera);

    @Query("SELECT * from tblCabecera ")
    LiveData<List<Cabecera>> getCabeceras();

    @Query("DELETE FROM tblCabecera")
    void deleteAll();

    @Query("SELECT ntra FROM tblCabecera LIMIT 1")
    LiveData<Integer> obtenerNtraCabecera();

    /*@Query("SELECT cli.ntra, cli.codCliente, cli.tipoPersona, cli.tipoDocumento, cli.numeroDocumento, cli.ruc, UPPER(cli.razonSocial) as 'razonSocial', " +
            "UPPER(cli.nombres) as 'nombres', UPPER(cli.apellidoPaterno) as 'apellidoPaterno', UPPER(cli.apellidoMaterno) as 'apellidoMaterno', cli.direccion, cli.correo, cli.telefono, cli.celular, " +
            "cli.frecuencia, cli.tipoListaPrecio, cli.codRuta, cli.busqueda, cli.flag " +
            "FROM tblCabecera cab " +
            "INNER JOIN table_cliente cli ON cli.ntra = cab.ntraCliente " +
            "LIMIT 1")
    LiveData<Cliente> obtenerNombreCliente();*/

    @Query("UPDATE tblCabecera SET tipoVenta = :tipoVenta, tipoDocumentoVenta = :tipodv, flagRecargo = :flagRecargo , recargo = :recargo, igv = :igv, total = :total, fechaEntrega = :fechaEntrega, horaEntrega = :horaEntrega ")
    void salvarCabecera(short tipoVenta, short tipodv, short flagRecargo, double recargo, double igv, double total, String fechaEntrega, String horaEntrega);

    @Query("UPDATE tblCabecera SET tipoVenta = :tipoVenta, tipoDocumentoVenta = :tipodv, flagRecargo = :flagRecargo ")
    void salvarCabecera(short tipoVenta, short tipodv, short flagRecargo);

    @Query("SELECT * FROM tblCabecera LIMIT 1 ")
    LiveData<Cabecera> obtenerDatoVenta();

    /*@Query("SELECT pun.id, pun.ntraPuntoEntrega, pun.direccion " +
            "FROM tblCabecera cab " +
            "INNER JOIN tblPuntoEntrega pun ON pun.ntraPersona = cab.ntraCliente ")
    LiveData<List<FilaDireccion>> obtenerDirecciones();*/

    @Query("SELECT cli.ntra, cli.codCliente, cli.tipoPersona, cli.tipoDocumento, cli.numeroDocumento, cli.ruc, UPPER(cli.razonSocial) as 'razonSocial', " +
            "UPPER(cli.nombres) as 'nombres', UPPER(cli.apellidoPaterno) as 'apellidoPaterno', UPPER(cli.apellidoMaterno) as 'apellidoMaterno', cli.direccion, cli.correo, cli.telefono, cli.celular, " +
            "cli.frecuencia, cli.tipoListaPrecio, cli.codRuta, cli.busqueda, cli.flag,cli.ubigeo,cli.clasificacionCliente " +
            "FROM tblCabecera cab " +
            "INNER JOIN table_cliente cli " +
            "WHERE cab.numeroDocumento = cli.numeroDocumento OR cab.numeroDocumento = cli.ruc " +
            "LIMIT 1")
    LiveData<Cliente> obtenerNombreClientePreventa();

    @Query("SELECT pun.id, pun.ntraPuntoEntrega, pun.direccion " +
            "FROM tblCabecera cab " +
            "INNER JOIN tblPuntoEntrega pun ON pun.numeroDocumento = cab.numeroDocumento ")
    LiveData<List<FilaDireccion>> obtenerDireccionesPreventa();

    @Query("SELECT * FROM tblCabecera LIMIT 1 ")
    Cabecera obtenerCabecera();

    @Query("SELECT cli.ntra, cli.codCliente, cli.tipoPersona, cli.tipoDocumento, cli.numeroDocumento, cli.ruc, UPPER(cli.razonSocial) as 'razonSocial', " +
            "UPPER(cli.nombres) as 'nombres', UPPER(cli.apellidoPaterno) as 'apellidoPaterno', UPPER(cli.apellidoMaterno) as 'apellidoMaterno', cli.direccion, cli.correo, cli.telefono, cli.celular, " +
            "cli.frecuencia, cli.tipoListaPrecio, cli.codRuta, cli.busqueda, cli.flag,cli.ubigeo,cli.clasificacionCliente " +
            "FROM tblCabecera cab " +
            "INNER JOIN table_cliente cli " +
            "WHERE cab.numeroDocumento = cli.numeroDocumento OR cab.numeroDocumento = cli.ruc " +
            "LIMIT 1")
    Cliente obtenerClienteCabecera();

    @Query("SELECT codCliente FROM tblCabecera LIMIT 1 ")
    Integer obtenerCodCliente();

    @Query("SELECT * FROM tbldetalle WHERE precioVenta != 0 ")
    List<Detalle> obtenerDetalles();

    @Query("SELECT SUM(precioVenta * cantidadUnidadBase) FROM tbldetalle ")
    Double obtenerImporteTotalDetalles();

    /*@Query("SELECT precioVenta FROM tbldetalle WHERE itemPreventa ")
    Double obtenerImporteTotalDetalles();*/

    //EDITAR PREVENTA

    @Query("SELECT COUNT(pun.numeroDocumento) FROM tblCabecera cab " +
            "INNER JOIN tblPuntoEntrega pun ON cab.numeroDocumento = pun.numeroDocumento ")
    LiveData<Integer> verificarCantidadPuntosEntrega();

    @Query("SELECT pun.id, pun.ntraPuntoEntrega, pun.direccion " +
            "FROM tblCabecera cab " +
            "INNER JOIN tblPuntoEntrega pun ON pun.numeroDocumento = cab.numeroDocumento ")
    LiveData<FilaDireccion> obtenerDireccionesPreventaDos();

    @Query("UPDATE tblCabecera SET tipoVenta = :tipoVenta ")
    void actualizarTipoVenta(int tipoVenta);

    @Query("UPDATE tblCabecera SET tipoDocumentoVenta = :tipoDocumentoVenta ")
    void actualizarTipoDocumentoVenta(int tipoDocumentoVenta);

    @Query("SELECT tipoVenta FROM tblCabecera LIMIT 1")
    Integer obtenerTipoVenta();
}
