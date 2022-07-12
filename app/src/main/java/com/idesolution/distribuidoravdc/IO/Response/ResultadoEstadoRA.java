package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultadoEstadoRA {

    @SerializedName("respRuAs")
    @Expose
    private respRuAs respRuAs;

    public respRuAs getrespRuAs() {
        return respRuAs;
    }

    public void setrespRuAs(com.idesolution.distribuidoravdc.IO.Response.respRuAs respRuAs) {
        this.respRuAs = respRuAs;
    }
}
