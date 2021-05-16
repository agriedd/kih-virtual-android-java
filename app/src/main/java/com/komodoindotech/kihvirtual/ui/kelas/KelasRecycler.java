package com.komodoindotech.kihvirtual.ui.kelas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterPagerKelasIbuHamil;
import com.komodoindotech.kihvirtual.json.KelasObject;

import java.util.ArrayList;
import java.util.List;

public class KelasRecycler extends Fragment {

    private ViewPager viewPager2;
    private KelasViewModel kelasViewModel;
    private AdapterPagerKelasIbuHamil adapterPagerKelasIbuHamil;
    private List<KelasObject> kelasObjectList;

    public KelasRecycler() {
    }

    public static KelasRecycler newInstance() {
        KelasRecycler fragment = new KelasRecycler();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kelasViewModel = new ViewModelProvider(requireActivity()).get(KelasViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kelas_recycler, container, false);
        viewPager2 = root.findViewById(R.id.container);

        initViewPager();

        kelasViewModel.getKelasListMutableLiveData().observe(requireActivity(), kelasObjects -> {
            adapterPagerKelasIbuHamil.update(kelasObjects);
        });

        return root;
    }

    private void initViewPager() {
        kelasObjectList = new ArrayList<>();
        adapterPagerKelasIbuHamil = new AdapterPagerKelasIbuHamil(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, requireContext(), kelasObjectList);
        viewPager2.setAdapter(adapterPagerKelasIbuHamil);
    }
}