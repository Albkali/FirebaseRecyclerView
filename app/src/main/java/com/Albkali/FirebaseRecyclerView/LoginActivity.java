package com.Albkali.FirebaseRecyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Albkali.FirebaseRecyclerView.Utility.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


   private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;
    EditText emailEdit;
    EditText passwordEdit;
    final String TAG = LoginActivity.class.getSimpleName();


    private class MyFocusChangeListener implements View.OnFocusChangeListener {
        public void onFocusChange(View v, boolean hasFocus){
            if(!hasFocus) { if(!Utility.isFocusedOnEditText(findViewById(android.R.id.content),false)){
                Utility.hideSoftKeyboard(LoginActivity.this, findViewById(android.R.id.content)); }
            } }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        emailEdit = findViewById(R.id.editEmail);
        passwordEdit = findViewById(R.id.editPassword);
        View.OnFocusChangeListener ofcListener = new MyFocusChangeListener();
        emailEdit.setOnFocusChangeListener(ofcListener);
        passwordEdit.setOnFocusChangeListener(ofcListener);
        progressBar = findViewById(R.id.progress_bar);
        // Hide the status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Get the root layout element
        ConstraintLayout constraintLayout = findViewById(R.id.login_layout);
        // Create an animation drawable from the background of the root layout
        AnimationDrawable animationDrawable = (AnimationDrawable)constraintLayout.getBackground();

        // Set animation options and start
        animationDrawable.setEnterFadeDuration(10); animationDrawable.setExitFadeDuration(5000); animationDrawable.start();

        if (firebaseAuth.getCurrentUser() != null) {

                Intent groceryItemsIntent = new Intent(this,
                        MainActivity.class);
                startActivity(groceryItemsIntent);

                finish();
            }

        /*  FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("app_name");
        myRef.setValue("Firebase");   */



    }




    public void loginClicked(View view){

            Utility.hideSoftKeyboard(LoginActivity.this,
                    findViewById(android.R.id.content));
           //Toast.makeText(getApplicationContext(),"Login clicked",Toast.LENGTH_SHORT).show();
            String email = emailEdit.getText().toString().trim();
            final String password = passwordEdit.getText().toString().trim();
            if (TextUtils.isEmpty(email)) { Toast.makeText(getApplicationContext(),
                    "Enter email address!", Toast.LENGTH_SHORT).show(); return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;

            } progressBar.setVisibility(View.VISIBLE);
        //authenticate user
        firebaseAuth.signInWithEmailAndPassword(email, password) .addOnCompleteListener(LoginActivity.this, new
                OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult>
                                                   task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the listener.
                        // signed in user can be handled in the

                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                passwordEdit.setError("Password should be at least 6 characters");
                            } else {
                                Toast.makeText(LoginActivity.this, "Authentication Error", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } });

    }

    // What's the problem?





    public void signupClicked(View view){


        Utility.hideSoftKeyboard(LoginActivity.this,
                findViewById(android.R.id.content));
        String email = emailEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(),
                    "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult>

                                                   task) {
                        progressBar.setVisibility(View.GONE);

                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this,

                                    "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Intent groceryItemsIntent = new
                                    Intent(LoginActivity.this, MainActivity.class);
                            startActivity(groceryItemsIntent);
                            finish();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Error."
                                        + e.getLocalizedMessage(),
                                Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        Log.w(TAG, e.getLocalizedMessage());
                    }
                });

        progressBar.setVisibility(View.VISIBLE);


    }





    protected void onResume() {
        super.onResume(); progressBar.setVisibility(View.GONE);
    }

}
