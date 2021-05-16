package com.komodoindotech.kihvirtual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
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
            }
        });

        pendaftaranViewModel.getPreviousPageLiveData().observe(this, isPrevious -> {
            is_previous = isPrevious;
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customView = getLayoutInflater().inflate(R.layout.progress_bar_loading_bar, null);
        TextView messageDialog = customView.findViewById(R.id.text_progress_bar);
        messageDialog.setText("Sedang memproses...");
        builder.setView(customView);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        pendaftaranViewModel.getLoading().observe(this, loading -> {
            if(loading){
                dialog.show();
            } else {
                dialog.dismiss();
            }
        });

        pendaftaranViewModel.getErrorGlobal().observe(this, message -> {
            final Snackbar snackbar = Snackbar.make(findViewById(R.id.container), message, Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("Tutup", v -> snackbar.dismiss());
            snackbar.show();
        });

        pendaftaranViewModel.getGotoKesimpulan().observe(this, status -> {
            if(status){
                Intent intent = new Intent(this, KesimpulanActivity.class);
                intent.putExtra("id_pendaftaran", pendaftaranViewModel.id_pendaftaran);
                intent.putExtra("id_pendaftaran_cloud", pendaftaranViewModel.id_pendaftaran_cloud);
                startActivity(intent);
                finish();
            }
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
                        .setPositiveButton("Keluar", (dialog, which) -> super.onBackPressed())
                        .setNegativeButton("Batal", (dialog, which) -> dialog.dismiss())
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