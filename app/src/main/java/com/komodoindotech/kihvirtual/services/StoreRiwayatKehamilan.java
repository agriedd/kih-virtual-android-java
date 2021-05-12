package com.komodoindotech.kihvirtual.services;

import com.komodoindotech.kihvirtual.models.RiwayatKehamilan;
import com.komodoindotech.kihvirtual.repositories.RiwayatKehamilanRepository;

import java.util.List;

public class StoreRiwayatKehamilan {
    public static Long[] handler(RiwayatKehamilanRepository riwayatKehamilanRepository, List<RiwayatKehamilan> riwayatKehamilans){
        return riwayatKehamilanRepository.insertAll(riwayatKehamilans);
    }
}
