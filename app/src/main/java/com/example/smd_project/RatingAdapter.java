package com.example.smd_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RatingAdapter extends RecyclerView.Adapter {
    public ArrayList<Rating> Ratings;

    public RatingAdapter(ArrayList<Rating> R){
        this.Ratings=R;

    }

    public class RatingViewHolder extends RecyclerView.ViewHolder{
        public TextView comment;
        public TextView rating;
        public RatingBar RBar;
        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);
            RBar=(RatingBar) itemView.findViewById(R.id.review_rating) ;
            comment=(TextView) itemView.findViewById(R.id.review_comment);
            rating=(TextView) itemView.findViewById(R.id.review_rating_text);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_list_item,parent,false);
        return new RatingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RatingViewHolder VW=(RatingViewHolder) holder;
        VW.comment.setText(Ratings.get(position).getComment());
        VW.rating.setText(String.valueOf(Ratings.get(position).getRatingValue())+"/5");
        VW.RBar.setRating(Ratings.get(position).getRatingValue());

    }

    @Override
    public int getItemCount() {
        return Ratings.size();
    }
}
