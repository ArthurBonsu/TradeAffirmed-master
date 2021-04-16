package com.simcoder.bimbo.WorkActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simcoder.bimbo.R;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity
{
    private Button joinNowButton, loginButton;
    private ProgressDialog loadingBar;
    String traderoruser= "";
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    String role;

    Intent roleintent;
    String userID;
    //AUTHENTICATORS

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Google Activity";
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent traderIDintent = getIntent();
        if (traderIDintent.getExtras().getString("userID") != null) {
            userID = traderIDintent.getExtras().getString("userID");
        }

        joinNowButton = (Button) findViewById(R.id.main_join_now_btn);
        loginButton = (Button) findViewById(R.id.main_login_btn);
        loadingBar = new ProgressDialog(this);


        Paper.init(this);

        final DatabaseReference traderoruserdetails = FirebaseDatabase.getInstance().getReference().child("Users").child(role).child(traderoruser);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        if (mGoogleApiClient != null) {
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        }

        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(MainActivity.this,
                    new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        }
                    }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        }

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    traderoruser = "";
                    traderoruser = user.getUid();
                }

                // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                // WHICH IS CUSTOMER TO BE ADDED.
                // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
            }
        };

        if (loginButton != null) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

        if (joinNowButton != null) {
            joinNowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });

        }
        if (role != null) {
            if (FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(traderoruser) != null && role.equals("Trader")) {
                Intent intent = new Intent(MainActivity.this, com.simcoder.bimbo.Admin.AdminCategoryActivity.class);

                intent.putExtra("Trader", role);
                intent.putExtra("ecommerceuserkey", traderoruser);

                startActivity(intent);
                finish();
                return;
            } else if (FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(traderoruser) != null && role.equals("Customers")) {
                Intent intent = new Intent(MainActivity.this, com.simcoder.bimbo.WorkActivities.HomeActivity.class);
                intent.putExtra("Trader", role);
                intent.putExtra("ecommerceuserkey", traderoruser);
                startActivity(intent);
                finish();
                return;
            } else {
                Intent intent = new Intent(MainActivity.this, com.simcoder.bimbo.WorkActivities.MainActivity.class);
                startActivity(intent);
                finish();
                return;


            }

        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        //     mProgress.hide();
       if (mAuth !=null) {
           mAuth.removeAuthStateListener(firebaseAuthListener);
       }
    }

}
