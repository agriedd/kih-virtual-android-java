package com.komodoindotech.kihvirtual.json;

public class PendaftaranObject {

    private String id;
    private String nama, alamat, pendidikan_istri, pendidikan_suami,
            pekerjaan_istri, pekerjaan_suami;
    private Integer umur, hamil_ke, usia_anak_terakhir, lama_menikah;
    private Long haid_terakhir;

    private Boolean status_riwayat_kehamilan;
    private Boolean status_riwayat_persalinan;

    private String riwayat_kehamilan_json, riwayat_persalinan_json, riwayat_imunisasi_tt_json, keluhan_json;

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

    public Boolean getStatus_riwayat_kehamilan() {
        return status_riwayat_kehamilan;
    }

    public void setStatus_riwayat_kehamilan(Boolean status_riwayat_kehamilan) {
        this.status_riwayat_kehamilan = status_riwayat_kehamilan;
    }

    public Boolean getStatus_riwayat_persalinan() {
        return status_riwayat_persalinan;
    }

    public void setStatus_riwayat_persalinan(Boolean status_riwayat_persalinan) {
        this.status_riwayat_persalinan = status_riwayat_persalinan;
    }

    public String getRiwayat_kehamilan_json() {
        return riwayat_kehamilan_json;
    }

    public void setRiwayat_kehamilan_json(String riwayat_kehamilan_json) {
        this.riwayat_kehamilan_json = riwayat_kehamilan_json;
    }

    public String getRiwayat_persalinan_json() {
        return riwayat_persalinan_json;
    }

    public void setRiwayat_persalinan_json(String riwayat_persalinan_json) {
        this.riwayat_persalinan_json = riwayat_persalinan_json;
    }

    public String getRiwayat_imunisasi_tt_json() {
        return riwayat_imunisasi_tt_json;
    }

    public void setRiwayat_imunisasi_tt_json(String riwayat_imunisasi_tt_json) {
        this.riwayat_imunisasi_tt_json = riwayat_imunisasi_tt_json;
    }

    public String getKeluhan_json() {
        return keluhan_json;
    }

    public void setKeluhan_json(String keluhan_json) {
        this.keluhan_json = keluhan_json;
    }

    public Boolean isError() {
        Boolean valid = true;
        if(nama == null || nama.trim().length() <= 0) valid = false;
        else if(alamat == null || alamat.trim().length() <= 0) valid = false;
        else if(pendidikan_istri == null || pendidikan_istri.trim().length() <= 0) valid = false;
        else if(pendidikan_suami == null || pendidikan_suami.trim().length() <= 0) valid = false;
        else if(pekerjaan_istri == null || pekerjaan_istri.trim().length() <= 0) valid = false;
        else if(pekerjaan_suami == null || pekerjaan_suami.trim().length() <= 0) valid = false;
        else if(umur == null || umur <= 0) valid = false;
        else if(hamil_ke == null || hamil_ke <= 0) valid = false;
        else if(haid_terakhir == null) valid = false;
        return valid;
    }
}
