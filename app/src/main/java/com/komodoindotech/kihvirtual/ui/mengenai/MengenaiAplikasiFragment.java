package com.komodoindotech.kihvirtual.ui.mengenai;

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

public class MengenaiAplikasiFragment extends Fragment {

    private MengenaiViewModel mViewModel;
    private WebView webView;

    public static MengenaiAplikasiFragment newInstance() {
        return new MengenaiAplikasiFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.mengenai_aplikasi_fragment, container, false);

        webView = root.findViewById(R.id.mengenai_aplikasi_webview);

        String url = "https://sites.google.com/view/kih-virtual/mengenai-aplikasi?authuser=0";
        webView.loadUrl(url);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}