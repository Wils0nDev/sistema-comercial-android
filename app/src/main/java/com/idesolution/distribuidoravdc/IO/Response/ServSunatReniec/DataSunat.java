
package com.idesolution.distribuidoravdc.IO.Response.ServSunatReniec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSunat {

    @SerializedName("RUC")
    @Expose
    private String rUC;
    @SerializedName("razonSocial")
    @Expose
    private String razonSocial;
    @SerializedName("estCont")
    @Expose
    private String estCont;
    @SerializedName("condDom")
    @Expose
    private String condDom;
    @SerializedName("ubigeo")
    @Expose
    private String ubigeo;
    @SerializedName("tipVia")
    @Expose
    private String tipVia;
    @SerializedName("nomVia")
    @Expose
    private String nomVia;
    @SerializedName("codZona")
    @Expose
    private String codZona;
    @SerializedName("nomZona")
    @Expose
    private String nomZona;
    @SerializedName("numero")
    @Expose
    private String numero;
    @SerializedName("interior")
    @Expose
    private String interior;
    @SerializedName("lote")
    @Expose
    private String lote;
    @SerializedName("departamento")
    @Expose
    private String departamento;
    @SerializedName("manzana")
    @Expose
    private String manzana;
    @SerializedName("kilometro")
    @Expose
    private String kilometro;
    @SerializedName("direccionFizcal")
    @Expose
    private String direccionFizcal;

    public String getRUC() {
        return rUC;
    }

    public void setRUC(String rUC) {
        this.rUC = rUC;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getEstCont() {
        return estCont;
    }

    public void setEstCont(String estCont) {
        this.estCont = estCont;
    }

    public String getCondDom() {
        return condDom;
    }

    public void setCondDom(String condDom) {
        this.condDom = condDom;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getTipVia() {
        return tipVia;
    }

    public void setTipVia(String tipVia) {
        this.tipVia = tipVia;
    }

    public String getNomVia() {
        return nomVia;
    }

    public void setNomVia(String nomVia) {
        this.nomVia = nomVia;
    }

    public String getCodZona() {
        return codZona;
    }

    public void setCodZona(String codZona) {
        this.codZona = codZona;
    }

    public String getNomZona() {
        return nomZona;
    }

    public void setNomZona(String nomZona) {
        this.nomZona = nomZona;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getKilometro() {
        return kilometro;
    }

    public void setKilometro(String kilometro) {
        this.kilometro = kilometro;
    }

    public String getDireccionFizcal() {
        return direccionFizcal;
    }

    public void setDireccionFizcal(String direccionFizcal) {
        this.direccionFizcal = direccionFizcal;
    }

}
