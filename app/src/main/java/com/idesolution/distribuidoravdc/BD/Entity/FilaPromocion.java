package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_promocion_habilitada")
public class FilaPromocion {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;           //serial
    @ColumnInfo(name = "ntra")
    private Integer ntra;         //numero de transaccion de promocion
    @ColumnInfo(name = "flag")
    private Integer flag;         //flag mostrar en combo

    public FilaPromocion(Integer ntra, Integer flag) {
        this.ntra = ntra;
        this.flag = flag;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
