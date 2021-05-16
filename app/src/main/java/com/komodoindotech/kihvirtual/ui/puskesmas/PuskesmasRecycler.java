package com.komodoindotech.kihvirtual.ui.puskesmas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerPuskesmas;
import com.komodoindotech.kihvirtual.json.PuskesmasObject;

import java.util.ArrayList;
import java.util.List;

public class PuskesmasRecycler extends Fragment {

    PuskesmasViewModel puskesmasViewModel;
    RecyclerView listPuskesmasView;
    List<PuskesmasObject> puskesmasObjectList;
    AdapterRecyclerPuskesmas adapterRecyclerPuskesmas;


    public PuskesmasRecycler() {
    }

    public static PuskesmasRecycler newInstance() {
        return new PuskesmasRecycler();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        puskesmasViewModel = new ViewModelProvider(requireActivity()).get(PuskesmasViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_puskesmas_recycler, container, false);
        listPuskesmasView = root.findViewById(R.id.list_puskesmas);
        puskesmasObjectList = new ArrayList<>();
        initRecyclerView();
        puskesmasViewModel.getPuskesmasObjectMutableLiveData().observe(requireActivity(), puskesmasObjects -> {
            puskesmasObjectList = puskesmasObjects;
            adapterRecyclerPuskesmas.update(puskesmasObjectList);
        });
        return root;
    }

    private void initRecyclerView() {
        listPuskesmasView.setNestedScrollingEnabled(false);
        listPuskesmasView.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        listPuskesmasView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listPuskesmasView.getContext(),
                layoutManager.getOrientation());
        listPuskesmasView.addItemDecoration(dividerItemDecoration);
        adapterRecyclerPuskesmas = new AdapterRecyclerPuskesmas(requireActivity(), puskesmasObjectList);
        listPuskesmasView.setAdapter(adapterRecyclerPuskesmas);

    }
}