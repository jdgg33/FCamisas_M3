package com.example.fcamisas_m3;

import android.icu.text.IDNA;
import android.os.Bundle;

import com.example.fcamisas_m3.models.CamisasModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateActivity extends BaseActivity {

    FloatingActionButton fab_create_save;
    FloatingActionButton fab_create_clear;
    FloatingActionButton fab_create_back;
    ImageView iv_create_image;
    TextView tv_create_click_image;
    EditText et_create_tipo;
    EditText et_create_equipo;
    EditText et_create_descripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        fab_create_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goTolist();
            }
        });

        fab_create_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });

        fab_create_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tipo, descripcion, equipo;
                boolean active;

                tipo = et_create_tipo.getText().toString();
                descripcion = et_create_descripcion.getText().toString();
                equipo = et_create_equipo.getText().toString();

                if(tipo.isEmpty() || descripcion.isEmpty() || equipo.isEmpty()){
                    makeSimpleAlertDialog("Info", "Por favor, llene todos los campos");
                }else{
                    model =new CamisasModel();
                    model.setActive(true);
                    model.setDescripcion(descripcion);
                    model.setEquipo(equipo);
                    model.setTipo(tipo);

                    save(model);

                }

            }
        });

    }

    private void save(CamisasModel model) {
        if(collectionReference != null){
            collectionReference.add(model)
            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()){
                        if (task.getResult()!= null){
                            makeSimpleAlertDialog("Success", "Camisa Guardada");
                            clear();
                        }else{
                            makeSimpleAlertDialog("Error", "Camisa no se guardo");
                        }
                }else{
                        makeSimpleAlertDialog("Error", task.getException().getMessage());
                    }
                }
            });
        }else{
            makeSimpleAlertDialog("Error", "No hay conexion con la base de datos");
        }


    }

    protected void init(){
        fab_create_save = findViewById(R.id.fab_create_save);
        fab_create_clear = findViewById(R.id.fab_create_clear);
        fab_create_back = findViewById(R.id.fab_create_back);
        iv_create_image = findViewById(R.id.iv_create_image);
        et_create_tipo = findViewById(R.id.et_create_tipo);
        et_create_equipo = findViewById(R.id.et_create_equipo);
        et_create_descripcion = findViewById(R.id.et_create_descripcion);
    }

    private void clear(){
        et_create_equipo.setText("");
        et_create_descripcion.setText("");
        et_create_tipo.setText("");

        et_create_tipo.requestFocus();

        iv_create_image.setImageResource(R.drawable.ic_people_alt_black_18dp);
    }

}