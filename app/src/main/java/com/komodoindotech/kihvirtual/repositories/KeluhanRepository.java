package com.komodoindotech.kihvirtual.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.komodoindotech.kihvirtual.core.AppDatabase;
import com.komodoindotech.kihvirtual.dao.RiwayatKeluhanDao;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;

import java.util.List;

public class KeluhanRepository {

    private RiwayatKeluhanDao keluhanDao;
    private LiveData<List<RiwayatKeluhan>> listLiveData;
    private static final String TAG = "riwayatpersalinan";

    public KeluhanRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        keluhanDao = db.riwayatKeluhan();
    }
    public LiveData<List<RiwayatKeluhan>> getAllRiwayatKeluhan() {
        return keluhanDao.getAll();
    }

    public Long insert (RiwayatKeluhan riwayatKeluhan) {
        return keluhanDao.insert(riwayatKeluhan);
    }
    public Long[] insertAll (List<RiwayatKeluhan> riwayatKeluhans) {
        return keluhanDao.insertAll(riwayatKeluhans);
    }

    public RiwayatKeluhanDao RiwayatKeluhanDao() {
        return keluhanDao;
    }

    public void deleteAll(){
        keluhanDao.deleteAll();
    }

    public RiwayatKeluhan getLatestRiwayatKeluhan() {
        return keluhanDao.getLatest();
    }

}
