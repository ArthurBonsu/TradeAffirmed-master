package com.simcoder.bimbo.WorkActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.SnapshotParser;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.ImageView;
import com.simcoder.bimbo.Admin.AdminAddNewProductActivityII;
import com.simcoder.bimbo.Admin.AdminAllCustomers;
import com.simcoder.bimbo.Admin.AdminAllProducts;
import com.simcoder.bimbo.Admin.AdminCategoryActivity;
import com.simcoder.bimbo.Admin.AdminMaintainProductsActivity;
import com.simcoder.bimbo.Admin.AdminNewOrdersActivity;
import com.simcoder.bimbo.Admin.AdminProductDetails;
import com.simcoder.bimbo.Admin.ViewAllCarts;
import com.simcoder.bimbo.Admin.ViewSpecificUsersCart;
import com.simcoder.bimbo.Admin.ViewYourPersonalProduct;
import com.simcoder.bimbo.DriverMapActivity;
import com.simcoder.bimbo.HistoryActivity;
import com.simcoder.bimbo.Interface.ItemClickListner;
import com.simcoder.bimbo.MainActivity;
import com.simcoder.bimbo.Model.Products;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simcoder.bimbo.R;
import com.simcoder.bimbo.historyRecyclerView.HistoryObject;
import com.simcoder.bimbo.historyRecyclerView.HistoryViewHolders;
import com.simcoder.bimbo.instagram.Models.User;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;


public  class  HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference Userdetails;

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference TraderDetails;
    DatabaseReference FollowerDatabaseReference;
    String productkey;
    String traderkeyhere;
    private String type = "";
    String clientuser = "";
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    String ProductID;
    FirebaseDatabase myfirebaseDatabase;
    FirebaseDatabase FollowerDatabase;
    FirebaseDatabase ProductsFirebaseDatabase;

    DatabaseReference ProductDatabase;
    DatabaseReference ProductsRef;
    FirebaseDatabase myproductfirebaseDatabaseRef;

    public FirebaseRecyclerAdapter adapter;
    String thetraderkey;
    String thenameofthetrader;
    String description;
    String thetradername;

    //AUTHENTICATORS

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Google Activity";
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    String productdescription;
    ImageView theproductimageview;
    TextView thetraderview;
    String keyhere;
    String thetraderhere;
    String traderkey;
    String key;
    String tradename;
    String traderimage;
    FirebaseUser user;
    Query QueryFollowingsshere;
    Query ProductsQuery;
    String followingid;
    String followingname;
    String followingimage;
    String userid;
    TextView thefollowerid;
    TextView product_discount;
    String role;
    String userID;
    Intent roleintent;
    FloatingActionButton fab;
    public static String thefolloweridgrabber;
    public static String thefollowerstepper;
    ArrayList idList = new ArrayList<>();

    public List<User> mUsers;
    String uid;
    int i = 0;
    String thefollowingidindex;
    //product_name
    // product_imagehere
    //  product_price

    //product_description
    //thetraderiknow


    // Footers
    ImageButton movetonext;
    ImageButton homebutton;
    ImageButton suggestionsbutton;
    ImageButton       services;
    ImageButton  expectedshipping;
    ImageButton       clientprofile;
    String categoryname, date, desc, discount, time, pid, pimage, pname, price, image, name, size, tradername, tid;

    android.widget.ImageView thetraderimageforproduct;


    Query QueryFollowershere;


    String followerid;
    String followeridname;
    String followername;
    String followerimage;
    String followeridimage;
    String number;
    ArrayList<String> followeridList;
    ArrayList<String> followeridnameList;
    ArrayList<String> followeridimageList;

    FirebaseDatabase TheFollowingsDatabase;
    DatabaseReference TheFollowingsDatabaseReference;
    Query TheFollowingsQueryFollowingshere;


    String thefollowingsid;
    String thefollowingsname;
    String thefollowingsimage;


    ArrayList<String> thefollowingidList;
    ArrayList<String> thefollowingidnameList;
    ArrayList<String> thefollowingidimageList;


    FirebaseDatabase TheLikesDatabase;
    DatabaseReference TheLikesDatabaseReference;
    Query TheLikesQuery;


    String thelikeid;
    String thelikerid;
    String thelikername;
    String thelikerimage;


    ArrayList<String> thelikeidList;
    ArrayList<String> thelikeridnameList;
    ArrayList<String> thelikernameList;
    ArrayList<String> thelikeridimageList;
    ImageSlider mainslider;

    public interface Getmyfollowings {

        void onCallback(String followingid, String followingname, String followingimage);

        String onfollowining(String following);

    }

    Getmyfollowings getmyfollowingsagain;

    ArrayList<String> secondfollowingidList = new ArrayList<>();
    ArrayList<String> secondfollowingidnameList = new ArrayList<>();
    ArrayList<String> secondfollowingidimageList = new ArrayList<>();


   android.widget.ImageView thetraderimagehere;
    android.widget.ImageView product_imagehere;
    TextView product_name;
    TextView product_price;
    TextView discounts;
    TextView thetradernamegiven;
    TextView categoryhere;
    TextView likenumberhere;
    TextView descriptionhere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                (R.layout.activity_home));
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent userIDintent = getIntent();
        if (userIDintent.getExtras().getString("userID") != null) {
            userID = userIDintent.getExtras().getString("userID");
        }

