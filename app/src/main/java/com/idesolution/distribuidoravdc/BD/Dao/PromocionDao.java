package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.PromocionEntity;

import java.util.List;

@Dao
public interface PromocionDao {

    @Query("SELECT * FROM tblpromociones")
    LiveData<List<PromocionEntity>> getAllPromocion();

    @Query("DELETE FROM tblpromociones")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblpromociones'")
    void deleteSequence();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PromocionEntity... promocion);

    @Insert
    void insertList(List<PromocionEntity> promociones);

    /*@Query("SELECT COUNT(ntra) FROM tblPromociones WHERE codProductoInicio = :codProducto and flag = 1 ")
    LiveData<Integer> verificarPromocion(String codProducto);*/

    //Verificar producto promocionado
    @Query("SELECT COUNT(ntra) FROM tblPromociones WHERE codProductoInicio = :codProducto and flag = 1 ")
    Integer verificarPromocion(String codProducto);

    //Obtener promociones
    @Query("SELECT * FROM tblPromociones WHERE codProductoInicio = :codProducto and flag = 1 ")
    List<PromocionEntity> obtenerPromociones(String codProducto);

    //cantidad / importe
    @Query("SELECT * FROM tblPromociones WHERE ntra = :ntra and flag = 2 ")
    PromocionEntity flag_dos(int ntra);

    //validar existencia de flag
    @Query("SELECT COUNT(ntra) FROM tblPromociones WHERE ntra = :ntra and flag = :flag ")
    Integer validar_exitencia_flag(int ntra, int flag);

    //obtener flag
    @Query("SELECT * FROM tblPromociones WHERE ntra = :ntra and flag = :flag ")
    PromocionEntity obtener_flag(int ntra, int flag);

    @Query("SELECT * FROM tblPromociones WHERE ntra = :ntra and flag = :flag ")
    LiveData<PromocionEntity> obtener_flag_promocion(int ntra, int flag);

    /*
    @Query("SELECT con.ntra, con.codConcepto ,con.correlativo, con.descripcion, dpr.cantidadUnidadBase " +
            "FROM tblProducto pro " +
            "INNER JOIN tblDetallePresentacion dpr ON pro.codProducto = dpr.codProducto " +
            "INNER JOIN table_concepto con ON dpr.codPresentancion = con.correlativo "+
            "WHERE pro.codProducto = :codProducto AND con.codConcepto = 12 order by con.correlativo asc ")
    LiveData<List<FilaPresentacion>> obtenerPresentacionesCombo(String codProducto);
    * */

    //cantidad de preventas que han usado la promocion
    @Query("SELECT COUNT(DISTINCT(des.codPreventa)) FROM tblPreventaPromocion des " +
            "INNER JOIN tblPreventa pre ON des.codPreventa = pre.ntraPreventa " +
            "WHERE pre.estado != 2 AND des.codPromocion = :ntra ")
    Integer cantidad_preventas_usan_promocion(int ntra);

    //cantidad de promociones usadas por un vendedor
    @Query("SELECT COUNT(DISTINCT(codPreventa)) " +
            "FROM tblPreventaPromocion pro " +
            "INNER JOIN tblPreventa pre ON pro.codPreventa = pre.ntraPreventa " +
            "WHERE pre.estado != 2 AND pro.codPromocion = :ntra and pre.codUsuario = :ntraUsuario ")
    Integer cantidad_preventas_usan_promocion_vendedor(int ntra, int ntraUsuario);

    //cantidad de promociones usadas por un cliente
    @Query("SELECT COUNT(DISTINCT(codPreventa)) " +
            "FROM tblPreventaPromocion pro " +
            "INNER JOIN tblPreventa pre ON pro.codPreventa = pre.ntraPreventa " +
            "WHERE pre.estado != 2 AND pro.codPromocion = :ntra and pre.codCliente = :codCliente ")
    Integer cantidad_preventas_usan_promocion_cliente(int ntra, int codCliente);

    @Query("SELECT ntra FROM tblPromociones WHERE codProductoInicio = :codProducto and flag = 1 ")
    List<Integer> ntraPromocion(String codProducto);



}
