package com.example.smd_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FirebaseClass {
    public interface DataObserver{
        public void update();
    }
    private  DataObserver observer;
    private FirebaseDatabase db;
    private FirebaseStorage store;

    public FirebaseClass() {
        observer=new DataObserver() {
            @Override
            public void update() {

            }
        };
        db=FirebaseDatabase.getInstance();
        store= FirebaseStorage.getInstance();
    }
    public void AddBooking(Booking b){
        DatabaseReference ref=db.getReference("bookings");

        ref.child(b.getBookDate()+b.getCust().getName()).setValue(b);

    }
    public ArrayList<Service> returnServices(){
        ArrayList<Service> s=new ArrayList<>();
        DatabaseReference servRef= db.getReference("services");
        servRef.addValueEventListener(new ValueEventListener() {
           @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Service temp=postSnapshot.getValue(Service.class);
                    s.add(temp);
                }
                observer.update();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return s;
    }

    public ArrayList<Rating> returnRating(){
        ArrayList<Rating> s=new ArrayList<>();
        DatabaseReference servRef= db.getReference("ratings");
        servRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Rating temp=postSnapshot.getValue(Rating.class);
                    s.add(temp);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return s;
    }
    public ArrayList<Customer> returnCust(){
        ArrayList<Customer> s=new ArrayList<>();
        DatabaseReference servRef= db.getReference("customers");

        servRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Customer temp=postSnapshot.getValue(Customer.class);
                    s.add(temp);
                }
                observer.update();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        observer.update();
//        servRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Customer temp=snapshot.getValue(Customer.class);
//                s.add(temp);
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Customer temp=snapshot.getValue(Customer.class);
//                s.add(temp);
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                Customer temp=snapshot.getValue(Customer.class);
//                s.add(temp);
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Customer temp=snapshot.getValue(Customer.class);
//                s.add(temp);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        return s;

    }



    public ArrayList<Booking> returnBooking(){
        ArrayList<Booking> s=new ArrayList<>();
        DatabaseReference servRef= db.getReference("bookings");
        servRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Booking temp=postSnapshot.getValue(Booking.class);
                    s.add(temp);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return s;
    }

    public Customer recCustomer(String email){
        final Customer[] customer = {new Customer()};
        DatabaseReference ref=db.getReference("customers").child(email.replace(".","1"));
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                customer[0] =snapshot.getValue(Customer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return customer[0];
    }
    public void AddCustomer(Customer c){
        DatabaseReference ref=db.getReference("customers");
        ref.child(c.getEmail().replace(".","1")).setValue(c);

    }
    public void AddService(Service service){
        DatabaseReference ref=db.getReference("services").child(service.getName());
        ref.setValue(service);

    }

    public ArrayList<Rating>   returnRating(Service service){
        DatabaseReference ref=db.getReference("services").child(service.getName()).child("ratings");
        ArrayList<Rating> ratings=new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Rating temp=postSnapshot.getValue(Rating.class);
                    ratings.add(temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return ratings;
    }

    public Bitmap returnPic(String id){
        final Bitmap[] bp = new Bitmap[1];
        StorageReference ref=store.getReference().child(("images/"+id+".jpg"));
        try {
            File localfile=File.createTempFile("tempfile",".jpg");

            ref.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    bp[0] = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bp[0];
    }


    public void getBitmapFromFirebaseStorage(String imagePath, final OnBitmapLoadedListener listener) {
        // Get a reference to the Firebase Storage instance
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Get a reference to the image file in Firebase Storage
        StorageReference imageRef = storage.getReference().child("images").child(imagePath+".jpg");

        // Download the image file into a byte array
        imageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Decode the byte array into a Bitmap object
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                // Pass the Bitmap object back to the listener
                listener.onBitmapLoaded(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors that occur during the download

            }
        });
    }

    public interface OnBitmapLoadedListener {
        void onBitmapLoaded(Bitmap bitmap);
    }

}
