package com.komodoindotech.kihvirtual.services;

import com.komodoindotech.kihvirtual.models.RiwayatImunisasi;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;
import com.komodoindotech.kihvirtual.repositories.RiwayatImunisasiRepository;
import com.komodoindotech.kihvirtual.repositories.RiwayatPersalinanRepository;

import java.util.List;

public class StoreRiwayatImunisasi {
    public static Long[] handler(RiwayatImunisasiRepository riwayatImunisasiRepository, List<RiwayatImunisasi> riwayatImunisasiList){
        return riwayatImunisasiRepository.insertAll(riwayatImunisasiList);
    }
}
