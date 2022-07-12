package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName = Constante.NAME_TABLE_DETALLE_DESCUENTO)
public class DetalleDescuentoEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "ntra")
    @NonNull
    public int ntra;

    @ColumnInfo(name = "ntraDescuento")
    @NonNull
    public String ntraDescuento;

    @ColumnInfo(name = "flag")
    @NonNull
    public int flag;

    @ColumnInfo(name = "descripcion")
    public String descripcion;

    @ColumnInfo(name = "valorEntero1")
    public int valorEntero1;

    @ColumnInfo(name = "valorEntero2")
    public int valorEntero2;

    @ColumnInfo(name = "valorMoneda1")
    public double valorMoneda1;

    @ColumnInfo(name = "valorMoneda2")
    public double valorMoneda2;

    @ColumnInfo(name = "valorCadena1")
    public String valorCadena1;

    @ColumnInfo(name = "valorCadena2")
    public String valorCadena2;

    @ColumnInfo(name = "valorFecha1")
    public String valorFecha1;

    @ColumnInfo(name = "valorFecha2")
    public String valorFecha2;

    @ColumnInfo(name = "estado")
    public short estado;

    public DetalleDescuentoEntity(int ntra, @NonNull String ntraDescuento, int flag, String descripcion, int valorEntero1, int valorEntero2, double valorMoneda1, double valorMoneda2, String valorCadena1, String valorCadena2, String valorFecha1, String valorFecha2, short estado) {
        this.ntra = ntra;
        this.ntraDescuento = ntraDescuento;
        this.flag = flag;
        this.descripcion = descripcion;
        this.valorEntero1 = valorEntero1;
        this.valorEntero2 = valorEntero2;
        this.valorMoneda1 = valorMoneda1;
        this.valorMoneda2 = valorMoneda2;
        this.valorCadena1 = valorCadena1;
        this.valorCadena2 = valorCadena2;
        this.valorFecha1 = valorFecha1;
        this.valorFecha2 = valorFecha2;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNtraDescuento() {
        return ntraDescuento;
    }

    public void setNtraDescuento(String ntraDescuento) {
        this.ntraDescuento = ntraDescuento;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValorEntero1() {
        return valorEntero1;
    }

    public void setValorEntero1(int valorEntero1) {
        this.valorEntero1 = valorEntero1;
    }

    public int getValorEntero2() {
        return valorEntero2;
    }

    public void setValorEntero2(int valorEntero2) {
        this.valorEntero2 = valorEntero2;
    }

    public double getValorMoneda1() {
        return valorMoneda1;
    }

    public void setValorMoneda1(double valorMoneda1) {
        this.valorMoneda1 = valorMoneda1;
    }

    public double getValorMoneda2() {
        return valorMoneda2;
    }

    public void setValorMoneda2(double valorMoneda2) {
        this.valorMoneda2 = valorMoneda2;
    }

    public String getValorCadena1() {
        return valorCadena1;
    }

    public void setValorCadena1(String valorCadena1) {
        this.valorCadena1 = valorCadena1;
    }

    public String getValorCadena2() {
        return valorCadena2;
    }

    public void setValorCadena2(String valorCadena2) {
        this.valorCadena2 = valorCadena2;
    }

    public String getValorFecha1() {
        return valorFecha1;
    }

    public void setValorFecha1(String valorFecha1) {
        this.valorFecha1 = valorFecha1;
    }

    public String getValorFecha2() {
        return valorFecha2;
    }

    public void setValorFecha2(String valorFecha2) {
        this.valorFecha2 = valorFecha2;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public int getNtra() {
        return ntra;
    }

    public void setNtra(int ntra) {
        this.ntra = ntra;
    }
}
