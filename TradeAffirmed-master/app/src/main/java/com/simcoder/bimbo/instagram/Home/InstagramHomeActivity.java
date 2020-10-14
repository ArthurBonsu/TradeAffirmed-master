package com.simcoder.bimbo.instagram.Home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.simcoder.bimbo.R;
import com.simcoder.bimbo.instagram.Login.InstagramLoginActivity;
import com.simcoder.bimbo.instagram.Models.Photo;

import com.simcoder.bimbo.instagram.Profile.EditProfileFragment.EditProfileFragment;
import com.simcoder.bimbo.instagram.Utils.BottomNavigationViewHelper;
import com.simcoder.bimbo.instagram.Utils.SectionsPagerAdapter;
import com.simcoder.bimbo.instagram.Utils.UniversalImageLoader;
import com.simcoder.bimbo.instagram.Utils.ViewCommentsFragment;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class InstagramHomeActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {
    /*
        @Override
        public void onLoadMoreItems() {
            Log.d(TAG, "onLoadMoreItems: displaying more photos");
            com.simcoder.bimbo.instagram.Home.MainViewFeedFragment fragment = (com.simcoder.bimbo.instagram.Home.MainViewFeedFragment)getSupportFragmentManager()
                    .findFragmentByTag("android:switcher:" + R.id.viewpager_container + ":" + mViewPager.getCurrentItem());
            if(fragment != null){
                fragment.displayMorePhotos();
            }
        }
    */
    private static final String TAG = "Home Fragement Activity";
    private static final int ACTIVITY_NUM = 0;
    private static final int HOME_FRAGMENT = 1;

    private Context mContext = InstagramHomeActivity.this;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //widgets
    private ViewPager mViewPager;
    private FrameLayout mFrameLayout;
    private RelativeLayout mRelativeLayout;


    private Uri ImageUri;

    private ProgressDialog loadingBar;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    String role;
    FirebaseUser user;
    //AUTHENTICATORS
    String saveCurrentDate, saveCurrentTime;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG1 = "Google Activity";

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

    DrawerLayout drawer;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    String traderoruserID;
    String userkey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instagramactivity_home);
        Log.d(TAG, "onCreate: starting.");
        mViewPager = (ViewPager) findViewById(R.id.viewpager_container);
        mFrameLayout = (FrameLayout) findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relLayoutParent);

        // KEYS PASSED IN FROM ADMINCATEGORY
        // ROLE SHOULD BE MADE AS 1
        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("rolefromnavigationtoinsta") != null) {
            role = roleintent.getExtras().getString("rolefromnavigationtoinsta");
        }
        Intent traderoruserintent = getIntent();
        if (traderoruserintent.getExtras().getString("traderfromnavigationtoinsta") != null) {
            traderoruserID = traderoruserintent.getExtras().getString("traderfromnavigationtoinsta");
        }

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            traderoruserID = "";
            traderoruserID = user.getUid();
        }
        loadingBar = new ProgressDialog(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        if (mGoogleApiClient != null) {
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        }

        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(InstagramHomeActivity.this,
                    new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        }
                    }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        }
        buildGoogleApiClient();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = mAuth.getCurrentUser();
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

        myfirebaseDatabase = FirebaseDatabase.getInstance();
        UsersRef = myfirebaseDatabase.getReference().child("Users");
        userkey = UsersRef.getKey();
        Paper.init(this);

        toolbar = (Toolbar) findViewById(R.id.drivertoolbar);
        if (toolbar != null) {
            toolbar.setTitle("Edit Profile");
            //        setSupportActionBar(toolbar);

            toggle = new ActionBarDrawerToggle(
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
            navigationView.setNavigationItemSelectedListener(InstagramHomeActivity.this);
        }


        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);



        setupFirebaseAuth();

        initImageLoader();
        setupBottomNavigationView();
        setupViewPager();

    }


    protected synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(InstagramHomeActivity.this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }
    }

        public void onCommentThreadSelected(Photo photo, String callingActivity){
        Log.d(TAG, "onCommentThreadSelected: selected a coemment thread");

        ViewCommentsFragment fragment  = new ViewCommentsFragment();

        Bundle args = new Bundle();
        if (args != null) {
            if (photo != null) {
                args.putParcelable(getString(R.string.photo), photo);
                args.putString(getString(R.string.instagramhomeactivity), getString(R.string.instagramhomeactivity
                ));
                if (fragment != null) {
                    fragment.setArguments(args);
                }
            }
        }
        if(getSupportFragmentManager() != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (transaction != null) {
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(getString(R.string.view_comments_fragment));
                transaction.commit();
            }

        }}

    public void hideLayout(){
        Log.d(TAG, "hideLayout: hiding layout");
        if (mRelativeLayout != null){        mRelativeLayout.setVisibility(View.GONE);}
        if (mFrameLayout != null) {
            mFrameLayout.setVisibility(View.VISIBLE);
        }
    }


    public void showLayout(){
        Log.d(TAG, "hideLayout: showing layout");
        if (mRelativeLayout != null) {
            mRelativeLayout.setVisibility(View.VISIBLE);
        }
        if (mFrameLayout != null) {
            mFrameLayout.setVisibility(View.GONE);

        }}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mFrameLayout != null){
            if(mFrameLayout.getVisibility() == View.VISIBLE) {
            }
            showLayout();
        }
    }


    private void initImageLoader() {
        if (mContext != null) {

            UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
            if (ImageLoader.getInstance() != null) {
                if (universalImageLoader.getConfig() != null) {
                    ImageLoader.getInstance().init(universalImageLoader.getConfig());
                }
            }
        }
    }
    /**
     * Responsible for adding the 3 tabs: Camera, Home, Messages
     */
    private void setupViewPager() {
        if (getSupportFragmentManager() != null) {
            SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
            if (adapter != null) {
                if (new CameraFragment() != null) {
                    adapter.addFragment(new CameraFragment()); //index 0
                }
                if (new MainViewFeedFragment() != null) {
                    adapter.addFragment(new MainViewFeedFragment()); //index 1
                }
                if (new MessagesFragment() != null) {
                    adapter.addFragment(new MessagesFragment()); //index 2
                }







                if (mViewPager != null) {
                    mViewPager.setAdapter(adapter);
                }
            }
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (tabLayout != null) {
            if (mViewPager != null) {
                tabLayout.setupWithViewPager(mViewPager);
            }
            if (tabLayout.getTabAt(0) != null) {

                tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
            }
            if (tabLayout.getTabAt(1) != null) {
                tabLayout.getTabAt(1).setIcon(R.drawable.ic_instagram_black);
            }
            if (tabLayout.getTabAt(2) != null) {
                tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);
            }
        }
    }
    /**
     * BottomNavigationView setup
     */
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        if (bottomNavigationViewEx != null) {

            {
                BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
            }
            if (mContext != null) {

                BottomNavigationViewHelper.enableNavigation(mContext, this, bottomNavigationViewEx);

                if (bottomNavigationViewEx != null) {
                    ;
                    Menu menu = bottomNavigationViewEx.getMenu();
                    if (menu != null) {
                        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
                        if (menuItem != null) {
                            menuItem.setChecked(true);
                        }
                    }
                }
            }
        }
    }
     /*
    ------------------------------------ Firebase ---------------------------------------------
     */

    /**
     * checks to see if the @param 'user' is logged in
     * @param user
     */


    private void checkCurrentUser(FirebaseUser user){
        Log.d(TAG, "checkCurrentUser: checking if user is logged in.");

        if(user == null) {
            if (mContext != null) {
                Intent intent = new Intent(mContext, InstagramLoginActivity.class);
                startActivity(intent);
            }
        }}
    /**
     * Setup the firebase auth object
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                //check if the user is logged in
                if (user != null) {
                    checkCurrentUser(user);
                }
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
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
        if (mViewPager != null) {

            mViewPager.setCurrentItem(HOME_FRAGMENT);
        }


        if (mAuth != null) {
            mAuth.addAuthStateListener(firebaseAuthListener);
        }



    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            if (mAuth != null) {
                mAuth.removeAuthStateListener(mAuthListener);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}


// #BuiltByGOD