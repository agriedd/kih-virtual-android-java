package com.komodoindotech.kihvirtual.services;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;
import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;
import com.komodoindotech.kihvirtual.models.RiwayatImunisasi;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;

import java.util.HashMap;
import java.util.Map;

public class StorePendaftaranCloud {
    public static interface StoreListener{
        void onSuccess(Boolean status);
        void onError(String message);
    }

    public static void handler(FirebaseFirestore db, PendaftaranDanRiwayat pendaftaranDanRiwayat, StoreListener listener){
        Map<String, Object> pendaftaran = new HashMap<>();
        Pendaftaran pendaftaran_value = pendaftaranDanRiwayat.pendaftaran;
        pendaftaran.put("nama", pendaftaran_value.nama);
        pendaftaran.put("umur", pendaftaran_value.umur);
        pendaftaran.put("alamat", pendaftaran_value.alamat);
        pendaftaran.put("hamil_ke", pendaftaran_value.hamil_ke);
        pendaftaran.put("pendidikan_istri", pendaftaran_value.pendidikan_istri);
        pendaftaran.put("pendidikan_suami", pendaftaran_value.pendidikan_suami);
        pendaftaran.put("pekerjaan_istri", pendaftaran_value.pekerjaan_istri);
        pendaftaran.put("haid_terakhir", pendaftaran_value.haid_terakhir);
        pendaftaran.put("usia_anak_terakhir", pendaftaran_value.usia_anak_terakhir);
        pendaftaran.put("lama_menikah", pendaftaran_value.lama_menikah);
        pendaftaran.put("tid", pendaftaran_value.created_at);
        pendaftaran.put("created_at", FieldValue.serverTimestamp());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            pendaftaran.put("user_id", uid);
        } else {
            pendaftaran.put("user_id", null);
        }

        WriteBatch batch = db.batch();

        db.collection("pendaftaran")
                .add(pendaftaran)
                .addOnSuccessListener(documentReference -> {
                    String id = documentReference.getId();
                    for(RiwayatKehamilan riwayatKehamilan: pendaftaranDanRiwayat.riwayatKehamilans){

                        Map<String, Object> value = new HashMap<>();
                        value.put("uid", riwayatKehamilan.uid);
                        value.put("label", riwayatKehamilan.label);
                        value.put("warna", riwayatKehamilan.warna);
                        value.put("tindakan", riwayatKehamilan.tindakan);
                        value.put("value", riwayatKehamilan.value);
                        value.put("tid", riwayatKehamilan.created_at);
                        value.put("created_at", FieldValue.serverTimestamp());
                        value.put("id_pendaftaran", id);

                        batch.set(
                                db.collection("riwayat_kehamilan").document(), value
                        );
                    }
                    for(RiwayatPersalinan riwayatPersalinan: pendaftaranDanRiwayat.riwayatPersalinans){

                        Map<String, Object> value = new HashMap<>();
                        value.put("uid", riwayatPersalinan.uid);
                        value.put("label", riwayatPersalinan.label);
                        value.put("warna", riwayatPersalinan.warna);
                        value.put("tindakan", riwayatPersalinan.tindakan);
                        value.put("value", riwayatPersalinan.value);
                        value.put("tid", riwayatPersalinan.created_at);
                        value.put("created_at", FieldValue.serverTimestamp());
                        value.put("id_pendaftaran", id);

                        batch.set(
                                db.collection("riwayat_persalinan").document(), value
                        );
                    }
                    for(RiwayatImunisasi riwayatImunisasi: pendaftaranDanRiwayat.riwayatImunisasis){

                        Map<String, Object> value = new HashMap<>();
                        value.put("uid", riwayatImunisasi.uid);
                        value.put("label", riwayatImunisasi.label);
                        value.put("value", riwayatImunisasi.value);
                        value.put("tid", riwayatImunisasi.created_at);
                        value.put("created_at", FieldValue.serverTimestamp());
                        value.put("id_pendaftaran", id);

                        batch.set(
                                db.collection("riwayat_imunisasi").document(), value
                        );
                    }
                    for(RiwayatKeluhan riwayatKeluhan: pendaftaranDanRiwayat.riwayatKeluhans){

                        Map<String, Object> value = new HashMap<>();
                        value.put("uid", riwayatKeluhan.uid);
                        value.put("label", riwayatKeluhan.label);
                        value.put("warna", riwayatKeluhan.warna);
                        value.put("tindakan", riwayatKeluhan.tindakan);
                        value.put("value", riwayatKeluhan.value);
                        value.put("tid", riwayatKeluhan.created_at);
                        value.put("created_at", FieldValue.serverTimestamp());
                        value.put("id_pendaftaran", id);

                        batch.set(
                                db.collection("keluhan").document(), value
                        );
                    }
                    batch.commit().addOnSuccessListener(command -> {
                        listener.onSuccess(true);
                    }).addOnFailureListener(e -> {
                        listener.onError(e.getMessage());
                    });
                })
                .addOnFailureListener(e -> Log.d("pendaftaran", "handler: gagal store firebase"));
    }
}
