package com.komodoindotech.kihvirtual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.komodoindotech.kihvirtual.ui.form.FormInfoDataDiriFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.FormKartuCommunityScreeningFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.KonfirmasiPendaftaranFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.KonfirmasiPendaftaranViewModel;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;
import com.komodoindotech.kihvirtual.ui.review.pendaftaran.ReviewPendaftaranFragment;

public class PendaftaranActivity extends AppCompatActivity {

    private static final String REVIEW_TAG = "review_tag";
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
                replaceFragment(FormKartuCommunityScreeningFragment.newInstance(), "Form");
            } else {
                replaceFragment(KonfirmasiPendaftaranFragment.newInstance());
            }
        });
        pendaftaranViewModel.getFormPagerPositionLiveData().observe(this, position -> {
            pager_position = position;
        });
        pendaftaranViewModel.getOpenReviewLiveData().observe(this, openReview -> {
            if(openReview){
                ReviewPendaftaranFragment bottomSheetDialogFragment = new ReviewPendaftaranFragment();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), REVIEW_TAG);
//                startActivity(new Intent(getApplicationContext(), ReviewPendaftaran.class));
            }
        });

        pendaftaranViewModel.getPreviousPageLiveData().observe(this, isPrevious -> {
            is_previous = isPrevious;
        });

    }

    public void replaceFragment(Fragment fragment, String tag){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, tag)
                .commitNow();
    }
    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("Form");
        if(fragment != null){
            if (pager_position == 0) {
                new MaterialAlertDialogBuilder(this)
                        .setTitle("Konfirmasi Keluar Formulir")
                        .setMessage("Apakah Anda yakin ingin keluar dari formulir, data yang sudah diinput tidak akan disimpan")
                        .setPositiveButton("Keluar", (dialog, which) -> {
                            super.onBackPressed();
                        })
                        .setNegativeButton("Batal", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            } else {
                pendaftaranViewModel.previousPageForm();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().getFragment(outState, FormInfoDataDiriFragment.KEY);
    }
}