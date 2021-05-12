package com.komodoindotech.kihvirtual.services;

import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.models.RiwayatPersalinan;
import com.komodoindotech.kihvirtual.repositories.RiwayatKehamilanRepository;
import com.komodoindotech.kihvirtual.repositories.RiwayatPersalinanRepository;

import java.util.List;

public class StoreRiwayatPersalinan {
    public static Long[] handler(RiwayatPersalinanRepository riwayatKehamilanRepository, List<RiwayatPersalinan> riwayatPersalinans){
        return riwayatKehamilanRepository.insertAll(riwayatPersalinans);
    }
}
