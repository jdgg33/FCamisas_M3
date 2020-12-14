package com.example.fcamisas_m3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fcamisas_m3.R;
import com.example.fcamisas_m3.models.CamisasModel;

import java.util.ArrayList;

public class CamisasAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CamisasModel> modelArrayList;

    public CamisasAdapter(Context context, ArrayList<CamisasModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public CamisasModel getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view ==null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view = layoutInflater.inflate(R.layout.camisas_list_item, viewGroup, false);
        }

        TextView tv_camisas_list_item_description = view.findViewById(R.id.tv_camisas_list_item_description);
        TextView tv_camisas_list_item_tipo = view.findViewById(R.id.tv_camisas_list_item_tipo);

        tv_camisas_list_item_description.setText(getItem(position).getDescripcion());
        tv_camisas_list_item_tipo.setText(getItem(position).getTipo());

        return view;
    }
}
