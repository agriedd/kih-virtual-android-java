package com.komodoindotech.kihvirtual.ui.data;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komodoindotech.kihvirtual.KesimpulanActivity;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerDataPendaftaran;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;

import java.util.ArrayList;
import java.util.List;

public class DaftarDataFragment extends Fragment {

    private DaftarDataViewModel mViewModel;
    private List<PendaftaranDanRiwayat> pendaftaranDanRiwayatList;
    private RecyclerView recyclerView;
    private AdapterRecyclerDataPendaftaran adapterRecyclerDataPendaftaran;

    public static DaftarDataFragment newInstance() {
        return new DaftarDataFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.daftar_data_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(DaftarDataViewModel.class);
        pendaftaranDanRiwayatList = new ArrayList<>();

        pendaftaranDanRiwayatList = mViewModel.pendaftaranDanRiwayatLiveData.getValue();
        adapterRecyclerDataPendaftaran = new AdapterRecyclerDataPendaftaran(requireContext(), pendaftaranDanRiwayatList, getChildFragmentManager(), new AdapterRecyclerDataPendaftaran.onClickListener() {
            @Override
            public void onClick(PendaftaranDanRiwayat pendaftaranDanRiwayat) {
                Intent intent = new Intent(requireContext(), KesimpulanActivity.class);
                intent.putExtra("id_pendaftaran_cloud", pendaftaranDanRiwayat.pendaftaran.getUid());
                startActivity(intent);
            }
        });

        recyclerView = root.findViewById(R.id.daftar_data_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterRecyclerDataPendaftaran);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}