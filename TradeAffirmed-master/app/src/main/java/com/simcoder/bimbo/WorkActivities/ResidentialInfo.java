package com.simcoder.bimbo.WorkActivities;

import com.google.firebase.auth.FirebaseUser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
import com.simcoder.bimbo.Model.HashMaps;
import com.simcoder.bimbo.Model.ResidentialInfoSubmitModelForClient;
import com.simcoder.bimbo.R;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class ResidentialInfo extends AppCompatActivity {

    private EditText   Nameinfo, Emailinfo, Phoneinfo;

    private Button mBack, mConfirm;

    private ImageView mProfileImage;

    private FirebaseAuth mAuth;
    private DatabaseReference mUserDatabase;
    private DatabaseReference mApprovalOrig;
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
    String traderID;
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
    EditText NationalIDofEmergencyPerson;
    Spinner countryspinner;
    Button saveinformationhere;
    ImageButton movetonext;
    ImageButton homebutton;
    ImageButton suggestionsbutton;
    ImageButton       services;
    ImageButton  expectedshipping;
    ImageButton       adminprofile;
    ImageButton deletebutton;



    EditText MailingAddress;
    EditText        GpsCode;
    Spinner CountrySpinner;
    EditText  StreetAddress;
    ImageButton      home_icon;

    String  themailingaddressstring;
    String  thegpscodestring;
    String thestreetaddressstring;
    String approvalID;
    ProgressDialog mProgressDialog;
    String date;
    String time;
    ProgressDialog mProgress;
    String residenceinfoapprove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.residentialinfo);
        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent userIDintent = getIntent();
        if (userIDintent.getExtras().getString("userID") != null) {
            userID = userIDintent.getExtras().getString("userID");
        }
        Intent approvalIDIntent = getIntent();
        if (approvalIDIntent.getExtras().getString("approvalID") != null) {
            approvalID = approvalIDIntent.getExtras().getString("approvalID");
        }
        MailingAddress = findViewById(R.id.MailingAddress);
        GpsCode = findViewById(R.id.GpsCode);
        StreetAddress = findViewById(R.id.StreetAddress);

        themailingaddressstring = MailingAddress.getText().toString();
        thegpscodestring = GpsCode.getText().toString();
        thestreetaddressstring = StreetAddress.getText().toString();


        countryspinner = (Spinner) findViewById(R.id.countryspinner);
        saveinformationhere = (Button) findViewById(R.id.saveinformationhere);
        movetonext = (ImageButton) findViewById(R.id.movetonext);
        homebutton = (ImageButton) findViewById(R.id.homebutton);
        suggestionsbutton = (ImageButton) findViewById(R.id.suggestionsbutton);
        services = (ImageButton) findViewById(R.id.services);
        expectedshipping = (ImageButton) findViewById(R.id.expectedshipping);
        adminprofile = (ImageButton) findViewById(R.id.adminprofile);
        deletebutton = (ImageButton) findViewById(R.id.deletebutton);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            userID = "";
            userID = user.getUid();


            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID);
            mApprovalOrig = FirebaseDatabase.getInstance().getReference().child("Approval");
            approvalID =  mApprovalOrig.push().getKey();
            mApproval = FirebaseDatabase.getInstance().getReference().child("Approval").child(approvalID);
            mUserDatabase.keepSynced(true);
            mApproval.keepSynced(true);
            getUserInfo();
            // SET THE AGE ADAPTER


            // SET THE COUNTRY ADAPTER
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ResidentialInfo.this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.countryspinner));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            countryspinner.setAdapter(myAdapter);

            countryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    countrytext = countryspinner.getSelectedItem().toString();
                    getUserInfo();
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
                    }
                });


                if (movetonext != null) {
                    movetonext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ResidentialInfo.this, BackgroundInfo.class);
                            if (intent != null) {
                                intent.putExtra("role", role);
                                intent.putExtra("userID", userID);
                                intent.putExtra("approvalID", approvalID);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });

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
    }
    //POPULATE THE EDIT BOX IF THERE ALREADY EXIST SUCH A TRANSACTION
    public void getUserInfo(){
        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue(HashMaps.class);
                    if (map.get("address") != null) {
                        String  themailingaddress = map.get("address").toString();

                        MailingAddress.setText(themailingaddress);


                    }
                    if (map.get("gpscode") != null) {
                        String    thegpscode = map.get("gpscode").toString();
                        GpsCode.setText(mPhone);
                    }

                    if (map.get("street") != null) {
                        String   thestreetaddress = map.get("street").toString();
                        StreetAddress.setText(thestreetaddress);
                    }

                }}


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }



    //AFTER FIRST TIME OF CREATING INFO HE CAN HAVE NO ABILITY TO ALTER UNLESS PROVIDED BY ADMINISTRATOR
    // PERSONAL INFORMATION COULD BE CROSS-CHECKED FOR SECURITY
    // IT IS THE PAYMENT THAT MAKES IT DECENTRALIZED
    //AFTER FIRST TIME OF CREATING INFO HE CAN HAVE NO ABILITY TO ALTER UNLESS PROVIDED BY ADMINISTRATOR
    // PERSONAL INFORMATION COULD BE CROSS-CHECKED FOR SECURITY
    // IT IS THE PAYMENT THAT MAKES IT DECENTRALIZED
