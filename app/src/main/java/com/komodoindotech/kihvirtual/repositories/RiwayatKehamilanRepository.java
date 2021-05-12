package com.komodoindotech.kihvirtual.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.komodoindotech.kihvirtual.core.AppDatabase;
import com.komodoindotech.kihvirtual.dao.PendaftaranDao;
import com.komodoindotech.kihvirtual.dao.RiwayatKehamilanDao;
import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;

import java.util.List;

public class RiwayatKehamilanRepository {

    private RiwayatKehamilanDao riwayatKehamilanDao;
    private LiveData<List<RiwayatKehamilan>> listLiveData;
    private static final String TAG = "riwayatkehamilan";

    public RiwayatKehamilanRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        riwayatKehamilanDao = db.riwayatKehamilanDao();
    }
    public LiveData<List<RiwayatKehamilan>> getAllRiwayatKehamilans() {
        return riwayatKehamilanDao.getAll();
    }

    public Long insert (RiwayatKehamilan riwayatKehamilan) {
        return riwayatKehamilanDao.insert(riwayatKehamilan);
    }
    public Long[] insertAll (List<RiwayatKehamilan> riwayatKehamilans) {
        return riwayatKehamilanDao.insertAll(riwayatKehamilans);
    }

    public RiwayatKehamilanDao getRiwayatKehamilanDao() {
        return riwayatKehamilanDao;
    }

    public void deleteAll(){
        riwayatKehamilanDao.deleteAll();
    }

    public RiwayatKehamilan getLatestRiwayatKehamilan() {
        return riwayatKehamilanDao.getLatest();
    }

}
