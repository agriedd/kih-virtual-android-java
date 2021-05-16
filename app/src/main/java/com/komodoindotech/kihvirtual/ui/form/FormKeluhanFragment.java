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

        pilihanObjects = pendaftaranViewModel.getRiwayatKeluhanObjectLiveData().getValue();

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
        pendaftaranViewModel.openReview();
    }
    AdapterRecyclerFormKeluhan.onCheckedChangeListener listener = (status, position) -> {
        pendaftaranViewModel.setRiwayatKeluhanObjectLiveData(pilihanObjects);
    };
}