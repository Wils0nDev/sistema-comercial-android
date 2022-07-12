package com.idesolution.distribuidoravdc.IO.Request;

import java.util.ArrayList;

public class RequestSincronizacionRutas {

    public ArrayList<ResquestRutasBitacora> listaBitacoras;
    //CONSTRUCTOR

    public RequestSincronizacionRutas(ArrayList<ResquestRutasBitacora> listaBitacoras) {

        this.listaBitacoras = listaBitacoras;
    }

    //GETTER AND SETTER

    public ArrayList<ResquestRutasBitacora> getListRutasBitacoras() {
        return listaBitacoras;
    }

    public void setListRutasBitacoras(ArrayList<ResquestRutasBitacora> listaBitacoras) {
        this.listaBitacoras = listaBitacoras;
    }


}
