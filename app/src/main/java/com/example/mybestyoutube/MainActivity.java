package com.example.mybestyoutube;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybestyoutube.Database.youtubeVideoDatabase;
import com.example.mybestyoutube.models.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private RecyclerView recyclerView;
    private TextView tvVideoName;
    private List<YoutubeVideo> youtubeVideoList;
    private Toolbar toolbar;
    private VideoAdapter videoAdapter;
    private Button buttonRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonRefresh = findViewById(R.id.btnRafraichir);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        recyclerView= findViewById(R.id.recyclerViewYT);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        youtubeVideoList = youtubeVideoDatabase.getDb(this).youtubeVideoDAO().list();

        videoAdapter = new VideoAdapter(this , youtubeVideoList);
        recyclerView.setAdapter(videoAdapter);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshData();
            }
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        refreshData();
    }
    private void refreshData(){
         List<YoutubeVideo> newVideoList = youtubeVideoDatabase.getDb(this).youtubeVideoDAO().list();
         youtubeVideoList.clear();
         youtubeVideoList.addAll(newVideoList);
         videoAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.AddVideo) {
            Intent ajouterVideo = new Intent(getApplicationContext(),AddYoutubeActivity.class);
            startActivity(ajouterVideo);
            // Ferme l'activité actuelle et retourne à l'écran précédent
            return true;
        }
        if(item.getItemId() == R.id.FiltreListe)
        {
            List<YoutubeVideo> favorisList = youtubeVideoDatabase.getDb(this).youtubeVideoDAO().listFavoris();
            youtubeVideoList.clear();
            youtubeVideoList.addAll(favorisList);
            videoAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }


}
