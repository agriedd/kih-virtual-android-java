package com.komodoindotech.kihvirtual.ui.form;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ReviewPendaftaran;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerFormKeluhan;
import com.komodoindotech.kihvirtual.json.PilihanObject;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

import java.util.ArrayList;
import java.util.List;

public class FormKeluhanFragment extends Fragment {

    private Toolbar toolbar;
    private RecyclerView listKeluhanView;
    private AdapterRecyclerFormKeluhan adapterRecyclerFormKeluhan;
    private List<PilihanObject> pilihanObjects;
    private PendaftaranViewModel pendaftaranViewModel;

    public FormKeluhanFragment() {}

    public static FormKeluhanFragment newInstance() {
        FormKeluhanFragment fragment = new FormKeluhanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_form_keluhan, container, false);

        pendaftaranViewModel = new ViewModelProvider(getActivity()).get(PendaftaranViewModel.class);

        toolbar = root.findViewById(R.id.toolbar_keluhan);
        listKeluhanView = root.findViewById(R.id.list_form_keluhan);
        Button buttonReview = root.findViewById(R.id.button_review);
        buttonReview.setOnClickListener(v -> {
            openReview();
        });

        setToolbar();

        initRecyclerView();


        return root;
    }

    private void initRecyclerView() {

        pilihanObjects = new ArrayList<>();
        PilihanObject pilihanObject = new PilihanObject("Silahkan centang pilihan-pilihan dibawah yang pernah dialami Ibu");
        pilihanObject.setDisabled(true);
        pilihanObjects.add(pilihanObject);

        pilihanObjects.add(new PilihanObject(
                "klh01",
                "Kehamilan ini tidak diingikan oleh keluarga",
                false, new String[] { "koseling" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh02",
                "Ibu hamil merokok atau Ibu hamil tinggal bersama anggota keluarga yang merokok.",
                false, new String[] { "koseling" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh03",
                "Ibu muntah terus menerus dan tidak mau makan",
                false, new String[] { "rujuk" }, PilihanObject.WARNA_MERAH));
        pilihanObjects.add(new PilihanObject(
                "klh04",
                "Ibu mengalami pusing /sakit kepala terus menerus",
                false, new String[] {"rujuk"}, PilihanObject.WARNA_MERAH));
        pilihanObjects.add(new PilihanObject(
                "klh05",
                "Ibu pusing /sakit kepala setiap baru bangun tidur",
                false, new String[] { "konseling" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh06",
                "Demam tinggi, menggigil dan berkeringat",
                false, new String[] { "rujuk" }, PilihanObject.WARNA_MERAH));

        pilihanObjects.add(new PilihanObject(
                "klh07",
                "Bengkak pada kaki, tangan dan wajah",
                false, new String[] { "rujuk" }, PilihanObject.WARNA_MERAH));
        pilihanObjects.add(new PilihanObject(
                "klh08",
                "Ibu mengalami mata berkunang-kunang",
                false, new String[] { "rujuk" }, PilihanObject.WARNA_MERAH));
        pilihanObjects.add(new PilihanObject(
                "klh09",
                "Pergerakan janin berkurang (<10 kali/hari)",
                false, new String[] { "konseling", "rujuk" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh10",
                "Tidak merasakan pergerakan janin",
                false, new String[] { "rujuk" }, PilihanObject.WARNA_MERAH));
        pilihanObjects.add(new PilihanObject(
                "klh11",
                "Mengalami pendarahan bercak",
                false, new String[] { "rujuk" }, PilihanObject.WARNA_MERAH));
        pilihanObjects.add(new PilihanObject(
                "klh12",
                "Ibu mengalami pendarahan (darah segar)",
                false, new String[] { "rujuk" }, PilihanObject.WARNA_MERAH));
        pilihanObjects.add(new PilihanObject(
                "klh13",
                "Ibu mengalami diare berulang",
                false, new String[] { "konseling", "rujuk" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh14",
                "Ibu mengalami nyeri saat buang air kecil",
                false, new String[] { "konseling", "rujuk" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh15",
                "Ibu mengalami keputihan",
                false, new String[] { "konseling" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh16",
                "Ibu mengalami tidak bisa tidur",
                false, new String[] { "konseling" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh17",
                "Ibu mengalami jantung berdebar-debar",
                false, new String[] { "konseling", "rujuk" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh18",
                "Umur ibu hamil anak pertama terlalu muda (<16 tahun) atau terlalu tua (> 30 tahun)",
                false, new String[] { "konseling" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh19",
                "Jarak dengan kehamilan sebelumnya kurang dari 2 tahun atau lebih dari 10 tahun",
                false, new String[] { "konseling" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh20",
                "Ibu hamil terlalu kurus atau terlalu gemuk",
                false, new String[] { "konseling" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh21",
                "Ibu khawatir akan kehamilannya",
                false, new String[] { "konseling" }, PilihanObject.WARNA_KUNING));
        pilihanObjects.add(new PilihanObject(
                "klh22",
                "Ibu khawatir dengan proses persalinan yang akan dihadapi",
                false, new String[] { "konseling" }, PilihanObject.WARNA_KUNING));

        adapterRecyclerFormKeluhan = new AdapterRecyclerFormKeluhan(getContext(), pilihanObjects);

        listKeluhanView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listKeluhanView.setAdapter(adapterRecyclerFormKeluhan);
    }

    private void setToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
    }

    private void openReview() {
        pendaftaranViewModel.openReview();
    }
}