package com.komodoindotech.kihvirtual.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;

import java.util.List;

@Dao
public interface PendaftaranDao {

    @Query("SELECT * FROM pendaftaran")
    LiveData<List<Pendaftaran>> getAll();

    @Query("SELECT * FROM pendaftaran ORDER BY created_at DESC LIMIT 1")
    PendaftaranDanRiwayat getLatest();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pendaftaran... pendaftarans);

    @Delete
    void delete(Pendaftaran pendaftaran);

    @Query("DELETE FROM pendaftaran")
    void deleteAll();

}
