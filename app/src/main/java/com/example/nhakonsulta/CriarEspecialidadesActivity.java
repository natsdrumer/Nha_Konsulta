package com.example.nhakonsulta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CriarEspecialidadesActivity extends AppCompatActivity {
    OkHttpClient client;
    Button btnCriarE;
    EditText etvNomeE;
    String url = "https://4491-38-44-73-67.ngrok-free.app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_especialidades);
        client = new OkHttpClient();
        etvNomeE = findViewById(R.id.etvNomeE);
        btnCriarE = findViewById(R.id.btnCriarE);

        btnCriarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etvNomeE.getText().toString();
                criarEspecialidade(name);
                etvNomeE.setText("");
            }
        });
    }

    private void criarEspecialidade(String nome) {
        RequestBody requestBody = new FormBody.Builder()
                .add("nome", nome)
                .build();
        Request request = new Request.Builder().url(url + "/especialidades").post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"resposta do server " + response.toString(),Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

    }
}