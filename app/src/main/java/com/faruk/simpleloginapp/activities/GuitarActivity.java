package com.faruk.simpleloginapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.faruk.simpleloginapp.entities.Gitar;
import com.faruk.simpleloginapp.GitarAdapter;
import com.faruk.simpleloginapp.GitarDetayi;
import com.faruk.simpleloginapp.R;

public class GuitarActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    static public GitarDetayi gitarDetayi;
    private GitarAdapter gitarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guitar);

        mRecyclerView = findViewById(R.id.activity_guitar_recyclerView);
        gitarAdapter = new GitarAdapter(Gitar.getData(this), this);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(gitarAdapter);
        mRecyclerView.addItemDecoration(new GridManagerDecoration());

        gitarAdapter.setOnItemClickListener(new GitarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Gitar gitar) {
                gitarDetayi = new GitarDetayi(gitar.getGitarAdi(), gitar.getGitarMarka(), gitar.getGitarTuru(), gitar.getGitarAciklama(), gitar.getGitarResim());
                Intent detayIntent = new Intent(GuitarActivity.this, DetailsActivity.class);
                startActivity(detayIntent);
            }
        });

    }

    private class GridManagerDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.bottom = 20;
        }
    }
}