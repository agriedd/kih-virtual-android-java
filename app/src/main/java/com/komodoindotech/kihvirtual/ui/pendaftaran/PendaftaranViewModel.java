package com.komodoindotech.kihvirtual.ui.pendaftaran;

import android.app.Application;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alibaba.fastjson.JSON;
import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;
import com.komodoindotech.kihvirtual.repositories.PendaftaranRepository;
import com.komodoindotech.kihvirtual.repositories.RiwayatKehamilanRepository;
import com.komodoindotech.kihvirtual.services.StorePendaftaran;
import com.komodoindotech.kihvirtual.services.StoreRiwayatKehamilan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendaftaranViewModel extends AndroidViewModel {
    private Integer formPagerPosition;
    private Integer countFormPager;
    private MutableLiveData<Integer> formPagerPositionLiveData;
    private MutableLiveData<Integer> countFormPagerLiveData;

    private Boolean riwayatPersalinan;
    private Boolean riwayatKehamilan;
    private MutableLiveData<Boolean> riwayatKehamilanLiveData;
    private MutableLiveData<Boolean> riwayatPersalinanLiveData;
    private Boolean nextPage = false;
    private Boolean previousPage = false;
    private MutableLiveData<Boolean> nextPageLiveData;
    private MutableLiveData<Boolean> previousPageLiveData;

    private MutableLiveData<Boolean> openReviewLiveData;
    private Map<String, Map<String, String>> inputError;
    private MutableLiveData<Map<String, Map<String, String>>> inputErrorLiveData;
    private Pendaftaran pendaftaran;
    private MutableLiveData<Pendaftaran> pendaftaranLiveData;
    private MutableLiveData<Boolean> validInputLiveData;

    private PendaftaranRepository pendaftaranRepository;

    private List<RiwayatKehamilan> riwayatKehamilans;
    private MutableLiveData<List<RiwayatKehamilan>> riwayatKehamilanObjectLiveData;
    private List<RiwayatPersalinan> riwayatPersalinans;
    private MutableLiveData<List<RiwayatPersalinan>> riwayatPersalinanObjectLiveData;
    private List<RiwayatKeluhan> riwayatKeluhans;
    private MutableLiveData<List<RiwayatKeluhan>> riwayatKeluhanObjectLiveData;

    private long id_pendaftaran;

    public PendaftaranViewModel(@NonNull Application application) {
        super(application);
        formPagerPositionLiveData = new MutableLiveData<>();
        countFormPagerLiveData = new MutableLiveData<>();
        riwayatKehamilanLiveData = new MutableLiveData<>();
        riwayatPersalinanLiveData = new MutableLiveData<>();
        nextPageLiveData = new MutableLiveData<>();
        previousPageLiveData = new MutableLiveData<>();
        openReviewLiveData = new MutableLiveData<>();
        inputErrorLiveData = new MutableLiveData<>();
        inputError = new HashMap<>();
        pendaftaranLiveData = new MutableLiveData<>();
        pendaftaran = new Pendaftaran();
        pendaftaranLiveData.setValue(pendaftaran);
        validInputLiveData = new MutableLiveData<>();
        validInputLiveData.setValue(false);

        riwayatKehamilanObjectLiveData = new MutableLiveData<>();
        riwayatKehamilans = new ArrayList<>();
        riwayatPersalinanLiveData = new MutableLiveData<>();
        riwayatPersalinanObjectLiveData = new MutableLiveData<>();
        riwayatPersalinans = new ArrayList<>();
        riwayatKeluhanObjectLiveData = new MutableLiveData<>();
        riwayatKeluhans = new ArrayList<>();

        pendaftaranRepository = new PendaftaranRepository(application);

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
    public LiveData<Boolean> getRiwayatPersalinanLiveData() { return riwayatPersalinanLiveData; }

    public void setRiwayatKehamilanLiveData(Boolean riwayatKehamilanLiveData) {
        this.riwayatKehamilanLiveData.setValue(riwayatKehamilan = riwayatKehamilanLiveData);
    }
    public void setRiwayatPersalinanLiveData(Boolean riwayatPersalinanLiveData) {
        this.riwayatPersalinanLiveData.setValue(riwayatPersalinan = riwayatPersalinanLiveData);
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
        previousPageLiveData.setValue(previousPage = false);
    }

    public void openReview() {
        openReviewLiveData.setValue(true);
        openReviewLiveData.setValue(false);
    }

    public LiveData<Boolean> getOpenReviewLiveData() {
        return openReviewLiveData;
    }

    public void setInputError(String key, Map<String, String> value){
        inputError.put(key, value);
        inputErrorLiveData.setValue( inputError );
    }
    public  LiveData<Map<String, Map<String, String>>> getInputErrors(){
        return inputErrorLiveData;
    }

    public LiveData<Pendaftaran> getPendaftaranObject() {
        return pendaftaranLiveData;
    }

    public void setPendaftaranObject(Pendaftaran pendaftaranObject) {
        pendaftaranLiveData.setValue(this.pendaftaran = pendaftaranObject);
        validInputLiveData.setValue(pendaftaranObject.isError());
    }

    public LiveData<Boolean> getValidInputLiveData() {
        return validInputLiveData;
    }

    public void storePendaftaran(Pendaftaran pendaftaran){
        StorePendaftaran.handler(pendaftaranRepository, pendaftaran);
    }
    public void storePendaftaran(){
        id_pendaftaran = StorePendaftaran.handler(pendaftaranRepository, pendaftaran);

        List<RiwayatKehamilan> riwayatKehamilans = new ArrayList<>(this.riwayatKehamilans);
        riwayatKehamilans.remove(0);
        for(RiwayatKehamilan riwayatKehamilan : riwayatKehamilans)
            riwayatKehamilan.id_pendaftaran = (int) id_pendaftaran;

        StoreRiwayatKehamilan.handler(new RiwayatKehamilanRepository(getApplication()), riwayatKehamilans);

        for(RiwayatPersalinan riwayatPersalinan : riwayatPersalinans){
            riwayatPersalinan.id_pendaftaran = (int) id_pendaftaran;
        }
    }

    public void setRiwayatKehamilanObjectLiveData(List<RiwayatKehamilan> pilihanObjects) {
        riwayatKehamilanObjectLiveData.setValue( riwayatKehamilans = pilihanObjects );
    }
    public void setRiwayatPersalinanObjectLiveData(List<RiwayatPersalinan> pilihanObjects) {
        riwayatPersalinanObjectLiveData.setValue( riwayatPersalinans = pilihanObjects );
    }
    public void setRiwayatKeluhanObjectLiveData(List<RiwayatKeluhan> pilihanObjects) {
        riwayatKeluhanObjectLiveData.setValue( riwayatKeluhans = pilihanObjects );
    }
}