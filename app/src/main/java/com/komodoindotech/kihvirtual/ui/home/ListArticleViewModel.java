package com.komodoindotech.kihvirtual.ui.home;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alibaba.fastjson.JSON;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.komodoindotech.kihvirtual.json.ArticleObject;
import com.komodoindotech.kihvirtual.models.Article;
import com.komodoindotech.kihvirtual.repositories.ArticleRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ListArticleViewModel extends AndroidViewModel {
    private static final String TAG = "listarticlevm";
    // TODO: Implement the ViewModel
    MutableLiveData<List<ArticleObject>> arrayListMutableLiveData;
    List<ArticleObject> articleObjects;
    private ArticleRepository articleRepository;
    private MutableLiveData<Boolean> liveDataLoading;
    private Boolean loading;

    public ListArticleViewModel(Application application) {
        super(application);
        arrayListMutableLiveData = new MutableLiveData<>();
        liveDataLoading = new MutableLiveData<>();
        init(application);
    }

    public MutableLiveData<List<ArticleObject>> getArticlesMutableLiveData() {
        return arrayListMutableLiveData;
    }
    public MutableLiveData<Boolean> getLoadingMutableLiveData() {
        return liveDataLoading;
    }

    public void init(Application application){
        articleObjects = new ArrayList<>();
        loading = false;
        liveDataLoading.setValue(loading);
        arrayListMutableLiveData.setValue(articleObjects);
        getArticles(application);
    }

    private void getArticles(Application application) {
        loading = true;
        liveDataLoading.setValue(loading);

        articleRepository = new ArticleRepository(application);
        Article latestCachedArticle = articleRepository.getArticleCached((new Date()).getTime());
        /**
         *
         * cek jika artikel sudah di cache
         *
         */
        if(latestCachedArticle == null || true){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("articles")
                    .limit(5)
                    .get()
                    .addOnCompleteListener(task -> {
                        articleObjects = new ArrayList<>();
                        if(task.isSuccessful()){
                            Log.d(TAG, "ListArticleViewModel: success");
                            List<ArticleObject> articleObjects = new ArrayList<ArticleObject>();
                            for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                                populateList(document);
                                ArticleObject article = document.toObject(ArticleObject.class);
                                article.setId(document.getId());
                                articleObjects.add(article);
                            }
                            Article article = new Article();
                            Date current_date = new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(new Date());
                            calendar.add(Calendar.DATE, 1);
                            String datajson = JSON.toJSONString(articleObjects);

                            article.created_at = current_date.getTime();
                            article.expired_at = calendar.getTime().getTime();
                            article.updated_at = current_date.getTime();
                            article.json = datajson;

                            articleRepository.insert(article);

                        } else {
                            Log.d(TAG, "ListArticleViewModel: failed");
                            Log.d(TAG, "ListArticleViewModel: "+ task.getException());
                        }
                        loading = false;
                        liveDataLoading.setValue(loading);
                    });
        } else {
            Log.d(TAG, "ListArticleViewModel: " + latestCachedArticle.json);
            List<ArticleObject> articlesObject = JSON.parseArray(latestCachedArticle.json, ArticleObject.class);

            if(articlesObject != null && articlesObject.size() > 0){

            }
            loading = false;
            liveDataLoading.setValue(loading);
        }
    }

    public void populateList(QueryDocumentSnapshot document){
        articleObjects.add(document.toObject(ArticleObject.class));
        arrayListMutableLiveData.setValue(articleObjects);
    }

    public void refresh() {
        if(loading) {
            Toast.makeText(getApplication(), "Sedang memuat...", Toast.LENGTH_LONG).show();
        } else {
            getArticles(getApplication());
        }
    }
}