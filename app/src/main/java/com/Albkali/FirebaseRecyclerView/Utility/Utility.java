package com.Albkali.FirebaseRecyclerView.Utility;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Utility {



    public static void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager inputMethodManager =
            (InputMethodManager) activity.getSystemService( Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow( view.getWindowToken(), 0);
        clearFocus(view);
    }

    public static void clearFocus(View view) {
// Set up touch listener for text box views to clear focus
        if (view instanceof EditText) { view.clearFocus();
        }
//If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                clearFocus(innerView); }
        }
    }


    public static boolean isFocusedOnEditText(View view, boolean ret) { // If any of the EditText has focus return true
        if (view instanceof EditText) {
            if(view.isFocused()){ ret = ret || true; return ret;
            } }
//If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                ret = isFocusedOnEditText(innerView, ret); }
        }
        ret = ret || false;
        return ret;
    }
}
