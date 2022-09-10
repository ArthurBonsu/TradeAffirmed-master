package com.simcoder.bimbo.WorkActivities;

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

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;

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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.simcoder.bimbo.Admin.AdminAddNewProductActivity;
import com.simcoder.bimbo.Admin.AdminAllCustomers;
import com.simcoder.bimbo.Admin.AdminCategoryActivity;
import com.simcoder.bimbo.Admin.AdminMaintainProductsActivity;
import com.simcoder.bimbo.Admin.AdminProductDetails;
import com.simcoder.bimbo.Admin.ViewAllCarts;
import com.simcoder.bimbo.Admin.ViewSpecificUsersCart;
import com.simcoder.bimbo.Admin.ViewYourPersonalProduct;
import com.simcoder.bimbo.DriverMapActivity;
import com.simcoder.bimbo.HistoryActivity;
import com.simcoder.bimbo.Model.SecurityInfoSubmitModel;
import com.simcoder.bimbo.Model.Users;
import com.simcoder.bimbo.R;
import com.simcoder.bimbo.instagram.Home.InstagramHomeActivity;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class SecurityInfo extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {
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
    DatabaseReference mCustomerUserDatabase;
    DatabaseReference mApprovalOrig;
    DatabaseReference mApproval;
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
    private  DatabaseReference mApprovalDatabaseFull;
    private ProgressDialog mProgress;
    private FirebaseAuth Auth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUser;
    private DatabaseReference mApprovalDatabase;

    private String CategoryName, Description, Price, Pname, saveCurrentDate, saveCurrentTime;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText InputProductName, InputProductDescription, InputProductPrice;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    private DatabaseReference ProductsTraderRef;
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

    String pimage;
    String tid;
    String pname;
    String  desc;
    String pid;
    String traderimage;
    String idimage;

    String gpscode;
    String gpsimage;

    EditText    GPSCodeIdText;
    ImageView GpsCodeMapID;
    Button GPSPickButton;
    Button deleteGPSCodeButton;
    Button GPSCodeButton;
    ImageView  nationalidpic;
    Button NationalIDPickButton;
    Button  NationalIDUploadButton;
    Button NationalIDDeleteButton;

    String approvalkey;
    String approvalID;
    String username;
    String securityapprovalapprove;
    String userimage;
    String status;








    public SecurityInfo() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_check2);
        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent userIDintent = getIntent();
        if (userIDintent.getExtras().getString("userID") != null) {
            userID = userIDintent.getExtras().getString("userID");
        }
        Intent approvalIDIntent = getIntent();
        if (approvalIDIntent.getExtras().getString("approvalID") != null) {
            approvalID = approvalIDIntent.getExtras().getString("approvalID");
        }



        //  deletenationalidpicture  nationalidpicture
        //        // gps code GpsCodeMapID,
        //        // pick up  PickMap
        //        // ImageViewOfGPSCodeMap (GPSCodeID)



        saveinformationhere = findViewById(R.id.saveinformationhere);
        movetonext = findViewById(R.id.movetonext);
        homebutton = findViewById(R.id.homebutton);
        suggestionsbutton = findViewById(R.id.suggestionsbutton);
        services = findViewById(R.id.services);
        expectedshipping = findViewById(R.id.expectedshipping);
        adminprofile = findViewById(R.id.adminprofile);


        GPSCodeIdText = (EditText) findViewById(R.id.GPSCodeIdText);

        GpsCodeMapID = (ImageView)findViewById(R.id.GpsCodeMapID );
        GPSPickButton = (Button)findViewById(R.id.GPSPickButton);
        deleteGPSCodeButton = (Button) findViewById(R.id.deleteGPSCodeButton);
        GPSCodeButton = (Button)findViewById(R.id.GPSCodeButton);

        thegpscodeinformationstring= GPSCodeIdText.getText().toString();



        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();


        user = mAuth.getCurrentUser();
        if (user != null) {
            userID = "";
            userID = user.getUid();
        }
        Paper.init(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.hometoolbar);
        if (toolbar != null) {
            toolbar.setTitle("GPS Code Upload");
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


                mCustomerUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID);
                mApprovalOrig = FirebaseDatabase.getInstance().getReference().child("Approval");
                approvalID =  mApprovalOrig.push().getKey();
                mApproval = FirebaseDatabase.getInstance().getReference().child("Approval").child(approvalID);
                mCustomerUserDatabase.keepSynced(true);
                mApproval.keepSynced(true);

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
                    mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(SecurityInfo.this,
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
                    .addConnectionCallbacks(SecurityInfo.this)
                    .addOnConnectionFailedListener(SecurityInfo.this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }



        // PICK UP MAP LOCATION IN ALBUMS
        GPSPickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        // UPLOAD ID CARD
        GpsCodeMapID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }

        });

        //DELETE ID CARD

        deleteGPSCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePosting();
            }

        });








        if (movetonext != null) {
            movetonext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // if pending == true or if registration == true
                    Intent intent = new Intent(SecurityInfo.this, ClientVerificationPendingPage.class);
                    if (intent != null) {
                        intent.putExtra("role", role);
                        intent.putExtra("userID", userID);
                        intent.putExtra("approvalID", approvalID);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }


    }




    // Post Info
    public void startPosting() {

        // GET THE INFORMATION FROM THE TEXT BOX


        thegpscodeinformationstring= GPSCodeIdText.getText().toString();


        user = mAuth.getCurrentUser();

        // GET DATES FOR PRODUCTS
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");

        if (currentDate != null) {
            date = currentDate.format(calendar.getTime()).toString();

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            if (currentTime != null) {
                time = currentTime.format(calendar.getTime());

            }


            if (!TextUtils.isEmpty(thegpscodeinformationstring)  && mImageUri != null) {
                mProgress.setMessage("Adding your GPS Check information");

                mProgress.show();

                // CHECK STORAGE FOR IMAGE AND PASS IMAGES GOTTEN THERE
                StorageReference filepath = mStorage.child(mImageUri.getLastPathSegment());

                filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        gpscode = thegpscodeinformationstring;



                        final Uri downloadUrl = taskSnapshot.getUploadSessionUri();

                        userID = user.getUid();
                       username = user.getDisplayName();
                        gpsimage = downloadUrl.toString();

                        Uri myphoto = user.getPhotoUrl();
                       userimage = myphoto.toString();
                        status = "pending";
                        securityapprovalapprove = "false";

                        // PICK UP THE SPECIAL PRODUCT INFO AND LOADING THEM INTO THE DATABASE
                        SecurityInfoSubmitModel securityinfosubmit = new SecurityInfoSubmitModel (gpscode,gpsimage,approvalID, securityapprovalapprove, status);
                         /*
                        mCustomerUserDatabase.setValue(securityinfosubmit, new
                                DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError databaseError, DatabaseReference
                                            databaseReference) {
                                        Toast.makeText(getApplicationContext(), "Add GPS Code Information Added", Toast.LENGTH_SHORT).show();
                                        Intent addgpscodeinformationintent = new Intent(SecurityInfo.this, SecurityInfo.class);

                                        startActivity(addgpscodeinformationintent);

                                    }
                                });
*/
                        mApprovalDatabase.setValue(securityinfosubmit, new
                        DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference
                                    databaseReference) {
                                Toast.makeText(getApplicationContext(), "GPS Security Information Deleted, Reuplod Info", Toast.LENGTH_SHORT).show();
                                Intent myapprovaldatabaseintent = new Intent(SecurityInfo.this, ClientVerificationPendingPage.class);
                                  myapprovaldatabaseintent.putExtra("userID", userID);
                                myapprovaldatabaseintent.putExtra("role", role);
                                myapprovaldatabaseintent.putExtra("approvalID", approvalID);
                                startActivity(myapprovaldatabaseintent);

                            }
                        });


        mProgress.dismiss();
                    }

                });

            }
        }
    }






    // Post Info
    public void deletePosting() {

        // GET THE INFORMATION FROM THE TEXT BOX


        thegpscodeinformationstring= GPSCodeIdText.getText().toString();

        user = mAuth.getCurrentUser();

        // GET DATES FOR PRODUCTS
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");

        if (currentDate != null) {
            date = currentDate.format(calendar.getTime()).toString();

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            if (currentTime != null) {
                time = currentTime.format(calendar.getTime());

            }


            if (!TextUtils.isEmpty(thegpscodeinformationstring)  && mImageUri != null) {
                mProgress.setMessage("Adding your GPS security check information");

                mProgress.show();

                // CHECK STORAGE FOR IMAGE AND PASS IMAGES GOTTEN THERE
                StorageReference filepath = mStorage.child(mImageUri.getLastPathSegment());

                filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        gpscode = "";
                        final Uri downloadUrl = taskSnapshot.getUploadSessionUri();

                        userID = user.getUid();
                        username = user.getDisplayName();
                        gpsimage ="";
                        status ="";
                        securityapprovalapprove= "";




                        Uri myphoto = user.getPhotoUrl();
                        traderimage = myphoto.toString();





                        mApprovalDatabaseFull = myuserfirebasedatabase.getReference().child("Approval");

                        mApprovalDatabase = myuserfirebasedatabase.getReference().child("Approval").child(approvalID);

                        mApprovalDatabase.keepSynced(true);
                        // PICK UP THE SPECIAL PRODUCT INFO AND LOADING THEM INTO THE DATABASE
                        /*
                        mCustomerUserDatabase.setValue(securityinfotobesent, new
                            DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference
                        databaseReference) {
                            Toast.makeText(getApplicationContext(), "GPS Security Information Deleted, Reupload Info", Toast.LENGTH_SHORT).show();
                            Intent securityinfotobesentapproval = new Intent(SecurityInfo.this, SecurityInfo.class);

                            startActivity(securityinfotobesentapproval);

                        }
                    });
*/

                        SecurityInfoSubmitModel securityinfotobesent = new SecurityInfoSubmitModel (gpscode,gpsimage,approvalID, securityapprovalapprove, status);

                        mApprovalDatabase.setValue(securityinfotobesent, new
                                DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError databaseError, DatabaseReference
                                            databaseReference) {
                                        Toast.makeText(getApplicationContext(), "GPS Security Information Deleted, Reuplod Info", Toast.LENGTH_SHORT).show();
                                        Intent securityapprovaldatabaseinfointent = new Intent(SecurityInfo.this, SecurityInfo.class);
                                        securityapprovaldatabaseinfointent.putExtra("userID", userID);
                                        securityapprovaldatabaseinfointent.putExtra("role", role);
                                        securityapprovaldatabaseinfointent.putExtra("approvalID", approvalID);
                                        startActivity(securityapprovaldatabaseinfointent);

                                    }
                                });
                }

                });


                mProgress.dismiss();

            }


        }}


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

                GpsCodeMapID.setImageURI(mImageUri);


            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }

        }


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
                    userID = "";
                    userID = user.getUid();
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
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, AdminAllCustomers.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, AdminAllCustomers.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
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
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, AdminAddNewProductActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, AdminAddNewProductActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.singeuserorderhere) {

            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, ViewYourPersonalProduct.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, ViewYourPersonalProduct.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.viewbuyershere) {

            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, ViewSpecificUsersCart.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, ViewSpecificUsersCart.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.usercartedactivityhere) {

            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, ViewAllCarts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, ViewAllCarts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.newproductdetailshere) {
            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, AdminProductDetails.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, AdminProductDetails.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.Maintainnewproducts) {
            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, AdminMaintainProductsActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, AdminMaintainProductsActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.allcategorieshere) {

            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, AdminCategoryActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, AdminCategoryActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
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
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, AdminAllCustomers.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, AdminAllCustomers.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }}}

        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected (MenuItem item)
    {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.viewmap) {
            if (!role.equals("Trader")) {

                Intent intent = new Intent(SecurityInfo.this, com.simcoder.bimbo.CustomerMapActivity.class);
                if (intent != null) {
                    intent.putExtra("roledhomeactivitytocustomermapactivity", role);
                    intent.putExtra("fromhomeactivitytocustomermapactivity", userID);
                    startActivity(intent);
                    finish();
                }
            } else {

                Intent intent = new Intent(SecurityInfo.this, DriverMapActivity.class);
                if (intent != null) {
                    intent.putExtra("rolefromhomeactivitytodrivermapactivity", role);
                    intent.putExtra("fromhomeactivitytodrivermapactivity", userID);
                    startActivity(intent);
                    finish();
                }
            }


        }


        if (id == R.id.nav_social_media) {
            if (!role.equals("Trader")) {

                Intent intent = new Intent(SecurityInfo.this, InstagramHomeActivity.class);
                if (intent != null) {
                    intent.putExtra("roledhomeactivitytocustomermapactivity", role);
                    intent.putExtra("fromhomeactivitytocustomermapactivity", userID);
                    startActivity(intent);
                    finish();
                }
            } else {

                Intent intent = new Intent(SecurityInfo.this, InstagramHomeActivity.class);
                if (intent != null) {
                    intent.putExtra("rolefromhomeactivitytodrivermapactivity", role);
                    intent.putExtra("fromhomeactivitytodrivermapactivity", userID);
                    startActivity(intent);
                    finish();
                }
            }


        }
        if (id == R.id.nav_cart) {
            if (!role.equals("Trader")) {
                Intent intent = new Intent(SecurityInfo.this, CartActivity.class);
                if (intent != null) {
                    startActivity(intent);
                }
            }

        }
        //   checkfeed

        if (id == R.id.viewproducts) {
            if (!role.equals("Trader")) {
                Intent intent = new Intent(SecurityInfo.this, HomeActivity.class);
                if (intent != null) {
                    startActivity(intent);
                }
            } else {
            }

        }
        if (id == R.id.nav_search) {
            if (!role.equals("Trader")) {
                Intent intent = new Intent(SecurityInfo.this, SearchProductsActivity.class);
                if (intent != null) {
                    startActivity(intent);
                }
            } else {
            }
        }

        if (id == R.id.nav_logout) {

            if (FirebaseAuth.getInstance() != null) {
                FirebaseAuth.getInstance().signOut();
                if (mGoogleApiClient != null) {
                    mGoogleSignInClient.signOut().addOnCompleteListener(SecurityInfo.this,
                            new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                }
            }
            Intent intent = new Intent(SecurityInfo.this, com.simcoder.bimbo.MainActivity.class);
            if (intent != null) {
                startActivity(intent);
                finish();
            }
        }

        if (id == R.id.nav_settings) {
            if (!role.equals("Trader")) {
                Intent intent = new Intent(SecurityInfo.this, com.simcoder.bimbo.WorkActivities.SettinsActivity.class);
                if (intent != null) {
                    startActivity(intent);
                }
            } else {
            }
        }
        if (id == R.id.nav_history) {
            if (!role.equals("Trader")) {
                Intent intent = new Intent(SecurityInfo.this, HistoryActivity.class);
                if (intent != null) {
                    startActivity(intent);
                }
            } else {
            }
        }
        if (id == R.id.nav_categories) {

        }
        if (id == R.id.nav_viewprofilehome) {
            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, CustomerProfile.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, TraderProfile.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
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
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, AdminAllCustomers.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
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
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, AdminAddNewProductActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }

        //   checkfeed
        if (id == R.id.singeuserorderhere) {

            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, ViewYourPersonalProduct.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.viewbuyershere) {

            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, ViewSpecificUsersCart.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.usercartedactivityhere) {

            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, ViewAllCarts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.newproductdetailshere) {
            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, AdminProductDetails.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.Maintainnewproducts) {
            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, AdminMaintainProductsActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.allcategorieshere) {

            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, AdminCategoryActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
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
                        String userID = "";

                        userID = user.getUid();
                        Intent intent = new Intent(SecurityInfo.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            } else {
                if (FirebaseAuth.getInstance() != null) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userID = "";
                        userID = user.getUid();

                        Intent intent = new Intent(SecurityInfo.this, AdminAllCustomers.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }

 /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
   */
        return true;
    }



}

