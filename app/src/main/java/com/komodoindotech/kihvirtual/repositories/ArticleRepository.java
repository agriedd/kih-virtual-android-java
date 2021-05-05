package com.komodoindotech.kihvirtual.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.alibaba.fastjson.JSON;
import com.komodoindotech.kihvirtual.core.AppDatabase;
import com.komodoindotech.kihvirtual.dao.ArticleDao;
import com.komodoindotech.kihvirtual.models.Article;

import java.util.List;

public class ArticleRepository {
    private ArticleDao articleDao;
    private LiveData<List<Article>> listLiveData;

    public ArticleRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        this.articleDao = db.articleDao();
//        this.listLiveData = articleDao.getAll();
    }
    public LiveData<List<Article>> getAllArticles() {
        return articleDao.getAll();
    }

    public void insert (Article article) {
        new insertAsyncTask(this.articleDao, article).execute(article);
    }

    public ArticleDao getArticleDao() {
        return articleDao;
    }

    public void insertSync(Article article) {
        articleDao.insert(article);
    }

    public Article getArticleCached(long time) {
        return articleDao.getLatest(time);
    }

    private static class insertAsyncTask extends AsyncTask<Article, Void, Void> {

        private static final String TAG = "articlerepo";
        ArticleDao articleDao;
        Article article;

        public insertAsyncTask(ArticleDao articleDao, Article article) {
            this.articleDao = articleDao;
            this.article = article;
        }

        @Override
        protected Void doInBackground(Article... articles) {
            this.articleDao.insert(article);
            Log.d(TAG, "doInBackground: " + JSON.toJSONString(article));
            return null;
        }
    }
}
