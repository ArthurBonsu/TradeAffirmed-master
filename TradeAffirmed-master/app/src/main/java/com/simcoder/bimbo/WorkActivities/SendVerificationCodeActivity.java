package com.simcoder.bimbo.WorkActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.simcoder.bimbo.R;

import java.net.Inet4Address;
import java.util.concurrent.TimeUnit;

public class SendVerificationCodeActivity extends AppCompatActivity {

    String role;

    Intent roleintent;
    Intent userIDintent;
    String userID;
    Intent verifyintent;
    public SendVerificationCodeActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        EditText inputCode1, inputCode2, inputCode3, inputCode4, inputCode5, inputCode6;
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);


        if (roleintent.getExtras().getString("role") != null) {
            role = roleintent.getExtras().getString("role");
        }

        Intent userIDintent = getIntent();
        if (userIDintent.getExtras().getString("userID") != null) {
            userID = userIDintent.getExtras().getString("userID");
        }


        final EditText inputMobile = findViewById(R.id.inputMobile);
        final Button getcode = findViewById(R.id.getverificationcode);


         getcode.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v) {
                 if (inputMobile.getText().toString().trim().isEmpty()){
                     Toast.makeText(SendVerificationCodeActivity.this, "Enter Mobile", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 progressBar.setVisibility(View.VISIBLE);
                 getcode.setVisibility(View.INVISIBLE);

                 PhoneAuthProvider.getInstance().verifyPhoneNumber("+233" + inputMobile.getText().toString(), 60, TimeUnit.SECONDS,SendVerificationCodeActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                     @Override
                     public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    progressBar.setVisibility(View.GONE);
                         getcode.setVisibility(View.VISIBLE);
                     }

                     @Override
                     public void onVerificationFailed(@NonNull FirebaseException e) {
                         progressBar.setVisibility(View.GONE);
                         getcode.setVisibility(View.VISIBLE);
                         Toast.makeText(SendVerificationCodeActivity.this, "e.getMessage", Toast.LENGTH_SHORT).show();
                     }

                     @Override
                     public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                         super.onCodeSent(verificationId, forceResendingToken);
                          progressBar.setVisibility(View.GONE);
                          getcode.setVisibility(View.VISIBLE);
                          Intent codeintent = new Intent(getApplicationContext(), VerifyVerificationCodeActivity.class);
                         codeintent.putExtra("mobile", inputMobile.getText().toString());
                         codeintent.putExtra("verificationId", verificationId);
                          startActivity( codeintent);

                     }
                 });
             }

         });
/*
         verifyintent = new Intent(getApplicationContext(), VerifyVerificationCodeActivity.class);
         verifyintent.putExtra("mobile", inputMobile.getText().toString()  );
         verifyintent.putExtra("verificationId", verificationId);
         startActivity(verifyintent);
*/


    }


}
