package com.komodoindotech.kihvirtual.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.ui.data.DaftarDataFragment;
import com.komodoindotech.kihvirtual.ui.data.DaftarDataKosongFragment;
import com.komodoindotech.kihvirtual.ui.data.DaftarDataViewModel;

public class DaftarDataActivity extends AppCompatActivity {

    DaftarDataViewModel daftarDataViewModel;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_data_activity);
        daftarDataViewModel = new ViewModelProvider(this).get(DaftarDataViewModel.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customView = getLayoutInflater().inflate(R.layout.progress_bar_loading_bar, null);
        TextView messageDialog = customView.findViewById(R.id.text_progress_bar);
        messageDialog.setText("Sedang memproses...");
        builder.setView(customView);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        daftarDataViewModel.getLoading().observe(this, aBoolean -> {
            if(aBoolean){
                dialog.show();
            } else {
                dialog.dismiss();
            }
        });

        daftarDataViewModel.getPendaftaranDanRiwayatLiveData().observe(this, pendaftaranDanRiwayats -> {
            if(pendaftaranDanRiwayats == null || pendaftaranDanRiwayats.size() <= 0){
                replaceFragment(DaftarDataKosongFragment.newInstance());
            } else {
                replaceFragment(DaftarDataFragment.newInstance());
            }
        });

        daftarDataViewModel.loadLocalPendaftaran();
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow();
    }
}