package com.example.mariana.desafiomobile.social;

import com.example.mariana.desafiomobile.entity.SocialEntity;
import com.example.mariana.desafiomobile.entity.SocialListEntity;

import java.util.List;

public interface SocialView {
    void updateList(List<SocialEntity> socialList);
    void showMessage(String s);
}
