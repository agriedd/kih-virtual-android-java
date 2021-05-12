package com.komodoindotech.kihvirtual.services;

import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.repositories.PendaftaranRepository;

public class StorePendaftaran {

    public static long handler(PendaftaranRepository pendaftaranDao, Pendaftaran pendaftaran){
        return pendaftaranDao.insert(pendaftaran);
    }

}
