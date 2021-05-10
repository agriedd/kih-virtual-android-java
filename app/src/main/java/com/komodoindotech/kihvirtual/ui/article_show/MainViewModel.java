package com.komodoindotech.kihvirtual.ui.article_show;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.komodoindotech.kihvirtual.json.ArticleObject;

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = "mainvm";
    MutableLiveData<ArticleObject> articleObjectMutableLiveData;
    ArticleObject articleObject;
    FirebaseFirestore db;
    Boolean loading;
    MutableLiveData<Boolean> liveDataLoading;

    public MainViewModel(@NonNull Application application) {
        super(application);

        articleObjectMutableLiveData = new MutableLiveData<>();
        liveDataLoading = new MutableLiveData<>();
        liveDataLoading.setValue(loading = false);

        init();
    }

    private void init() {
        db = FirebaseFirestore.getInstance();
    }

    public LiveData<ArticleObject> getArticle(){
        return this.articleObjectMutableLiveData;
    }
    public LiveData<Boolean> getLoading(){
        return this.liveDataLoading;
    }

    public void loadArticle(String id){
        liveDataLoading.setValue(loading = true);

        db.collection("articles")
                .document(id)
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) bindArticles(task);
                    else{
                        Log.d(TAG, "loadArticles: "+task.getException().getMessage());
                    }
                    liveDataLoading.setValue(loading = false);
                })
                .addOnFailureListener(e -> {
                    liveDataLoading.setValue(loading = false);
                    Log.d(TAG, "onFailure: "+e.getMessage());
                });
    }

    private void bindArticles(Task<DocumentSnapshot> task) {
        ArticleObject articleObject = task.getResult().toObject(ArticleObject.class);
        articleObjectMutableLiveData.setValue(this.articleObject = articleObject);
    }
}