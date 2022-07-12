package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class rc {
    @SerializedName("codCliente")
    @Expose
    private Integer codCliente;

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }
}
