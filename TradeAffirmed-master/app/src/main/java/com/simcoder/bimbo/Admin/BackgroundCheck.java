package com.simcoder.bimbo.Admin;

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
import android.widget.TextView;
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

public class BackgroundCheck extends AppCompatActivity {

    private EditText   Nameinfo, Emailinfo, Phoneinfo;

    private Button mBack, mConfirm;

    private ImageView mProfileImage;

    private FirebaseAuth mAuth;
    private DatabaseReference mAdminTraderDatabase;

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
    Spinner        typeofidspinner;
    ArrayAdapter<String> myIDAdapter;
    ArrayAdapter<String> mycountryAdapter;
    String theemergencypersonnamestring;
    String theemergencypersonphonestring;
    String theemergencypersonemailstring;
    String typeofidtext;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.residentialinformationpage);
        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent traderIDintent = getIntent();
        if (traderIDintent.getExtras().getString("traderID") != null) {
            traderID = traderIDintent.getExtras().getString("traderID");
        }


         EmergencyPersonName = findViewById(R.id.EmergencyPersonName);
                EmergencyPersonPhoneNumber = findViewById(R.id.EmergencyPersonPhoneNumber);
         EmergencyPersonEmail = findViewById(R.id.EmergencyPersonEmail);
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



        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            traderID = "";
            traderID = user.getUid();


            mAdminTraderDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(traderID);
            getUserInfo();
            // SET THE AGE ADAPTER


            // SET THE COUNTRY ADAPTER
            mycountryAdapter = new ArrayAdapter<String>(BackgroundCheck.this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.countryspinner));
            mycountryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            countryspinner.setAdapter(mycountryAdapter);

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
               // SET THE TYPE OF ID ADAPTER
            myIDAdapter = new ArrayAdapter<String>(BackgroundCheck.this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.typeofidspinner));
            myIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            NationalIDofEmergencyPerson.setAdapter(myIDAdapter);

            NationalIDofEmergencyPerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    typeofidtext = NationalIDofEmergencyPerson.getSelectedItem().toString();
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
                            Intent intent = new Intent(ResidentialInformationPage.this, BackgroundCheck.class);
                            if (intent != null) {
                                intent.putExtra("role", role);
                                intent.putExtra("traderID", traderID);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }


            }

        }

    }
    //POPULATE THE EDIT BOX IF THERE ALREADY EXIST SUCH A TRANSACTION
    public void getUserInfo(){
        mAdminTraderDatabase.addValueEventListener(new ValueEventListener() {
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

    public void saveUserInformation() {

        themailingaddressstring = MailingAddress.getText().toString();
        thegpscodestring = GpsCode.getText().toString();
        thestreetaddressstring = StreetAddress.getText().toString();

        if (themailingaddressstring != null && thegpscodestring != null && thestreetaddressstring != null) {

            Map userInfo = new HashMap();
            userInfo.put("address", themailingaddressstring);
            userInfo.put("street", thestreetaddressstring);
            userInfo.put("gpscode", thegpscodestring);
            userInfo.put("country",countrytext);
            mAdminTraderDatabase.updateChildren(userInfo);

        }

    }

}




