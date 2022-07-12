package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class respRuAs {

    @SerializedName("codRuta")
    @Expose
    private Integer codRuta;

    public Integer getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(Integer codRuta) {
        this.codRuta = codRuta;
    }
}
