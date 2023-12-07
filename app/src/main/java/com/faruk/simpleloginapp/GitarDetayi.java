package com.faruk.simpleloginapp;

import android.graphics.Bitmap;

public class GitarDetayi {
    private String gitarAdi, gitarMarkasi, gitarTuru, gitarAciklama;
    private Bitmap gitarResim;

    public GitarDetayi(String gitarAdi, String gitarMarkasi, String gitarTuru, String gitarAciklama, Bitmap gitarResim) {
        this.gitarAdi = gitarAdi;
        this.gitarMarkasi = gitarMarkasi;
        this.gitarTuru = gitarTuru;
        this.gitarAciklama = gitarAciklama;
        this.gitarResim = gitarResim;
    }

    public String getGitarAdi() {
        return gitarAdi;
    }

    public String getGitarMarkasi() {
        return gitarMarkasi;
    }

    public String getGitarTuru() {
        return gitarTuru;
    }

    public String getGitarAciklama() {
        return gitarAciklama;
    }

    public Bitmap getGitarResim() {
        return gitarResim;
    }
}
