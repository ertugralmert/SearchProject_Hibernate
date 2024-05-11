package com.mert.service;

import com.mert.entity.Urun;
import com.mert.repository.UrunRepository;
import com.mert.utility.Response;
import com.mert.utility.UniqChecker;

import java.util.List;
import java.util.Optional;

public class UrunService {

    private final UrunRepository urunRepository;

    public UrunService() {
        urunRepository = new UrunRepository();
    }

    public Response<Urun> createUrun(Urun urun) {
        Optional<Urun> existUrun = urunRepository.searchByName(urun.getUrunAdi());
        Response<Urun> response = new Response<>();
        if (existUrun.isPresent()) {
            response.setData(existUrun.get());
            response.setStatusCode(400);
            response.setMessage("Urun zaten mevcut.");
            return response;
        }
        urunRepository.save(urun);
        response.setData(urun);
        response.setStatusCode(200);
        response.setMessage("Urun oluşturuldu.");
        return response;
    }


public List<Urun> searchUrun(String urunAdi) {
    // gelen listeden 10 tanesini alıyoruz.
    return urunRepository.searchUrun(urunAdi);

}


public List<Urun> getAllUrun() {
    return urunRepository.findAll();
}
}
