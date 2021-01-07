package com.simcoder.bimbo.WorkActivities;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;

import com.firebase.ui.database.SnapshotParser;
import com.google.android.gms.analytics.ecommerce.Product;
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
import android.widget.LinearLayout;
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
import com.simcoder.bimbo.Interface.ItemClickListner;
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
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;
import com.simcoder.bimbo.ViewHolder.ProductDetailsViewHolders;

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
    private FirebaseRecyclerAdapter adapter;
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
    ImageView product_image_details;
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
    int  quantityselected= 1;
    String quantitytext;
    TextView quantitytextview;
   String  quantity;
    String  image;
     String discount;
    String   pimage;
    TextView   product_name_details;
      TextView  product_description_details;
    TextView product_price_details;
    TextView         product_tradername;
    public  ElegantNumberButton  number_btn;
    Button pd_add_to_cart_button;
    TextView quantityselectedandshown;
    TextView productdiscount;
   TextView likesgiven;
    ImageView traderimagehere;
    DatabaseReference cartListRef;
    ProductDetailsViewHolders1 holders;
       int currentprice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


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


        thetraderkey = getIntent().getStringExtra("fromthehomeactivitytoproductdetails");
        Intent intents = new Intent(ProductDetailsActivity1.this, ConfirmFinalOrderActivity.class);



        if (getIntent() != null) {
            orderkey = getIntent().getStringExtra("orderkey");

            product_image_details = (ImageView) findViewById(R.id.product_image_details);
        pd_add_to_cart_button = (Button) findViewById(R.id.pd_add_to_cart_button);
        number_btn = (ElegantNumberButton) findViewById(R.id.number_btn);

        product_name_details = (TextView) findViewById(R.id.product_name_details);
        product_description_details = (TextView) findViewById(R.id.product_description_details);
        product_price_details = (TextView) findViewById(R.id.product_price_details);
        tradername = (TextView)findViewById(R.id.product_tradername);
        quantityselectedandshown = (TextView)findViewById(R.id.quantityselectedandshown);
        likesgiven = (TextView)findViewById(R.id.likesgiven);
        productdiscount = (TextView)findViewById(R.id.productdiscount);
            traderimagehere = (ImageView) findViewById(R.id.traderimagehere);
        fetch();
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

            Log.d("TAG", productID);

              // Elegant Users Inforamation Here

            intents.putExtra("cartkey", cartkey);


    }}


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public class ProductDetailsViewHolders1 extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public ImageView product_image_details;
        public TextView product_name_details;
        public TextView product_description_details;
        public TextView product_price_details;
        public TextView product_tradername;
        public  ElegantNumberButton number_btn;
        public  TextView  quantityselectedandshown;
        public  TextView pd_add_to_cart_button;
        public  TextView likesgiven;
        public  TextView productdiscount;
        public  ImageView traderimagehere;
        public ItemClickListner listner;

        public ProductDetailsViewHolders1(View itemView) {
            super(itemView);
            product_image_details = itemView.findViewById(R.id.product_image_details);
            product_name_details = itemView.findViewById(R.id.product_name_details);
            product_description_details = itemView.findViewById(R.id.product_description_details);
            product_price_details = itemView.findViewById(R.id.product_price_details);
            product_tradername = itemView.findViewById(R.id.product_tradername);
            likesgiven = itemView.findViewById(R.id.likesgiven);
            number_btn = itemView.findViewById(R.id.number_btn);
            //cartimage referst to the trader of the product

            quantityselectedandshown = itemView.findViewById(R.id.quantityselectedandshown);
            pd_add_to_cart_button = itemView.findViewById(R.id.pd_add_to_cart_button);
             likesgiven = itemView.findViewById(R.id.likesgiven);
            productdiscount = itemView.findViewById(R.id.productdiscount);
            traderimagehere = itemView.findViewById(R.id.traderimagehere);

        }





        public void setItemClickListner(ItemClickListner listner) {
            this.listner = listner;
        }

        public void setcartproductname(String cartproductname) {

            product_name_details.setText(cartproductname);
        }
        public void setproduct_name_details(String addressfoundhere) {

            product_name_details.setText(addressfoundhere);
        }

        public void setproduct_description_details(String product_description_details1) {

            product_description_details.setText(product_description_details1);
        }

        public void setproduct_price_details(String product_price_details1) {

            product_price_details.setText(product_price_details1);
        }

        public void settradername(String product_tradername1) {

            product_tradername.setText(product_tradername1);
        }


        public void setlikesgiven(String likesgiven1) {

            likesgiven.setText(likesgiven1);
        }


        public void setquantityselected(String quantityselectedandshown1) {

            quantityselectedandshown.setText(quantityselectedandshown1);
        }

        public void setpd_add_to_cart_button(String pd_add_to_cart_button1) {

            pd_add_to_cart_button.setText(pd_add_to_cart_button1);
        }

        public void setproductdiscount(String productdiscount1) {

            productdiscount.setText(productdiscount1);
        }

        public void setImage(final Context ctx, final String image) {
            product_image_details = (android.widget.ImageView) itemView.findViewById(R.id.product_image_details);
            if (image != null) {
                if (product_image_details != null) {

                    //
                    Picasso.get().load(image).resize(400, 0).networkPolicy(NetworkPolicy.OFFLINE).into(product_image_details, new Callback() {


                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get().load(image).resize(100, 0).into(product_image_details);
                        }


                    });

                }}
        }

        public void setTraderImage(final Context ctx, final String image) {
            final android.widget.ImageView traderimageonscreen = (android.widget.ImageView) itemView.findViewById(R.id.traderimagehere);

            Picasso.get().load(image).resize(400, 0).networkPolicy(NetworkPolicy.OFFLINE).into(traderimagehere, new Callback() {


                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).resize(100, 0).into(traderimagehere);
                }


            });


        }



        }







   @Override
    protected void onStart()
    {
        super.onStart();

       // CheckOrderState();
    }

    public void addingToCartList() {
        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userID = "";

            userID = user.getUid();

        }
       cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart");



            cartkey = cartListRef.push().getKey();
            //cartListRef.setValue(cartkey);

        //    cartkey = cartListRef.getKey();

        final HashMap<String, Object> cartMap = new HashMap<>();
        final HashMap<String, Object> traderMap = new HashMap<>();
   //     pid, tid, price, desc, pimage, pname, discount, name, image, tradernamehere, traderimage

        cartMap.put("pid", pid);
        cartMap.put("tid", tid);
        cartMap.put("price", price);
        cartMap.put("amount", currentprice);
        cartMap.put("desc", desc);
        cartMap.put("pimage", pimage);
        cartMap.put("pname", pname);
        cartMap.put("discount", discount);
        cartMap.put("name", name);
        cartMap.put("image", image);
        cartMap.put("tradernamehere", tradernamehere);
        cartMap.put("traderimage", traderimage);
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            cartMap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
        }
        if (quantityselected != 0) {
            cartMap.put("quantity",quantityselected);
        }




        if (cartkey != null) {
            cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart");
            cartListRef.keepSynced(true);
            user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                userID = "";
                userID = user.getUid();
            }

            cartListRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    dataSnapshot.getValue(Users.class);
                    if (dataSnapshot.hasChild(cartkey)) {


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


                        cartkey = cartListRef.push().getKey();

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

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        }

        }





    private void fetch() {


        Query query = myfirebaseDatabase
                .getReference()
                .child("Product").orderByChild("pid").equalTo(productID).limitToFirst(1);
        if (query != null) {

            FirebaseRecyclerOptions<Products> options =
                    new FirebaseRecyclerOptions.Builder<Products>()
                            .setQuery(query, new SnapshotParser<Products>() {


                                @NonNull
                                @Override
                                public Products parseSnapshot(@NonNull DataSnapshot snapshot) {


                                    if (snapshot.child("pid").getValue() != null) {
                                        pid = snapshot.child("pid").getValue(String.class);
                                    }
                                    if (snapshot.child("tid").getValue() != null) {
                                        tid = snapshot.child("tid").getValue(String.class);
                                    }

                                       if (snapshot.child("price").getValue() != null) {
                                        price = snapshot.child("price").getValue(String.class);

                                    }

                                    if (snapshot.child("desc").getValue() != null) {
                                        desc = snapshot.child("desc").getValue(String.class);
                                    }


                                    if (snapshot.child("pimage").getValue() != null) {
                                        pimage = snapshot.child("pimage").getValue(String.class);
                                    }
                                    if (snapshot.child("pname").getValue() != null) {
                                        pname = snapshot.child("pname").getValue(String.class);
                                    }


                                    if (snapshot.child("discount").getValue() != null) {
                                        discount = snapshot.child("discount").getValue(String.class);
                                    }

                                    if (snapshot.child("name").getValue() != null) {
                                        name = snapshot.child("name").getValue(String.class);
                                    }


                                    if (snapshot.child("image").getValue() != null) {
                                        image = snapshot.child("image").getValue(String.class);
                                    }

                                    if (snapshot.child("tradername").getValue() != null) {
                                        tradernamehere = snapshot.child("tradername").getValue(String.class);
                                    }

                                    if (snapshot.child("traderimage").getValue() != null) {
                                        traderimage = snapshot.child("traderimage").getValue(String.class);
                                    }


                                    return new Products(pid, tid, price, desc, pimage, pname, discount, name, image, tradernamehere, traderimage);


                                }

                            }).build();


            adapter = new FirebaseRecyclerAdapter<Products, ProductDetailsActivity1.ProductDetailsViewHolders1>(options) {
                @Override
                protected void onBindViewHolder(@NonNull ProductDetailsActivity1.ProductDetailsViewHolders1 holder, int i, @NonNull Products model) {
                    holders = holder;
                    if (holder != null) {


                        pid = model.getpid();
                        traderkey = model.gettid();
                        holder.product_name_details.setText(model.getpname());
                        holder.product_description_details.setText(model.getdesc());
                        holder.product_price_details.setText(model.getprice());
                        holder.product_tradername.setText(model.gettradername());
                        holder.likesgiven.setText(model.getnumber());
                        holder.productdiscount.setText(model.getdiscount());


                        //      model.setTrader(traderkey);

                        holder.setImage(getApplicationContext(), model.getpimage());
                        holder.setTraderImage(getApplicationContext(), model.gettraderimage());

                    }

                    holder.number_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            quantityselected = +1;
                            holders.quantityselectedandshown.setText(quantityselected);
                              currentprice  =  Integer.parseInt(model.getprice()) * quantityselected;
                            holders.product_price_details.setText(currentprice);
                        }
                    });

                    holder.pd_add_to_cart_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                             addingToCartList();
                            Intent intent = new Intent(ProductDetailsActivity1.this, CartActivity.class);
                            intent.putExtra("pid", pid);
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
                        holder.product_tradername.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(ProductDetailsActivity1.this, TraderProfile.class);
                                intent.putExtra("pid", pid);
                                intent.putExtra("fromhomeactivitytotraderprofile", traderkey);

                                startActivity(intent);
                            }
                        });
                    }
                }




                @NonNull
                @Override
                public ProductDetailsActivity1.ProductDetailsViewHolders1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_product_details, parent, false);
                    ProductDetailsViewHolders1 holder = new ProductDetailsViewHolders1 (view);
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
            }
        }
    }

       /*
        private void CheckOrderState () {
            DatabaseReference ordersRef;

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                userID = "";
                userID = user.getUid();

            }

            // We have to move this to cart Acitivity
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

       */




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
