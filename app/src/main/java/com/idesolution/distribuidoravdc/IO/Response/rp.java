package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class rp {
    @SerializedName("ntraPreventa")
    @Expose
    private Integer ntraPreventa;

    @SerializedName("codPersona")
    @Expose
    private Integer codPersona;

    @SerializedName("ntraPuntoEntrega")
    @Expose
    private Integer ntraPuntoEntrega;

    public Integer getNtraPreventa() {
        return ntraPreventa;
    }

    public void setNtraPreventa(Integer ntraPreventa) {
        this.ntraPreventa = ntraPreventa;
    }

    public Integer getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(Integer codPersona) {
        this.codPersona = codPersona;
    }

    public Integer getNtraPuntoEntrega() {
        return ntraPuntoEntrega;
    }

    public void setNtraPuntoEntrega(Integer ntraPuntoEntrega) {
        this.ntraPuntoEntrega = ntraPuntoEntrega;
    }
}
