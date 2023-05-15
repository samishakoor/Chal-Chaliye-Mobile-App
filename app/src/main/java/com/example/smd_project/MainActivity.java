package com.example.smd_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {
    private static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 991;
    private PaymentsClient paymentsClient;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ArrayList<String> e=new ArrayList<>()  ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel=new NotificationChannel("paymentNotification","paymentNotification",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager =getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        Uri gmmIntentUri = Uri.parse("geo:0,0?q=FAST NUCES Lahore");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        paymentsClient = PaymentsUtil.createPaymentsClient(this);

//        Service s=new Service("FAST-NUCES",200,"FAST-NUCES");
//        s.AddToRatings(new Rating(2,"bad"));
//        s.AddToRatings(new Rating(5,"good"));
//        s.setType(serviceType.Place);
//
//        Service t=new Service("Faisal Mover",200,"Thokar Niaz Baig");
//        t.AddToRatings(new Rating(2,"bad"));
//        t.AddToRatings(new Rating(5,"good"));
//        t.setType(serviceType.Transport);
//
//        Service v=new Service("Hotel One",200,"Hotel One Faisal Town");
//        v.AddToRatings(new Rating(2,"bad"));
//        v.AddToRatings(new Rating(5,"good"));
//        v.setType(serviceType.Hotel);
//
//        Service u=new Service("Goga Naqeeba",200,"Model Town");
//        u.AddToRatings(new Rating(2,"bad"));
//        u.AddToRatings(new Rating(5,"good"));
//        u.setType(serviceType.Restaurant);
//
//
//        FirebaseDatabase.getInstance().getReference().child("services").child(s.getName()).setValue(s);
//        FirebaseDatabase.getInstance().getReference().child("services").child(t.getName()).setValue(t);
//        FirebaseDatabase.getInstance().getReference().child("services").child(u.getName()).setValue(u);
//        FirebaseDatabase.getInstance().getReference().child("services").child(v.getName()).setValue(v);






//        Intent i=new Intent(getApplicationContext(),SignupActivity.class);
//        startActivity(i);

    }
    public void NextActivity(View view){
        Intent i=new Intent(this,SignIn.class);

        startActivity(i);

    }


}