package com.faruk.simpleloginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faruk.simpleloginapp.entities.Gitar;

import java.util.ArrayList;

public class GitarAdapter extends RecyclerView.Adapter<GitarAdapter.GitarHolder> {
    private ArrayList<Gitar> gitarList;
    private Context context;
    private OnItemClickListener listener;
    public GitarAdapter(ArrayList<Gitar> gitarList, Context context) {
        this.gitarList = gitarList;
        this.context = context;
    }

    @NonNull
    @Override
    public GitarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.gitar_item, parent, false);
        return new GitarHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GitarHolder holder, int position) {
        Gitar gitar = gitarList.get(position);
        holder.setData(gitar);
    }


    @Override
    public int getItemCount() {
        return gitarList.size();
    }

    class GitarHolder extends RecyclerView.ViewHolder {
        TextView txtGitarAdi, txtGitarMarkasi, txtGitarTuru, txtGitarAciklama;
        ImageView imgGitarResim;
        Button btnGitarDetay;
        public GitarHolder(@NonNull View itemView) {
            super(itemView);

            txtGitarAdi = itemView.findViewById(R.id.gitar_item_txtViewGitarAdi);
            txtGitarMarkasi = itemView.findViewById(R.id.gitar_item_txtViewGitarMarkasi);
            txtGitarTuru = itemView.findViewById(R.id.gitar_item_txtViewGitarTuru);
            txtGitarAciklama = itemView.findViewById(R.id.gitar_item_txtViewGitarAciklama);
            imgGitarResim = itemView.findViewById(R.id.gitar_item_imageViewGitarResim);
            btnGitarDetay = itemView.findViewById(R.id.gitar_item_btnDetay);

            btnGitarDetay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(gitarList.get(position));
                }
            });

        }

        public void setData(Gitar gitar) {
            this.txtGitarAdi.setText(gitar.getGitarAdi());
            this.txtGitarMarkasi.setText(gitar.getGitarMarka());
            this.txtGitarTuru.setText(gitar.getGitarTuru());
            this.txtGitarAciklama.setText(gitar.getGitarAciklama());
            this.imgGitarResim.setImageBitmap(gitar.getGitarResim());
        }


    }
    public interface OnItemClickListener {
        void onItemClick(Gitar gitar);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
 }
