package com.faruk.simpleloginapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.faruk.simpleloginapp.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AddGuitarActivity extends AppCompatActivity {
    private ImageView imgGitarResim;
    private EditText editTxtGitarAdi, editTxtGitarAciklama;
    private TextView spinnerSelected;
    private Spinner spinnerMarka;
    private String gitarAdi, gitarAciklama, gitarMarka;
    private ArrayList markaList = new ArrayList<>();
    private int imgIzinAlmaKodu = 0, imgIzinVerildiKodu = 1;
    private Bitmap secilenResim, kucultulenResim;
    private RadioButton radioBtnKlasik, radioBtnAkustik, radioBtnElektro;
    private Button btnKaydet;

    private void init() {
        imgGitarResim = findViewById(R.id.add_guitar_activity_imageViewGitarResmi);
        editTxtGitarAdi = findViewById(R.id.add_guitar_activity_editTextGitarIsmi);
        editTxtGitarAciklama = findViewById(R.id.add_guitar_activity_editGitarAciklama);
        spinnerMarka = findViewById(R.id.add_guitar_activity_spinnerMarka);
        radioBtnKlasik = findViewById(R.id.add_guitar_activity_radioBtnKlasik);
        radioBtnAkustik = findViewById(R.id.add_guitar_activity_radioBtnAkustik);
        radioBtnElektro = findViewById(R.id.add_guitar_activity_radioBtnElektro);
        btnKaydet = findViewById(R.id.add_guitar_activity_btnKaydet);
        spinnerSelected = findViewById(R.id.activity_add_guitar_spinnerSelected);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == imgIzinVerildiKodu) {
            if (resultCode == RESULT_OK && data != null) {
                Uri o = data.getData();
                try {
                    if (Build.VERSION.SDK_INT >= 28) {
                        ImageDecoder.Source resimSource = ImageDecoder.createSource(this.getContentResolver(), o);
                        secilenResim = ImageDecoder.decodeBitmap(resimSource);
                        imgGitarResim.setImageBitmap(secilenResim);
                    } else {
                        secilenResim = MediaStore.Images.Media.getBitmap(this.getContentResolver(), o);
                        imgGitarResim.setImageBitmap(secilenResim);
                    }
                    btnKaydet.setEnabled(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guitar);
        SQLiteDatabase db;
        db = this.openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Gitarlar (gitar_id INTEGER PRIMARY KEY AUTOINCREMENT, gitar_adi TEXT, gitar_aciklama TEXT, gitar_turu_id INTEGER, marka_id INTEGER, gitar_resim BLOB, FOREIGN KEY (gitar_turu_id) REFERENCES Turler (gitar_turu_id), FOREIGN KEY (marka_id) REFERENCES Markalar (marka_id))");
        init();
        getMarksToSpinner();
        //db = new GitarDBHelper(this);



    }


    public void kitapKaydetOnClick(View v) {
        gitarAdi = editTxtGitarAdi.getText().toString();
        gitarAciklama = editTxtGitarAciklama.getText().toString();
        gitarMarka = spinnerMarka.getSelectedItem().toString();
        radioBtnKlasik = findViewById(R.id.add_guitar_activity_radioBtnKlasik);
        radioBtnAkustik = findViewById(R.id.add_guitar_activity_radioBtnAkustik);
        radioBtnElektro = findViewById(R.id.add_guitar_activity_radioBtnElektro);
        if (!TextUtils.isEmpty(gitarAdi)) {
            if (!TextUtils.isEmpty(gitarAciklama)) {
                if (!radioBtnKlasik.isChecked() && !radioBtnAkustik.isChecked() && !radioBtnElektro.isChecked()) {
                    showToast("Gitar türlerinden birini seçmelisiniz.");
                } else {
                    int marka_id = 2;
                    int gitar_turu_id = 1;
                    gitarMarka = spinnerMarka.getSelectedItem().toString();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    kucultulenResim = resmiKucult(secilenResim);
                    kucultulenResim.compress(Bitmap.CompressFormat.PNG, 75, outputStream);
                    byte[] kayitEdilecekResim = outputStream.toByteArray();
                    try {
                        if (radioBtnKlasik.isChecked()) {
                            gitar_turu_id = 1;
                        } else if (radioBtnAkustik.isChecked()) {
                            gitar_turu_id = 2;
                        } else if (radioBtnElektro.isChecked()) {
                            gitar_turu_id = 3;
                        }

                        SQLiteDatabase db = this.openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
                        Cursor cursor = db.rawQuery("SELECT * FROM Markalar", null);
                        int markaIsmiId = cursor.getColumnIndex("marka_adi");
                        while (cursor.moveToNext()) {
                            if (cursor.getString(markaIsmiId).equals(gitarMarka)) {
                                int markaId = cursor.getColumnIndex("marka_id");
                                marka_id = cursor.getInt(markaId);
                            }
                        }
                        cursor.close();
                        String sqlQuery = "INSERT INTO Gitarlar (gitar_adi, gitar_aciklama, gitar_turu_id, marka_id, gitar_resim) VALUES (?, ?, ?, ?, ?)";

                        SQLiteStatement statement = db.compileStatement(sqlQuery);
                        statement.bindString(1, gitarAdi);
                        statement.bindString(2, gitarAciklama);
                        statement.bindLong(3, gitar_turu_id);
                        statement.bindLong(4, marka_id);
                        statement.bindBlob(5, kayitEdilecekResim);
                        statement.execute();
                        nesneleriTemizle();
                        showToast("Gitar başarıyla eklendi.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("catch", "try gecersiz" + e);
                    }
                }
            } else
                showToast("Gitar açıklaması boş olamaz.");
        } else
            showToast("Gitar adı boş olamaz.");
    }

    public void showToast(String mesaj) {
        Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();
    }

    public void getDataOnClick(View v) {
        getData();
    }
    //gitar_id , gitar_adi TEXT, gitar_aciklama , gitar_turu_id , marka_id , gitar_resim ,  (gitar_turu_id (gitar_turu_id),  marka_id
    public void getData() {
        SQLiteDatabase db = openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Gitarlar", null);
        int gitarIdIndex = cursor.getColumnIndex("gitar_id");
        int gitarAdiIndex = cursor.getColumnIndex("gitar_adi");
        int gitarAciklamaIndex = cursor.getColumnIndex("gitar_aciklama");
        int gitarTuruIndex = cursor.getColumnIndex("gitar_turu_id");
        int markaIndex = cursor.getColumnIndex("marka_id");
        int gitarResimIndex = cursor.getColumnIndex("gitar_resim");
        while (cursor.moveToNext()) {
            if (!cursor.isNull(gitarIdIndex)){
                System.out.println(cursor.getInt(gitarIdIndex) + " Gitar adı: " + cursor.getString(gitarAdiIndex) + " Gitar açıklama: " + cursor.getString(gitarAciklamaIndex) + " Gitar türü: " + cursor.getInt(gitarTuruIndex) + " Gitar markası: " + cursor.getInt(markaIndex) + " " + cursor.getBlob(gitarResimIndex));
            }
        }
        cursor.close();
    }

    public void nesneleriTemizle() {
        editTxtGitarAdi.setText("");
        editTxtGitarAciklama.setText("");
        radioBtnElektro.setChecked(false);
        radioBtnAkustik.setChecked(false);
        radioBtnKlasik.setChecked(false);
        imgGitarResim.setImageBitmap(null);
        imgGitarResim.setBackgroundResource(R.drawable.sec);
        btnKaydet.setEnabled(false);
    }

    public void getMarksToSpinner() {
        SQLiteDatabase db;
        db = this.openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Markalar", null);
        int markaAdiIndex = cursor.getColumnIndex("marka_adi");
        cursor.moveToFirst();
        if (cursor.getCount() != 1) {
            do {
                markaList.add(cursor.getString(markaAdiIndex));
                System.out.println(markaAdiIndex);
            } while (cursor.moveToNext());
        } else if (cursor.getCount() == 1) {
            markaList.add(cursor.getString(markaAdiIndex));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddGuitarActivity.this,
                android.R.layout.simple_spinner_dropdown_item, markaList);
        spinnerMarka.setAdapter(adapter);
        cursor.close();
    }

    private Bitmap resmiKucult(Bitmap resim) {
        return Bitmap.createScaledBitmap(resim, 120, 150, true);
    }

    public void resimSec(View v) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, imgIzinAlmaKodu);
        } else {
            Intent resimiAl = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //bana bir tane URI döndür
            startActivityForResult(resimiAl, imgIzinVerildiKodu);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == imgIzinAlmaKodu) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { //kullanici izni vermisse
                Intent resimiAl = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //bana bir tane URI döndür
                startActivityForResult(resimiAl, imgIzinVerildiKodu);
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}