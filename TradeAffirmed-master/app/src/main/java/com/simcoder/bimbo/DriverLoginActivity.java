package com.simcoder.bimbo;


import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.auth.FirebaseUser;
import com.simcoder.bimbo.WorkActivities.MainHall;


public class DriverLoginActivity extends AppCompatActivity {
    private EditText mEmail, mPassword;
    private Button mLogin, mRegistration;
    private SignInButton GoogleBtn;
    private static final int RC_SIGN_IN = 9001;
    Intent signInIntent;
     private GoogleSignInClient mGoogleSignInClient;
   // GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Google Activity";
    private ProgressDialog mProgress;
    // SIGN IN SHOULD BE ADDED HERE

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    Button   MainHallButton;;
    String traderID;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_driver_login);

        GoogleBtn = findViewById(R.id.signinbtngoogle);

        mProgress  = new ProgressDialog(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

       /*
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,
                new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

*/
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (mProgress != null) {
                    mProgress.show();
                    signIn();
                    mProgress.hide();
                }}
        });



        mAuth = FirebaseAuth.getInstance();
        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent traderIDintent = getIntent();
        if (traderIDintent.getExtras().getString("userID") != null) {
            traderID = traderIDintent.getExtras().getString("userID");
        }
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();

                if(user!=null  ) {

                    traderID = "";
                    traderID = user.getUid();

                    if (FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(traderID) != null) {


                        Intent intent = new Intent(DriverLoginActivity.this, DriverMapActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    } else {
                        Toast.makeText(DriverLoginActivity.this, "Driver not found", Toast.LENGTH_SHORT).show();

                    }

                }
             /*   if(user!=null){

                    // WE HAVE TO CHECK THE DATA HERE AND SEE IF THESE DETAILS ARE EXISTING AND THEN WE
                     // FLIP TO THE NEXT STAGE
                    Intent intent = new Intent(DriverLoginActivity.this, DriverMapActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
           */ }
        };

        //THERE HAS TO BE AN UPDATE PRODUCT ACTIVITY

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

        mLogin = findViewById(R.id.login);
        mRegistration = findViewById(R.id.registration);
        MainHallButton = (Button)findViewById(R.id.mainhall_button);

        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                if (!validateFormForSignUp()) {
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Log.d(TAG, "createUserWithEmail:success");
                            traderID = mAuth.getCurrentUser().getUid();
                            mProgress.show();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(traderID).child("name");
                            current_user_db.setValue(email);
                            mProgress.hide();
                            Toast.makeText(DriverLoginActivity.this, "REGISTERD!", Toast.LENGTH_SHORT).show();
                            // WE HAVE TO UPDATE THE CURRENT UI AND GO TO THE NEXT ACTIVITY WHICH IS MAP
                            Intent intent = new Intent(DriverLoginActivity.this, DriverMapActivity.class);
                            startActivity(intent);
                            return;


                        }else{
                            Toast.makeText(DriverLoginActivity.this, "sign up error", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });

        MainHallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainhallintent = new Intent(DriverLoginActivity.this, MainHall.class);

                mainhallintent.putExtra("userID", traderID);
                startActivity(mainhallintent);
            }
        });
         // SET THE VALUE T O FIREBASE
        //SET THE STATUS OR ROLE TYPE TO CUSTOMER

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                  if (! validateFormForSignIn()) {
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            traderID = mAuth.getCurrentUser().getUid();
                            mProgress.show();
                            Intent intent = new Intent(DriverLoginActivity.this, DriverMapActivity.class);
                            startActivity(intent);
                            mProgress.hide();
                            return;
                        }else{
                            Toast.makeText(DriverLoginActivity.this, "sign in error", Toast.LENGTH_SHORT).show();
                            // WE HAVE TO UPDATE THE CURRENT UI AND GO TO THE NEXT ACTIVITY WHICH IS MAP

                        }
                    }
                });

            }
        });
    }



    private  void signIn(){
    //    signInIntent  = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        startActivityForResult(signInIntent, RC_SIGN_IN);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }



    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(DriverLoginActivity.this, DriverMapActivity.class);
                            startActivity(intent);
                            return;

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(DriverLoginActivity.this, "sign in error", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
      //  mProgress.show();

        mAuth.addAuthStateListener(firebaseAuthListener);
        }





    @Override
    protected void onStop() {
        super.onStop();
   //     mProgress.hide();
        mAuth.removeAuthStateListener(firebaseAuthListener);

    }
    private boolean validateFormForSignUp() {
        boolean valid = true;

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Required.");
            valid = false;
            mRegistration.setVisibility(View.VISIBLE);
            mLogin.setVisibility(View.GONE);

        } else {
            mEmail.setError(null);
            mRegistration.setVisibility(View.VISIBLE);
            mLogin.setVisibility(View.VISIBLE);

        }

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Required.");
            valid = false;
            mRegistration.setVisibility(View.VISIBLE);
            mLogin.setVisibility(View.GONE);

        } else {
            mPassword.setError(null);
            mRegistration.setVisibility(View.VISIBLE);
            mLogin.setVisibility(View.VISIBLE);

        }

        return valid;
    }

    private boolean validateFormForSignIn() {
        boolean valid = true;

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Required.");
            valid = false;
            mRegistration.setVisibility(View.GONE);
            mLogin.setVisibility(View.VISIBLE);

        } else {
            mEmail.setError(null);
            mRegistration.setVisibility(View.VISIBLE);
            mLogin.setVisibility(View.VISIBLE);

        }

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Required.");
            valid = false;
            mRegistration.setVisibility(View.GONE);
            mLogin.setVisibility(View.VISIBLE);

        } else {
            mPassword.setError(null);
            mRegistration.setVisibility(View.VISIBLE);
            mLogin.setVisibility(View.VISIBLE);

        }

        return valid;
    }


    }





