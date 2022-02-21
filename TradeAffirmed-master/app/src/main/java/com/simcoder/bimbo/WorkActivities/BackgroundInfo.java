package com.simcoder.bimbo.WorkActivities;

import com.google.firebase.auth.FirebaseUser;

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
import com.simcoder.bimbo.R;


import java.util.HashMap;
import java.util.Map;

public class BackgroundInfo extends AppCompatActivity {

    private EditText   Nameinfo, Emailinfo, Phoneinfo;

    private Button mBack, mConfirm;

    private ImageView mProfileImage;

    private FirebaseAuth mAuth;
    private DatabaseReference mUserDatabase;

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
    ImageButton homebutton;
    ImageButton suggestionsbutton;
    ImageButton       services;
    ImageButton  expectedshipping;
    ImageButton       adminprofile;



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


        theemergencypersonnamestring = EmergencyPersonName.getText().toString();
        theemergencypersonphonestring= EmergencyPersonPhoneNumber.getText().toString();
        theemergencypersonemailstring = EmergencyPersonEmail.getText().toString();
        emergencypersonidstring = YourEmergencypersonID.getText().toString();


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            userID = "";
            userID = user.getUid();


            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID);

            // SET THE AGE ADAPTER


            // SET THE COUNTRY ADAPTER
            mycountryAdapter = new ArrayAdapter<String>(BackgroundInfo.this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.countryspinner));
            mycountryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            countryspinner.setAdapter(mycountryAdapter);

            countryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    countrytext = countryspinner.getSelectedItem().toString();

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
                    typeofidtext = NationalIDofEmergencyPerson.getSelectedItem().toString();

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
                            Intent intent = new Intent(BackgroundInfo.this, ClientSecurityCheck.class);
                            if (intent != null) {
                                intent.putExtra("role", role);
                                intent.putExtra("userID", userID);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }


            }

        }

    }

    //AFTER FIRST TIME OF CREATING INFO HE CAN HAVE NO ABILITY TO ALTER UNLESS PROVIDED BY ADMINISTRATOR
    // PERSONAL INFORMATION COULD BE CROSS-CHECKED FOR SECURITY
    // IT IS THE PAYMENT THAT MAKES IT DECENTRALIZED

    public void saveUserInformation() {
        theemergencypersonnamestring = EmergencyPersonName.getText().toString();
        theemergencypersonphonestring= EmergencyPersonPhoneNumber.getText().toString();
        theemergencypersonemailstring = EmergencyPersonEmail.getText().toString();
        emergencypersonidstring = YourEmergencypersonID.getText().toString();

        if (theemergencypersonnamestring != null && theemergencypersonphonestring != null && theemergencypersonemailstring != null && emergencypersonidstring !=null) {



                //WE WILL USE FULL NAME TO STORE VALUE TO PREVENT USERNAME

                    Map userInfo = new HashMap();
                    userInfo.put("auxname", theemergencypersonnamestring);
                    userInfo.put("auxphone", theemergencypersonphonestring);
                    userInfo.put("auxemail", theemergencypersonemailstring);
                    userInfo.put("auxid", emergencypersonidstring);
                    userInfo.put("typeofid", typeofidtext);
                    userInfo.put("auxid", emergencypersonidstring);
                    userInfo.put("auxcountry", countrytext);
                    mUserDatabase.updateChildren(userInfo);

                }}}





