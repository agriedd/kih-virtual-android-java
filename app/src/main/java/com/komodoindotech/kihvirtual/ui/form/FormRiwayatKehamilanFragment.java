package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ui.pendaftaran.InformasiFormRiwayatKehamilanFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

public class FormRiwayatKehamilanFragment extends Fragment {

    private PendaftaranViewModel pendaftaranViewModel;
    private Boolean statusRiwayatKehamilan;
    private SwitchMaterial statusRiwayatKehamilanView;
    private MaterialToolbar toolbar;

    public FormRiwayatKehamilanFragment() {

    }

    public static FormRiwayatKehamilanFragment newInstance() {
        return new FormRiwayatKehamilanFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_from_riwayat_kehamilan, container, false);

        statusRiwayatKehamilanView = root.findViewById(R.id.status_riwayat_kehamilan);
        toolbar = root.findViewById(R.id.toolbar);
        initToolbar();

        pendaftaranViewModel = new ViewModelProvider(requireActivity()).get(PendaftaranViewModel.class);

        statusRiwayatKehamilanView.setOnCheckedChangeListener((buttonView, isChecked) -> pendaftaranViewModel.setRiwayatKehamilanLiveData(isChecked));

        pendaftaranViewModel.getRiwayatKehamilanLiveData().observe(requireActivity(), aBoolean -> {
            statusRiwayatKehamilan = aBoolean;
            statusRiwayatKehamilanView.setChecked(aBoolean);
            if(statusRiwayatKehamilan){
                replaceFragment(FormRiwayatKehamilanRecyclerFragment.newInstance());
            } else {
                replaceFragment(InformasiFormRiwayatKehamilanFragment.newInstance());
            }
        });

        replaceFragment(InformasiFormRiwayatKehamilanFragment.newInstance());

        return root;
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        toolbar.setNavigationOnClickListener(v -> pendaftaranViewModel.previousPageForm());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onStart() {
        super.onStart();

//        Balloon balloon = new Balloon.Builder(requireContext())
//                .setArrowSize(10)
//                .setArrowOrientation(ArrowOrientation.TOP)
//                .setArrowVisible(true)
//                .setWidthRatio(.75f)
//                .setHeight(80)
//                .setMargin(10)
//                .setArrowPosition(0.8f)
//                .setText("Geser, jika Ibu memiliki riwayat kehamilan sebelumnya")
//                .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//                .setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.teal_700))
//                .setBalloonAnimation(BalloonAnimation.FADE)
//                .setLifecycleOwner(getActivity())
//                .build();
//        balloon.showAlignBottom(statusRiwayatKehamilanView);
    }

    public void replaceFragment(Fragment fragment){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.riwayat_kehamilan_content, fragment)
                .commitNow();
    }
}