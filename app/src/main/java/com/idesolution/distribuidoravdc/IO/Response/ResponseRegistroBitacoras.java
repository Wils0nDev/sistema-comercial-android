package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRegistroBitacoras {

    @SerializedName("ResultadoBitacora")
    @Expose
    private ResultadoBitacora resultadoBitacora;

    @SerializedName("ErrorWebSer")
    @Expose
    private ErrorWebSer errorWebSer;

    public ResultadoBitacora getResultadoBitacora() {
        return resultadoBitacora;
    }

    public void setResultadoBitacora(ResultadoBitacora resultadoBitacora) {
        this.resultadoBitacora = resultadoBitacora;
    }

    public ErrorWebSer getErrorWebSer() {
        return errorWebSer;
    }

    public void setErrorWebSer(ErrorWebSer errorWebSer) {
        this.errorWebSer = errorWebSer;
    }
}
