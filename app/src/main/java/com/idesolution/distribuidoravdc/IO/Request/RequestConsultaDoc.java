package com.idesolution.distribuidoravdc.IO.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestConsultaDoc {
    @SerializedName("numDocumento")
    @Expose
    private String numDocumento;

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }
}
