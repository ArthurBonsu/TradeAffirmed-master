package com.simcoder.bimbo.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.ImageView;
import com.simcoder.bimbo.DriverMapActivity;
import com.simcoder.bimbo.HistoryActivity;
import com.simcoder.bimbo.Interface.ItemClickListner;
import com.simcoder.bimbo.Model.AdminOrders;
import com.simcoder.bimbo.Model.Users;
import com.simcoder.bimbo.R;
import com.simcoder.bimbo.WorkActivities.CartActivity;
import com.simcoder.bimbo.WorkActivities.CustomerProfile;
import com.simcoder.bimbo.WorkActivities.TraderProfile;
import com.simcoder.bimbo.instagram.Home.InstagramHomeActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public  class  AdminCustomerServed extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseReference ProductsRef;
    private DatabaseReference Userdetails;
    private DatabaseReference ProductsRefwithproduct;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference UsersRef;
    DatabaseReference FollowerDatabaseReference;
    String productkey;
    String traderkeyhere;
    private String role = "";
    String traderID = "";
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    String ProductID;
    FirebaseDatabase myfirebaseDatabase;
    FirebaseDatabase FollowerDatabase;
    DatabaseReference OrdersReference;

    public  ViewHolder holders;

    public FirebaseRecyclerAdapter feedadapter;

    //AUTHENTICATORS

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Google Activity";
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;


    TextView allcustomersname;
    TextView allcustomersphonenumber;
    TextView allcustomersjob;
    ImageView allcustomersimage;
    String traderkey;
    String key;
    String tradename;
    String traderimage;
    FirebaseUser user;


    String categoryname, date, desc, discount, time, pid, pimage, pname, price, image, name, size, tradername, tid;
    String thetraderimage;
    String address;
    String amount;
    String city;
    String delivered;
    String distance;
    String uid;
    String mode;

    String number;
    String phone;
    String quantity;
    String shippingcost;
    String state;
    String thecustomersjob;

    Getmyfollowings getmyfollowingsagain;
    String userkey;
    String hasbeeserviced;
    String orderkey;
    String useranddelivered;
