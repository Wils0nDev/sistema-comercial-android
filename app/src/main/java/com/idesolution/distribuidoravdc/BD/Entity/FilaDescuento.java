package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_descuento_habilitado")
public class FilaDescuento {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;           //serial
    @ColumnInfo(name = "ntra")
    private Integer ntra;         //numero de transaccion de descuento
    //@ColumnInfo(name = "flag")
    //private Integer flag;         //flag mostrar en combo

    public FilaDescuento(Integer ntra) {
        this.ntra = ntra;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public Integer getNtra() {
        return ntra;
    }

    public void setNtra(Integer ntra) {
        this.ntra = ntra;
    }

}
