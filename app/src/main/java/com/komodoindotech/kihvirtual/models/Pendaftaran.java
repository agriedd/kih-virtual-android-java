package com.komodoindotech.kihvirtual.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pendaftaran")
public class Pendaftaran {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "uid")
    public String uid;

    @ColumnInfo(name = "user_id")
    public String user_id;

    @ColumnInfo(name = "nama")
    public String nama;

    @ColumnInfo(name = "alamat")
    public String alamat;
    @ColumnInfo(name = "pendidikan_istri")
    public String pendidikan_istri;
    @ColumnInfo(name = "pendidikan_suami")
    public String pendidikan_suami;
    @ColumnInfo(name = "pekerjaan_istri")
    public String pekerjaan_istri;
    @ColumnInfo(name = "pekerjaan_suami")
    public String pekerjaan_suami;
    @ColumnInfo(name = "umur")
    public Integer umur;
    @ColumnInfo(name = "hamil_ke")
    public Integer hamil_ke;
    @ColumnInfo(name = "usia_anak_terakhir")
    public Integer usia_anak_terakhir;
    @ColumnInfo(name = "lama_menikah")
    public Integer lama_menikah;
    @ColumnInfo(name = "haid_terakhir")
    public Long haid_terakhir;

    @ColumnInfo(name = "status")
    public Boolean status = false;

    @ColumnInfo(name = "created_at")
    public Long created_at;
    @ColumnInfo(name = "updated_at")
    public Long updated_at;

}
