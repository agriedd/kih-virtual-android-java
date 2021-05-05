package com.komodoindotech.kihvirtual.core;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.komodoindotech.kihvirtual.dao.ArticleDao;
import com.komodoindotech.kihvirtual.models.Article;

@Database(entities = {Article.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "kih-virtual")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .allowMainThreadQueries()
//                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
