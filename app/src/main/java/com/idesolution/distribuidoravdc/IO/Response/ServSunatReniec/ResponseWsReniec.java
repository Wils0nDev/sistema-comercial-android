
package com.idesolution.distribuidoravdc.IO.Response.ServSunatReniec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.idesolution.distribuidoravdc.IO.Response.ErrorWebSer;

public class ResponseWsReniec {

    @SerializedName("respuestaWsReniec")
    @Expose
    private RespuestaWsReniec respuestaWsReniec;
    @SerializedName("ErrorWebSer")
    @Expose
    private ErrorWebSer errorWebSer;

    public RespuestaWsReniec getRespuestaWsReniec() {
        return respuestaWsReniec;
    }

    public void setRespuestaWsReniec(RespuestaWsReniec respuestaWsReniec) {
        this.respuestaWsReniec = respuestaWsReniec;
    }

    public ErrorWebSer getErrorWebSer() {
        return errorWebSer;
    }

    public void setErrorWebSer(ErrorWebSer errorWebSer) {
        this.errorWebSer = errorWebSer;
    }

}
