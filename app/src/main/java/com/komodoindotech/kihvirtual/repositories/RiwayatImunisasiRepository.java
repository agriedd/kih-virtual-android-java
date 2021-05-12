package com.komodoindotech.kihvirtual.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.komodoindotech.kihvirtual.core.AppDatabase;
import com.komodoindotech.kihvirtual.dao.RiwayatImunisasiDao;
import com.komodoindotech.kihvirtual.dao.RiwayatPersalinanDao;
import com.komodoindotech.kihvirtual.models.RiwayatImunisasi;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;

import java.util.List;

public class RiwayatImunisasiRepository {

    private RiwayatImunisasiDao riwayatImunisasiDao;
    private LiveData<List<RiwayatImunisasi>> listLiveData;
    private static final String TAG = "riwayatimunisasi";

    public RiwayatImunisasiRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        riwayatImunisasiDao = db.riwayatImunisasiDao();
    }
    public LiveData<List<RiwayatImunisasi>> getAllRiwayatImunisasi() {
        return riwayatImunisasiDao.getAll();
    }

    public Long insert (RiwayatImunisasi riwayatImunisasi) {
        return riwayatImunisasiDao.insert(riwayatImunisasi);
    }
    public Long[] insertAll (List<RiwayatImunisasi> riwayatImunisasiList) {
        return riwayatImunisasiDao.insertAll(riwayatImunisasiList);
    }

    public RiwayatImunisasiDao getRiwayatImunisasiDao() {
        return riwayatImunisasiDao;
    }

    public void deleteAll(){
        riwayatImunisasiDao.deleteAll();
    }

    public RiwayatImunisasi getLatestRiwayatImunisasi() {
        return riwayatImunisasiDao.getLatest();
    }

}
