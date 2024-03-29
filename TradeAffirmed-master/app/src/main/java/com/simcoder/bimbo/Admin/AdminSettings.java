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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class AdminSettings extends AppCompatActivity {

    private EditText mNameField, mPhoneField, mCarField;

    private Button mBack, mConfirm;

    private ImageView mProfileImage;

    private FirebaseAuth mAuth;
    private DatabaseReference mAdminTraderDatabase;

    private String userID;
    private String mName;
    private String mPhone;
    private String mCar;
    private String mService;
    private String mProfileImageUrl;

    private Uri resultUri;

    private RadioGroup mRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_settings);


        mNameField = findViewById(R.id.name);
        mPhoneField = findViewById(R.id.phone);
        mCarField = findViewById(R.id.car);

        mProfileImage = findViewById(R.id.profileImage);

        mRadioGroup = findViewById(R.id.radioGroup);

        mBack = findViewById(R.id.back);
        mConfirm = findViewById(R.id.confirm);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            userID = "";
            userID = user.getUid();


            mAdminTraderDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(userID);

            getUserInfo();
            if (mProfileImage != null) {
                mProfileImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, 1);
                    }
                });
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
                }
            }
        }
    }
    private void getUserInfo(){
        mAdminTraderDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue(HashMaps.class);
                    if(map.get("name")!=null){
                        mName = map.get("name").toString();
                        mNameField.setText(mName);
                    }
                    if(map.get("phone")!=null){
                        mPhone = map.get("phone").toString();
                        mPhoneField.setText(mPhone);
                    }
                    // WE CAN GET THE TRADE AREA OF TRADE HERE AND PASSPORT NUMBER OR NATIONAL ID
                    //THIS PART REPRESENTS THE KIND OF TRADING SERVICE WE PROVIDE

                    if(map.get("service")!=null){
                        // THIS MUST BE CHANGED TO ALL THE DIFFERENT SERVICES WE HAVE , MOBILE, IMMOBILE, HYBRID
                        mService = map.get("service").toString();
                        switch (mService){
                            case"UberX":
                                mRadioGroup.check(R.id.UberX);
                                break;
                            case"UberBlack":
                                mRadioGroup.check(R.id.UberBlack);
                                break;
                            case"UberXl":
                                mRadioGroup.check(R.id.UberXl);
                                break;
                        }
                    }
                    if(map.get("profileImageUrl")!=null){
                        mProfileImageUrl = map.get("profileImageUrl").toString();
                        Glide.with(getApplication()).load(mProfileImageUrl).into(mProfileImage);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    //AFTER FIRST TIME OF CREATING INFO HE CAN HAVE NO ABILITY TO ALTER UNLESS PROVIDED BY ADMINISTRATOR
    // PERSONAL INFORMATION COULD BE CROSS-CHECKED FOR SECURITY
    // IT IS THE PAYMENT THAT MAKES IT DECENTRALIZED

    private void saveUserInformation() {
        mName = mNameField.getText().toString();
        mPhone = mPhoneField.getText().toString();
        mCar = mCarField.getText().toString();
        if (mName != null && mPhone != null && mCar != null) {

            int selectId = mRadioGroup.getCheckedRadioButtonId();

            final RadioButton radioButton = findViewById(selectId);
            if (radioButton != null) {
                if (radioButton.getText() == null) {
                    return;
                }

                mService = radioButton.getText().toString();
                if (mService != null) {
                    Map userInfo = new HashMap();
                    userInfo.put("name", mName);
                    userInfo.put("phone", mPhone);
                    userInfo.put("car", mCar);
                    userInfo.put("service", mService);
                    mAdminTraderDatabase.updateChildren(userInfo);

                    if (resultUri != null) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            userID = "";
                            userID = user.getUid();
                            StorageReference filePath = FirebaseStorage.getInstance().getReference().child("profile_images").child(userID);
                            Bitmap bitmap = null;
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                            byte[] data = baos.toByteArray();
                            UploadTask uploadTask = filePath.putBytes(data);

                            uploadTask.addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    finish();
                                    return;
                                }
                            });
                            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();

                                    Map newImage = new HashMap();
                                    newImage.put("profileImageUrl", downloadUrl.toString());
                                    mAdminTraderDatabase.updateChildren(newImage);

                                    finish();
                                    return;
                                }
                            });
                        } else {
                            finish();
                        }

                    }
                } else {

                    Toast.makeText(getApplicationContext(), "Please provide details", Toast.LENGTH_LONG).show();
                }

            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            mProfileImage.setImageURI(resultUri);
        }
    }


}
