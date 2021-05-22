package com.komodoindotech.kihvirtual.services;

import com.komodoindotech.kihvirtual.models.Pendaftaran;
import com.komodoindotech.kihvirtual.models.PendaftaranDanRiwayat;
import com.komodoindotech.kihvirtual.repositories.PendaftaranRepository;

public class UpdatePendaftaran {
    public static void handler(PendaftaranRepository pendaftaranRepository, long id_pendaftaran, String id){
        PendaftaranDanRiwayat pendaftaran = pendaftaranRepository.find(id_pendaftaran);
        if(pendaftaran != null && pendaftaran.pendaftaran != null){
            pendaftaran.pendaftaran.setCid(id);
            pendaftaranRepository.update(pendaftaran.pendaftaran);
        }
    }
}
