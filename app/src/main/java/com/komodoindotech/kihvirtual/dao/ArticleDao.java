package com.komodoindotech.kihvirtual.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.komodoindotech.kihvirtual.models.Article;

import java.util.List;

@Dao
public interface  ArticleDao {
    @Query("SELECT * FROM article")
    LiveData<List<Article>> getAll();

    @Query("SELECT * FROM article WHERE expired_at > :time ORDER BY created_at LIMIT 1")
    Article getLatest(long time);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Article... article);

    @Delete
    void delete(Article article);

    @Delete
    void deleteAll(Article... article);
}
