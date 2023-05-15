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

public class TransportList extends AppCompatActivity implements  RecyclerViewInterface {

    ArrayList<Service> Transports;
    RecyclerView TP_Rview;
    FirebaseClass FC;
    Customer C;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FC=new FirebaseClass();
        ArrayList<Service> temp=(ArrayList<Service>) getIntent().getSerializableExtra("services");
        Transports=new ArrayList<>();


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
            if (temp.get(i).getType()==serviceType.Transport){
                Transports.add(temp.get(i));
            }

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_list);
        TP_Rview=(RecyclerView) findViewById(R.id.TP_rview);
        TP_Rview.setLayoutManager(new LinearLayoutManager(this));
        TP_Rview.setAdapter(new ServiceAdapter(Transports,this));

    }

    @Override
    public void onItemClick(int pos) {
        Service s=Transports.get(pos);
        Intent i=new Intent(this,service_view.class);
        i.putExtra("customer",C);
        i.putExtra("Service",s);
        startActivity(i);

    }
}