// Post Info
    public void saveUserInformation() {
        themailingaddressstring = MailingAddress.getText().toString();
        thegpscodestring = GpsCode.getText().toString();
        thestreetaddressstring = StreetAddress.getText().toString(); // GET THE INFORMATION FROM THE TEXT BOX

        residenceinfoapprove = "false";



        // GET DATES FOR PRODUCTS
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");

        if (currentDate != null) {
            date = currentDate.format(calendar.getTime()).toString();

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            if (currentTime != null) {
                time = currentTime.format(calendar.getTime());

            }


            if (themailingaddressstring != null && thegpscodestring != null && thestreetaddressstring != null) {


                    mProgress.setMessage("Sending Residence Information To Approval ");

                    mProgress.show();




                    // PICK UP THE SPECIAL PRODUCT INFO AND LOADING THEM INTO THE DATABASE
                    ResidentialInfoSubmitModelForClient residentialinfotobesent = new ResidentialInfoSubmitModelForClient( themailingaddressstring , thegpscodestring, thestreetaddressstring,residenceinfoapprove);

                    mApproval.setValue(residentialinfotobesent, new
                            DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference
                                        databaseReference) {
                                    Toast.makeText(getApplicationContext(), "Add Residence Info Code Information To Approval", Toast.LENGTH_SHORT).show();
                                    Intent residentialinofoapprove = new Intent(ResidentialInfo.this, ResidentialInfo.class);

                                    startActivity(residentialinofoapprove);

                                }
                            });
/*
                    mUserDatabase.setValue(residentialinfotobesent, new
                            DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference
                                        databaseReference) {
                                    Toast.makeText(getApplicationContext(), "Add Residence Info Code Information To Approval", Toast.LENGTH_SHORT).show();
                                    Intent residentialinofoapprove = new Intent(ResidentialInfo.this, SecurityInfo.class);
                                    residentialinofoapprove.putExtra("userID", userID);
                                    residentialinofoapprove.putExtra("approvalID", approvalID);
                                    residentialinofoapprove.putExtra("role", role);
                                    startActivity(residentialinofoapprove);

                                }
                            });

*/


                    mProgress.dismiss();
                }

            };

        }


    public void deleteUserInformation() {
        themailingaddressstring = "";
        thegpscodestring = "";
        thestreetaddressstring = ""; // GET THE INFORMATION FROM THE TEXT BOX

        residenceinfoapprove = "false";



        // GET DATES FOR PRODUCTS
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");

        if (currentDate != null) {
            date = currentDate.format(calendar.getTime()).toString();

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            if (currentTime != null) {
                time = currentTime.format(calendar.getTime());

            }


            if (themailingaddressstring != null && thegpscodestring != null && thestreetaddressstring != null) {


                mProgress.setMessage("Deleting Residence Information For Approval");

                mProgress.show();




                // PICK UP THE SPECIAL PRODUCT INFO AND LOADING THEM INTO THE DATABASE
                ResidentialInfoSubmitModelForClient residentialinfotobesent = new ResidentialInfoSubmitModelForClient( themailingaddressstring , thegpscodestring, thestreetaddressstring,residenceinfoapprove);

                mApproval.setValue(residentialinfotobesent, new
                        DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference
                                    databaseReference) {
                                Toast.makeText(getApplicationContext(), "Remove Residence Info Code Information To Approval", Toast.LENGTH_SHORT).show();
                                Intent residentialinofoapprove = new Intent(ResidentialInfo.this, ResidentialInfo.class);

                                startActivity(residentialinofoapprove);

                            }
                        });
/*
                    mUserDatabase.setValue(residentialinfotobesent, new
                            DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference
                                        databaseReference) {
                                    Toast.makeText(getApplicationContext(), "Add Residence Info Code Information To Approval", Toast.LENGTH_SHORT).show();
                                    Intent residentialinofoapprove = new Intent(ResidentialInfo.this, SecurityInfo.class);
                                    residentialinofoapprove.putExtra("userID", userID);
                                    residentialinofoapprove.putExtra("approvalID", approvalID);
                                    residentialinofoapprove.putExtra("role", role);
                                    startActivity(residentialinofoapprove);

                                }
                            });

*/


                mProgress.dismiss();
            }

        };

    }





}




