package com.komodoindotech.kihvirtual.ui.review.pendaftaran;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerContainers;
import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;
import com.komodoindotech.kihvirtual.models.RiwayatContract;
import com.komodoindotech.kihvirtual.models.RiwayatGroup;
import com.komodoindotech.kihvirtual.models.RiwayatImunisasi;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ReviewPendaftaranFragment extends BottomSheetDialogFragment {

    private static final String TAG = "reviewpendaftaran";
    private PendaftaranViewModel pendaftaranViewModel;
    private View root;

    private TextView nama, umur, alamat, hamil_ke, pendidikan_ibu, pendidikan_suami,
            pekerjaan_ibu, pekerjaan_suami, haid_terakhir, usia_anak_terakhir, lama_pernikahan;
    private RecyclerView listRiwayatView;
    private List<RiwayatGroup> riwayatGroupList;
    private MaterialButton proses;

    public static ReviewPendaftaranFragment newInstance() {
        return new ReviewPendaftaranFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.review_pendaftaran_fragment, container, false);

        pendaftaranViewModel = new ViewModelProvider(requireActivity()).get(PendaftaranViewModel.class);

        initView();

        loadData();
        initRecyclerView();

        return root;
    }

    private void initRecyclerView() {
        listRiwayatView.setHasFixedSize(true);
        listRiwayatView.setNestedScrollingEnabled(true);
        listRiwayatView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        listRiwayatView.setAdapter(
                new AdapterRecyclerContainers(requireContext(), riwayatGroupList)
        );
    }

    private void loadData() {
        Pendaftaran pendaftaran = pendaftaranViewModel.getPendaftaranObject().getValue();
        List<RiwayatKehamilan> riwayatKehamilanList = pendaftaranViewModel.getRiwayatKehamilanObjectLiveData().getValue();
        List<RiwayatPersalinan> riwayatPersalinanList = pendaftaranViewModel.getRiwayatPersalinanObjectLiveData().getValue();
        List<RiwayatImunisasi> riwayatImunisasiList = pendaftaranViewModel.getRiwayatImunisasiObjectLiveData().getValue();
        List<RiwayatKeluhan> riwayatKeluhanList = pendaftaranViewModel.getRiwayatKeluhanObjectLiveData().getValue();
        bindView(pendaftaran, riwayatKehamilanList, riwayatPersalinanList, riwayatImunisasiList, riwayatKeluhanList);
    }

    private void bindView(Pendaftaran pendaftaran, List<RiwayatKehamilan> riwayatKehamilanList, List<RiwayatPersalinan> riwayatPersalinanList, List<RiwayatImunisasi> riwayatImunisasiList, List<RiwayatKeluhan> riwayatKeluhanList) {
        String value_umur = pendaftaran.umur + " Tahun";
        String value_hamil_ke;
        if(pendaftaran.hamil_ke == 1){
             value_hamil_ke = "Hamil yang pertama";
        } else {
            value_hamil_ke = "Hamil yang ke-" + pendaftaran.hamil_ke;
        }
        String value_lama_pernikahan = pendaftaran.lama_menikah + " Tahun";
        nama.setText(pendaftaran.nama.toUpperCase());
        umur.setText(value_umur.toUpperCase());
        alamat.setText(pendaftaran.alamat.toUpperCase());
        hamil_ke.setText(value_hamil_ke.toUpperCase());
        pendidikan_ibu.setText(pendaftaran.pendidikan_istri.toUpperCase());
        pendidikan_suami.setText(pendaftaran.pendidikan_suami.toUpperCase());
        pekerjaan_ibu.setText(pendaftaran.pekerjaan_istri.toUpperCase());
        pekerjaan_suami.setText(pendaftaran.pekerjaan_suami.toUpperCase());
        if(pendaftaran.usia_anak_terakhir != null){
            String value_usia_anak_terakhir = pendaftaran.usia_anak_terakhir + " Tahun";
            usia_anak_terakhir.setText(value_usia_anak_terakhir.toUpperCase());
        } else {
            usia_anak_terakhir.setText("-");
        }
        lama_pernikahan.setText(value_lama_pernikahan.toUpperCase());
        if(pendaftaran.haid_terakhir != null){
            haid_terakhir.setText(
                    new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                            .format(new Date(pendaftaran.haid_terakhir))
            );
        }

        initRiwayatData(riwayatKehamilanList, riwayatPersalinanList, riwayatImunisasiList, riwayatKeluhanList);

    }

    private void initRiwayatData(List<RiwayatKehamilan> riwayatKehamilans, List<RiwayatPersalinan> riwayatPersalinans, List<RiwayatImunisasi> riwayatImunisasis, List<RiwayatKeluhan> riwayatKeluhans) {
        riwayatGroupList = new ArrayList<>();
        List<RiwayatContract> riwayatKehamilanList = new ArrayList<>(riwayatKehamilans);
        riwayatGroupList.add(
                new RiwayatGroup(RiwayatGroup.ID_KEHAMILAN, "Riwayat Kehamilah", "Masalah Riwayat Kehamilan", riwayatKehamilanList)
        );
        List<RiwayatContract> riwayatPersalinanList = new ArrayList<>(riwayatPersalinans);
        riwayatGroupList.add(
                new RiwayatGroup(RiwayatGroup.ID_PERSALINAN, "Riwayat Persalinan", "Masalah Riwayat Persalinan", riwayatPersalinanList)
        );
        List<RiwayatContract> riwayatImunisasiList = new ArrayList<>(riwayatImunisasis);
        riwayatGroupList.add(
                new RiwayatGroup(RiwayatGroup.ID_IMUNISASI, "Riwayat Imunisasi", "Tanggal Imunisasi", riwayatImunisasiList)
        );
        List<RiwayatContract> riwayatKeluhanList = new ArrayList<>(riwayatKeluhans);
        riwayatGroupList.add(
                new RiwayatGroup(RiwayatGroup.ID_KELUHAN, "Keluhan", "Keluhan yang dirasakan saat ini", riwayatKeluhanList)
        );
    }

    private void initView() {
        nama = root.findViewById(R.id.nama);
        umur = root.findViewById(R.id.umur);
        alamat = root.findViewById(R.id.alamat);
        hamil_ke = root.findViewById(R.id.hamil_ke);
        pendidikan_ibu = root.findViewById(R.id.pendidikan_istri);
        pendidikan_suami = root.findViewById(R.id.pendidikan_suami);
        pekerjaan_ibu = root.findViewById(R.id.pekerjaan_istri);
        pekerjaan_suami = root.findViewById(R.id.pekerjaan_suami);
        haid_terakhir = root.findViewById(R.id.haid_terakhir);
        usia_anak_terakhir = root.findViewById(R.id.usia_anak_terakhir);
        lama_pernikahan = root.findViewById(R.id.lama_menikah);
        listRiwayatView = root.findViewById(R.id.list_riwayat);

        proses = root.findViewById(R.id.proses);
        proses.setOnClickListener(
                storeData
        );
    }

    private final View.OnClickListener storeData = v -> pendaftaranViewModel.storePendaftaran();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }
}