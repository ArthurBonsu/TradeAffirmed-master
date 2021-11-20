package com.simcoder.bimbo.WorkActivities;
import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.Share;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.simcoder.bimbo.Admin.AdminCategoryActivity;
import com.simcoder.bimbo.Admin.AdminLogin;
import com.simcoder.bimbo.Admin.AdminMainPage;
import com.simcoder.bimbo.Admin.AdminRegister;
import com.simcoder.bimbo.Admin.AdminResetPasswordEmail;
import com.simcoder.bimbo.Admin.SessionManager;
import com.simcoder.bimbo.Model.Users;
import com.simcoder.bimbo.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;
import com.simcoder.bimbo.R;

import java.util.HashMap;
import java.util.List;

import io.paperdb.Paper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class OnboardingAdapter extends  RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

private List<OnboardingItem> onboardingItems;

    public OnboardingAdapter(List<OnboardingItem> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_onboarding,parent, false    ));
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
    holder.setOnboardngData(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder{
        private  TextView textTitle;
        private TextView textDescription;
        private ImageView imageOnboarding;
      OnboardingViewHolder(View itemView)  {
          super(itemView);
          textTitle = itemView.findViewById(R.id.textTitle);
        textDescription =itemView.findViewById(R.id.textDescription);
          imageOnboarding =itemView.findViewById(R.id.imageOnboarding);

      }

    void setOnboardngData(OnboardingItem onboardingItem) {
          textTitle.setText(onboardingItem.getTitle());
          textDescription.setText(onboardingItem.getDescription());
          imageOnboarding.setImageResource(onboardingItem.getImage());

    }


    }
}
