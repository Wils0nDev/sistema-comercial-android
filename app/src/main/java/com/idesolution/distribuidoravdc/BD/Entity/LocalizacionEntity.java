package com.idesolution.distribuidoravdc.BD.Entity;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_localizacion")
public class LocalizacionEntity {

            @NonNull
            @PrimaryKey
            @ColumnInfo(name = "ntra")
            private Integer ntra; //numero de transaccion    @ColumnInfo(name = "codCliente")
            @NonNull
            @ColumnInfo(name = "codPersona")
            private Integer codPersona;
            @ColumnInfo(name = "numeroDocumento")
            private String numeroDocumento; //numero document
            @NonNull
            @ColumnInfo(name = "nombreCompleto")
            private String nombreCompleto; //nombre o razonSocial
            @NonNull
            @ColumnInfo(name = "coordenadaX")
            private  String coordenadaX;
            @NonNull
            @ColumnInfo(name = "coordenadaY")
            private  String coordenadaY;

            @ColumnInfo(name = "flag")
            private Integer flag; //flag de sincronizacion

            public LocalizacionEntity(@NonNull Integer codPersona, String numeroDocumento,String nombreCompleto, @NonNull String coordenadaX, @NonNull String coordenadaY, Integer flag ){

                this.codPersona = codPersona;
                this.numeroDocumento = numeroDocumento;
                this.nombreCompleto = nombreCompleto;
                this.coordenadaX = coordenadaX;
                this.coordenadaY = coordenadaY;
                this.flag = flag;
            }

    @NonNull
    public Integer getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(@NonNull Integer codPersona) {
        this.codPersona = codPersona;
    }

    @NonNull
    public String getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(@NonNull String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    @NonNull
    public String getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(@NonNull String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @NonNull
    public Integer getNtra() {
        return ntra;
    }

    public void setNtra(@NonNull Integer ntra) {
        this.ntra = ntra;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @NonNull
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(@NonNull String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
