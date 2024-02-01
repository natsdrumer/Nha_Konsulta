package com.example.nhakonsulta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLanguage;
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

public class MainActivity extends AppCompatActivity {

    EditText etvPassword, etvEmail;
    Button btnLogin, btnRegistar;
    String url = "https://4491-38-44-73-67.ngrok-free.app";
    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etvEmail = findViewById(R.id.etvEmail);
        etvPassword = findViewById(R.id.etvPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistar = findViewById(R.id.btnRegistar);
        client = new OkHttpClient();

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etvPassword.setText("");
                etvEmail.setText("");
                Intent intent = new Intent(getApplicationContext(), EspecialidadesActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etvEmail.getText().toString();
                String pass = etvPassword.getText().toString();
                boolean isValidated = login(email, pass);

                if(isValidated){
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean login(String email, String pass) {
        final boolean[] valid = new boolean[1];
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("senha", pass)
                .build();
        Request request = new Request.Builder().url(url + "/auth/login").post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Toast.makeText(getApplicationContext(),"resposta do server " + response,Toast.LENGTH_LONG).show();
                if(response.equals(200)){
                    valid[0] = true;
                }else {
                    valid[0] = false;
                }
            }
        });
        return valid[0];
    }
}