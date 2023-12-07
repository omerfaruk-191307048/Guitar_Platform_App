package com.faruk.simpleloginapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.faruk.simpleloginapp.R;

public class DetailsActivity extends AppCompatActivity {
    private ImageView imgGitarResim;
    private TextView txtGitarAdi, txtGitarMarkasi, txtGitarTuru, txtGitarAciklama;
    private String gitarAdi, gitarMarkasi, gitarTuru, gitarAciklama;
    private Bitmap gitarResim;

    private void init() {
        imgGitarResim = findViewById(R.id.details_activity_imageGitarResim);
        txtGitarAdi = findViewById(R.id.details_activity_textViewGitarAdi);
        txtGitarMarkasi = findViewById(R.id.details_activity_textViewGitarMarkasi);
        txtGitarTuru = findViewById(R.id.details_activity_textViewGitarTuru);
        txtGitarAciklama = findViewById(R.id.details_activity_textViewGitarAciklama);

        gitarAdi = GuitarActivity.gitarDetayi.getGitarAdi();
        gitarMarkasi = GuitarActivity.gitarDetayi.getGitarMarkasi();
        gitarTuru = GuitarActivity.gitarDetayi.getGitarTuru();
        gitarAciklama = GuitarActivity.gitarDetayi.getGitarAciklama();
        gitarResim = GuitarActivity.gitarDetayi.getGitarResim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();

        if (!TextUtils.isEmpty(gitarAdi) && !TextUtils.isEmpty(gitarMarkasi) && !TextUtils.isEmpty(gitarTuru) && !TextUtils.isEmpty(gitarAciklama)) {
            txtGitarAdi.setText(gitarAdi);
            txtGitarMarkasi.setText(gitarMarkasi);
            txtGitarTuru.setText(gitarTuru);
            txtGitarAciklama.setText(gitarAciklama);
            imgGitarResim.setImageBitmap(gitarResim);
        }
    }
}