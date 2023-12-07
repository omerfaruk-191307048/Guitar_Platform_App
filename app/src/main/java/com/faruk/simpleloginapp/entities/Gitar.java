package com.faruk.simpleloginapp.entities;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class Gitar {
    private String gitarAdi, gitarMarka, gitarTuru, gitarAciklama;
    private Bitmap gitarResim;

    public Gitar(String gitarAdi, String gitarAciklama, String gitarTuru, String gitarMarka, Bitmap gitarResim) {
        this.gitarAdi = gitarAdi;
        this.gitarAciklama = gitarAciklama;
        this.gitarTuru = gitarTuru;
        this.gitarMarka = gitarMarka;
        this.gitarResim = gitarResim;
    }

    public Gitar() {

    }

    public String getGitarAdi() {
        return gitarAdi;
    }

    public String getGitarMarka() {
        return gitarMarka;
    }

    public String getGitarTuru() {
        return gitarTuru;
    }
    public String getGitarAciklama() { return gitarAciklama; }

    public Bitmap getGitarResim() {
        return gitarResim;
    }

    public void setGitarAdi(String gitarAdi) {
        this.gitarAdi = gitarAdi;
    }

    public void setGitarMarka(String gitarMarka) {
        this.gitarMarka = gitarMarka;
    }

    public void setGitarTuru(String gitarTuru) {
        this.gitarTuru = gitarTuru;
    }

    public void setGitarAciklama(String gitarAciklama) {
        this.gitarAciklama = gitarAciklama;
    }

    public void setGitarResim(Bitmap gitarResim) {
        this.gitarResim = gitarResim;
    }

    static public ArrayList<Gitar> getData(Context context) {
        ArrayList<Gitar> gitarList = new ArrayList<>();

        ArrayList<String> gitarAdiList = new ArrayList<>();
        ArrayList<String> gitarMarkaList = new ArrayList<>();
        ArrayList<String> gitarTuruList = new ArrayList<>();
        ArrayList<Bitmap> gitarResimList = new ArrayList<>();
        ArrayList<String> gitarAciklamaList = new ArrayList<>();
        String marka = new String();
        String tur = new String();
        try {
            SQLiteDatabase db = context.openOrCreateDatabase("GitarPlatformu", MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("select *, (select marka_adi from markalar where marka_id=gitarlar.marka_id) as marka_adi, (select gitar_turu from turler where gitar_turu_id=gitarlar.gitar_turu_id) as gitar_turu from gitarlar", null);

            int gitarAdiIndex = cursor.getColumnIndex("gitar_adi");
            int gitarAciklamaIndex = cursor.getColumnIndex("gitar_aciklama");
            int gitarMarkaAdiIndex = cursor.getColumnIndex("marka_adi");
            int gitarResimIndex = cursor.getColumnIndex("gitar_resim");
            int gitarTuruAdiIndex = cursor.getColumnIndex("gitar_turu");

            while (cursor.moveToNext()) {
                gitarAdiList.add(cursor.getString(gitarAdiIndex));
                gitarAciklamaList.add(cursor.getString(gitarAciklamaIndex));
                gitarMarkaList.add(cursor.getString(gitarMarkaAdiIndex));
                gitarTuruList.add(cursor.getString(gitarTuruAdiIndex));

                byte[] gelenResimByte = cursor.getBlob(gitarResimIndex);
                Bitmap gelenResim = BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length);
                gitarResimList.add(gelenResim);

            }

            cursor.close();

            for (int i = 0; i < gitarAdiList.size(); i++) {
                Gitar gitar = new Gitar();
                gitar.setGitarAdi(gitarAdiList.get(i));
                gitar.setGitarAciklama(gitarAciklamaList.get(i));
                gitar.setGitarTuru(gitarTuruList.get(i));
                gitar.setGitarMarka(gitarMarkaList.get(i));
                gitar.setGitarResim(gitarResimList.get(i));

                gitarList.add(gitar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gitarList;
    }


}
