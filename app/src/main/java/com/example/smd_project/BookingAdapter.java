package com.example.smd_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter {
    private ArrayList<Booking> bookings;

    public BookingAdapter(ArrayList<Booking> b){
    this.bookings=b;
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder {

        TextView BookingS_Name;
        TextView Booking_Date;
        TextView Booking_Price;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            BookingS_Name=itemView.findViewById(R.id.book_service_name);
            Booking_Date=itemView.findViewById(R.id.book_service_date);
            Booking_Price=itemView.findViewById(R.id.book_service_price);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_row_item,parent,false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BookingViewHolder VH=(BookingViewHolder)holder;
        VH.BookingS_Name.setText(bookings.get(position).getServices().getName());
        VH.Booking_Price.setText(String.valueOf(bookings.get(position).getTotalCost()));
        VH.Booking_Date.setText(bookings.get(position).getBookDate());

    }

    @Override
    public int getItemCount() {
        return 0;
       // return bookings.size();
    }
}
