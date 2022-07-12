package com.idesolution.distribuidoravdc.IO;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {

    private static InterfazApiService API_SERVICE;

    public static InterfazApiService getApiService() {

        // Creamos un interceptor y le indicamos el log level a usar
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //String baseUrl = "http://iis10.ide-solution.com/ws_wa_DistribuidoraVDC_Alex/";
        //String baseUrl = "http://app01.ide-solution.com:8181/Virgen_del_Carmen_ws_qa/";
        //String baseUrl = "http://app01.ide-solution.com:8181/ws_wa_DistribuidoraVDC_WV/";
        //String baseUrl = "http://190.117.119.193/ws_wa_DistribuidoraVDC/";
        //String baseUrl = "http://app01.ide-solution.com:8181/ws_wa_DistribuidoraVDC/";
        //String baseUrl = "http://app01.ide-solution.com:8181/ws_wa_DistribuidoraVDC_wsanchez/";
        String baseUrl = "http://app01.ide-solution.com:8181/ws_wa_DistribuidoraVDC_qa/";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // <-- usamos el log level
                    .build();
            API_SERVICE = retrofit.create(InterfazApiService.class);
        }

        return API_SERVICE;
    }
}
