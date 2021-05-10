package com.komodoindotech.kihvirtual.ui.review.pendaftaran;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ui.pendaftaran.PendaftaranViewModel;

public class ReviewPendaftaranFragment extends Fragment {

    private static final String TAG = "reviewpendaftaran";
    private ReviewPendaftaranViewModel mViewModel;
    private PendaftaranViewModel pendaftaranViewModel;

    public static ReviewPendaftaranFragment newInstance() {
        return new ReviewPendaftaranFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.review_pendaftaran_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReviewPendaftaranViewModel.class);
        pendaftaranViewModel = new ViewModelProvider(getActivity()).get(PendaftaranViewModel.class);

        pendaftaranViewModel.getCountFormPagerLiveData().observe(getActivity(), integer -> {
            Log.d(TAG, "onActivityCreated: hei"+integer);
        });
    }
}