package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultadoRegPreventa {

    @SerializedName("rp")
    @Expose
    private rp rp;

    public com.idesolution.distribuidoravdc.IO.Response.rp getRp() {
        return rp;
    }

    public void setRp(com.idesolution.distribuidoravdc.IO.Response.rp rp) {
        this.rp = rp;
    }
}
