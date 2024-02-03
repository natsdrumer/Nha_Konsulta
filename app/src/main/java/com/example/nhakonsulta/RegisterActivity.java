package com.example.nhakonsulta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etvNome, etvEmail, etvPassword, etvIdade, etvMorada, etvNif, etvInps, etvTelefone;

    Button btnRegistar;
    String url = "https://7ecd-102-213-204-145.ngrok-free.app";
    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etvNome = findViewById(R.id.etName);
        etvEmail = findViewById(R.id.etMail);
        etvPassword = findViewById(R.id.etPass);
        etvIdade = findViewById(R.id.etIdade);
        etvMorada = findViewById(R.id.etMorada);
        etvNif = findViewById(R.id.etNif);
        etvInps = findViewById(R.id.etInps);
        btnRegistar = findViewById(R.id.btnReg);
        etvTelefone = findViewById(R.id.etTelefone);
        client = new OkHttpClient();

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome, email, password, morada;
                int idade, inps, telefone;
                nome = etvNome.getText().toString();
                email = etvEmail.getText().toString();
                password = etvPassword.getText().toString();
                morada = etvMorada.getText().toString();
                idade = Integer.parseInt(etvIdade.getText().toString());
                inps = Integer.parseInt(etvInps.getText().toString());
                telefone = Integer.parseInt(etvTelefone.getText().toString());




                RequestBody requestBody = new FormBody.Builder()
                        .add("email", email)
                        .add("senha", password)
                        .add("tipo", "paciente")
                        .add("nome", nome)
                        .add("idade", String.valueOf(idade))
                        .add("telefone", String.valueOf(telefone))
                        .add("morada", morada)
                        .add("inps", String.valueOf(inps))
                        .build();
                Request request = new Request.Builder().url(url + "/users").post(requestBody).build();
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
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        });

                    }
                });

            }
        });
    }
}