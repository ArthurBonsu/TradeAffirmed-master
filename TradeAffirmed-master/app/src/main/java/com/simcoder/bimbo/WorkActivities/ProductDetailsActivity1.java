package com.simcoder.bimbo.WorkActivities;

import android.content.Intent;
import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Query;
import com.simcoder.bimbo.Model.Cart;
import com.simcoder.bimbo.Model.Products;
import com.simcoder.bimbo.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.simcoder.bimbo.R;
import com.simcoder.bimbo.ViewHolder.ProductDetailsViewHolders;
import com.squareup.picasso.Picasso;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class ProductDetailsActivity1 extends AppCompatActivity   implements NavigationView.OnNavigationItemSelectedListener
{
    private Button addToCartButton;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productPrice, productDescription, productName, tradername;
    private String productID = "", state = "Normal";
    String cartkey;
    String orderkey;
    String thetraderkey;
    String thenameofthetrader;
    String description;
    String thetraderhere;
    DatabaseReference userDetails;
    String traderoruser= "";
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    RecyclerView productdetailsRecycler;
    RecyclerView.LayoutManager layoutManager;
    //AUTHENTICATORS

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Google Activity";
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    DatabaseReference ProductRef;
    FirebaseDatabase myfirebaseDatabase;
    String traderkey;
    String traderkeyhere;
    String theproductname;
    String suchtheprice;
    String thedescription;
    String thetraderwehave;
    String imagehere;
    String key;
    ImageView theproductimageview;
    String describe;
    String theprice;
    String productname;
    String userID;
    DatabaseReference  cartintoproductListRef;
    DatabaseReference cartforuser;
   String  traderimage;

   String  name;
    String  pid;
    String  tid;
     String  tradernamehere;
    String desc;
    String price;
    String pname;
    int  quantityselected;
    String quantitytext;
    TextView quantitytextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        theproductimageview = (ImageView) findViewById(R.id.product_image_details);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null)
        // TYPE IS THE SAME AS ROLE


        {  if (getIntent().getStringExtra("pid") != null) {
            productID = getIntent().getStringExtra("pid");
        }
        }
        traderoruser = getIntent().getStringExtra("fromcustomermapactivitytohomeactivity");


        {
            if (getIntent().getStringExtra("fromthehomeactivitytraderkey")!= null) {
                traderkeyhere = getIntent().getStringExtra("fromthehomeactivitytraderkey");
            }
        }


        traderoruser = getIntent().getStringExtra("fromdrivermapactivitytohomeactivity");


        //KEY PASSESS FOR TRADER

        {
            if ( getIntent().getStringExtra("fromthehomeactivityname") != null) {
                theproductname =  getIntent().getStringExtra("fromthehomeactivityname");
            }
        }
        if (getIntent().getStringExtra("fromthehomeactivityprice") != null) {
            theprice =  getIntent().getStringExtra("fromthehomeactivityprice");
        }

        if ( getIntent().getStringExtra("fromthehomeactivitydesc") != null) {
            thedescription =   getIntent().getStringExtra("fromthehomeactivitydesc");
        }

        if ( getIntent().getStringExtra("fromthehomeactivityname") != null) {

            thetraderwehave =  getIntent().getStringExtra("fromthehomeactivityname");
        }

        if (getIntent().getStringExtra("fromthehomeactivityimage") != null) {
            imagehere = getIntent().getStringExtra("fromthehomeactivityimage");
        }

        addToCartButton = (Button) findViewById(R.id.pd_add_to_cart_button);
        numberButton = (ElegantNumberButton) findViewById(R.id.number_btn);
        productImage = (ImageView) findViewById(R.id.product_image_details);
        productName = (TextView) findViewById(R.id.product_name_details);
        productDescription = (TextView) findViewById(R.id.product_description_details);
        productPrice = (TextView) findViewById(R.id.product_price_details);
        tradername = (TextView)findViewById(R.id.product_tradername);
        quantitytextview = (TextView)findViewById(R.id.quantityselected);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        if (mGoogleApiClient != null) {
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        }

        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(ProductDetailsActivity1.this,
                    new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        }
                    }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        }


        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    traderoruser = "";
                    traderoruser = user.getUid();
                }

                // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                // WHICH IS CUSTOMER TO BE ADDED.
                // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
            }
        };

        Paper.init(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.hometoolbar);
        if (toolbar != null) {
            toolbar.setTitle("Product Details");
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
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



            {
                if (user.getDisplayName() != null) {
                    if (user.getDisplayName() != null) {
                        userNameTextView.setText(user.getDisplayName());

                        Picasso.get().load(user.getPhotoUrl()).placeholder(R.drawable.profile).into(profileImageView);
                    }
                }
            }


            productdetailsRecycler = findViewById(R.id.recycler_menu);
            if (productdetailsRecycler != null) {
                productdetailsRecycler.setHasFixedSize(true);

            }
            layoutManager = new LinearLayoutManager(this);
            if (productdetailsRecycler != null) {
                productdetailsRecycler.setLayoutManager(layoutManager);
            }
        }





        //   DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("Product");

        thetraderkey = getIntent().getStringExtra("fromthehomeactivitytoproductdetails");
        Intent intents = new Intent(ProductDetailsActivity1.this, ConfirmFinalOrderActivity.class);

        intents.putExtra("cartkey", cartkey);
        if (getIntent() != null) {
            orderkey = getIntent().getStringExtra("orderkey");

            if (productID != null) {

                ProductRef = myfirebaseDatabase.getInstance().getReference().child("Product");

                Query productsquery =

                        myfirebaseDatabase.getInstance().getReference().child("Product").orderByChild("pid").equalTo(productID);

                if (productsquery != null) {
                    productsquery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                dataSnapshot.getValue(Products.class);


                                        if (dataSnapshot.child("key").getValue() != null) {
                                            key = dataSnapshot.child("key").getValue(String.class);
                                            Log.d("TAG", key);

                                        }

                                if (dataSnapshot.child("name").getValue() != null) {
                                    name = dataSnapshot.child("name").getValue(String.class);
                                    Log.d("TAG", name);

                                }
                                if (dataSnapshot.child("pid").getValue() != null) {
                                    pid = dataSnapshot.child("pid").getValue(String.class);
                                    Log.d("TAG", pid);

                                }

                                if (dataSnapshot.child("tid").getValue() != null) {
                                    tid = dataSnapshot.child("tid").getValue(String.class);
                                    Log.d("TAG", tid);

                                }
                                if (dataSnapshot.child("tradername").getValue() != null) {
                                    tradernamehere = dataSnapshot.child("tradername").getValue(String.class);
                                    Log.d("TAG", tradernamehere);

                                }
                                if (dataSnapshot.child("desc").getValue() != null) {
                                    desc = dataSnapshot.child("desc").getValue(String.class);
                                    Log.d("TAG", desc);

                                }
                                if (dataSnapshot.child("price").getValue() != null) {
                                    price = dataSnapshot.child("price").getValue(String.class);
                                    Log.d("TAG", price);

                                }
                                if (dataSnapshot.child("pname").getValue() != null) {
                                    pname = dataSnapshot.child("pname").getValue(String.class);
                                    Log.d("TAG", pname);

                                }
                            }

                                }



                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

            }}
            Log.d("TAG", productID);

              // Elegant Users Inforamation Here

           getProductDetails();
            if (numberButton != null)  {
                numberButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        quantityselected +=1;
                        quantitytextview.setText(quantityselected);




                });
                });
            }


            if (addToCartButton != null)  {
                addToCartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (state.equals("Order Placed") || state.equals("Order Shipped")) {
                            Toast.makeText(ProductDetailsActivity1.this, "you can add purchase more products, once your order is shipped or confirmed.", Toast.LENGTH_LONG).show();
                        } else {
                            addingToCartList();
                            Toast.makeText(ProductDetailsActivity1.this, "adding your product to cart.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
    }

   @Override
    protected void onStart()
    {
        super.onStart();

        CheckOrderState();
    }

    private void addingToCartList() {
        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(calForDate.getTime());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userID = "";

            userID = user.getUid();

        }
        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart");


        if (cartListRef.getKey() == "") {
            cartkey = cartListRef.push().getKey();
            //cartListRef.setValue(cartkey);
        } else {
            cartkey = cartListRef.getKey();
        }
        final HashMap<String, Object> cartMap = new HashMap<>();
        final HashMap<String, Object> traderMap = new HashMap<>();


        cartMap.put("pid", key);
        cartMap.put("name", productname);
        cartMap.put("desc", describe);
        cartMap.put("price", suchtheprice);
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);



        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            cartMap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
        }
        if (numberButton != null) {
            cartMap.put("quantity", numberButton.getNumber());
        }
        cartMap.put("discount", "");
        cartMap.put("tid", traderkey);


        cartMap.put("tradername", thetraderhere );
        cartMap.put("traderimage", traderimage );
        if (cartkey != null) {
            cartforuser = FirebaseDatabase.getInstance().getReference().child("Cart").child(cartkey).child("Users");
            cartforuser.keepSynced(true);
            user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null){
                userID = "";
                userID = user.getUid();
            }

            cartforuser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    dataSnapshot.getValue(Users.class);
                    if (dataSnapshot.hasChild(userID)) {

                        cartintoproductListRef = FirebaseDatabase.getInstance().getReference().child("Cart").child(cartkey).child("Users").child(userID).child("products");
                        cartintoproductListRef.keepSynced(true);
                        //    String productinputedkey = cartintoproductListRef.push().getKey();

                        cartintoproductListRef.setValue(key);
                        if (key != null) {
                            cartintoproductListRef.child(key)
                                    .updateChildren(cartMap);
                            cartListRef.child(cartkey)
                                    .updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ProductDetailsActivity1.this, "Added to Cart List.", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(ProductDetailsActivity1.this, HomeActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });

                        } else {


                            cartforuser.setValue(userID);
                            DatabaseReference cartintoproductListRef = FirebaseDatabase.getInstance().getReference().child("Cart").child(cartkey).child("Users").child(userID).child("products");
                            //    String productinputedkey = cartintoproductListRef.push().getKey();

                            cartintoproductListRef.setValue(key);
                            if (key != null) {
                                cartintoproductListRef.child(key)
                                        .updateChildren(cartMap);
                                cartListRef.child(cartkey)
                                        .updateChildren(cartMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(ProductDetailsActivity1.this, "Added to Cart List.", Toast.LENGTH_SHORT).show();

                                                    Intent intent = new Intent(ProductDetailsActivity1.this, HomeActivity.class);
                                                    startActivity(intent);
                                                }
                                            }
                                        });
                            }
                        }



                    }






                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                };
            });

        }












    }
























    private void getProductDetails() {



        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(ProductRef, Products.class)
                        .build();


        FirebaseRecyclerAdapter<Products, ProductDetailsViewHolders> adapter =
                new FirebaseRecyclerAdapter<Products, ProductDetailsViewHolders>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductDetailsViewHolders holder, int position, @NonNull final Products model) {
                        if (holder != null) {

                            holder.txtProductName.setText(model.getname());
                            key = model.getpid();
                            traderkey = model.gettid();
                            describe = model.getdesc();
                            suchtheprice = model.getprice();
                            productname = model.getpname();
                            model.setTrader(traderkey);

                            holder.txtProductDescription.setText(model.getdesc());
                            holder.txtProductPrice.setText("Price = " + model.getprice() + "$");
                            holder.setImage(getApplicationContext(), model.getpimage());


                            if (ProductRef != null) {
                                ProductRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            dataSnapshot.getValue(Products.class);
                                            if (key != null) {
                                                if (traderkey != null) {
                                                    if (dataSnapshot.child("tradername").getValue() != null) {
                                                        thetraderhere = dataSnapshot.child("tradername").getValue(String.class);


                                                    }

                                                }

                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                holder.settrader(thetraderhere);
                            }


                        }

                        holder.addthenumbersbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                numberButton.getNumber();
                            }
                        });

                        holder.AddtoCartButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (state.equals("Order Placed") || state.equals("Order Shipped")) {
                                    Toast.makeText(ProductDetailsActivity1.this, "you can add purchase more products, once your order is shipped or confirmed.", Toast.LENGTH_LONG).show();
                                } else {
                                    addingToCartList();
                                    Toast.makeText(ProductDetailsActivity1.this, "adding your product to cart.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                        /*if (model != null) {
                            if (theproductimageview != null) {
                                Picasso.get().load(Integer.parseInt(model.getimage())).resize(400, 0).networkPolicy(NetworkPolicy.OFFLINE).into(theproductimageview, new Callback() {


                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get().load(Integer.parseInt(model.getimage())).resize(100,0).into(theproductimageview);
                                    }

                                });


                            }

*/


                        if (holder != null) {
                            holder.tradername.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Intent intent = new Intent(ProductDetailsActivity1.this, TraderProfile.class);
                                    intent.putExtra("pid", key);
                                    intent.putExtra("fromhomeactivitytotraderprofile", traderkey);

                                    startActivity(intent);
                                }
                            });
                        }

                    }

                    @NonNull
                    @Override
                    public ProductDetailsViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_product_details, parent, false);
                        ProductDetailsViewHolders holder = new ProductDetailsViewHolders(view);
                        return holder;
                    }



                };

/*

        productName.setText(theproductname);
        productPrice.setText(theprice);
        productDescription.setText(thedescription);
        tradername.setText(thetraderwehave );
              if (productImage != null) {
                  Picasso.get().load(imagehere).into(productImage);
              }
        tradername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                {
                    Intent intent = new Intent(ProductDetailsActivity.this, TraderProfile.class);
                    intent.putExtra("pid", productID);
                    intent.putExtra("fromhomeactivitytotraderprofile", traderkeyhere);




                    startActivity(intent);
                }


            }
        });

  */
        if (productdetailsRecycler != null) {
            productdetailsRecycler.setAdapter(adapter);
        }
        if (adapter != null) {
            adapter.startListening();
        }     }


    private void CheckOrderState() {
        DatabaseReference ordersRef;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userID = "";
            userID = user.getUid();

        }
        if (orderkey != null) {
            ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(orderkey);

            ordersRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        dataSnapshot.getValue(Cart.class);
                        String shippingState = dataSnapshot.child("state").getValue(String.class);

                        if (shippingState.equals("shipped")) {
                            state = "Order Shipped";
                        } else if (shippingState.equals("not shipped")) {
                            state = "Order Placed";
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
