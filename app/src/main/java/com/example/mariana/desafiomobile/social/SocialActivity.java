package com.example.mariana.desafiomobile.social;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mariana.desafiomobile.R;
import com.example.mariana.desafiomobile.detail.SocialDetailActivity;
import com.example.mariana.desafiomobile.entity.SocialEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SocialActivity extends AppCompatActivity implements SocialView {

    @BindView(R.id.rv_social)
    RecyclerView rvSocial;

    SocialPresenter socialPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        ButterKnife.bind(this);

        socialPresenter = new SocialPresenter(this);
        socialPresenter.updateList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void updateList(final List<SocialEntity> socialList) {

        //seta o adapter
        SocialAdapter socialAdapter = new SocialAdapter(socialList, this);
        socialAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            @Override
            public void onClick(View view, int position) {
                Intent openDetailActivity = new Intent(SocialActivity.this, SocialDetailActivity.class);
                openDetailActivity.putExtra("social",socialPresenter.getSocialId(position));
                startActivity(openDetailActivity);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(SocialActivity.this, "Clique Longo", Toast.LENGTH_SHORT).show();

            }
        });

        rvSocial.setAdapter(socialAdapter);

        // criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvSocial.setLayoutManager(layoutManager);

        //inserindo separador de itens
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        rvSocial.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}

