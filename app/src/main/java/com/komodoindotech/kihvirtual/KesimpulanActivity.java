package com.komodoindotech.kihvirtual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
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
        String id_cloud = null;
        if(getIntent().getExtras() != null){
            id = getIntent().getLongExtra("id_pendaftaran", 0L);
            id_cloud = getIntent().getStringExtra("id_pendaftaran_cloud");
        }
        kesimpulanViewModel.setId(id);
        if(id_cloud != null){
            kesimpulanViewModel.setIdCloud(id_cloud);
        }
        kesimpulanViewModel.loadDataPendaftaran();
        kesimpulanViewModel.getPendaftaran().observe(this, this::prosesKesimpulan);
    }

    private void prosesKesimpulan(PendaftaranDanRiwayat pendaftaranDanRiwayat) {

        Log.d("wtf", "prosesKesimpulan: "+ JSON.toJSONString(pendaftaranDanRiwayat));

        if(pendaftaranDanRiwayat != null) {

            kesimpulan = PilihanObject.WARNA_HIJAU;

            List<RiwayatContract> merah = new ArrayList<>();
            List<RiwayatContract> kuning = new ArrayList<>();
            List<RiwayatContract> hijau = new ArrayList<>();

            if (pendaftaranDanRiwayat.riwayatKehamilans != null) {
                for (RiwayatKehamilan riwayatKehamilan : pendaftaranDanRiwayat.riwayatKehamilans) {
                    if (riwayatKehamilan.warna == PilihanObject.WARNA_MERAH && riwayatKehamilan.value) {
                        merah.add(riwayatKehamilan);
                    } else if (riwayatKehamilan.warna == PilihanObject.WARNA_KUNING && riwayatKehamilan.value) {
                        kuning.add(riwayatKehamilan);
                    } else {
                        hijau.add(riwayatKehamilan);
                    }
                }
            }
            if (pendaftaranDanRiwayat.riwayatPersalinans != null)
                for (RiwayatPersalinan riwayatPersalinan : pendaftaranDanRiwayat.riwayatPersalinans) {
                    if (riwayatPersalinan.warna == PilihanObject.WARNA_MERAH && riwayatPersalinan.value)
                        merah.add(riwayatPersalinan);
                    else if (riwayatPersalinan.warna == PilihanObject.WARNA_KUNING && riwayatPersalinan.value)
                        kuning.add(riwayatPersalinan);
                    else
                        hijau.add(riwayatPersalinan);
                }
            if (pendaftaranDanRiwayat.riwayatKeluhans != null)
                for (RiwayatKeluhan riwayatKeluhan : pendaftaranDanRiwayat.riwayatKeluhans) {
                    if (riwayatKeluhan.warna == PilihanObject.WARNA_MERAH && riwayatKeluhan.value)
                        merah.add(riwayatKeluhan);
                    else if (riwayatKeluhan.warna == PilihanObject.WARNA_KUNING && riwayatKeluhan.value)
                        kuning.add(riwayatKeluhan);
                    else
                        hijau.add(riwayatKeluhan);
                }

            if (merah.size() > 0) {
                kesimpulan = PilihanObject.WARNA_MERAH;
                replaceFragment(KesimpulanMerah.newInstance());
            } else if (kuning.size() > 0) {
                kesimpulan = PilihanObject.WARNA_KUNING;
                replaceFragment(KesimpulanKuning.newInstance());
            } else {
                kesimpulan = PilihanObject.WARNA_HIJAU;
                replaceFragment(KesimpulanHijau.newInstance());
            }
        } else {
            Toast.makeText(getApplicationContext(), "Data tidak ditemukan", Toast.LENGTH_LONG).show();
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(kesimpulanViewModel.id_pendaftaran.getValue() != null && kesimpulanViewModel.id_pendaftaran.getValue() != 0L && kesimpulanViewModel.id_pendaftaran_cloud.getValue() != null)
            Toast.makeText(getApplicationContext(), "Data yang sudah diinput dapat dilihat pada menu lainnya", Toast.LENGTH_LONG).show();
    }
}