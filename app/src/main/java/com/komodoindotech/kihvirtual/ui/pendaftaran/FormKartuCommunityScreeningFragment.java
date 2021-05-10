package com.komodoindotech.kihvirtual.ui.pendaftaran;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterPagerFormKCS;
import com.komodoindotech.kihvirtual.ui.form.FormInfoDataDiriFragment;
import com.komodoindotech.kihvirtual.ui.form.FormKeluhanFragment;
import com.komodoindotech.kihvirtual.ui.form.FormRiwayatImunisasiTTFragment;
import com.komodoindotech.kihvirtual.ui.form.FormRiwayatKehamilanFragment;
import com.komodoindotech.kihvirtual.ui.form.FormRiwayatPersalinanFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormKartuCommunityScreeningFragment extends Fragment {

    ViewPager2 formViewPager;
    AdapterPagerFormKCS adapterPagerFormKCS;
    List<Fragment> fragmentsForm;
    BottomAppBar bottomAppBar;
    TextView pageControlInfo;
    FloatingActionButton fabNextControl;
    PendaftaranViewModel pendaftaranViewModel;
    private int page_position = 0;
    private Boolean validInputs = false;


    public FormKartuCommunityScreeningFragment() { }

    public static FormKartuCommunityScreeningFragment newInstance() {
        return new FormKartuCommunityScreeningFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_form_kartu_community_screening, container, false);

        pendaftaranViewModel = new ViewModelProvider(getActivity()).get(PendaftaranViewModel.class);

        formViewPager = root.findViewById(R.id.form_pager);
        bottomAppBar = root.findViewById(R.id.bottomappbar);
        pageControlInfo = root.findViewById(R.id.page_control_info);
        fabNextControl = root.findViewById(R.id.fab_next_controll);

        ((AppCompatActivity) getActivity()).setSupportActionBar(bottomAppBar);

        fragmentsForm = new ArrayList<>();
        fragmentsForm.add(FormInfoDataDiriFragment.newInstance());
        fragmentsForm.add(FormRiwayatKehamilanFragment.newInstance());
        fragmentsForm.add(FormRiwayatPersalinanFragment.newInstance());
        fragmentsForm.add(FormRiwayatImunisasiTTFragment.newInstance());
        fragmentsForm.add(FormKeluhanFragment.newInstance());

        adapterPagerFormKCS = new AdapterPagerFormKCS(getActivity(), fragmentsForm,  getContext());

        formViewPager.setAdapter(adapterPagerFormKCS);
        formViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                pendaftaranViewModel.setFormPagerPositionLiveData(position);
                int pagePosition = position + 1;
                String value = pagePosition +"/"+adapterPagerFormKCS.getItemCount();
                pageControlInfo.setText(value);
                if(pagePosition == adapterPagerFormKCS.getItemCount()){
                    fabNextControl.hide();
                } else {
                    if(validInputs)
                        fabNextControl.show();
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        formViewPager.setUserInputEnabled(false);

        fabNextControl.setOnClickListener(v -> formViewPager.setCurrentItem(
                formViewPager.getCurrentItem() + 1
        ));

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        pendaftaranViewModel.getFormPagerPositionLiveData().observe(getActivity(), position -> page_position = position);

        pendaftaranViewModel.getValidInputLiveData().observe(getActivity(), aBoolean -> {
            Log.d("wtf", "onCreateView: "+aBoolean.toString());
            validInputs = aBoolean;
            if(aBoolean) fabNextControl.show();
            else fabNextControl.hide();
        });

        pendaftaranViewModel.getInputErrors().observe(getActivity(), stringMapMap -> {
            try {
                Map<String, String> inputErrorDataDiri = stringMapMap.get(FormInfoDataDiriFragment.KEY);
                Log.d("wtf", "onCreateView: "+inputErrorDataDiri.size() + " | " + validInputs);
                if((inputErrorDataDiri != null && inputErrorDataDiri.size() > 0) || !validInputs){
                    fabNextControl.hide();
                } else {
                    fabNextControl.show();
                }
            } catch (Exception ignored){

            }
        });

        pendaftaranViewModel.getNextPageLiveData().observe(getActivity(), isNext -> {
            if(isNext){
                formViewPager.setCurrentItem(formViewPager.getCurrentItem()+1, true);
            }
        });
        pendaftaranViewModel.getPreviousPageLiveData().observe(getActivity(), isPrev -> {
            if(isPrev){
                formViewPager.setCurrentItem(formViewPager.getCurrentItem()-1, true);
            }
        });
    }

    public void setCurrentPage(int item) {
        formViewPager.setCurrentItem(item);
    }
}