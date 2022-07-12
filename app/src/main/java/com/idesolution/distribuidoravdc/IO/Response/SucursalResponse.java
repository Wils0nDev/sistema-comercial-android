
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SucursalResponse {

    @SerializedName("ResultadoSucursal")
    @Expose
    private ResultadoSucursal resultadoSucursal;
    @SerializedName("ErrorWebSer")
    @Expose
    private ErrorWebSer errorWebSer;

    public ResultadoSucursal getResultadoSucursal() {
        return resultadoSucursal;
    }

    public void setResultadoSucursal(ResultadoSucursal resultadoSucursal) {
        this.resultadoSucursal = resultadoSucursal;
    }

    public ErrorWebSer getErrorWebSer() {
        return errorWebSer;
    }

    public void setErrorWebSer(ErrorWebSer errorWebSer) {
        this.errorWebSer = errorWebSer;
    }

}
