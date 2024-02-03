package com.example.nhakonsulta;

import androidx.annotation.NonNull;

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

public class Login {

    private final OkHttpClient client = new OkHttpClient();
    private final String url = "https://7ecd-102-213-204-145.ngrok-free.app";

    public void login(String email, String pass, final LoginCallback callback) {
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("senha", pass)
                .build();
        Request request = new Request.Builder().url(url + "/auth/login").post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                callback.onLoginResult(false, null); // Callback with failure and null token
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    try {
                        JSONObject jsonResponse = new JSONObject(responseBody);
                        String jwtToken = jsonResponse.getString("token");
                        callback.onLoginResult(true, jwtToken); // Callback with success and token
                    } catch (JSONException e) {
                        e.printStackTrace();
                        callback.onLoginResult(false, null); // Callback with failure and null token
                    }
                } else {
                    callback.onLoginResult(false, null); // Callback with failure and null token
                }
            }
        });
    }
}

