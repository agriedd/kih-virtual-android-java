package com.komodoindotech.kihvirtual.ui.article_show;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.json.ArticleObject;

public class ArticleShowFragment extends Fragment {

    private static final String TAG = "articleshowfragment";
    private MainViewModel mViewModel;
    private View root;
    private TextView title, body;
    private ImageView cover;
    private ArticleObject articleObject;

    public static ArticleShowFragment newInstance() {
        return new ArticleShowFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_show_article, container, false);

        title = root.findViewById(R.id.article_title);
        body = root.findViewById(R.id.article_body);
        cover = root.findViewById(R.id.article_cover);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        mViewModel.getArticle().observe(getActivity(), bindArticle);
    }

    private final Observer<ArticleObject> bindArticle = articleObject -> {
        title.setText(articleObject.getTitle());
        try {
            if(articleObject.getCover() != null && articleObject.getCover().length() > 0){
                cover.clearColorFilter();
                Glide.with(getContext())
                        .load(articleObject.getCover())
                        .centerCrop()
                        .into(cover);
            } else {
                cover.setVisibility(View.GONE);
            }
        } catch (Exception e){
            Log.d(TAG, "bind: " + e.getMessage());
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            body.setText(Html.fromHtml(articleObject.getBody(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            body.setText(Html.fromHtml(articleObject.getBody()));
        }
    };

}