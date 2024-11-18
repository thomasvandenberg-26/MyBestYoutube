package com.example.mybestyoutube;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybestyoutube.Database.youtubeVideoDatabase;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mybestyoutube.models.YoutubeVideo;

public class UpdateVideo extends AppCompatActivity {
public EditText editTitre;
public EditText editDescription;
public EditText editUrl;
public Button btnModifier;
public Button btnAjouterFavoris;
public Button btnAnnuler;
public Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_video);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        editTitre = findViewById(R.id.editTitre);
        editDescription = findViewById(R.id.editDescription);
        editUrl = findViewById(R.id.editUrl);
        btnModifier = findViewById(R.id.btnModifier) ;
        btnAjouterFavoris = findViewById(R.id.btnFavoris);
        btnAnnuler = findViewById(R.id.btnAnnuler);
        Long videoId = getIntent().getLongExtra("VIDEO_ID", -1);

        YoutubeVideo yv = youtubeVideoDatabase.getDb(this).youtubeVideoDAO().find(videoId);

        editTitre.setText(yv.getTitre());
        editDescription.setText(yv.getDescription());
        editUrl.setText(yv.getUrl());
        if(yv.getFavorie()==1)
        {
            btnAjouterFavoris.setText("Supprimer des favoris");
        }



        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yv.setTitre(editTitre.getText().toString());
                yv.setDescription(editDescription.getText().toString());
                yv.setUrl(editUrl.getText().toString());

                youtubeVideoDatabase.getDb(UpdateVideo.this).youtubeVideoDAO().update(yv);
                Toast.makeText(UpdateVideo.this, "Video updated successfully ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btnAjouterFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(yv.getFavorie()==0) {
                    yv.setFavorie(1);
                    youtubeVideoDatabase.getDb(UpdateVideo.this).youtubeVideoDAO().update(yv);
                    Toast.makeText(UpdateVideo.this, "Video added in favoris ", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    yv.setFavorie(0);
                    youtubeVideoDatabase.getDb(UpdateVideo.this).youtubeVideoDAO().update(yv);
                    Toast.makeText(UpdateVideo.this, "Video delete froms favoris ", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }




}