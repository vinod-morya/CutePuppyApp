package com.wildnettechnologies.cutepuppyapp.Adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.wildnettechnologies.cutepuppyapp.R;
import com.wildnettechnologies.cutepuppyapp.models.RecepieModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecepie extends RecyclerView.Adapter<AdapterRecepie.ContactsViewHolder> {
    private final Activity mActivity;
    private List<RecepieModel.Result> list = new ArrayList<>();

    public AdapterRecepie(Activity activity, List<RecepieModel.Result> list) {
        this.list = list;
        mActivity = activity;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_recepie_item, viewGroup, false);
        return new ContactsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        final RecepieModel.Result recepie = list.get(position);
        holder.tvName.setText(recepie.getTitle());
        holder.tvDesc.setText(recepie.getIngredients());
        if (recepie.getThumbnail() != null && recepie.getThumbnail().length() > 0) {
            Log.e("", "" + recepie.getThumbnail());
            Picasso.with(mActivity).load(recepie.getThumbnail()).placeholder(R.drawable.profile_default).into(holder.ivImage);
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder {
        final CardView cardView;
        final ImageView ivImage;
        final TextView tvName;
        final TextView tvDesc;

        ContactsViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            ivImage = (ImageView) itemView.findViewById(R.id.picture);
            tvName = (TextView) itemView.findViewById(R.id.tv_recepie);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_recepie_details);
        }
    }
}
