package com.komodoindotech.kihvirtual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.komodoindotech.kihvirtual.json.ArticleObject;
import com.komodoindotech.kihvirtual.ui.article_show.ArticleNotFoundFragment;
import com.komodoindotech.kihvirtual.ui.article_show.ArticleShowFragment;
import com.komodoindotech.kihvirtual.ui.article_show.ArticleShowUrlFragment;
import com.komodoindotech.kihvirtual.ui.article_show.LoadingFragment;
import com.komodoindotech.kihvirtual.ui.article_show.MainViewModel;

public class ArticleShowActivity extends AppCompatActivity {

    private static final String TAG = "articleshowactivity";
    private String id;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_show_activity);
        mainViewModel = new ViewModelProvider(this ).get(MainViewModel.class);
        id = getIntent().getExtras().getString("id");

        mainViewModel.getArticle().observe(this, loadArticle);
        mainViewModel.getLoading().observe(this, loadingArticle);
        mainViewModel.loadArticle(id);
    }

    private final Observer<Boolean> loadingArticle = loadingArticle -> {
        if(loadingArticle){
            replaceFragment(LoadingFragment.newInstance());
        }
    };
    private final Observer<ArticleObject> loadArticle = articleObject -> {
        if(articleObject == null){
            replaceFragment(ArticleNotFoundFragment.newInstance());
        } else {
            if(articleObject.getUrl() != null && articleObject.getUrl().length() > 0){
                replaceFragment(ArticleShowUrlFragment.newInstance());
            } else {
                replaceFragment(ArticleShowFragment.newInstance());
            }
        }
    };
    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_article_show, fragment)
                .commitNow();
    }
}