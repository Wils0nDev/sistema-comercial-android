
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultadoSincro {

    @SerializedName("data_sincronizacion")
    @Expose
    private DataSincronizacion dataSincronizacion;

    public DataSincronizacion getDataSincronizacion() {
        return dataSincronizacion;
    }

    public void setDataSincronizacion(DataSincronizacion dataSincronizacion) {
        this.dataSincronizacion = dataSincronizacion;
    }

}
