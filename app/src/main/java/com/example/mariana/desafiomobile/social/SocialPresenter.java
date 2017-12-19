package com.example.mariana.desafiomobile.social;

import com.example.mariana.desafiomobile.entity.SocialEntity;
import com.example.mariana.desafiomobile.entity.SocialListEntity;
import com.example.mariana.desafiomobile.network.api.SocialApi;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocialPresenter {

    private SocialView socialView;
    private List<SocialEntity> socialList;
    private SocialListEntity socialListEntity;

    public SocialPresenter(SocialView socialView) {
        this.socialView = socialView;
    }


    protected void updateList(String jsonSocial) {

        //verifica se há informações no json
        if (jsonSocial != null) {
            socialListEntity = new Gson().fromJson(jsonSocial, SocialListEntity.class);
            socialList = socialListEntity.getSocial();
            socialView.updateList(socialList);

        } else { //se não houver informações previamente no json, é necessário baixá-las

            final SocialApi socialApi = SocialApi.getInstance();
            socialView.showLoading();
            socialApi.getSocial().enqueue(new Callback<SocialListEntity>() {
                @Override
                public void onResponse(Call<SocialListEntity> call, Response<SocialListEntity> response) {
                    socialView.hideLoading();
                    socialListEntity = response.body();

                    if (socialListEntity != null && socialListEntity.getSocial() != null) {
                        socialView.updateList(socialListEntity.getSocial());
                    } else {
                        socialView.showMessage("Falha no acesso");
                    }
                }

                @Override
                public void onFailure(Call<SocialListEntity> call, Throwable t) {
                    socialView.hideLoading();
                    socialView.showMessage("Falha no acesso ao servidor");
                    socialView.workOffline();

                }

            });
        }
    }

    SocialEntity getSocialId(int position) throws IndexOutOfBoundsException {
        return socialListEntity.getSocial().get(position);

    }

    //Converte a lista de filmes para json e retorna para a camada de visualização persistir os dados
    public void saveSocial() {
        String jsonSocialEntity = new Gson().toJson(socialListEntity);
        socialView.saveSocialInSharedPreferences(jsonSocialEntity);
    }


    void openSocial(String jsonSocial) {
        if(jsonSocial != null){
            socialView.openSocial(jsonSocial);
        }

    }
}

