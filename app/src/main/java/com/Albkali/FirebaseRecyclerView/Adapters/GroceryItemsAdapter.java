package com.Albkali.FirebaseRecyclerView.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.Albkali.FirebaseRecyclerView.ItemActivity;
import com.Albkali.FirebaseRecyclerView.Model.GroceryItem;
import com.Albkali.FirebaseRecyclerView.R;

import java.util.List;

public class GroceryItemsAdapter extends RecyclerView.Adapter<GroceryItemsAdapter.GroceryItemsViewHolder> {



    public List<GroceryItem> groceryItems;
    Context context;

    public GroceryItemsAdapter(Context con) {
      // groceryItems = itemNames;
        context = con;
    }
    @NonNull
    @Override
    public GroceryItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {




        int layoutIdForListItem=R.layout.grocery_item;
        LayoutInflater inflater= LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately=false;
        View view=inflater.inflate(layoutIdForListItem,parent,shouldAttachToParentImmediately);
        GroceryItemsViewHolder viewHolder=new GroceryItemsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryItemsViewHolder holder, final int position) {
        holder.bind(position);

       // holder.tvItem.setText(groceryItems.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ItemActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Item_name", groceryItems.get(position).getName());
                intent.putExtra("id", groceryItems.get(position).getId());

                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {

        if(groceryItems!=null){
            return groceryItems.size();
        }
        return 0;
    }

    public class GroceryItemsViewHolder extends RecyclerView.ViewHolder{
        public TextView tvItem;
        public TextView tvEmail;
        public ConstraintLayout viewBackground, viewForeground;
        public GroceryItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
            tvEmail = itemView.findViewById(R.id.tv_email);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);

        }
        void bind(int listIndex){ tvItem.setText(groceryItems.get(listIndex).getName());
        tvEmail.setText(groceryItems.get(listIndex).getAddedByUser());
        }
    }






    public void setGroceryItems(List<GroceryItem> groceryItems){
        this.groceryItems=groceryItems;
        notifyDataSetChanged();
    }

    public void removeItem(int position) { groceryItems.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged() notifyItemRemoved(position);
    }


}