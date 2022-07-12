package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resultado {

    @SerializedName("reg_mod")
    @Expose
    private RegMod regMod;

    public RegMod getRegMod() {
        return regMod;
    }

    public void setRegMod(RegMod regMod) {
        this.regMod = regMod;
    }
}
