package com.komodoindotech.kihvirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.komodoindotech.kihvirtual.ui.mengenai.MengenaiAplikasiFragment;

public class MengenaiAplikasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mengenai_aplikasi_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MengenaiAplikasiFragment.newInstance())
                    .commitNow();
        }
    }
}