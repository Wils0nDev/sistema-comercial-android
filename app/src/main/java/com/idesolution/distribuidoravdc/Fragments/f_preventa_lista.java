package com.idesolution.distribuidoravdc.Fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.idesolution.distribuidoravdc.Adaptador.PreventaListAdapter;
import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.BD.Repository.CabeceraViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PreventaViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;
import com.idesolution.distribuidoravdc.UI.registro_preventa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_preventa_lista extends Fragment {

    String a;
    String b;
    private Calendar mCalendar = Calendar.getInstance();
    private int dia;
    private int mes;
    private int anio;
    private FloatingActionButton btnNuevaPreventa;
    private PreventaListAdapter adapter;
    private RecyclerView recyclerView;
    //private PreventaViewModel preventaVM;
    private CabeceraViewModel cabeceraVM;
    private List<Preventa> lista_preventas;
    private PreventaViewModel preventaVM;
    private Bundle parametroSalida;
    private FragmentTransaction ft;

    public f_preventa_lista() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_preventa_lista, container, false);
        if(v != null){
            getActivity().setTitle(R.string.titulo_preventa);
            recyclerView = v.findViewById(R.id.rvListaPreventas);
            adapter = new PreventaListAdapter(getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            btnNuevaPreventa = (FloatingActionButton) v.findViewById(R.id.btnNuevaPreventa);

        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
        preventaVM = new ViewModelProvider(this).get(PreventaViewModel.class);

        String fecha = MetodosGlobales.obtenerFechaActual();
        buscarPreventas(fecha);
        /*preventaVM.obtenerPreventas().observe(this, preventas -> {
            lista_preventas = preventas;
            adapter.setPreventas(preventas);
        });*/

        btnNuevaPreventa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //MetodosGlobales.mostrarMensaje(getContext(), "HOLAAAAAAAAAAAAAAAAAAAAA");
                cabeceraVM.deletePreventaInconclusa();
                Intent intentNuevaPreventa = new Intent(getContext(), registro_preventa.class);
                intentNuevaPreventa.putExtra("par_proceso_preventa", Constante.g_const_1);
                intentNuevaPreventa.putExtra("par_transaccion_preventa", Constante.g_const_0);
                startActivity(intentNuevaPreventa);
            }
        });

        adapter.setOnClickListener(v -> {
            parametroSalida = new Bundle();
            Preventa p = lista_preventas.get(recyclerView.getChildAdapterPosition(v));
            parametroSalida.putInt("par_ntraPreventa", p.getNtra());
            parametroSalida.putInt("par_estado", p.getEstado());
            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_principal, new f_preventa_detalle().newInstance(parametroSalida));
            ft.addToBackStack(null);
            ft.commit();
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fecha, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnFecha:
                mCalendar  = Calendar.getInstance();
                dia = mCalendar.get(Calendar.DAY_OF_MONTH);
                mes = mCalendar.get(Calendar.MONTH);
                anio = mCalendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        if(month < 10)
                            a = "0" + month;
                            else
                            a = month + "";

                        if(dayOfMonth < 10)
                            b = "0" + dayOfMonth;
                        else
                            b = dayOfMonth + "";

                        buscarPreventas(b+"/"+ a +"/"+year);
                        MetodosGlobales.mostrarMensaje(getContext(), dayOfMonth+"/"+ a +"/"+year);
                    }
                }
                        ,anio,mes,dia);
                datePickerDialog.show();
                /*DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, monthOfYear);
                        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String date = DateFormat.getDateInstance(DateFormat).format(mCalendar.getTime());
                        MetodosGlobales.mostrarMensaje(getContext(), date);
                        //Log.d("MainActivity", "Selected date is " + date);
                    }
                }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();*/
        }
        return true;
    }

    public void buscarPreventas(String fecha){
        preventaVM = new ViewModelProvider(this).get(PreventaViewModel.class);
        preventaVM.obtenerPreventasPorFecha(fecha).observe(this, preventas -> {
            lista_preventas = preventas;
            adapter.setPreventas(preventas);
        });
    }
}
