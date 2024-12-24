package com.example.fishtrack.httpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    static Retrofit retrofit = null;
    public static Retrofit retrofitSingleton(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://146.190.131.25:3000").build();
        }
        return retrofit;
    }

}
