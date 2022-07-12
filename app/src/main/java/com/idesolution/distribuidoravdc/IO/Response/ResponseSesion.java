package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSesion {
    @SerializedName("DescripcionResp")
    @Expose
    private String descripcionResp;
    @SerializedName("ErrorWebSer")
    @Expose
    private ErrorWebSer errorWebSer;

    public String getDescripcionResp() {
        return descripcionResp;
    }

    public void setDescripcionResp(String descripcionResp) {
        this.descripcionResp = descripcionResp;
    }

    public ErrorWebSer getErrorWebSer() {
        return errorWebSer;
    }

    public void setErrorWebSer(ErrorWebSer errorWebSer) {
        this.errorWebSer = errorWebSer;
    }
}
