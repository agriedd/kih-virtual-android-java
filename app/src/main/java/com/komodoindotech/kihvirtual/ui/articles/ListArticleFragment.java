package com.komodoindotech.kihvirtual.ui.articles;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.adapters.AdapterRecyclerviewArticle;
import com.komodoindotech.kihvirtual.json.ArticleObject;
import com.komodoindotech.kihvirtual.ui.home.ListArticleViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListArticleFragment extends Fragment {

    private ListArticleViewModel mViewModel;
    private View root;
    private RecyclerView view_list_article;
    private AdapterRecyclerviewArticle adapterRecyclerviewArticle;
    private List<ArticleObject> articleObjects;

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
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.list_article_fragment, container, false);
        view_list_article = root.findViewById(R.id.list_article_home);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getParentFragment().getActivity()).get(ListArticleViewModel.class);
        mViewModel.getArticlesMutableLiveData().observe(getViewLifecycleOwner(), articleListUpdateObserver);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        view_list_article.setLayoutManager(layoutManager);

        articleObjects = new ArrayList<>();

        adapterRecyclerviewArticle = new AdapterRecyclerviewArticle(getContext(), articleObjects);
        view_list_article.setAdapter(adapterRecyclerviewArticle);
    }

    Observer<List<ArticleObject>> articleListUpdateObserver = new Observer<List<ArticleObject>>() {
        @Override
        public void onChanged(List<ArticleObject> articleObjects) {
            adapterRecyclerviewArticle.updateList(articleObjects);
        }
    };

    public void setSearch(String query){
        mViewModel.getPaginateSearchArticles(query);
    }

}