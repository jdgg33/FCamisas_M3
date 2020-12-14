package com.example.fcamisas_m3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fcamisas_m3.models.CamisasModel;

public class DataDetailFragment extends Fragment {

    private static String tipo, descripcion, equipo;
    private boolean active;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv_data_detail_tipo, tv_data_detail_descripcion, tv_data_detail_equipo;

        tv_data_detail_tipo =view.findViewById(R.id.tv_data_detail_tipo);
        tv_data_detail_descripcion =view.findViewById(R.id.tv_data_detail_descripcion);
        tv_data_detail_equipo =view.findViewById(R.id.tv_data_detail_equipo);

        tv_data_detail_tipo.setText(tipo);
        tv_data_detail_descripcion.setText(descripcion);
        tv_data_detail_equipo.setText(equipo);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DataDetailFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    public static void receiveData (Bundle bundle){
        CamisasModel model = (CamisasModel) bundle.getSerializable("model");
        if (model != null);
            tipo = model.getTipo();
            descripcion = model.getDescripcion();
            equipo = model.getEquipo();
    }
}