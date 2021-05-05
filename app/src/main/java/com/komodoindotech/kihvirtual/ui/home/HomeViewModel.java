package com.komodoindotech.kihvirtual.ui.home;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Ignore;
import androidx.room.Room;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.komodoindotech.kihvirtual.core.AppDatabase;
import com.komodoindotech.kihvirtual.dao.ArticleDao;
import com.komodoindotech.kihvirtual.json.ArticleObject;
import com.komodoindotech.kihvirtual.json.ArticlesObject;
import com.komodoindotech.kihvirtual.models.Article;
import com.komodoindotech.kihvirtual.repositories.ArticleRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class HomeViewModel extends AndroidViewModel {

    private static final String TAG = "homevm";
    private MutableLiveData<String> mText;
    private ArticleRepository articleRepository;

    public HomeViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        articleRepository = new ArticleRepository(application);
        Article latestCachedArticle = articleRepository.getArticleCached((new Date()).getTime());
        Log.d(TAG, "HomeViewModel: "+JSON.toJSONString(latestCachedArticle));
        /**
         *
         * cek jika artikel sudah di cache
         *
         */
        if(latestCachedArticle == null){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("articles")
                    .limit(1)
                    .get()
                    .addOnCompleteListener(task -> {
                        Log.d(TAG, "HomeViewModel: complete");
                        if(task.isSuccessful()){
                            Log.d(TAG, "HomeViewModel: success");
                            List<ArticleObject> articleObjects = new ArrayList<ArticleObject>();
                            for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                                ArticleObject article = document.toObject(ArticleObject.class);
                                article.setId(document.getId());
                                articleObjects.add(article);
                                mText.setValue(document.getString("title"));
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

                            insert(article);
                        } else {
                            Log.d(TAG, "HomeViewModel: failed");
                            Log.d(TAG, "HomeViewModel: "+ task.getException());
                            mText.setValue("This is home fragment");
                        }
                    });
        } else {
            Log.d(TAG, "HomeViewModel: "+latestCachedArticle.json);
            List<ArticleObject> articlesObject = JSON.parseArray(latestCachedArticle.json, ArticleObject.class);

            if(articlesObject != null && articlesObject.size() > 0){
                mText.setValue(articlesObject.get(0).getTitle());
            }
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
    public void insert(Article article) {
        articleRepository.insert(article);
    }
}