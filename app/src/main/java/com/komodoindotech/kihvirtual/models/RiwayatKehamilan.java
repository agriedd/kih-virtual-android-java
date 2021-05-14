package com.komodoindotech.kihvirtual.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "riwayat_kehamilan")
public class RiwayatKehamilan extends RiwayatContract {

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
    public Boolean value = false;
    @ColumnInfo(name = "created_at")
    public Long created_at;
    @ColumnInfo(name = "updated_at")
    public Long updated_at;

    @Ignore
    public Boolean disabled = false;

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public RiwayatKehamilan(String label, Boolean disabled) {
        this.label = label;
        this.disabled = disabled;
    }

    public RiwayatKehamilan(String uid, String label, int warna, String tindakan) {
        this.uid = uid;
        this.label = label;
        this.warna = warna;
        this.tindakan = tindakan;
    }

    public String getLabel() {
        return label;
    }
}
