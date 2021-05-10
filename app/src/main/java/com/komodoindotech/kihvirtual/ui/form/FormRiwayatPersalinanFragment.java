package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ui.pendaftaran.InformasiFormRiwayatKehamilanFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.InformasiFormRiwayatPersalinanFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;

public class FormRiwayatPersalinanFragment extends Fragment {

    private Switch statusRiwayatKehamilanView;
    private PendaftaranViewModel pendaftaranViewModel;
    private boolean statusRiwayatKehamilan;

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

        statusRiwayatKehamilanView = root.findViewById(R.id.status_riwayat_persalinan);

        pendaftaranViewModel = new ViewModelProvider(getActivity()).get(PendaftaranViewModel.class);

        statusRiwayatKehamilanView.setOnCheckedChangeListener((buttonView, isChecked) -> {
            pendaftaranViewModel.setRiwayatKehamilanLiveData(isChecked);
        });

        pendaftaranViewModel.getRiwayatKehamilanLiveData().observe(getActivity(), aBoolean -> {
            statusRiwayatKehamilan = aBoolean;
            statusRiwayatKehamilanView.setChecked(aBoolean);
            if(statusRiwayatKehamilan){
                replaceFragment(FormRiwayatPersalinanRecyclerFragment.newInstance());
            } else {
                replaceFragment(InformasiFormRiwayatPersalinanFragment.newInstance());
            }
        });

//        Balloon balloon = new Balloon.Builder(getContext())
//                .setArrowSize(10)
//                .setArrowOrientation(ArrowOrientation.TOP)
//                .setArrowVisible(true)
//                .setWidthRatio(.75f)
//                .setHeight(80)
//                .setMargin(10)
//                .setArrowPosition(0.8f)
//                .setText("Geser, jika Ibu memiliki riwayat persalinan sebelumnya")
//                .setTextColor(ContextCompat.getColor(getContext(), R.color.white))
//                .setBackgroundColor(ContextCompat.getColor(getContext(), R.color.teal_700))
//                .setBalloonAnimation(BalloonAnimation.FADE)
//                .setLifecycleOwner(getActivity())
//                .build();
//        balloon.showAlignBottom(statusRiwayatKehamilanView);
        replaceFragment(InformasiFormRiwayatPersalinanFragment.newInstance());

        return root;
    }
    public void replaceFragment(Fragment fragment){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.riwayat_persalinan_content, fragment)
                .commitNow();
    }
}