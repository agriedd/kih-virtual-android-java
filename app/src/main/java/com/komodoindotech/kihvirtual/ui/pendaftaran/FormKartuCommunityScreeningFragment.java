package com.komodoindotech.kihvirtual.ui.pendaftaran;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.fastjson.JSON;
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

    private static final String VALID_INPUT = "valid_input";
    private static final String PAGE_POSITION = "page_position";


    ViewPager2 formViewPager;
    AdapterPagerFormKCS adapterPagerFormKCS;
    List<Fragment> fragmentsForm;
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

        pendaftaranViewModel = new ViewModelProvider(requireActivity()).get(PendaftaranViewModel.class);

        formViewPager = root.findViewById(R.id.form_pager);
        pageControlInfo = root.findViewById(R.id.page_control_info);
        fabNextControl = root.findViewById(R.id.fab_next_controll);

        fragmentsForm = new ArrayList<>();
        fragmentsForm.add(FormInfoDataDiriFragment.newInstance());
        fragmentsForm.add(FormRiwayatKehamilanFragment.newInstance());
        fragmentsForm.add(FormRiwayatPersalinanFragment.newInstance());
        fragmentsForm.add(FormRiwayatImunisasiTTFragment.newInstance());
        fragmentsForm.add(FormKeluhanFragment.newInstance());

        adapterPagerFormKCS = new AdapterPagerFormKCS(requireActivity(), fragmentsForm,  getContext());

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
//                super.onPageScrollStateChanged(state);
                if (state == ViewPager2.SCROLL_STATE_IDLE){
                    InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    View view = requireActivity().getCurrentFocus();
                    if (view == null)
                        view = new View(requireActivity());
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

        formViewPager.setUserInputEnabled(false);

        fabNextControl.setOnClickListener(v -> formViewPager.setCurrentItem(
                formViewPager.getCurrentItem() + 1
        ));

        return root;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    private void storeState() {
        formViewPager.setCurrentItem(page_position);
    }

    @Override
    public void onStart() {
        super.onStart();

        pendaftaranViewModel.getFormPagerPositionLiveData().observe(requireActivity(), position -> page_position = position);

        pendaftaranViewModel.getValidInputLiveData().observe(requireActivity(), aBoolean -> {
            Log.d("wtf", "onCreateView: "+aBoolean.toString());
            validInputs = aBoolean;
            if(aBoolean) fabNextControl.show();
            else fabNextControl.hide();
        });

        pendaftaranViewModel.getInputErrors().observe(requireActivity(), stringMapMap -> {
            try {
                Map<String, String> inputErrorDataDiri = stringMapMap.get(FormInfoDataDiriFragment.KEY);
                assert inputErrorDataDiri != null;
                if((inputErrorDataDiri.size() > 0 || !validInputs) || page_position + 1 == adapterPagerFormKCS.getItemCount()){
                    fabNextControl.hide();
                } else {
                    fabNextControl.show();
                }
            } catch (Exception ignored){

            }
        });

        pendaftaranViewModel.getNextPageLiveData().observe(requireActivity(), isNext -> {
            if(isNext){
                formViewPager.setCurrentItem(formViewPager.getCurrentItem()+1, true);
            }
        });
        pendaftaranViewModel.getPreviousPageLiveData().observe(requireActivity(), isPrev -> {
            if(isPrev){
                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                //Find the currently focused view, so we can grab the correct window token from it.
                View view = requireActivity().getCurrentFocus();
                //If no view currently has focus, create a new one, just so we can grab a window token from it
                if (view == null) {
                    view = new View(requireActivity());
                }
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                formViewPager.setCurrentItem(formViewPager.getCurrentItem()-1, true);
            }
        });
    }

    public void setCurrentPage(int item) {
        formViewPager.setCurrentItem(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("page_position", page_position);
        outState.putBoolean("valid_input", validInputs);
        super.onSaveInstanceState(outState);
    }

}