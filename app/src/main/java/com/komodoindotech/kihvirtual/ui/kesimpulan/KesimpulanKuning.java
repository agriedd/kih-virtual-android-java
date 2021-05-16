package com.komodoindotech.kihvirtual.ui.kesimpulan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;
import com.komodoindotech.kihvirtual.ui.kelas.KelasRecycler;
import com.komodoindotech.kihvirtual.ui.puskesmas.PuskesmasRecycler;
import com.komodoindotech.kihvirtual.ui.puskesmas.PuskesmasViewModel;
import com.komodoindotech.kihvirtual.ui.review.InfoDataFragment;

public class KesimpulanKuning extends Fragment {

    private PuskesmasViewModel puskesmasViewModel;
    private KesimpulanViewModel kesimpulanViewModel;
    private ExtendedFloatingActionButton extendedFloatingActionButton;
    private PendaftaranDanRiwayat pendaftaranDanRiwayat;

    public static KesimpulanKuning newInstance() {
        KesimpulanKuning fragment = new KesimpulanKuning();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kesimpulanViewModel = new ViewModelProvider(requireActivity()).get(KesimpulanViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kesimpulan_kuning, container, false);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.daftar_puskesmas, PuskesmasRecycler.newInstance())
                .commitNow();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.daftar_kelas, KelasRecycler.newInstance())
                .commitNow();
        extendedFloatingActionButton = root.findViewById(R.id.lihat_data);
        kesimpulanViewModel.getPendaftaran().observe(requireActivity(), this::prosesKesimpulan);
        extendedFloatingActionButton.setOnClickListener(v -> {
            InfoDataFragment bottomSheetDialogFragment = new InfoDataFragment(
                    pendaftaranDanRiwayat.pendaftaran, pendaftaranDanRiwayat.riwayatKehamilans,
                    pendaftaranDanRiwayat.riwayatPersalinans, pendaftaranDanRiwayat.riwayatImunisasis,
                    pendaftaranDanRiwayat.riwayatKeluhans
            );
            bottomSheetDialogFragment.show(getChildFragmentManager(), "info");
        });
        return root;
    }
    private void prosesKesimpulan(PendaftaranDanRiwayat pendaftaranDanRiwayat) {
        this.pendaftaranDanRiwayat = pendaftaranDanRiwayat;
    }
}