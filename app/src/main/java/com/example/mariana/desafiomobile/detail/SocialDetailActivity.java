package com.example.mariana.desafiomobile.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mariana.desafiomobile.R;
import com.example.mariana.desafiomobile.entity.SocialEntity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SocialDetailActivity extends AppCompatActivity{

    @BindView(R.id.image_view_header)
    ImageView imgHeader;

    @BindView(R.id.text_view_description)
    TextView descricaoDetail;

    @BindView(R.id.text_view_sites)
    TextView siteDetail;

    //@BindView(R.id.linear_layout_loading)
    //LinearLayout loadingLayout;

    SocialEntity socialEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_detail);

        ButterKnife.bind(this);

        socialEntity = (SocialEntity) getIntent().getSerializableExtra("social");
        setDetails(socialEntity);
    }

    public void setDetails(SocialEntity socialEntity) {
        Picasso.with(this)
                .load(socialEntity.getImage())
                .centerCrop()
                .fit()
                .into(imgHeader);
        descricaoDetail.setText(socialEntity.getDescription());
        siteDetail.setText(socialEntity.getSite());
        setTitle(socialEntity.getName());
    }
}
