package com.faruk.simpleloginapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.faruk.simpleloginapp.R;
import com.faruk.simpleloginapp.helpers.UserDBHelper;

public class MainActivity extends AppCompatActivity {

    TextView txtViewYanlisGiris;
    Button btnGiris, btnUyeOl, btnForgotPass;
    EditText editTextKullanici, editTextParola;
    UserDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextKullanici = findViewById(R.id.main_activity_editTxtKullanici);
        editTextParola = findViewById(R.id.main_activity_editTxtParola);
        txtViewYanlisGiris = findViewById(R.id.main_activity_yanlisGirisUyari);
        btnGiris = findViewById(R.id.main_activity_btnGiris);
        btnUyeOl = findViewById(R.id.main_activity_btnUyeOl);
        btnForgotPass = findViewById(R.id.main_activity_btnForgotPassword);
        db = new UserDBHelper(this);
        destroyGitarTuru();

        //Cursor cursor = db.getWritableDatabase().rawQuery("SELECT * FROM Kullanici", null);
        //int oturumIndex = cursor.getColumnIndex("oturum");
        db.getWritableDatabase().execSQL("UPDATE Kullanicilar SET oturum = 0 WHERE oturum = 1");
    }

    public void checkOnClick(View v) {
        getData();
    }

    public void tabloSilOnClick(View v) {
        tabloyuSil();
    }

    public void forgotPasswordGirisOnClick(View v) {

    }

    public void destroyGitarTuru() {
        SQLiteDatabase db = this.openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS Turler");
    }

    public void girisOnClick(View v) {
        String kullanici = editTextKullanici.getText().toString();
        String parola = editTextParola.getText().toString();
        if (kullanici.equals("") || parola.equals("")) {
            //Toast.makeText(this, "Lütfen bütün alanları doldurun.", Toast.LENGTH_SHORT).show();
            txtViewYanlisGiris.setText("Lütfen bütün alanları doldurun.");
        }
        else {
            Boolean checkKulParola = db.checkKulParola(kullanici, parola);
            if (checkKulParola == true) {
                Toast.makeText(this, "Giriş işlemi başarılı.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                //loggedIn(kullanici);
                finish();
                startActivity(intent);
            } else {
                //Toast.makeText(this, "Kimlik bilgileri hatalı.", Toast.LENGTH_SHORT).show();
                txtViewYanlisGiris.setText("Kimlik bilgileri hatalı.");
            }
        }
    }

    /*public void loggedIn(String kullanici) {
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT oturum FROM Kullanicilar", null);
        db.getWritableDatabase().execSQL("UPDATE Kullanicilar SET oturum = 1 WHERE k_adi = " + kullanici);
        cursor.close();
    }*/


    private void tabloyuSil() {
        db.getWritableDatabase().execSQL("DROP TABLE Kullanicilar");
    }

    private void getData() {
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM Kullanicilar", null);
        int kIdIndex = cursor.getColumnIndex("k_id");
        int kAdiIndex = cursor.getColumnIndex("k_adi");
        int parolaIndex = cursor.getColumnIndex("parola");
        int mailIndex = cursor.getColumnIndex("mail");
        int adIndex = cursor.getColumnIndex("ad");
        int soyadIndex = cursor.getColumnIndex("soyad");
        int adresIndex = cursor.getColumnIndex("adres");
        int telIndex = cursor.getColumnIndex("telefon");
        int cinsiyetIndex = cursor.getColumnIndex("cinsiyet");
        int oturumIndex = cursor.getColumnIndex("oturum");
        /*contentValues.put("mail", mail);
        contentValues.put("ad", ad);
        contentValues.put("soyad", soyad);
        contentValues.put("adres", adres);
        contentValues.put("telefon", telefon);
        contentValues.put("cinsiyet", cinsiyet);*/

        while (cursor.moveToNext()) {
            if (!cursor.isNull(kAdiIndex)) {
                System.out.println(cursor.getInt(kIdIndex) + "Kullanıcı adı: " + cursor.getString(kAdiIndex) + " Parola: " + cursor.getString(parolaIndex) + " Mail" + cursor.getString(mailIndex) + " Ad: " + cursor.getString(adIndex) + " Soyad:" + cursor.getString(soyadIndex) + " Adres: " + cursor.getString(adresIndex) + " Telefon: " + cursor.getString(telIndex) + " Cinsiyet: " + cursor.getString(cinsiyetIndex) + " Oturum: " + cursor.getInt(oturumIndex));
            } else {
                System.out.println("Kayıt bulunamadı.");
            }
        }

        String kAdi = db.getDataFromDatabase("k_adi");
        System.out.println(kAdi);

        cursor.close();
    }



    public void uyeOlOnClick(View v) {
        Intent intent = new Intent(MainActivity.this, UyeOl.class);
        finish();
        startActivity(intent);
    }

}