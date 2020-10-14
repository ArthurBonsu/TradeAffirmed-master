package com.simcoder.bimbo.instagram.Home;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.simcoder.bimbo.R;
import com.simcoder.bimbo.WorkActivities.HomeActivity;
import com.simcoder.bimbo.WorkActivities.ProductDetailsActivity;
import com.simcoder.bimbo.instagram.Models.Like;
import com.simcoder.bimbo.instagram.Models.Photo;
import com.simcoder.bimbo.instagram.Models.Tags;
import com.simcoder.bimbo.instagram.Profile.EditProfileFragment.EditProfileFragment;
import com.simcoder.bimbo.instagram.Utils.MainfeedListAdapter;
import com.simcoder.bimbo.instagram.Models.Comment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    //vars
    private ArrayList<Photo> mPhotos;
    private ArrayList<Photo> mPaginatedPhotos;
    private ArrayList<String> mFollowing;
    //LIST VIEW AS THE SUBJECT IS NOT REALLY NECESSARY

    private ListView mListView;
    private MainfeedListAdapter mAdapter;
    private int mResults;
    String followingkey;
    String thePhotosKeykey;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);


        mFollowing = new ArrayList<>();
        mPhotos = new ArrayList<>();
        //   mPhotos = (ArrayList<Photo>) new ArrayList<>();
        //   mPhotos = (ArrayList<Photo>) new ArrayList<>();

        return view;
    }}


// #BuiltByGOD




