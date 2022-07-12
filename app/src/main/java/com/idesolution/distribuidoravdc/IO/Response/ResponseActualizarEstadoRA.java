package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseActualizarEstadoRA {


    @SerializedName("ResultadoEstadoRA")
    @Expose
    private ResultadoEstadoRA resultadoAcEsRA;

    @SerializedName("ErrorWebSer")
    @Expose
    private ErrorWebSer errorWebSer;

    public ResultadoEstadoRA getResultadoAcEsRA() {
        return resultadoAcEsRA;
    }

    public void setResultadoAcEsRA(ResultadoEstadoRA resultadoAcEsRA) {
        this.resultadoAcEsRA = resultadoAcEsRA;
    }

    public ErrorWebSer getErrorWebSer() {
        return errorWebSer;
    }

    public void setErrorWebSer(ErrorWebSer errorWebSer) {
        this.errorWebSer = errorWebSer;
    }
}
