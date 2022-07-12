package com.idesolution.distribuidoravdc.BD.Dao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.Entidad.LoginLocal;
import com.idesolution.distribuidoravdc.MainActivity;
import com.idesolution.distribuidoravdc.UI.principal;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String ID = "ID";
    public static final String UBIGEO = "UBIGEO";
    public static final String SUCURSAL = "SUCURSAL";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(int idUsuario,String codUbigeo,int codSucursal){
        editor.putBoolean(LOGIN, true);
        editor.putInt(ID, idUsuario);
        editor.putString(UBIGEO, codUbigeo);
        editor.putInt(SUCURSAL, codSucursal);
        editor.apply();

    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){

        if (!this.isLoggin()){
            //Intent i = new Intent(context, MainActivity.class);
            //context.startActivity(i);
            ((principal) context).finish();
        }
    }

    public void LogueoActivo(){
        if (this.isLoggin()){
            Intent i = new Intent(context, principal.class);
            context.startActivity(i);
        }

    }

    public LoginLocal getUserDetail(){
        LoginLocal loginLocal = new LoginLocal();
        loginLocal.setNtraUsuario(sharedPreferences.getInt(ID, Constante.g_const_0));
        loginLocal.setCodUbigeo(sharedPreferences.getString(UBIGEO, Constante.G_CONST_VACIO));
        loginLocal.setCodSucursal(sharedPreferences.getInt(SUCURSAL,Constante.g_const_0));

        return loginLocal;
    }

    public void logout(){

        editor.clear();
        editor.commit();
        //Intent i = new Intent(context, MainActivity.class);
        ((principal) context).finish();
        //context.startActivity(i);
    }

    public void logoutCliente(){

        editor.clear();
        editor.commit();
        //((principal) context).finish();
        Intent intentNuevoCliente = new Intent(context, MainActivity.class);
        context.startActivity(intentNuevoCliente);


    }

    public void logoutConexion(){
        editor.clear();
        editor.commit();
    }
}
