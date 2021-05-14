package com.komodoindotech.kihvirtual.services;

import com.komodoindotech.kihvirtual.models.RiwayatImunisasi;
import com.komodoindotech.kihvirtual.models.RiwayatKeluhan;
import com.komodoindotech.kihvirtual.repositories.KeluhanRepository;
import com.komodoindotech.kihvirtual.repositories.RiwayatImunisasiRepository;

import java.util.List;

public class StoreKeluhan {
    public static Long[] handler(KeluhanRepository keluhanRepository, List<RiwayatKeluhan> riwayatKeluhans){
        return keluhanRepository.insertAll(riwayatKeluhans);
    }
}
