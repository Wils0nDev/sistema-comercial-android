package com.idesolution.distribuidoravdc.UI;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.R;
import com.idesolution.distribuidoravdc.Entidad.Constante;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class map_cliente extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, View.OnClickListener, GoogleMap.OnMapClickListener{

    private static final String TAG = "error geocoder" ;
    private GoogleMap mMap;
    Marker mCurrLocationMarker;
    Location mLastLocation;
    MarkerOptions markerOptions = new MarkerOptions();
    private EditText edit_busquedaDir;
    private FloatingActionButton btn_busquedaDir;
    private FloatingActionButton btn_ubicacion;
    private FloatingActionButton btn_save_dir;
    private int entrar = 0;
    private  int entrarG = 0;
    private FloatingActionButton btn_guardarDirec;

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 metros
    private static final long MIN_TIME_BW_UPDATES = 1000 * 10 * 1; // 1 minuto
    private FusedLocationProviderClient fusedLocationClient;
    private Double  latitud, longitud;
    Geocoder geocoder  = null;
    List<Address> addresses = null;
    String errorMessage = "";
    String  direc = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_cliente);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);


        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        edit_busquedaDir =  (EditText) findViewById(R.id.busquedaDireccion);
        btn_busquedaDir  = (FloatingActionButton) findViewById(R.id.btnbusquedaDireccion);
        btn_ubicacion = (FloatingActionButton) findViewById(R.id.btnUbicacion_client);
       // btn_save_dir = (FloatingActionButton) findViewById(R.id.btnGuardarDirec);
        btn_guardarDirec =  (FloatingActionButton) findViewById(R.id.btnGuardarDirec);
        btn_busquedaDir.setOnClickListener(this);
        btn_ubicacion.setOnClickListener(this);
        btn_guardarDirec.setOnClickListener(this);

        Integer conexion = MetodosGlobales.verificarConexionInternet(getApplicationContext());
        if (conexion == 0){
            locationStart();
        }else{
            obtenerCoordenadas();
        }

    }



    private  void geoLocate(View view) throws IOException{


        String location = edit_busquedaDir.getText().toString();
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location, 1);
        Address address = list.get(0);
        String localy;
        String feature = address.getFeatureName();
        String thoroughfare = address.getThoroughfare();
        String city = address.getSubAdminArea();

        localy = address.getLocality();
        Toast.makeText(this,localy, Toast.LENGTH_LONG).show();

        double lat = address.getLatitude();
        double lng = address.getLongitude();

        mMap.clear();
        String  snipet = "";
        float zoomLevel = 18.0f; //This goes up to 21
        LatLng  PERTH = new LatLng(lat, lng);

        markerOptions.position(PERTH);
        markerOptions.title(getCompleteAddressString(lat,lng));
        snipet = "latitud: "+lat+"\n"+"longitud: "+lng;
        markerOptions.snippet(snipet);
        markerOptions.draggable(true);
        mCurrLocationMarker = mMap.addMarker(markerOptions);
        mCurrLocationMarker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PERTH,zoomLevel));

        latitud = lat;
        longitud = lng;

    }


    private void obtenerCoordenadas() {

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                       /* MetodosGlobales.mostrarMensaje(map_cliente.this, "entra google");*/
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            if (entrarG == 0) {
                                entrarG = 1;
                                if (mCurrLocationMarker != null) {
                                    mCurrLocationMarker.remove();
                                }
                                latitud = location.getLatitude();
                                longitud = location.getLongitude();

                                mMap.clear();
                                String snipet = "";
                                float zoomLevel = 18.0f; //This goes up to 21
                                LatLng PERTH = new LatLng(latitud, longitud);

                                markerOptions.position(PERTH);
                                direc = getCompleteAddressString(location.getLatitude(), location.getLongitude());
                                markerOptions.title(direc);
                                snipet = "latitud: " + location.getLatitude() + "\n" + "longitud: " + location.getLongitude();
                                markerOptions.snippet(snipet);
                                markerOptions.draggable(true);
                                mCurrLocationMarker = mMap.addMarker(markerOptions);
                                mCurrLocationMarker.showInfoWindow();
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PERTH, zoomLevel));
                               // guardarDireccion(location.getLatitude(),location.getLongitude(),direc);
                            }
                        }
                    }
                });
    }


    private void locationStart(){
        // Acquire a reference to the system Location Manager
        if(entrar == 0) {
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            // Define a listener that responds to location updates
            MetodosGlobales.mostrarMensaje(map_cliente.this, "entra1");
            LocationListener locationListener = new LocationListener() {


                public void onLocationChanged(Location location) {


                    MetodosGlobales.mostrarMensaje(map_cliente.this, "entra");
                    entrar = 1;
                    mLastLocation = new Location(location);
                    if (mCurrLocationMarker != null) {
                        mCurrLocationMarker.remove();
                    }

                    if (location != null) {

                        mMap.clear();
                        String snipet = "";
                        float zoomLevel = 18.0f; //This goes up to 21
                        LatLng PERTH = new LatLng(location.getLatitude(), location.getLongitude());

                        latitud = location.getLatitude();
                        longitud = location.getLongitude();

                        markerOptions.position(PERTH);
                        direc = getCompleteAddressString(location.getLatitude(), location.getLongitude());
                        markerOptions.title(direc);
                        snipet = "latitud: " + location.getLatitude() + "\n" + "longitud: " + location.getLongitude();
                        markerOptions.snippet(snipet);
                        markerOptions.draggable(true);
                        mCurrLocationMarker = mMap.addMarker(markerOptions);
                        mCurrLocationMarker.showInfoWindow();
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PERTH, zoomLevel));
                        //guardarDireccion(location.getLatitude(),location.getLongitude(),direc);
                    }


                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {

                }
            };
            int permissionCheck = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            // Register the listener with the Location Manager to receive location updates
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5, 20, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 20, locationListener);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case  R.id.btnbusquedaDireccion:
                try{
                    geoLocate(v);
                }catch (Exception e){
                        MetodosGlobales.mostrarMensaje(this,"No tiene conexion a internet");
                }
                break;
            case  R.id.btnUbicacion_client:

                Integer conexion = MetodosGlobales.verificarConexionInternet(getApplicationContext());
                if(conexion == 0){
                    entrar = 0;
                    locationStart();
                }else{
                    entrarG = 0;
                    obtenerCoordenadas();
                }
                break;

            case R.id.btnGuardarDirec:
                guardarDireccion();
                break;
        }
    }



