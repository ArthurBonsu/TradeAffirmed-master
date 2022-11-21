package com.simcoder.bimbo.Approver;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.storage.StorageReference;
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
import com.simcoder.bimbo.Model.BackgroundInfoSubmitModel;
import com.simcoder.bimbo.Model.Users;
import com.simcoder.bimbo.WorkActivities.CartActivity;
import com.simcoder.bimbo.WorkActivities.HomeActivity;
import com.simcoder.bimbo.DriverMapActivity;
import com.simcoder.bimbo.HistoryActivity;
import com.simcoder.bimbo.Interface.ItemClickListner;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simcoder.bimbo.R;
import com.simcoder.bimbo.WorkActivities.TraderProfile;
import com.simcoder.bimbo.instagram.Home.InstagramHomeActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;


public  class BackgroundInfoApproveForTrader extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //These are susbmissions to be made for each of the modules
    DatabaseReference ProductsRef;
    private DatabaseReference Userdetails;
    private DatabaseReference ProductsRefwithproduct;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference UsersRef;
    DatabaseReference FollowerDatabaseReference;
    String productkey;
    String traderkeyhere;
    private String type = "";
    String traderoruser = "";
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    String ProductID;
    FirebaseDatabase myfirebaseDatabase;
    FirebaseDatabase FollowerDatabase;
    String gender;
    public ViewHolder holders;

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
    String uid, name, address, street, gpscode, country;

    String categoryname, date, desc, discount, time, pid, pimage, pname, price, image,  size, tradername, tid;
    String thetraderimage;

    String amount;
    String city;
    String delivered;
    String distance;

    String mode;

    String number;
    String phone;
    String quantity;
    String shippingcost;
    String state;
    String thecustomersjob;

    String userkey;

    private RecyclerView productsList;
    private DatabaseReference cartListRef;
    private Query mQueryTraderandUserCart;
    private String userID = "";
    String traderID = "";
    Query QueryUser;
    String role;
    String cartkey;
    String photoid;
    String getimage;
    DatabaseReference myreferencetoimage;
    String productID;

    ImageButton ApprovalButtton;
    ImageButton RejectButton;
    ImageButton PauseButton;

    ImageView ProfileImageofPerson;
    TextView NameofPerson;
    TextView PhoneNumberofPerson;
    TextView PersonEmail;
    TextView Gender;
    TextView Age;
    String email;
    String age;
    TextView candidateuserid;
    String
            backgroundinfostatus;
    String backgroundinfoactivity = "backgroundinfoapproveact";
    String  auxname,auxemail,auxcountry,auxid,auxidtype, auxphone;
    //
    //AUTHENTICATORS
    android.widget.ImageView admincartimageofproduct;
    TextView admincartproductid;
    TextView admincarttitlehere;
    TextView admincartquantity;
    TextView admincart_price;
    TextView admincarttime;

    ImageView admincartimageofuser;
    TextView admincartusername;

    ImageView admincartimageofprouct;
    String traderuser;
    String trader;
    private Uri mImageUri = null;
    private static final int GALLERY_REQUEST = 1;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseCHURCHCHOSEN;
    private ProgressDialog mProgress;
    TextView CadidateId;
    TextView CandidateStreet;
    TextView CandidateGPSCode;
    TextView CandidateCountry;

    TextView EmergencyPersonName;
    TextView EmergencyPersonPhone;
    TextView EmergencyPersonEmail;
    TextView EmergencyPersonID;
    TextView EmergencyPersonCountry;
    TextView EmergencyPersonType;

    ImageButton candidateapprovebackbutton;
    ImageButton candidateapprovenextbutton;
    String approverID, approvalID;
    String  emcountry,empersonname, ememail, empersionid, emphone,  empidtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                (R.layout.stickynoterecycler));

        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent approverIDintent = getIntent();
        if (approverIDintent.getExtras().getString("approverID") != null) {
            approverID= approverIDintent.getExtras().getString("approverID");
        }
        Intent userIDIntent = getIntent();
        if (userIDIntent.getExtras().getString("userID") != null) {
            userID= userIDIntent.getExtras().getString("userID");
        }



        Intent approvalIDintent = getIntent();
        if (approvalIDintent.getExtras().getString("approvalID") != null) {
            approvalID= approvalIDintent.getExtras().getString("approvalID");
        }


        recyclerView = findViewById(R.id.stickyheaderrecyler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
        }
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);

        }

        ApprovalButtton = (ImageButton) findViewById(R.id.approve);
        RejectButton = (ImageButton) findViewById(R.id.reject);
        PauseButton = (ImageButton) findViewById(R.id.pauseapproval);

        ProfileImageofPerson = (ImageView) findViewById(R.id.candidateprofileimage);
        EmergencyPersonName = (TextView) findViewById(R.id.emergencypersonname);

        NameofPerson = (TextView) findViewById(R.id.candidatename);
        candidateuserid = (TextView) findViewById(R.id.candidateuserid);
        EmergencyPersonName = (TextView) findViewById(R.id.emergencypersonname);
        EmergencyPersonPhone = (TextView) findViewById(R.id.emergencypersonphone);
        EmergencyPersonEmail = (TextView) findViewById(R.id.emergencypersonemail);
        EmergencyPersonCountry = (TextView) findViewById(R.id.emergencypersoncountry);
        EmergencyPersonID = (TextView) findViewById(R.id.emergencypersonID);
        EmergencyPersonType = (TextView) findViewById(R.id.emergencypersonidtype);




        candidateapprovebackbutton =  (ImageButton) findViewById(R.id.candidateapproveback);
        candidateapprovenextbutton = (ImageButton) findViewById(R.id.candidateapprovenext);





        Paper.init(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Background Information Activity");
        }
