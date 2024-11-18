package com.example.mybestyoutube.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mybestyoutube.models.YoutubeVideo;

import java.util.List;

@Dao
public interface youtubeVideoDAO {

    @Query("SELECT *  FROM YoutubeVideo WHERE id =:id")
    public YoutubeVideo find(Long id);

    @Query("SELECT * FROM YoutubeVideo")
    public List<YoutubeVideo> list();

    @Query("SELECT * FROM YoutubeVideo WHERE favorie = 1")
    public List<YoutubeVideo> listFavoris();
    @Insert
    public void add(YoutubeVideo... YoutubeVideo);
/*
    @Query("SELECT * FROM YoutubeVideo WHERE id=:id")
    public YoutubeVideo delete(Long id);*/
    @Update
    public void update(YoutubeVideo... YoutubeVideo);
    @Delete
    public void delete(YoutubeVideo... YoutubeVideo);
}
