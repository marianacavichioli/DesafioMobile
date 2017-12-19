package com.example.mariana.desafiomobile.network.service;

import com.example.mariana.desafiomobile.entity.SocialListEntity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SocialService {
    @GET("s/50vmlj7dhfaibpj/sociais.json")
    Call<SocialListEntity> getSocialList();

}
