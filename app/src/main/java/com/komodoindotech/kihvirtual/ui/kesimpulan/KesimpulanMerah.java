package com.komodoindotech.kihvirtual.ui.kesimpulan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ui.puskesmas.PuskesmasRecycler;
import com.komodoindotech.kihvirtual.ui.puskesmas.PuskesmasViewModel;

public class KesimpulanMerah extends Fragment {

    private PuskesmasViewModel puskesmasViewModel;

    public KesimpulanMerah() {}

    public static KesimpulanMerah newInstance() {
        return new KesimpulanMerah();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        puskesmasViewModel = new ViewModelProvider(requireActivity()).get(PuskesmasViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kesimpulan_merah, container, false);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.daftar_puskesmas, PuskesmasRecycler.newInstance())
                .commitNow();
        return root;
    }
}