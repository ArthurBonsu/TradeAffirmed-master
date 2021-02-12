package com.simcoder.bimbo.Admin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.simcoder.bimbo.R;

public class AboutPage extends AppCompatActivity {
    public AboutPage() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.adminaboutpage);
    }
}
