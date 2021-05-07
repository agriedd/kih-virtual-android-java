package com.komodoindotech.kihvirtual.ui.home;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alibaba.fastjson.JSON;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.komodoindotech.kihvirtual.json.ArticleObject;
import com.komodoindotech.kihvirtual.models.Article;
import com.komodoindotech.kihvirtual.repositories.ArticleRepository;
import com.komodoindotech.kihvirtual.services.ClearArticleCached;
import com.komodoindotech.kihvirtual.services.StoreArticleCached;

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
    private Long last_cached;
    private MutableLiveData<Long> liveDataLastCached;
    FirebaseFirestore db;

    public ListArticleViewModel(Application application) {
        super(application);
        arrayListMutableLiveData = new MutableLiveData<>();
        liveDataLoading = new MutableLiveData<>();
        liveDataLastCached = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();
        init(application);
    }

    public MutableLiveData<List<ArticleObject>> getArticlesMutableLiveData() {
        return arrayListMutableLiveData;
    }
    public MutableLiveData<Boolean> getLoadingMutableLiveData() {
        return liveDataLoading;
    }
    public MutableLiveData<Long> getLastCachedMutableLiveData(){
        return liveDataLastCached;
    }

    public void init(Application application){
        articleObjects = new ArrayList<>();
        liveDataLoading.setValue(loading = false);
        arrayListMutableLiveData.setValue(articleObjects);
        articleRepository = new ArticleRepository(application);
        getArticles(application);
    }

    private void getArticles(Application application) {
        liveDataLoading.setValue(loading = true);
        Article latestCachedArticle = articleRepository.getArticleCached((new Date()).getTime());

        if(latestCachedArticle == null)
            loadArticles();
        else {
            List<ArticleObject> articlesObject = JSON.parseArray(latestCachedArticle.json, ArticleObject.class);
            liveDataLastCached.setValue(last_cached = latestCachedArticle.created_at);
            if(articlesObject != null && articlesObject.size() > 0){
                populateList(articlesObject);
            }
            liveDataLoading.setValue(loading = false);
        }
    }
    public void getPaginateArticles() {
        liveDataLoading.setValue(loading = true);
        db.collection("articles")
                .limit(10)
                .whereEqualTo("published", true)
                .orderBy("created_at", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    articleObjects = new ArrayList<>();
                    if(task.isSuccessful()) bindArticles(task);
                    else Log.d(TAG, "ListArticleViewModel: failed: "+ task.getException());
                    liveDataLoading.setValue(loading = false);
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });;
    }
    public void getPaginateSearchArticles(String search) {
        liveDataLoading.setValue(loading = true);
        db.collection("articles")
                .limit(10)
                .whereEqualTo("published", true)
                .orderBy("title").startAt(search.trim()).endAt(search.trim() + "\uf8ff")
                .get()
                .addOnCompleteListener(task -> {
                    articleObjects = new ArrayList<>();
                    if(task.isSuccessful()) bindArticles(task);
                    else Log.d(TAG, "ListArticleViewModel: failed: "+ task.getException());
                    liveDataLoading.setValue(loading = false);
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });;
    }

    private void loadArticles() {
        db.collection("articles")
                .limit(5)
                .whereEqualTo("published", true)
                .orderBy("created_at", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    articleObjects = new ArrayList<>();
                    if(task.isSuccessful()) bindArticles(task);
                    else Log.d(TAG, "ListArticleViewModel: failed: "+ task.getException());
                    liveDataLoading.setValue(loading = false);
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void populateList(List<ArticleObject> articles) {
        articleObjects = new ArrayList<>();
        articleObjects = articles;
        arrayListMutableLiveData.setValue(articleObjects);
    }

    private void bindArticles(Task<QuerySnapshot> task) {
        List<ArticleObject> articleObjects = new ArrayList<ArticleObject>();
        for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
            addList(document);
            ArticleObject article = document.toObject(ArticleObject.class);
            article.setId(document.getId());
            articleObjects.add(article);
        }
        cachedArticlesObject(articleObjects);
        liveDataLastCached.setValue(last_cached = new Date().getTime());
    }

    private void cachedArticlesObject(List<ArticleObject> articleObjects) {
        ClearArticleCached.handler(articleRepository);
        StoreArticleCached.handler(articleRepository, articleObjects);
    }

    public void addList(QueryDocumentSnapshot document){
        articleObjects.add(document.toObject(ArticleObject.class));
        arrayListMutableLiveData.setValue(articleObjects);
    }

    public void refresh() {
        if(loading) {
            Toast.makeText(getApplication(), "Sedang memuat...", Toast.LENGTH_SHORT).show();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MINUTE, -2);
            long in_inutes = calendar.getTime().getTime();
            if(last_cached >= in_inutes && last_cached != 0L){
                liveDataLoading.setValue(loading = false);
                Toast.makeText(getApplication(), "Artikel sudah terupdate", Toast.LENGTH_SHORT).show();
            } else {
                loadArticles();
            }
        }
    }
}