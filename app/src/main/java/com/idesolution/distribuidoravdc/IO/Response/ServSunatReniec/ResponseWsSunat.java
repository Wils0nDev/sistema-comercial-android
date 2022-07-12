
package com.idesolution.distribuidoravdc.IO.Response.ServSunatReniec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.idesolution.distribuidoravdc.IO.Response.ErrorWebSer;

public class ResponseWsSunat {

    @SerializedName("respuestaWsSunat")
    @Expose
    private RespuestaWsSunat respuestaWsSunat;
    @SerializedName("ErrorWebSer")
    @Expose
    private ErrorWebSer errorWebSer;

    public RespuestaWsSunat getRespuestaWsSunat() {
        return respuestaWsSunat;
    }

    public void setRespuestaWsSunat(RespuestaWsSunat respuestaWsSunat) {
        this.respuestaWsSunat = respuestaWsSunat;
    }

    public ErrorWebSer getErrorWebSer() {
        return errorWebSer;
    }

    public void setErrorWebSer(ErrorWebSer errorWebSer) {
        this.errorWebSer = errorWebSer;
    }

}
