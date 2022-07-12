package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegMod {
    @SerializedName("codCliente")
    @Expose
    private Integer codCliente;
    @SerializedName("codPuntoEntrega")
    @Expose
    private Integer codPuntoEntrega;

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public Integer getCodPuntoEntrega() {
        return codPuntoEntrega;
    }

    public void setCodPuntoEntrega(Integer codPuntoEntrega) {
        this.codPuntoEntrega = codPuntoEntrega;
    }
}
