package com.simcoder.bimbo.Admin;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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

public class PersonalInformationForm extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalinformationform);
        Intent roleintent = getIntent();
        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent traderIDintent = getIntent();
        if (traderIDintent.getExtras().getString("traderID") != null) {
            traderID = traderIDintent.getExtras().getString("traderID");
        }

        Nameinfo = findViewById(R.id.Nameinfo);
        Emailinfo = findViewById(R.id.Phoneinfo);
        Phoneinfo = findViewById(R.id.Emailinfo);


        NameinfoString = Nameinfo.getText().toString();
        EmailinfoString = Emailinfo.getText().toString();
        PhoneinfoString = Phoneinfo.getText().toString();

        mProfileImage = findViewById(R.id.profileImage);

        mRadioGroup = findViewById(R.id.Gender);
        FemaleRadioButton = (RadioButton) findViewById(R.id.maleradiobutton);
        MaleRadioButton = (RadioButton) findViewById(R.id.maleradiobutton);

        mBack = findViewById(R.id.back);
        mConfirm = findViewById(R.id.confirm);
        mySpinner = (Spinner) findViewById(R.id.agespinner);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            traderID = "";
            traderID = user.getUid();


            mAdminTraderDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(traderID);
            getUserInfo();

            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(PersonalInformationForm.this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.agespinner));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mySpinner.setAdapter(myAdapter);

            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    text = mySpinner.getSelectedItem().toString();
                    getUserInfo();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }



            if (mConfirm != null) {
                mConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                                saveUserInformation();
                    }
                });
                if (mBack != null) {
                    mBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                            return;
                        }
                    });
                }


            }}



    public void getUserInfo(){
        mAdminTraderDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue(HashMaps.class);
                    if (map.get("name") != null) {
                        mName = map.get("name").toString();

                        Nameinfo.setText(mName);


                    }
                    if (map.get("phone") != null) {
                        mEmail = map.get("phone").toString();
                        Emailinfo.setText(mPhone);
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


         thenameinfostring = Nameinfo.getText().toString();
         theemailinfostring = Emailinfo.getText().toString();
         thephoneinfostring = Phoneinfo.getText().toString();

        if (thenameinfostring != null && theemailinfostring != null && thephoneinfostring != null) {

            int selectedId = mRadioGroup.getCheckedRadioButtonId();

            // find the radiobutton by returned id
            radioButtonforgender = (RadioButton) findViewById(selectedId);
            radiotext = radioButtonforgender.getText().toString();
            Toast.makeText(PersonalInformationForm.this,
                    radioButtonforgender.getText(), Toast.LENGTH_SHORT).show();



            if (radioButtonforgender != null) {
                if (radioButtonforgender.getText() == null) {
                    return;
                }


                if (radiotext != null) {
                    Map userInfo = new HashMap();
                    userInfo.put("name", thenameinfostring);
                    userInfo.put("phone", thephoneinfostring);
                    userInfo.put("email", theemailinfostring);
                    userInfo.put("gender", radiotext);
                    mAdminTraderDatabase.updateChildren(userInfo);

                }}}}



}
