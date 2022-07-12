package com.idesolution.distribuidoravdc.BD.Entity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity (tableName = "table_bitacoraVendedor")
public class BitacoraVendedorEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ntra")
    private Integer ntra; //numero de transaccion

    @NonNull
    @ColumnInfo(name = "codRuta")
    private Integer codRuta;

    @NonNull
    @ColumnInfo(name = "codCliente")
    private String codCliente;

    @NonNull
    @ColumnInfo(name = "fecha")
    private String fecha;


    @ColumnInfo(name = "horaFinal")
    private String horaFinal;

    @NonNull
    @ColumnInfo(name = "visita")
    private Integer visita;

    @ColumnInfo(name = "motivo")
    private String motivo;

    @ColumnInfo(name = "usuario")
    private  String usuario;

    @ColumnInfo(name = "ip")
    private String ip;

    @ColumnInfo(name = "mac")
    private String mac;

    @NonNull
    @ColumnInfo(name = "cordenadaX")
    private String cordenadaX;

    @NonNull
    @ColumnInfo(name = "cordenadaY")
    private String CordenadaY;

    @NonNull
    @ColumnInfo(name = "estado")
    private Integer estado;

    @NonNull
    @ColumnInfo(name = "internet")
    private Integer internet;

    public BitacoraVendedorEntity(@NonNull Integer codRuta, @NonNull String codCliente,@NonNull String fecha, String horaFinal, @NonNull Integer visita,String motivo, String usuario, String ip, String mac,
                                  @NonNull String cordenadaX,  @NonNull String CordenadaY,@NonNull Integer estado, Integer internet){

        this.codRuta = codRuta;
        this.codCliente = codCliente;
        this.fecha = fecha;
        this.horaFinal = horaFinal;
        this.visita = visita;
        this.motivo = motivo;
        this.usuario = usuario;
        this.ip = ip;
        this.mac = mac;
        this.cordenadaX = cordenadaX;
        this.CordenadaY = CordenadaY;
        this.estado = estado;
        this.internet = internet;


    }


    @NonNull
    public Integer getNtra() {
        return ntra;
    }

    public void setNtra(@NonNull Integer ntra) {
        this.ntra = ntra;
    }

    @NonNull
    public Integer getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(@NonNull Integer codRuta) {
        this.codRuta = codRuta;
    }

    @NonNull
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(@NonNull Integer estado) {
        this.estado = estado;
    }

    @NonNull
    public String getFecha() {
        return fecha;
    }

    public void setFecha(@NonNull String fecha) {
        this.fecha = fecha;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    @NonNull
    public Integer getVisita() {
        return visita;
    }

    public void setVisita(@NonNull Integer visita) {
        this.visita = visita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @NonNull
    public String getCordenadaX() {
        return cordenadaX;
    }

    public void setCordenadaX( String cordenadaX) {
        this.cordenadaX = cordenadaX;
    }

    @NonNull
    public String getCordenadaY() {
        return CordenadaY;
    }

    public void setCordenadaY( String cordenadaY) {
        CordenadaY = cordenadaY;
    }

    @NonNull
    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(@NonNull String codCliente) {
        this.codCliente = codCliente;
    }

    @NonNull
    public Integer getInternet() {
        return internet;
    }

    public void setInternet(@NonNull Integer internet) {
        this.internet = internet;
    }
}
