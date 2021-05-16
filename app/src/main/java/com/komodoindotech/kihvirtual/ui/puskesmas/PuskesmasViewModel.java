package com.komodoindotech.kihvirtual.ui.puskesmas;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.komodoindotech.kihvirtual.json.PuskesmasObject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PuskesmasViewModel extends AndroidViewModel {

    private static final String TAG = "puskesmasvm";

    private MutableLiveData<List<PuskesmasObject>> puskesmasObjectMutableLiveData;

    FirebaseFirestore db;

    public PuskesmasViewModel(@NonNull @NotNull Application application) {
        super(application);
        db = FirebaseFirestore.getInstance();

        puskesmasObjectMutableLiveData = new MutableLiveData<>();

        getPuskesmas();
    }

    public void getPuskesmas(){
        db.collection("puskesmas")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                })
                .addOnFailureListener(e -> {

                })
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        List<PuskesmasObject> puskesmasObjectList = new ArrayList<>();
                        for(QueryDocumentSnapshot document : task.getResult()){
                            PuskesmasObject puskesmasObject = document.toObject(PuskesmasObject.class);
                            puskesmasObject.setId(document.getId());
                            puskesmasObjectList.add(puskesmasObject);
                        }
                        puskesmasObjectMutableLiveData.setValue(puskesmasObjectList);
                    } else Log.d(TAG, "PuskesmasViewModel: failed: "+ task.getException());
                });
    }

    public LiveData<List<PuskesmasObject>> getPuskesmasObjectMutableLiveData() {
        return puskesmasObjectMutableLiveData;
    }
}
