package com.Albkali.FirebaseRecyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.Albkali.FirebaseRecyclerView.Adapters.GroceryItemsAdapter;
import com.Albkali.FirebaseRecyclerView.Adapters.GroceryItemsFirebase;
import com.Albkali.FirebaseRecyclerView.Fragments.GroceryItemDialogFragment;
import com.Albkali.FirebaseRecyclerView.Helpers.RecyclerItemTouchHelper;
import com.Albkali.FirebaseRecyclerView.Model.GroceryItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class GroceryItemsActivity extends AppCompatActivity {

//public class GroceryItemsActivity extends AppCompatActivity implements
//        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
//
//    private GroceryItemsAdapter adapter;
//    private RecyclerView recyclerView;
//    private GroceryItemsFirebase groceryItemsFirebase;
//    private FirebaseDatabase database;
//    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_items);


//        firebaseAuth = FirebaseAuth.getInstance();
                // this listener will be called when there is change in firebase user session




//
//        recyclerView=(RecyclerView)findViewById(R.id.rv_grocery_items);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        adapter=new GroceryItemsAdapter(getApplicationContext());
//        recyclerView.setAdapter(adapter);
//        database = FirebaseDatabase.getInstance();
//        groceryItemsFirebase = new GroceryItemsFirebase(adapter, database, this);
//
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//
//        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0,
//                ItemTouchHelper.LEFT, this);
//        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
//
    }
//
//
//
//    public void addItemToFirebase(GroceryItem item){
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if(user!=null){
//        item.setAddedByUser(user.getEmail()); }
//
//
//        groceryItemsFirebase.addGroceryItem(item);
//                 //Toast.makeText(GroceryItemsActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
//    }
//
//    public void updateItemToFirebase(String item){
////        FirebaseUser user = firebaseAuth.getCurrentUser();
//
//        if(groceryItemsFirebase==null){
//            Log.i("test", "groceryItemsFirebase is null");
//        }
//
//        groceryItemsFirebase.updateGroceryItem(item);
//        //Toast.makeText(GroceryItemsActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
//    }
//
//    public void addClicked(View view){
//
//        DialogFragment dialog = new GroceryItemDialogFragment();
//        dialog.show(getSupportFragmentManager(), "GroceryItemDialogFragment");
//
//
//        Toast.makeText(GroceryItemsActivity.this, "Add Button Clicked", Toast.LENGTH_SHORT).show();
//    }
//
//
//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
//
//
//        if (viewHolder instanceof GroceryItemsAdapter.GroceryItemsViewHolder) {
//             // get the removed item name to display it in snack bar
//            String name = adapter.groceryItems.get(viewHolder.getAdapterPosition()).getName();
//            // backup of removed item for undo purpose
//            final GroceryItem deletedItem = adapter.groceryItems.get(viewHolder.getAdapterPosition());
//            final int deletedIndex = viewHolder.getAdapterPosition();
//             // Remove the item from recycler view
//            adapter.removeItem(viewHolder.getAdapterPosition());
//            groceryItemsFirebase.deleteGroceryItem(adapter.groceryItems.get(deletedIndex));
//        }
//    }
//
//
}
