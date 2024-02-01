package com.example.nhakonsulta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EspecialidadesActivity extends AppCompatActivity {
    Button btnListar, btnCriar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialidades);
        btnCriar = findViewById(R.id.btnCriar);
        btnListar = findViewById(R.id.btnListar);

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CriarEspecialidadesActivity.class);
                startActivity(intent);
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListarEspecialidadesActivity.class);
                startActivity(intent);
            }
        });
    }
}