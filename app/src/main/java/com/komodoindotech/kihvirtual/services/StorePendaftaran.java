package com.komodoindotech.kihvirtual.services;

import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.repositories.PendaftaranRepository;

import java.util.UUID;

public class StorePendaftaran {

    public static long handler(PendaftaranRepository pendaftaranDao, Pendaftaran pendaftaran){
        pendaftaran.setCid(UUID.randomUUID().toString() + pendaftaran.getNama());
        return pendaftaranDao.insert(pendaftaran);
    }

}
