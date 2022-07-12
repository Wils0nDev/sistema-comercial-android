package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName = Constante.NAME_TABLE_PUNTO_ENTREGA)
public class EntregaEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "ntraPuntoEntrega")
    public Integer ntraPuntoEntrega;

    @ColumnInfo(name = "coordenadaX")
    public String coordenadaX;

    @ColumnInfo(name = "coordenadaY")
    public String coordenadaY;

    @ColumnInfo(name = "direccion")
    public String direccion;

    @ColumnInfo(name = "referencia")
    public String referencia;

    @ColumnInfo(name = "ordenEntrega")
    public Integer ordenEntrega;

    @ColumnInfo(name = "tipoDocumento")
    private short tipoDocumento;     //tipo documento

    @ColumnInfo(name = "numeroDocumento")
    private String numeroDocumento;     //numero documento

    @ColumnInfo(name = "codPersona")
    public Integer codPersona;

    @ColumnInfo(name = "flag")
    public int flag;

    public EntregaEntity(Integer ntraPuntoEntrega, String coordenadaX, String coordenadaY, String direccion, String referencia, Integer ordenEntrega, short tipoDocumento, String numeroDocumento, Integer codPersona, int flag) {
        this.ntraPuntoEntrega = ntraPuntoEntrega;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.direccion = direccion;
        this.referencia = referencia;
        this.ordenEntrega = ordenEntrega;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.codPersona = codPersona;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNtraPuntoEntrega() {
        return ntraPuntoEntrega;
    }

    public void setNtraPuntoEntrega(Integer ntraPuntoEntrega) {
        this.ntraPuntoEntrega = ntraPuntoEntrega;
    }

    public String getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public String getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getOrdenEntrega() {
        return ordenEntrega;
    }

    public void setOrdenEntrega(Integer ordenEntrega) {
        this.ordenEntrega = ordenEntrega;
    }

    public short getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(short tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(Integer codPersona) {
        this.codPersona = codPersona;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
