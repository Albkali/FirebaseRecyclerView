package com.Albkali.FirebaseRecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



/**
 * Created by User on 1/2/2018.
 */

public class ItemActivity extends AppCompatActivity {

    private static final String TAG = "ItemActivity";
     GroceryItemsActivity GroceryItemsActivity;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Log.d(TAG, "onCreate: started.");
        final EditText input = new EditText(this);

        GroceryItemsActivity = new GroceryItemsActivity();
        TextView name = findViewById(R.id.image_description);

        name.setText(getIntent().getStringExtra("Item_name"));

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dilog_update_Name(view);

            }
        });
    }




        public boolean Dilog_update_Name(View v) {


            final EditText input = new EditText(this);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);

//            input.setText(getIntent().getStringExtra("Item_name"));
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
            alertDialog.setView(input); // uncomment this line
            alertDialog.setTitle("Rename ...");
            alertDialog.setMessage("Are you sure to rename  " );

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String itemName = input.getText().toString();
                        String id = getIntent().getStringExtra("id");

                        mDatabase = FirebaseDatabase.getInstance().getReference();

                        mDatabase.child("grocery-items").child(id).child("name").setValue(itemName);
                        input.setText(itemName);
                        setItemName(itemName);
                        Toast.makeText(ItemActivity.this, input.getText().toString(), Toast.LENGTH_SHORT).show();
//                        (GroceryItemsActivity).updateItemToFirebase(itemName);

                    }

                });


            alertDialog.show();

            return true;
        }

    private void getItemName(){
            String Itemname = getIntent().getStringExtra("image_name");
        setItemName(Itemname);
        }

    private void setItemName(String ItemName){
        TextView name = findViewById(R.id.image_description);
        name.setText(ItemName);
    }



}
















