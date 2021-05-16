package com.komodoindotech.kihvirtual;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KesimpulanHijau#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KesimpulanHijau extends Fragment {

    public KesimpulanHijau() {
    }

    public static KesimpulanHijau newInstance() {
        return new KesimpulanHijau();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kesimpulan_hijau, container, false);
        return root;
    }
}