
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SincronizacionResponse {

    @SerializedName("ResultadoSincro")
    @Expose
    private ResultadoSincro resultado;
    @SerializedName("ErrorWebSer")
    @Expose
    private ErrorWebSer errorWebSer;

    public ResultadoSincro getResultadoSincro() {
        return resultado;
    }

    public void setResultadoSincro(ResultadoSincro resultado) {
        this.resultado = resultado;
    }

    public ErrorWebSer getErrorWebSer() {
        return errorWebSer;
    }

    public void setErrorWebSer(ErrorWebSer errorWebSer) {
        this.errorWebSer = errorWebSer;
    }

}
