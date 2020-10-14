package  com.simcoder.bimbo.instagram.Profile.EditProfileFragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.simcoder.bimbo.Admin.AdminAddNewProductActivityII;
import com.simcoder.bimbo.CustomerMapActivity;
import com.simcoder.bimbo.DriverMapActivity;
import com.simcoder.bimbo.HistoryActivity;
import com.simcoder.bimbo.MainActivity;
import com.simcoder.bimbo.Model.HashMaps;
import com.simcoder.bimbo.Model.Products;
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
import com.simcoder.bimbo.WorkActivities.CartActivity;
import com.simcoder.bimbo.WorkActivities.HomeActivity;
import com.simcoder.bimbo.WorkActivities.SearchProductsActivity;
import com.simcoder.bimbo.historyRecyclerView.HistoryObject;
import com.simcoder.bimbo.instagram.Models.User;
import com.simcoder.bimbo.instagram.Models.UserSettings;
import com.simcoder.bimbo.instagram.Profile.ProfileActivity;
import com.simcoder.bimbo.instagram.Utils.FirebaseMethods;
import com.simcoder.bimbo.instagram.dialogs.ConfirmPasswordDialog;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

public class EditProfileFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {
    private View EditProfileView;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private static final int GALLERY_REQUEST = 1;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    private DatabaseReference ProductsTraderRef;
    DatabaseReference mydriversreference;
    private ProgressDialog loadingBar;
    private static final int RC_SIGN_IN = 1;
    private StorageReference ProfileStorageImage;
    private ProgressDialog mProgress;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    String role;
    FirebaseUser user;
    //AUTHENTICATORS
    String saveCurrentDate, saveCurrentTime;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Google Activity";
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    DatabaseReference myuserreference;
    FirebaseDatabase myfirebaseDatabase;
    private DatabaseReference myRef;
    private DatabaseReference UsersRef;
    ImageView backArrow;
    //Edit Profile Fragment Widgets
    EditText Passwordedittext, mUsername, mWebsite, mDescription, mEmail, mPhoneNumber;
    TextView mChangeProfilePhoto;
    CircleImageView mProfilePhoto;
    private Uri mImageUri = null;
    // vars
    // TAKEN FROM AUTHENTICATION LISTENERS
    private SignInButton GoogleBtn;
    Intent signInIntent;
    String email;
    String username;
    String password;
    String website;
    String description;
    String phone;
    String image;
    String traderoruserID;
    String userkey;
    String profileimage;
    String traderorusername;
    String mytraderoruserimage;
    Button clearinfo;
    DrawerLayout drawer;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    public EditProfileFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        EditProfileView = inflater.inflate(R.layout.fragment_edit_profile, container, false);


        Intent roleintent = getActivity().getIntent();
        if (roleintent.getExtras().getString("rolefrominstagramhome") != null) {
            role = roleintent.getExtras().getString("rolefrominstagramhome");
        }
        Intent traderoruserintent = getActivity().getIntent();
        if (traderoruserintent.getExtras().getString("traderfrominstagramhome") != null) {
            traderoruserID = traderoruserintent.getExtras().getString("traderfrominstagramhome");
        }


