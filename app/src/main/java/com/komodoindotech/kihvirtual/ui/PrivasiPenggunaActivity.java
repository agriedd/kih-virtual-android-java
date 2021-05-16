package com.komodoindotech.kihvirtual.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ui.privasipengguna.PrivasiPenggunaFragment;

public class PrivasiPenggunaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privasi_pengguna_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PrivasiPenggunaFragment.newInstance())
                    .commitNow();
        }
    }
}