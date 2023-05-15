package com.example.smd_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class customer_landing_page extends AppCompatActivity {
    ImageButton TransportButton;
    ImageButton PlaceButton;
    ImageButton RestaurantButton;
    ImageButton HotelButton;
    ImageButton ProfileButton;
    TextView welcome;
    ArrayList<Service> cs;
    Customer cust;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cs=new ArrayList<>();
        cust=new Customer();
         cust= (Customer) getIntent().getSerializableExtra("customer");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_landing_page);
        mAdView=(AdView) findViewById(R.id.adView);
        ProfileButton=(ImageButton) findViewById(R.id.custom_profile_button);
        PlaceButton=(ImageButton) findViewById(R.id.Place_Button);
        HotelButton=(ImageButton) findViewById(R.id.Hotel_Button);
        RestaurantButton=(ImageButton) findViewById(R.id.Restaurant_Button);
        TransportButton=(ImageButton) findViewById(R.id.Transport_Button);
        welcome=(TextView) findViewById(R.id.welcome_customer);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("services");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Service temp=postSnapshot.getValue(Service.class);
                    cs.add(temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref.child("temp").setValue(new Service());
        welcome.setText("Welcome "+cust.getName());
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(getApplicationContext(),UserProfile.class);
                i.putExtra("customer",cust);
                startActivity(i);
            }
        });

        PlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(getApplicationContext(),PlaceList.class);
                i.putExtra("customer",cust);
                i.putExtra("services",cs);
                startActivity(i);
            }
        });

        HotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),HotelList.class);
                i.putExtra("customer",cust);
                i.putExtra("services",cs);
                startActivity(i);
            }
        });

        TransportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),TransportList.class);
                i.putExtra("customer",cust);
                i.putExtra("services",cs);
                startActivity(i);

            }
        });

        RestaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),RestaurantList.class);
                i.putExtra("customer",cust);
                i.putExtra("services",cs);
                startActivity(i);
            }
        });


    }
}