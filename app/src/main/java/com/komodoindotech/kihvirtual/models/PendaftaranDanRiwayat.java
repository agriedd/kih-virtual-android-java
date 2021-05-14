package com.komodoindotech.kihvirtual.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PendaftaranDanRiwayat {
    @Embedded
    public Pendaftaran pendaftaran;

    @Relation(
            parentColumn = "id",
            entityColumn = "id_pendaftaran"
    )
    public List<RiwayatKehamilan> riwayatKehamilans;

    @Relation(
            parentColumn = "id",
            entityColumn = "id_pendaftaran"
    )
    public List<RiwayatPersalinan> riwayatPersalinans;

    @Relation(
            parentColumn = "id",
            entityColumn = "id_pendaftaran"
    )
    public List<RiwayatImunisasi> riwayatImunisasis;
    @Relation(
            parentColumn = "id",
            entityColumn = "id_pendaftaran"
    )
    public List<RiwayatKeluhan> riwayatKeluhans;
}
