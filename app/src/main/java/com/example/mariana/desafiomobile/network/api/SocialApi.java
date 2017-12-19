package com.example.mariana.desafiomobile.network.api;

import com.example.mariana.desafiomobile.entity.SocialListEntity;
import com.example.mariana.desafiomobile.network.service.SocialService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SocialApi {

    private static SocialApi instance;
    private SocialService socialService;

    public static SocialApi getInstance(){
        if(instance == null){
            instance = new SocialApi();
        }

        return instance;
    }

    private SocialApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com/")
                .addConverterFactory(defaultConvertFactory())
                .build();

        this.socialService = retrofit.create(SocialService.class);

    }

    private Converter.Factory defaultConvertFactory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return GsonConverterFactory.create(gson);
    }

    public Call<SocialListEntity> getSocial() {
        return socialService.getSocialList();
    }

}


