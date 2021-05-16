package com.komodoindotech.kihvirtual.ui.kesimpulan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;
import com.komodoindotech.kihvirtual.repositories.PendaftaranRepository;

import org.jetbrains.annotations.NotNull;

public class KesimpulanViewModel extends AndroidViewModel {

    private MutableLiveData<Long> id_pendaftaran;
    private MutableLiveData<PendaftaranDanRiwayat> pendaftaran;

    public KesimpulanViewModel(@NonNull @NotNull Application application) {
        super(application);
        pendaftaran = new MutableLiveData<>();
        id_pendaftaran = new MutableLiveData<>();
    }

    public void setId(Long id) {
        id_pendaftaran.setValue(id);
        loadPendaftaran(id);
    }

    private void loadPendaftaran(Long id) {
        if(id != 0){
            PendaftaranDanRiwayat pendaftaranDanRiwayat = new PendaftaranRepository(getApplication()).find(id);
            pendaftaran.setValue(pendaftaranDanRiwayat);
        }
    }

    public LiveData<PendaftaranDanRiwayat> getPendaftaran() {
        /**
         *
         * developer
         */
        PendaftaranDanRiwayat pendaftaranDanRiwayat = new PendaftaranRepository(getApplication()).getLatest();
        pendaftaran.setValue(pendaftaranDanRiwayat);

        return pendaftaran;
    }
}