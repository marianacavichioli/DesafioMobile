package com.example.mariana.desafiomobile.social;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mariana.desafiomobile.R;
import com.example.mariana.desafiomobile.entity.SocialEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.ViewHolder> {

    private Context context;
    private OnRecyclerViewSelected mOnRecyclerViewSelected;
    private List<SocialEntity> socialList;

    public SocialAdapter(List<SocialEntity> socialList, Context context) {
        this.socialList = socialList;
        this.context = context;
    }

    //infla o componente view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.social_item_list, parent, false);
        return new ViewHolder(v);
    }

    //seta os dados nas views
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SocialEntity socialEntity = socialList.get(position);
        holder.txSocialName.setText(socialEntity.getName());
        Picasso.with(context)
                .load(socialEntity.getImage())
                .centerCrop()
                .fit()
                .into(holder.imgBackground);    }

    //retorna o tamanho da lista
    @Override
    public int getItemCount() {
        return socialList.size();
    }

    //mapeamento dos componentes da view
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tx_social_name)
        TextView txSocialName;

        @BindView(R.id.image_view_background)
        ImageView imgBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.container)
        void onItemClick(View view){
            if(mOnRecyclerViewSelected != null)
                mOnRecyclerViewSelected.onClick(view, getAdapterPosition());
        }

        //seta o clique longo
        @OnLongClick(R.id.container)
        boolean onLongItemClick(View view){
            if(mOnRecyclerViewSelected != null)
                mOnRecyclerViewSelected.onLongClick(view, getAdapterPosition());

            return true;
        }
    }

    public void setOnRecyclerViewSelected(OnRecyclerViewSelected onRecyclerViewSelected){
        this.mOnRecyclerViewSelected = onRecyclerViewSelected;
    }
}
