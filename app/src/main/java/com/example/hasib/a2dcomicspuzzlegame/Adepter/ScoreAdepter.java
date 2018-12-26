package com.example.hasib.a2dcomicspuzzlegame.Adepter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasib.a2dcomicspuzzlegame.Model.Score;
import com.example.hasib.a2dcomicspuzzlegame.R;

import java.util.ArrayList;
import java.util.List;

public class ScoreAdepter extends RecyclerView.Adapter<ScoreAdepter.ScoreViewHolder> {
    List<Score> score_list=new ArrayList<>();
    Context context;

    public ScoreAdepter(List<Score> score_list, Context context) {
        this.score_list = score_list;
        this.context = context;
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.score_design,null);
        return new ScoreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        Score score=score_list.get(position);

        holder.image.setImageResource(score.getImage());
        holder.playername.setText(score.getPlayerName());
        holder.name.setText(score.getName());
        holder.time.setText(score.getTime()+"s");
    }

    @Override
    public int getItemCount() {
        return score_list.size();
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,playername,time;

        public ScoreViewHolder(View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.score_image);
            name=itemView.findViewById(R.id.score_imageName);
            time=itemView.findViewById(R.id.score_time);
            playername=itemView.findViewById(R.id.score_playerName);
        }
    }
}