public  void guardarDireccion(){

          String lat = String.valueOf(latitud);
          String lon = String.valueOf(longitud);
          // String data[] = new String[3];
            ArrayList<String> data = new ArrayList<String>();
            data.add(lat);
            data.add(lon);
            data.add(direc);
            Intent i = getIntent();
            i.putStringArrayListExtra("RESULTADO", data);
            setResult(RESULT_OK,i);
            finish();

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
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
        });

        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnMapClickListener(this);
    }



    @Override
    public void onMapClick(LatLng latLng) {

        mMap.clear();
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        String snipet = "";
        float zoomLevel = 18.0f; //This goes up to 21
        //LatLng PERTH = new LatLng(location.getLatitude(), location.getLongitude());

        markerOptions.position(latLng);
        direc= getCompleteAddressString(latLng.latitude, latLng.longitude);
         markerOptions.title(direc);

        latitud = latLng.latitude;
        longitud = latLng.longitude;

        snipet = "latitud: " + latLng.latitude + "\n" + "longitud: " + latLng.longitude;
        markerOptions.snippet(snipet);
        markerOptions.draggable(true);
        mCurrLocationMarker = mMap.addMarker(markerOptions);
        mCurrLocationMarker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
        //guardarDireccion(latLng.latitude, latLng.longitude,direc);

    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    public  void onMarkerDragEnd (Marker marker){
        mMap.clear();
        LatLng position=marker.getPosition();
        // Remove Old Marker
        mCurrLocationMarker.remove();
        // Add New marker position
        markerOptions.position(position);
        direc= getCompleteAddressString(position.latitude, position.longitude);
        // Add Title on Marker
        markerOptions.title(direc);
        Log.w("title", getCompleteAddressString(position.latitude,position.longitude));
        markerOptions.snippet("latitud: "+position.latitude+"\n"+"longitud: "+position.longitude);
        // Set Draggable
        markerOptions.draggable(true);
        // Add marker on map
        mCurrLocationMarker = mMap.addMarker(markerOptions);
        // Show Info window on marker
        mCurrLocationMarker.showInfoWindow();

        latitud = position.latitude;
        longitud = position.longitude;

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