//        setSupportActionBar(toolbar);


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


                if (mAuth != null) {
                    user = mAuth.getCurrentUser();
                    if (user != null) {
                        userID = user.getUid();

                    }

                    myfirebaseDatabase = FirebaseDatabase.getInstance();

                    UsersRef = myfirebaseDatabase.getReference().child("Users");

                    userkey = UsersRef.getKey();
                    // GET FROM FOLLOWING KEY
                    fetch();
                    recyclerView.setAdapter(feedadapter);
                    //        setSupportActionBar(toolbar);

                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
                    if (mGoogleApiClient != null) {

                        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
                    }

                    if (mGoogleApiClient != null) {
                        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(BackgroundInfoApproveForTrader.this,
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
    }    //GETFOLLOWING WILL PULL FROM DIFFERENT DATASTORE( THE USER DATASTORE)


    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;

        public ImageButton ApprovalButtton;
        public ImageButton RejectButton;
        public ImageButton PauseButton;


        public ImageButton candidateapprovebackbutton;
        public ImageButton candidateapprovenextbutton;







        public TextView NameofPerson;
        public  TextView candidateuserid;
        public  TextView EmergencyPersonName;
        public TextView  EmergencyPersonPhone;
        public TextView EmergencyPersonEmail;
        public  TextView  EmergencyPersonCountry;
        public  TextView  EmergencyPersonID;
        public  TextView EmergencyPersonType;
        public android.widget.ImageView ProfileImageofPerson;


        public ItemClickListner listner;

        public ViewHolder(View itemView) {
            super(itemView);


            ApprovalButtton = itemView.findViewById(R.id.approve);
            RejectButton = itemView.findViewById(R.id.reject);
            PauseButton = itemView.findViewById(R.id.pauseapproval);

            ProfileImageofPerson = itemView.findViewById(R.id.candidateprofileimage);

            NameofPerson = itemView. findViewById(R.id.candidatename);
            candidateuserid = itemView. findViewById(R.id.candidateuserid);
            EmergencyPersonName = itemView. findViewById(R.id.emergencypersonname);
            EmergencyPersonPhone = itemView. findViewById(R.id.emergencypersonphone);
            EmergencyPersonEmail = itemView. findViewById(R.id.emergencypersonemail);
            EmergencyPersonCountry = itemView.findViewById(R.id.emergencypersoncountry);
            EmergencyPersonID = itemView. findViewById(R.id.emergencypersonID);
            EmergencyPersonType =itemView. findViewById(R.id.emergencypersonidtype);
        }

        public void setItemClickListner(ItemClickListner listner) {
            this.listner = listner;
        }

        public void setnameofcandidateid(String ourfcandidateid) {

            candidateuserid.setText(ourfcandidateid);
        }

        public void setnameofcandidate(String nameofcandidate) {

            NameofPerson.setText(nameofcandidate);
        }

        public void setemergencypersonname(String emergencyperson) {

            EmergencyPersonName.setText(emergencyperson);
        }

        public void setemergencypersonphone(String emergencypersonphone) {

            EmergencyPersonPhone.setText(emergencypersonphone);
        }

        public void set(String emergencypersonemail) {

            EmergencyPersonEmail.setText(emergencypersonemail);
        }
        public void setemergencypersonID(String emergencypersonID) {

            EmergencyPersonID.setText(emergencypersonID);
        }

        public void setemergencypersoncountry(String emergencypersoncountry) {

            EmergencyPersonCountry.setText(emergencypersoncountry);
        }
        public void setemergencypersontype(String emergencypersontype) {

            EmergencyPersonType.setText(emergencypersontype);
        }

        public void setcandidateprofileimage(final Context ctx, final String image) {
            final android.widget.ImageView candidateprofileimage = (android.widget.ImageView) itemView.findViewById(R.id.candidateprofileimage);

            Picasso.get().load(image).resize(400, 0).networkPolicy(NetworkPolicy.OFFLINE).into(candidateprofileimage, new Callback() {


                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).resize(100, 0).into(candidateprofileimage);
                }


            });
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
                traderID = user.getUid();

            }
            @Nullable

            Query queryhere =

                    FirebaseDatabase.getInstance().getReference().child("Approval").orderByChild("tid").equalTo(userID);
            if (queryhere != null) {

                FirebaseRecyclerOptions<BackgroundInfoSubmitModel> options =
                        new FirebaseRecyclerOptions.Builder<BackgroundInfoSubmitModel>()
                                .setQuery(queryhere, new SnapshotParser<BackgroundInfoSubmitModel>() {


                                    @Nullable
                                    @Override
                                    public BackgroundInfoSubmitModel parseSnapshot(@Nullable DataSnapshot snapshot) {


                                      /*
                                      String commentkey = snapshot.child("Comments").getKey();
                                      String likekey = snapshot.child("Likes").getKey();*/
                                        Log.i(TAG, "BackgroundInfoSubmitModel " + snapshot);

                                        if (snapshot.child("uid").getValue() != null) {
                                            uid = snapshot.child("uid").getValue(String.class);
                                        }
                                        if (snapshot.child("empersonname").getValue() != null) {
                                            empersonname = snapshot.child("empersonname").getValue(String.class);
                                        }
                                        if (snapshot.child("name").getValue() != null) {
                                            name = snapshot.child("name").getValue(String.class);
                                        }
                                        if (snapshot.child("emcountry").getValue() != null) {
                                            emcountry = snapshot.child("emcountry").getValue(String.class);
                                        }

                                        if (snapshot.child("ememail").getValue() != null) {
                                            ememail = snapshot.child("ememail").getValue(String.class);
                                        }


                                        if (snapshot.child("empersionid").getValue() != null) {
                                            empersionid = snapshot.child("empersionid").getValue(String.class);
                                        }
                                        if (snapshot.child("emphone").getValue() != null) {
                                            emphone = snapshot.child("emphone").getValue(String.class);
                                        }

                                        if (snapshot.child("empidtype").getValue() != null) {
                                            empidtype = snapshot.child("empidtype").getValue(String.class);
                                        }

                                        if (snapshot.child("backgroundinfostatus").getValue() != null) {
                                            backgroundinfostatus = snapshot.child("backgroundinfostatus").getValue(String.class);
                                        }



                                        return new BackgroundInfoSubmitModel( uid, empersonname,emcountry, ememail, empersionid, emphone,  empidtype, backgroundinfostatus);


                                    }

                                }).build();




                feedadapter = new FirebaseRecyclerAdapter<BackgroundInfoSubmitModel, ViewHolder>(options) {
                    @Nullable
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        @Nullable
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.backgroundinfoapprove, parent, false);

                        return new ViewHolder(view);
                    }


                    @Override
                    public int getItemCount() {
                        return super.getItemCount();
                    }

                    @Override
                    protected void onBindViewHolder(@Nullable final ViewHolder holder, int position, @Nullable BackgroundInfoSubmitModel model) {
                        if (model != null) {



                            holder.NameofPerson.setText( name);
                            holder.candidateuserid.setText(uid);


                            holder.EmergencyPersonName.setText(empersonname);
                            holder.EmergencyPersonPhone.setText(emphone);
                            holder.EmergencyPersonEmail.setText(ememail);
                            holder.EmergencyPersonType.setText(empidtype);
                            holder.EmergencyPersonID.setText(empersionid);
                            holder.EmergencyPersonCountry.setText(emcountry);

                            Log.d(TAG, "Background Approval Info" + name);
                            holder.setcandidateprofileimage(getApplicationContext(), image);


                            if (ProfileImageofPerson != null) {
                                Picasso.get().load(image).placeholder(R.drawable.profile).into(ProfileImageofPerson);
                            }






                            holder.ApprovalButtton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    backgroundinfostatus = "approve";
                                    setDecision(
                                            backgroundinfostatus);
                                    Intent approvalintent = new Intent(BackgroundInfoApproveForTrader.this, BackgroundInfoApproveForCustomer.class);
                                    approvalintent.putExtra("role", role);
                                    approvalintent.putExtra("uid", uid);
                                    approvalintent.putExtra("approverID", approverID);
                                    approvalintent.putExtra("approvalID", approvalID);
                                    approvalintent.putExtra("userID", userID);
                                    Toast.makeText(BackgroundInfoApproveForTrader.this, "Background has been approved", Toast.LENGTH_SHORT).show();
                                    startActivity(approvalintent);
                                }
                            });

                            if (holder.RejectButton != null) {
                                holder.RejectButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        backgroundinfostatus = "reject";
                                        setDecision(
                                                backgroundinfostatus);
                                        Intent rejectintent = new Intent(BackgroundInfoApproveForTrader.this, BackgroundInfoApproveForCustomer.class);
                                        rejectintent.putExtra("role", role);
                                        rejectintent.putExtra("uid", uid);
                                        rejectintent.putExtra("approverID", approverID);
                                        rejectintent.putExtra("approvalID", approvalID);
                                        rejectintent.putExtra("userID", userID);

                                        Toast.makeText(BackgroundInfoApproveForTrader.this, "BackgroundInfo has been rejected", Toast.LENGTH_SHORT).show();
                                        startActivity(rejectintent);


                                    }
                                });
                            }

                            // Product Details
                            if (holder.PauseButton != null) {
                                holder.PauseButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        backgroundinfostatus = "pause";
                                        setDecision(
                                                backgroundinfostatus);
                                        Intent pausebuttonintent = new Intent(BackgroundInfoApproveForTrader.this, BackgroundInfoApproveForCustomer.class);
                                        pausebuttonintent.putExtra("role", role);
                                        pausebuttonintent.putExtra("uid", uid);
                                        pausebuttonintent.putExtra("approverID", approverID);
                                        pausebuttonintent.putExtra("approvalID", approvalID);
                                        pausebuttonintent.putExtra("userID", userID);


                                        Toast.makeText(BackgroundInfoApproveForTrader.this, "BackgroundInfo has been paused", Toast.LENGTH_SHORT).show();
                                        startActivity(pausebuttonintent);

                                    }
                                });
                            }

                            // Product Details
                            if (holder.candidateapprovebackbutton != null) {
                                holder.candidateapprovebackbutton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Intent candidatebackbutton  = new Intent(BackgroundInfoApproveForTrader.this, ResidentialInfoApproveForClient.class);
                                        candidatebackbutton.putExtra("role", role);
                                        candidatebackbutton.putExtra("uid", uid);
                                        candidatebackbutton.putExtra("approverID", approverID);
                                        candidatebackbutton.putExtra("approvalID", approvalID);
                                        candidatebackbutton.putExtra("userID", userID);

                                        Toast.makeText(BackgroundInfoApproveForTrader.this, "Back to Residential Approval List", Toast.LENGTH_SHORT).show();
                                        startActivity(candidatebackbutton);

                                    }
                                });
                            }


                            // Product Details
                            if (holder.candidateapprovenextbutton != null) {
                                holder.candidateapprovenextbutton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Intent caandidateapprovenextbutton = new Intent(BackgroundInfoApproveForTrader.this, SecurityCheckApproveForCustomer.class);
                                        caandidateapprovenextbutton.putExtra("role", role);
                                        caandidateapprovenextbutton.putExtra("uid", uid);
                                        caandidateapprovenextbutton.putExtra("approverID", approverID);
                                        caandidateapprovenextbutton.putExtra("approvalID", approvalID);
                                        caandidateapprovenextbutton.putExtra("userID", userID);

                                        Toast.makeText(BackgroundInfoApproveForTrader.this, "To SecurityCheckApproveForCustomer Page", Toast.LENGTH_SHORT).show();

                                        startActivity(caandidateapprovenextbutton);

                                    }
                                });
                            }




                        }
                    }
                };
            }
