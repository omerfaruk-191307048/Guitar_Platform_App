package com.faruk.simpleloginapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.faruk.simpleloginapp.R;

public class AddBrandActivity extends AppCompatActivity {

    private EditText editTxtMarka;
    private String markaAdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_brand);
        editTxtMarka = findViewById(R.id.add_brand_activity_editTextGitarMarka);



    }

    public void markaKaydetOnClick(View v) {
        markaAdi = editTxtMarka.getText().toString();
        if (!TextUtils.isEmpty(markaAdi)) {
            SQLiteDatabase db = this.openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
            String sqlQuery = "INSERT INTO Markalar (marka_adi) VALUES (?)";

            SQLiteStatement statement = db.compileStatement(sqlQuery);
            statement.bindString(1, markaAdi);
            //db.execSQL();
            statement.execute();

            editTxtMarka.setText("");
            Toast.makeText(this, "Marka başarıyla eklendi.", Toast.LENGTH_SHORT).show();
        }
    }

    public void getData() {
        SQLiteDatabase db = this.openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Markalar", null);
        int markaIdIndex = cursor.getColumnIndex("marka_id");
        int markaAdiIndex = cursor.getColumnIndex("marka_adi");

        while (cursor.moveToNext()) {
            if (!cursor.isNull(markaIdIndex)) {
                System.out.println(cursor.getInt(markaIdIndex) + " " + cursor.getString(markaAdiIndex));
            } else {
                System.out.println("Kayıt bulunamadı.");
            }
        }
        cursor.close();
    }

    public void getDataOnClick(View v) {
        getData();
    }

}