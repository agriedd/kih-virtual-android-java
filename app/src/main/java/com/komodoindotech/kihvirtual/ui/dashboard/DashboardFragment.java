package com.komodoindotech.kihvirtual.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.komodoindotech.kihvirtual.MengenaiAplikasiActivity;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerMenu;
import com.komodoindotech.kihvirtual.json.MenuObject;
import com.komodoindotech.kihvirtual.ui.DaftarDataActivity;
import com.komodoindotech.kihvirtual.ui.PrivasiPenggunaActivity;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private RecyclerView daftar_menu;
    private MaterialToolbar toolbar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        daftar_menu = root.findViewById(R.id.daftar_menu);
        toolbar = root.findViewById(R.id.toolbar);

        initToolbar();
        initRecycler();

        return root;
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity();
        appCompatActivity.setSupportActionBar(toolbar);
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(),
                layoutManager.getOrientation());
        daftar_menu.addItemDecoration(dividerItemDecoration);
        daftar_menu.setLayoutManager(layoutManager);

        List<MenuObject> menuObjects = new ArrayList<>();
        menuObjects.add(new MenuObject(1, "Daftar Data", "Daftar data yang sudah diinput"));
        menuObjects.add(new MenuObject(2, "Ketentuan Privasi", "Ketentuan Privasi Pengguna"));
        menuObjects.add(new MenuObject(3, "Mengenai Aplikasi", "Mengenai Aplikasi"));

        AdapterRecyclerMenu adapterRecyclerMenu = new AdapterRecyclerMenu(requireContext(), menuObjects, new AdapterRecyclerMenu.onClickListener() {
            @Override
            public void onClick(View v, Integer i) {
                if(i == 1){
                    Intent intent = new Intent(requireContext(), DaftarDataActivity.class);
                    startActivity(intent);
                } else if(i == 2){
                    Intent intent = new Intent(requireContext(), PrivasiPenggunaActivity.class);
                    startActivity(intent);
                } else if(i == 3){
                    Intent intent = new Intent(requireContext(), MengenaiAplikasiActivity.class);
                    startActivity(intent);
                }
            }
        });

        daftar_menu.setAdapter(
                adapterRecyclerMenu
        );
    }
}