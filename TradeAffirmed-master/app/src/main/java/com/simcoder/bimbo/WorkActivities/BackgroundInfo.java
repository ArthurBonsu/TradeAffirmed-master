package com.simcoder.bimbo.WorkActivities;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.simcoder.bimbo.Model.BackgroundInfoSubmitModel;
import com.simcoder.bimbo.Model.HashMaps;
import com.simcoder.bimbo.Model.Users;
import com.simcoder.bimbo.R;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BackgroundInfo extends AppCompatActivity {

    private EditText   Nameinfo, Emailinfo, Phoneinfo;

    private Button mBack, mConfirm;

    private ImageView mProfileImage;

    private FirebaseAuth mAuth;
    private DatabaseReference mUserDatabase;
    private DatabaseReference mApproval;
    private String userID;
    private String mName;
    private String mPhone;
    String  mEmail;
    private String mCar;
    String mService;
    private String mProfileImageUrl;
    Spinner mySpinner;

    String NameinfoString;
    String EmailinfoString;
    String PhoneinfoString;



    private Uri resultUri;
    String text;
    private RadioGroup mRadioGroup;
    RadioButton FemaleRadioButton;
    RadioButton MaleRadioButton;
    String role;

    RadioButton radioButtonforgender;
    String radiotext;

    String thenameinfostring;
    String theemailinfostring;
    String thephoneinfostring;
    String agetext;
    String countrytext;

    EditText NameofPerson;
    EditText  PhoneNumberOfPerson;
    EditText PersonEmail;
    RadioGroup genderspinnerradiogroup;
    RadioButton MaleRadiobutton;

    RadioButton FemaleMaleRadiobutton;
    Spinner agespinner;
    Spinner NationalIDofEmergencyPerson;
    Spinner countryspinner;
    Button saveinformationhere;
    ImageButton movetonext;
    ImageButton deletebutton;
    ImageButton homebutton;
    ImageButton suggestionsbutton;
    ImageButton       services;
    ImageButton  expectedshipping;
    ImageButton       adminprofile;
    String backgroundapprove;


    EditText MailingAddress;
    EditText        GpsCode;
    Spinner CountrySpinner;
    EditText  StreetAddress;
    ImageButton      home_icon;

    String  themailingaddressstring;
    String  thegpscodestring;
    String thestreetaddressstring;

    EditText EmergencyPersonName;
    EditText        EmergencyPersonPhoneNumber;
    EditText EmergencyPersonEmail;
    EditText YourEmergencypersonID;
    Spinner        typeofidspinner;
    ArrayAdapter<String> myIDAdapter;
    ArrayAdapter<String> mycountryAdapter;
    String theemergencypersonnamestring;
    String theemergencypersonphonestring;
    String theemergencypersonemailstring;
    String emergencypersonidstring;
    String typeofidtext;
    FirebaseUser user;
    String date;
    String time;
    private ProgressDialog mProgress;
    private StorageReference mStorage;
    String approvalID;
    String emnamestring, emphonestring, ememailstring, empersonid, emidtype , emcountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgroundcheck);
        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent userIDintent = getIntent();
        if (userIDintent.getExtras().getString("userID") != null) {
            userID = userIDintent.getExtras().getString("userID");
        }

        Intent approvalidintent = getIntent();
        if (approvalidintent.getExtras().getString("approvalID") != null) {
            approvalID = userIDintent.getExtras().getString("approvalID");
        }



        EmergencyPersonName = findViewById(R.id.EmergencyPersonName);
        EmergencyPersonPhoneNumber = findViewById(R.id.EmergencyPersonPhoneNumber);
        EmergencyPersonEmail = findViewById(R.id.EmergencyPersonEmail);
        YourEmergencypersonID = findViewById(R.id.emergencypersonID);
        typeofidspinner = findViewById(R.id.typeofidspinner) ;
        NationalIDofEmergencyPerson = findViewById(R.id.NationalIDofEmergencyPerson);
        countryspinner = findViewById(R.id.countryspinner);
        saveinformationhere = findViewById(R.id.saveinformationhere);
        movetonext = findViewById(R.id.movetonext);
        homebutton = findViewById(R.id.homebutton);
        suggestionsbutton = findViewById(R.id.suggestionsbutton);
        services = findViewById(R.id.services);
        expectedshipping = findViewById(R.id.expectedshipping);
        adminprofile =findViewById(R.id.adminprofile);


        emnamestring = EmergencyPersonName.getText().toString();
        emphonestring= EmergencyPersonPhoneNumber.getText().toString();
        ememailstring = EmergencyPersonEmail.getText().toString();
        empersonid = YourEmergencypersonID.getText().toString();


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            userID = "";
            userID = user.getUid();


            // GET FROM FOLLOWING KEY
            // HAVE TO BUILD THE STORAGE
            mStorage = FirebaseStorage.getInstance().getReference().child("user_images");

