package com.example.mybestyoutube;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mybestyoutube.Database.youtubeVideoDatabase;
import com.example.mybestyoutube.UpdateVideo;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mybestyoutube.models.YoutubeVideo;

public class DetailActivity extends AppCompatActivity {
    private Context context;
private Button btnVoir, btnSupprimer;
    private TextView tvTitle, tvDescription, tvUrl;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

         btnVoir = findViewById(R.id.btnVoir);
         btnSupprimer = findViewById(R.id.btnSupprimer);
         tvTitle = findViewById(R.id.tvVideoName);
         tvDescription = findViewById(R.id.tvVideoDescription);
         tvUrl = findViewById(R.id.tvVideoUrl);
         toolbar = findViewById(R.id.toolbar);
 context = this;
        setSupportActionBar(toolbar);

        // Activer le bouton de navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

         Long videoId = getIntent().getLongExtra("VIDEO_ID", -1);

         if(videoId != -1) {
             YoutubeVideo youtubeVideo = youtubeVideoDatabase.getDb(this).youtubeVideoDAO().find(videoId);

             if(youtubeVideo != null)
             {
                 tvTitle.setText(youtubeVideo.getTitre());
                 tvDescription.setText(youtubeVideo.getDescription());
                 tvUrl.setText(youtubeVideo.getUrl());
                 String url = youtubeVideo.getUrl();

             btnVoir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    try {
                        DetailActivity.this.startActivity(webIntent);
                    } catch (ActivityNotFoundException ex) {
                    }
                }
            });
            btnSupprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    youtubeVideoDatabase.getDb(context).youtubeVideoDAO().delete(youtubeVideo);
                    finish();
                }
            });



         }
    }
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_update, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.update) {
            Intent mettreAJourVideo = new Intent(DetailActivity.this,UpdateVideo.class);
            Long videoId = getIntent().getLongExtra("VIDEO_ID", -1);
            mettreAJourVideo.putExtra("VIDEO_ID", videoId);
            startActivity(mettreAJourVideo);
            // Ferme l'activité actuelle et retourne à l'écran précédent
            return true;
        }
        else
             finish();
        return true;
    }

}