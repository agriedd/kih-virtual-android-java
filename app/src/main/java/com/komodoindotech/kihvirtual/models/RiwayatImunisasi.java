package com.komodoindotech.kihvirtual.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "riwayat_imunisasi")
public class RiwayatImunisasi {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "id_pendaftaran")
    public int id_pendaftaran;

    @ColumnInfo(name = "uid")
    public String uid;
    @ColumnInfo(name = "label")
    public String label;
    @ColumnInfo(name = "value")
    public String value;
    @ColumnInfo(name = "created_at")
    public Long created_at;
    @ColumnInfo(name = "updated_at")
    public Long updated_at;

    public RiwayatImunisasi(String uid, String label) {
        this.uid = uid;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