/*
        if (clientuser != null) {
            clientuser = getIntent().getStringExtra("traderID");
        }
*/
        fab = (FloatingActionButton) findViewById(R.id.fab);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("remember", "false");
         editor.apply();

         finish();
        if (fab != null) {
            fab.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(HomeActivity.this, ProductDetailsActivity.class);
                            intent.putExtra("pid", pid);
                            startActivity(intent);
                        }


                    });


            recyclerView = findViewById(R.id.recycler_menu);


            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            if (recyclerView != null) {
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            }
     /*  if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);

        }
*/



            thetraderimagehere = (android.widget.ImageView)findViewById(R.id.thetraderimagehere);
            product_imagehere = (android.widget.ImageView)findViewById(R.id.product_imagehere);
            product_name =(TextView) findViewById(R.id.product_name);
            product_price = (TextView) findViewById(R.id.product_price);
            discounts =(TextView) findViewById(R.id.discount);
            thetradernamegiven =(TextView) findViewById(R.id.thetradername);
            categoryhere = (TextView) findViewById(R.id.category);
            likenumberhere = (TextView) findViewById(R.id.likenumber);
            descriptionhere = (TextView) findViewById(R.id.description);



            movetonext = findViewById(R.id.movetonext);
            homebutton = findViewById(R.id.homebutton);
            suggestionsbutton = findViewById(R.id.suggestionsbutton);
            services = findViewById(R.id.services);
            expectedshipping = findViewById(R.id.expectedshipping);



            Paper.init(this);

            Toolbar toolbar = (Toolbar) findViewById(R.id.hometoolbar);
            if (toolbar != null) {
                toolbar.setTitle("Home");
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


                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                if (user.getDisplayName() != null) {
                    if (user.getDisplayName() != null) {
                        userNameTextView.setText(user.getDisplayName());

                        Picasso.get().load(user.getPhotoUrl()).placeholder(R.drawable.profile).into(profileImageView);
                    }
                }
            }

            myfirebaseDatabase = FirebaseDatabase.getInstance();

            ProductsRef = myfirebaseDatabase.getReference().child("Product");


            productkey = ProductsRef.getKey();
            // GET FROM FOLLOWING KEY


            recyclerView.setAdapter(adapter);
            fetch();
            ProductsRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        // This method is called once with the initial value and again

                        tradename = ds.child("tradername").getValue(String.class);
                        traderimage = ds.child("traderimage").getValue(String.class);
                        Log.d(TAG, "Value is 1: " + tradename + traderimage);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            if (mAuth != null) {
                user = mAuth.getCurrentUser();
                if (user != null) {
                    userid = user.getUid();


                    // THE SAME THING THE FOLLOWING
                    /*
                    FollowerDatabase = FirebaseDatabase.getInstance();
                    FollowerDatabaseReference = FollowerDatabase.getReference().child("Following");
                    FollowerDatabaseReference.keepSynced(true);
                    QueryFollowingsshere = FollowerDatabaseReference.orderByChild("uid").equalTo(userid);
                    QueryFollowingsshere.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Name of trader being followed
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if (ds.child("tid").getValue() != null) {
                                    followingid = ds.child("tid").getValue(String.class);
                                }

                                if (ds.child("tradername").getValue() != null) {
                                    followingname = ds.child("tradername").getValue(String.class);
                                }
                                if (ds.child("traderimage").getValue() != null) {
                                    followingimage = ds.child("traderimage").getValue(String.class);
                                }
                                Log.d("Followers Well", followingid + followingname + followingimage);
                                thefolloweridgrabber = followingid;

                                Log.d("Followersgrabber", thefolloweridgrabber);

                                thefollowerstepper = thefolloweridgrabber;
                                Log.d("Followerstepper", thefollowerstepper);

                                if (getmyfollowingsagain != null) {
                                    if (followingid != null && followingname != null && followingimage != null) {

                                        getmyfollowingsagain.onCallback(followingid, followingname, followingimage);
                                        getmyfollowingsagain.onfollowining(followingid);

                                    }
                                }
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }


                    });
                    if (thefolloweridgrabber != null) {
                        Log.d("Followerinfo", thefolloweridgrabber);


                        Log.i("Mygrabback", thefolloweridgrabber);
                        if (thefollowerstepper != null) {
                            Log.d("Followerdownstepper", thefollowerstepper);
                        }
                    }
                    */

//        setSupportActionBar(toolbar);

                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
                    if (mGoogleApiClient != null) {

                        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
                    }

                    if (mGoogleApiClient != null) {
                        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(HomeActivity.this,
                                new GoogleApiClient.OnConnectionFailedListener() {
                                    @Override
                                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                                    }
                                }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
                    }


                    // USER
                    user = mAuth.getCurrentUser();


                    if (user != null) {
                        userID = "";
                        userID = user.getUid();


                    }
                }

            }

            getFollowers();
            getFollowings();
            getLikes();
            showProducts();
        }

        // IMAGE SLIDER
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mainslider=(ImageSlider)findViewById(R.id.serviceadslider);
        final List<SlideModel> remoteimages=new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("Slider")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for(DataSnapshot data:dataSnapshot.getChildren())
                            remoteimages.add(new SlideModel(data.child("url").getValue().toString(),data.child("title").getValue().toString(), ScaleTypes.FIT));

                        mainslider.setImageList(remoteimages,ScaleTypes.FIT);
                        mainslider.setScrollBarFadeDuration(1);


                        mainslider.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemSelected(int i) {
                                Toast.makeText(getApplicationContext(),remoteimages.get(i).getTitle().toString(),Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




        clientprofile = findViewById(R.id.movetonext);
        expectedshipping  = findViewById(R.id.expectedshipping);
        suggestionsbutton = findViewById(R.id.suggestionsbutton);
        services = findViewById(R.id.services);

        homebutton = findViewById(R.id.homebutton);

        clientprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CustomerProfile.class);
                startActivity(intent);
                finish();

                return;
        };

        });

            expectedshipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Shipping.class);
                startActivity(intent);
                finish();

                return;
            };

        });

            homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();

                return;
            };

        });

            suggestionsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Suggestions.class);
                startActivity(intent);
                finish();

                return;
            };

        });
 // We have to replace the list of services there as well, so people can visit the services
            services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Services.class);
                startActivity(intent);
                finish();

                return;
            };

        });




        }


    /*
    public void getFollowings() {

        FirebaseDatabase.getInstance().getReference().child("Following").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                followingidList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    followingidList.add((snapshot.getKey()));
                }

                showUsers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

*/
    public void getFollowers() {


        FollowerDatabase = FirebaseDatabase.getInstance();
        FollowerDatabaseReference = FollowerDatabase.getReference().child("Followers");
        FollowerDatabaseReference.keepSynced(true);
        QueryFollowershere = FollowerDatabaseReference.orderByChild("uid").equalTo(userid);
        QueryFollowershere.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Name of trader being followed
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("uid").getValue() != null) {
                        followerid = ds.child("tid").getValue(String.class);
                    }

                    if (ds.child("name").getValue() != null) {
                        followername = ds.child("name").getValue(String.class);
                    }
                    if (ds.child("image").getValue() != null) {
                        followerimage = ds.child("image").getValue(String.class);
                    }

                    followeridList.add(followerid);
                    followeridnameList.add(followeridname);
                    followeridimageList.add(followerimage);
