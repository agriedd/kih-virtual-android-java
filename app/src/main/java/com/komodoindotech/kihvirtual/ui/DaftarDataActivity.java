package com.komodoindotech.kihvirtual.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ui.data.DaftarDataFragment;
import com.komodoindotech.kihvirtual.ui.data.DaftarDataKosongFragment;
import com.komodoindotech.kihvirtual.ui.data.DaftarDataViewModel;

public class DaftarDataActivity extends AppCompatActivity {

    DaftarDataViewModel daftarDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_data_activity);
        daftarDataViewModel = new ViewModelProvider(this).get(DaftarDataViewModel.class);
        daftarDataViewModel.getPendaftaranDanRiwayatLiveData().observe(this, pendaftaranDanRiwayats -> {
            if(pendaftaranDanRiwayats == null){
                replaceFragment(DaftarDataKosongFragment.newInstance());
            } else {
                replaceFragment(DaftarDataFragment.newInstance());
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow();
    }
}