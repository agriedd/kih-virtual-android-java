package com.komodoindotech.kihvirtual.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.komodoindotech.kihvirtual.models.RiwayatImunisasi;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;

import java.util.List;

@Dao
public interface RiwayatImunisasiDao {
    @Query("SELECT * FROM riwayat_imunisasi")
    LiveData<List<RiwayatImunisasi>> getAll();

    @Query("SELECT * FROM riwayat_imunisasi ORDER BY created_at DESC LIMIT 1")
    RiwayatImunisasi getLatest();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RiwayatImunisasi... riwayatImunisasis);

    @Delete
    void delete(RiwayatImunisasi riwayatImunisasi);

    @Query("DELETE FROM riwayat_imunisasi")
    void deleteAll();
}
