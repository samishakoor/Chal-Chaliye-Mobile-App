package com.example.smd_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;

public class SignIn extends AppCompatActivity {
    TextView email;
    TextView password;
    Button signin;
    ArrayList<Customer> cs;
    TextView register;
    private FirebaseAuth mAuth;
    private FirebaseClass FC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

cs= new ArrayList<>();
        FC=new FirebaseClass();
        mAuth=FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email=(TextView) findViewById(R.id.SignIn_email);
        password=(TextView) findViewById(R.id.SignIN_password);

        signin=(Button) findViewById(R.id.SignIN_button);
        register=(TextView) findViewById(R.id.RegisterButton);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user=mAuth.getCurrentUser();
                            //TODO: get Customer and pass it

                            final Customer[] c = {new Customer("","","","","")};

                            DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("customers");
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                                        Customer temp=postSnapshot.getValue(Customer.class);
                                        cs.add(temp);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            DatabaseReference ref2=FirebaseDatabase.getInstance().getReference().child("customers");
                            ref2.child("temp").setValue(c[0]);


                           for (int i=0;i<cs.size();i++){
                                if (cs.get(i).getEmail().equals(email.getText().toString())){
                                        c[0]=cs.get(i);
                                }

                           }
                            Intent i =new Intent(getApplicationContext(),customer_landing_page.class);
                            i.putExtra("customer", c[0]);
                            if (cs.size()!=0){
                            startActivity(i);
                            }

                        }else{

                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });
    }




}