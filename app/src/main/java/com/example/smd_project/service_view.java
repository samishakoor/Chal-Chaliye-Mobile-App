package com.example.smd_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class service_view extends AppCompatActivity {
    private Service temp;
    private Customer cust;
    Button ReviewButton;
    Button BookingButton;
    Button ViewOnMapButton;
    TextView servName;
    TextView servPrice;
    RecyclerView ratingRView;
    ImageView img;
    FirebaseClass FC;
    RatingAdapter RA;
    ArrayList<Rating> ratings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FC=new FirebaseClass();
        temp =(Service) getIntent().getSerializableExtra("Service");
        cust=(Customer) getIntent().getSerializableExtra("customer");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_view);
        ReviewButton=(Button) findViewById(R.id.leave_review_button);
        img=(ImageView) findViewById(R.id.serv_image);
        servName=(TextView)findViewById(R.id.place_name);
        servPrice=(TextView)findViewById(R.id.place_price);
        ratingRView=(RecyclerView)findViewById(R.id.RatingRView);
        BookingButton=(Button) findViewById(R.id.Book_Button);
        ViewOnMapButton=(Button) findViewById(R.id.viewLoc_button);
        ratings=FC.returnRating(temp);
        RA=new RatingAdapter(ratings);

        ratingRView.setAdapter(RA);
        ratingRView.setLayoutManager(new LinearLayoutManager(this));

        servName.setText(temp.getName());
        servPrice.setText(String.valueOf(temp.getPrice()));
        //img.setImageBitmap(FC.returnPic(temp.getName()));
        //TODO: Get image from firebase DONE

        FC.getBitmapFromFirebaseStorage(temp.getName(), new FirebaseClass.OnBitmapLoadedListener() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap) {
                img.setImageBitmap(bitmap);
            }
        });

        BookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),CheckoutActivity.class);

                i.putExtra("service",temp);
                i.putExtra("customer",cust);

                startActivity(i);

            }
        });

        ReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RatingDialog dialog = new RatingDialog(service_view.this);
                dialog.setOnRatingSelectedListener(new RatingDialog.OnRatingSelectedListener() {
                    @Override
                    public void onRatingSelected(Rating rating) {
                      temp.AddToRatings(rating);
                      FC.AddService(temp);
                        ratings=FC.returnRating(temp);
                      RA.notifyDataSetChanged();
                    }
                });
                dialog.show();




            }
        });

        ViewOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loc="geo:0,0?q=";
                loc=loc+temp.getName();
                Uri gmmIntentUri = Uri.parse(loc);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                startActivity(mapIntent);
            }
        });
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder= new AlertDialog.Builder(getApplicationContext());
        LayoutInflater IF=getLayoutInflater();
        builder.setView(IF.inflate(R.layout.review_layout,null))
        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                EditText Et1=(EditText) findViewById(R.id.ratingnum_input);
                EditText Et2=(EditText) findViewById(R.id.rating_text_inputbox);
                float val= Float.parseFloat(Et1.getText().toString());
                String comment=Et2.getText().toString();
                if(val>5 ||val<0){
                    Toast T=new Toast(getApplicationContext());
                    T.setText("Invalid Rating! Enter within 0-5");
                    T.show();
                }else{
                    Rating r_temp=new Rating(val,comment);
                    temp.AddToRatings(r_temp);
                    Toast T=new Toast(getApplicationContext());
                    T.setText("Added Successfully");
                    T.show();
                }
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       return;
                    }
                });
        return builder.create();

    }
}