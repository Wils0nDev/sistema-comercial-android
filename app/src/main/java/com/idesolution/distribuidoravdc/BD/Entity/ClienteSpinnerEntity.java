package com.idesolution.distribuidoravdc.BD.Entity;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class ClienteSpinnerEntity {

    @ColumnInfo(name = "ruc")
    private String ruc; //ruc


    @ColumnInfo(name = "numeroDocumento")
    private String numeroDocumento; //codigo cliente


    @ColumnInfo(name = "nombreCompleto")
    private String nombreCompleto; //nombres

    @ColumnInfo(name = "razonSocial")
    private String razonSocial; //razon social

    public ClienteSpinnerEntity(String ruc, String razonSocial,  String numeroDocumento,  String nombreCompleto) {

        this.numeroDocumento = numeroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.ruc = ruc;
        this.razonSocial = razonSocial;


    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }


    public void setNumeroDocumento( String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public void setNombreCompleto(String nombres) {
        this.nombreCompleto = nombreCompleto;
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





}
