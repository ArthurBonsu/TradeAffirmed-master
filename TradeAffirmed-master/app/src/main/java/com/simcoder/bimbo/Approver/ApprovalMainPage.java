package com.simcoder.bimbo.Approver;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.simcoder.bimbo.Admin.AdminAddNewProductActivityII;
import com.simcoder.bimbo.Admin.AdminAllCustomers;
import com.simcoder.bimbo.Admin.AdminAllOrderHistory;
import com.simcoder.bimbo.Admin.AdminAllProducts;
import com.simcoder.bimbo.Admin.AdminCustomerServed;
import com.simcoder.bimbo.Admin.AdminNewOrdersActivity;
import com.simcoder.bimbo.Admin.AdminPaymentHere;
import com.simcoder.bimbo.Admin.AdminSettings;
import com.simcoder.bimbo.Admin.AllGoodsBought;
import com.simcoder.bimbo.Admin.AllProductsPurchased;
import com.simcoder.bimbo.Admin.NotTraderActivity;
import com.simcoder.bimbo.Admin.SearchForAdminProductsActivity;
import com.simcoder.bimbo.Admin.TradersFollowing;
import com.simcoder.bimbo.Admin.ViewAllCarts;
import com.simcoder.bimbo.Admin.ViewAllCustomers;
import com.simcoder.bimbo.DriverLoginActivity;
import com.simcoder.bimbo.DriverMapActivity;
import com.simcoder.bimbo.HistoryActivity;
import com.simcoder.bimbo.Model.HashMaps;
import com.simcoder.bimbo.Model.Products;
import com.simcoder.bimbo.Model.Users;
import com.simcoder.bimbo.R;
import com.simcoder.bimbo.WorkActivities.CartActivity;
import com.simcoder.bimbo.WorkActivities.HomeActivity;
import com.simcoder.bimbo.WorkActivities.LoginActivity;
import com.simcoder.bimbo.WorkActivities.TraderProfile;
import com.simcoder.bimbo.instagram.Home.InstagramHomeActivity;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class ApprovalMainPage extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {
    private static final int GALLERY_REQUEST2 = 2;
    private EditText   Nameinfo, Emailinfo, Phoneinfo;

    private Button mBack, mConfirm;

    private ImageView mProfileImage;

    private FirebaseAuth mAuth;
    private DatabaseReference mAdminTraderDatabase;

    private String userID;
    private String mName;
    private String mPhone;
    String  mEmail;
    private String mCar;
    String mService;
    private String mProfileImageUrl;
    Spinner mySpinner;

    String NameinfoString;
    String EmailinfoString;
    String PhoneinfoString;

    private Uri mImageUri = null;
    private static final int GALLERY_REQUEST = 1;

    private Uri resultUri;
    String text;
    private RadioGroup mRadioGroup;
    RadioButton FemaleRadioButton;
    RadioButton MaleRadioButton;
    String role;
    String traderID;
    RadioButton radioButtonforgender;
    String radiotext;

    String thenameinfostring;
    String theemailinfostring;
    String thephoneinfostring;
    String agetext;
    String countrytext;

    EditText NameofPerson;
    EditText  PhoneNumberOfPerson;
    EditText PersonEmail;
    RadioGroup genderspinnerradiogroup;
    RadioButton MaleRadiobutton;

    RadioButton FemaleMaleRadiobutton;
    Spinner agespinner;
    Spinner NationalIDofEmergencyPerson;
    Spinner countryspinner;
    Button saveinformationhere;
    ImageButton movetonext;
    ImageButton homebutton;
    ImageButton suggestionsbutton;
    ImageButton       services;
    ImageButton  expectedshipping;
    ImageButton       adminprofile;
    EditText MailingAddress;
    EditText        GpsCode;
    Spinner CountrySpinner;
    EditText  StreetAddress;
    ImageButton      home_icon;

    String  themailingaddressstring;
    String  thegpscodestring;
    String thestreetaddressstring;

    EditText EmergencyPersonName;
    EditText        EmergencyPersonPhoneNumber;
    EditText EmergencyPersonEmail;
    Spinner        typeofidspinner;
    ArrayAdapter<String> myIDAdapter;
    ArrayAdapter<String> mycountryAdapter;
    String theemergencypersonnamestring;
    String theemergencypersonphonestring;
    String theemergencypersonemailstring;
    String typeofidtext;
    String thenationalidstring;
    String thegpscodeinformationstring;


    private ImageButton mEventImage;
    private EditText mEventtitle;
    private EditText mEventDescription;
    private EditText mEventDate;

    String mPostKey;
    String churchkey;
    private Button msubmitButton;

    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseCHURCHCHOSEN;
    private ProgressDialog mProgress;
    private FirebaseAuth Auth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUser;

    private String CategoryName, Description, Price, Pname, saveCurrentDate, saveCurrentTime;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText InputProductName, InputProductDescription, InputProductPrice;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    private DatabaseReference mApproverDatabase;
    private ProgressDialog loadingBar;
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    String traderkeryhere;
    FirebaseUser user;
    Uri ImageStore;
    Intent intent;
    String date;
    String time;
    String userid;
    String productkey;
    String mytraderimage;
    String idcode;
    //AUTHENTICATORS

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Google Activity";

    private GoogleSignInClient mGoogleSignInClient;

    ImageView imagetobesetto;
    ImageButton setimagebutton;
    ImageButton AddimageButon;

    FirebaseDatabase  myuserfirebasedatabase;
    String titleval;
    String descval;
    String  price;
    String tradername;
    String myphotoimage;
    String traderid;
    String pimage;
    String tid;
    String pname;
    String  desc;
    String pid;
    String traderimage;
    String idimage;

    String gpscode;
    String gpsimage;
    Button movetohome;

    Button zoneshop;
    Button zonemap;
    Button zonetalkhub;
    Button approvalhome;

    Button approverclientlogin;
    Button approvertraderlogin;
    String approverID;

    public ApprovalMainPage() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approvalmainpage);
        Intent roleintent = getIntent();

        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent traderIDintent = getIntent();
        if (traderIDintent.getExtras().getString("approvalID") != null) {
            approverID = traderIDintent.getExtras().getString("approvalID");
        }

        approvalhome = findViewById(R.id.approvalhome);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();


        user = mAuth.getCurrentUser();
        if (user != null) {
            approverID = "";
            approverID = user.getUid();
        }
        Paper.init(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.hometoolbar);
        if (toolbar != null) {
            toolbar.setTitle("Approval Main Page");
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            if (toggle != null) {
                toggle.syncState();
            }

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            if (navigationView != null) {
                navigationView.setNavigationItemSelectedListener(this);
            }
            View headerView = navigationView.getHeaderView(0);
            TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
            CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);

            // USER


            mAuth = FirebaseAuth.getInstance();

            if (mAuth != null) {
                FirebaseUser user = mAuth.getCurrentUser();


                if (user.getDisplayName() != null) {
                    if (user.getDisplayName() != null) {
                        userNameTextView.setText(user.getDisplayName());

                        Picasso.get().load(user.getPhotoUrl()).placeholder(R.drawable.profile).into(profileImageView);
                    }
                }


                // GET FROM FOLLOWING KEY
                // HAVE TO BUILD THE STORAGE
                mStorage = FirebaseStorage.getInstance().getReference().child("user_images");
                myuserfirebasedatabase = FirebaseDatabase.getInstance();
                mApproverDatabase = myuserfirebasedatabase.getReference().child("Users").child("Approvers").child(approverID);

                mApproverDatabase.keepSynced(true);
/*
                if (ProductsRef.push() != null) {
                    productRandomKey = ProductsRef.push().getKey();

                }
*/
                //I have to  check to ensure that gallery intent is not placed here for the other classes
                mProgress = new ProgressDialog(this);


                mAuth = FirebaseAuth.getInstance();
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
                if (mGoogleApiClient != null) {
                    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
                }

                if (mGoogleApiClient != null) {
                    mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(ApprovalMainPage.this,
                            new GoogleApiClient.OnConnectionFailedListener() {
                                @Override
                                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                                }
                            }).addApi(com.google.android.gms.auth.api.Auth.GOOGLE_SIGN_IN_API, gso).build();
                }
                buildGoogleApiClient();


            }
        }}

    protected synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(ApprovalMainPage.this)
                    .addOnConnectionFailedListener(ApprovalMainPage.this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }




        getUserInfo();
        // SET THE AGE ADAPTER
        // CHOSE ID CARD
        // UPLOAD ID CARD





        if (approvalhome != null) {
            approvalhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ApprovalMainPage.this, DriverLoginActivity.class);
                    if (intent != null) {
                        intent.putExtra("role", role);
                        intent.putExtra("approverID", approverID);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }

        }


    // You get the information here and send it to the top
    // OnActivity result is lacking behind, I have to get the URi from it
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // PICK NATIONAL ID INFORMATION
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {

            Uri imageUri = data.getData();

            CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1, 1).start(this);


        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mImageUri = result.getUri();

                InputProductImage.setImageURI(mImageUri);


            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }

        }


    }




    //POPULATE THE EDIT BOX IF THERE ALREADY EXIST SUCH A TRANSACTION
    public void getUserInfo() {
        mApproverDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue(HashMaps.class);
                    if (map.get("address") != null) {
                        String themailingaddress = map.get("address").toString();

                        MailingAddress.setText(themailingaddress);


                    }
                    if (map.get("gpscode") != null) {
                        String thegpscode = map.get("gpscode").toString();
                        GpsCode.setText(mPhone);
                    }

                    if (map.get("street") != null) {
                        String thestreetaddress = map.get("street").toString();
                        StreetAddress.setText(thestreetaddress);
                    }

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }








    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    approverID = "";
                    approverID = user.getUid();
                }

                // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                // WHICH IS CUSTOMER TO BE ADDED.
                // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
            }
        };



        if (mAuth != null) {
            mAuth.addAuthStateListener(firebaseAuthListener);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mAuth !=null) {
            mAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (getMenuInflater() != null) {
            getMenuInflater().inflate(R.menu.traderscontrol, menu);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings)
//        {
//            return true;
//        }




        if (id == R.id.viewallcustomershere) {
            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("trader", traderID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";
                        cusomerId = user.getUid();

                        Intent intent = new Intent(ApprovalMainPage.this, AdminAllCustomers.class);
                        if (intent != null) {
                            intent.putExtra("traderID", traderID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }



        if (id == R.id. allcustomersincart) {

            if (!role.equals("Approver")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderID", approverID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";
                        cusomerId = user.getUid();

                        Intent intent = new Intent(ApprovalMainPage.this, ViewAllCarts.class);
                        if (intent != null) {
                            intent.putExtra("traderID", traderID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }



        if (id == R.id.addnewproducthere) {
            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderID", approverID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";
                        cusomerId = user.getUid();

                        Intent intent = new Intent(ApprovalMainPage.this, AdminAddNewProductActivityII.class);
                        if (intent != null) {
                            intent.putExtra("traderID", approverID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }

        if (id == R.id.allproductshere) {
            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderID", approverID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";
                        cusomerId = user.getUid();

                        Intent intent = new Intent(ApprovalMainPage.this, AdminAllProducts.class);
                        if (intent != null) {
                            intent.putExtra("traderID", approverID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }}

                if (id == R.id.allproductspurchased) {
                    if (!role.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderID", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    } else {
                        if (FirebaseAuth.getInstance() != null) {

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";
                                cusomerId = user.getUid();

                                Intent intent = new Intent(ApprovalMainPage.this, AllProductsPurchased
                                        .class);
                                if (intent != null) {
                                    intent.putExtra("traderID", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }


                if (id == R.id. viewallcustomershere) {
                    if (!role.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderID", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    } else {
                        if (FirebaseAuth.getInstance() != null) {

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";
                                cusomerId = user.getUid();

                                Intent intent = new Intent(ApprovalMainPage.this, ViewAllCustomers.class);
                                if (intent != null) {
                                    intent.putExtra("traderID", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }

                if (id == R.id.tradersfollowing) {
                    if (!role.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderID", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    } else {
                        if (FirebaseAuth.getInstance() != null) {

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";
                                cusomerId = user.getUid();

                                Intent intent = new Intent(ApprovalMainPage.this, TradersFollowing.class);
                                if (intent != null) {
                                    intent.putExtra("traderID", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }


                if (id == R.id.AdminNewOrders) {

                    if (!role.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderID", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    } else {
                        if (FirebaseAuth.getInstance() != null) {

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";
                                cusomerId = user.getUid();

                                Intent intent = new Intent(ApprovalMainPage.this, AdminNewOrdersActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderID", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }


                if (id == R.id.allcustomersserved) {

                    if (!role.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderID", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    } else {
                        if (FirebaseAuth.getInstance() != null) {

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";
                                cusomerId = user.getUid();

                                Intent intent = new Intent(ApprovalMainPage.this, AdminCustomerServed.class);
                                if (intent != null) {
                                    intent.putExtra("trader", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }

                if (id == R.id.allordershistory) {

                    if (!role.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("trader", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    } else {
                        if (FirebaseAuth.getInstance() != null) {

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";
                                cusomerId = user.getUid();

                                Intent intent = new Intent(ApprovalMainPage.this, AdminAllOrderHistory.class);
                                if (intent != null) {
                                    intent.putExtra("trader", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }


            }
        }


        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected (MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.viewmap) {
            if (!role.equals("Trader")) {

                Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                if (intent != null) {
                    intent.putExtra("trader", approverID);
                    intent.putExtra("role", role);
                    startActivity(intent);
                    finish();
                }
            } else {

                Intent intent = new Intent(ApprovalMainPage.this, DriverMapActivity.class);
                if (intent != null) {
                    intent.putExtra("trader", approverID);
                    intent.putExtra("role", role);
                    startActivity(intent);
                    finish();
                }
            }


        }


        if (id == R.id.nav_cart) {
            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("trader", approverID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";
                        cusomerId = user.getUid();

                        Intent intent = new Intent(ApprovalMainPage.this, CartActivity.class);
                        if (intent != null) {
                            intent.putExtra("trader", approverID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }

            }


            if (id == R.id.nav_social_media) {
                if (!role.equals("Trader")) {
                    if (FirebaseAuth.getInstance() != null) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            String cusomerId = "";

                            cusomerId = user.getUid();
                            Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                            if (intent != null) {
                                intent.putExtra("trader", approverID);
                                intent.putExtra("role", role);
                                startActivity(intent);
                            }
                        }
                    }
                } else {
                    if (FirebaseAuth.getInstance() != null) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            String cusomerId = "";
                            cusomerId = user.getUid();

                            Intent intent = new Intent(ApprovalMainPage.this, InstagramHomeActivity.class);
                            if (intent != null) {
                                intent.putExtra("trader", approverID);
                                intent.putExtra("role", role);
                                startActivity(intent);
                            }
                        }
                    }

                }


                if (id == R.id.viewproducts) {
                    if (!role.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("trader", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }
                    } else {
                        if (FirebaseAuth.getInstance() != null) {

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";
                                cusomerId = user.getUid();

                                Intent intent = new Intent(ApprovalMainPage.this, AdminAllProducts.class);
                                if (intent != null) {
                                    intent.putExtra("trader", approverID);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            }
                        }

                        if (id == R.id.nav_searchforproducts) {
                            if (!role.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            } else {
                                if (FirebaseAuth.getInstance() != null) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";
                                        cusomerId = user.getUid();

                                        Intent intent = new Intent(ApprovalMainPage.this, SearchForAdminProductsActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }

                        if (id == R.id.nav_logout) {

                            if (FirebaseAuth.getInstance() != null) {
                                FirebaseAuth.getInstance().signOut();
                                if (mGoogleApiClient != null) {
                                    mGoogleSignInClient.signOut().addOnCompleteListener(ApprovalMainPage.this,
                                            new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                }
                                            });
                                }
                            }
                            Intent intent = new Intent(ApprovalMainPage.this, com.simcoder.bimbo.MainActivity.class);
                            if (intent != null) {
                                startActivity(intent);
                                finish();
                            }
                        }

                        if (id == R.id.nav_settings) {
                            if (!role.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            } else {
                                if (FirebaseAuth.getInstance() != null) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";
                                        cusomerId = user.getUid();

                                        Intent intent = new Intent(ApprovalMainPage.this, com.simcoder.bimbo.WorkActivities.SettinsActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }
                        if (id == R.id.nav_history) {
                            if (!role.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            } else {
                                if (FirebaseAuth.getInstance() != null) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";
                                        cusomerId = user.getUid();

                                        Intent intent = new Intent(ApprovalMainPage.this, HistoryActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }


                        if (id == R.id.nav_viewprofilehome) {
                            if (!role.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            } else {
                                if (FirebaseAuth.getInstance() != null) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";
                                        cusomerId = user.getUid();

                                        Intent intent = new Intent(ApprovalMainPage.this, TraderProfile.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }


                        if (id == R.id.viewallcustomershere) {
                            if (!role.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            } else {
                                if (FirebaseAuth.getInstance() != null) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";
                                        cusomerId = user.getUid();

                                        Intent intent = new Intent(ApprovalMainPage.this, AdminAllCustomers.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }


                        if (id == R.id.addnewproducthere) {

                            if (!role.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            } else {
                                if (FirebaseAuth.getInstance() != null) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";
                                        cusomerId = user.getUid();

                                        Intent intent = new Intent(ApprovalMainPage.this, AdminAddNewProductActivityII.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }


                        if (id == R.id.goodsbought) {
                            if (!role.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            } else {
                                if (FirebaseAuth.getInstance() != null) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";
                                        cusomerId = user.getUid();

                                        Intent intent = new Intent(ApprovalMainPage.this, AllGoodsBought.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }


                        if (id == R.id.nav_paymenthome) {
                            if (!role.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            } else {
                                if (FirebaseAuth.getInstance() != null) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";
                                        cusomerId = user.getUid();

                                        Intent intent = new Intent(ApprovalMainPage.this, AdminPaymentHere.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }


                        if (id == R.id.nav_settings) {
                            if (!role.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(ApprovalMainPage.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            } else {
                                if (FirebaseAuth.getInstance() != null) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";
                                        cusomerId = user.getUid();

                                        Intent intent = new Intent(ApprovalMainPage.this, AdminSettings.class);
                                        if (intent != null) {
                                            intent.putExtra("trader", approverID);
                                            intent.putExtra("role", role);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }


                    }
                }


                return true;
            }

            return true;
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}




