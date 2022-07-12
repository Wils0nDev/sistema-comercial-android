package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorWebSer {
    @SerializedName("TipoErr")
    @Expose
    private Integer tipoErr;
    @SerializedName("CodigoErr")
    @Expose
    private Integer codigoErr;
    @SerializedName("DescripcionErr")
    @Expose
    private String descripcionErr;

    public Integer getTipoErr() {
        return tipoErr;
    }

    public Integer getCodigoErr() {
        return codigoErr;
    }

    public String getDescripcionErr() {
        return descripcionErr;
    }

    public void setTipoErr(Integer tipoErr) {
        this.tipoErr = tipoErr;
    }

    public void setCodigoErr(Integer codigoErr) {
        this.codigoErr = codigoErr;
    }

    public void setDescripcionErr(String descripcionErr) {
        this.descripcionErr = descripcionErr;
    }
}