        ProfileStorageImage = FirebaseStorage.getInstance().getReference().child("profile_images");


        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            traderoruserID = "";
            traderoruserID = user.getUid();
        }


        mProfilePhoto = (CircleImageView) EditProfileView.findViewById(R.id.profile_photo);
        Passwordedittext = (EditText) EditProfileView.findViewById(R.id.display_name);
        mUsername = (EditText) EditProfileView.findViewById(R.id.username);
        mWebsite = (EditText) EditProfileView.findViewById(R.id.website);
        mDescription = (EditText) EditProfileView.findViewById(R.id.description);
        mEmail = (EditText) EditProfileView.findViewById(R.id.email);
        mPhoneNumber = (EditText) EditProfileView.findViewById(R.id.phoneNumber);
        mChangeProfilePhoto = (TextView) EditProfileView.findViewById(R.id.changeProfilePhoto);
        clearinfo = (Button) EditProfileView.findViewById(R.id.clearinfo);
        //  setimagebutton = (ImageView)findViewById(R.id.setimagebutton);
        loadingBar = new ProgressDialog(getContext());
        drawer = (DrawerLayout) EditProfileView.findViewById(R.id.drawer_layout);
        FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        if (mGoogleApiClient != null) {
            mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        }

        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext()).enableAutoManage(getActivity(),
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
            user = mAuth.getCurrentUser();


            if (mAuth != null) {
                user = mAuth.getCurrentUser();
                if (user != null) {
                    traderoruserID = user.getUid();

                }


                myfirebaseDatabase = FirebaseDatabase.getInstance();


                UsersRef = myfirebaseDatabase.getReference().child("Users");


                userkey = UsersRef.getKey();

                //IF USER KEY

                if (role == "Trader") {
                    myuserreference = UsersRef.child("Drivers").child(traderoruserID);

                    myuserreference.keepSynced(true);
                    if (myuserreference != null) {
                        myuserreference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    if (dataSnapshot != null) {
                                        dataSnapshot.getValue(User.class);
                                        role = dataSnapshot.child("role").getValue(String.class);

                                        email = dataSnapshot.child("email").getValue(String.class);
                                        mEmail.setText(email);

                                        username = dataSnapshot.child("username").getValue(String.class);
                                        mUsername.setText(username);
                                        password = dataSnapshot.child("password").getValue(String.class);
                                        Passwordedittext.setText(password);
                                        website = dataSnapshot.child("website").getValue(String.class);
                                        mWebsite.setText(website);
                                        description = dataSnapshot.child("description").getValue(String.class);
                                        mDescription.setText(description);
                                        phone = dataSnapshot.child("phone").getValue(String.class);
                                        mPhoneNumber.setText(phone);
                                        image = dataSnapshot.child("image").getValue(String.class);
                                        mProfilePhoto.setImageResource(Integer.parseInt(image));
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    } else {

                        myuserreference = UsersRef.child("Customers").child(traderoruserID);

                        myuserreference.keepSynced(true);
                        mydriversreference = UsersRef.child("Drivers").child(traderoruserID);

                        mydriversreference.keepSynced(true);

                        if (myuserreference != null) {
                            myuserreference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        if (dataSnapshot != null) {
                                            dataSnapshot.getValue(User.class);
                                            role = dataSnapshot.child("role").getValue(String.class);
                                            email = dataSnapshot.child("email").getValue(String.class);
                                            mEmail.setText(email);
                                            username = dataSnapshot.child("username").getValue(String.class);
                                            mUsername.setText(username);
                                            password = dataSnapshot.child("password").getValue(String.class);
                                            Passwordedittext.setText(password);
                                            website = dataSnapshot.child("website").getValue(String.class);
                                            mWebsite.setText(website);
                                            description = dataSnapshot.child("description").getValue(String.class);
                                            mDescription.setText(description);
                                            phone = dataSnapshot.child("phone").getValue(String.class);
                                            mPhoneNumber.setText(phone);
                                            image = dataSnapshot.child("image").getValue(String.class);
                                            // We are going to set the image here
                                            mProfilePhoto.setImageResource(Integer.parseInt(image));
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                        }

                        Paper.init(getContext());

                        toolbar = (Toolbar) EditProfileView.findViewById(R.id.drivertoolbar);
                        if (toolbar != null) {
                            toolbar.setTitle("Edit Profile");
                            //        setSupportActionBar(toolbar);

                            toggle = new ActionBarDrawerToggle(
                                    getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                            if (drawer != null) {
                                drawer.addDrawerListener(toggle);
                                if (toggle != null) {
                                    toggle.syncState();

                                }
                            }
                        }

                        //back arrow for navigating back to "ProfileActivity"
                        backArrow = (ImageView) EditProfileView.findViewById(R.id.backArrow);
                        if (backArrow != null) {
                            backArrow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.d(TAG, "onClick: navigating back to ProfileActivity");
                                    Intent profileactivity = new Intent(getContext(), ProfileActivity.class);

                                    startActivity(profileactivity);

                                }
                            });
                        }
                    }

                    // green checkmark icon to update user settings information
                    ImageView checkmark = (ImageView) EditProfileView.findViewById(R.id.saveChanges);
                    Log.d(TAG, "onClick: Saving the information typed in the activity");
                    if (checkmark != null) {
                        checkmark.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d(TAG, "onClick: attempting to save changes.");
                                saveProfileSettings();

                            }
                        });
                    }

                    NavigationView navigationView = (NavigationView) EditProfileView.findViewById(R.id.nav_view);

                    if (navigationView != null) {
                        navigationView.setNavigationItemSelectedListener(EditProfileFragment.this);
                    }


                    View headerView = navigationView.getHeaderView(0);
                    TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
                    CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);

                }
            }
        }

        clearinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEdit();
            }
        });

        return EditProfileView;
    }


    protected synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(EditProfileFragment.this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }


    }


    /**
     * Retrieves the data from widgets and submits it to database
     * Checks that username is unique
     */
    //
    private void startEdit() {

        if (mUsername.getText() != null) {
            mUsername.setText("");

            if (Passwordedittext.getText() != null) {
                Passwordedittext.setText("");
                if (mDescription.getText() != null) {
                    mDescription.setText("");

                    if (mWebsite.getText() != null) {
                        mWebsite.setText("");

                        if (mEmail.getText() != null) {
                            mEmail.setText("");

                            if (mPhoneNumber.getText() != null) {
                                mPhoneNumber.setText("");
                                // case1: if user changed to same name.
                            }
                        }
                    }
                }
            }
        }

    }

    public void saveProfileSettings() {
        if (role == "Trader") {
            if (loadingBar != null) {
                loadingBar.setTitle("Saving Trader Information");
                loadingBar.setMessage("Dear Trader, please wait while we save your profile information.");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                if (mUsername.getText() != null) {


                    final String usernamerewritten = mUsername.getText().toString();
                    if (Passwordedittext.getText() != null) {
                        final String passwordwritten = Passwordedittext.getText().toString();
                        if (mDescription.getText() != null) {
                            final String descriptionrewritten = mDescription.getText().toString();

                            if (mWebsite.getText() != null) {
                                final String websiterewritten = mWebsite.getText().toString();

                                if (mEmail.getText() != null) {
                                    final String emailrewritten = mEmail.getText().toString();

                                    if (mPhoneNumber.getText() != null) {
                                        final String phoneNumberrewritten = mPhoneNumber.getText().toString();


                                        // case1: if user changed to same name.
                                        if (usernamerewritten == username) {
                                            Toast.makeText(getContext(), "This username already exist", Toast.LENGTH_SHORT).show();
                                        }
                                        // case2: if user changed to same password.
                                        else if (passwordwritten == password) {
                                            Toast.makeText(getContext(), "This password already exist", Toast.LENGTH_SHORT).show();
                                        }

                                        // case3: if user changed to same description.
                                        else if (descriptionrewritten == description) {
                                            Toast.makeText(getContext(), "This description already exist", Toast.LENGTH_SHORT).show();
                                        }

                                        // case4: if user changed to same website.
                                        else if (websiterewritten == website) {
                                            Toast.makeText(getContext(), "This website already exist", Toast.LENGTH_SHORT).show();
                                        }

                                        // case5: if user changed to same email.
                                        else if (emailrewritten == email) {
                                            Toast.makeText(getContext(), "This email already exist", Toast.LENGTH_SHORT).show();
                                        }

                                        // case5: if user changed to same email.
                                        else if (phoneNumberrewritten == phone) {
                                            Toast.makeText(getContext(), "This phone number already exist", Toast.LENGTH_SHORT).show();
                                        } else {

                                            Calendar calendar = Calendar.getInstance();
                                            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");

                                            if (currentDate != null) {
                                                saveCurrentDate = currentDate.format(calendar.getTime()).toString();

                                                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                                                if (currentTime != null) {
                                                    saveCurrentTime = currentTime.format(calendar.getTime());

                                                }


                                                if (!TextUtils.isEmpty(usernamerewritten) && !TextUtils.isEmpty(passwordwritten) && !TextUtils.isEmpty(descriptionrewritten) && !TextUtils.isEmpty(websiterewritten) && !TextUtils.isEmpty(emailrewritten) && !TextUtils.isEmpty(phoneNumberrewritten) && mImageUri != null) {
                                                    mProgress.setMessage("Editing Profile Here");

                                                    mProgress.show();
                                                    StorageReference filepath = ProfileStorageImage.child(mImageUri.getLastPathSegment());

                                                    filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                        @Override
                                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                                                            final Uri downloadUrl = taskSnapshot.getUploadSessionUri();

                                                            traderoruserID = user.getUid();
                                                            traderorusername = user.getDisplayName();
                                                            profileimage = downloadUrl.toString();

                                                            Uri myphoto = user.getPhotoUrl();
                                                            mytraderoruserimage = myphoto.toString();


                                                            User userinformationtobeupdated = new User(emailrewritten, usernamerewritten, descriptionrewritten, websiterewritten, profileimage, passwordwritten, phoneNumberrewritten);

                                                            UsersRef.child("Drivers").child(traderoruserID).setValue(userinformationtobeupdated, new
                                                                    DatabaseReference.CompletionListener() {
                                                                        @Override
                                                                        public void onComplete(DatabaseError databaseError, DatabaseReference
                                                                                databaseReference) {
                                                                            Toast.makeText(getApplicationContext(), "Information being updated", Toast.LENGTH_SHORT).show();
                                                                            Intent gotohomefromeditprofile = new Intent(getContext(), HomeActivity.class);

                                                                            startActivity(gotohomefromeditprofile);

                                                                        }
                                                                    });


                                                        }

                                                    });


                                                    mProgress.dismiss();

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        if (role == "Customers") {
            if (loadingBar != null) {
                loadingBar.setTitle("Save information for user");
                loadingBar.setMessage("Dear User, please wait  while we save your profile information");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                if (mUsername.getText() != null) {


                    final String usernamerewritten = mUsername.getText().toString();
                    if (Passwordedittext.getText() != null) {
                        final String passwordwritten = Passwordedittext.getText().toString();
                        if (mDescription.getText() != null) {
                            final String descriptionrewritten = mDescription.getText().toString();

                            if (mWebsite.getText() != null) {
                                final String websiterewritten = mWebsite.getText().toString();

                                if (mEmail.getText() != null) {
                                    final String emailrewritten = mEmail.getText().toString();

                                    if (mPhoneNumber.getText() != null) {
                                        final String phoneNumberrewritten = mPhoneNumber.getText().toString();


                                        // case1: if user changed to same name.
                                        if (usernamerewritten == username) {
                                            Toast.makeText(getContext(), "This username already exist", Toast.LENGTH_SHORT).show();
                                        }
                                        // case2: if user changed to same password.
                                        else if (passwordwritten == password) {
                                            Toast.makeText(getContext(), "This password already exist", Toast.LENGTH_SHORT).show();
                                        }

                                        // case3: if user changed to same description.
                                        else if (descriptionrewritten == description) {
                                            Toast.makeText(getContext(), "This description already exist", Toast.LENGTH_SHORT).show();
                                        }

                                        // case4: if user changed to same website.
                                        else if (websiterewritten == website) {
                                            Toast.makeText(getContext(), "This website already exist", Toast.LENGTH_SHORT).show();
                                        }

                                        // case5: if user changed to same email.
                                        else if (emailrewritten == email) {
                                            Toast.makeText(getContext(), "This email already exist", Toast.LENGTH_SHORT).show();
                                        }

                                        // case5: if user changed to same email.
                                        else if (phoneNumberrewritten == phone) {
                                            Toast.makeText(getContext(), "This phone number already exist", Toast.LENGTH_SHORT).show();
                                        } else {

                                            Calendar calendar = Calendar.getInstance();
                                            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");

                                            if (currentDate != null) {
                                                saveCurrentDate = currentDate.format(calendar.getTime()).toString();

                                                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                                                if (currentTime != null) {
                                                    saveCurrentTime = currentTime.format(calendar.getTime());

                                                }


                                                if (!TextUtils.isEmpty(usernamerewritten) && !TextUtils.isEmpty(passwordwritten) && !TextUtils.isEmpty(descriptionrewritten) && !TextUtils.isEmpty(websiterewritten) && !TextUtils.isEmpty(emailrewritten) && !TextUtils.isEmpty(phoneNumberrewritten) && mImageUri != null) {
                                                    mProgress.setMessage("Editing Profile Here");

                                                    mProgress.show();
                                                    StorageReference filepath = ProfileStorageImage.child(mImageUri.getLastPathSegment());

                                                    filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                        @Override
                                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                                                            final Uri downloadUrl = taskSnapshot.getUploadSessionUri();

                                                            traderoruserID = user.getUid();
                                                            traderorusername = user.getDisplayName();
                                                            profileimage = downloadUrl.toString();

                                                            Uri myphoto = user.getPhotoUrl();
                                                            mytraderoruserimage = myphoto.toString();


                                                            User userinformationtobeupdated = new User(emailrewritten, usernamerewritten, descriptionrewritten, websiterewritten, profileimage, passwordwritten, phoneNumberrewritten);

                                                            UsersRef.child("Customers").child(traderoruserID).setValue(userinformationtobeupdated, new
                                                                    DatabaseReference.CompletionListener() {
                                                                        @Override
                                                                        public void onComplete(DatabaseError databaseError, DatabaseReference
                                                                                databaseReference) {
                                                                            Toast.makeText(getApplicationContext(), "Information being updated", Toast.LENGTH_SHORT).show();

                                                                            ///update information
                                                                            Intent gotohomefromeditprofile = new Intent(getContext(), HomeActivity.class);

                                                                            startActivity(gotohomefromeditprofile);

                                                                        }
                                                                    });


                                                        }

                                                    });


                                                    mProgress.dismiss();

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // OnActivity result is lacking behind, I have to get the URi from it
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {

            Uri imageUri = data.getData();

            CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1, 1).start(getActivity());


        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mImageUri = result.getUri();

                mProfilePhoto.setImageURI(mImageUri);


            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }

        }
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings)
//        {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_ViewStore) {

            {

                if (UsersRef.child("Customers").child(traderoruserID) != null) {
                    Intent intent = new Intent(getContext(), com.simcoder.bimbo.WorkActivities.HomeActivity.class);

                    intent.putExtra("rolefromcustomermapactivitytohomeactivity", role);
                    intent.putExtra("fromcustomermapactivitytohomeactivity", traderoruserID);

                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getContext(), com.simcoder.bimbo.WorkActivities.MainActivity.class);
                    intent.putExtra("traderoruser", traderoruserID);
                    intent.putExtra("traderoruser", role);
                    startActivity(intent);

                }
            }
        } else if (id == R.id.nav_SearchforTraders) {
        } else if (id == R.id.nav_searchforproducts) {

            {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String cusomerId = "";
                cusomerId = user.getUid();
                Intent intent = new Intent(getContext(), SearchProductsActivity.class);
                intent.putExtra("fromcustomermapactivitytosearchproductactivity", cusomerId);
                intent.putExtra("rolefromcustomermapactivtytosearchproductactivity", role);
                startActivity(intent);


            }
        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            if (mGoogleSignInClient != null) {
                mGoogleSignInClient.signOut().addOnCompleteListener(getActivity(),
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
            }
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_history) {

            {
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                // WE PASS THE CUSTOMER OR DRIVER CODE TO THE HISTORY ACTIVITY TO SEE ALL THE HISTORY ACTIVITES
                intent.putExtra("customerOrDriver", "Drivers");
                startActivity(intent);
            }
        } else if (id == R.id.nav_viewprofilehome) {

            {
                Paper.book().destroy();

                Intent intent = new Intent(getContext(), com.simcoder.bimbo.WorkActivities.CustomerProfile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        } else if (id == R.id.nav_paymenthome) {


           /* drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer != null) {

                drawer.closeDrawer(GravityCompat.START);
            }
*/
        }
        return true;
    }



    @Override
    public void onStart() {
        super.onStart();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                user = mAuth.getCurrentUser();
                if (mAuth != null) {
                    if (user != null) {

                        traderoruserID = user.getUid();
                    }

                    // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                    // WHICH IS CUSTOMER TO BE ADDED.
                    // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
                }
            }
        };


        if (mAuth != null) {
            mAuth.addAuthStateListener(firebaseAuthListener);
        }



    }

    @Override
    public void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            if (mAuth != null) {
                mAuth.removeAuthStateListener(firebaseAuthListener);
            }    }
    }

    @Override
    public void onResume() {

        super.onResume();
       }



    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onPause() {
        super.onPause();
       }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

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



}
// #BuiltByGOD