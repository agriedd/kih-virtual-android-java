package com.komodoindotech.kihvirtual.ui.pendaftaran;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class KonfirmasiPendaftaranViewModel extends AndroidViewModel {

    private static final String TAG = "konfirmasipendaftaranvm";
    private static final String STATUS = "status";
    // TODO: 5/9/2021 segera ganti
    private String url = "https://sites.google.com/view/kih-virtual/persetujuan-data-pengguna";
    private Boolean agreement_status;

    private MutableLiveData<Boolean> agreementStatusLiveData;
    private MutableLiveData<String> urlLiveData;
    private SharedPreferences preferences;

    public KonfirmasiPendaftaranViewModel(@NonNull Application application) {
        super(application);

        agreementStatusLiveData = new MutableLiveData<>();
        urlLiveData = new MutableLiveData<>();

        preferences = application.getSharedPreferences("kih-virtual", Context.MODE_PRIVATE);
        agreement_status = preferences.getBoolean(STATUS, false);
        agreementStatusLiveData.setValue(agreement_status);
        urlLiveData.setValue(url);

    }

    public LiveData<Boolean> getAgreementStatusLiveData(){
        return agreementStatusLiveData;
    }

    public LiveData<String> getUrlLiveData() {
        return urlLiveData;
    }

    public void setAgreementStatus(Boolean agreement_status) {
        agreementStatusLiveData.setValue(
                this.agreement_status = agreement_status
        );
        preferences.edit().putBoolean(STATUS, agreement_status).apply();
    }
}