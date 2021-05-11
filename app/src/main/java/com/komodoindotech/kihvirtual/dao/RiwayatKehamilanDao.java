package com.komodoindotech.kihvirtual.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.komodoindotech.kihvirtual.models.Article;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;

import java.util.List;

@Dao
public interface RiwayatKehamilanDao {
    @Query("SELECT * FROM riwayat_kehamilan")
    LiveData<List<RiwayatKehamilan>> getAll();

    @Query("SELECT * FROM riwayat_kehamilan ORDER BY created_at DESC LIMIT 1")
    RiwayatKehamilan getLatest();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RiwayatKehamilan... riwayatKehamilans);

    @Delete
    void delete(RiwayatKehamilan riwayatKehamilan);

    @Query("DELETE FROM riwayat_kehamilan")
    void deleteAll();
}
