package com.komodoindotech.kihvirtual.ui.pendaftaran;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PendaftaranViewModel extends AndroidViewModel {
    private Integer formPagerPosition;
    private Integer countFormPager;
    private MutableLiveData<Integer> formPagerPositionLiveData;
    private MutableLiveData<Integer> countFormPagerLiveData;

    private Boolean riwayatKehamilan;
    private MutableLiveData<Boolean> riwayatKehamilanLiveData;

    public PendaftaranViewModel(@NonNull Application application) {
        super(application);
        formPagerPositionLiveData = new MutableLiveData<>();
        countFormPagerLiveData = new MutableLiveData<>();
        riwayatKehamilanLiveData = new MutableLiveData<>();
    }

    public LiveData<Integer> getFormPagerPositionLiveData() {
        return formPagerPositionLiveData;
    }

    public void setFormPagerPositionLiveData(int formPagerPositionLiveData) {
        formPagerPosition = formPagerPositionLiveData;
        this.formPagerPositionLiveData.setValue(formPagerPosition);
    }

    public LiveData<Integer> getCountFormPagerLiveData() {
        return countFormPagerLiveData;
    }

    public void setCountFormPagerLiveData(int countFormPagerLiveData) {
        countFormPager = countFormPagerLiveData;
        this.countFormPagerLiveData.setValue(countFormPager);
    }

    public LiveData<Boolean> getRiwayatKehamilanLiveData() {
        return riwayatKehamilanLiveData;
    }

    public void setRiwayatKehamilanLiveData(Boolean riwayatKehamilanLiveData) {
        this.riwayatKehamilanLiveData.setValue(riwayatKehamilan = riwayatKehamilanLiveData);
    }
}