package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ui.pendaftaran.InformasiFormRiwayatPersalinanFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

public class FormRiwayatPersalinanFragment extends Fragment {

    private SwitchMaterial statusRiwayatPersalinanView;
    private PendaftaranViewModel pendaftaranViewModel;
    private boolean statusRiwayatPersalinan;
    private MaterialToolbar toolbar;

    public FormRiwayatPersalinanFragment() {
    }

    public static FormRiwayatPersalinanFragment newInstance() {
        return new FormRiwayatPersalinanFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_form_riwayat_persalinan, container, false);

        statusRiwayatPersalinanView = root.findViewById(R.id.status_riwayat_persalinan);
        toolbar = root.findViewById(R.id.toolbar);

        initToolbar();

        pendaftaranViewModel = new ViewModelProvider(requireActivity()).get(PendaftaranViewModel.class);

        statusRiwayatPersalinanView.setOnCheckedChangeListener((buttonView, isChecked) -> pendaftaranViewModel.setRiwayatPersalinanLiveData(isChecked));

        pendaftaranViewModel.getRiwayatPersalinanLiveData().observe(requireActivity(), aBoolean -> {
            statusRiwayatPersalinan = aBoolean;
            statusRiwayatPersalinanView.setChecked(aBoolean);
            if(statusRiwayatPersalinan){
                replaceFragment(FormRiwayatPersalinanRecyclerFragment.newInstance());
            } else {
                replaceFragment(InformasiFormRiwayatPersalinanFragment.newInstance());
            }
        });

        replaceFragment(InformasiFormRiwayatPersalinanFragment.newInstance());

        return root;
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
    }

    public void replaceFragment(Fragment fragment){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.riwayat_persalinan_content, fragment)
                .commitNow();
    }
}