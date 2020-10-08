package  com.simcoder.bimbo.instagram.Profile.EditProfileFragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.simcoder.bimbo.DriverMapActivity;
import com.simcoder.bimbo.Model.HashMaps;
import  com.simcoder.bimbo.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.simcoder.bimbo.historyRecyclerView.HistoryObject;
import com.simcoder.bimbo.instagram.Models.User;
import com.simcoder.bimbo.instagram.Models.UserSettings;
import com.simcoder.bimbo.instagram.Utils.FirebaseMethods;
import com.simcoder.bimbo.instagram.dialogs.ConfirmPasswordDialog;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class EditProfileFragment extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    private  DatabaseReference ProductsTraderRef;
    private ProgressDialog loadingBar;
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    String traderID;
    String role;
    String traderkeryhere;
    FirebaseUser user;
    Uri ImageStore;
    Intent intent;
    //AUTHENTICATORS

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Google Activity";
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    ImageView imagetobesetto;
    ImageButton setimagebutton;
    DatabaseReference myuserreference;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
     FirebaseDatabase mrolefirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseMethods mFirebaseMethods;
    private String userID;
    String userid;

    //Edit Profile Fragment Widgets
    private EditText mDisplayName, mUsername, mWebsite, mDescription, mEmail, mPhoneNumber;
    private TextView mChangeProfilePhoto;
    private CircleImageView mProfilePhoto;

    // vars
    private UserSettings mUserSettings;

    // TAKEN FROM AUTHENTICATION LISTENERS


    private SignInButton GoogleBtn;

    Intent signInIntent;
    private static final String TAG1 = "Google Activity";
    private ProgressDialog mProgress;

      String email;
      String username;
      String password;
    String website;
    String description;
    String phone;
    String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        Intent category = getIntent();
        if( category.getExtras().getString("category") != null) {
            CategoryName = category.getExtras().getString("category");
        }

        // KEYS PASSED IN FROM ADMINCATEGORY
        Intent roleintent = getIntent();
        if( roleintent.getExtras().getString("rolefromadmincategorytoaddadmin") != null) {
            role = roleintent.getExtras().getString("rolefromadmincategorytoaddadmin");
        }
        Intent traderintent = getIntent();
        if( traderintent.getExtras().getString("fromadmincategoryactivitytoaddadmin") != null) {
            traderID = category.getExtras().getString("fromadmincategoryactivitytoaddadmin");
        }


        ProfileStorageImage = FirebaseStorage.getInstance().getReference().child("userimages");



        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            traderoruserID = "";
            traderoruserID = user.getUid();
        }
        if (ProductsRef.push() != null) {
            productRandomKey = ProductsRef.push().getKey();

        }

        mProfilePhoto = (CircleImageView) findViewById(R.id.profile_photo);
        mDisplayName = (EditText) findViewById(R.id.display_name);
        mUsername = (EditText) findViewById(R.id.username);
        mWebsite = (EditText) findViewById(R.id.website);
        mDescription = (EditText) findViewById(R.id.description);
        mEmail = (EditText) findViewById(R.id.email);
        mPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        mChangeProfilePhoto = (TextView) findViewById(R.id.changeProfilePhoto);

        //  setimagebutton = (ImageView)findViewById(R.id.setimagebutton);
        loadingBar = new ProgressDialog(this);

        FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        if (mGoogleApiClient != null) {
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        }

        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(EditProfileFragment.this,
                    new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        }
                    }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        }
        buildGoogleApiClient();


        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    traderoruserID = "";
                    traderoruserID = user.getUid();
                }

                // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                // WHICH IS CUSTOMER TO BE ADDED.
                // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
            }
        };



        mAuth = FirebaseAuth.getInstance();

        if (mAuth != null) {
            FirebaseUser user = mAuth.getCurrentUser();


            if (user.getDisplayName() != null) {
                if (user.getDisplayName() != null) {
                    userNameTextView.setText(user.getDisplayName());

                    Picasso.get().load(user.getPhotoUrl()).placeholder(R.drawable.profile).into(profileImageView);
                }
            }


            if (mAuth != null) {
                user = mAuth.getCurrentUser();
                if (user != null) {
                    traderoruserID = user.getUid();

                }


                myfirebaseDatabase = FirebaseDatabase.getInstance();
        //         myfirebaseDatabase = FirebaseDatabase.getInstance();

                 mrolefirebaseDatabase = FirebaseDatabase.getInstance();
                UsersRef = myfirebaseDatabase.getReference().child("Users");
                DatabaseReference = mrolefirebaseDatabase.getReference().child("Users").


                userkey = UsersRef.getKey();
                role =


                           if (role == 'Customer'){
                 myuserreference = FirebaseDatabase.getInstance().getReference().child("Users").child(traderoruserID);
                  specificuserkey = myuserreference.getKey();
                myrolereference.keepSynced(true);
                if (myrolereference != null) {
                    myrolereference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                if (dataSnapshot != null) {
                                    dataSnapshot.getValue(User.class);
                                    role = dataSnapshot.child("role").getValue(String.class);
                                    String email = dataSnapshot.child().getValue();
                                    String username = dataSnapshot.getKey();
                                    String password = "";
                                    String website = "";
                                    String description = "";
                                    String phone = " ";
                                    String image = "";
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
                else{

                     myuserreference = FirebaseDatabase.getInstance().getReference().child("Users").child(traderoruserID);
                    specificuserkey = myuserreference.getKey();
                    myrolereference.keepSynced(true);
                    if (myrolereference != null) {
                        myrolereference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    if (dataSnapshot != null) {
                                        dataSnapshot.getValue(User.class);
                                        role = dataSnapshot.child("role").getValue(String.class);
                                        String email = dataSnapshot.child().getValue();
                                        String username = dataSnapshot.getKey();
                                        String password = "";
                                        String website = "";
                                        String description = "";
                                        String phone = " ";
                                        String image = "";
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }

                    Paper.init(this);

                    Toolbar toolbar = (Toolbar) findViewById(R.id.drivertoolbar);
                    if (toolbar != null) {
                        toolbar.setTitle("Trader MapView");
//        setSupportActionBar(toolbar);
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                        if (drawer != null) {
                            drawer.addDrawerListener(toggle);
                            if (toggle != null) {
                                toggle.syncState();

                            }
                        }
                    }
                    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

                    if (navigationView != null) {
                        navigationView.setNavigationItemSelectedListener(EditProfileFragment.this); }


                    View headerView = navigationView.getHeaderView(0);
                    TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
                    CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);
                    if (getIntent() != null) {
                        {
                            if (getIntent().getExtras().get("rolefromhomeactivitytodrivermapactivity") != null) {
                                role = getIntent().getExtras().get("rolefromhomeactivitytodrivermapactivity").toString();
                            }
                        }
                        if ( != null) {
                            if (getIntent() != null) {
                                driverId = getIntent().getStringExtra("fromhomeactivitytodrivermapactivity");
                            }

                            if (mydrivernavigations != null) {

                                mydrivernavigations.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getAssignedCustomerInfo();
                                    }
                                });
                            }
                        }
                    }
                }}}}


    protected synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(EditProfileFragment.this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }
    }

    //back arrow for navigating back to "ProfileActivity"
    ImageView backArrow = (ImageView)findViewById(R.id.backArrow);
        if (backArrow != null) {
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to ProfileActivity");
                if (getActivity() != null) {
                    getActivity().finish();

                }
            }
        });
    }

    // green checkmark icon to update user settings information
    ImageView checkmark = (ImageView) findViewById(R.id.saveChanges);
        if (checkmark != null) {
        checkmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: attempting to save changes.");
                saveProfileSettings();

            }
        });
    }



    /**
     * Retrieves the data from widgets and submits it to database
     * Checks that username is unique
     */


          //SAVE PROFILE SETTINGS HERE
    private void saveProfileSettings() {
        if (mDisplayName.getText() != null) {
            final String displayName = mDisplayName.getText().toString();

            if (mUsername.getText() != null) {
                final String username = mUsername.getText().toString();

                if (mDescription.getText() != null) {
                    final String description = mDescription.getText().toString();

                    if (mWebsite.getText() != null) {
                        final String website = mWebsite.getText().toString();

                        if (mEmail.getText() != null) {
                            final String email = mEmail.getText().toString();

                            if (mPhoneNumber.getText() != null) {
                                final String phoneNumber = mPhoneNumber.getText().toString();


                                // case1: if user changed to same name.
                                if (!mUserSettings.getUser().getname().equals(username)) {
                                    checkIfUsernameExists(username);
                                }
                                // case2: if user change their email
                                if (!mUserSettings.getUser().getemail().equals(email)) {
                                    //step1 Re-Auth
                                    //      - Confirm password and email
                                    ConfirmPasswordDialog dialog = new ConfirmPasswordDialog();
                                    if (dialog != null) {
                                        if (getFragmentManager() != null) {
                                            dialog.show(getFragmentManager(), getString(R.string.confirm_password_dialog));
                                        }

                                        dialog.setTargetFragment(EditProfileFragment.this, 1);
                                        //step2 Check if email already exists


                                        //      - 'fetchProvidersForEmail(String email)
                                        //step3 change email
                                        //      - submit new email to database and authentication

                                    }
                                }
                            }
                        }
                    }}
            }
        }
    }
