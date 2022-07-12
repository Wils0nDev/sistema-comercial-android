package com.idesolution.distribuidoravdc.IO.Request;

public class RequestClienteSinc {

    public short proceso;        //proceso 1: registro 2:modificacion
    public int codPersona;        //codigo de persona
    public short tipoPersona;     //tipo persona
    public short tipoDocumento ;   //tipo documento
    public String numDocumento ;  //numero documento
    public String ruc;           //ruc
    public String razonSocial ;  //razon social
    public String nombres ;       //nombres
    public String apePaterno ;  //apellido paterno
    public String apeMaterno ; //apellido materno
    public String direccion ;     //direccion
    public String correo ;      //correo
    public String telefono ;  //telefono
    public String celular ;      //celular
    public String ubigeo ;     //ubigeo
    public Integer ordenAtencion ; //orden de atencion
    public Integer perfilCliente ;  //perfil del cliente
    public Integer clasificacion; //clasificacion
    public short frecuencia;    //frecuencia
    public short tipoListaPrecio ;//tipoListaPrecio
    public int codRuta ; //codRuta
    public String coordenadaX; // latitud
    public String coordenadaY; //longitud
    public String usuario ;    //usuario
    public String ip ;         //direccion ip
    public String mac ;           //mac

    public RequestClienteSinc(short proceso, int codPersona, short tipoPersona, short tipoDocumento, String numDocumento, String ruc, String razonSocial, String nombres, String apePaterno, String apeMaterno, String direccion, String correo, String telefono, String celular, String ubigeo, Integer ordenAtencion, Integer perfilCliente, Integer clasificacion, short frecuencia, short tipoListaPrecio, int codRuta,String coordenadaX,String coordenadaY, String usuario, String ip, String mac) {
        this.proceso = proceso;
        this.codPersona = codPersona;
        this.tipoPersona = tipoPersona;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.nombres = nombres;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.celular = celular;
        this.ubigeo = ubigeo;
        this.ordenAtencion = ordenAtencion;
        this.perfilCliente = perfilCliente;
        this.clasificacion = clasificacion;
        this.frecuencia = frecuencia;
        this.tipoListaPrecio = tipoListaPrecio;
        this.codRuta = codRuta;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.usuario = usuario;
        this.ip = ip;
        this.mac = mac;
    }


}



