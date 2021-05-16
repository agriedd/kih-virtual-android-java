package com.komodoindotech.kihvirtual;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;
import com.komodoindotech.kihvirtual.ui.kelas.KelasRecycler;
import com.komodoindotech.kihvirtual.ui.kesimpulan.KesimpulanViewModel;
import com.komodoindotech.kihvirtual.ui.puskesmas.PuskesmasViewModel;
import com.komodoindotech.kihvirtual.ui.review.InfoDataFragment;

public class KesimpulanHijau extends Fragment {

    private PuskesmasViewModel puskesmasViewModel;
    private KesimpulanViewModel kesimpulanViewModel;
    private ExtendedFloatingActionButton extendedFloatingActionButton;
    private PendaftaranDanRiwayat pendaftaranDanRiwayat;

    public KesimpulanHijau() {
    }

    public static KesimpulanHijau newInstance() {
        return new KesimpulanHijau();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kesimpulanViewModel = new ViewModelProvider(requireActivity()).get(KesimpulanViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kesimpulan_hijau, container, false);
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