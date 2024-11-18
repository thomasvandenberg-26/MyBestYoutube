package com.example.mybestyoutube.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "YoutubeVideo")
public class YoutubeVideo {




    @PrimaryKey(autoGenerate = true)
    private Long id;


    @ColumnInfo(name="url")
    private String url;

    @ColumnInfo(name="titre")
    private String titre;

    @ColumnInfo(name="description")
    private String description;

    @ColumnInfo(name="categorie")
    private String categorie;

    @ColumnInfo(name="favorie")
    private Integer favorie;

    public YoutubeVideo(){

    }

    public YoutubeVideo(String url, String titre, String description, String categorie, Integer favorie) {
        this.url = url;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.favorie = favorie;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getFavorie() {
        return favorie;
    }

    public void setFavorie(Integer favorie) {
        this.favorie = favorie;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
