package com.faruk.simpleloginapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.faruk.simpleloginapp.helpers.UserDBHelper;
import com.faruk.simpleloginapp.activities.AddBrandActivity;
import com.faruk.simpleloginapp.activities.AddGuitarActivity;
import com.faruk.simpleloginapp.activities.GuitarActivity;
import com.faruk.simpleloginapp.databinding.FragmentGuitarsBinding;

public class FragmentGuitars extends Fragment {
    private Button btnGitarEkle, btnMarkaEkle, btnGoruntule;
    private FragmentGuitarsBinding binding;
    private UserDBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_guitars, container, false);
        binding = FragmentGuitarsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public int getCursorLength() {
        SQLiteDatabase db = this.getActivity().openOrCreateDatabase("GitarPlatformu", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Markalar", null);
        int l = cursor.getCount();
        cursor.close();
        return l;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHelper = new UserDBHelper(FragmentGuitars.this);
        btnGitarEkle = binding.fragmentGuitarBtnGitarEkle;
        btnMarkaEkle = binding.fragmentGuitarBtnMarkaEkle;
        btnGoruntule = binding.fragmentGuitarsBtnGoruntule;

        if (dbHelper.getDataFromDatabase("k_adi").equals("admin")) {
            btnMarkaEkle.setVisibility(View.VISIBLE);
            btnGitarEkle.setVisibility(View.VISIBLE);
        }

        btnGitarEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = getCursorLength();
                if (a == 0) {
                    Toast.makeText(getContext(), "Marka bulunamadı. Gitar ekleyebilmek için önce marka ekleyiniz.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getActivity(), AddGuitarActivity.class);
                    startActivity(intent);
                }
                //getCursorLength();
            }
        });

        btnMarkaEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddBrandActivity.class);
                startActivity(intent);
            }
        });

        btnGoruntule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GuitarActivity.class);
                startActivity(intent);
            }
        });

    }


}