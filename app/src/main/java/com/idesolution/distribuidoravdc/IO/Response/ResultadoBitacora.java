package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultadoBitacora {


        @SerializedName("respBiRu")
        @Expose
        private respBiRu respBiRu;

        public respBiRu getRespBiRu() {
            return respBiRu;
        }

        public void setRespBiRu(com.idesolution.distribuidoravdc.IO.Response.respBiRu respBiRu) {
            this.respBiRu = respBiRu;
        }

}
