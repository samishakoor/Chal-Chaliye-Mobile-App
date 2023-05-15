package com.example.smd_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//TODO: Complete Adapter ??
public class ServiceAdapter extends RecyclerView.Adapter {
    private ArrayList<Service> Services;
    private RecyclerViewInterface recyclerViewInterface;
    public ServiceAdapter(ArrayList<Service> H,RecyclerViewInterface RVI){
        this.Services=H;
        this.recyclerViewInterface=RVI;
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder{
        public TextView ServiceName;
        public TextView ServicePrice;
        public RatingBar ServiceRating;
        public ImageView IV;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            ServiceName=(TextView) itemView.findViewById(R.id.service_name);
            ServicePrice=(TextView) itemView.findViewById(R.id.service_price);
            ServiceRating=(RatingBar) itemView.findViewById(R.id.service_rating);
            IV=(ImageView) itemView.findViewById(R.id.service_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();
                    recyclerViewInterface.onItemClick(pos);
                }
            });

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_list_item,parent,false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ServiceViewHolder VW=(ServiceViewHolder) holder;
        VW.ServiceName.setText(Services.get(position).getName());
        VW.ServicePrice.setText(String.valueOf( Services.get(position).getPrice()));
        VW.ServiceRating.setRating(Services.get(position).getAvgRating());
        switch (Services.get(position).getType()){
            case Hotel:
                VW.IV.setImageResource(R.drawable.ic_shortcut_hotel);
                break;
            case Place:
                VW.IV.setImageResource(R.drawable.ic_shortcut_place);
                break;
            case Transport:
                VW.IV.setImageResource(R.drawable.ic_shortcut_directions_transit);
                break;
            case Restaurant:
                VW.IV.setImageResource(R.drawable.ic_shortcut_restaurant);
                break;


        }


    }

    @Override
    public int getItemCount() {
        return Services.size();
    }
}
