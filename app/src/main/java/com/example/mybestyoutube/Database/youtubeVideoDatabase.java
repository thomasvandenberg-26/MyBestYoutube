package com.example.mybestyoutube.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.mybestyoutube.DAO.youtubeVideoDAO;
import com.example.mybestyoutube.models.YoutubeVideo;

@Database(entities = {YoutubeVideo.class}, version = 1)
public abstract class youtubeVideoDatabase extends RoomDatabase {
        private static youtubeVideoDatabase instance;
    private static final String DATABASE_NAME = "YoutubeVideo";
    public static youtubeVideoDatabase getDb(Context context){
        return Room.databaseBuilder(context, youtubeVideoDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
    }
    public abstract youtubeVideoDAO youtubeVideoDAO();
}
