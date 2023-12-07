package com.faruk.simpleloginapp.helpers;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;

import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;

public class GitarDBHelper extends SQLiteOpenHelper {
    public static final String DBName = "GitarPlatformu";

    public GitarDBHelper(Context context) {
        super(context, DBName, null, 1);

    }


    public GitarDBHelper(Fragment fragment) {
        super(fragment.getContext(), DBName, null, 1);
        SQLiteDatabase db;
        db = fragment.getContext().openOrCreateDatabase(DBName, MODE_PRIVATE, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Gitarlar");
    }

    public void insertData(String gitar_adi, String gitar_aciklama, Integer gitar_turu_id, Integer marka_id, byte[] kayitEdilecekResim, Bitmap kucultulenResim) {

    }
}
