package com.komodoindotech.kihvirtual.ui.article_show;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.alibaba.fastjson.JSON;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.json.ArticleObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleShowUrlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleShowUrlFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "articleshowurlfragment";
    private View root;
    private MainViewModel mainViewModel;
    private WebView webViewArticle;
    private FloatingActionButton articleShareButton;
    private ArticleObject article;

    public ArticleShowUrlFragment() {
        // Required empty public constructor
    }

    public static ArticleShowUrlFragment newInstance() {
        return new ArticleShowUrlFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_article_show_url, container, false);

        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        mainViewModel.getArticle().observe(getActivity(), bindArticle);
        webViewArticle = root.findViewById(R.id.webview_article);
        articleShareButton = root.findViewById(R.id.article_share_button);
        articleShareButton.setOnClickListener(this);

        init();

        return root;
    }

    private void init() {
    }

    private Observer<ArticleObject> bindArticle = articleObject -> {
        article = articleObject;
        if(articleObject.getUrl() != null && articleObject.getUrl().length() > 0){
            webViewArticle.loadUrl(articleObject.getUrl());
        }
    };

    @Override
    public void onClick(View v) {
        if(v == articleShareButton){
            try {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, article.getUrl());
                Intent chooser = Intent.createChooser(intent, "Bagikan");
                startActivity(chooser);
            } catch (Exception e){
                Log.d(TAG, "onClick: " + e.getMessage());
            }
        }
    }
}