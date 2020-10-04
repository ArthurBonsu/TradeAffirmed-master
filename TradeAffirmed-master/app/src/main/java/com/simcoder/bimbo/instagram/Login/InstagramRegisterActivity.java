package com.simcoder.bimbo.instagram.Login;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.simcoder.bimbo.instagram.Models.User;
import com.simcoder.bimbo.R;
import com.simcoder.bimbo.instagram.Utils.FirebaseMethods;

/**
 * Created by User on 6/19/2017.
 */

public class InstagramRegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private Context mContext;
    private String email, username, password;
    private EditText mEmail, mPassword, mUsername;
    private TextView loadingPleaseWait;
    private Button btnRegister;
    private ProgressBar mProgressBar;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseMethods firebaseMethods;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private String append = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = InstagramRegisterActivity.this;
        if (mContext != null) {
            firebaseMethods = new FirebaseMethods(mContext);
        }
        Log.d(TAG, "onCreate: started.");

        initWidgets();
        setupFirebaseAuth();
        init();
    }

    private void init() {
        if (btnRegister != null) {
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mEmail.getText() != null) {
                        email = mEmail.getText().toString();
                    }
                        if (mUsername.getText() != null)
                    username = mUsername.getText().toString();
                       if (mPassword.getText() != null) {
                           password = mPassword.getText().toString();
                       }
                       if (email != null){
                           if (username != null){
                               if (password !=null){


                    if (checkInputs(email, username, password)) {
                        if(mProgressBar != null) {
                            mProgressBar.setVisibility(View.VISIBLE);
                        }
                        if (loadingPleaseWait != null) {
                            loadingPleaseWait.setVisibility(View.VISIBLE);
                        }
                            if(firebaseMethods != null) {
                                firebaseMethods.registerNewEmail(email, password, username);
                            }
                    }}}           }
                }
            });
        }
    }
    private boolean checkInputs(String email, String username, String password){
        Log.d(TAG, "checkInputs: checking inputs for null values.");
        if(email.equals("") || username.equals("") || password.equals("")) {
            if (mContext != null) {
                Toast.makeText(mContext, "All fields must be filled out.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
    /**
     * Initialize the activity widgets
     */
    private void initWidgets(){
        Log.d(TAG, "initWidgets: Initializing Widgets.");
        mEmail = (EditText) findViewById(R.id.input_email);
        mUsername = (EditText) findViewById(R.id.input_username);
        btnRegister = (Button) findViewById(R.id.btn_register);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        loadingPleaseWait = (TextView) findViewById(R.id.loadingPleaseWait);
        mPassword = (EditText) findViewById(R.id.input_password);
        mContext = InstagramRegisterActivity.this;
         if (mProgressBar !=null) {
             mProgressBar.setVisibility(View.GONE);
         }
         if (loadingPleaseWait != null) {
             loadingPleaseWait.setVisibility(View.GONE);
         }
    }

    private boolean isStringNull(String string){
        Log.d(TAG, "isStringNull: checking string if null.");

        if(string.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

     /*
    ------------------------------------ Firebase ---------------------------------------------
     */

    /**
     * Check if @param username already exists in database
     * @param username
     */
    private void checkIfUsernameExists(final String username) {
        Log.d(TAG, "checkIfUsernameExists: Checking if " + username + " already exists");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference
                .child("Users").child("Customers")
                .orderByChild("name")
                .equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot singleSnapshot: dataSnapshot.getChildren()) {
                    if (singleSnapshot != null) {
                        if (singleSnapshot.exists()) {
                            if (singleSnapshot.getValue(User.class) != null) {
                                Log.d(TAG, "checkIfUsernameExists: FOUND A MATCH" + singleSnapshot.getValue(User.class));
                                if (myRef.push() != null) {
                                    append = myRef.push().getKey().substring(3, 10);
                                    Log.d(TAG, "onDataChange: username already exists. Appending random string to name: " + append);
                                }
                            }
                        }
                    }
                }
                //1st check: Make sure the username is not already in use
                String mUsername;
                mUsername = username + append;

                //add new user to the database
                   if (firebaseMethods != null) {
                       firebaseMethods.addNewUser(email, mUsername, "", "", "");
                   }

                   if (mContext != null) {
                       Toast.makeText(mContext, "Signup successful. Sending verification email.", Toast.LENGTH_SHORT).show();
                   }
                   if(mAuth != null) {
                       mAuth.signOut();
                   }
                   }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /**
     * Setup the firebase auth object
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");
                   if (FirebaseAuth.getInstance() != null) {
                       mAuth = FirebaseAuth.getInstance();
                   }
                   if (FirebaseDatabase.getInstance() != null) {
                       mFirebaseDatabase = FirebaseDatabase.getInstance();
                   }
                   if (mFirebaseDatabase.getReference() != null) {
                       myRef = mFirebaseDatabase.getReference();
                   }

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                         public void onDataChange(DataSnapshot dataSnapshot) {
                        if (username != null) {
                            checkIfUsernameExists(username);
                        }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    finish();

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuth != null) {
           if (mAuthListener != null) {
               mAuth.addAuthStateListener(mAuthListener);
           }}
        }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {

            if (mAuth != null){mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}}


// #BuiltByGOD