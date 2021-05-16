package com.komodoindotech.kihvirtual.ui.privasipengguna;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.komodoindotech.kihvirtual.R;


public class PrivasiPenggunaFragment extends Fragment {

    private PrivasiPenggunaModel mViewModel;
    private WebView webView;

    public static PrivasiPenggunaFragment newInstance() {
        return new PrivasiPenggunaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.privasi_pengguna_fragment, container, false);

        webView = root.findViewById(R.id.webview);

        String url = "https://sites.google.com/view/kih-virtual/ketentuan-privasi-pengguna?authuser=0";
        webView.loadUrl(url);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PrivasiPenggunaModel.class);
        // TODO: Use the ViewModel
    }

}