package com.komodoindotech.kihvirtual.models;

import java.util.List;

public class RiwayatGroup {

    public static final String ID_KEHAMILAN     = "kehamilan";
    public static final String ID_PERSALINAN    = "persalinan";
    public static final String ID_IMUNISASI     = "imunisasi";
    public static final String ID_KELUHAN       = "keluhan";

    public String uid;
    public String label;
    public String subheading;
    public List<RiwayatContract> riwayatContracts;

    public RiwayatGroup(String label, List<RiwayatContract> riwayatContracts) {
        this.label = label;
        this.riwayatContracts = riwayatContracts;
    }

    public RiwayatGroup(String uid, String label, String subheading, List<RiwayatContract> riwayatContracts) {
        this.uid = uid;
        this.label = label;
        this.subheading = subheading;
        this.riwayatContracts = riwayatContracts;
    }

    public RiwayatGroup(String label, String subheading, List<RiwayatContract> riwayatContracts) {
        this.label = label;
        this.subheading = subheading;
        this.riwayatContracts = riwayatContracts;
    }

    public String getLabel() {
        return label;
    }
    public List<RiwayatContract> getRiwayatContracts() {
        return riwayatContracts;
    }
}
