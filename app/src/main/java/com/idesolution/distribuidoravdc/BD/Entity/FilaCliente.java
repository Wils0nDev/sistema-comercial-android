package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class FilaCliente {
    @ColumnInfo(name = "ntra")
    private Integer ntra; //numero de transaccion
    @ColumnInfo(name = "codCliente")
    private Integer codCliente; //codigo cliente
    @ColumnInfo(name = "tipoPersona")
    private short tipoPersona; //tipo documento
    @ColumnInfo(name = "tipoDocumento")
    private short tipoDocumento; //tipo documento
    @ColumnInfo(name = "numeroDocumento")
    private String numeroDocumento; //numero documento
    @ColumnInfo(name = "ruc")
    private String ruc; //ruc
    @ColumnInfo(name = "razonSocial")
    private String razonSocial; //razon social
    @ColumnInfo(name = "nombres")
    private String nombres; //nombres
    @ColumnInfo(name = "apellidoPaterno")
    private String apellidoPaterno; //apellido paterno
    @ColumnInfo(name = "apellidoMaterno")
    private String apellidoMaterno; //apellido materno
    @ColumnInfo(name = "flag")
    private Integer flag; //flag

    public FilaCliente(Integer ntra, @NonNull Integer codCliente, short tipoDocumento, String numeroDocumento, String ruc, String razonSocial, String nombres, String apellidoPaterno, String apellidoMaterno, Integer flag) {
        this.ntra = ntra;
        this.codCliente = codCliente;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.flag = flag;
    }

    public short getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(short tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Integer getNtra() {
        return ntra;
    }

    public void setNtra(Integer ntra) {
        this.ntra = ntra;
    }

    @NonNull
    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(@NonNull Integer codCliente) {
        this.codCliente = codCliente;
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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
