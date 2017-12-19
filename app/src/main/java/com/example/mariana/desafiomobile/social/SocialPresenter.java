package com.example.mariana.desafiomobile.social;

import com.example.mariana.desafiomobile.entity.SocialEntity;
import com.example.mariana.desafiomobile.entity.SocialListEntity;
import com.example.mariana.desafiomobile.network.api.SocialApi;
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


    protected void updateList(){

        final SocialApi socialApi = SocialApi.getInstance();
        socialApi.getSocial().enqueue(new Callback<SocialListEntity>() {
            @Override
            public void onResponse(Call<SocialListEntity> call, Response<SocialListEntity> response) {
                socialListEntity = response.body();

                if(socialListEntity != null && socialListEntity.getSocial() != null) {
                    //socialList = socialListEntity.getSocial();
                    socialView.updateList(socialListEntity.getSocial());
                }else {
                    socialView.showMessage("Falha no acesso");
                }
            }

            @Override
            public void onFailure(Call<SocialListEntity> call, Throwable t){
                socialView.showMessage("Falha no acesso ao servidor");
            }

        });
    }

    SocialEntity getSocialId(int position) throws IndexOutOfBoundsException{
        return socialListEntity.getSocial().get(position);
    }
}

