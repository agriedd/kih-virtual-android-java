package com.komodoindotech.kihvirtual.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
    public Integer hamil_ke = 1;
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
    @ColumnInfo(name = "cid")
    public String cid;
    @ColumnInfo(name = "kesimpulan")
    public Integer kesimpulan;

    @Ignore
    public Boolean isError() {
        boolean valid = true;
        if(nama == null || nama.trim().length() <= 0) valid = false;
        else if(alamat == null || alamat.trim().length() <= 0) valid = false;
        else if(pendidikan_istri == null || pendidikan_istri.trim().length() <= 0) valid = false;
        else if(pendidikan_suami == null || pendidikan_suami.trim().length() <= 0) valid = false;
        else if(pekerjaan_istri == null || pekerjaan_istri.trim().length() <= 0) valid = false;
        else if(pekerjaan_suami == null || pekerjaan_suami.trim().length() <= 0) valid = false;
        else if(umur == null || umur <= 0) valid = false;
        else if(hamil_ke == null || hamil_ke <= 0) valid = false;
        else if(haid_terakhir == null) valid = false;
        else if(lama_menikah == null || lama_menikah < 0) valid = false;
        return valid;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPendidikan_istri() {
        return pendidikan_istri;
    }

    public void setPendidikan_istri(String pendidikan_istri) {
        this.pendidikan_istri = pendidikan_istri;
    }

    public String getPendidikan_suami() {
        return pendidikan_suami;
    }

    public void setPendidikan_suami(String pendidikan_suami) {
        this.pendidikan_suami = pendidikan_suami;
    }

    public String getPekerjaan_istri() {
        return pekerjaan_istri;
    }

    public void setPekerjaan_istri(String pekerjaan_istri) {
        this.pekerjaan_istri = pekerjaan_istri;
    }

    public String getPekerjaan_suami() {
        return pekerjaan_suami;
    }

    public void setPekerjaan_suami(String pekerjaan_suami) {
        this.pekerjaan_suami = pekerjaan_suami;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public Integer getHamil_ke() {
        return hamil_ke;
    }

    public void setHamil_ke(Integer hamil_ke) {
        this.hamil_ke = hamil_ke;
    }

    public Integer getUsia_anak_terakhir() {
        return usia_anak_terakhir;
    }

    public void setUsia_anak_terakhir(Integer usia_anak_terakhir) {
        this.usia_anak_terakhir = usia_anak_terakhir;
    }

    public Integer getLama_menikah() {
        return lama_menikah;
    }

    public void setLama_menikah(Integer lama_menikah) {
        this.lama_menikah = lama_menikah;
    }

    public Long getHaid_terakhir() {
        return haid_terakhir;
    }

    public void setHaid_terakhir(Long haid_terakhir) {
        this.haid_terakhir = haid_terakhir;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Long updated_at) {
        this.updated_at = updated_at;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getKesimpulan() {
        return kesimpulan;
    }

    public void setKesimpulan(Integer kesimpulan) {
        this.kesimpulan = kesimpulan;
    }
}
