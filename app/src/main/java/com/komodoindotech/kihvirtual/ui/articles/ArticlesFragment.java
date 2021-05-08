package com.komodoindotech.kihvirtual.ui.articles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ui.home.ListArticleViewModel;

public class ArticlesFragment extends Fragment {

    private ArticlesViewModel articlesViewModel;
    private ListArticleViewModel listArticleViewModel;
    private Toolbar toolbar;
    private SearchView searchViewArticle;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        articlesViewModel = new ViewModelProvider(this).get(ArticlesViewModel.class);
        listArticleViewModel = new ViewModelProvider(this).get(ListArticleViewModel.class);

        View root = inflater.inflate(R.layout.fragment_articles, container, false);
//        searchViewArticle = root.findViewById(R.id.search_article);
//        searchViewArticle.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                ListArticleFragment fragment = (ListArticleFragment) getChildFragmentManager().findFragmentById(R.id.frame_artikel);
//                fragment.setSearch(query);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });

        articlesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        loadFragmentArticle();

        getChildFragmentManager().findFragmentById(R.id.frame_artikel);
        return root;
    }

    private void loadFragmentArticle() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_artikels, ListArticleFragment.newInstance());
        transaction.commit();
    }
}