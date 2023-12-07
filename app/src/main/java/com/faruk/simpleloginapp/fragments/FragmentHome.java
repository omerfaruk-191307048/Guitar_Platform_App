package com.faruk.simpleloginapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.faruk.simpleloginapp.R;
import com.faruk.simpleloginapp.databinding.FragmentHomeBinding;
import com.faruk.simpleloginapp.databinding.FragmentProfileBinding;

public class FragmentHome extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }


}