/*
    addnewproducthere
            allproductshere
    allproductspurchased
            viewallcustomershere
    tradersfollowing
            Maintainnewordershere
    AdminNewOrders
            allcustomersincart
    allcustomersserved
            allorders
  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                (R.layout.activityhomeforadmin));


        recyclerView = findViewById(R.id.recycler_menu);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
        }
     /*  if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);

        }
*/


        allcustomersname = findViewById(R.id.allcustomersname);
        allcustomersphonenumber = findViewById(R.id.allcustomersphonenumber);

        allcustomersjob = findViewById(R.id.allcustomersjob);


        allcustomersimage = (ImageView) findViewById(R.id.allcustomersimage);


        Paper.init(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.hometoolbar);
        if (toolbar != null) {
            toolbar.setTitle("All Customers");
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


                myfirebaseDatabase = FirebaseDatabase.getInstance();

                UsersRef = myfirebaseDatabase.getReference().child("Users");


                userkey = UsersRef.getKey();
                // GET FROM FOLLOWING KEY


                fetch();
                recyclerView.setAdapter(feedadapter);


                if (mAuth != null) {
                    user = mAuth.getCurrentUser();
                    if (user != null) {
                        traderID = user.getUid();

                    }


//        setSupportActionBar(toolbar);

                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
                    if (mGoogleApiClient != null) {

                        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
                    }

                    if (mGoogleApiClient != null) {
                        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(AdminCustomerServed.this,
                                new GoogleApiClient.OnConnectionFailedListener() {
                                    @Override
                                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                                    }
                                }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
                    }


                    // USER
                    user = mAuth.getCurrentUser();


                }
            }
        }
    }


    public interface Getmyfollowings {

        void onCallback(String followingid, String followingname, String followingimage);


    }





    //GETFOLLOWING WILL PULL FROM DIFFERENT DATASTORE( THE USER DATASTORE)


    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;





        public TextView allcustomersname;
        public TextView allcustomersphonenumber;

        public TextView allcustomersjob;

        public android.widget.ImageView allcustomersimage;
        public ItemClickListner listner;

        public ViewHolder(View itemView) {
            super(itemView);




            allcustomersname = itemView.findViewById(R.id.allcustomersname);
            allcustomersphonenumber = itemView.findViewById(R.id.allcustomersphonenumber);
            allcustomersjob = itemView.findViewById(R.id.allcustomersjob);




        }

        public void setItemClickListner(ItemClickListner listner) {
            this.listner = listner;
        }

        public void setallcustomersshown(String allcustomershownname) {

            allcustomersname.setText(allcustomershownname);
        }

        public void setallcustomerphoneinformation(String customersphonenumber) {

            allcustomersphonenumber.setText(customersphonenumber);
        }

        public void settradername(String setthecustomerjob) {

            allcustomersjob.setText(setthecustomerjob);
        }



        public void setallcustomersimage(final Context ctx, final String image) {
            final android.widget.ImageView allcustomersimage = (android.widget.ImageView) itemView.findViewById(R.id.allcustomersimage);

            Picasso.get().load(image).resize(400, 0).networkPolicy(NetworkPolicy.OFFLINE).into(allcustomersimage, new Callback() {


                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).resize(100, 0).into(allcustomersimage);
                }


            });
        }




    };


    public void useValue (String yourValue){

        Log.d(TAG, "countryNameCode: " + yourValue);

    }


    private void fetch() {
        if (mAuth != null) {
            user = mAuth.getCurrentUser();
            if (user != null) {
                traderID = user.getUid();

            }

            @Nullable

            Query queryhere =

                    FirebaseDatabase.getInstance().getReference().child("Orders").orderByChild("tid").equalTo(traderID);
            if (queryhere != null) {

                FirebaseRecyclerOptions<AdminOrders> options =
                        new FirebaseRecyclerOptions.Builder<AdminOrders>()
                                .setQuery(queryhere, new SnapshotParser<AdminOrders>() {


                                    @Nullable
                                    @Override
                                    public AdminOrders parseSnapshot(@Nullable DataSnapshot snapshot) {


                                      /*
                                      String commentkey = snapshot.child("Comments").getKey();
                                      String likekey = snapshot.child("Likes").getKey();
*/


                                        Log.i(TAG, "AdminAllCustomers " + snapshot);


                                        if (snapshot.child("date").getValue(String.class) != null) {
                                            date = snapshot.child("date").getValue(String.class);
                                        }

                                        if (snapshot.child("time").getValue(String.class) != null) {
                                            time = snapshot.child("time").getValue(String.class);
                                        }

                                        if (snapshot.child("tid").getValue(String.class) != null) {
                                            tid = snapshot.child("tid").getValue(String.class);
                                        }
                                        if (snapshot.child("traderimage").getValue(String.class) != null) {
                                            thetraderimage = snapshot.child("traderimage").getValue(String.class);
                                        }


                                        if (snapshot.child("tradername").getValue(String.class) != null) {
                                            tradername = snapshot.child("tradername").getValue(String.class);
                                        }


                                        if (snapshot.child("address").getValue(String.class) != null) {
                                            address = snapshot.child("address").getValue(String.class);
                                        }


                                        if (snapshot.child("amount").getValue(String.class) != null) {
                                            amount = snapshot.child("amount").getValue(String.class);
                                        }


                                        if (snapshot.child("city").getValue(String.class) != null) {
                                            city = snapshot.child("city").getValue(String.class);
                                        }


                                        if (snapshot.child("delivered").getValue(String.class) != null) {
                                            delivered = snapshot.child("delivered").getValue(String.class);
                                        }

                                        if (snapshot.child("distance").getValue(String.class) != null) {
                                            distance = snapshot.child("distance").getValue(String.class);
                                        }

                                        if (snapshot.child("image").getValue(String.class) != null) {
                                            image = snapshot.child("image").getValue(String.class);
                                        }
                                        if (snapshot.child("uid").getValue(String.class) != null) {
                                            uid = snapshot.child("uid").getValue(String.class);
                                        }
                                        if (snapshot.child("name").getValue(String.class) != null) {
                                            name = snapshot.child("name").getValue(String.class);
                                        }
                                        if (snapshot.child("mode").getValue(String.class) != null) {
                                            mode = snapshot.child("mode").getValue(String.class);
                                        }


                                        if (snapshot.child("number").getValue(String.class) != null) {
                                            number = snapshot.child("number").getValue(String.class);
                                        }

                                        if (snapshot.child("phone").getValue(String.class) != null) {
                                            phone = snapshot.child("phone").getValue(String.class);
                                        }
                                        if (snapshot.child("quantity").getValue(String.class) != null) {
                                            quantity = snapshot.child("quantity").getValue(String.class);
                                        }

                                        if (snapshot.child("shippingcost").getValue(String.class) != null) {
                                            shippingcost = snapshot.child("shippingcost").getValue(String.class);
                                        }

                                        if (snapshot.child("state").getValue(String.class) != null) {
                                            state = snapshot.child("state").getValue(String.class);
                                        }
                                        if (snapshot.child("state").getValue(String.class) != null) {
                                            orderkey = snapshot.child("state").getValue(String.class);
                                        }

                                        return new AdminOrders(date, time, tid, thetraderimage, tradername,uid,name,image);


                                    }

                                }).build();


                feedadapter = new FirebaseRecyclerAdapter<AdminOrders, ViewHolder>(options) {
                    @Nullable
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewrole) {

                        @Nullable
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.admincustomeractivity, parent, false);

                        return new ViewHolder(view);
                    }


                    @Override
                    public int getItemCount() {
                        return super.getItemCount();
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder holder, int i, @NonNull AdminOrders adminOrders) {
                        if (adminOrders != null) {

                            hasbeeserviced = tid + delivered;
                            holders = holder;


                            holder.allcustomersname.setText(name);
                            holder.allcustomersphonenumber.setText(phone);


                            Log.d(TAG, "The Customers here " + name + phone);
                            holder.setallcustomersimage(getApplicationContext(), image);

              // This is a rare scenario, it is called a three layer scenario
                            // In this case we are trying to query for the information that is subject to the user
                            //SO THE RULES ARE , YOU QUERY TRADER AND THEN QUERY USER + INDEX( WHETHER DELIVERED OR NOT)
                            myfirebaseDatabase = FirebaseDatabase.getInstance();
                            useranddelivered = uid + "true";


                                OrdersReference = myfirebaseDatabase.getReference().child("Orders").child("products");
                                UsersRef.keepSynced(true);
                                Query firebasequery = myfirebaseDatabase.getReference().child("Orders").orderByChild("useranddelivered").equalTo(useranddelivered);

                                firebasequery.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                            userkey = dataSnapshot.getKey();
                                            Log.d(TAG, "The ServedPeopleKey " + uid);

                                            Log.d(TAG, "Served people key here  " + uid);
                                            if (dataSnapshot.child("name").getValue() != null) {
                                                name = dataSnapshot.child("name").getValue(String.class);
                                                holders.allcustomersname.setText(name);
                                                Log.d(TAG, "The customer " + name);
                                            }
                                        if (dataSnapshot.child("phone").getValue() != null) {
                                            phone = dataSnapshot.child("phone").getValue(String.class);
                                            holders.allcustomersphonenumber.setText(phone);
                                            Log.d(TAG, "The customer phone " + phone);
                                        }
                                        if (dataSnapshot.child("image").getValue() != null) {
                                            image = dataSnapshot.child("image").getValue(String.class);
                                            holders.allcustomersphonenumber.setText(image);
                                            Log.d(TAG, "The customer image " + image);
                                        }
                                        if (allcustomersimage != null) {
                                            Picasso.get().load(image).placeholder(R.drawable.profile).into(allcustomersimage);
                                        }


                                    }



                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });




                                if (holder.allcustomersname != null) {
                                    holder.allcustomersname.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent allcustomernameesintent = new Intent(AdminCustomerServed.this, CustomerProfile.class);

                                            startActivity(allcustomernameesintent);

                                        }
                                    });
                                }
                                if (holder.allcustomersimage != null) {
                                    holder.allcustomersimage.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent allcustomerimagesintent = new Intent(AdminCustomerServed.this, CustomerProfile.class);

                                            startActivity(allcustomerimagesintent);

                                        }
                                    });
                                }


                            }
                        }


                    }

                    ;



            };

            if (recyclerView != null) {
                recyclerView.setAdapter(feedadapter);
            }
                    }



    }
    @Nullable
    @Override
    public void onStart() {
        super.onStart();
        if (feedadapter != null) {
            feedadapter.startListening();
        }

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                user = mAuth.getCurrentUser();
                if (mAuth != null) {
                    if (user != null) {

                        traderID = user.getUid();
                    }

                    // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                    // WHICH IS CUSTOMER TO BE ADDED.
                    // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
                }
            }    };


        if (mAuth != null) {
            mAuth.addAuthStateListener(firebaseAuthListener);
        }



    }


    @Override
    public void onStop () {
        super.onStop();
        if (feedadapter != null) {
            feedadapter.stopListening();
        }
        //     mProgress.hide();
        if (mAuth != null) {
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
                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderID);
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

                        Intent intent = new Intent(AdminCustomerServed.this, AdminAllCustomers.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderID);
                            intent.putExtra("role", role);
                            startActivity(intent);
                        }
                    }
                }
            }
        }



        if (id == R.id. allcustomersincart) {

            if (!role.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderID);
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

                        Intent intent = new Intent(AdminCustomerServed.this, ViewAllCarts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderID);
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
                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderID);
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

                        Intent intent = new Intent(AdminCustomerServed.this, AdminAddNewProductActivityII.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderID);
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
                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderID);
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

                        Intent intent = new Intent(AdminCustomerServed.this, AdminAllProducts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderID);
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
                                Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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

                                Intent intent = new Intent(AdminCustomerServed.this, AllProductsPurchased.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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
                                Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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

                                Intent intent = new Intent(AdminCustomerServed.this, ViewAllCustomers.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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
                                Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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

                                Intent intent = new Intent(AdminCustomerServed.this, TradersFollowing.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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
                                Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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

                                Intent intent = new Intent(AdminCustomerServed.this, AdminNewOrdersActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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
                                Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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

                                Intent intent = new Intent(AdminCustomerServed.this, AdminCustomerServed.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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
                                Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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

                                Intent intent = new Intent(AdminCustomerServed.this, AdminAllOrderHistory.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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

                Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                if (intent != null) {
                    intent.putExtra("traderorcustomer", traderID);
                    intent.putExtra("role", role);
                    startActivity(intent);
                    finish();
                }
            } else {

                Intent intent = new Intent(AdminCustomerServed.this, DriverMapActivity.class);
                if (intent != null) {
                    intent.putExtra("traderorcustomer", traderID);
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
                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderID);
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

                        Intent intent = new Intent(AdminCustomerServed.this, CartActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderID);
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
                            Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                            if (intent != null) {
                                intent.putExtra("traderorcustomer", traderID);
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

                            Intent intent = new Intent(AdminCustomerServed.this, InstagramHomeActivity.class);
                            if (intent != null) {
                                intent.putExtra("traderorcustomer", traderID);
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
                                Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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

                                Intent intent = new Intent(AdminCustomerServed.this, AdminAllProducts.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderID);
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
                                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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

                                        Intent intent = new Intent(AdminCustomerServed.this, SearchForAdminProductsActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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
                                    mGoogleSignInClient.signOut().addOnCompleteListener(AdminCustomerServed.this,
                                            new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                }
                                            });
                                }
                            }
                            Intent intent = new Intent(AdminCustomerServed.this, com.simcoder.bimbo.MainActivity.class);
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
                                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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

                                        Intent intent = new Intent(AdminCustomerServed.this, com.simcoder.bimbo.WorkActivities.SettinsActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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
                                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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

                                        Intent intent = new Intent(AdminCustomerServed.this, HistoryActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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
                                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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

                                        Intent intent = new Intent(AdminCustomerServed.this, TraderProfile.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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
                                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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

                                        Intent intent = new Intent(AdminCustomerServed.this, AdminAllCustomers.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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
                                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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

                                        Intent intent = new Intent(AdminCustomerServed.this, AdminAddNewProductActivityII.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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
                                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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

                                        Intent intent = new Intent(AdminCustomerServed.this, AllGoodsBought.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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
                                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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

                                        Intent intent = new Intent(AdminCustomerServed.this, AdminPaymentHere.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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
                                        Intent intent = new Intent(AdminCustomerServed.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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

                                        Intent intent = new Intent(AdminCustomerServed.this, AdminSettings.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderID);
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
}
