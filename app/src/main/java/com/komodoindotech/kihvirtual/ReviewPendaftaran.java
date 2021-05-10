package com.komodoindotech.kihvirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.komodoindotech.kihvirtual.ui.review.pendaftaran.ReviewPendaftaranFragment;

public class ReviewPendaftaran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_pendaftaran_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ReviewPendaftaranFragment.newInstance())
                    .commitNow();
        }
    }
}