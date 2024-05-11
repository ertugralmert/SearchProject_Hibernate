package com.mert.service;

import com.mert.entity.Kategori;
import com.mert.repository.KategoriRepository;
import com.mert.utility.Response;
import com.mert.utility.UniqChecker;

import java.util.List;
import java.util.Optional;

public class KategoriService {
    private final KategoriRepository kategoriRepository;
    private final UniqChecker<Kategori> uniqChecker = new UniqChecker<>();


    public KategoriService() {
        kategoriRepository = new KategoriRepository();
    }

    public Response<Kategori> createMainCategory(String kategoriAdi) {
        Optional<Kategori> existKategori = kategoriRepository.searchByName(kategoriAdi.trim().toLowerCase());
        Response<Kategori> response = new Response<>();
        if (!existKategori.isPresent()) {
            Kategori kategori = Kategori.builder()
                    .kategoriAdi(kategoriAdi)
                    .build();
            kategoriRepository.save(kategori);
            response.setData(kategori);
            response.setStatusCode(200);
            response.setMessage("Kategori oluşturuldu.");
            return response;
        }else {
            response.setStatusCode(400);
            response.setMessage("Kategori zaten mevcut.");
            response.setData(null);
            return response;
        }
    }

    public Response<Kategori> createChildCategory(String parentKategoriAdi, String kategoriAdi) {
        Kategori parentKategori = findCategoryByName(parentKategoriAdi);
        Response<Kategori> response = new Response<>();
        if(parentKategori == null){
             response.setMessage("Üst Kategori bulunamadı");
             response.setStatusCode(404);
             response.setData(null);
             return response;
        }
        List<Kategori> allCategories = kategoriRepository.findAll();

        if (uniqChecker.isUnique(allCategories, Kategori::getKategoriAdi, kategoriAdi)) {
            Kategori kategori = Kategori.builder()
                    .kategoriAdi(kategoriAdi)
                    .parentKategori(parentKategori)
                    .build();
            kategoriRepository.save(kategori);
            response.setData(kategori);
            response.setStatusCode(200);
            response.setMessage("Kategori oluşturuldu.");
            return response;
        }else if (!uniqChecker.isUnique(allCategories, Kategori::getKategoriAdi, kategoriAdi)) {
            response.setStatusCode(400);
            response.setMessage("Kategori zaten mevcut.");
            return response;
        }else{
            response.setStatusCode(500);
            response.setMessage("Kategori oluşturulamadı. Sistemsel bir hata oluştu");
            return response;
        }
    }

    public List<Kategori> getAllCategories() {
        return kategoriRepository.findAll();
    }

    private Kategori findCategoryByName(String kategoriAdi) {
        return getAllCategories().stream().filter(k -> k.getKategoriAdi().equalsIgnoreCase(kategoriAdi)).
               findFirst().orElse(null);
    }
}
