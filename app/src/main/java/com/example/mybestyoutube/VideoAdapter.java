package com.example.mybestyoutube;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mybestyoutube.Database.youtubeVideoDatabase;
import com.example.mybestyoutube.models.YoutubeVideo;

import java.util.List;

public class VideoAdapter  extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private Context context;
    private List<YoutubeVideo> youtubeVideoList;
    public class VideoViewHolder extends RecyclerView.ViewHolder{
        public TextView tvVideoName;
        public TextView tvVideoDescription;
        public TextView tvVideoUrl;
        public  VideoViewHolder(View itemView){
            super(itemView);
            tvVideoName = itemView.findViewById(R.id.tvVideoName);
            tvVideoDescription = itemView.findViewById(R.id.tvVideoDescription);

        }
    }
    public VideoAdapter(Context context, List<YoutubeVideo> youtubeVideoList){
        this.context = context;
        this.youtubeVideoList = youtubeVideoList;
    }
    @Override
    public  VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_yt, parent, false);
        return new VideoViewHolder(view);
    }
    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position)
    {
        YoutubeVideo youtubeVideo = youtubeVideoList.get(position);
        holder.tvVideoName.setText(youtubeVideo.getTitre());
        holder.tvVideoDescription.setText(youtubeVideo.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("VIDEO_ID", youtubeVideo.getId());
                context.startActivity(intent);

            }
        });


    }
    @Override public int getItemCount(){
        return youtubeVideoList.size();
    }
}
