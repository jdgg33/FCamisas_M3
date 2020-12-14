package com.example.fcamisas_m3;

import android.os.Bundle;

import com.example.fcamisas_m3.adapters.CamisasAdapter;
import com.example.fcamisas_m3.models.CamisasModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends BaseActivity {

    private FloatingActionButton fab_list_create;
    private ListView lv_list_camisas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();



        fab_list_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreate();
            }
        });

        lv_list_camisas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                model = modelArrayList.get(position);
                goToDetail(model);
            }
        });

    }

    protected void init(){
        fab_list_create = findViewById(R.id.fab_list_create);
        lv_list_camisas = findViewById(R.id.lv_list_camisas);
    }

    protected void getCamisas(){
        if (collectionReference != null) {
            collectionReference.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                if(task.getResult() != null){
                                    modelArrayList =new ArrayList<>();
                                    for (QueryDocumentSnapshot snapshot : task.getResult()){
                                        model = snapshot.toObject(CamisasModel.class);
                                        modelArrayList.add(model);
                                    }

                                    if (modelArrayList.size()>0){
                                        paintCamisas(modelArrayList);

                                    }else{
                                        makeSimpleAlertDialog("Alerta", "Camisas no encontradas");

                                    }

                                }else{
                                    makeSimpleAlertDialog("Alerta", "La camisa no fue encontrada");
                                }

                            }else{
                                makeSimpleAlertDialog("Error", task.getException().getMessage());
                            }
                        }
                    });
        }else{
            makeSimpleToast("Error Base de datos", 1);

        }
    }

    private void paintCamisas(ArrayList<CamisasModel> modelArrayList) {
        adapter = new CamisasAdapter(this,modelArrayList);
        lv_list_camisas.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCamisas();
    }
}