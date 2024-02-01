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
    String url = "https://ae29-38-44-73-67.ngrok-free.app";
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
                attemptLogin(email, pass);


            }
        });
    }

    private void attemptLogin(String email, String password) {
        // Get email and password from your UI components


        Login login = new Login();
        login.login(email, password, this);
    }

    @Override
    public void onLoginResult(boolean success, @Nullable String jwtToken) {
        if (success) {
            // Login successful, handle accordingly (e.g., save the token and navigate to the next activity)
            if (jwtToken != null) {
                // Save the token to SharedPreferences or wherever you want to store it
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("jwtToken", jwtToken);
                editor.apply();

                // Navigate to the next activity
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish(); // Optional: Finish the current activity to prevent going back with back button
            }
            if (jwtToken != null) {
                // Save the token to SharedPreferences or wherever you want to store it
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("jwtToken", jwtToken);
                editor.apply();
            }
            //Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        } else {
            // Login failed, handle accordingly (e.g., show an error message)
            //Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }

}