/*
                    Log.d("Followers Well", followeridList + followeridnameList + followeridimageList);
                    thefolloweridgrabber = followingid;

                    Log.d("Followersgrabber", thefolloweridgrabber);

                    thefollowerstepper = thefolloweridgrabber;
                    Log.d("Followerstepper", thefollowerstepper);

                    if (getmyfollowingsagain != null) {
                        if (followingid != null && followingname != null && followingimage != null) {

                            getmyfollowingsagain.onCallback(followingid, followingname, followingimage);
                            getmyfollowingsagain.onfollowining(followingid);

                        }
  */

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }

    // we get the followers
    // FOR THIS PAGE WE JUST WANT TO GET THE FOLLOWING LIST
    public void getFollowings() {


        TheFollowingsDatabase = FirebaseDatabase.getInstance();
        TheFollowingsDatabaseReference = TheFollowingsDatabase.getReference().child("Following");
        TheFollowingsDatabaseReference.keepSynced(true);
        TheFollowingsQueryFollowingshere = TheFollowingsDatabaseReference.orderByChild("uid").equalTo(userid);
        TheFollowingsQueryFollowingshere.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Name of trader being followed
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("tid").getValue() != null) {
                        thefollowingsid = ds.child("tid").getValue(String.class);
                    }

                    if (ds.child("tradername").getValue() != null) {
                        thefollowingsname = ds.child("tradername").getValue(String.class);
                    }
                    if (ds.child("traderimage").getValue() != null) {
                        thefollowingsimage = ds.child("traderimage").getValue(String.class);
                    }

                    thefollowingidList.add(thefollowingsid);
                    thefollowingidnameList.add(thefollowingsname);
                    thefollowingidimageList.add(thefollowingsimage);

                    fetch();

                    /*
                    Log.d("Following People", thefollowingsid + thefollowingsname + thefollowingsimage);
                    thefolloweridgrabber = thefollowingsid;

                    Log.d("Followinigrabber", thefolloweridgrabber);

                    thefollowerstepper = thefolloweridgrabber;
                    Log.d("Followingstepper", thefollowerstepper);

                    if (getmyfollowingsagain != null) {
                        if (thefollowingsid != null && thefollowingsname != null && thefollowingsimage != null) {

                            getmyfollowingsagain.onCallback(thefollowingsid, thefollowingsname, thefollowingsimage);
                            getmyfollowingsagain.onfollowining(thefollowingsid);

                        }

                    }
*/                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }


    // NUMBER OF LIKES OF FOLLOWER
    // we get the people that have liked us
    public void getLikes() {


        TheLikesDatabase = FirebaseDatabase.getInstance();
        TheLikesDatabaseReference = TheLikesDatabase.getReference().child("Likes");
        TheLikesDatabaseReference.keepSynced(true);
        TheLikesQuery= TheLikesDatabaseReference.orderByChild("uid").equalTo(userid);
        TheLikesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Name of trader being followed
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("tid").getValue() != null) {
                        thelikeid = ds.child("tid").getValue(String.class);
                    }

                    if (ds.child("tid").getValue() != null) {
                        thelikerid = ds.child("tid").getValue(String.class);
                    }

                    if (ds.child("tradername").getValue() != null) {
                        thelikername = ds.child("tradername").getValue(String.class);
                    }
                    if (ds.child("traderimage").getValue() != null) {
                        thelikerimage = ds.child("traderimage").getValue(String.class);
                    }

                    thelikeidList.add(thefollowingsid);
                    thelikeridnameList.add(thefollowingsname);
                    thelikernameList.add(thefollowingsimage);
                    thelikeridimageList.add(thefollowingsimage);

                    /*
                    Log.d("Following People", thefollowingsid + thefollowingsname + thefollowingsimage);
                    thefolloweridgrabber = thefollowingsid;

                    Log.d("Followinigrabber", thefolloweridgrabber);

                    thefollowerstepper = thefolloweridgrabber;
                    Log.d("Followingstepper", thefollowerstepper);

                    if (getmyfollowingsagain != null) {
                        if (thefollowingsid != null && thefollowingsname != null && thefollowingsimage != null) {

                            getmyfollowingsagain.onCallback(thefollowingsid, thefollowingsname, thefollowingsimage);
                            getmyfollowingsagain.onfollowining(thefollowingsid);

                        }

                    }
                    */
                }

            }
            //get the trader// traderkey
            // orderfoodpostofproduct
            // reschedule, or randomize
            //the likes of each food


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

    }


