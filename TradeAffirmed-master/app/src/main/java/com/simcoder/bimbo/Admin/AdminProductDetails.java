package com.simcoder.bimbo.Admin;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
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
import com.simcoder.bimbo.DriverMapActivity;
import com.simcoder.bimbo.HistoryActivity;
import com.simcoder.bimbo.Interface.ItemClickListner;
import com.simcoder.bimbo.Model.AdminOrders;
import com.simcoder.bimbo.Model.HashMaps;
import com.simcoder.bimbo.Model.Products;
import com.simcoder.bimbo.Model.Users;
import com.simcoder.bimbo.R;
import com.simcoder.bimbo.WorkActivities.CartActivity;
import com.simcoder.bimbo.WorkActivities.CustomerProfile;
import com.simcoder.bimbo.WorkActivities.TraderProfile;
import com.simcoder.bimbo.instagram.Home.InstagramHomeActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class AdminProductDetails extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener ,NavigationView.OnNavigationItemSelectedListener {

 //FIREBASE COLLECTOR FOR SUGGESTIONS MUST BE BUILT

    private Button ViewBuyers;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productPrice, productDescription, productName;
    private String productID = "", state = "Normal";
    String role;
    String traderID;
    Query myproductsdetails;
    DatabaseReference mDatabaseLikeCount;
    DatabaseReference ProductsRefDatabase;
String    productdetailsimage;
String
        pname, pimage,desc,number;
            String productdetailsname;
    String productdetailsdescription;
            String productdetailsnumber;
    String LikeRefkey;
 String photokey;
 String price;
 String tradername;


    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

   FirebaseDatabase myfirebasedatabase;
   FirebaseDatabase likesgetdatabase;
    //AUTHENTICATORS
    private RecyclerView recyclerView;


    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Google Activity";
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    DatabaseReference productsRef;
    DatabaseReference photodatabase;
    DatabaseReference LikeRef;
    Boolean mProcessLike;
    String userID;
    boolean increment;
    FirebaseUser user;
    String productkey;
    String productIDhere;



    Button ViewCustomers;
    ImageView adminproductimagelikebutton;
    ImageView adminproductdetailsimage;
    TextView adminproductimageproductname;
    TextView adminproductimagedescription;
    TextView adminproductimagenumberoflikes;
    TextView adminproductimageprice;

    String productinfokey;
     String productidkey;
     String name;
     String productimage;
     String productsdescription;
     String productsprice;
     String productslikes;
       TextView admintradername;
    TextView admincategoryid;
    ImageView admintraderimage;
    TextView adminnumberofreviews;
    String  thetradernamestring;
    String         thecategoryid;
    String theadmintraderimage;
     String theadminnumberofreviews;
      TextView thetradername;

    DatabaseReference ProductsRef;
    private DatabaseReference Userdetails;
    private DatabaseReference ProductsRefwithproduct;
    private  DatabaseReference UsersRef;

    RecyclerView.LayoutManager layoutManager;

    DatabaseReference AllOrderDatabaseRef;
    DatabaseReference FollowerDatabaseReference;

    String traderkeyhere;

    String ProductID;
    FirebaseDatabase myfirebaseDatabase;
    FirebaseDatabase FollowerDatabase;

    public AdminProductViewViewHolder holders;

    public FirebaseRecyclerAdapter feedadapter;

    //AUTHENTICATORS

    TextView allcustomersname;
    TextView allcustomersphonenumber;
    TextView allcustomersjob;
    com.rey.material.widget.ImageView allcustomersimage;
    String traderkey;
    String key;
    String tradename;
    String traderimage;
   String categoryname, date,discount, time, pid,  image,  size, tid;
    String thetraderimage;
    String address;
    String amount;
    String city;
    String delivered;
    String distance;
    String uid;
    String mode;

    String phone;
    String quantity;
    String shippingcost;
    String thecustomersjob;
    String orderkey;
    String newornot;
    AdminAllOrderHistory.Getmyfollowings getmyfollowingsagain;
    String userkey;
    TextView  orderid;
    TextView customername;
    TextView orderedtime;
    TextView ordereddate;
    ImageView suggestionpagegrilledview;
     TextView suggestionpagekebbabtext;
    TextView suggestionpagekebbabcost;
    ImageView suggestionpageheartpic;
    TextView suggestionkebbabvaluecost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminproductdetailslayout);

         // THE MAIN APP MAIN COMPONENTS
        ViewBuyers = (Button) findViewById(R.id.ViewBuyers);
        adminproductimagelikebutton = (ImageView) findViewById(R.id.adminproductimagelikebutton);
        adminproductdetailsimage = (ImageView) findViewById(R.id.adminproductdetailsimage);
        adminproductimageproductname = (TextView) findViewById(R.id.adminproductimageproductname);

        adminproductimagenumberoflikes = (TextView) findViewById(R.id.adminproductimagenumberoflikes);
        adminproductimageprice = (TextView)findViewById(R.id.adminproductimageprice);
        admintradername =  (TextView)findViewById(R.id.admintradername);
        admincategoryid = (TextView)findViewById(R.id.admincategoryid);
        admintraderimage = (ImageView)findViewById(R.id.admintraderimage);
        adminnumberofreviews = (TextView)findViewById(R.id.adminnumberofreviews);

        // THE RECYCLER VIEW COMPONENTS
        suggestionpagegrilledview = (ImageView) findViewById(R.id.suggestionpagegrilledview);
        suggestionpagekebbabtext =  (TextView)findViewById(R.id.suggestionpagekebbabtext);
        suggestionpagekebbabcost = (TextView)findViewById(R.id.suggestionpagekebbabcost);
        suggestionpageheartpic = (ImageView)findViewById(R.id.suggestionpageheartpic);
        suggestionkebbabvaluecost = (TextView)findViewById(R.id.suggestionkebbabvaluecost);

        //   productID = getIntent().getStringExtra("pid");

        Intent priceintent = getIntent();
        if( priceintent.getExtras().getString("price") != null) {
            price  = priceintent.getExtras().getString("price");
        }

        Intent pnameintent = getIntent();
        if( pnameintent.getExtras().getString("pname") != null) {
            pname = pnameintent.getExtras().getString("pname");
        }

        Intent pimageintent = getIntent();
        if( pimageintent.getExtras().getString("pimage") != null) {
            pimage = pimageintent.getExtras().getString("pimage");
        }

        Intent descintent = getIntent();
        if( descintent.getExtras().getString("desc") != null) {
            desc = descintent.getExtras().getString("desc");
        }

        Intent productlikesintent = getIntent();
        if( productlikesintent.getExtras().getString("likenumber") != null) {
            number = productlikesintent.getExtras().getString("likenumber");
        }

        Intent productIDidentifyintent = getIntent();
        if( productIDidentifyintent.getExtras().getString("pid") != null) {
            productID = productIDidentifyintent.getExtras().getString("pid");
        }

        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent traderIDintent = getIntent();
        if (traderIDintent.getExtras().getString("traderID") != null) {
            traderID = traderIDintent.getExtras().getString("traderID");
        }

        Intent userintent = getIntent();
        if( userintent.getExtras().getString("userID") != null) {
            userID = userintent.getExtras().getString("userID");
        }


        recyclerView = findViewById(R.id.productdetailsinnerrecyclerview);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
        }





        Paper.init(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.hometoolbar);
        if (toolbar != null) {
            toolbar.setTitle("Admin Product Details");
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

                mAuth = FirebaseAuth.getInstance();
                myfirebasedatabase = FirebaseDatabase.getInstance();

                user = mAuth.getCurrentUser();
                if (user != null) {
                    traderID = "";
                    traderID = user.getUid();
                    tradername = "";
                    tradername = user.getDisplayName();

                    // GET FROM FOLLOWING KEY


                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
                    if (mGoogleApiClient != null) {
                        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
                    }

                    if (mGoogleApiClient != null) {
                        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(com.simcoder.bimbo.Admin.AdminProductDetails.this,
                                new GoogleApiClient.OnConnectionFailedListener() {
                                    @Override
                                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                                    }
                                }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
                    }
                    buildGoogleApiClient();


                    ProductsRefDatabase = myfirebasedatabase.getInstance().getReference().child("Product");
                       Query queryhere = ProductsRefDatabase.orderByChild("productID").equalTo(productID);
                    queryhere.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                                if (dataSnapshot != null) {
                                    dataSnapshot.getValue(Users.class);

                                    if (productID != null) {


                                        // I don't know which one to use , what is mostly seen is the datasnapshot.child(productID).getkey() area
                                        productinfokey = dataSnapshot.getKey();
                                        productidkey = dataSnapshot.getKey();
                                        name = dataSnapshot.child("name").getValue(String.class);

                                        if (adminproductimageproductname != null) {
                                            adminproductimageproductname.setText(name);
                                        }

                                        productimage = dataSnapshot.child("image").getValue(String.class);

                                        if (adminproductdetailsimage != null) {
                                            if (productimage != null) {
                                                adminproductdetailsimage.setImageResource(Integer.parseInt(productimage));
                                            }
                                        }

                                        // DESC WILL HAVE TO GO TO NEXT PAGE
                         /*       productsdescription = dataSnapshot.child(productID).child("desc").getValue(String.class);
                                if (adminproductimagedescription != null) {
                                    adminproductimagedescription.setText(productsdescription);
                                }
*/
                                        productsprice = dataSnapshot.child("price").getValue(String.class);
                                        if (adminproductimageprice != null) {
                                            adminproductimageprice.setText(productsprice);
                                        }

                                        productslikes = dataSnapshot.child("likenumber").getValue(String.class);

                                        if (adminproductimagenumberoflikes != null) {
                                            adminproductimagenumberoflikes.setText(productslikes);
                                        }

                                        thetradernamestring = dataSnapshot.child("tradername").getValue(String.class);
                                        if (admintradername != null) {
                                            admintradername.setText(thetradernamestring);
                                        }
                                        thecategoryid = dataSnapshot.child("categoryname").getValue(String.class);
                                        if (admintradername != null) {
                                            admintradername.setText(thecategoryid);
                                        }

                                        theadmintraderimage = dataSnapshot.child("traderimage").getValue(String.class);
                                        if (admintradername != null) {
                                            admintradername.setText(theadmintraderimage);
                                        }

                                        theadminnumberofreviews = dataSnapshot.child("reviewnumber").getValue(String.class);
                                        if (admintradername != null) {
                                            admintradername.setText(theadminnumberofreviews);
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

                if (pimage != null) {
                    Picasso.get().load(pimage).resize(400, 0).networkPolicy(NetworkPolicy.OFFLINE).into(adminproductdetailsimage, new Callback() {


                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get().load(pimage).resize(100, 0).into(adminproductdetailsimage);
                        }


                    });

                }


                if (ViewBuyers != null) {
                    ViewBuyers.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Toast.makeText(getApplicationContext(), "Cannot Buy As An Administrator", Toast.LENGTH_SHORT);
                            Intent myintent = new Intent(AdminProductDetails.this, AdminProductDetails.class);

                            myintent.putExtra("productID", productID);

                            myintent.putExtra("traderID", traderID);
                            myintent.putExtra("role", role);

                            startActivity(myintent);
                        }
                    });
                }}}}

            public interface Getmyfollowings {

                void onCallback(String followingid, String followingname, String followingimage);


            }


            //GETFOLLOWING WILL PULL FROM DIFFERENT DATASTORE( THE USER DATASTORE)


            public class AdminProductViewViewHolder extends RecyclerView.ViewHolder {
                public LinearLayout root;


                public ImageView suggestionpagegrilledview;
                public TextView suggestionpagekebbabtext;

                public TextView  suggestionpagekebbabcost;
                public ImageView  suggestionpageheartpic;
                public TextView suggestionkebbabvaluecost;


                public ItemClickListner listner;

                public AdminProductViewViewHolder(View itemView) {
                    super(itemView);


                    suggestionpagegrilledview = itemView.findViewById(R.id.suggestionpagegrilledview);
                    suggestionpagekebbabtext = itemView.findViewById(R.id.suggestionpagekebbabtext);
                    suggestionpagekebbabcost = itemView.findViewById(R.id.suggestionpagekebbabcost);
                    suggestionpageheartpic = itemView.findViewById(R.id.suggestionpageheartpic);
                    suggestionkebbabvaluecost = itemView.findViewById(R.id.suggestionkebbabvaluecost);


                }

                public void setItemClickListner(ItemClickListner listner) {
                    this.listner = listner;
                }

                public void setsuggestionpagekebbabtext(String suggestionpagekebbabtextstring) {

                    suggestionpagekebbabtext.setText(suggestionpagekebbabtextstring);
                }

                public void setsuggestionpagekebbabcost(String suggestionkebbabvaluecosttext) {

                    suggestionkebbabvaluecost.setText(suggestionkebbabvaluecosttext);
                }

                public void setsuggestionkebbabvaluecost(String suggestionkebbabvaluecosttext) {

                    suggestionkebbabvaluecost.setText(suggestionkebbabvaluecosttext);
                }


                public void setsuggestionpagegrilledview(final Context ctx, final String image) {
                    final android.widget.ImageView suggestionpagegrilledview = (android.widget.ImageView) itemView.findViewById(R.id.suggestionpagegrilledview);

                    Picasso.get().load(image).resize(400, 0).networkPolicy(NetworkPolicy.OFFLINE).into(suggestionpagegrilledview, new Callback() {


                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get().load(image).resize(100, 0).into(suggestionpagegrilledview);
                        }


                    });
                }

            }

            public void useValue (String yourValue){

                Log.d(TAG, "countryNameCode: " + yourValue);

            }
            private void fetch () {
                if (mAuth != null) {
                    user = mAuth.getCurrentUser();
                    if (user != null) {
                        traderID = user.getUid();

                    }

                     // ORDER MANY MORE PRODUCTS HERE
                    @Nullable

                    Query queryhere =

                            FirebaseDatabase.getInstance().getReference().child("Product").orderByChild("tid").equalTo(traderID);
                    if (queryhere != null) {

                        FirebaseRecyclerOptions<Products> options =
                                new FirebaseRecyclerOptions.Builder<Products>()
                                        .setQuery(queryhere, new SnapshotParser<Products>() {


                                            @Nullable
                                            @Override
                                            public Products parseSnapshot(@Nullable DataSnapshot snapshot) {


                                      /*
                                      String commentkey = snapshot.child("Comments").getKey();
                                      String likekey = snapshot.child("Likes").getKey();
*/


                                                Log.i(TAG, "Admin Product Details  " + snapshot);


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


                                                if (snapshot.child("likenumber").getValue(String.class) != null) {
                                                    number = snapshot.child("likenumber").getValue(String.class);
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
                                                if (snapshot.child("newornot").getValue(String.class) != null) {
                                                    newornot = snapshot.child("newornot").getValue(String.class);
                                                }
                                                return new Products(orderkey, date, time, tid, thetraderimage, tradername, address, amount, city, delivered, distance, image, uid, name, mode,

                                                        number, phone, quantity, shippingcost, state, newornot);
                                            }

                                        }).build();


                        feedadapter = new FirebaseRecyclerAdapter<Products, AdminProductDetails.AdminProductViewViewHolder >(options) {
                            @Nullable
                            @Override
                            public AdminProductDetails.AdminProductViewViewHolder onCreateViewHolder(ViewGroup parent, int viewrole) {

                                @Nullable
                                View view = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.suggestionpage, parent, false);

                                return new AdminProductDetails.AdminProductViewViewHolder(view);
                            }


                            @Override
                            public int getItemCount() {
                                return super.getItemCount();
                            }

                            @Override
                            protected void onBindViewHolder(@Nullable final AdminProductDetails.AdminProductViewViewHolder holder, int position, @Nullable Products model) {
                                if (model != null) {
                                    holders = holder;


                                          // kebabcost is the current cost while value cost is the discount
                                    holder.suggestionpagekebbabtext.setText(pname);
                                    holder.suggestionpagekebbabcost.setText(price);
                                    holder.suggestionkebbabvaluecost.setText(discount);


                                    Log.d(TAG, "Suggested Pages Are Here " + name + phone);
                                    holder.setsuggestionpagegrilledview(getApplicationContext(), image);


                                    myfirebaseDatabase = FirebaseDatabase.getInstance();


             /*
                            Query firebasequery =  myfirebaseDatabase.getReference().child("Users").child("Customers").orderByChild("uid").equalTo(uid);

                            firebasequery.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {



                                        userkey = dataSnapshot1.getKey();
                                        Log.d(TAG, "The Photokey " + userkey);

                                        Log.d(TAG, "UserID here  " + uid );
                                        if (dataSnapshot1.child("job").getValue() != null) {
                                            thecustomersjob = dataSnapshot1.child("job").getValue(String.class);
                                            holders.allcustomersjob.setText(thecustomersjob);
                                            Log.d(TAG, "The CustomerJob " + thecustomersjob);



                                            useValue (thecustomersjob);
                                            Log.d(TAG, "The UseValueJob " + thecustomersjob);
                                        }
                                    }
                                }


                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
 */


                                    if (allcustomersimage != null) {
                                        Picasso.get().load(image).placeholder(R.drawable.profile).into(allcustomersimage);
                                    }

                                    suggestionpagekebbabtext.setText(pname);
                                    holder.suggestionpagekebbabcost.setText(price);
                                    holder.suggestionkebbabvaluecost.setText(discount);

                                    if (holder.suggestionpagekebbabtext != null) {
                                        holder.suggestionpagekebbabtext.setOnClickListener(new View.OnClickListener() {
                                            // Image Cost Here
                                            @Override
                                            public void onClick(View view) {
                                                Intent viewactualproductinformation = new Intent(AdminProductDetails.this, AdminProductDetails.class);

                                                viewactualproductinformation.putExtra("traderorcustomer", traderID);
                                                viewactualproductinformation.putExtra("role", role);
                                                viewactualproductinformation.putExtra("productID", productID);

                                                startActivity(viewactualproductinformation);

                                            }
                                        });
                                    }

                                    if (holder.suggestionpagekebbabcost != null) {
                                        holder.suggestionpagekebbabcost.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent viewactualproductinformation = new Intent(AdminProductDetails.this, AdminProductDetails.class);
                                                viewactualproductinformation.putExtra("traderorcustomer", traderID);
                                                viewactualproductinformation.putExtra("role", role);
                                                viewactualproductinformation.putExtra("productID", productID);

                                                startActivity(viewactualproductinformation);

                                            }
                                        });
                                    }

                                    if (holder.suggestionkebbabvaluecost != null) {
                                        holder.suggestionkebbabvaluecost.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent viewactualproductinformation = new Intent(AdminProductDetails.this, AdminProductDetails.class);
                                                viewactualproductinformation.putExtra("traderorcustomer", traderID);
                                                viewactualproductinformation.putExtra("role", role);
                                                viewactualproductinformation.putExtra("productID", productID);
                                                startActivity(viewactualproductinformation);

                                            }
                                        });
                                    }


                                }

                                if (holder.suggestionpagegrilledview != null) {
                                    holder.suggestionpagegrilledview.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent viewactualproductinformation = new Intent(AdminProductDetails.this, AdminProductDetails.class);
                                            viewactualproductinformation.putExtra("traderorcustomer", traderID);
                                            viewactualproductinformation.putExtra("role", role);
                                            viewactualproductinformation.putExtra("productID", productID);


                                            startActivity(viewactualproductinformation);

                                        }
                                    });
                                }

                            }
                                               };
                    }
                    if (recyclerView != null) {
                        recyclerView.setAdapter(feedadapter);
                    }

                }}

    protected void onStart() {
        super.onStart();

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
        };
    }


    protected synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(AdminProductDetails.this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }
    }


    @Override
    public void onClick(View v) {

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

    protected void onStop() {
        super.onStop();
        //     mProgress.hide();
        if (mAuth != null) {
            mAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                        Intent intent = new Intent(AdminProductDetails.this, AdminAllCustomers.class);
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
                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                        Intent intent = new Intent(AdminProductDetails.this, ViewAllCarts.class);
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
                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                        Intent intent = new Intent(AdminProductDetails.this, AdminAddNewProductActivityII.class);
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
                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                        Intent intent = new Intent(AdminProductDetails.this, AdminAllProducts.class);
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
                                Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(AdminProductDetails.this, AllProductsPurchased.class);
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
                                Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(AdminProductDetails.this, ViewAllCustomers.class);
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
                                Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(AdminProductDetails.this, TradersFollowing.class);
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
                                Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(AdminProductDetails.this, AdminNewOrdersActivity.class);
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
                                Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(AdminProductDetails.this, AdminCustomerServed.class);
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
                                Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(AdminProductDetails.this, AdminAllOrderHistory.class);
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

                Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
                if (intent != null) {
                    intent.putExtra("traderorcustomer", traderID);
                    intent.putExtra("role", role);
                    startActivity(intent);
                    finish();
                }
            } else {

                Intent intent = new Intent(AdminProductDetails.this, DriverMapActivity.class);
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
                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                        Intent intent = new Intent(AdminProductDetails.this, CartActivity.class);
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
                            Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                            Intent intent = new Intent(AdminProductDetails.this, InstagramHomeActivity.class);
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
                                Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(AdminProductDetails.this, AdminAllProducts.class);
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
                                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(AdminProductDetails.this, SearchForAdminProductsActivity.class);
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
                                    mGoogleSignInClient.signOut().addOnCompleteListener(AdminProductDetails.this,
                                            new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                }
                                            });
                                }
                            }
                            Intent intent = new Intent(AdminProductDetails.this, com.simcoder.bimbo.MainActivity.class);
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
                                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(AdminProductDetails.this, com.simcoder.bimbo.WorkActivities.SettinsActivity.class);
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
                                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(AdminProductDetails.this, HistoryActivity.class);
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
                                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(AdminProductDetails.this, TraderProfile.class);
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
                                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(AdminProductDetails.this, AdminAllCustomers.class);
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
                                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(AdminProductDetails.this, AdminAddNewProductActivityII.class);
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
                                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(AdminProductDetails.this, AllGoodsBought.class);
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
                                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(AdminProductDetails.this, AdminPaymentHere.class);
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
                                        Intent intent = new Intent(AdminProductDetails.this, NotTraderActivity.class);
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

                                        Intent intent = new Intent(AdminProductDetails.this, AdminSettings.class);
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

// #BuiltByGOD