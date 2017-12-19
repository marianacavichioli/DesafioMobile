package com.example.mariana.desafiomobile.social;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
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

    @BindView(R.id.rv_social)RecyclerView rvSocial;
    @BindView(R.id.linear_layout_loading) LinearLayout loadingLayout;

    SocialPresenter socialPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String jsonSocial = getIntent().getStringExtra("json_social");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        ButterKnife.bind(this);

        socialPresenter = new SocialPresenter(this);
        socialPresenter.updateList(jsonSocial);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_download, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Clique no icone de download da Action Bar para que salve as informações da lista de açoes assim que clicado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_download:
                socialPresenter.saveSocial();

            default:
                return super.onOptionsItemSelected(item);
        }

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

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void saveSocialInSharedPreferences(String jsonSocialEntity) {
        //salva json das acoes para trabalhar ofline
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.social),MODE_PRIVATE).edit();
        editor.putString(getString(R.string.social_entity_json), jsonSocialEntity);
        editor.apply();
        showMessage("Informações salvas com sucesso");
    }

    @Override
    public void workOffline() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.social), MODE_PRIVATE);
        String jsonMovies = preferences.getString(getString(R.string.social_entity_json), null);

        socialPresenter.openSocial(jsonMovies);

    }

    @Override
    public void openSocial(String jsonSocial) {
        Intent abreSocialActivityOffline = new Intent(SocialActivity.this, SocialActivity.class);
        abreSocialActivityOffline.putExtra("json_social", jsonSocial);
        startActivity(abreSocialActivityOffline);
        finish();
    }

}

