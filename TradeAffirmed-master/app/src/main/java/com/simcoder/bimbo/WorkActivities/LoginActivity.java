package com.simcoder.bimbo.WorkActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.Share;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.simcoder.bimbo.Admin.AdminCategoryActivity;
import com.simcoder.bimbo.Admin.AdminLogin;
import com.simcoder.bimbo.Admin.AdminMainPage;
import com.simcoder.bimbo.Admin.AdminRegister;
import com.simcoder.bimbo.Admin.AdminResetPasswordEmail;
import com.simcoder.bimbo.Admin.SessionManager;
import com.simcoder.bimbo.Approver.ApprovalLogin;
import com.simcoder.bimbo.Model.Users;
import com.simcoder.bimbo.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;
import com.simcoder.bimbo.R;

import java.util.HashMap;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity
{
    private EditText InputPhoneNumber, InputPassword, Emailaccess;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView AdminLink, NotAdminLink, ForgetPasswordLink;

    private String parentDbName = "";


    private CheckBox chkBoxRememberMe;

    String traderoruser= "";
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    String role;


    //AUTHENTICATORS

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Google Activity";
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton GoogleBtn;

    private ProgressDialog mProgress;
    Intent roleintent;
    String userID;
    Button SignupButton;
    TextView IamTrader;
    Button TryVerificationCode;
    Button MainHallButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent userintent = getIntent();
        if (userintent.getExtras().getString("userID") != null) {
            userID = userintent.getExtras().getString("userID");
        }

        Emailaccess = (EditText) findViewById(R.id.login_email_input);
        InputPassword = (EditText) findViewById(R.id.login_password_input);

        LoginButton = (Button) findViewById(R.id.login_btn);
        SignupButton = (Button)findViewById(R.id.signup);
        MainHallButton =(Button)findViewById(R.id.mainhall_button);

        // InputPhoneNumber = (EditText) findViewById(R.id.login_phone_number_input);

        IamTrader = (TextView) findViewById(R.id.iamtrader);
        TryVerificationCode = findViewById(R.id.mobileverification);
        loadingBar = new ProgressDialog(this);
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


        GoogleBtn = findViewById(R.id.eccommercegooglelogin);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);

        // I HAVE TO BUILD A GOOGLE BUTTON ON TOP OF THE USERNAME
        GoogleBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                signIn();

            }
        });

        if (mGoogleApiClient != null) {
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        }

        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(LoginActivity.this,
                    new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        }
                    }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        }



        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me_chkb);
        Paper.init(this);

        SessionManager sessionManager = new SessionManager(LoginActivity.this, SessionManager.SESSION_REMEMBERME);
        if (sessionManager.checkRememberMe()) {

            HashMap<String, String > rememberMeDetails = sessionManager.getRememberMeDetailsFromSession();
            Emailaccess.setText(rememberMeDetails.get(SessionManager.KEY_SESSIONEMAIL));
            InputPassword.setText(rememberMeDetails.get(SessionManager.KEY_SESSIONPASSWORD));

        }



        // GET FROM FOLLOWING KEY

        // FORGET PASSWORD PROBLEMS
        ForgetPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                intent.putExtra("check", "login");
                startActivity(intent);
            }
        });
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });

        TryVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tryverificationcodeintent = new Intent(LoginActivity.this, SendVerificationCodeActivity.class);

                tryverificationcodeintent.putExtra("role", role);
                tryverificationcodeintent.putExtra("userID", userID);
                startActivity(tryverificationcodeintent);
            }
        });

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientregisterintent = new Intent(LoginActivity.this, RegisterActivity.class);
                clientregisterintent.putExtra("role", role);
                clientregisterintent.putExtra("userID", userID);
                startActivity(clientregisterintent);

            }
        });
        MainHallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainhallintent = new Intent(LoginActivity.this, MainHall.class);

                mainhallintent.putExtra("userID", userID);
                startActivity(mainhallintent);
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
                // We then move to the next activity after authentication is complete
                Intent firebasetoclientmainpageactivity = new Intent(getApplicationContext(), ClientMainPage.class);
                startActivity(firebasetoclientmainpageactivity);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if (user != null) {
                                userID = "";
                                userID = user.getUid();

                                final DatabaseReference RootRef;

                                RootRef = FirebaseDatabase.getInstance().getReference().child("Users");


                                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {


                                                                           @Override
                                                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                               if (dataSnapshot.child("Customers").child(userID).exists()) {
                                                                                   //    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                                                                                   // GETTING THE TYPE OF TRADER

                                                                                   //    if (parentDbName.equals("Drivers") && role.equals("Trader")) {
                                                                                   firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
                                                                                       @Override
                                                                                       public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                                                                                           FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                                                                                           if (user != null) {
                                                                                               userID = "";
                                                                                               userID = user.getUid();

                                                                                               if (FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID) != null && role.equals("Customer")) {
                                                                                                   Toast.makeText(LoginActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                                                                                   loadingBar.dismiss();


                                                                                                   Intent intent = new Intent(LoginActivity.this, ClientMainPage.class);
                                                                                                   intent.putExtra("role", role);
                                                                                                   intent.putExtra("userID", userID);
                                                                                                   startActivity(intent);
                                                                                                   finish();

                                                                                               }
                                                                                           } else {

                                                                                               loadingBar.dismiss();
                                                                                               Toast.makeText(LoginActivity.this, "User " + acct.getDisplayName() + "does does not exist", Toast.LENGTH_SHORT).show();

                                                                                           }

                                                                                           // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                                                                                           // WHICH IS CUSTOMER TO BE ADDED.
                                                                                           // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE

                                                                                       }

                                                                                       ;
                                                                                   };

                                                                                   // Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                                                                   //   startActivity(intent);
                                                                               }


                                                                                           /*else if (parentDbName.equals("Customers") && role.equals("Customers") ) {


                                                                                               firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
                                                                                                   @Override
                                                                                                   public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                                                                                                       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                                                                       if (user != null) {
                                                                                                           traderoruser = "";
                                                                                                           traderoruser = user.getUid();

                                                                                                           if (FirebaseDatabase.getInstance().getReference().child("Users").child(parentDbName).child(traderoruser) != null && role.equals("Customer")); {

                                                                                                               Toast.makeText(AdminLogin.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                                                                                               loadingBar.dismiss();
                                                                                                               Intent intent = new Intent(AdminLogin.this, HomeActivity.class);
                                                                                                               startActivity(intent);
                                                                                                               finish();

                                                                                                           }} else {
                                                                                                           loadingBar.dismiss();
                                                                                                           Toast.makeText(AdminLogin.this, "User with" + acct.getDisplayName() + "does does not exist", Toast.LENGTH_SHORT).show();
                                                                                                           // WE HAVE TO UPDATE THE CURRENT UI AND GO TO THE NEXT ACTIVITY WHICH IS MAP

                                                                                                       }
                                                                                                   }

                                                                                               };
                                                                                           };





                                                                                           // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                                                                                           // WHICH IS CUSTOMER TO BE ADDED.
                                                                                           // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
                                                                                           ;





                                                                                           ;}}}}

                                                                           @Override
                                                                           public void onCancelled(DatabaseError databaseError) {

                                                                           }
                                                                           */

                                                                           }



                                                                           @Override
                                                                           public void onCancelled(@NonNull DatabaseError error) {

                                                                           }
                                                                       }
                                );
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "sign in error", Toast.LENGTH_SHORT).show();
                            }
                        }}}

                );
    }
    // ...



