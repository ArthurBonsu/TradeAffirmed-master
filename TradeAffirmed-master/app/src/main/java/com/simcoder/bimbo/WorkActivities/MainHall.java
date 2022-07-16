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
import com.simcoder.bimbo.Admin.AdminLogin;
import com.simcoder.bimbo.Approver.ApprovalLogin;
import com.simcoder.bimbo.MapViewAccountTypeDecider;
import com.simcoder.bimbo.R;

import io.paperdb.Paper;

public class MainHall extends AppCompatActivity
{
    private Button Admin, Approver, Client, MapViewButton;
    private ProgressDialog loadingBar;

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
        setContentView(R.layout.mainhall);


        Intent traderIDintent = getIntent();
        if (traderIDintent.getExtras().getString("userID") != null) {
            userID = traderIDintent.getExtras().getString("userID");
        }

        Admin = (Button) findViewById(R.id.admin);
        Client = (Button) findViewById(R.id.client);
        Approver = (Button) findViewById(R.id.approver);
        MapViewButton = (Button)findViewById(R.id.mapviewlogintypeselect);
        loadingBar = new ProgressDialog(this);


        Paper.init(this);




        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        if (mGoogleApiClient != null) {
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        }

        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(MainHall.this,
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
                    userID = "";
                    userID = user.getUid();
                }

                // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                // WHICH IS CUSTOMER TO BE ADDED.
                // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
            }
        };

        if (Admin != null) {
            Admin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainHall.this, AdminLogin.class);
                    intent.putExtra("userID", userID);
                    startActivity(intent);
                }
            });
        }

        if (Approver != null) {
            Approver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainHall.this, ApprovalLogin.class);
                    intent.putExtra("userID", userID);
                    startActivity(intent);
                }
            });
        }
        if (Client != null) {
            Client.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainHall.this, LoginActivity.class);
                    intent.putExtra("userID", userID);
                    startActivity(intent);
                }
            });

            if (MapViewButton != null) {
                MapViewButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainHall.this, MapViewAccountTypeDecider.class);
                        intent.putExtra("userID", userID);
                        startActivity(intent);
                    }
                });

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
