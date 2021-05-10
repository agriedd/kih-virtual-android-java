package com.komodoindotech.kihvirtual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import com.komodoindotech.kihvirtual.ui.pendaftaran.FormKartuCommunityScreeningFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.KonfirmasiPendaftaranFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.KonfirmasiPendaftaranViewModel;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

public class PendaftaranActivity extends AppCompatActivity {

    KonfirmasiPendaftaranViewModel konfirmasiPendaftaranViewModel;
    PendaftaranViewModel pendaftaranViewModel;
    Boolean agreement_status = false;
    int pager_position = 0;
    boolean is_previous = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pendaftaran_activity);

        konfirmasiPendaftaranViewModel = new ViewModelProvider(this).get(KonfirmasiPendaftaranViewModel.class);
        pendaftaranViewModel = new ViewModelProvider(this).get(PendaftaranViewModel.class);

        konfirmasiPendaftaranViewModel.getAgreementStatusLiveData().observe(this, status -> {
            agreement_status = status;
            if(agreement_status){
                replaceFragment(FormKartuCommunityScreeningFragment.newInstance());
            } else {
                replaceFragment(KonfirmasiPendaftaranFragment.newInstance());
            }
        });
        pendaftaranViewModel.getFormPagerPositionLiveData().observe(this, position -> {
            pager_position = position;
        });

        pendaftaranViewModel.getPreviousPageLiveData().observe(this, isPrevious -> {
            is_previous = isPrevious;
        });

    }

    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow();
    }

    @Override
    public void onBackPressed() {
        if (pager_position == 0) {
            super.onBackPressed();
        } else {
            pendaftaranViewModel.previousPageForm();
        }
    }
}