package com.komodoindotech.kihvirtual.ui.data;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alibaba.fastjson.JSON;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.komodoindotech.kihvirtual.core.AppDatabase;
import com.komodoindotech.kihvirtual.dao.PendaftaranDao;
import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DaftarDataViewModel extends AndroidViewModel {

    MutableLiveData<List<PendaftaranDanRiwayat>> pendaftaranDanRiwayatLiveData;
    FirebaseFirestore db;
    MutableLiveData<Boolean> loading;
    MutableLiveData<List<Pendaftaran>> pendaftaranLocalList = new MutableLiveData<>();

    public DaftarDataViewModel(@NonNull @NotNull Application application) {
        super(application);
        pendaftaranDanRiwayatLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>();
    }

    public LiveData<List<PendaftaranDanRiwayat>> getPendaftaranDanRiwayatLiveData() {
        return pendaftaranDanRiwayatLiveData;
    }

    public void loadLocalPendaftaran(){
        PendaftaranDao pendaftaranDao = AppDatabase.getDatabase(getApplication()).pendaftaranDao();
        pendaftaranLocalList.setValue(pendaftaranDao.getLocals());
        loadPendaftaran();
    }

    private void loadPendaftaran() {
        loading.setValue(true);
        db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null || (pendaftaranLocalList.getValue() != null && pendaftaranLocalList.getValue().size() > 0)) {
            Query query = db.collection("pendaftaran")
                    .orderBy("created_at", Query.Direction.DESCENDING);

            if(user != null){
                String uid = user.getUid();
                query = query.whereEqualTo("user_id", uid);
            } else if (pendaftaranLocalList.getValue() != null && pendaftaranLocalList.getValue().size() > 0){
                List<String> cidList = new ArrayList<>();
                for (Pendaftaran pend: pendaftaranLocalList.getValue()) {
                    if(pend.getCid() != null)
                        cidList.add(pend.getCid());
                }
                query = query.whereIn("cid", cidList);
            }
            query.get()
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
                            pendaftaranList.add(pendaftaranDanRiwayat);
                        }
                        pendaftaranDanRiwayatLiveData.setValue(pendaftaranList);
                    } else {
                        pendaftaranDanRiwayatLiveData.setValue(null);
                    }
                    loading.setValue(false);
                }).addOnFailureListener(e -> {
                    Log.d("edd", "loadPendaftaran: "+e.getMessage());
                    loading.setValue(false);
                });
        } else {
            pendaftaranDanRiwayatLiveData.setValue(null);
            loading.setValue(false);
        }
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }
}