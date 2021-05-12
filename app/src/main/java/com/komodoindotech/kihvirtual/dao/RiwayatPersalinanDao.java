package com.komodoindotech.kihvirtual.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;

import java.util.List;

@Dao
public interface RiwayatPersalinanDao {
    @Query("SELECT * FROM riwayat_persalinan")
    LiveData<List<RiwayatPersalinan>> getAll();

    @Query("SELECT * FROM riwayat_persalinan ORDER BY created_at DESC LIMIT 1")
    RiwayatKehamilan getLatest();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(RiwayatPersalinan riwayatPersalinan);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertAll(List<RiwayatPersalinan> riwayatPersalinans);

    @Delete
    void delete(RiwayatPersalinan riwayatPersalinan);

    @Query("DELETE FROM riwayat_persalinan")
    void deleteAll();
}
