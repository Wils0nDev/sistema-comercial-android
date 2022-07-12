package com.idesolution.distribuidoravdc.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.idesolution.distribuidoravdc.BD.Entity.Concepto;
import com.idesolution.distribuidoravdc.BD.Entity.DepartamentoEntity;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class SpinnerConceptoEntityAdapter extends ArrayAdapter<Concepto> {
    public SpinnerConceptoEntityAdapter(Context context, List<Concepto> countryList) {
        super(context, 0, countryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fila_combo_spinner, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.txt_combo_opcion);

        Concepto currentItem = getItem(position);

        if (currentItem != null) {
            textViewName.setText(currentItem.getDescripcion());
        }

        return convertView;
    }
}
