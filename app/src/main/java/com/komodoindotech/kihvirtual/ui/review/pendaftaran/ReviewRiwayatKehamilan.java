package com.komodoindotech.kihvirtual.ui.review.pendaftaran;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;

import java.util.List;

public class ReviewRiwayatKehamilan extends Fragment {
    public ReviewRiwayatKehamilan() {}

    public static ReviewRiwayatKehamilan newInstance() {
        return new ReviewRiwayatKehamilan();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_review_riwayat_kehamilan, container, false);
    }
}