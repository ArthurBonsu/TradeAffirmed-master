package com.simcoder.bimbo.Approver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.rey.material.widget.ImageView;
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
import com.simcoder.bimbo.DriverMapActivity;
import com.simcoder.bimbo.HistoryActivity;
import com.simcoder.bimbo.Interface.ItemClickListner;
import com.simcoder.bimbo.Model.Users;
import com.simcoder.bimbo.R;
import com.simcoder.bimbo.WorkActivities.CartActivity;
import com.simcoder.bimbo.WorkActivities.HomeActivity;
import com.simcoder.bimbo.WorkActivities.TraderProfile;
import com.simcoder.bimbo.instagram.Home.InstagramHomeActivity;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public  class ApprovalDirectRejectOrReviewForTrader extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //Here we approve or reject both the status
    DatabaseReference ProductsRef;
    private DatabaseReference Userdetails;
    private DatabaseReference ProductsRefwithproduct;
    private DatabaseReference UsersRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference AllOrderDatabaseRef;
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

    public ApprovalDirectRejectOrReviewViewHolders holders;

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
    String orderkey;

    Getmyfollowings getmyfollowingsagain;
    String userkey;
    TextView orderid;
    TextView customername;
    TextView thetradername;
    TextView orderedtime;
    TextView ordereddate;
    String newornot;
    String aid;
    String approvername;
    String approvalID;
    String userID;
    DatabaseReference ApprovalRef;

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


    TextView immediateeapprovaldate;
    TextView uidimmediateapprovalvalue;
    TextView nameforimmediateapprovalvalue;
    Button rejectimmediately;
    Button reviewimmediately;
    String approverID;
    String status, approverimage;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                (R.layout.activityhomeforadmin));

        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent approverIDintent = getIntent();
        if (approverIDintent.getExtras().getString("approverID") != null) {
            approverID = approverIDintent.getExtras().getString("approverID");
        }
        Intent userIDIntent = getIntent();
        if (userIDIntent.getExtras().getString("userID") != null) {
            userID = userIDIntent.getExtras().getString("userID");
        }



        Intent approvalIDintent = getIntent();
        if (approvalIDintent.getExtras().getString("approvalID") != null) {
            approvalID = approvalIDintent.getExtras().getString("approvalID");
        }


        recyclerView = findViewById(R.id.recycler_menu);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
        }
     /*  if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);

        }
*/

        immediateeapprovaldate = (TextView) findViewById(R.id.immediateeapprovaldate);
        uidimmediateapprovalvalue = (TextView) findViewById(R.id.nameforimmediateapprovalvalue);
        nameforimmediateapprovalvalue = (TextView) findViewById(R.id.nameforimmediateapprovalvalue);
        rejectimmediately = (Button) findViewById(R.id.rejectimmediately);
        reviewimmediately = (Button) findViewById(R.id.reviewimmediately);


        Paper.init(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.hometoolbar);
        if (toolbar != null) {
            toolbar.setTitle("Approval Direct Review Or Reject");
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
                        approverID = user.getUid();

                    }


