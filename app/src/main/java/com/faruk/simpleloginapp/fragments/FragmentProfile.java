package com.faruk.simpleloginapp.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.faruk.simpleloginapp.R;
import com.faruk.simpleloginapp.helpers.UserDBHelper;
import com.faruk.simpleloginapp.databinding.FragmentProfileBinding;

import java.util.ArrayList;

public class FragmentProfile extends Fragment {

    private TextView txtFeedBack, txtFeedBackMail;
    private FragmentProfileBinding binding;
    private EditText editTxtKAdi, editTxtParola, editTxtMail, editTxtAd, editTxtSoyad, editTxtAdres, editTxtTelefon, editTxtCinsiyet;
    private UserDBHelper userDb;
    private Button btnGuncelle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtFeedBack = binding.fragmentProfileTxtFeedback;
        txtFeedBackMail = binding.fragmentProfileTxtFeedbackMail;
        editTxtKAdi = binding.fragmentProfileEditTxtkAdi;
        editTxtKAdi.setEnabled(false);
        editTxtParola = binding.fragmentProfileEditTxtParola;
        editTxtMail = binding.fragmentProfileEditTxtMail;
        editTxtAd = binding.fragmentProfileEditTxtad;
        editTxtSoyad = binding.fragmentProfileEditTxtsoyad;
        editTxtAdres = binding.fragmentProfileEditTxtadres;
        editTxtTelefon = binding.fragmentProfileEditTxttelefon;
        editTxtCinsiyet = binding.fragmentProfileEditTxtcinsiyet;
        btnGuncelle = binding.fragmentProfileGuncelle;
        userDb = new UserDBHelper(FragmentProfile.this);
        editTxtKAdi.setText(userDb.getDataFromDatabase("k_adi"));
        editTxtParola.setText(userDb.getDataFromDatabase("parola"));
        editTxtMail.setText(userDb.getDataFromDatabase("mail"));
        editTxtAd.setText(userDb.getDataFromDatabase("ad"));
        editTxtSoyad.setText(userDb.getDataFromDatabase("soyad"));
        editTxtAdres.setText(userDb.getDataFromDatabase("adres"));
        editTxtTelefon.setText(userDb.getDataFromDatabase("telefon"));
        editTxtCinsiyet.setText(userDb.getDataFromDatabase("cinsiyet"));


        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList guncellenenAlanlar = new ArrayList();
                if (editTxtParola.getText().toString().equalsIgnoreCase(userDb.getDataFromDatabase("parola"))) {

                } else {
                    //txtFeedBack.setVisibility(View.VISIBLE);
                    userDb.updateUser("parola", editTxtParola.getText().toString());
                    //txtFeedBack.setBackgroundColor(getResources().getColor(R.color.feedBackColor));
                    //txtFeedBack.setText("Bilgileriniz başarıyla güncellenmiştir.");
                    guncellenenAlanlar.add("parola");
                }
                if (editTxtAdres.getText().toString().equalsIgnoreCase(userDb.getDataFromDatabase("adres"))) {

                } else {
                    //txtFeedBack.setVisibility(View.VISIBLE);
                    userDb.updateUser("adres", editTxtAdres.getText().toString());
                    //txtFeedBack.setBackgroundColor(getResources().getColor(R.color.feedBackColor));
                    //txtFeedBack.setText("Bilgileriniz başarıyla güncellenmiştir.");
                    guncellenenAlanlar.add("adres");
                }
                if (editTxtAd.getText().toString().equalsIgnoreCase(userDb.getDataFromDatabase("ad"))) {

                } else {
                    //txtFeedBack.setVisibility(View.VISIBLE);
                    userDb.updateUser("ad", editTxtAd.getText().toString());
                    //txtFeedBack.setBackgroundColor(getResources().getColor(R.color.feedBackColor));
                    //txtFeedBack.setText("Bilgileriniz başarıyla güncellenmiştir.");
                    guncellenenAlanlar.add("ad");
                }
                if (editTxtSoyad.getText().toString().equalsIgnoreCase(userDb.getDataFromDatabase("soyad"))) {

                } else {
                    //txtFeedBack.setVisibility(View.VISIBLE);
                    userDb.updateUser("soyad", editTxtSoyad.getText().toString());
                    //txtFeedBack.setBackgroundColor(getResources().getColor(R.color.feedBackColor));
                    //txtFeedBack.setText("Bilgileriniz başarıyla güncellenmiştir.");
                    guncellenenAlanlar.add("soyad");
                }

