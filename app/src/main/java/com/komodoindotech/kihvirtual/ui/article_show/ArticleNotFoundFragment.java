package com.komodoindotech.kihvirtual.ui.article_show;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komodoindotech.kihvirtual.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleNotFoundFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleNotFoundFragment extends Fragment implements View.OnClickListener {

    View root;

    public ArticleNotFoundFragment() {
        // Required empty public constructor
    }

    public static ArticleNotFoundFragment newInstance() {
        return new ArticleNotFoundFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_article_not_found, container, false);

        root.findViewById(R.id.btn_kembali).setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        getActivity().finish();
    }
}