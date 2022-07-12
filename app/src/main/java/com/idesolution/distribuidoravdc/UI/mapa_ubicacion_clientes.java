package com.idesolution.distribuidoravdc.UI;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.ui.IconGenerator;
import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionClienteEntity;
import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionEntity;
import com.idesolution.distribuidoravdc.BD.Repository.LocalizacionViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.VendedorViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class mapa_ubicacion_clientes extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {

    private GoogleMap mMap;
    MarkerOptions markerOptions = new MarkerOptions();
    Marker mCurrLocationMarker;
    List<Address> addresses = null;
    Geocoder geocoder  = null;
    String errorMessage = "";
    LocalizacionViewModel locaVM;
    private static final String TAG = "error geocoder" ;
    double latitudx, longitudy;
    String direc = "";
    String snipet = "";
    private ArrayList<String> listaCodigoCliente;
    private ArrayList<String> listaNombreCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_ubicacion_clientes);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapUbicliente);
        mapFragment.getMapAsync(this);
        obtenerCoordenadas();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(this);

  }

    private void obtenerCoordenadas(){

        locaVM = new ViewModelProvider(this).get(LocalizacionViewModel.class);
        locaVM.obtenerCoordenadas().observe(this, loClientes -> {
            if(loClientes != null){
                addItems(loClientes);
            }

        });

    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        Context context = getApplicationContext(); //or getActivity(), YourActivity.this, etc.

        LinearLayout info = new LinearLayout(context);
        info.setOrientation(LinearLayout.VERTICAL);

        TextView title = new TextView(context);
        title.setTextColor(Color.BLACK);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(null, Typeface.BOLD);
        title.setText(marker.getTitle());

        TextView snippet = new TextView(context);
        snippet.setTextColor(Color.GRAY);
        snippet.setText(marker.getSnippet());

        info.addView(title);
        info.addView(snippet);

        return info;
    }



    private void addItems(List<LocalizacionClienteEntity>loClientes) {
        double lat;
        double lng;
        float zoomLevel = 17.0f;
        LatLng PERTH;


        for (LocalizacionClienteEntity loc: loClientes) {
            //double offset = i / 60d;
            lat = Double.parseDouble(loc.getCoordenadaX());
            lng = Double.parseDouble(loc.getCoordenadaY());
            PERTH = new LatLng(lat, lng);
            markerOptions.position(PERTH);
            direc = getCompleteAddressString(lat, lng);
             markerOptions.title(loc.getNombreCompleto());
            //snipet = "latitud: " + lat + "\n" + "longitud: " + lng;
            markerOptions.snippet(direc);
            mCurrLocationMarker = mMap.addMarker(markerOptions);
            mCurrLocationMarker.showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PERTH, zoomLevel));
        }
    }




    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Integer conexion = MetodosGlobales.verificarConexionInternet(getApplicationContext());
        if(conexion != 0) {
            while ( addresses == null) {
                geocoder = new Geocoder(this, Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
                    if (addresses != null) {
                        Address returnedAddress = addresses.get(0);
                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        StringBuilder strReturnedAddress = new StringBuilder("");
                        for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                            strReturnedAddress.append(returnedAddress.getAddressLine(i));
                        }
                        strAdd = strReturnedAddress.toString();
                        Log.w("MyCurrentctionaddress", "" + strReturnedAddress.toString());
                    } else {
                        Log.w("MyCurrentloctionaddress", "NoAddress returned!");
                    }

                } catch (IOException ioException) {
                    errorMessage = Constante.service_not_available;
                    Log.e(TAG, errorMessage, ioException);
                } catch (IllegalArgumentException illegalArgumentException) {
                    errorMessage = Constante.invalid_lat_long_used;
                    Log.e(TAG, errorMessage + ". " +
                            "Latitude = " + LATITUDE +
                            ", Longitude = " +
                            LONGITUDE, illegalArgumentException);
                }
            }
        }
        addresses = null;
        return strAdd;
    }



}
