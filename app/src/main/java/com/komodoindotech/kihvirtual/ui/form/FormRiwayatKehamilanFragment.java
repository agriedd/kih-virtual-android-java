package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;

import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ui.article_show.ArticleNotFoundFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.InformasiFormRiwayatKehamilanFragment;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;

public class FormRiwayatKehamilanFragment extends Fragment {

    private PendaftaranViewModel pendaftaranViewModel;
    private Boolean statusRiwayatKehamilan;
    private Switch statusRiwayatKehamilanView;

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

        pendaftaranViewModel = new ViewModelProvider(getActivity()).get(PendaftaranViewModel.class);

        statusRiwayatKehamilanView.setOnCheckedChangeListener((buttonView, isChecked) -> {
            pendaftaranViewModel.setRiwayatKehamilanLiveData(isChecked);
        });

        pendaftaranViewModel.getRiwayatKehamilanLiveData().observe(getActivity(), aBoolean -> {
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

    @Override
    public void onStart() {
        super.onStart();

        Balloon balloon = new Balloon.Builder(getContext())
                .setArrowSize(10)
                .setArrowOrientation(ArrowOrientation.TOP)
                .setArrowVisible(true)
                .setWidthRatio(.75f)
                .setHeight(80)
                .setMargin(10)
                .setArrowPosition(0.8f)
                .setText("Geser, jika Ibu memiliki riwayat kehamilan sebelumnya")
                .setTextColor(ContextCompat.getColor(getContext(), R.color.white))
                .setBackgroundColor(ContextCompat.getColor(getContext(), R.color.teal_700))
                .setBalloonAnimation(BalloonAnimation.FADE)
                .setLifecycleOwner(getActivity())
                .build();
        balloon.showAlignBottom(statusRiwayatKehamilanView);
    }

    public void replaceFragment(Fragment fragment){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.riwayat_kehamilan_content, fragment)
                .commitNow();
    }
}