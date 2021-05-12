package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerFormRiwayatKehamilan;
import com.komodoindotech.kihvirtual.json.PilihanObject;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

import java.util.ArrayList;
import java.util.List;

public class FormRiwayatKehamilanRecyclerFragment extends Fragment {

    RecyclerView recyclerViewFormRiwayatKehamilan;
    List<RiwayatKehamilan> pilihanObjects;
    AdapterRecyclerFormRiwayatKehamilan adapterRecyclerFormRiwayatKehamilan;
    private PendaftaranViewModel pendaftaranViewModel;

    public FormRiwayatKehamilanRecyclerFragment() {
    }

    public static FormRiwayatKehamilanRecyclerFragment newInstance() {
        return new FormRiwayatKehamilanRecyclerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_from_riwayat_kehamilan_recycler, container, false);

        pendaftaranViewModel = new ViewModelProvider(requireActivity()).get(PendaftaranViewModel.class);

        recyclerViewFormRiwayatKehamilan = root.findViewById(R.id.recycler_form_riwayat_kehamilan);

        pilihanObjects = new ArrayList<>();
        pilihanObjects.add(new RiwayatKehamilan("Silahkan centang pilihan-pilihan dibawah yang pernah dialami Ibu", true));
        pilihanObjects.add(new RiwayatKehamilan("rk01", "Ibu mengalami tekanan darah tinggi", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatKehamilan("rk02", "Ibu mengalami pendarahan", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatKehamilan("rk03","Ibu mengalami mual muntah berlebihan hingga perlu dirawat di RS", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatKehamilan("rk04","Ibu mengalami bed rest selama masa kehamilan", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatKehamilan("rk05","Ibu mengalami sesak napas", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatKehamilan("rk06","Ibu mengalami bengkak pada alat gerak dan mata berkunang kunang", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatKehamilan("rk07","Ibu mengalami gangguan perkemihan (Infeksi Saluran Kencing, Tidak dapat Miksi)", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatKehamilan("rk08","Ibu mengalami penyakit infeksi (malaria, campak, cacar)", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatKehamilan("rk09","Ibu mengalami kehamilan kembar", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatKehamilan("rk10","Ibu mengalami hamil anggur", PilihanObject.WARNA_KUNING, "konsultasi"));

        adapterRecyclerFormRiwayatKehamilan = new AdapterRecyclerFormRiwayatKehamilan(getContext(), pilihanObjects, listener);
        recyclerViewFormRiwayatKehamilan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewFormRiwayatKehamilan.setAdapter(adapterRecyclerFormRiwayatKehamilan);
        return root;
    }

    private final AdapterRecyclerFormRiwayatKehamilan.onCheckedChangeListener listener = new AdapterRecyclerFormRiwayatKehamilan.onCheckedChangeListener() {
        @Override
        public void change(Boolean isChecked) {
            pendaftaranViewModel.setRiwayatKehamilanObjectLiveData(pilihanObjects);
        }
    };
}