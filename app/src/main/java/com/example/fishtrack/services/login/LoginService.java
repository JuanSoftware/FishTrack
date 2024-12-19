package com.example.fishtrack.services.login;

import com.example.fishtrack.services.login.LoginDTO.LoginResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {
    @POST("login")
    Call<LoginResponseDTO> user(@Body LoginModal user);
}
