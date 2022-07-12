package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.ClienteSpinnerEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCliente;

import java.util.List;

@Dao
public interface ClienteDao {
    @Insert //(onConflict = OnConflictStrategy.IGNORE)
    void insert(Cliente cliente);

    @Query("SELECT * from table_cliente")
    LiveData<List<Cliente>> getClientes();

    @Query("SELECT COUNT(tipoDocumento) from table_cliente where numeroDocumento = (:numeroDoc) or ruc = (:numeroDoc) ")
    LiveData<Integer> buscarNumeroDocumento(String numeroDoc);

    @Query("SELECT ntra, codCliente, tipoPersona, tipoDocumento, numeroDocumento, ruc, razonSocial, nombres, apellidoPaterno, apellidoMaterno, flag  from table_cliente LIMIT 15")
    LiveData<List<FilaCliente>> getListaClientes();

    @Query("SELECT ntra, codCliente, tipoPersona, tipoDocumento, numeroDocumento, ruc, razonSocial, nombres, apellidoPaterno, apellidoMaterno, flag from table_cliente where busqueda like '%' || :cadena || '%' ORDER BY ntra DESC LIMIT 100 ")
    LiveData<List<FilaCliente>> buscarClientes(String cadena);

    @Query("SELECT ntra, codCliente, tipoPersona, tipoDocumento, numeroDocumento, ruc, razonSocial, nombres, apellidoPaterno, apellidoMaterno, direccion, correo, telefono, celular, frecuencia, tipoListaPrecio, codRuta, busqueda, flag,ubigeo,clasificacionCliente from table_cliente where ntra = :transaccion LIMIT 1")
    LiveData<Cliente> buscarCliente(Integer transaccion);

    @Query("UPDATE table_cliente SET razonSocial = :razonSocial, nombres = :nombres, apellidoPaterno = :apePaterno, apellidoMaterno = :apeMaterno, direccion = :direccion, correo = :correo, telefono = :telefono, celular = :celular, flag = :flag WHERE ntra = :ntra ")
    void update(Integer ntra, String razonSocial, String nombres, String apePaterno, String apeMaterno, String direccion, String correo, String telefono, String celular, Integer flag);

    @Query("DELETE FROM table_cliente")
    void deleteAll();

    @Insert
    void insertList(List<Cliente> clientes);

    @Query("DELETE FROM sqlite_sequence WHERE name='table_cliente'")
    void deleteSequence();

    @Query("SELECT cli.ntra, cli.codCliente, cli.tipoPersona, cli.tipoDocumento, cli.numeroDocumento, cli.ruc, cli.razonSocial, cli.nombres, " +
            "cli.apellidoPaterno, cli.apellidoMaterno, cli.direccion, cli.correo, cli.telefono, cli.celular, cli.frecuencia, cli.tipoListaPrecio, " +
            "cli.codRuta, cli.busqueda, cli.flag,cli.ubigeo,cli.clasificacionCliente " +
            "from table_cliente cli " +
            "INNER JOIN tblCabecera cab " +
            "WHERE cab.numeroDocumento = cli.numeroDocumento OR cab.numeroDocumento = cli.ruc LIMIT 1")
    LiveData<Cliente> buscarClientePreventa();

    @Query("UPDATE table_cliente SET flag = 1, codCliente = :codCliente WHERE ntra = :ntra and flag = 0 ")
    void actualizarDatosPreventa(Integer codCliente, Integer ntra);

    @Query("SELECT * from table_cliente where flag = 0")
    LiveData<List<Cliente>> getClientesSin();
    @Query("SELECT * from table_cliente where flag = 0")
    List<Cliente> getClientesSinRep();

    @Query("SELECT ruc, numeroDocumento,  (nombres ||' '||apellidoPaterno||' '||apellidoMaterno) as nombreCompleto, razonSocial from table_cliente AS tc " +
            "WHERE NOT EXISTS (SELECT 1 codCliente FROM table_bitacoraVendedor AS tv WHERE tc.numeroDocumento = tv.codCliente OR tc.ruc = tv.codCliente)")
    LiveData<List<ClienteSpinnerEntity>> obtenerCliente();

    @Query("SELECT MAX(ntra) as 'ntra' FROM table_cliente LIMIT 1")
    Integer ntraCliente();

    @Query("SELECT COUNT(*) FROM table_cliente tc " +
            "WHERE NOT EXISTS (SELECT 1 codCliente FROM table_bitacoraVendedor " +
            "AS tv WHERE tc.numeroDocumento = tv.codCliente OR tc.ruc = tv.codCliente)")
    LiveData<Integer> cantidadClientes();

    @Query("DELETE FROM table_cliente WHERE ntra = :ntra ")
    void eliminarCliente(int ntra);

    @Query("SELECT COUNT(tipoDocumento) from table_cliente where numeroDocumento = (:numeroDoc) or ruc = (:numeroDoc) ")
    Integer buscarNumeroDocumentoDos(String numeroDoc);

    @Query("SELECT UPPER(nombres || ' ' || apellidoPaterno || ' '||apellidoMaterno ) FROM table_cliente WHERE numeroDocumento = :numDoc ")
    LiveData<String> obtenerNombreClienteND(String numDoc);

    @Query("SELECT UPPER(razonSocial) FROM table_cliente WHERE ruc = :ruc ")
    LiveData<String> obtenerNombreClienteRuc(String ruc);

    @Query("UPDATE tblPuntoEntrega SET flag = 1, ntraPuntoEntrega = :ntraPuntoEntrega , codPersona = :codCliente WHERE id = :ntra and flag = 0 ")
    void actualizarDatosPuntoEntrega(int ntraPuntoEntrega, int codCliente, int ntra);

    @Query("SELECT MAX(id) FROM tblPuntoEntrega LIMIT 1")
    Integer ntraPuntoEntrega();
}
