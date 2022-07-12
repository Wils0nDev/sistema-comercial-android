package com.idesolution.distribuidoravdc.Conexion;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MetodosGlobales {

    public static int verificarConexionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return 1; // Si hay conexión a Internet en este momento
        } else {
            // No hay conexión a Internet en este momento
        }
        return 0;
    }

    public static boolean validarNulos(String cadena){
        try{
            if (cadena == null || cadena.length()==0 ){
                return false;
            }
        }catch (Exception e){
            throw  e;
        }
        return true;
    }

    public static void mostrarMensaje(Context context, String cadena){
        Toast.makeText( context, cadena, Toast.LENGTH_SHORT).show();
    }

    public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }

    public static String obtenerFechaActual(){
        Date objDate = new Date();
        String strDateFormat = "dd/MM/yyyy";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        //objSDF.format(objDate).toString();
        return objSDF.format(objDate);
    }

    public static String obtenerFecha(){
        Date objDate = new Date();
        String strDateFormat = "yyyy/MM/dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        //objSDF.format(objDate).toString();
        return objSDF.format(objDate);
    }

    public static boolean like(String str, String expr) {
        expr = expr.toLowerCase(); // ignoring locale for now
        expr = expr.replace(".", "\\."); // "\\" is escaped to "\" (thanks, Alan M)
        // ... escape any other potentially problematic characters here
        expr = expr.replace("?", ".");
        expr = expr.replace("%", ".*");
        str = str.toLowerCase();
        return str.matches(expr);
    }

    public static boolean isConnectedWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isConnectedMobile(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    public static String obtenerIP(Context context) {
        boolean flagW;
        boolean flagD;
        String ip;

        flagW = isConnectedWifi(context);
        flagD = isConnectedMobile(context);

        if (flagW){
            ip = getIPAddressIPv4("wlan");
        }else {
            if(flagD){
                ip = getIPAddressIPv4("rmnet");
            }else{
                ip = "";
            }
        }
        return  ip;
    }

    public static String getIPAddressIPv4(String id) {
        try {

            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (intf.getName().contains(id)) {
                    List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                    for (InetAddress addr : addrs) {

                        if (!addr.isLoopbackAddress()) {
                            String sAddr = addr.getHostAddress();
                            if (addr instanceof Inet4Address) {
                                return sAddr;
                            }
                        }

                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String obtenerMAC(Context context) {
        String mac = "";
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    //return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                mac = res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Log.e("Error", ex.getMessage());
        }
        return mac;
    }

}
