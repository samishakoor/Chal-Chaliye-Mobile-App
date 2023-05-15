package com.example.smd_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HotelList extends AppCompatActivity implements  RecyclerViewInterface {
    RecyclerView hotels;
    ArrayList<Service> hotels_list;
    FirebaseClass FC;
    Customer C;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FC=new FirebaseClass();
        ArrayList<Service> temp=(ArrayList<Service>) getIntent().getSerializableExtra("services");
        hotels_list=new ArrayList<>();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("services");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Service temp1=snapshot.getValue(Service.class);
                temp.add(temp1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        C= (Customer) getIntent().getSerializableExtra("customer");


        for (int i=0;i<temp.size();i++){
            if (temp.get(i).getType()==serviceType.Hotel){
                hotels_list.add(temp.get(i));
            }

        }

        //TODO: Add FB code for getting Hotel list
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        hotels=(RecyclerView) findViewById(R.id.hotel_rView);

        hotels.setLayoutManager(new LinearLayoutManager(this));
        hotels.setAdapter(new ServiceAdapter(hotels_list,this));

    }

    @Override
    public void onItemClick(int pos) {
        Service s=hotels_list.get(pos);
        Intent i=new Intent(this,service_view.class);
        i.putExtra("Service",s);
        i.putExtra("customer",C);
        startActivity(i);
    }
}