// HERE  WE CAN DO THE GOOGLE IMPLEMENTATION HERE

    private void LoginUser()
    {
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();
        String email =Emailaccess.getText().toString();
        // WE HAVE TO BUILD AN EMAIL BUTTON

/*
        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        */
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your your email...", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            AllowAccessToAccount(phone, password, email);
        }


    }



    private void AllowAccessToAccount(final String phone, final String password, final String email) {
        if (chkBoxRememberMe.isChecked()) {
            /*
            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        */
            SessionManager sessionManager = new SessionManager(LoginActivity.this, SessionManager.SESSION_REMEMBERME);

            sessionManager.createRememberMeSession(Emailaccess.getText().toString(), InputPassword.getText().toString());

        }


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            userID = "";
            userID = user.getUid();

            final DatabaseReference RootRef;

            RootRef = FirebaseDatabase.getInstance().getReference().child("Users");


            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("Customers").child(userID).exists()) {
                        dataSnapshot.getValue(Users.class);
                        //    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                        // GETTING THE TYPE OF TRADER
                        role = dataSnapshot.child("Customers").child(userID).child("role").getValue(String.class);

                        //  if (usersData.getPhone().equals(phone))
                        {
                            //    if (usersData.getPassword().equals(password))

                            {

                                firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
                                    @Override
                                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                                        if (user != null) {
                                            userID = "";
                                            userID= user.getUid();


                                                if (FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID) != null && role.equals("Customer")) {

                                                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {

                                                            if (task.isSuccessful()) {
                                                                Log.d(TAG, "signInWithEmail:success");
                                                                String traderID = mAuth.getCurrentUser().getUid();
                                                                SessionManager sessionManager = new SessionManager(LoginActivity.this, SessionManager.SESSION_REMEMBERME);
                                                                sessionManager.createLogInSession(email, password);
                                                                Toast.makeText(LoginActivity.this, "Welcome User, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                                                                loadingBar.dismiss();

                                                                Intent intent = new Intent(LoginActivity.this, AdminMainPage.class);
                                                                startActivity(intent);
                                                                finish();
                                                                return;
                                                            } else {
                                                                Toast.makeText(LoginActivity.this, "User with" + user.getDisplayName() + "does does not exist", Toast.LENGTH_SHORT).show();
                                                                // WE HAVE TO UPDATE THE CURRENT UI AND GO TO THE NEXT ACTIVITY WHICH IS MAP

                                                            }
                                                        }
                                                    });
                                                }
                                            }

                                            // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                                            // WHICH IS CUSTOMER TO BE ADDED.
                                            // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
                                        }
                                    }

                                    ;

                                };
                                // Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                //   startActivity(intent);
                            }
                                /*
                                else if (parentDbName.equals("Customers") && role.equals("Customer")) {
                                    Toast.makeText(AdminLogin.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                    loadingBar.dismiss();

                                    firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
                                        @Override
                                        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                                            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                            if (user != null) {
                                                traderoruser = "";
                                                traderoruser = user.getUid();

                                                if (FirebaseDatabase.getInstance().getReference().child("Users").child(parentDbName).child(traderoruser) != null && role.equals("Customer")) {

                                                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {

                                                            if (task.isSuccessful()) {
                                                                Log.d(TAG, "signInWithEmail:success");
                                                                String user_id = mAuth.getCurrentUser().getUid();

                                                                Toast.makeText(AdminLogin.this, "Welcome Trader, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                                                                loadingBar.dismiss();

                                                                Intent intent = new Intent(AdminLogin.this, HomeActivity.class);
                                                                startActivity(intent);
                                                                finish();


                                                                return;
                                                            } else {
                                                                loadingBar.dismiss();
                                                                Toast.makeText(AdminLogin.this, "User with" + user.getDisplayName() + "does does not exist", Toast.LENGTH_SHORT).show();
                                                                // WE HAVE TO UPDATE THE CURRENT UI AND GO TO THE NEXT ACTIVITY WHICH IS MAP

                                                            }
                                                        }
                                                    });

                                                }

                                            }


                                        }
                                    };


 */


                            // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                            // WHICH IS CUSTOMER TO BE ADDED.
                            // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
                            ;





                            ;}

                    }




                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
    }



    @Override
    protected void onStop() {
        super.onStop();
        //     mProgress.hide();
        mAuth.removeAuthStateListener(firebaseAuthListener);

    }

}