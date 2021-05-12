package com.komodoindotech.kihvirtual.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.komodoindotech.kihvirtual.core.AppDatabase;
import com.komodoindotech.kihvirtual.dao.RiwayatPersalinanDao;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;

import java.util.List;

public class RiwayatPersalinanRepository {

    private RiwayatPersalinanDao riwayatPersalinanDao;
    private LiveData<List<RiwayatPersalinan>> listLiveData;
    private static final String TAG = "riwayatpersalinan";

    public RiwayatPersalinanRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        riwayatPersalinanDao = db.riwayatPersalinanDao();
    }
    public LiveData<List<RiwayatPersalinan>> getAllRiwayatPersalinan() {
        return riwayatPersalinanDao.getAll();
    }

    public Long insert (RiwayatPersalinan riwayatPersalinan) {
        return riwayatPersalinanDao.insert(riwayatPersalinan);
    }
    public Long[] insertAll (List<RiwayatPersalinan> riwayatPersalinans) {
        return riwayatPersalinanDao.insertAll(riwayatPersalinans);
    }

    public RiwayatPersalinanDao getRiwayatPersalinanDao() {
        return riwayatPersalinanDao;
    }

    public void deleteAll(){
        riwayatPersalinanDao.deleteAll();
    }

    public RiwayatKehamilan getLatestRiwayatPersalinan() {
        return riwayatPersalinanDao.getLatest();
    }

}