//            if (recyclerView != null) {
            //              recyclerView.setAdapter(feedadapter);
            //        }
        }
    }


    // Post Info
    private void setDecision(String
                                     backgroundinfostatus) {

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

            if (
                    backgroundinfostatus != null) {
                mProgress.setMessage("Making +" +
                        backgroundinfostatus + "for this current user");

                mProgress.show();




                // PICK UP THE SPECIAL PRODUCT INFO AND LOADING THEM INTO THE DATABASE

                Users newuserapprovalinfo = new Users(uid,name, address, street, gpscode, country,
                        backgroundinfostatus,backgroundinfoactivity);
                UsersRef.child(userID).setValue(newuserapprovalinfo, new
                        DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference
                                    databaseReference) {
                                Toast.makeText(getApplicationContext(), "Background Decision Taken as "  +
                                        backgroundinfostatus, Toast.LENGTH_SHORT).show();
                                Intent personapprovalloginfointent = new Intent(BackgroundInfoApproveForTrader.this, HomeActivity.class);

                                startActivity(personapprovalloginfointent);

                            }
                        });


            }

        };


        mProgress.dismiss();

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
            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderoruser);
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

                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AdminAllCustomers.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderoruser);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }
            }
        }



        if (id == R.id. allcustomersincart) {

            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderoruser);
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

                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, ViewAllCarts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderoruser);
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
                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderoruser);
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

                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AdminAddNewProductActivityII.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderoruser);
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
                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderoruser);
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

                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AdminAllProducts.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderoruser);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }}

                if (id == R.id.allproductspurchased) {
                    if (!type.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
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

                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AllProductsPurchased.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
                                    intent.putExtra("role", type);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }


                if (id == R.id. viewallcustomershere) {
                    if (!type.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
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

                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, ViewAllCustomers.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
                                    intent.putExtra("role", type);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }

                if (id == R.id.tradersfollowing) {
                    if (!type.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
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

                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, TradersFollowing.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
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
                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
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

                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AdminNewOrdersActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
                                    intent.putExtra("role", type);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }


                if (id == R.id.allcustomersserved) {

                    if (!type.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
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

                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AdminCustomerServed.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
                                    intent.putExtra("role", type);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }

                if (id == R.id.allordershistory) {

                    if (!type.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
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

                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AdminAllOrderHistory.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
                                    intent.putExtra("role", type);
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
            if (!type.equals("Trader")) {

                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                if (intent != null) {
                    intent.putExtra("traderorcustomer", traderoruser);
                    intent.putExtra("role", type);
                    startActivity(intent);
                    finish();
                }
            } else {

                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, DriverMapActivity.class);
                if (intent != null) {
                    intent.putExtra("traderorcustomer", traderoruser);
                    intent.putExtra("role", type);
                    startActivity(intent);
                    finish();
                }
            }


        }


        if (id == R.id.nav_cart) {
            if (!type.equals("Trader")) {
                if (FirebaseAuth.getInstance() != null) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String cusomerId = "";

                        cusomerId = user.getUid();
                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderoruser);
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

                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, CartActivity.class);
                        if (intent != null) {
                            intent.putExtra("traderorcustomer", traderoruser);
                            intent.putExtra("role", type);
                            startActivity(intent);
                        }
                    }
                }

            }


            if (id == R.id.nav_social_media) {
                if (!type.equals("Trader")) {
                    if (FirebaseAuth.getInstance() != null) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            String cusomerId = "";

                            cusomerId = user.getUid();
                            Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                            if (intent != null) {
                                intent.putExtra("traderorcustomer", traderoruser);
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

                            Intent intent = new Intent(BackgroundInfoApproveForTrader.this, InstagramHomeActivity.class);
                            if (intent != null) {
                                intent.putExtra("traderorcustomer", traderoruser);
                                intent.putExtra("role", type);
                                startActivity(intent);
                            }
                        }
                    }

                }


                if (id == R.id.viewproducts) {
                    if (!type.equals("Trader")) {
                        if (FirebaseAuth.getInstance() != null) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String cusomerId = "";

                                cusomerId = user.getUid();
                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
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

                                Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AdminAllProducts.class);
                                if (intent != null) {
                                    intent.putExtra("traderorcustomer", traderoruser);
                                    intent.putExtra("role", type);
                                    startActivity(intent);
                                }
                            }
                        }

                        if (id == R.id.nav_searchforproducts) {
                            if (!type.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
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

                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, SearchForAdminProductsActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
                                            intent.putExtra("role", type);
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
                                    mGoogleSignInClient.signOut().addOnCompleteListener(BackgroundInfoApproveForTrader.this,
                                            new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                }
                                            });
                                }
                            }
                            Intent intent = new Intent(BackgroundInfoApproveForTrader.this, com.simcoder.bimbo.MainActivity.class);
                            if (intent != null) {
                                startActivity(intent);
                                finish();
                            }
                        }

                        if (id == R.id.nav_settings) {
                            if (!type.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
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

                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, com.simcoder.bimbo.WorkActivities.SettinsActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
                                            intent.putExtra("role", type);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }
                        if (id == R.id.nav_history) {
                            if (!type.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
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

                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, HistoryActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
                                            intent.putExtra("role", type);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }


                        if (id == R.id.nav_viewprofilehome) {
                            if (!type.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
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

                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, TraderProfile.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
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
                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
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

                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AdminAllCustomers.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
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
                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
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

                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AdminAddNewProductActivityII.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
                                            intent.putExtra("role", type);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }


                        if (id == R.id.goodsbought) {
                            if (!type.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
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

                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AllGoodsBought.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
                                            intent.putExtra("role", type);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }


                        if (id == R.id.nav_paymenthome) {
                            if (!type.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
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

                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AdminPaymentHere.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
                                            intent.putExtra("role", type);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }


                        if (id == R.id.nav_settings) {
                            if (!type.equals("Trader")) {
                                if (FirebaseAuth.getInstance() != null) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String cusomerId = "";

                                        cusomerId = user.getUid();
                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, NotTraderActivity.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
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

                                        Intent intent = new Intent(BackgroundInfoApproveForTrader.this, AdminSettings.class);
                                        if (intent != null) {
                                            intent.putExtra("traderorcustomer", traderoruser);
                                            intent.putExtra("role", type);
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



// #BuiltByGOD



