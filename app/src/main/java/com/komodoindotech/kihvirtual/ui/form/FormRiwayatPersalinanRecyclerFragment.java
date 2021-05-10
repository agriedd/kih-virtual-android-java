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
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerFormRiwayatPersalinan;
import com.komodoindotech.kihvirtual.json.PilihanObject;

import java.util.ArrayList;
import java.util.List;

public class FormRiwayatPersalinanRecyclerFragment extends Fragment {

    private RecyclerView recyclerViewFormRiwayatPersalinan;
    private List<PilihanObject> pilihanObjects;
    private AdapterRecyclerFormRiwayatPersalinan adapterRecyclerFormRiwayatPersalinan;

    public FormRiwayatPersalinanRecyclerFragment() {
    }

    public static FormRiwayatPersalinanRecyclerFragment newInstance() {
        return new FormRiwayatPersalinanRecyclerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_form_riwayat_persalinan_recycler, container, false);

        recyclerViewFormRiwayatPersalinan = root.findViewById(R.id.recycler_form_riwayat_persalinan);

        pilihanObjects = new ArrayList<>();
        PilihanObject pilihanObject = new PilihanObject("Silahkan centang pilihan-pilihan dibawah yang pernah dialami Ibu");
        pilihanObject.setDisabled(true);
        pilihanObjects.add(pilihanObject);
        pilihanObjects.add(new PilihanObject("Ibu mengalami persalinan dengan tindakan: <br>- Induksi atau rangsang <br>- Valum <br>- Forseps"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami persalinan sebelum waktunya/prematur"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami persalinan lewat waktu"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami persalinan operasi sectio caesarea"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami pecah ketuban sebelum waktunya"));
        pilihanObjects.add(new PilihanObject("Ibu melahirkan bayi berat lahir rendah (BB kurang dari 2500 gr)"));
        pilihanObjects.add(new PilihanObject("Ibu melahirkan bayi besar (BB lahir lebih dari sama dengan 4000 gr)"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami penyakit infeksi (malaria, campak, cacar)"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami kehamilan kembar"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami hamil anggur"));
        pilihanObjects.add(new PilihanObject("Ibu mengalami keguguran"));
        pilihanObjects.add(new PilihanObject("Ibu melahirkan bayi meninggal"));

        adapterRecyclerFormRiwayatPersalinan = new AdapterRecyclerFormRiwayatPersalinan(getContext(), pilihanObjects);
        recyclerViewFormRiwayatPersalinan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewFormRiwayatPersalinan.setAdapter(adapterRecyclerFormRiwayatPersalinan);

        return root;
    }
}