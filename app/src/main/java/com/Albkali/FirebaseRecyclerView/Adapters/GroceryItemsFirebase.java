package com.Albkali.FirebaseRecyclerView.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.Albkali.FirebaseRecyclerView.Model.GroceryItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GroceryItemsFirebase {

    FirebaseDatabase database;
    GroceryItemsAdapter adapter;
    DatabaseReference groceryItems;
    AppCompatActivity callingActivity;
    GroceryItem groceryItem;

    final String TAG = GroceryItemsFirebase.class.getSimpleName();


    public GroceryItemsFirebase(final GroceryItemsAdapter adapter, FirebaseDatabase database, AppCompatActivity callingActivity) {
        this.database = database;
        this.adapter = adapter;
        this.callingActivity = callingActivity;
        groceryItems = database.getReference("grocery-items");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser(); Query query =
                groceryItems.orderByChild("addedByUser").equalTo(user.getEmail() );
        query.addValueEventListener(new ValueEventListener() { @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            List<GroceryItem> groceryItems = new ArrayList<>();
            for(DataSnapshot child:dataSnapshot.getChildren()){ GroceryItem groceryItem =
                    child.getValue(GroceryItem.class); groceryItem.setId(child.getKey());
                groceryItems.add(groceryItem); }
            adapter.setGroceryItems(groceryItems); }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Failed to read value.", databaseError.toException());
            } });


    }



    public void addGroceryItem(GroceryItem groceryItem){
        String groceryItemID = groceryItems.push().getKey();
    groceryItem.setId(groceryItemID);
        groceryItems.child(groceryItemID).setValue(groceryItem).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
        });
    }


    public void updateGroceryItem(String rename){

        groceryItems.child(groceryItem.getId()).child("name").setValue(rename)
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, e.getLocalizedMessage()); }
        });
    }


    public void deleteGroceryItem(GroceryItem groceryItem){
        groceryItems.child(groceryItem.getId()).removeValue().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, e.getLocalizedMessage()); }
        });
    }
}
