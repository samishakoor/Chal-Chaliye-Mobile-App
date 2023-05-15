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

public class RestaurantList extends AppCompatActivity implements  RecyclerViewInterface{

    ArrayList<Service> Restaurants;
    RecyclerView Rest_Rview;
    FirebaseClass FC;
    Customer C;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FC=new FirebaseClass();
        ArrayList<Service> temp=(ArrayList<Service>) getIntent().getSerializableExtra("services");

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


        Restaurants=new ArrayList<>();

        C= (Customer) getIntent().getSerializableExtra("customer");


        for (int i=0;i<temp.size();i++){
            if (temp.get(i).getType()==serviceType.Restaurant){
                Restaurants.add(temp.get(i));
            }

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        Rest_Rview=(RecyclerView) findViewById(R.id.restaurant_rview);
        Rest_Rview.setLayoutManager(new LinearLayoutManager(this));
        Rest_Rview.setAdapter(new ServiceAdapter(Restaurants,this));

    }

    @Override
    public void onItemClick(int pos) {
        Service s=Restaurants.get(pos);
        Intent i=new Intent(this,service_view.class);
        i.putExtra("customer",C);
        i.putExtra("Service",s);
        startActivity(i);

    }
}