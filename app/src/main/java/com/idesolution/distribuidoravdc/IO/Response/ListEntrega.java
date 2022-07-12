
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListEntrega {

    @SerializedName("ntraPuntoEntrega")
    @Expose
    private Integer ntraPuntoEntrega;
    @SerializedName("coordenadaX")
    @Expose
    private String coordenadaX;
    @SerializedName("coordenadaY")
    @Expose
    private String coordenadaY;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("referencia")
    @Expose
    private String referencia;
    @SerializedName("ordenEntrega")
    @Expose
    private Integer ordenEntrega;
    @SerializedName("tipoDocumento")
    private short tipoDocumento;     //numero documento
    @SerializedName("numeroDocumento")
    private String numeroDocumento;     //numero documento
    @SerializedName("codPersona")
    @Expose
    private Integer codPersona;
    @SerializedName("flag")
    public int flag;

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

    public Integer getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(Integer codPersona) {
        this.codPersona = codPersona;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
