package com.komodoindotech.kihvirtual.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "riwayat_kehamilan")
public class RiwayatKehamilan {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "id_pendaftaran")
    public int id_pendaftaran;

    @ColumnInfo(name = "uid")
    public String uid;
    @ColumnInfo(name = "label")
    public String label;
    @ColumnInfo(name = "warna")
    public int warna;
    @ColumnInfo(name = "tindakan")
    public String tindakan;
    @ColumnInfo(name = "value")
    public Boolean value;
    @ColumnInfo(name = "created_at")
    public Long created_at;
    @ColumnInfo(name = "updated_at")
    public Long updated_at;
}
