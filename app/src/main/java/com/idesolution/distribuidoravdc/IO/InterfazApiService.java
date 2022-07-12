package com.idesolution.distribuidoravdc.IO;

import androidx.annotation.Nullable;

import com.idesolution.distribuidoravdc.IO.Request.RequestAnularPreventa;
import com.idesolution.distribuidoravdc.IO.Request.RequestConsultaDoc;
import com.idesolution.distribuidoravdc.IO.Request.RequestEstadoAcRA;
import com.idesolution.distribuidoravdc.IO.Request.RequestLogin;
import com.idesolution.distribuidoravdc.IO.Request.RequestPreventa;
import com.idesolution.distribuidoravdc.IO.Request.RequestSesion;
import com.idesolution.distribuidoravdc.IO.Request.RequestSincronizacion;
import com.idesolution.distribuidoravdc.IO.Request.RequestSincronizacionRutas;
import com.idesolution.distribuidoravdc.IO.Request.RequestUbigeo;
import com.idesolution.distribuidoravdc.IO.Response.LoginResponse;
import com.idesolution.distribuidoravdc.IO.Response.ResponseActualizarEstadoRA;
import com.idesolution.distribuidoravdc.IO.Response.ResponseAnularPreventa;
import com.idesolution.distribuidoravdc.IO.Response.ResponseRegistroCliente;
import com.idesolution.distribuidoravdc.IO.Response.ResponseRegistroPreventa;
import com.idesolution.distribuidoravdc.IO.Response.ResponseSesion;
import com.idesolution.distribuidoravdc.IO.Response.ResponseUbigeoSucur;
import com.idesolution.distribuidoravdc.IO.Response.ServSunatReniec.ResponseWsReniec;
import com.idesolution.distribuidoravdc.IO.Response.ServSunatReniec.ResponseWsSunat;
import com.idesolution.distribuidoravdc.IO.Response.ServSunatReniec.ResponseWsSunatService;
import com.idesolution.distribuidoravdc.IO.Response.SincronizacionResponse;
import com.idesolution.distribuidoravdc.IO.Request.ResquestRutasBitacora;
import com.idesolution.distribuidoravdc.IO.Response.ResponseRegistroBitacoras;
import com.idesolution.distribuidoravdc.IO.Response.SucursalResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfazApiService {

    @FormUrlEncoded
    @POST("v1/api/Distribuidora/RegistrarModificarCliente")
    Call<ResponseRegistroCliente> RegistroActualizacionCliente(
            @Field("proceso")
                    short proceso, //proceso 1: registro 2: modificacion
            @Field("codPersona")
                    Integer codPersona, //codigo de persona
            @Field("tipoPersona")
                    short tipoPersona , //tipo persona
            @Field("tipoDocumento")
                    short tipoDocumento, //tipo documento
            @Field("numDocumento")
                    String numDocumento, //numero documento
            @Field("ruc")
                    String ruc, //ruc
            @Field("razonSocial")
                    String razonSocial, //razon social
            @Field("nombres")
                    String nombres, //nombres
            @Field("apePaterno")
                    String apePaterno, //apellido paterno
            @Field("apeMaterno")
                    String apeMaterno, //apellido materno
            @Field("direccion")
                    String direccion, //direccion
            @Field("correo")
                    String correo, //correo
            @Field("telefono")
                    String telefono, //telefono
            @Field("celular")
                    String celular, //celular
            @Field("ubigeo")
                    String ubigeo, //ubigeo
            @Field("ordenAtencion")
                    Integer ordenAtencion, //orden atencion
            @Field("perfilCliente")
                    Integer perfilCliente, //perfil cliente
            @Field("clasificacion")
                    Integer clasificacion, //clasificacion
            @Field("frecuencia")
                    short frecuencia, //frecuencia
            @Field("tipoListaPrecio")
                    short tipoListaPrecio, //tipo de lista precio
            @Field("codRuta")
                    short codRuta, //codigo de ruta
            @Field("coordenadaX")
                    String coordenadaX,
            @Field("coordenadaY")
                    String coordenadaY,
            @Field("usuario")
                    String usuario, //usuario
            @Field("ip")
                    String ip, //ip
            @Field("mac")
                    String mac //mac
    );

    @POST("v1/api/Distribuidora/RegistrarModificarPreventa")
    Call<ResponseRegistroPreventa> registrarPreventa(@Nullable @Body RequestPreventa objPreventa);

    @POST("v1/api/Distribuidora/Sincronizacion")
    //@FormUrlEncoded
    Call<SincronizacionResponse> sincronizacion(
            @Nullable @Body RequestSincronizacion objSincro
    );
    
     //@FormUrlEncoded
    @POST("v1/api/Distribuidora/RegistrarRutasBitacoras")
    Call<ResponseRegistroBitacoras> registrarRutasBitacoras(
                    @Nullable @Body ResquestRutasBitacora objBiRu
            );


    @POST("v1/api/Distribuidora/ActualizarEstadoRutaAsignada")
    Call<ResponseActualizarEstadoRA> actualizarEstadoRA(
            @Nullable @Body RequestEstadoAcRA objAcEsRA
    );

    @POST("v1/api/Distribuidora/SincronizarRutasBitacora")
    Call<ResponseRegistroBitacoras> registrarRutasSinBitacoras(
            @Nullable @Body RequestSincronizacionRutas objBiRu
    );

    @POST("v1/api/Distribuidora/AnularPreventa")
    Call<ResponseAnularPreventa> anularPreventa(@Nullable @Body RequestAnularPreventa objPreventa);

    @POST("v1/api/Distribuidora/ObtenerSucursales")
    Call<SucursalResponse> ObtenerSucursales();

    @POST("v1/api/Distribuidora/ValidarLogin")
    Call<LoginResponse> ConsultaLoginServidor(
            @Nullable @Body RequestLogin objLogin
    );
    @POST("v1/api/Distribuidora/ControlSesioninUsu")
    Call<ResponseSesion> ControlSesionUsu(
            @Nullable @Body RequestSesion requestSesion
    );

    //Consulta de servicio de sunat y reniec
    @POST("v1/api/ConsultaServicios/ConsultaDocumentoSunat")
    Call<ResponseWsSunat> ConsultaDocumentoSunat(
            @Nullable @Body RequestConsultaDoc request
    );

    @POST("v1/api/ConsultaServicios/ConsultaDocumentoReniec")
    Call<ResponseWsReniec> ConsultaDocumentoReniec(
            @Nullable @Body RequestConsultaDoc request
    );

    @POST("v1/api/Distribuidora/ObtenerDataUbigeo")
    Call<ResponseUbigeoSucur> ObtenerDataUbigeo(
            @Nullable @Body RequestUbigeo requestUbigeo
    );

    @POST("v1/api/Distribuidora/ConsultaRUCSunatBD")
    Call<ResponseWsSunatService> ConsultaDocumentoSunatService(
            @Nullable @Body RequestConsultaDoc request
    );

}
