package com.komodoindotech.kihvirtual.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;

import java.util.List;

@Dao
public interface RiwayatKeluhanDao {
    @Query("SELECT * FROM riwayat_keluhan")
    LiveData<List<RiwayatKeluhan>> getAll();

    @Query("SELECT * FROM riwayat_keluhan ORDER BY created_at DESC LIMIT 1")
    RiwayatKeluhan getLatest();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RiwayatKeluhan... riwayatKeluhans);

    @Delete
    void delete(RiwayatKeluhan riwayatKehamilan);

    @Query("DELETE FROM riwayat_keluhan")
    void deleteAll();
}
