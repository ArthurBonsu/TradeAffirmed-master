package com.simcoder.bimbo;
import android.app.ProgressDialog;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.auth.FirebaseUser;

//D:\DOCUMENT\TRADEX  PROJECT\TradeAffirmed-master\TradeAffirmed-master\app\src\main\res\drawable


public class CustomerLoginActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private Button mLogin, mRegistration;
    private SignInButton GoogleBtn;
    private static final int RC_SIGN_IN = 1;
    Intent signInIntent;
    GoogleApiClient mGoogleApiClient;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private static final String TAG = "Google Activity";
    private ProgressDialog mProgress;
    String  userID;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

        mLogin = findViewById(R.id.login);
        mRegistration = findViewById(R.id.registration);
        GoogleBtn = findViewById(R.id.customersigninbtngoogle);
        mProgress  = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();


        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent traderIDintent = getIntent();
        if (traderIDintent.getExtras().getString("userID") != null) {
            userID = traderIDintent.getExtras().getString("userID");
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

  /*
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(CustomerLoginActivity.this,
                new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
*/

        mGoogleSignInClient = GoogleSignIn.getClient(CustomerLoginActivity.this, gso);
        if (GoogleBtn != null) {
            GoogleBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mProgress.show();
                    signIn();
                    mProgress.hide();
                }
            });
        }

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                if (user != null) {


                             userID = "";
                             userID = user.getUid();
                            if (FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID) != null) {
                            Intent intent = new Intent(CustomerLoginActivity.this, CustomerMapActivity.class);
                            startActivity(intent);
                            finish();
                            return;
                        }

                    } else {
                        Toast.makeText(CustomerLoginActivity.this, "Customer not found", Toast.LENGTH_SHORT).show();

                    }


                // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                // WHICH IS CUSTOMER TO BE ADDED.
                // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
            }
        };
// I must add Google Sign in and Facebook sign in




        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                if (!validateFormForSignUp()) {
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(CustomerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Log.d(TAG, "createUserWithEmail:success");
                            userID = mAuth.getCurrentUser().getUid();
                            mProgress.show();

                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID).child("name");
                            current_user_db.setValue(email);
                            mProgress.hide();
                            Toast.makeText(CustomerLoginActivity.this, "REGISTERD!", Toast.LENGTH_SHORT).show();
                            // WE HAVE TO UPDATE THE CURRENT UI AND GO TO THE NEXT ACTIVITY WHICH IS MAP
                            Intent intent = new Intent(CustomerLoginActivity.this, CustomerMapActivity.class);
                            startActivity(intent);
                            return;


                        }else{
                            Toast.makeText(CustomerLoginActivity.this, "sign up error", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });

        //String user_id = mAuth.getCurrentUser().getUid();
      //  DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(user_id);
      //  current_user_db.setValue(true);



        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                if (! validateFormForSignIn()) {
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(CustomerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            userID = mAuth.getCurrentUser().getUid();
                            mProgress.show();
                            Intent intent = new Intent(CustomerLoginActivity.this, CustomerMapActivity.class);
                            startActivity(intent);
                            mProgress.hide();
                            return;
                        }else{
                            Toast.makeText(CustomerLoginActivity.this, "sign in error", Toast.LENGTH_SHORT).show();
                            // WE HAVE TO UPDATE THE CURRENT UI AND GO TO THE NEXT ACTIVITY WHICH IS MAP

                        }
                    }
                });

            }
        });
    }

    private  void signIn() {
        //    signInIntent  = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        if (mGoogleSignInClient != null) {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();

            startActivityForResult(signInIntent, RC_SIGN_IN);


        }
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
                .addOnCompleteListener(CustomerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(CustomerLoginActivity.this, CustomerMapActivity.class);
                            startActivity(intent);
                            return;

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(CustomerLoginActivity.this, "sign in error", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
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
