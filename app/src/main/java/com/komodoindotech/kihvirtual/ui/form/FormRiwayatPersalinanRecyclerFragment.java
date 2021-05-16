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
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerFormRiwayatPersalinan;
import com.komodoindotech.kihvirtual.json.PilihanObject;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

import java.util.ArrayList;
import java.util.List;

public class FormRiwayatPersalinanRecyclerFragment extends Fragment {

    private RecyclerView recyclerViewFormRiwayatPersalinan;
    private List<RiwayatPersalinan> pilihanObjects;
    private PendaftaranViewModel pendaftaranViewModel;

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

        pendaftaranViewModel = new ViewModelProvider(requireActivity()).get(PendaftaranViewModel.class);

        recyclerViewFormRiwayatPersalinan = root.findViewById(R.id.recycler_form_riwayat_persalinan);

        initRecycler();

        return root;
    }

    private void initRecycler() {

        pilihanObjects = new ArrayList<>();
        pilihanObjects.add(new RiwayatPersalinan("Silahkan centang pilihan-pilihan dibawah yang pernah dialami Ibu", true));

        pilihanObjects.add(new RiwayatPersalinan("rp01", "Ibu mengalami persalinan dengan tindakan: <br>- Induksi atau rangsang <br>- Valum <br>- Forseps", PilihanObject.WARNA_MERAH, "rujuk"));
        pilihanObjects.add(new RiwayatPersalinan("rp02", "Ibu mengalami persalinan sebelum waktunya/prematur", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatPersalinan("rp03", "Ibu mengalami persalinan lewat waktu", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatPersalinan("rp04", "Ibu mengalami persalinan operasi sectio caesarea", PilihanObject.WARNA_MERAH, "rujuk"));
        pilihanObjects.add(new RiwayatPersalinan("rp05", "Ibu mengalami pecah ketuban sebelum waktunya", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatPersalinan("rp06", "Ibu melahirkan bayi berat lahir rendah (BB kurang dari 2500 gr)", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatPersalinan("rp07", "Ibu melahirkan bayi besar (BB lahir lebih dari sama dengan 4000 gr)", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatPersalinan("rp08", "Ibu mengalami penyakit infeksi (malaria, campak, cacar)", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatPersalinan("rp09", "Ibu mengalami kehamilan kembar", PilihanObject.WARNA_KUNING, "konsultasi"));
        pilihanObjects.add(new RiwayatPersalinan("rp10", "Ibu mengalami hamil anggur", PilihanObject.WARNA_MERAH, "rujuk"));
        pilihanObjects.add(new RiwayatPersalinan("rp11", "Ibu mengalami keguguran", PilihanObject.WARNA_MERAH, "rujuk"));
        pilihanObjects.add(new RiwayatPersalinan("rp12", "Ibu melahirkan bayi meninggal", PilihanObject.WARNA_KUNING, "konsultasi"));

        AdapterRecyclerFormRiwayatPersalinan adapterRecyclerFormRiwayatPersalinan = new AdapterRecyclerFormRiwayatPersalinan(getContext(), pilihanObjects, listener);
        recyclerViewFormRiwayatPersalinan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewFormRiwayatPersalinan.setAdapter(adapterRecyclerFormRiwayatPersalinan);
    }

    private final AdapterRecyclerFormRiwayatPersalinan.onCheckedChangeListener listener = status -> pendaftaranViewModel.setRiwayatPersalinanObjectLiveData(pilihanObjects);
}