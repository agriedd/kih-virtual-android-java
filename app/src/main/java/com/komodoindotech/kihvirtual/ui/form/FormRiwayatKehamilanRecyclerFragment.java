package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerFormRiwayatKehamilan;
import com.komodoindotech.kihvirtual.json.PilihanObject;

import java.util.ArrayList;
import java.util.List;

public class FormRiwayatKehamilanRecyclerFragment extends Fragment {

    RecyclerView recyclerViewFormRiwayatKehamilan;
    List<PilihanObject> pilihanObjects;
    AdapterRecyclerFormRiwayatKehamilan adapterRecyclerFormRiwayatKehamilan;

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

        recyclerViewFormRiwayatKehamilan = root.findViewById(R.id.recycler_form_riwayat_kehamilan);

        pilihanObjects = new ArrayList<>();
        PilihanObject pilihanObject = new PilihanObject("Silahkan centang pilihan-pilihan dibawah yang pernah dialami Ibu");
        pilihanObject.setDisabled(true);
        pilihanObjects.add(pilihanObject);
        pilihanObjects.add(new PilihanObject("Ibu mengalami tekanan darah tinggi"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami pendarahan"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami mual muntah berlebihan hingga perlu dirawat di RS"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami bed rest selama masa kehamilan"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami mengalami sesak napas"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami bengkak pada alat gerak dan mata berkunang kunang"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami gangguan perkemihan (Infeksi Saluran Kencing, Tidak dapat Miksi)"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami penyakit infeksi (malaria, campak, cacar)"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami kehamilan kembar"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami hamil anggur"));

        adapterRecyclerFormRiwayatKehamilan = new AdapterRecyclerFormRiwayatKehamilan(getContext(), pilihanObjects);
        recyclerViewFormRiwayatKehamilan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewFormRiwayatKehamilan.setAdapter(adapterRecyclerFormRiwayatKehamilan);
        return root;
    }
}