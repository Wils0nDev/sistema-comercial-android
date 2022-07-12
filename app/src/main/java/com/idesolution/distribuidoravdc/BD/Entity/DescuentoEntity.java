package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName = Constante.NAME_TABLE_DESCUENTO)
public class DescuentoEntity {
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

    @ColumnInfo(name = "valorInicial")
    public String valorInicial;

    @ColumnInfo(name = "valorFinal")
    public String valorFinal;

    @ColumnInfo(name = "detalle")
    public int detalle;

    @ColumnInfo(name = "estado")
    public short estado;

    @ColumnInfo(name = "tipoDescuento")
    public short tipoDescuento;

    public DescuentoEntity(int ntra, @NonNull String ntraDescuento, int flag, String descripcion, String valorInicial, String valorFinal, int detalle, short estado, short tipoDescuento) {
        this.ntra = ntra;
        this.ntraDescuento = ntraDescuento;
        this.flag = flag;
        this.descripcion = descripcion;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
        this.detalle = detalle;
        this.estado = estado;
        this.tipoDescuento = tipoDescuento;
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

    public String getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(String valorInicial) {
        this.valorInicial = valorInicial;
    }

    public String getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(String valorFinal) {
        this.valorFinal = valorFinal;
    }

    public int getDetalle() {
        return detalle;
    }

    public void setDetalle(int detalle) {
        this.detalle = detalle;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public short getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(short tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }
    public int getNtra() {
        return ntra;
    }

    public void setNtra(int ntra) {
        this.ntra = ntra;
    }
}
