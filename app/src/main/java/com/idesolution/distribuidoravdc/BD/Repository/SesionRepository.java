package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.SessionManager;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.IO.ApiAdapter;
import com.idesolution.distribuidoravdc.IO.Request.RequestSesion;
import com.idesolution.distribuidoravdc.IO.Response.ResponseSesion;
import com.idesolution.distribuidoravdc.UI.registro_cliente;
import com.idesolution.distribuidoravdc.UI.registro_preventa;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;

//import es.dmoral.toasty.Toasty;

public class SesionRepository {
    ProgressDialog progressDialog;
    SweetAlertDialog pDialog;
    SessionManager sessionManager;
    SesionRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
    }
    public void RegistrarSesionInicio(RequestSesion requestSesion, int tipo, Context context) {
        progressDialog = new ProgressDialog(context);
        sessionManager = new SessionManager(context);
        Call<ResponseSesion> call = ApiAdapter.getApiService().ControlSesionUsu(requestSesion);
        call.enqueue(new Callback<ResponseSesion>() {
            @Override
            public void onResponse(Call<ResponseSesion> call, Response<ResponseSesion> response) {
                if(response.isSuccessful()){
                    ResponseSesion rb = response.body();
                    if(rb.getErrorWebSer().getCodigoErr() == Constante.g_const_2000 ) {
                        if(tipo == Constante.g_const_1){
                            progressDialog.dismiss();
                            sessionManager.logout();
                            //((principal)context).finish();
                        }
                        if(tipo == Constante.g_const_3){
                            progressDialog.dismiss();
                            sessionManager.logout();
                        }
                        if(tipo == Constante.g_const_4){
                            Constante.g_global_clie = 1;
                            sessionManager.logoutConexion();
                            Intent i = ((registro_cliente) context).getIntent();
                            i.putExtra("RESULTADO_CLIENTE",1);
                            ((registro_cliente) context).setResult(RESULT_CANCELED,i);
                            ((registro_cliente) context).finish();

                        }
                        if(tipo == Constante.g_const_5){
                            Constante.g_global_clie = 1;
                            sessionManager.logoutConexion();
                            //Intent i = ((registro_preventa) context).getIntent();
                            //i.putExtra("RESULTADO_CLIENTE",1);
                            //((registro_cliente) context).setResult(RESULT_CANCELED,i);
                            ((registro_preventa) context).finish();

                        }

                    }
                    else{
                        // Toast.makeText(MainActivity.this, rb.getDescripcionResp(), Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    //Toasty.error(context, Constante.g_const_error_conexion, Toast.LENGTH_SHORT, true).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseSesion> call, Throwable t) {
                // Toast.makeText(getApplicationContext(), Constante.g_const_error_conexion, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
