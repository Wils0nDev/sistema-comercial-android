package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRegistroPreventa {
    @SerializedName("ResultadoRegPreventa")
    @Expose
    private ResultadoRegPreventa resultado;
    @SerializedName("ErrorWebSer")
    @Expose
    private ErrorWebSer errorWebSer;

    public ResultadoRegPreventa getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoRegPreventa resultado) {
        this.resultado = resultado;
    }

    public ErrorWebSer getErrorWebSer() {
        return errorWebSer;
    }

    public void setErrorWebSer(ErrorWebSer errorWebSer) {
        this.errorWebSer = errorWebSer;
    }
}
