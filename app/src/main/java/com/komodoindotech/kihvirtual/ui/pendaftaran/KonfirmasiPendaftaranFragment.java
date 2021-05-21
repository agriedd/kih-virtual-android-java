package com.komodoindotech.kihvirtual.ui.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.komodoindotech.kihvirtual.R;

public class KonfirmasiPendaftaranFragment extends Fragment {

    public static final String TAG = "konfrimasipendaftaranfragment";
    private KonfirmasiPendaftaranViewModel mViewModel;
    private WebView persetujuan_pengguna;
    private KonfirmasiPendaftaranViewModel konfirmasiPendaftaranViewModel;
    private Boolean status_agreement = false;
    private CheckBox checkBoxSetuju;
    private Button buttonSelanjutnya;
    private MaterialToolbar toolbar;

    public static KonfirmasiPendaftaranFragment newInstance() {
        return new KonfirmasiPendaftaranFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.konfirmasi_pendaftaran_fragment, container, false);

        persetujuan_pengguna = root.findViewById(R.id.persetujuan_data_pengguna_webview);
        buttonSelanjutnya = root.findViewById(R.id.button_berikutnya);
        checkBoxSetuju = root.findViewById(R.id.checkbox_setuju);
        toolbar = root.findViewById(R.id.toolbar);

        iniToolbar();

        checkBoxSetuju.setOnCheckedChangeListener((buttonView, isChecked) -> {
            status_agreement = isChecked;
            if(isChecked){
                buttonSelanjutnya.setEnabled(true);
            } else {
                buttonSelanjutnya.setEnabled(false);
            }
        });
        buttonSelanjutnya.setOnClickListener(v -> {
            if(status_agreement){
                konfirmasiPendaftaranViewModel.setAgreementStatus(status_agreement);
            }
        });

        konfirmasiPendaftaranViewModel = new ViewModelProvider(requireActivity()).get(KonfirmasiPendaftaranViewModel.class);
        konfirmasiPendaftaranViewModel.getAgreementStatusLiveData().observe(requireActivity(), status -> {
            status_agreement = status;
            checkBoxSetuju.setChecked(status);
        });
        konfirmasiPendaftaranViewModel.getUrlLiveData().observe(requireActivity(), url -> {
            if(!status_agreement){
                persetujuan_pengguna.loadUrl(url);
            }
        });
        return root;
    }

    private void iniToolbar() {
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KonfirmasiPendaftaranViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}