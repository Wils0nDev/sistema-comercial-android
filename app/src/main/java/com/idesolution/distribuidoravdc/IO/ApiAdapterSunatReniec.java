package com.idesolution.distribuidoravdc.IO;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapterSunatReniec {
    private static InterfazApiService API_SERVICE_SR;

    public static InterfazApiService getApiService() {

        // Creamos un interceptor y le indicamos el log level a usar
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //String baseUrl = "http://app01.ide-solution.com:8181/ws_wa_consulta_sunat_reniec/";
        String baseUrl = "http://app01.ide-solution.com:8181/ws_wa_consulta_sunat_reniec_qa/";
        if (API_SERVICE_SR == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // <-- usamos el log level
                    .build();
            API_SERVICE_SR = retrofit.create(InterfazApiService.class);
        }

        return API_SERVICE_SR;
    }
}
