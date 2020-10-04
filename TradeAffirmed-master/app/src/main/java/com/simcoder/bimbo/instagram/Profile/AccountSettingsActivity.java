package com.simcoder.bimbo.instagram.Profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.simcoder.bimbo.R;
import com.simcoder.bimbo.instagram.Utils.SectionsStatePagerAdapter;
import com.simcoder.bimbo.instagram.Utils.BottomNavigationViewHelper;
import com.simcoder.bimbo.instagram.Utils.FirebaseMethods;

import java.util.ArrayList;

import static com.simcoder.bimbo.R.string.selected_image;

public class AccountSettingsActivity extends AppCompatActivity{

    private static final String TAG = "AccountSettingsActivity";
    public static final int ACTIVITY_NUM = 4;

    private Context mContext;

    public SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        mContext = AccountSettingsActivity.this;
        Log.d(TAG, "onCreate: started");
        mViewPager = (ViewPager) findViewById(R.id.viewpager_container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rel_layout_1);

        setupSettingsList();
        setupBottomNavigationView();
        setupFragments();
        getIncomingIntent();


        /*
          setup back arrow to navigate to "ProfileActivity"
         */
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        if (backArrow != null) {
            backArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: navigating back to profile page");
                    finish();
                }
            });
        }
    }
    private void getIncomingIntent() {
                      if (getIntent() != null){
        Intent intent = getIntent();
        if (intent != null) {
            if (getString(selected_image) != null) {
                if (getString(R.string.selected_bitmap) != null) {
                    if (intent.hasExtra(getString(selected_image))


                            || intent.hasExtra(getString(R.string.selected_bitmap))) {

                        //if there is an imageUrl attached as an extra, then it was chosen from the gallery/photo fragment
                        Log.d(TAG, "getIncomingIntent: New incoming imgUrl");
                        if (getString(R.string.return_to_fragment) != null && getString(R.string.edit_profile_fragment) != null) {
                            if (intent.getStringExtra(getString(R.string.return_to_fragment)).equals(getString(R.string.edit_profile_fragment))) {
                                if (getString(selected_image) != null) {
                                    if (intent.hasExtra(getString(selected_image))) {
                                        //set the new profile picture

                                        FirebaseMethods firebaseMethods = new FirebaseMethods(AccountSettingsActivity.this);

                                        firebaseMethods.uploadNewPhoto(getString(R.string.profile_photo), null, 0,
                                                intent.getStringExtra(getString(selected_image)), null);
                                        if (getString(R.string.selected_bitmap) != null) {
                                        } else if (intent.hasExtra(getString(R.string.selected_bitmap))) {
                                            //set the new profile picture
                                            if (firebaseMethods != null) {
                                               if (intent.getParcelableExtra(getString(R.string.selected_bitmap)) != null){
                                                firebaseMethods.uploadNewPhoto(getString(R.string.profile_photo), null, 0,

                                                        null, (Bitmap) intent.getParcelableExtra(getString(R.string.selected_bitmap)));

                                            }}
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        if (getString(R.string.calling_activity) != null) {
            if (intent.hasExtra(getString(R.string.calling_activity))) {
                Log.d(TAG, "getIncomingIntent: received incoming intent from " + getString(R.string.profile_activity));

                if (getString(R.string.edit_profile_fragment) != null) {
                    if (pagerAdapter.getFragmentNumber(getString(R.string.edit_profile_fragment)) != null) {
                        setViewPager(pagerAdapter.getFragmentNumber(getString(R.string.edit_profile_fragment)));
                    }
                }
            }
        }
    }}
    private void setupFragments() {
        if (getSupportFragmentManager() != null) {
            if (pagerAdapter != null) {
                pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
                pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment));
                pagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment));

            }
        }
    }
    /**
     * responsible for navigation of fragments fragmentNumber
     * @param fragmentNumber
     */
    public void setViewPager(int fragmentNumber) {
        if (mRelativeLayout != null) {
            mRelativeLayout.setVisibility(View.GONE);
            Log.d(TAG, "setViewPager: navigating to fragment #: " + fragmentNumber);
            if (mViewPager != null) {
                mViewPager.setAdapter(pagerAdapter);
                mViewPager.setCurrentItem(fragmentNumber);
            }
        }
    }
    private void setupSettingsList() {
        Log.d(TAG, "setupSettingsList: initializing 'Account Settings' list.");
        ListView listView = (ListView) findViewById(R.id.lvAccountSettings);

        ArrayList<String> options = new ArrayList<>();
        if (options != null) {
            options.add(getString(R.string.edit_profile_fragment)); //fragment 0
            options.add(getString(R.string.sign_out_fragment)); //fragment 1

            ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, options);
            if (listView != null) {
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d(TAG, "onItemClick: navigating to fragment#: " + position);
                        setViewPager(position);
                    }
                });

            }
        }
    }

    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, this, bottomNavigationViewEx);
        if (bottomNavigationViewEx != null) {
            Menu menu = bottomNavigationViewEx.getMenu();
            if (menu != null) {
                MenuItem menuItem = menu.getItem(ACTIVITY_NUM);

                if (menuItem != null) {
                    menuItem.setChecked(true);
                }
            }
        }
    }


}


// #BuiltByGOD