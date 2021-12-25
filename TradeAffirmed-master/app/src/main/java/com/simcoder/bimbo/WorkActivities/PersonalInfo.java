package com.simcoder.bimbo.WorkActivities;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.simcoder.bimbo.Model.HashMaps;
import com.simcoder.bimbo.R;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PersonalInfo extends AppCompatActivity {

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);
        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent userIDintent = getIntent();
        if (userIDintent.getExtras().getString("userID") != null) {
            userID = userIDintent.getExtras().getString("userID");
        }

        NameofPerson = findViewById(R.id.NameofPerson);
        PhoneNumberOfPerson = findViewById(R.id.PhoneNumberOfPerson);
        PersonEmail = findViewById(R.id.PersonEmail);

        thenameinfostring = NameofPerson.getText().toString();
        theemailinfostring  = PersonEmail.getText().toString();
        thephoneinfostring = PhoneNumberOfPerson.getText().toString();

        mProfileImage = findViewById(R.id.profileImage);

        genderspinnerradiogroup = findViewById(R.id.genderspinnerradiogroup);
        FemaleRadioButton = (RadioButton) findViewById(R.id.FemaleMaleRadiobutton);
        MaleRadiobutton = (RadioButton) findViewById(R.id.MaleRadiobutton);


        agespinner = (Spinner)findViewById(R.id.agespinner);
        NationalIDofEmergencyPerson = (EditText)findViewById(R.id.NationalIDofEmergencyPerson);
        countryspinner = (Spinner)findViewById(R.id.countryspinner);
        saveinformationhere = (Button)findViewById(R.id.saveinformationhere);
        movetonext = (ImageButton)findViewById(R.id.movetonext);
        homebutton = (ImageButton)findViewById(R.id.homebutton);
        suggestionsbutton = (ImageButton)findViewById(R.id.suggestionsbutton);
        services = (ImageButton)findViewById(R.id.services);
        expectedshipping = (ImageButton)findViewById(R.id.expectedshipping);
        adminprofile = (ImageButton)findViewById(R.id.adminprofile);




        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            userID = "";
            userID  = user.getUid();


            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID);
            getUserInfo();
            // SET THE AGE ADAPTER
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(PersonalInfo.this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.agespinner));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            agespinner.setAdapter(myAdapter);

            agespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    agetext = agespinner.getSelectedItem().toString();
                    getUserInfo();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }

        // SET THE COUNTRY ADAPTER
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(PersonalInfo.this,
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
                        Intent intent = new Intent(PersonalInfo.this, ResidentialInfo.class);
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



    public void getUserInfo(){
        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue(HashMaps.class);


                    if (map.get("fullname") != null) {
                        mName = map.get("fullname").toString();

                        NameofPerson.setText(mName);


                    }
                    if (map.get("email") != null) {
                        mEmail  = map.get("email").toString();
                        PersonEmail.setText(mEmail);
                    }
                    if (map.get("phone") != null) {
                        mPhone  = map.get("phone").toString();
                        PhoneNumberOfPerson.setText(mEmail);
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

    public void saveUserInformation() {


        thenameinfostring = NameofPerson.getText().toString();
        theemailinfostring = PersonEmail.getText().toString();
        thephoneinfostring = PhoneNumberOfPerson.getText().toString();

        if (thenameinfostring != null && theemailinfostring != null && thephoneinfostring != null) {

            int selectedId = mRadioGroup.getCheckedRadioButtonId();

            // find the radiobutton by returned id
            radioButtonforgender = (RadioButton) findViewById(selectedId);
            radiotext = radioButtonforgender.getText().toString();
            Toast.makeText(PersonalInfo.this,
                    radioButtonforgender.getText(), Toast.LENGTH_SHORT).show();



            if (radioButtonforgender != null) {
                if (radioButtonforgender.getText() == null) {
                    return;
                }

                //WE WILL USE FULL NAME TO STORE VALUE TO PREVENT USERNAME
                if (radiotext != null) {
                    Map userInfo = new HashMap();
                    userInfo.put("fullname", thenameinfostring);
                    userInfo.put("phone", thephoneinfostring);
                    userInfo.put("email", theemailinfostring);
                    userInfo.put("gender", radiotext);
                    userInfo.put("age", agetext);
                    userInfo.put("country",countrytext);
                    mUserDatabase.updateChildren(userInfo);

                }}}}



}