/**
 * Change fields in Settings that doesn't require unique values
 */


/**
 * Check if @param username already exists in database
 * @param username
 */
    /**
     * Check if @param username already exists in database
     * @param username
     */
 // CHECK IF USERNAME EXIST AND FIREBASE CAN BE USED TO POPULATE ACTIVITY


    private void checkandpoulate(final String username) {
        if (username != null) {
            Log.d(TAG, "checkIfUsernameExists: Checking if " + username + " already exists");
            if (FirebaseAuth.getInstance() != null) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    userid = "";
                    userid = user.getUid();
                    if (FirebaseDatabase.getInstance() != null) {

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                        if (username != null) {
                            Query query = reference
                                    .child("Users").child("Customers")
                                    .orderByChild("name")
                                    .equalTo(username);
                            if (query != null) {
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot != null) {
                                            if (!dataSnapshot.exists()) {

                                                // add the username
                                                if (mFirebaseMethods != null) {
                                                    if (username != null) {
                                                        mFirebaseMethods.updateUsername(username);
                                                        Toast.makeText(getActivity(), "saved username.", Toast.LENGTH_SHORT).show();
                                                    }
                                                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                                                        if (singleSnapshot != null) {
                                                            if (singleSnapshot.exists()) {
                                                                if (singleSnapshot.child(userid).child("name").getValue(String.class) != null) {
                                                                    Log.d(TAG, "checkIfUsernameExists: FOUND A MATCH" + singleSnapshot.child(userid).child("name").getValue(String.class));
                                                                    Toast.makeText(getActivity(), "That username already exists.", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            }
                        }
                    }
                }
            }
        }
    }

    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        if (galleryIntent != null) {
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, GalleryPick);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {
            if (ImageUri != null) {
                if (data != null) {
                    ImageUri = data.getData();
                    ImageStore = ImageUri;
                    setimagebutton.setImageURI(ImageUri);

                    if (InputProductImage != null) {
                        InputProductImage.setImageURI(ImageUri);
                    }

                }
            }


        }
    }


    private void ValidateProductData() {
        if (InputProductDescription != null ) {
            Description = InputProductDescription.getText().toString();
            if (InputProductName != null){
                if (InputProductPrice != null) {
                    if (InputProductPrice.getText() !=null) {
                        Price = InputProductPrice.getText().toString();
                    }       if (InputProductName != null) {
                        Pname = InputProductName.getText().toString();

                    }
                    if (ImageStore == null) {
                        Toast.makeText(this, "Product image is mandatory...", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(Description)) {
                        Toast.makeText(this, "Please write product description...", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(Price)) {
                        Toast.makeText(this, "Please write product Price...", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(Pname)) {
                        Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
                    } else {
                        StoreProductInformation();
                    }}
            }
        }

    }






    private void StoreProductInformation() {
        if (loadingBar != null) {
            loadingBar.setTitle("Add New Product");
            loadingBar.setMessage("Dear Trader, please wait while we are adding the new product.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
            if (currentDate != null) {
                saveCurrentDate = currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                if (currentTime != null) {
                    saveCurrentTime = currentTime.format(calendar.getTime());


                    final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

                    if (filePath !=null){
                        final UploadTask uploadTask = filePath.putFile(ImageUri);


                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                String message = e.toString();
                                Toast.makeText(EditProfileFragment.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                                if (loadingBar != null) {
                                    loadingBar.dismiss();
                                }
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(EditProfileFragment.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();


                                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                    @Override
                                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                        if (!task.isSuccessful()) {
                                            throw task.getException();
                                        }

                                        if (filePath != null) {
                                            downloadImageUrl = filePath.getDownloadUrl().toString();
                                        }
                                        return filePath.getDownloadUrl();
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            if (task != null) {
                                                downloadImageUrl = task.getResult().toString();
                                            }
                                            Toast.makeText(EditProfileFragment.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();

                                            SaveProductInfoToDatabase();
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            }
        }}

    private void SaveProductInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        final HashMap<String, Object> traderhashmap = new HashMap<>();
        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("desc", Description);
        productMap.put("image", downloadImageUrl);
        productMap.put("categoryID", CategoryName);
        productMap.put("price", Price);
        productMap.put("name", Pname);

        traderhashmap.put("name",user.getDisplayName()   );
        traderhashmap.put("tid", user.getUid());
        traderhashmap.put("image",user.getPhotoUrl() );


        ProductsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            ProductsTraderRef.child(traderkeryhere).updateChildren(traderhashmap);

                            intent = new Intent(EditProfileFragment.this, AdminCategoryActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(EditProfileFragment.this, "Product is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            if (task !=null) {
                                //     String message = task.getException().toString();

                                Toast.makeText(EditProfileFragment.this, "Error: " + "Task Exception Thrown",  Toast.LENGTH_SHORT).show();
                            }
                        }}
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
}
// #BuiltByGOD