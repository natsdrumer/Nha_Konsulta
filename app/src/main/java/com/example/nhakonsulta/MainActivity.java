package com.example.nhakonsulta;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements LoginCallback {

    EditText etvPassword, etvEmail;
    Button btnLogin, btnRegistar;
    String url = "https://7ecd-102-213-204-145.ngrok-free.app";
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
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etvEmail.getText().toString();
                String pass = etvPassword.getText().toString();
                attemptLogin(email, pass);


            }
        });
    }

    private void attemptLogin(String email, String password) {



        Login login = new Login();
        login.login(email, password, this);
    }

    @Override
    public void onLoginResult(boolean success, @Nullable String jwtToken) {
        if (success) {

            if (jwtToken != null) {

                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("jwtToken", jwtToken);
                editor.apply();


                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
            if (jwtToken != null) {

                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("jwtToken", jwtToken);
                editor.apply();
            }
            //Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        } else {

            //Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }

}