package com.example.mariana.desafiomobile.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SocialListEntity {
    @SerializedName("acoes_sociais")
    @Expose

    public List<SocialEntity> acoes_sociais;
    public List<SocialEntity> getSocial() {
        return acoes_sociais;
    }
}