package com.komodoindotech.kihvirtual.ui.kesimpulan;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;
import com.komodoindotech.kihvirtual.models.RiwayatImunisasi;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;
import com.komodoindotech.kihvirtual.repositories.PendaftaranRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KesimpulanViewModel extends AndroidViewModel {

    public MutableLiveData<Long> id_pendaftaran;
    public MutableLiveData<String> id_pendaftaran_cloud;
    private final MutableLiveData<PendaftaranDanRiwayat> pendaftaran;
    private PendaftaranDanRiwayat pendaftaranDanRiwayat;
    MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public KesimpulanViewModel(@NonNull @NotNull Application application) {
        super(application);
        pendaftaran = new MutableLiveData<>();
        id_pendaftaran = new MutableLiveData<>();
        id_pendaftaran_cloud = new MutableLiveData<>();
        loading.setValue(false);
    }

    public void setId(Long id) {
        id_pendaftaran.setValue(id);
    }

    public void setIdCloud(String id_pendaftaran_cloud) {
        this.id_pendaftaran_cloud.setValue(id_pendaftaran_cloud);
    }

    private void loadPendaftaran(Long id) {
        loading.setValue(true);
        PendaftaranDanRiwayat pendaftaranDanRiwayat = new PendaftaranRepository(getApplication()).find(id);
        pendaftaran.setValue(pendaftaranDanRiwayat);
        loading.setValue(false);
    }

    public LiveData<PendaftaranDanRiwayat> getPendaftaran() {
        return pendaftaran;
    }
    public void loadDataPendaftaran() {
        loading.setValue(true);
        PendaftaranDanRiwayat pendaftaranDanRiwayat = new PendaftaranRepository(getApplication()).find(id_pendaftaran.getValue());

        if(pendaftaranDanRiwayat != null){
            pendaftaran.setValue(this.pendaftaranDanRiwayat = pendaftaranDanRiwayat);
            loading.setValue(false);
        } else {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("pendaftaran")
                    .document(Objects.requireNonNull(id_pendaftaran_cloud.getValue()))
                    .get()
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            assert document != null;
                            if (document.exists()) {
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

                                PendaftaranDanRiwayat pendaftaranTemp = new PendaftaranDanRiwayat();
                                pendaftaranTemp.pendaftaran = pendaftaran;
                                this.pendaftaran.setValue(this.pendaftaranDanRiwayat = pendaftaranTemp);

                                loadDataRiwayatKehamilan(db, pendaftaran);

                            } else {
                                Log.d("wtf", "No such document");
                                this.pendaftaran.setValue(null);
                            }
                        } else {
                            this.pendaftaran.setValue(null);
                        }
                    }).addOnFailureListener(e -> Log.d("wtf", "loadPendaftaran: "+e.getMessage()));
        }
    }

    private void loadDataRiwayatKehamilan(FirebaseFirestore db, Pendaftaran pendaftaran) {
        db.collection("riwayat_kehamilan")
                .whereEqualTo("id_pendaftaran", pendaftaran.getUid())
                .get()
                .addOnCompleteListener(task1 -> {
                    if(task1.isSuccessful()){
                        List<RiwayatKehamilan> riwayatKehamilans = new ArrayList<>();
                        for(QueryDocumentSnapshot document1 : Objects.requireNonNull(task1.getResult())) {
                            RiwayatKehamilan riwayatKehamilan = new RiwayatKehamilan();
                            riwayatKehamilan.label = document1.getString("label");
                            riwayatKehamilan.tindakan = document1.getString("tindakan");
                            riwayatKehamilan.value = document1.get("value", Boolean.class);
                            try {
                                riwayatKehamilan.warna = document1.get("warna", Integer.class);
                            }catch (Exception ignored){}
                            riwayatKehamilans.add(riwayatKehamilan);
                        }
                        Objects.requireNonNull(this.pendaftaran.getValue()).riwayatKehamilans = riwayatKehamilans;
                        this.pendaftaran.setValue(this.pendaftaranDanRiwayat = this.pendaftaran.getValue());
                    }
                    loadDataRiwayatPersalinan(db, pendaftaran);
                });
    }

    private void loadDataRiwayatPersalinan(FirebaseFirestore db, Pendaftaran pendaftaran) {
        db.collection("riwayat_persalinan")
                .whereEqualTo("id_pendaftaran", pendaftaran.getUid())
                .get()
                .addOnCompleteListener(task1 -> {
                    if(task1.isSuccessful()){
                        List<RiwayatPersalinan> riwayatPersalinans = new ArrayList<>();
                        for(QueryDocumentSnapshot document1 : task1.getResult()) {
                            RiwayatPersalinan riwayatPersalinan = new RiwayatPersalinan();
                            riwayatPersalinan.label = document1.getString("label");
                            riwayatPersalinan.tindakan = document1.getString("tindakan");
                            riwayatPersalinan.value = document1.get("value", Boolean.class);
                            try {
                                riwayatPersalinan.warna = document1.get("warna", Integer.class);
                            } catch (Exception ignored){}
                            riwayatPersalinans.add(riwayatPersalinan);
                        }
                        Objects.requireNonNull(this.pendaftaran.getValue()).riwayatPersalinans = riwayatPersalinans;
                        this.pendaftaran.setValue(this.pendaftaranDanRiwayat = this.pendaftaran.getValue());
                    }
                    loadDataRiwayatImunisasi(db, pendaftaran);
                });
    }

    private void loadDataRiwayatImunisasi(FirebaseFirestore db, Pendaftaran pendaftaran) {
        db.collection("riwayat_imunisasi")
                .whereEqualTo("id_pendaftaran", pendaftaran.getUid())
                .get()
                .addOnCompleteListener(task1 -> {
                    if(task1.isSuccessful()){
                        List<RiwayatImunisasi> riwayatImunisasis = new ArrayList<>();
                        for(QueryDocumentSnapshot document1 : Objects.requireNonNull(task1.getResult())) {
                            RiwayatImunisasi riwayatImunisasi = new RiwayatImunisasi();
                            riwayatImunisasi.label = document1.getString("label");
                            riwayatImunisasi.value = document1.getString("value");
                            riwayatImunisasis.add(riwayatImunisasi);
                        }
                        Objects.requireNonNull(this.pendaftaran.getValue()).riwayatImunisasis = riwayatImunisasis;
                        this.pendaftaran.setValue(this.pendaftaranDanRiwayat = this.pendaftaran.getValue());
                    }
                    loadDataKeluhan(db, pendaftaran);
                });
    }

    private void loadDataKeluhan(FirebaseFirestore db, Pendaftaran pendaftaran) {
        db.collection("keluhan")
                .whereEqualTo("id_pendaftaran", pendaftaran.getUid())
                .get()
                .addOnCompleteListener(task1 -> {
                    if(task1.isSuccessful()){
                        List<RiwayatKeluhan> riwayatKeluhans = new ArrayList<>();
                        for(QueryDocumentSnapshot document1 : Objects.requireNonNull(task1.getResult())) {
                            RiwayatKeluhan riwayatKeluhan = new RiwayatKeluhan();
                            riwayatKeluhan.label = document1.getString("label");
                            riwayatKeluhan.tindakan = document1.getString("tindakan");
                            riwayatKeluhan.value = document1.get("value", Boolean.class);
                            try {
                                riwayatKeluhan.warna = document1.get("warna", Integer.class);
                            } catch (Exception ignored){}
                            riwayatKeluhans.add(riwayatKeluhan);
                        }
                        Objects.requireNonNull(this.pendaftaran.getValue()).riwayatKeluhans = riwayatKeluhans;
                        this.pendaftaran.setValue(this.pendaftaranDanRiwayat = this.pendaftaran.getValue());
                    }
                    loading.setValue(false);
                });
    }

    public PendaftaranDanRiwayat getPendaftaranDanRiwayat() {
        return pendaftaranDanRiwayat;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }
}