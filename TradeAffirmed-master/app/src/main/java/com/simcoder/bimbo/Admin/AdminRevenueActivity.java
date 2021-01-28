package  com.simcoder.bimbo.Admin;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.simcoder.bimbo.DriverMapActivity;
import com.simcoder.bimbo.HistoryActivity;
import com.simcoder.bimbo.WorkActivities.CartActivity;
import com.simcoder.bimbo.WorkActivities.HomeActivity;
import com.simcoder.bimbo.MainActivity;
import  com.simcoder.bimbo.R;
import com.simcoder.bimbo.WorkActivities.TraderProfile;
import com.simcoder.bimbo.instagram.Home.InstagramHomeActivity;
import com.simcoder.bimbo.instagram.Profile.ProfileActivity;

public class AdminRevenueActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private ImageView tShirts, sportsTShirts, femaleDresses, sweathers;
    private ImageView glasses, hatsCaps, walletsBagsPurses, shoes;
    private ImageView headPhonesHandFree, Laptops, watches, mobilePhones;
    ImageView addproduct;
    ImageView checkorders;
    ImageView viewproducts;
    ImageView viewgoodsbought;
    ImageView addadminproduct;
    ImageView checkordersfromadminhome;
    ImageView adminviewproducts;
    ImageView adminviewgoodsbought;
    ImageView adminprofile;
    ImageView adminrevenue;
    TextView slogan_category;
    TextView addproducttext;
    TextView checkorderstext;
    TextView viewproductstext;
    TextView viewgoodsboughttext;

    TextView   adminrevenuetext;
    ImageView viewsocial;
    TextView viewsocialtext;
    TextView adminprofiletext;
    private Button LogoutBtn, CheckOrdersBtn, maintainProductsBtn, HomeBtn, AllProducts,InCart;
    private DatabaseReference RoleReference;
    String role;
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    String traderID;
    FirebaseUser user;
    ImageView adminsettings;
    TextView adminsettingstext;
    //AUTHENTICATORS


    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Google Activity";
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminhomeactivity);

        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent traderIDintent = getIntent();
        if (traderIDintent.getExtras().getString("traderID") != null) {
            traderID = traderIDintent.getExtras().getString("traderID");
        }


        LogoutBtn = (Button) findViewById(R.id.admin_logout_btn);
        CheckOrdersBtn = (Button) findViewById(R.id.check_orders_btn);
        maintainProductsBtn = (Button) findViewById(R.id.maintain_btn);
        HomeBtn = (Button) findViewById(R.id.homebuttonhere);
        InCart = (Button) findViewById(R.id.incarts);
        AllProducts = (Button) findViewById(R.id.allproducts);
        addproduct = (ImageView) findViewById(R.id.addproduct);
        checkorders = (ImageView) findViewById(R.id.checkorders);
        viewproducts = (ImageView) findViewById(R.id.viewproducts);
        viewgoodsbought = (ImageView) findViewById(R.id.viewgoodsbought);
        slogan_category = (TextView) findViewById(R.id.slogan_category);


        adminprofile = (ImageView) findViewById(R.id.adminprofile);

        adminrevenue = (ImageView) findViewById(R.id.adminrevenue);
        adminrevenuetext = (TextView) findViewById(R.id.adminrevenuetext);
        addproducttext = (TextView) findViewById(R.id.addproducttext);
        checkorderstext = (TextView) findViewById(R.id.checkorderstext);
        viewproductstext = (TextView) findViewById(R.id.viewproductstext);
        viewgoodsboughttext = (TextView) findViewById(R.id.viewgoodsboughttext);


        adminsettings = (ImageView) findViewById(R.id.adminsettings);
        adminsettingstext = (TextView) findViewById(R.id.adminsettingstext);
        viewsocial = (ImageView) findViewById(R.id.viewsocial);
        viewsocialtext = (TextView) findViewById(R.id.viewsocialtext);
        adminprofiletext = (TextView) findViewById(R.id.adminprofiletext);


        //AUTHENTICATORS
        FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        if (mGoogleApiClient != null) {
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        }

        if (mGoogleApiClient != null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(AdminRevenueActivity.this,
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
                    traderID = "";
                    traderID = user.getUid();
                }

                // I HAVE TO TRY TO GET THE SETUP INFORMATION , IF THEY ARE ALREADY PROVIDED WE TAKE TO THE NEXT STAGE
                // WHICH IS CUSTOMER TO BE ADDED.
                // PULLING DATABASE REFERENCE IS NULL, WE CHANGE BACK TO THE SETUP PAGE ELSE WE GO STRAIGHT TO MAP PAGE
            }
        };

        buildGoogleApiClient();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            traderID = "";
            traderID = user.getUid();

    }}
        protected synchronized void buildGoogleApiClient() {
            if (mGoogleApiClient != null) {
                mGoogleApiClient = new GoogleApiClient.Builder(this)
                        .addConnectionCallbacks(AdminRevenueActivity.this)
                        .addOnConnectionFailedListener(this)
                        .addApi(LocationServices.API)
                        .build();
                mGoogleApiClient.connect();
            }
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
            if (mAuth != null){
                mAuth.removeAuthStateListener(firebaseAuthListener);
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
                if (!role.equals("Trader")) {
                    if (FirebaseAuth.getInstance() != null) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            String cusomerId = "";

                            cusomerId = user.getUid();
                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                            Intent intent = new Intent(AdminRevenueActivity.this, AdminAllCustomers.class);
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
                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                            Intent intent = new Intent(AdminRevenueActivity.this, ViewAllCarts.class);
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
                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                            Intent intent = new Intent(AdminRevenueActivity.this, AdminAddNewProductActivityII.class);
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
                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                            Intent intent = new Intent(AdminRevenueActivity.this, AdminAllProducts.class);
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
                                    Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                    Intent intent = new Intent(AdminRevenueActivity.this, AllProductsPurchased.class);
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
                                    Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                    Intent intent = new Intent(AdminRevenueActivity.this, ViewAllCustomers.class);
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
                                    Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                    Intent intent = new Intent(AdminRevenueActivity.this, TradersFollowing.class);
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
                                    Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                    Intent intent = new Intent(AdminRevenueActivity.this, AdminNewOrdersActivity.class);
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
                                    Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                    Intent intent = new Intent(AdminRevenueActivity.this, AdminCustomerServed.class);
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
                                    Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                    Intent intent = new Intent(AdminRevenueActivity.this, AdminAllOrderHistory.class);
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

                    Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
                    if (intent != null) {
                        intent.putExtra("traderorcustomer", traderID);
                        intent.putExtra("role", role);
                        startActivity(intent);
                        finish();
                    }
                } else {

                    Intent intent = new Intent(AdminRevenueActivity.this, DriverMapActivity.class);
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
                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                            Intent intent = new Intent(AdminRevenueActivity.this, CartActivity.class);
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
                                Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                Intent intent = new Intent(AdminRevenueActivity.this, InstagramHomeActivity.class);
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
                                    Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                    Intent intent = new Intent(AdminRevenueActivity.this, AdminAllProducts.class);
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
                                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                            Intent intent = new Intent(AdminRevenueActivity.this, SearchForAdminProductsActivity.class);
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
                                        mGoogleSignInClient.signOut().addOnCompleteListener(AdminRevenueActivity.this,
                                                new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                    }
                                                });
                                    }
                                }
                                Intent intent = new Intent(AdminRevenueActivity.this, com.simcoder.bimbo.MainActivity.class);
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
                                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                            Intent intent = new Intent(AdminRevenueActivity.this, com.simcoder.bimbo.WorkActivities.SettinsActivity.class);
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
                                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                            Intent intent = new Intent(AdminRevenueActivity.this, HistoryActivity.class);
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
                                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                            Intent intent = new Intent(AdminRevenueActivity.this, TraderProfile.class);
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
                                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                            Intent intent = new Intent(AdminRevenueActivity.this, AdminAllCustomers.class);
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
                                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                            Intent intent = new Intent(AdminRevenueActivity.this, AdminAddNewProductActivityII.class);
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
                                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                            Intent intent = new Intent(AdminRevenueActivity.this, AllGoodsBought.class);
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
                                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                            Intent intent = new Intent(AdminRevenueActivity.this, AdminPaymentHere.class);
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
                                            Intent intent = new Intent(AdminRevenueActivity.this, NotTraderActivity.class);
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

                                            Intent intent = new Intent(AdminRevenueActivity.this, AdminSettings.class);
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