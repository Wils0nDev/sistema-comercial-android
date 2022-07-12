
package com.idesolution.distribuidoravdc.IO.Response.ServSunatReniec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.idesolution.distribuidoravdc.IO.Response.ErrorWebSer;

public class ResponseWsSunatService {

    @SerializedName("dataSunat")
    @Expose
    private DataSunat dataSunat;
    @SerializedName("ErrorWebSer")
    @Expose
    private ErrorWebSer errorWebSer;

    public DataSunat getDataSunat() {
        return dataSunat;
    }

    public void setDataSunat(DataSunat dataSunat) {
        this.dataSunat = dataSunat;
    }

    public ErrorWebSer getErrorWebSer() {
        return errorWebSer;
    }

    public void setErrorWebSer(ErrorWebSer errorWebSer) {
        this.errorWebSer = errorWebSer;
    }

}