//        setSupportActionBar(toolbar);

                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
                    if (mGoogleApiClient != null) {

                        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
                    }

                    if (mGoogleApiClient != null) {
                        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(ApprovalDirectRejectOrReviewForTrader.this,
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


    public class ApprovalDirectRejectOrReviewViewHolders extends RecyclerView.ViewHolder {
        public LinearLayout root;

        public TextView immediateeapprovaldate;
        public TextView uidimmediateapprovalvalue;
        public TextView nameforimmediateapprovalvalue;
        public Button rejectimmediately;
        public Button reviewimmediately;


        public android.widget.ImageView theapproverhomepic;
        public ItemClickListner listner;

        public ApprovalDirectRejectOrReviewViewHolders(View itemView) {
            super(itemView);

            immediateeapprovaldate = itemView.findViewById(R.id.immediateeapprovaldate);
            uidimmediateapprovalvalue = itemView.findViewById(R.id.uidimmediateapprovalvalue);
            nameforimmediateapprovalvalue = itemView.findViewById(R.id.nameforimmediateapprovalvalue);

            rejectimmediately = itemView.findViewById(R.id.rejectimmediately);
            reviewimmediately = itemView.findViewById(R.id.reviewimmediately);


        }

        public void setItemClickListner(ItemClickListner listner) {
            this.listner = listner;
        }


        public void setimmediateapprovaldate(String _immediateeapprovaldate) {

            immediateeapprovaldate.setText(_immediateeapprovaldate);
        }

        public void setimmediateapprovalvalue(String _immediateapprovalvalue) {

            uidimmediateapprovalvalue.setText(_immediateapprovalvalue);
        }

        public void set(String _theuiditselftext) {

            nameforimmediateapprovalvalue.setText(_theuiditselftext);
        }


    }

    ;


    public void useValue(String yourValue) {

        Log.d(TAG, "countryNameCode: " + yourValue);

    }


    private void fetch() {
        if (mAuth != null) {
            user = mAuth.getCurrentUser();
            if (user != null) {
                approverID = user.getUid();

            }

            @Nullable

            Query queryhere =

                    FirebaseDatabase.getInstance().getReference().child("Approval").orderByChild("statusidentifier").equalTo("pendingTrader");
            if (queryhere != null) {

                FirebaseRecyclerOptions<Users> options =
                        new FirebaseRecyclerOptions.Builder<Users>()
                                .setQuery(queryhere, new SnapshotParser<Users>() {


                                    @Nullable
                                    @Override
                                    public Users parseSnapshot(@Nullable DataSnapshot snapshot) {


                                      /*
                                      String commentkey = snapshot.child("Comments").getKey();
                                      String likekey = snapshot.child("Likes").getKey();
*/


                                        Log.i(TAG, "Approval Direct Reject For Trader " + snapshot);


                                        if (snapshot.child("uid").getValue(String.class) != null) {
                                            uid = snapshot.child("uid").getValue(String.class);
                                        }

                                        if (snapshot.child("status").getValue(String.class) != null) {
                                            status = snapshot.child("status").getValue(String.class);
                                        }

                                        if (snapshot.child("approverimage").getValue(String.class) != null) {
                                            approverimage = snapshot.child("approverimage").getValue(String.class);
                                        }
                                        if (snapshot.child("aid").getValue(String.class) != null) {
                                            aid = snapshot.child("aid").getValue(String.class);
                                        }
                                        if (snapshot.child("approvalID").getValue(String.class) != null) {
                                            approvalID = snapshot.child("approvalID").getValue(String.class);
                                        }
                                        if (snapshot.child("approvername").getValue(String.class) != null) {
                                            approvername = snapshot.child("approvername").getValue(String.class);
                                        }
                                        if (snapshot.child("tid").getValue(String.class) != null) {
                                            tid = snapshot.child("tid").getValue(String.class);
                                        }
                                        if (snapshot.child("date").getValue(String.class) != null) {
                                            date = snapshot.child("date").getValue(String.class);
                                        }
                                        if (snapshot.child("time").getValue(String.class) != null) {
                                            time = snapshot.child("time").getValue(String.class);
                                        }

                                        return new Users(aid, tid, status, date, approvalID);
                                    }
                                }).build();


                feedadapter = new FirebaseRecyclerAdapter<Users, ApprovalDirectRejectOrReviewForTrader.ApprovalDirectRejectOrReviewViewHolders>(options) {
                    @Nullable
                    @Override
                    public ApprovalDirectRejectOrReviewViewHolders onCreateViewHolder(ViewGroup parent, int viewrole) {

                        @Nullable
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.approvaldirectrejectorreview, parent, false);

                        return new ApprovalDirectRejectOrReviewViewHolders(view);
                    }


                    @Override
                    public int getItemCount() {
                        return super.getItemCount();
                    }


                    @Override
                    protected void onBindViewHolder(@Nullable final ApprovalDirectRejectOrReviewForTrader.ApprovalDirectRejectOrReviewViewHolders holder, int position, @Nullable Users model) {
                        if (model != null) {
                            holders = holder;

                            holder.immediateeapprovaldate.setText(date);
                            holder.nameforimmediateapprovalvalue.setText(tid);
                            holder.uidimmediateapprovalvalue.setText(approvername);

                            Log.d(TAG, "The Pending Review List for Traders Here  " + date + approvername);


                            if (holder.reviewimmediately != null) {
                                holder.reviewimmediately.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {


                                        Intent reviewbuttonintent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, ApprovalPagelForCustomerAndTrader.class);
                                        reviewbuttonintent.putExtra("role", role);
                                        reviewbuttonintent.putExtra("uid", tid);
                                        reviewbuttonintent.putExtra("approverID", approverID);
                                        reviewbuttonintent.putExtra("approvalID", approvalID);
                                        reviewbuttonintent.putExtra("userID", userID);


                                        startActivity(reviewbuttonintent);

                                    }
                                });
                            }
                            if (holder.rejectimmediately != null) {
                                holder.rejectimmediately.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                           status = "reject";
                                        setnewstatus(status);
                                        Toast.makeText(getApplicationContext(), "The User has been rejected ", Toast.LENGTH_SHORT).show();
                                        Intent reviewimmediatelyintent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, ApproveHome.class);

                                        startActivity(reviewimmediatelyintent);

                                    }
                                });
                            }


                        }
                    }


                };


            }


            if (recyclerView != null) {
                recyclerView.setAdapter(feedadapter);
            }

        }

    }

    // Post Info
    private void setnewstatus(String rejectorapprovestatus) {
        ApprovalRef = FirebaseDatabase.getInstance().getReference().child("Product");
        // GET DATES FOR PRODUCTS
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");

        if (currentDate != null) {
            date = currentDate.format(calendar.getTime()).toString();

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            if (currentTime != null) {
                time = currentTime.format(calendar.getTime());

            }

            status = rejectorapprovestatus;
            mProgress.setMessage("Setting status as " + rejectorapprovestatus);

            mProgress.show();

            // PICK UP THE SPECIAL PRODUCT INFO AND LOADING THEM INTO THE DATABASE
            Users approvalstobesent = new Users(aid, tid, status, date, approvalID);

            ApprovalRef.child(aid).setValue(approvalstobesent, new
                    DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference
                                databaseReference) {
                            Toast.makeText(getApplicationContext(), "The User has been rejected rejected", Toast.LENGTH_SHORT).show();
                            Intent approvaldirectreject = new Intent(ApprovalDirectRejectOrReviewForTrader.this, HomeActivity.class);

                            startActivity(approvaldirectreject);

                        }
                    });
        }
    }


    public void onConnected(@Nullable Bundle bundle) {

    }


    public void onConnectionSuspended(int i) {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }


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
                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AdminAllCustomers.class);
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
                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, ViewAllCarts.class);
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
                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AdminAddNewProductActivityII.class);
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
                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AdminAllProducts.class);
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
                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AllProductsPurchased.class);
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
                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, ViewAllCustomers.class);
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
                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, TradersFollowing.class);
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
                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AdminNewOrdersActivity.class);
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
                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AdminCustomerServed.class);
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
                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AdminAllOrderHistory.class);
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

                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
                if (intent != null) {
                    intent.putExtra("traderorcustomer", traderID);
                    intent.putExtra("role", role);
                    startActivity(intent);
                    finish();
                }
            } else {

                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, DriverMapActivity.class);
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
                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, CartActivity.class);
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
                            Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                            Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, InstagramHomeActivity.class);
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
                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AdminAllProducts.class);
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
                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, SearchForAdminProductsActivity.class);
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
                                    mGoogleSignInClient.signOut().addOnCompleteListener(ApprovalDirectRejectOrReviewForTrader.this,
                                            new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                }
                                            });
                                }
                            }
                            Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, com.simcoder.bimbo.MainActivity.class);
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
                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, com.simcoder.bimbo.WorkActivities.SettinsActivity.class);
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
                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, HistoryActivity.class);
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
                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, TraderProfile.class);
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
                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AdminAllCustomers.class);
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
                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AdminAddNewProductActivityII.class);
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
                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AllGoodsBought.class);
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
                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AdminPaymentHere.class);
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
                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(ApprovalDirectRejectOrReviewForTrader.this, AdminSettings.class);
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

            }

        }
        return false;
    }}


