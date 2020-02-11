package com.Albkali.FirebaseRecyclerView.Fragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.Albkali.FirebaseRecyclerView.Model.GroceryItem;
import com.Albkali.FirebaseRecyclerView.R;
import com.Albkali.FirebaseRecyclerView.ui.Orders.OrdersFragment;


public class GroceryItemDialogFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new
            AlertDialog.Builder(getActivity());
// Get the layout inflater LayoutInflater inflater =
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        requireActivity().getLayoutInflater();
        View root = getView();
// Inflate and set the layout for the dialog
// Pass null as the parent view because its going in the dialog layout

        builder.setView(inflater.inflate(R.layout.add_grocery_item_dialog, null))
// Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int
                            id) {
                        EditText text = GroceryItemDialogFragment.this.getDialog().findViewById(R.id.et_grocery_item);
                        String itemName = text.getText().toString();
                        GroceryItem item = new GroceryItem(itemName,
                                "yasser@gmail.com", false);

                        OrdersFragment.addItemToFirebase(item);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        GroceryItemDialogFragment.this.getDialog().cancel();
                    }
                });



        return builder.create();


    }


}