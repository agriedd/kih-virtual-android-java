package com.komodoindotech.kihvirtual.ui.form;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komodoindotech.kihvirtual.R;

public class FormInfoDataDiriFragment extends Fragment {

    public FormInfoDataDiriFragment() {
    }

    public static FormInfoDataDiriFragment newInstance() {
        return new FormInfoDataDiriFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form_info_data_diri, container, false);
    }
}