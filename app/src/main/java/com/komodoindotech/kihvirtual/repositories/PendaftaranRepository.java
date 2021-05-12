package com.komodoindotech.kihvirtual.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.komodoindotech.kihvirtual.core.AppDatabase;
import com.komodoindotech.kihvirtual.dao.PendaftaranDao;
import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;

import java.util.List;

public class PendaftaranRepository {
    private PendaftaranDao pendaftaranDao;
    private LiveData<List<Pendaftaran>> listLiveData;
    private static final String TAG = "pendaftaranrepo";

    public PendaftaranRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        this.pendaftaranDao = db.pendaftaranDao();
    }
    public LiveData<List<Pendaftaran>> getAllPendaftarans() {
        return pendaftaranDao.getAll();
    }

    public Long insert (Pendaftaran pendaftaran) {
        return pendaftaranDao.insert(pendaftaran);
    }

    public PendaftaranDao getPendaftaranDao() {
        return pendaftaranDao;
    }

    public void insertSync(Pendaftaran pendaftaran) {
        pendaftaranDao.insert(pendaftaran);
    }

    public void deleteAll(){
        pendaftaranDao.deleteAll();
    }

    public PendaftaranDanRiwayat getPendaftaranCached() {
        return pendaftaranDao.getLatest();
    }

    private static class insertAsyncTask extends AsyncTask<Pendaftaran, Void, Void> {

        PendaftaranDao pendaftaranDao;
        Pendaftaran pendaftaran;

        public insertAsyncTask(PendaftaranDao pendaftaranDao, Pendaftaran pendaftaran) {
            this.pendaftaranDao = pendaftaranDao;
            this.pendaftaran = pendaftaran;
        }

        @Override
        protected Void doInBackground(Pendaftaran... pendaftarans) {
            this.pendaftaranDao.insert(pendaftarans[0]);
            return null;
        }
    }
}