/*

    public void getLikes() {

        FirebaseDatabase.getInstance().getReference().child("Likes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                likesidList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    likesidList.add((snapshot.getKey()));
                }

                showUsers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

*/

/*
    public void showUsers() {

        FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.
                clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);

                    for  (i=0; i< followingidList i++) {
                        if (user.getuid().equals(uid)) {
                            mUsers.add(user);
                        }
                    }
                }
                Log.d("list f", mUsers.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

*/




    public void showProducts() {

        for (int i = 0; i < thefollowingidList.size(); i++) {

            thefollowingidindex = thefollowingidList.get(i);
            ProductsFirebaseDatabase = FirebaseDatabase.getInstance();
            ProductsRef = ProductsFirebaseDatabase.getReference().child("Products");
            ProductsRef.keepSynced(true);
            ProductsQuery = ProductsRef.orderByChild("tid").equalTo(thefollowingidindex);
            ProductsQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Name of trader being followed
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (ds.child("pid").getValue() != null) {
                            pid = ds.child("pid").getValue(String.class);
                        }

                        if (ds.child("tradername").getValue() != null) {
                            pname = ds.child("tradername").getValue(String.class);
                        }
                        if (ds.child("traderimage").getValue() != null) {
                            pimage = ds.child("traderimage").getValue(String.class);
                        }

                        if (ds.child("traderimage").getValue() != null) {
                            price = ds.child("traderimage").getValue(String.class);
                        }
                        if (ds.child("traderimage").getValue() != null) {
                            tid = ds.child("traderimage").getValue(String.class);
                        }
                        if (ds.child("traderimage").getValue() != null) {
                            tradername = ds.child("traderimage").getValue(String.class);
                        }

                        if (ds.child("traderimage").getValue() != null) {
                            traderimage = ds.child("traderimage").getValue(String.class);
                        }

                        if (ds.child("number").getValue() != null) {
                            number = ds.child("number").getValue(String.class);
                        }


                    }



                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });
        }
        //MERGING THIS WITH MULTIPLE TREEES
    }

    public class HomeActivityViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;

        //product_name
        // product_imagehere
        //  product_price

        //product_description
        //thetraderiknow

       public android.widget.ImageView   thetraderimagehere;
       public android.widget.ImageView product_imagehere;
       public TextView product_name;
       public TextView product_price;
       public TextView discounts;
       public  TextView descriptionhere;
       public TextView thetradernamegiven;
       public TextView categoryhere;
        public TextView likenumberhere;

        public android.widget.ImageView thetraderimageforproduct;
        public ItemClickListner listner;

        public HomeActivityViewHolder(View itemView) {
            super(itemView);

            //product_name
            // product_imagehere
            //  product_price

            //product_description
            //thetraderiknow



            thetraderimagehere = itemView.findViewById(R.id.thetraderimagehere);
             product_imagehere= itemView.findViewById(R.id.product_imagehere);;
             product_name= itemView.findViewById(R.id.product_name);
             product_price= itemView.findViewById(R.id.product_price);
             discounts= itemView.findViewById(R.id.discount);
              descriptionhere =itemView.findViewById(R.id.description);
             thetradernamegiven= itemView.findViewById(R.id.thetradername);
             categoryhere= itemView.findViewById(R.id.category);
            likenumberhere= itemView.findViewById(R.id.likenumber);



        }

        public void setItemClickListner(ItemClickListner listner) {
            this.listner = listner;
        }

        public void setcurrentproductname(String currentproductname) {

            product_name.setText(currentproductname);
        }

        public void setproductprice(String price) {

            product_price.setText(price);
        }

        public void settradername(String tradername) {

            thetradernamegiven.setText(tradername);
        }


        public void setdescription(String currentdescription) {

            descriptionhere.setText(currentdescription);
        }

        public void setproduct_discount(String product_discount1) {

            product_discount.setText(product_discount1);
        }
        public void setcategoryhere(String category) {

            categoryhere.setText(category);
        }

        public void setLikenumber(String likenumber) {

            likenumberhere.setText(likenumber);
        }

        public void setdicounts(String discount) {

            discounts.setText(discount);
        }

        //like, discount, category
        public void setTraderImage(final Context ctx, final String image) {
            final android.widget.ImageView thetraderimagehere = (android.widget.ImageView) itemView.findViewById(R.id.thetraderimagehere);

            Picasso.get().load(image).resize(400, 0).networkPolicy(NetworkPolicy.OFFLINE).into(thetraderimagehere, new Callback() {


                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).resize(100, 0).into(thetraderimagehere);
                }


            });
        }


        public void setImage(final Context ctx, final String image) {
            product_imagehere = (android.widget.ImageView) itemView.findViewById(R.id.product_imagehere);
            if (image != null) {
                if (product_imagehere != null) {

                    //
                    Picasso.get().load(image).resize(400, 0).networkPolicy(NetworkPolicy.OFFLINE).into(product_imagehere, new Callback() {


                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get().load(image).resize(100, 0).into(product_imagehere);
                        }


                    });

                }

            }
        }


    }

    public void fetch () {

        FirebaseUser user;
        if (mAuth != null) {
            user = mAuth.getCurrentUser();
            if (user != null) {
                userid = user.getUid();







                        /*
                        QueryFollowingsshere.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    if (ds.child("tid").getValue() != null) {
                                        followingid = ds.child("tid").getValue(String.class);
                                    }

                                    if (ds.child("tradername").getValue() != null) {
                                        followingname = ds.child("tradername").getValue(String.class);
                                    }
                                    if (ds.child("traderimage").getValue() != null) {
                                        followingimage = ds.child("traderimage").getValue(String.class);
                                    }
                                    Log.d("Followers Well", followingid + followingname + followingimage);
                                    thefolloweridgrabber = followingid;

                                    Log.d("Followersgrabber", thefolloweridgrabber);

                                    thefollowerstepper = thefolloweridgrabber;
                                    Log.d("Followerstepper", thefollowerstepper);

                                    if (getmyfollowingsagain != null) {
                                        if (followingid != null && followingname != null && followingimage != null) {

                                            getmyfollowingsagain.onCallback(followingid, followingname, followingimage);
                                             getmyfollowingsagain.onfollowining(followingid);

                                        }
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }}
                    String getfollowingid  = getmyfollowingsagain.onfollowining(followingid);
                if (clientuser != null) {
                    // No. has to give the whole population details of the product
*/

                for (int i = 0; i < thefollowingidList.size(); i++) {

                    thefollowingidindex = thefollowingidList.get(i);
                    ProductsFirebaseDatabase = FirebaseDatabase.getInstance();
                    ProductsRef = ProductsFirebaseDatabase.getReference().child("Product");
                    ProductsRef.keepSynced(true);

                    if (ProductsRef != null) {
                        ProductsQuery = ProductsRef.orderByChild("tid").equalTo(thefollowingidindex);



                        if (ProductsQuery != null) {

                            FirebaseRecyclerOptions<Products> options =
                                    new FirebaseRecyclerOptions.Builder<Products>()
                                            .setQuery(ProductsQuery, new SnapshotParser<Products>() {


                                                @NonNull
                                                @Override
                                                public Products parseSnapshot(@NonNull DataSnapshot snapshot) {


                                                    if (snapshot.child("categoryname").getValue() != null) {
                                                        categoryname = snapshot.child("categoryname").getValue(String.class);
                                                    }
                                                    if (snapshot.child("date").getValue() != null) {
                                                        date = snapshot.child("date").getValue(String.class);
                                                    }

                                                    if (snapshot.child("desc").getValue() != null) {
                                                        desc = snapshot.child("desc").getValue(String.class);
                                                    }
                                                    if (snapshot.child("discount").getValue() != null) {
                                                        discount = snapshot.child("discount").getValue(String.class);
                                                    }

                                                    if (snapshot.child("time").getValue() != null) {
                                                        time = snapshot.child("time").getValue(String.class);
                                                    }


                                                    if (snapshot.child("pimage").getValue() != null) {
                                                        pimage = snapshot.child("pimage").getValue(String.class);
                                                    }
                                                    if (snapshot.child("pname").getValue() != null) {
                                                        pname = snapshot.child("pname").getValue(String.class);
                                                    }
                                                    if (snapshot.child("pid").getValue() != null) {
                                                        pid = snapshot.child("pid").getValue(String.class);
                                                    }

                                                    if (snapshot.child("price").getValue() != null) {
                                                        price = snapshot.child("price").getValue(String.class);
                                                    }

                                                    if (snapshot.child("image").getValue() != null) {
                                                        image = snapshot.child("image").getValue(String.class);
                                                    }
                                                    if (snapshot.child("name").getValue() != null) {
                                                        name = snapshot.child("name").getValue(String.class);
                                                    }

                                                    if (snapshot.child("size").getValue() != null) {
                                                        size = snapshot.child("size").getValue(String.class);
                                                    }

                                                    if (snapshot.child("pname").getValue() != null) {
                                                        pname = snapshot.child("pname").getValue(String.class);
                                                    }

                                                    if (snapshot.child("pimage").getValue() != null) {
                                                        pimage = snapshot.child("pimage").getValue(String.class);
                                                    }

                                                    if (snapshot.child("pid").getValue() != null) {
                                                        pid = snapshot.child("pid").getValue(String.class);
                                                    }

                                                    if (snapshot.child("tradername").getValue() != null) {
                                                        tradername = snapshot.child("tradername").getValue(String.class);
                                                    }

                                                    if (snapshot.child("traderimage").getValue() != null) {
                                                        traderimage = snapshot.child("traderimage").getValue(String.class);
                                                    }

                                                    if (snapshot.child("tid").getValue() != null) {
                                                        tid = snapshot.child("tid").getValue(String.class);
                                                    }
                                                    if (snapshot.child("number").getValue() != null) {
                                                        number = snapshot.child("number").getValue(String.class);
                                                    }

                                                    return new Products(categoryname, date, desc, discount, time, pid, pimage, pname, price, image, name, size, tradername, traderimage, tid, number);


                                                }

                                            }).build();


                            adapter = new FirebaseRecyclerAdapter<Products, HomeActivityViewHolder>(options) {
                                @Override
                                public HomeActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                    View view = LayoutInflater.from(parent.getContext())
                                            .inflate(R.layout.product_items_layout, parent, false);

                                    return new HomeActivityViewHolder(view);
                                }

                                @Override
                                public int getItemCount() {
                                    return super.getItemCount();
                                }

                                @Override
                                protected void onBindViewHolder(HomeActivityViewHolder holder, final int position, final Products model) {




                                    holder.product_price.setText("GHS" + price);
                                    holder.discounts.setText(discount);
                                    holder.descriptionhere.setText(desc);
                                    holder.thetradernamegiven.setText(tradername);

                                    holder.categoryhere.setText(categoryname);

                                    holder.likenumberhere.setText(number);

                                    //   thetraderimageforproduct
                                    if (thetraderimagehere != null) {
                                        Picasso.get().load(traderimage).placeholder(R.drawable.profile).into(thetraderimagehere);
                                    }
                                    if (product_imagehere != null) {
                                        Picasso.get().load(pimage).placeholder(R.drawable.profile).into(product_imagehere);
                                    }

                                    holder.setTraderImage(getApplication(), traderimage);
                                    holder.setImage(getApplicationContext(), pimage);


                                    holder.product_imagehere.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (type.equals("Trader")) {
                                                Intent intent = new Intent(HomeActivity.this, ProductDetailsActivity.class);
                                                if (intent != null) {
                                                    intent.putExtra("pid", pid);
                                                    intent.putExtra("userID", userID);
                                                    intent.putExtra("role", role);
                                                }
                                                startActivity(intent);
                                            } else {
                                                Intent intent = new Intent(HomeActivity.this, ProductDetailsActivity.class);
                                                if (intent != null) {
                                                    intent.putExtra("pid", pid);
                                                    intent.putExtra("userID", userID);
                                                    intent.putExtra("role", role);
                                                }
                                                startActivity(intent);
                                            }
                                        }
                                    });


                                    holder.thetraderimageforproduct.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (type.equals("Trader")) {
                                                Intent intent = new Intent(HomeActivity.this, TraderProfile.class);
                                                intent.putExtra("pid", pid);
                                                intent.putExtra("userID", userID);
                                                intent.putExtra("role", role);

                                                startActivity(intent);
                                            } else {
                                                Intent intent = new Intent(HomeActivity.this, TraderProfile.class);
                                                intent.putExtra("pid", pid);
                                                intent.putExtra("userID", userID);
                                                intent.putExtra("role", role);

                                                startActivity(intent);
                                            }

                                        }

                                    });


                                }


                            };

                        }

                    }

                }


            }

        }

    }

        /*
        FirebaseDatabase.getInstance().getReference().child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                     int i = 0;
                    for  (i=0; i< followingidList.length(); i++) {
                        if (user.getuid().equals(uid)) {
                            mUsers.add(user);
                        }
                    }
                }
                Log.d("list f", mUsers.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/




        @Override
    protected void onStart() {
        super.onStart();


        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                 user = mAuth.getCurrentUser();
                if (mAuth != null) {
                    if (user != null) {

                        userID = user.getUid();
                    }

                    // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                    // WHICH IS CUSTOMER TO BE ADDED.
                    // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
                }
            }    };



            if (mAuth != null) {
                mAuth.addAuthStateListener(firebaseAuthListener);
            }

        if (adapter != null) {
            adapter.startListening();

        }


    }

    @Override
    protected void onStop () {
        super.onStop();
        if (adapter != null){
        adapter.stopListening();
        //     mProgress.hide();
        if (mAuth != null) {
            mAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }}
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
            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, AdminAllCustomers.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminAllCustomers.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.addnewproducthere) {

            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, AdminAddNewProductActivityII.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminAddNewProductActivityII.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.singeuserorderhere) {

            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, ViewYourPersonalProduct.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, ViewYourPersonalProduct.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }

//AdminNewOrders
             if (id == R.id.viewbuyershere) {

            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, ViewSpecificUsersCart.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, ViewSpecificUsersCart.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }

        if (id == R.id.AdminNewOrders) {

            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, AdminNewOrdersActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminNewOrdersActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.usercartedactivityhere) {

            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, ViewAllCarts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, ViewAllCarts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.newproductdetailshere) {
            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, AdminProductDetails.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminProductDetails.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.Maintainnewproducts) {
            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, AdminMaintainProductsActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminMaintainProductsActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.allcategorieshere) {

            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, AdminCategoryActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminCategoryActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.allproductshere) {
            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, AdminAllProducts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminAllProducts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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
            if (!type.equals("Trader")) {

                Intent intent = new Intent(HomeActivity.this, com.simcoder.bimbo.CustomerMapActivity.class);
                if (intent != null) {
                    intent.putExtra("roledhomeactivitytocustomermapactivity", type);
                    intent.putExtra("fromhomeactivitytocustomermapactivity", userID);
                    startActivity(intent);
                    finish();
                }
            } else {

                Intent intent = new Intent(HomeActivity.this, DriverMapActivity.class);
                if (intent != null) {
                    intent.putExtra("rolefromhomeactivitytodrivermapactivity", type);
                    intent.putExtra("fromhomeactivitytodrivermapactivity", userID);
                    startActivity(intent);
                    finish();
                }
            }


        }
        if (id == R.id.nav_cart) {
            if (!type.equals("Trader")) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                if (intent != null) {
                    startActivity(intent);
                }
            }

        }

        if (id == R.id.viewproducts) {
            if (!type.equals("Trader")) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                if (intent != null) {
                    startActivity(intent);
                }
            } else {
            }

        }
        if (id == R.id.nav_search) {
            if (!type.equals("Trader")) {
                Intent intent = new Intent(HomeActivity.this, SearchProductsActivity.class);
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
                    mGoogleSignInClient.signOut().addOnCompleteListener(HomeActivity.this,
                            new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                }
            }
            Intent intent = new Intent(HomeActivity.this, com.simcoder.bimbo.MainActivity.class);
            if (intent != null) {
                startActivity(intent);
                finish();
            }
        }

        if (id == R.id.nav_settings) {
            if (!type.equals("Trader")) {
                Intent intent = new Intent(HomeActivity.this, com.simcoder.bimbo.WorkActivities.SettinsActivity.class);
                if (intent != null) {
                    startActivity(intent);
                }
            } else {
            }
        }
        if (id == R.id.nav_history) {
            if (!type.equals("Trader")) {
                Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
                if (intent != null) {
                    startActivity(intent);
                }
            } else {
            }
        }
        if (id == R.id.nav_categories) {

        }
        if (id == R.id.nav_viewprofilehome) {
            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, CustomerProfile.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, TraderProfile.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }
        if (id == R.id.viewallcustomershere) {
            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminAllCustomers.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.addnewproducthere) {

            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminAddNewProductActivityII.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.singeuserorderhere) {

            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, ViewYourPersonalProduct.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.viewbuyershere) {

            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, ViewSpecificUsersCart.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.usercartedactivityhere) {

            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, ViewAllCarts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.newproductdetailshere) {
            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminProductDetails.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.Maintainnewproducts) {
            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminMaintainProductsActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.allcategorieshere) {

            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminCategoryActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }


        if (id == R.id.allproductshere) {
            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(HomeActivity.this, AdminAllProducts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
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

                        Intent intent = new Intent(HomeActivity.this, AdminAllProducts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", userID);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }



        return true;
    }

}
