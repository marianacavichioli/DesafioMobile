package com.example.mariana.desafiomobile.social;

import com.example.mariana.desafiomobile.entity.SocialEntity;
import com.example.mariana.desafiomobile.entity.SocialListEntity;

import java.util.List;

public interface SocialView {
    void showMessage(String s);
    void showLoading();
    void hideLoading();

    void saveSocialInSharedPreferences(String jsonMovieEntity);

    void updateList(List<SocialEntity> socialList);

    void workOffline();

    void openSocial(String jsonSocial);
}
