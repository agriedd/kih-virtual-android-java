package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ui.pendaftaran.InformasiFormRiwayatPersalinanFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

public class FormRiwayatPersalinanFragment extends Fragment {

    private Switch statusRiwayatPersalinanView;
    private PendaftaranViewModel pendaftaranViewModel;
    private boolean statusRiwayatPersalinan;

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

        pendaftaranViewModel = new ViewModelProvider(requireActivity()).get(PendaftaranViewModel.class);

        statusRiwayatPersalinanView.setOnCheckedChangeListener((buttonView, isChecked) -> {
            pendaftaranViewModel.setRiwayatPersalinanLiveData(isChecked);
        });

        pendaftaranViewModel.getRiwayatPersalinanLiveData().observe(getActivity(), aBoolean -> {
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
    public void replaceFragment(Fragment fragment){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.riwayat_persalinan_content, fragment)
                .commitNow();
    }
}