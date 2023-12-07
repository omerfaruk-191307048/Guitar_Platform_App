package com.faruk.simpleloginapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.faruk.simpleloginapp.R;
import com.faruk.simpleloginapp.helpers.UserDBHelper;

public class UyeOl extends AppCompatActivity {

    EditText editTextKullanici, editTextParola, editTextParolaDogrula;
    float parolaSize;
    Button btnUyeOl, btnGirisYap;
    UserDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ol);
        //Intent gelenIntent = getIntent();

        editTextKullanici = findViewById(R.id.uyeol_editTxtKullanici);
        editTextParola = findViewById(R.id.uyeol_editTxtParola);
        editTextParolaDogrula = findViewById(R.id.uyeol_editTxtParolaDogrula);
        btnUyeOl = findViewById(R.id.uyeol_btnUyeOl);
        btnGirisYap = findViewById(R.id.uyeol_btnGirisYap);
        db = new UserDBHelper(this);

        btnUyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kullanici = editTextKullanici.getText().toString();
                String parola = editTextParola.getText().toString();
                String parolaDogrula = editTextParolaDogrula.getText().toString();
                parolaSize = editTextParola.length();
                editTextKullanici = findViewById(R.id.uyeol_editTxtKullanici);
                editTextParola = findViewById(R.id.uyeol_editTxtParola);
                editTextParolaDogrula = findViewById(R.id.uyeol_editTxtParolaDogrula);

                if (kullanici.equals("") || parola.equals("") || parolaDogrula.equals("")) {
                    Toast.makeText(UyeOl.this, "Lütfen bütün alanları doldurun.", Toast.LENGTH_SHORT).show();
                } else {
                    if (parolaSize < 7) {
                        Toast.makeText(UyeOl.this, "Parola alanı 8 karakterden az olamaz.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (parola.equals(parolaDogrula)) {
                            Boolean checkKullanici = db.checkK_adi(kullanici);
                            if (checkKullanici == false) {
                                Boolean insert = db.insertData(kullanici, parola, "", "", "", "", "", "", 1);
                                if (insert == true) {
                                    Toast.makeText(UyeOl.this, "Üye olma işlemi başarılı.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(UyeOl.this, HomeActivity.class);
                                    finish();
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(UyeOl.this, "Üye olma işlemi başarısız.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(UyeOl.this, "Kullanıcı zaten mevcut! Lütfen giriş yapın.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(UyeOl.this, "Parolalar eşleşmiyor!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }


    public void uyeOl_girisOnClick(View v) {

        Intent backIntent = new Intent(UyeOl.this, MainActivity.class);
        finish();
        startActivity(backIntent);

    }

}