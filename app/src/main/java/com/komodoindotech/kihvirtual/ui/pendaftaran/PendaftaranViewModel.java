package com.komodoindotech.kihvirtual.ui.pendaftaran;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alibaba.fastjson.JSON;
import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.models.RiwayatImunisasi;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;
import com.komodoindotech.kihvirtual.repositories.PendaftaranRepository;
import com.komodoindotech.kihvirtual.repositories.RiwayatImunisasiRepository;
import com.komodoindotech.kihvirtual.repositories.RiwayatKehamilanRepository;
import com.komodoindotech.kihvirtual.repositories.RiwayatPersalinanRepository;
import com.komodoindotech.kihvirtual.services.StorePendaftaran;
import com.komodoindotech.kihvirtual.services.StoreRiwayatImunisasi;
import com.komodoindotech.kihvirtual.services.StoreRiwayatKehamilan;
import com.komodoindotech.kihvirtual.services.StoreRiwayatPersalinan;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendaftaranViewModel extends AndroidViewModel {
    private final MutableLiveData<Integer> formPagerPositionLiveData;
    private final MutableLiveData<Integer> countFormPagerLiveData;

    private final MutableLiveData<Boolean> riwayatKehamilanLiveData;
    private MutableLiveData<Boolean> riwayatPersalinanLiveData;
    private Boolean nextPage = false;
    private Boolean previousPage = false;
    private final MutableLiveData<Boolean> nextPageLiveData;
    private final MutableLiveData<Boolean> previousPageLiveData;

    private final MutableLiveData<Boolean> openReviewLiveData;
    private final Map<String, Map<String, String>> inputError;
    private final MutableLiveData<Map<String, Map<String, String>>> inputErrorLiveData;
    private Pendaftaran pendaftaran;
    private final MutableLiveData<Pendaftaran> pendaftaranLiveData;
    private final MutableLiveData<Boolean> validInputLiveData;

    private final PendaftaranRepository pendaftaranRepository;

    private List<RiwayatKehamilan> riwayatKehamilans;
    private final MutableLiveData<List<RiwayatKehamilan>> riwayatKehamilanObjectLiveData;
    private List<RiwayatPersalinan> riwayatPersalinans;
    private final MutableLiveData<List<RiwayatPersalinan>> riwayatPersalinanObjectLiveData;
    private List<RiwayatKeluhan> riwayatKeluhans;
    private final MutableLiveData<List<RiwayatKeluhan>> riwayatKeluhanObjectLiveData;
    private final MutableLiveData<List<RiwayatImunisasi>> riwayatImunisasiObjectLiveData;

    private long id_pendaftaran;
    private List<RiwayatImunisasi> riwayatImunisasis;

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
        riwayatImunisasis = new ArrayList<>();
        riwayatImunisasiObjectLiveData = new MutableLiveData<>();

        pendaftaranRepository = new PendaftaranRepository(application);

    }

    public LiveData<Integer> getFormPagerPositionLiveData() {
        return formPagerPositionLiveData;
    }

    public void setFormPagerPositionLiveData(int formPagerPositionLiveData) {
        Integer formPagerPosition = formPagerPositionLiveData;
        this.nextPageLiveData.setValue(nextPage = false);
        this.previousPageLiveData.setValue(previousPage = false);
        this.formPagerPositionLiveData.setValue(formPagerPosition);
    }

    public LiveData<Integer> getCountFormPagerLiveData() {
        return countFormPagerLiveData;
    }

    public void setCountFormPagerLiveData(int countFormPagerLiveData) {
        Integer countFormPager = countFormPagerLiveData;
        this.countFormPagerLiveData.setValue(countFormPager);
    }

    public LiveData<Boolean> getRiwayatKehamilanLiveData() {
        return riwayatKehamilanLiveData;
    }
    public LiveData<Boolean> getRiwayatPersalinanLiveData() { return riwayatPersalinanLiveData; }

    public void setRiwayatKehamilanLiveData(Boolean riwayatKehamilanLiveData) {
        this.riwayatKehamilanLiveData.setValue(riwayatKehamilanLiveData);
    }
    public void setRiwayatPersalinanLiveData(Boolean riwayatPersalinanLiveData) {
        this.riwayatPersalinanLiveData.setValue(riwayatPersalinanLiveData);
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

    public void storePendaftaran(){

        pendaftaran.created_at = new Date().getTime();
        pendaftaran.updated_at = new Date().getTime();
        id_pendaftaran = StorePendaftaran.handler(pendaftaranRepository, pendaftaran);

        List<RiwayatKehamilan> riwayatKehamilans = new ArrayList<>(this.riwayatKehamilans);
        riwayatKehamilans.remove(0);
        for(RiwayatKehamilan riwayatKehamilan : riwayatKehamilans){
            riwayatKehamilan.created_at = new Date().getTime();
            riwayatKehamilan.updated_at = new Date().getTime();
            riwayatKehamilan.id_pendaftaran = (int) id_pendaftaran;
        }

        StoreRiwayatKehamilan.handler(new RiwayatKehamilanRepository(getApplication()), riwayatKehamilans);

        List<RiwayatPersalinan> riwayatPersalinans = new ArrayList<>(this.riwayatPersalinans);
        riwayatKehamilans.remove(0);
        for(RiwayatPersalinan riwayatPersalinan : riwayatPersalinans){
            riwayatPersalinan.created_at = new Date().getTime();
            riwayatPersalinan.updated_at = new Date().getTime();
            riwayatPersalinan.id_pendaftaran = (int) id_pendaftaran;
        }

        StoreRiwayatPersalinan.handler(new RiwayatPersalinanRepository(getApplication()), riwayatPersalinans);
        for(RiwayatImunisasi riwayatImunisasi : riwayatImunisasis){
            riwayatImunisasi.id_pendaftaran = (int) id_pendaftaran;
            riwayatImunisasi.created_at = new Date().getTime();
            riwayatImunisasi.updated_at = new Date().getTime();
        }
        StoreRiwayatImunisasi.handler(new RiwayatImunisasiRepository(getApplication()), riwayatImunisasis);
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
    public void setRiwayatImunisasiObjectLiveData(List<RiwayatImunisasi> riwayatImunisasiObjectLiveData) {
        this.riwayatImunisasiObjectLiveData.setValue(riwayatImunisasis = riwayatImunisasiObjectLiveData);
    }
}