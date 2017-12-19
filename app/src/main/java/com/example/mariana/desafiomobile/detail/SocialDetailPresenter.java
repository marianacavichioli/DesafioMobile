package com.example.mariana.desafiomobile.detail;


import com.example.mariana.desafiomobile.entity.SocialEntity;

public class SocialDetailPresenter {
    private SocialDetailView socialDetailView;

    public SocialDetailPresenter(SocialDetailView view) {
        this.socialDetailView = view;
    }

    private SocialEntity socialDetail;
    public void getSocialDetail(SocialEntity socialEntity){
        socialDetailView.showDetail(socialEntity);
    }
}
