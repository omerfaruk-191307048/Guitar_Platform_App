package com.faruk.simpleloginapp.helpers;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.fragment.app.Fragment;

//myDb.execSQL("CREATE TABLE Kullanicilar (k_id INTEGER PRIMARY KEY AUTOINCREMENT, k_adi VARCHAR, parola VARCHAR, mail VARCHAR, >ad VARCHAR, soyad VARCHAR, adres VARCHAR, telefon VARCHAR, cinsiyet VARCHAR)");
//database.execSQL("INSERT INTO Kullanicilar (k_adi, parola, mail, ad, soyad, adres, telefon, cinsiyet) VALUES ('faruk4153', 'omer2000', 'faruk@gmail.com', 'Ömer Faruk', 'Yılmaz', 'Kocaeli İzmit', '05301121707', 'Erkek')");
//database = this.openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);

public class UserDBHelper extends SQLiteOpenHelper {

    public static final String DBName = "GitarPlatformu";

    public UserDBHelper(Context context) {
        super(context, "GitarPlatformu", null, 1);
        SQLiteDatabase myDb;
        myDb = context.openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS Kullanicilar  (k_id INTEGER PRIMARY KEY AUTOINCREMENT, k_adi TEXT, parola TEXT, mail TEXT, ad TEXT, soyad TEXT, adres TEXT, telefon TEXT, cinsiyet TEXT, oturum INT)");
    }

    public UserDBHelper(Fragment fragment) {
        super(fragment.getContext(), "GitarPlatformu", null, 1);
        SQLiteDatabase myDb;
        myDb = fragment.getContext().openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
    }


    @Override
    public void onCreate(SQLiteDatabase myDb) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase myDb, int oldVersion, int newVersion) {
        myDb.execSQL("DROP TABLE IF EXISTS Kullanicilar");
    }

    public Boolean insertData(String k_adi, String parola, String mail, String ad, String soyad, String adres, String telefon, String cinsiyet, int oturum) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("k_adi", k_adi);
        contentValues.put("parola", parola);
        contentValues.put("mail", mail);
        contentValues.put("ad", ad);
        contentValues.put("soyad", soyad);
        contentValues.put("adres", adres);
        contentValues.put("telefon", telefon);
        contentValues.put("cinsiyet", cinsiyet);
        contentValues.put("oturum", oturum);
        long result = MyDB.insert("Kullanicilar", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public String getDataFromDatabase(String sutun) {
        SQLiteDatabase db = this.getReadableDatabase();
        String result = "";
        int arananIndex;

        Cursor cursor = db.rawQuery("SELECT * FROM Kullanicilar", null);
        // Veritabanı sorgusu
        if (sutun.equals("k_adi")) {

            while (cursor.moveToNext()) {
                arananIndex = cursor.getColumnIndex("k_adi");
                int oturumIndex = cursor.getColumnIndex("oturum");
                if (cursor.getInt(oturumIndex) == 1) {
                    result = cursor.getString(arananIndex);
                    return result;
                }
            }
        } else if (sutun.equals("parola")) {

            while (cursor.moveToNext()) {
                arananIndex = cursor.getColumnIndex("parola");
                int oturumIndex = cursor.getColumnIndex("oturum");
                if (cursor.getInt(oturumIndex) == 1) {
                    result = cursor.getString(arananIndex);
                    return result;
                }
            }
        } else if (sutun.equals("mail")) {

            while (cursor.moveToNext()) {
                arananIndex = cursor.getColumnIndex("mail");
                int oturumIndex = cursor.getColumnIndex("oturum");
                if (cursor.getInt(oturumIndex) == 1) {
                    result = cursor.getString(arananIndex);
                    return result;
                }
            }
        } else if (sutun.equals("ad")) {

            while (cursor.moveToNext()) {
                arananIndex = cursor.getColumnIndex("ad");
                int oturumIndex = cursor.getColumnIndex("oturum");
                if (cursor.getInt(oturumIndex) == 1) {
                    result = cursor.getString(arananIndex);
                    return result;
                }
            }
        } else if (sutun.equals("soyad")) {

            while (cursor.moveToNext()) {
                arananIndex = cursor.getColumnIndex("soyad");
                int oturumIndex = cursor.getColumnIndex("oturum");
                if (cursor.getInt(oturumIndex) == 1) {
                    result = cursor.getString(arananIndex);
                    return result;
                }
            }
        } else if (sutun.equals("adres")) {

            while (cursor.moveToNext()) {
                arananIndex = cursor.getColumnIndex("adres");
                int oturumIndex = cursor.getColumnIndex("oturum");
                if (cursor.getInt(oturumIndex) == 1) {
                    result = cursor.getString(arananIndex);
                    return result;
                }
            }
        } else if (sutun.equals("telefon")) {

            while (cursor.moveToNext()) {
                arananIndex = cursor.getColumnIndex("telefon");
                int oturumIndex = cursor.getColumnIndex("oturum");
                if (cursor.getInt(oturumIndex) == 1) {
                    result = cursor.getString(arananIndex);
                    return result;
                }
            }
        } else if (sutun.equals("cinsiyet")) {

            while (cursor.moveToNext()) {
                arananIndex = cursor.getColumnIndex("cinsiyet");
                int oturumIndex = cursor.getColumnIndex("oturum");
                if (cursor.getInt(oturumIndex) == 1) {
                    result = cursor.getString(arananIndex);
                    return result;
                }
            }
        }


        cursor.close();
        db.close();

        return result;
    }

    public void updateUser(String sutun, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (sutun.equals("parola")) {
            db.execSQL("UPDATE Kullanicilar SET parola = ? WHERE oturum = 1", new String[]{data});
        } else if (sutun.equals("mail")) {
            if (data.contains("@") && data.endsWith(".com")){
                db.execSQL("UPDATE Kullanicilar SET mail = ? WHERE oturum = 1", new String[]{data});
            }
        } else if (sutun.equals("ad")) {
            db.execSQL("UPDATE Kullanicilar SET ad = ? WHERE oturum = 1", new String[]{data});
        } else if (sutun.equals("soyad")) {
            db.execSQL("UPDATE Kullanicilar SET soyad = ? WHERE oturum = 1", new String[]{data});
        } else if (sutun.equals("adres")) {
            db.execSQL("UPDATE Kullanicilar SET adres = ? WHERE oturum = 1", new String[]{data});
        } else if (sutun.equals("telefon")) {
            db.execSQL("UPDATE Kullanicilar SET telefon = ? WHERE oturum = 1", new String[]{data});
        } else if (sutun.equals("cinsiyet")) {
            db.execSQL("UPDATE Kullanicilar SET cinsiyet = ? WHERE oturum = 1", new String[]{data});
        }
    }

    public Boolean checkK_adi(String k_adi) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Kullanicilar WHERE k_adi = ?", new String[]{k_adi});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkKulParola(String k_adi, String parola) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Kullanicilar WHERE k_adi = ? AND parola = ?", new String[]{k_adi, parola});
        if (cursor.getCount() > 0) {
            MyDB.execSQL("UPDATE Kullanicilar SET oturum = 1 WHERE k_adi = ?", new String[]{k_adi});
            return true;
        } else
            return false;
    }

}
