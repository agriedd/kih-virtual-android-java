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
    private Boolean nextPage = false;
    private Boolean previousPage = false;
    private MutableLiveData<Boolean> nextPageLiveData;
    private MutableLiveData<Boolean> previousPageLiveData;

    private MutableLiveData<Boolean> openReviewLiveData;

    public PendaftaranViewModel(@NonNull Application application) {
        super(application);
        formPagerPositionLiveData = new MutableLiveData<>();
        countFormPagerLiveData = new MutableLiveData<>();
        riwayatKehamilanLiveData = new MutableLiveData<>();
        nextPageLiveData = new MutableLiveData<>();
        previousPageLiveData = new MutableLiveData<>();
        openReviewLiveData = new MutableLiveData<>();
    }

    public LiveData<Integer> getFormPagerPositionLiveData() {
        return formPagerPositionLiveData;
    }

    public void setFormPagerPositionLiveData(int formPagerPositionLiveData) {
        formPagerPosition = formPagerPositionLiveData;
        this.nextPageLiveData.setValue(nextPage = false);
        this.previousPageLiveData.setValue(previousPage = false);
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

    public LiveData<Boolean> getNextPageLiveData() {
        return nextPageLiveData;
    }

    public void nextPageForm() {
        nextPageLiveData.setValue(nextPage = true);
    }
    public LiveData<Boolean> getPreviousPageLiveData() {
        return previousPageLiveData;
    }

    public void previousPageForm() {
        previousPageLiveData.setValue(previousPage = true);
    }

    public void openReview() {
        openReviewLiveData.setValue(true);
        openReviewLiveData.setValue(false);
    }

    public LiveData<Boolean> getOpenReviewLiveData() {
        return openReviewLiveData;
    }
}