package com.example.smd_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class editProfile extends AppCompatActivity {
    private Customer cust;
    Button save;
    EditText Name;
    EditText DOB;
    EditText Phone;
    EditText PW;
FirebaseClass FC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FC=new FirebaseClass();
        cust=(Customer) getIntent().getSerializableExtra("customer");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        save=(Button) findViewById(R.id.save_edit_profile_btn);
        Name=(EditText) findViewById(R.id.editName);
        DOB=(EditText) findViewById(R.id.editDOB);
        Phone=(EditText) findViewById(R.id.editContact);
        PW=(EditText) findViewById(R.id.editAge);

        Name.setText(cust.getName());
        DOB.setText(cust.getDOB());
        Phone.setText(cust.getPhoneNumber());
        PW.setText(cust.getPassword());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( (Name.getText().toString().isEmpty()|| DOB.getText().toString().isEmpty() || PW.getText().toString().isEmpty() || DOB.getText().toString().isEmpty())== false )
                {
                    cust.setName(Name.getText().toString());
                    cust.setDOB(DOB.getText().toString());
                    cust.setPassword(PW.getText().toString());
                    cust.setPhoneNumber(Phone.getText().toString());


                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String newPassword = cust.getPassword();

                    user.updatePassword(newPassword)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(editProfile.this, "Updated !", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                    FC.AddCustomer(cust);
                }

            }
        });

    }
}