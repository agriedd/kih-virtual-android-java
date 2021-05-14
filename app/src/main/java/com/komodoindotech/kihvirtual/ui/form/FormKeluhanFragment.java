package com.komodoindotech.kihvirtual.ui.form;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ReviewPendaftaran;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerFormKeluhan;
import com.komodoindotech.kihvirtual.json.PilihanObject;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

import java.util.ArrayList;
import java.util.List;

public class FormKeluhanFragment extends Fragment {

    private Toolbar toolbar;
    private RecyclerView listKeluhanView;
    private List<RiwayatKeluhan> pilihanObjects;
    private PendaftaranViewModel pendaftaranViewModel;

    public FormKeluhanFragment() {}

    public static FormKeluhanFragment newInstance() {
        return new FormKeluhanFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_form_keluhan, container, false);

        pendaftaranViewModel = new ViewModelProvider(requireActivity()).get(PendaftaranViewModel.class);

        toolbar = root.findViewById(R.id.toolbar);
        listKeluhanView = root.findViewById(R.id.list_form_keluhan);
        Button buttonReview = root.findViewById(R.id.button_review);
        buttonReview.setOnClickListener(v -> openReview());

        setToolbar();

        initRecyclerView();


        return root;
    }

    private void initRecyclerView() {

        pilihanObjects = new ArrayList<>();
        pilihanObjects.add(new RiwayatKeluhan("klh00", "Silahkan centang pilihan-pilihan dibawah yang pernah dialami Ibu", true));

        pilihanObjects.add(new RiwayatKeluhan(
                "klh01",
                "Kehamilan ini tidak diingikan oleh keluarga",
                PilihanObject.WARNA_KUNING, "konseling"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh02",
                "Ibu hamil merokok atau Ibu hamil tinggal bersama anggota keluarga yang merokok.",
                PilihanObject.WARNA_KUNING, "konseling"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh03",
                "Ibu muntah terus menerus dan tidak mau makan",
                PilihanObject.WARNA_MERAH, "rujuk"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh04",
                "Ibu mengalami pusing /sakit kepala terus menerus",
                PilihanObject.WARNA_MERAH, "rujuk"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh05",
                "Ibu pusing /sakit kepala setiap baru bangun tidur",
                PilihanObject.WARNA_KUNING, "konseling"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh06",
                "Demam tinggi, menggigil dan berkeringat",
                PilihanObject.WARNA_MERAH, "rujuk"));

        pilihanObjects.add(new RiwayatKeluhan(
                "klh07",
                "Bengkak pada kaki, tangan dan wajah",
                PilihanObject.WARNA_MERAH, "rujuk"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh08",
                "Ibu mengalami mata berkunang-kunang",
                PilihanObject.WARNA_MERAH, "rujuk"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh09",
                Html.escapeHtml("Pergerakan janin berkurang (<10 kali/hari)"),
                PilihanObject.WARNA_KUNING, "konseling, rujuk"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh10",
                "Tidak merasakan pergerakan janin",
                PilihanObject.WARNA_MERAH, "rujuk"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh11",
                "Mengalami pendarahan bercak",
                PilihanObject.WARNA_MERAH, "rujuk"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh12",
                "Ibu mengalami pendarahan (darah segar)",
                PilihanObject.WARNA_MERAH, "rujuk"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh13",
                "Ibu mengalami diare berulang",
                PilihanObject.WARNA_KUNING, "konseling, rujuk"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh14",
                "Ibu mengalami nyeri saat buang air kecil",
                PilihanObject.WARNA_KUNING, "konseling, rujuk"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh15",
                "Ibu mengalami keputihan",
                PilihanObject.WARNA_KUNING, "konseling"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh16",
                "Ibu mengalami tidak bisa tidur",
                PilihanObject.WARNA_KUNING, "konseling"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh17",
                "Ibu mengalami jantung berdebar-debar",
                PilihanObject.WARNA_KUNING, "konseling, rujuk"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh18",
                Html.escapeHtml("Umur ibu hamil anak pertama terlalu muda (<16 tahun) atau terlalu tua (> 30 tahun)"),
                PilihanObject.WARNA_KUNING, "konseling"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh19",
                "Jarak dengan kehamilan sebelumnya kurang dari 2 tahun atau lebih dari 10 tahun",
                PilihanObject.WARNA_KUNING, "konseling"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh20",
                "Ibu hamil terlalu kurus atau terlalu gemuk",
                PilihanObject.WARNA_KUNING, "konseling"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh21",
                "Ibu khawatir akan kehamilannya",
                PilihanObject.WARNA_KUNING, "konseling"));
        pilihanObjects.add(new RiwayatKeluhan(
                "klh22",
                "Ibu khawatir dengan proses persalinan yang akan dihadapi",
                PilihanObject.WARNA_KUNING, "konseling"));

        AdapterRecyclerFormKeluhan adapterRecyclerFormKeluhan = new AdapterRecyclerFormKeluhan(getContext(), pilihanObjects, listener);

        listKeluhanView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listKeluhanView.setHasFixedSize(true);
        listKeluhanView.setAdapter(adapterRecyclerFormKeluhan);

    }

    private void setToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> pendaftaranViewModel.previousPageForm());
    }

    private void openReview() {
//        pendaftaranViewModel.storePendaftaran();
        pendaftaranViewModel.openReview();
    }
    AdapterRecyclerFormKeluhan.onCheckedChangeListener listener = (status, position) -> {
        pendaftaranViewModel.setRiwayatKeluhanObjectLiveData(pilihanObjects);
    };
}