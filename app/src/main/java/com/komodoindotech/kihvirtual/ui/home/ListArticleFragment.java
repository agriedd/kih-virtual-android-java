package com.komodoindotech.kihvirtual.ui.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komodoindotech.kihvirtual.R;

public class ListArticleFragment extends Fragment {

    private ListArticleViewModel mViewModel;

    public static ListArticleFragment newInstance() {
        return new ListArticleFragment();
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View
     *
     * @// TODO: 5/6/2021 membuat recycler list article terbatas (terbaru)
     * @// TODO: 5/6/2021 membuat aksi yang meredirect ke tab article
     * @// TODO: 5/6/2021 membuat activity baru untuk menampilkan berita
     * @// TODO: 5/6/2021 mengisi konten tab article
     * @// TODO: 5/6/2021 membuat recycler awal diimplementasikan ke tab ini
     * @// TODO: 5/6/2021 membuat infinite loading di recycler view tab article
     * @// TODO: 5/6/2021 menambah tampilan pencarian
     * @// TODO: 5/6/2021 membuat activity pencarian untuk menampikan hasil article
     *
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_article_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListArticleViewModel.class);
        // TODO: Use the ViewModel
    }

}