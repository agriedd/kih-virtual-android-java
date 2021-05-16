package com.komodoindotech.kihvirtual.ui.data;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alibaba.fastjson.JSON;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.komodoindotech.kihvirtual.json.PuskesmasObject;
import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;
import com.komodoindotech.kihvirtual.models.RiwayatImunisasi;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DaftarDataViewModel extends AndroidViewModel {

    MutableLiveData<List<PendaftaranDanRiwayat>> pendaftaranDanRiwayatLiveData;
    FirebaseFirestore db;

    public DaftarDataViewModel(@NonNull @NotNull Application application) {
        super(application);
        pendaftaranDanRiwayatLiveData = new MutableLiveData<>();
        loadPendaftaran();
    }

    public LiveData<List<PendaftaranDanRiwayat>> getPendaftaranDanRiwayatLiveData() {
        return pendaftaranDanRiwayatLiveData;
    }

    private void loadPendaftaran() {
        db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            db.collection("pendaftaran").whereEqualTo("user_id", uid)
                    .get()
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            List<PendaftaranDanRiwayat> pendaftaranList = new ArrayList<>();
                            for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                                Pendaftaran pendaftaran = new Pendaftaran();

                                pendaftaran.setNama(document.getString("nama"));
                                pendaftaran.setAlamat(document.getString("alamat"));
                                pendaftaran.setHaid_terakhir(document.get("haid_terakhir", Long.class));
                                pendaftaran.setHamil_ke(document.get("hamil_ke", Integer.class));
                                pendaftaran.setLama_menikah(document.get("lama_menikah", Integer.class));
                                pendaftaran.setPekerjaan_istri(document.getString("pekerjaan_istri"));
                                pendaftaran.setPendidikan_istri(document.getString("pendidikan_istri"));
                                pendaftaran.setPekerjaan_suami(document.getString("pekerjaan_suami"));
                                pendaftaran.setPendidikan_suami(document.getString("pendidikan_suami"));
                                pendaftaran.setUmur(document.get("umur", Integer.class));
                                pendaftaran.setUsia_anak_terakhir(document.get("usia_anak_terakhir", Integer.class));
                                try {
                                    Timestamp timestamp = document.getTimestamp("created_at");
                                    if(timestamp != null){
                                        pendaftaran.setCreated_at(timestamp.toDate().getTime());
                                    }
                                } catch (Exception ignored){}

                                pendaftaran.setUid(document.getId());

                                PendaftaranDanRiwayat pendaftaranDanRiwayat = new PendaftaranDanRiwayat();
                                pendaftaranDanRiwayat.pendaftaran = pendaftaran;
//
//                                List<RiwayatKehamilan> riwayatKehamilans = new ArrayList<>();
//                                db.collection("riwayat_kehamilan")
//                                        .whereEqualTo("id_pendaftaran", pendaftaran.getUid())
//                                        .get()
//                                        .addOnCompleteListener(task1 -> {
//                                            if(task1.isSuccessful()){
//                                                for(QueryDocumentSnapshot document1 : Objects.requireNonNull(task1.getResult())) {
//                                                    RiwayatKehamilan riwayatKehamilan = new RiwayatKehamilan();
//                                                    riwayatKehamilan.label = document.getString("label");
//                                                    riwayatKehamilan.tindakan = document.getString("tindakan");
//                                                    riwayatKehamilan.value = document.get("value", Boolean.class);
//                                                    try {
//                                                        riwayatKehamilan.warna = document.get("warna", Integer.class);
//                                                    }catch (Exception ignored){}
//                                                    if(riwayatKehamilan.value != null && riwayatKehamilan.value){
//                                                        riwayatKehamilans.add(riwayatKehamilan);
//                                                    }
//                                                }
//                                            }
//                                        });
//                                pendaftaranDanRiwayat.riwayatKehamilans = riwayatKehamilans;
//
//
//                                List<RiwayatPersalinan> riwayatPersalinans = new ArrayList<>();
//                                db.collection("riwayat_persalinan")
//                                        .whereEqualTo("id_pendaftaran", pendaftaran.getUid())
//                                        .get()
//                                        .addOnCompleteListener(task1 -> {
//                                            if(task1.isSuccessful()){
//                                                for(QueryDocumentSnapshot document1 : Objects.requireNonNull(task1.getResult())) {
//                                                    RiwayatPersalinan riwayatPersalinan = new RiwayatPersalinan();
//                                                    riwayatPersalinan.label = document.getString("label");
//                                                    riwayatPersalinan.tindakan = document.getString("tindakan");
//                                                    riwayatPersalinan.value = document.get("value", Boolean.class);
//                                                    try {
//                                                        riwayatPersalinan.warna = document.get("warna", Integer.class);
//                                                    } catch (Exception ignored){}
//
//                                                    if(riwayatPersalinan.value != null && riwayatPersalinan.value){
//                                                        riwayatPersalinans.add(riwayatPersalinan);
//                                                    }
//                                                }
//                                            }
//                                        });
//                                pendaftaranDanRiwayat.riwayatPersalinans = riwayatPersalinans;
//
//                                List<RiwayatImunisasi> riwayatImunisasis = new ArrayList<>();
//                                db.collection("riwayat_imunisasi")
//                                        .whereEqualTo("id_pendaftaran", pendaftaran.getUid())
//                                        .get()
//                                        .addOnCompleteListener(task1 -> {
//                                            if(task1.isSuccessful()){
//                                                for(QueryDocumentSnapshot document1 : Objects.requireNonNull(task1.getResult())) {
//                                                    RiwayatImunisasi riwayatImunisasi = new RiwayatImunisasi();
//                                                    riwayatImunisasi.label = document.getString("label");
//                                                    riwayatImunisasi.value = document.getString("value");
//                                                    if(riwayatImunisasi.value != null && riwayatImunisasi.value.length() > 0){
//                                                        riwayatImunisasis.add(riwayatImunisasi);
//                                                    }
//                                                }
//                                            }
//                                        });
//                                pendaftaranDanRiwayat.riwayatImunisasis = riwayatImunisasis;


//                                List<RiwayatKeluhan> riwayatKeluhans = new ArrayList<>();
//                                db.collection("keluhan")
//                                        .whereEqualTo("id_pendaftaran", pendaftaran.getUid())
//                                        .get()
//                                        .addOnCompleteListener(task1 -> {
//                                            if(task1.isSuccessful()){
//                                                for(QueryDocumentSnapshot document1 : Objects.requireNonNull(task1.getResult())) {
//                                                    RiwayatKeluhan riwayatKeluhan = new RiwayatKeluhan();
//                                                    riwayatKeluhan.label = document.getString("label");
//                                                    riwayatKeluhan.tindakan = document.getString("tindakan");
//                                                    riwayatKeluhan.value = document.get("value", Boolean.class);
//                                                    try {
//                                                        riwayatKeluhan.warna = document.get("warna", Integer.class);
//                                                    } catch (Exception ignored){}
//
//                                                    if(riwayatKeluhan.value != null && riwayatKeluhan.value){
//                                                        riwayatKeluhans.add(riwayatKeluhan);
//                                                    }
//                                                }
//                                            }
//                                        });
//                                pendaftaranDanRiwayat.riwayatKeluhans = riwayatKeluhans;

                                pendaftaranList.add(pendaftaranDanRiwayat);
                            }
                            pendaftaranDanRiwayatLiveData.setValue(pendaftaranList);
                        }
                    }).addOnFailureListener(e -> {
                        Log.d("wtf", "loadPendaftaran: "+e.getMessage());
                    });
        } else {
            pendaftaranDanRiwayatLiveData.setValue(null);
        }
    }

}