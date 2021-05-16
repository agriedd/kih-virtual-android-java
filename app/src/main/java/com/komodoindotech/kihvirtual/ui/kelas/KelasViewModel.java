package com.komodoindotech.kihvirtual.ui.kelas;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.komodoindotech.kihvirtual.json.KelasObject;
import com.komodoindotech.kihvirtual.json.PuskesmasObject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KelasViewModel extends AndroidViewModel {

    private static final String TAG = "kelasvm";

    private final MutableLiveData<List<KelasObject>> kelasListMutableLiveData;

    FirebaseFirestore db;

    public KelasViewModel(@NonNull @NotNull Application application) {
        super(application);
        db = FirebaseFirestore.getInstance();

        kelasListMutableLiveData = new MutableLiveData<>();

        getPuskesmas();
    }

    public void getPuskesmas(){
        db.collection("groups")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                })
                .addOnFailureListener(e -> {

                })
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        List<KelasObject> kelasObjectList = new ArrayList<>();
                        for(QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                            KelasObject kelasObject = document.toObject(KelasObject.class);
                            kelasObject.setId(document.getId());
                            kelasObjectList.add(kelasObject);
                        }
                        kelasListMutableLiveData.setValue(kelasObjectList);
                    } else Log.d(TAG, "KelasViewModel: failed: "+ task.getException());
                });
    }

    public LiveData<List<KelasObject>> getKelasListMutableLiveData() {
        return kelasListMutableLiveData;
    }
}
