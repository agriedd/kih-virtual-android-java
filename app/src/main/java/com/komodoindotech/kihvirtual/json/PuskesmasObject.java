package com.komodoindotech.kihvirtual.json;

import com.google.firebase.firestore.GeoPoint;

import java.util.List;

public class PuskesmasObject {
    private String id;
    private String nama;
    private String alamat;
    private GeoPoint lokasi;
    private List<Object> telepon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public GeoPoint getLokasi() {
        return lokasi;
    }

    public void setLokasi(GeoPoint lokasi) {
        this.lokasi = lokasi;
    }

    public List<Object> getTelepon() {
        return telepon;
    }

    public void setTelepon(List<Object> telepon) {
        this.telepon = telepon;
    }
}
