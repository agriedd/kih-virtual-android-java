package com.komodoindotech.kihvirtual.ui.mengenai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.google.android.material.appbar.MaterialToolbar;
import com.komodoindotech.kihvirtual.R;

import org.jetbrains.annotations.NotNull;

public class MengenaiAplikasiFragment extends Fragment {

    private MengenaiViewModel mViewModel;
    private WebView webView;
    private MaterialToolbar toolbar;

    public static MengenaiAplikasiFragment newInstance() {
        return new MengenaiAplikasiFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.mengenai_aplikasi_fragment, container, false);

        webView = root.findViewById(R.id.mengenai_aplikasi_webview);
        toolbar = root.findViewById(R.id.toolbar);

        initToolbar();

        String url = "https://sites.google.com/view/kih-virtual/mengenai-aplikasi?authuser=0";
        webView.loadUrl(url);

        return root;
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_only_close, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        requireActivity().finish();
        return true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}