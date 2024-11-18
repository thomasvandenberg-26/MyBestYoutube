package com.example.mybestyoutube;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mybestyoutube.Database.youtubeVideoDatabase;
import com.example.mybestyoutube.models.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;

public class AddYoutubeActivity extends AppCompatActivity {
private Toolbar toolbar;
private Button btnAjouter;
private Button btnAnnuler;
private EditText videoName;
private EditText videoDescription;
private EditText videoUrl;
private Spinner spinnerCategorie;
private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_youtube);
        btnAjouter = findViewById(R.id.btnAjouter);
        btnAnnuler = findViewById(R.id.btnAnnuler);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        videoDescription = findViewById(R.id.editDescription);
        videoName = findViewById(R.id.editTitre);
        videoUrl = findViewById(R.id.editUrl);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        spinnerCategorie = findViewById(R.id.spinnerCategories);
        List<String> lesCategories = new ArrayList<>();

        lesCategories.add("Sport");
        lesCategories.add("Music");
        lesCategories.add("Cuisine");
        lesCategories.add("Comedy");
        spinnerCategorie.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lesCategories ));


        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoutubeVideo yv = new YoutubeVideo();
                String strDescription = videoDescription.getText().toString();
                String strVideoName = videoName.getText().toString();
                String strVideoUrl = videoUrl.getText().toString();

                if(!strVideoName.isEmpty() && !strDescription.isEmpty() && !strVideoUrl.isEmpty()) {
                    yv.setTitre(strVideoName);
                    yv.setDescription(strDescription);
                    yv.setUrl(strVideoUrl);
                    yv.setFavorie(0);

                    youtubeVideoDatabase.getDb(AddYoutubeActivity.this).youtubeVideoDAO().add(yv);
                    Toast.makeText(AddYoutubeActivity.this, "Video added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(AddYoutubeActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
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