package com.faruk.simpleloginapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.faruk.simpleloginapp.fragments.FragmentGuitars;
import com.faruk.simpleloginapp.fragments.FragmentHome;
import com.faruk.simpleloginapp.fragments.FragmentProfile;
import com.faruk.simpleloginapp.R;
import com.faruk.simpleloginapp.helpers.UserDBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    private Button btnCancel, btnQuit;
    private BottomNavigationView bottomNavigationView;
    private Dialog quitDialog;
    private FragmentHome fragmentHome;
    private FragmentProfile fragmentProfile;
    private FragmentGuitars fragmentGuitars;
    private UserDBHelper userDBHelper;
    /*@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.add_brand).setEnabled(false);
        menu.findItem(R.id.add_guitar).setEnabled(false);
        return super.onPrepareOptionsMenu(menu);
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.home_activity_bottomNavigationView);
        fragmentHome = new FragmentHome();
        fragmentProfile = new FragmentProfile();
        fragmentGuitars = new FragmentGuitars();
        userDBHelper = new UserDBHelper(this);
        //gitarTuruHelper.insertData();
        SQLiteDatabase db = this.openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Turler (gitar_turu_id INTEGER PRIMARY KEY AUTOINCREMENT, gitar_turu TEXT)");
        db = this.openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Markalar (marka_id INTEGER PRIMARY KEY AUTOINCREMENT, marka_adi TEXT)");
        String sqlQuery = "INSERT INTO Turler (gitar_turu) VALUES (?)";
        SQLiteStatement statement1 = db.compileStatement(sqlQuery);
        SQLiteStatement statement2 = db.compileStatement(sqlQuery);
        SQLiteStatement statement3 = db.compileStatement(sqlQuery);
        statement1.bindString(1, "Klasik");
        statement2.bindString(1, "Akustik");
        statement3.bindString(1, "Elektro");
        statement1.execute();
        statement2.execute();
        statement3.execute();

        setFragment(fragmentHome);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Cursor cursor = userDBHelper.getReadableDatabase().rawQuery("SELECT * FROM Kullanicilar", null);
                //int kAdiIndex = cursor.getColumnIndex("k_adi");
                //int oturumIndex = cursor.getColumnIndex("oturum");
                /*if (cursor.getInt(oturumIndex) == 1){

                }*/

                switch (item.getItemId()) {
                    case R.id.bottombar_menu_home:
                        setFragment(fragmentHome);
                        return true;

                    case R.id.bottombar_menu_profile:
                        setFragment(fragmentProfile);
                        return true;

                    case R.id.bottombar_menu_guitars:
                        setFragment(fragmentGuitars);
                        return true;

                    case R.id.bottombar_menu_exit:
                        showQuitDialog();

                    default:
                        return false;
                }

            }
        });

        //SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        //Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Kullanicilar", null);


    }

    public void getGitarTuru() {
        SQLiteDatabase db = this.openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Turler", null);
        int idIndex = cursor.getColumnIndex("gitar_turu_id");
        int gitarTuruIndex = cursor.getColumnIndex("gitar_turu");
        while (cursor.moveToNext()) {
            System.out.println(cursor.getInt(idIndex) + " " + cursor.getString(gitarTuruIndex));
        }
        cursor.close();
    }



    private void showQuitDialog() {
        quitDialog = new Dialog(this);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(quitDialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        quitDialog.setContentView(R.layout.customdialog_quit);

        quitDialog.setCancelable(false);

        btnCancel = quitDialog.findViewById(R.id.btnCustomdialog_cancel);
        btnQuit = quitDialog.findViewById(R.id.btnCustomdialog_quit);

        quitDialog.show();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitDialog.dismiss();
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }

    /*@Override
    protected void onDestroy() {
        Cursor cursor = userDBHelper.getWritableDatabase().rawQuery("SELECT * FROM Kullanicilar", null);
        int oturumIndex = cursor.getColumnIndex("oturum");
        while (cursor.moveToNext()) {
            if (oturumIndex == 1){
                userDBHelper.getWritableDatabase().execSQL("UPDATE Kullanicilar SET oturum = 0 WHERE oturum = 1");
            }
        }
        cursor.close();
        super.onDestroy();
    }*/

    private void setFragment(Fragment fragment) { //Bu bizim fragment'leri icerisinde acmamiz gereken metodumuz
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_activity_frameLayout, fragment);
        transaction.commit();
    }

    public void getDataOnClick(View v) {
        getData();
        getGitarTuru();
    }

    /*public void loggedIn(String kullanici) {
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT oturum FROM Kullanicilar", null);
        db.getWritableDatabase().execSQL("UPDATE Kullanicilar SET oturum = 1 WHERE k_adi = " + kullanici);
        cursor.close();
    }*/

    private void getData() {
        Cursor cursor = userDBHelper.getReadableDatabase().rawQuery("SELECT * FROM Kullanicilar", null);
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

        cursor.close();
    }
}