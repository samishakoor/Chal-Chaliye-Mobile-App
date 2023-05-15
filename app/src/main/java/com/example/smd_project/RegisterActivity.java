package com.example.smd_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    TextView Rname;
    TextView RDOB;
    TextView REmail;
    TextView RPassword;
    TextView RPhone;
    Button regAcc;

    private FirebaseAuth FB;
    private FirebaseClass FC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FC=new FirebaseClass();
        FB=FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Rname=(TextView) findViewById(R.id.reg_name);
        RDOB=(TextView) findViewById(R.id.reg_DOB);
        REmail=(TextView) findViewById(R.id.reg_email);
        RPassword=(TextView) findViewById(R.id.reg_pass);
        RPhone=(TextView) findViewById(R.id.reg_phone);

        regAcc=(Button) findViewById(R.id.registerAcc);

        regAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FB.createUserWithEmailAndPassword(REmail.getText().toString(),RPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = FB.getCurrentUser();
                            Customer c =new Customer(Rname.getText().toString(),RPhone.getText().toString(),RDOB.getText().toString(),REmail.getText().toString(),RPassword.getText().toString());
                            FC.AddCustomer(c);

                            Intent i=new Intent(getApplicationContext(),customer_landing_page.class);
                            i.putExtra("customer",c);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }
}