                if (editTxtTelefon.getText().toString().equalsIgnoreCase(userDb.getDataFromDatabase("telefon"))) {

                } else {
                    //txtFeedBack.setVisibility(View.VISIBLE);
                    userDb.updateUser("telefon", editTxtTelefon.getText().toString());
                    //txtFeedBack.setBackgroundColor(getResources().getColor(R.color.feedBackColor));
                    //txtFeedBack.setText("Bilgileriniz başarıyla güncellenmiştir.");
                    guncellenenAlanlar.add("telefon");
                }

                if (editTxtCinsiyet.getText().toString().equalsIgnoreCase(userDb.getDataFromDatabase("cinsiyet"))) {

                } else {
                    //txtFeedBack.setVisibility(View.VISIBLE);
                    userDb.updateUser("cinsiyet", editTxtCinsiyet.getText().toString());
                    //txtFeedBack.setBackgroundColor(getResources().getColor(R.color.feedBackColor));
                    //txtFeedBack.setText("Bilgileriniz başarıyla güncellenmiştir.");
                    guncellenenAlanlar.add("cinsiyet");
                }
                if (editTxtMail.getText().toString().equalsIgnoreCase(userDb.getDataFromDatabase("mail"))) {
                    //txtFeedBack.setVisibility(View.VISIBLE);
                    userDb.updateUser("mail", editTxtMail.getText().toString());
                    //txtFeedBack.setBackgroundColor(getResources().getColor(R.color.feedBackColor));
                    //txtFeedBack.setText("Bilgileriniz başarıyla güncellenmiştir.");
                    guncellenenAlanlar.add("mail");
                } else {
                    txtFeedBackMail.setBackgroundColor(Color.RED);
                    txtFeedBackMail.setText("Mail adresinizi güncellemek için lütfen geçerli bir mail adresi giriniz!");
                }

                if (!guncellenenAlanlar.isEmpty() && guncellenenAlanlar.size()>1) {
                    String alanlar = new String();
                    String ilkHarf, kalan;
                    for (int i = 0; i < guncellenenAlanlar.toArray().length; i++) {
                        alanlar = guncellenenAlanlar.toString();
                    }
                    txtFeedBack.setVisibility(View.VISIBLE);
                    txtFeedBack.setBackgroundColor(getResources().getColor(R.color.feedBackColor));
                    alanlar = alanlar.replace("[", "");
                    alanlar = alanlar.replace("]", "");
                    ilkHarf = alanlar.substring(0, 1).toUpperCase();
                    kalan = alanlar.substring(1);
                    txtFeedBack.setText(ilkHarf + kalan + " bilgileriniz başarıyla güncellenmiştir.");
                    System.out.println(alanlar);
                } else if (!guncellenenAlanlar.isEmpty() && guncellenenAlanlar.size() == 1) {
                    String alanlar = new String();
                    String ilkHarf, kalan;
                    alanlar = guncellenenAlanlar.toString();
                    txtFeedBack.setVisibility(View.VISIBLE);
                    txtFeedBack.setBackgroundColor(getResources().getColor(R.color.feedBackColor));
                    alanlar = alanlar.replace("[", "");
                    alanlar = alanlar.replace("]", "");
                    ilkHarf = alanlar.substring(0, 1).toUpperCase();
                    kalan = alanlar.substring(1);
                    txtFeedBack.setText(ilkHarf + kalan + " bilginiz başarıyla güncellenmiştir.");
                    System.out.println(alanlar);
                }
            }
        });
    }

}