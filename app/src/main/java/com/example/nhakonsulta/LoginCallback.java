package com.example.nhakonsulta;

import androidx.annotation.Nullable;

public interface LoginCallback {

    void onLoginResult(boolean success, @Nullable String jwtToken);
}
