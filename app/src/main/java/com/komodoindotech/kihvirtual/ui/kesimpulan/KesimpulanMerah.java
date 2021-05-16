package com.komodoindotech.kihvirtual.ui.kesimpulan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.json.PilihanObject;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;
import com.komodoindotech.kihvirtual.models.RiwayatContract;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;
import com.komodoindotech.kihvirtual.ui.kelas.KelasRecycler;
import com.komodoindotech.kihvirtual.ui.puskesmas.PuskesmasRecycler;
import com.komodoindotech.kihvirtual.ui.puskesmas.PuskesmasViewModel;
import com.komodoindotech.kihvirtual.ui.review.InfoDataFragment;
import com.komodoindotech.kihvirtual.ui.review.pendaftaran.ReviewPendaftaranFragment;

import java.util.ArrayList;
import java.util.List;

public class KesimpulanMerah extends Fragment {

    private PuskesmasViewModel puskesmasViewModel;
    private KesimpulanViewModel kesimpulanViewModel;
    private ExtendedFloatingActionButton extendedFloatingActionButton;
    private PendaftaranDanRiwayat pendaftaranDanRiwayat;

    public KesimpulanMerah() {}

    public static KesimpulanMerah newInstance() {
        return new KesimpulanMerah();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        puskesmasViewModel = new ViewModelProvider(requireActivity()).get(PuskesmasViewModel.class);
        kesimpulanViewModel = new ViewModelProvider(requireActivity()).get(KesimpulanViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kesimpulan_merah, container, false);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.daftar_puskesmas, PuskesmasRecycler.newInstance())
                .commitNow();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.daftar_kelas, KelasRecycler.newInstance())
                .commitNow();

        extendedFloatingActionButton = root.findViewById(R.id.lihat_data);
        pendaftaranDanRiwayat = kesimpulanViewModel.getPendaftaranDanRiwayat();
        if(pendaftaranDanRiwayat != null){
            extendedFloatingActionButton.setOnClickListener(v -> {
                InfoDataFragment bottomSheetDialogFragment = new InfoDataFragment(
                        pendaftaranDanRiwayat.pendaftaran, pendaftaranDanRiwayat.riwayatKehamilans,
                        pendaftaranDanRiwayat.riwayatPersalinans, pendaftaranDanRiwayat.riwayatImunisasis,
                        pendaftaranDanRiwayat.riwayatKeluhans
                );
                bottomSheetDialogFragment.show(getChildFragmentManager(), "info");
            });
        }
        return root;
    }
}