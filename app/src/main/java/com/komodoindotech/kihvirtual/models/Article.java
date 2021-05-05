package com.komodoindotech.kihvirtual.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.alibaba.fastjson.JSON;
import com.komodoindotech.kihvirtual.json.ArticleObject;
import com.komodoindotech.kihvirtual.json.ArticlesObject;

import java.time.OffsetDateTime;
import java.util.Date;

@Entity(tableName = "article")
public class Article {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "json")
    public String json;

    @ColumnInfo(name = "created_at")
    public Long created_at;

    @ColumnInfo(name = "expired_at")
    public Long expired_at;

    @ColumnInfo(name = "updated_at")
    public Long updated_at;

}