/*
                if (ProductsRef.push() != null) {
                    productRandomKey = ProductsRef.push().getKey();

                }
*/
            //I have to  check to ensure that gallery intent is not placed here for the other classes

            mProgress = new ProgressDialog(this);

            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID);
            mApproval = FirebaseDatabase.getInstance().getReference().child("Approval").child(approvalID);
            mUserDatabase.keepSynced(true);
            mApproval.keepSynced(true);
            // SET THE AGE ADAPTER

            // SET THE COUNTRY ADAPTER
            mycountryAdapter = new ArrayAdapter<String>(BackgroundInfo.this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.countryspinner));
            mycountryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            countryspinner.setAdapter(mycountryAdapter);

            countryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    emcountry = countryspinner.getSelectedItem().toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            // SET THE TYPE OF ID ADAPTER
            myIDAdapter = new ArrayAdapter<String>(BackgroundInfo.this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.typeofidspinner));
            myIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            NationalIDofEmergencyPerson.setAdapter(myIDAdapter);

            NationalIDofEmergencyPerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    emidtype = NationalIDofEmergencyPerson.getSelectedItem().toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            if (saveinformationhere != null) {
                saveinformationhere.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        saveUserInformation();
                        Toast.makeText(BackgroundInfo.this, "Background Information Saved", Toast.LENGTH_SHORT).show();
                    }
                });
                if (movetonext != null) {
                    movetonext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(BackgroundInfo.this, SecurityInfo.class);
                            if (intent != null) {
                                intent.putExtra("role", role);
                                intent.putExtra("userID", userID);
                                intent.putExtra("approvalID", approvalID);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
                if (deletebutton != null) {
                    deletebutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                          deleteUserInformation();
                        }
                    });
                }

            }

        }

    }

    //AFTER FIRST TIME OF CREATING INFO HE CAN HAVE NO ABILITY TO ALTER UNLESS PROVIDED BY ADMINISTRATOR
    // PERSONAL INFORMATION COULD BE CROSS-CHECKED FOR SECURITY
    // IT IS THE PAYMENT THAT MAKES IT DECENTRALIZED


    // Post Info
    public void saveUserInformation() {
        emnamestring = EmergencyPersonName.getText().toString();
        emphonestring= EmergencyPersonPhoneNumber.getText().toString();
        ememailstring = EmergencyPersonEmail.getText().toString();
        empersonid = YourEmergencypersonID.getText().toString();
        // GET THE INFORMATION FROM THE TEXT BOX

        backgroundapprove = "false";





        // GET DATES FOR PRODUCTS
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");

        if (currentDate != null) {
            date = currentDate.format(calendar.getTime()).toString();

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            if (currentTime != null) {
                time = currentTime.format(calendar.getTime());

            }


            if (emnamestring != null && emphonestring != null && ememailstring != null && empersonid !=null && emidtype !=null && emcountry !=null ) {

                mProgress.setMessage("Adding your Background Person Information To UserInfo And ApprovalInfo");

                mProgress.show();




                        // PICK UP THE SPECIAL PRODUCT INFO AND LOADING THEM INTO THE DATABASE
                BackgroundInfoSubmitModel backgroundinfotobesent = new BackgroundInfoSubmitModel( emnamestring, emphonestring,   ememailstring , empersonid , emidtype, emcountry,backgroundapprove);

                mApproval.setValue(backgroundinfotobesent, new
                                DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError databaseError, DatabaseReference
                                            databaseReference) {
                                        Toast.makeText(getApplicationContext(), "Add BackgroundInfo Code Information To Approval", Toast.LENGTH_SHORT).show();
                                        Intent addbackgroundinformationtosapproval = new Intent(BackgroundInfo.this, BackgroundInfo.class);

                                        startActivity(addbackgroundinformationtosapproval);

                                    }
                                });

                /*
                mUserDatabase.setValue(backgroundinfotobesent, new
                        DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference
                                    databaseReference) {
                                Toast.makeText(getApplicationContext(), "Add BackgroundInfo Code Information To User", Toast.LENGTH_SHORT).show();
                                Intent userbackgroundinformationaddintent = new Intent(BackgroundInfo.this, SecurityInfo.class);
                                userbackgroundinformationaddintent.putExtra("userID",  userID);
                                userbackgroundinformationaddintent.putExtra("role", role);
                                userbackgroundinformationaddintent.putExtra("approvalID", approvalID);
                                startActivity(userbackgroundinformationaddintent);

                            }
                        });
*/



                        mProgress.dismiss();
                    }

                };

            }

    public void deleteUserInformation() {
        emnamestring = "";
        emphonestring= "";
        ememailstring = "";
        empersonid = "";
        emidtype="";
        emcountry="";
        // GET THE INFORMATION FROM THE TEXT BOX

        backgroundapprove = "";





        // GET DATES FOR PRODUCTS
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");

        if (currentDate != null) {
            date = currentDate.format(calendar.getTime()).toString();

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            if (currentTime != null) {
                time = currentTime.format(calendar.getTime());

            }


            if (emnamestring != null && emphonestring != null && ememailstring != null && empersonid !=null && emidtype !=null && emcountry !=null ) {

                mProgress.setMessage("Deleting Background Person Information From Approval Info");

                mProgress.show();




                // PICK UP THE SPECIAL PRODUCT INFO AND LOADING THEM INTO THE DATABASE
                BackgroundInfoSubmitModel backgroundinfotobesent = new BackgroundInfoSubmitModel( emnamestring, emphonestring,   ememailstring , empersonid , emidtype, emcountry,backgroundapprove);

                mApproval.setValue(backgroundinfotobesent, new
                        DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference
                                    databaseReference) {
                                Toast.makeText(getApplicationContext(), "Remove BackgroundInfo Code Information From Approval", Toast.LENGTH_SHORT).show();
                                Intent addbackgroundinformationtosapproval = new Intent(BackgroundInfo.this, BackgroundInfo.class);

                                startActivity(addbackgroundinformationtosapproval);

                            }
                        });

                /*
                mUserDatabase.setValue(backgroundinfotobesent, new
                        DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference
                                    databaseReference) {
                                Toast.makeText(getApplicationContext(), "Add BackgroundInfo Code Information To User", Toast.LENGTH_SHORT).show();
                                Intent userbackgroundinformationaddintent = new Intent(BackgroundInfo.this, SecurityInfo.class);
                                userbackgroundinformationaddintent.putExtra("userID",  userID);
                                userbackgroundinformationaddintent.putExtra("role", role);
                                userbackgroundinformationaddintent.putExtra("approvalID", approvalID);
                                startActivity(userbackgroundinformationaddintent);

                            }
                        });
*/



                mProgress.dismiss();
            }

        };

    }

        }













