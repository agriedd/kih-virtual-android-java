package com.komodoindotech.kihvirtual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.komodoindotech.kihvirtual.json.PilihanObject;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;
import com.komodoindotech.kihvirtual.models.RiwayatContract;
import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;
import com.komodoindotech.kihvirtual.ui.kesimpulan.KesimpulanFragment;
import com.komodoindotech.kihvirtual.ui.kesimpulan.KesimpulanKuning;
import com.komodoindotech.kihvirtual.ui.kesimpulan.KesimpulanMerah;
import com.komodoindotech.kihvirtual.ui.kesimpulan.KesimpulanViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KesimpulanActivity extends AppCompatActivity {

    private KesimpulanViewModel kesimpulanViewModel;
    private int kesimpulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kesimpulan_activity);
        kesimpulanViewModel = new ViewModelProvider(this).get(KesimpulanViewModel.class);

        Long id = 0L;
        if(getIntent().getExtras() != null)
            id = getIntent().getLongExtra("id", 0L);
        kesimpulanViewModel.setId(id);
        kesimpulanViewModel.getPendaftaran().observe(this, this::prosesKesimpulan);
    }

    private void prosesKesimpulan(PendaftaranDanRiwayat pendaftaranDanRiwayat) {

        kesimpulan = PilihanObject.WARNA_HIJAU;

        List<RiwayatContract> merah = new ArrayList<>();
        List<RiwayatContract> kuning = new ArrayList<>();
        List<RiwayatContract> hijau = new ArrayList<>();

        for(RiwayatKehamilan riwayatKehamilan : pendaftaranDanRiwayat.riwayatKehamilans){
            if(riwayatKehamilan.warna == PilihanObject.WARNA_MERAH && riwayatKehamilan.value){
                merah.add(riwayatKehamilan);
            } else if(riwayatKehamilan.warna == PilihanObject.WARNA_KUNING && riwayatKehamilan.value){
                kuning.add(riwayatKehamilan);
            } else {
                hijau.add(riwayatKehamilan);
            }
        }
        for(RiwayatPersalinan riwayatPersalinan : pendaftaranDanRiwayat.riwayatPersalinans){
            if(riwayatPersalinan.warna == PilihanObject.WARNA_MERAH && riwayatPersalinan.value)
                merah.add(riwayatPersalinan);
            else if(riwayatPersalinan.warna == PilihanObject.WARNA_KUNING && riwayatPersalinan.value)
                kuning.add(riwayatPersalinan);
            else
                hijau.add(riwayatPersalinan);
        }
        for(RiwayatKeluhan riwayatKeluhan : pendaftaranDanRiwayat.riwayatKeluhans){
            if(riwayatKeluhan.warna == PilihanObject.WARNA_MERAH && riwayatKeluhan.value)
                merah.add(riwayatKeluhan);
            else if(riwayatKeluhan.warna == PilihanObject.WARNA_KUNING && riwayatKeluhan.value)
                kuning.add(riwayatKeluhan);
            else
                hijau.add(riwayatKeluhan);
        }

        if(merah.size() > 0){
            kesimpulan = PilihanObject.WARNA_MERAH;
            replaceFragment(KesimpulanMerah.newInstance());
        } else if(kuning.size() > 0){
            kesimpulan = PilihanObject.WARNA_KUNING;
            replaceFragment(KesimpulanKuning.newInstance());
        } else {
            kesimpulan = PilihanObject.WARNA_HIJAU;
            replaceFragment(KesimpulanMerah.newInstance());
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow();
    }
}