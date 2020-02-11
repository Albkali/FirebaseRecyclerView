package com.Albkali.FirebaseRecyclerView.ui.Orders;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Albkali.FirebaseRecyclerView.Adapters.GroceryItemsAdapter;
import com.Albkali.FirebaseRecyclerView.Adapters.GroceryItemsFirebase;
import com.Albkali.FirebaseRecyclerView.Fragments.GroceryItemDialogFragment;
import com.Albkali.FirebaseRecyclerView.Helpers.RecyclerItemTouchHelper;
import com.Albkali.FirebaseRecyclerView.Model.GroceryItem;
import com.Albkali.FirebaseRecyclerView.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class OrdersFragment extends Fragment implements
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{

    private GroceryItemsAdapter adapter;
    private RecyclerView recyclerView;
    private static GroceryItemsFirebase groceryItemsFirebase;
    private FirebaseDatabase database;
    private static FirebaseAuth firebaseAuth;

    private OrdersViewModel OrdersdViewModel;
    Context context;
    AppCompatActivity callingActivity;


//    public OrdersFragment(Context con , AppCompatActivity callingActivity, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener listener) {
//
//        this.context = con;
//        this.callingActivity = callingActivity;
//        this.listener = listener;
//
//    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OrdersdViewModel =
                ViewModelProviders.of(this).get(OrdersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_orders, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        firebaseAuth = FirebaseAuth.getInstance();
        // this listener will be called when there is change in firebase user session





        recyclerView=(RecyclerView) root.findViewById(R.id.rv_grocery_items);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new GroceryItemsAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        database = FirebaseDatabase.getInstance();
        groceryItemsFirebase = new GroceryItemsFirebase(adapter, database, callingActivity);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0,
                ItemTouchHelper.LEFT,this );
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


        FloatingActionButton myButton = (FloatingActionButton) root.findViewById(R.id.floatingActionButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment dialog = new GroceryItemDialogFragment();
                dialog.show(getActivity().getSupportFragmentManager(), "GroceryItemDialogFragment");


//                Toast.makeText(context, "Add Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        return root;

    }



    public static void addItemToFirebase(GroceryItem item){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            item.setAddedByUser(user.getEmail()); }


        groceryItemsFirebase.addGroceryItem(item);
        //Toast.makeText(GroceryItemsActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
    }

    public void updateItemToFirebase(String item){
//        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(groceryItemsFirebase==null){
            Log.i("test", "groceryItemsFirebase is null");
        }

        groceryItemsFirebase.updateGroceryItem(item);
        //Toast.makeText(GroceryItemsActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
    }

    public void addClicked( View view){



    }


    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {


        if (viewHolder instanceof GroceryItemsAdapter.GroceryItemsViewHolder) {
            // get the removed item name to display it in snack bar
            String name = adapter.groceryItems.get(viewHolder.getAdapterPosition()).getName();
            // backup of removed item for undo purpose
            final GroceryItem deletedItem = adapter.groceryItems.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            // Remove the item from recycler view
            adapter.removeItem(viewHolder.getAdapterPosition());
            groceryItemsFirebase.deleteGroceryItem(adapter.groceryItems.get(deletedIndex));
        }
    }


}





