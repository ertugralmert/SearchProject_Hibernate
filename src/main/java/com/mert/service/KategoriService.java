package com.mert.service;

import com.mert.entity.Kategori;
import com.mert.repository.KategoriRepository;
import com.mert.utility.Response;
import com.mert.utility.UniqChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class KategoriService {
    private final KategoriRepository kategoriRepository;
    private final UniqChecker<Kategori> uniqChecker = new UniqChecker<>();


    public KategoriService() {
        kategoriRepository = new KategoriRepository();
    }

    public Response<Kategori> createCategory(String kategoriAdi,String parentKategoriAdi) {
        kategoriAdi = kategoriAdi.trim().toLowerCase();
        if(kategoriAdi.isEmpty()){
            return new Response<>(400,"Kategori Adı Boş Kalamaz",null);
        }
        Optional<Kategori> existKategori = kategoriRepository.searchByName(kategoriAdi);
        if (existKategori.isPresent()) {
            return new Response<>(400, "Kategori zaten mevcut", null);
        }
        Kategori parentKategori = null;
        if (parentKategoriAdi != null && !parentKategoriAdi.isEmpty()) {
            parentKategori = findCategoryByName(parentKategoriAdi.trim().toLowerCase());
            if (parentKategori == null) {
                return new Response<>(404, "Ust Kategori Bulunamadı", null);
            }
        }
        Kategori newKategori = Kategori.builder()
                .kategoriAdi(kategoriAdi)
                .parentKategori(parentKategori)
                .build();
        kategoriRepository.save(newKategori);
        return new Response<>(200, "Kategori oluşturuldu", newKategori);
    }





    public List<Kategori> getAllCategories() {
        return kategoriRepository.findAllwithAll();
    }

    private Kategori findCategoryByName(String kategoriAdi) {
        return getAllCategories().stream().filter(k -> k.getKategoriAdi().equalsIgnoreCase(kategoriAdi)).
                findFirst().orElse(null);
    }
///////

    public List<Kategori> findMainCategories() {
        return getAllCategories().stream().filter(k -> k.getParentKategori() == null).toList();
    }




}


/**
 * public Response<Kategori> createMainCategory(String kategoriAdi) {
 *         Optional<Kategori> existKategori = kategoriRepository.searchByName(kategoriAdi.trim().toLowerCase());
 *         Response<Kategori> response = new Response<>();
 *         if (!existKategori.isPresent()) {
 *             Kategori kategori = Kategori.builder()
 *                     .kategoriAdi(kategoriAdi)
 *                     .build();
 *             kategoriRepository.save(kategori);
 *             response.setData(kategori);
 *             response.setStatusCode(200);
 *             response.setMessage("Kategori oluşturuldu.");
 *             return response;
 *         } else {
 *             response.setStatusCode(400);
 *             response.setMessage("Kategori zaten mevcut.");
 *             response.setData(null);
 *             return response;
 *         }
 *     }
 *
 *     public Response<Kategori> createChildCategory(String parentKategoriAdi, String kategoriAdi) {
 *         Kategori parentKategori = findCategoryByName(parentKategoriAdi);
 *
 *         if (parentKategori == null) {
 *             return new Response<>(404, "Ust Kategori Bulunamadı", null );
 *         }
 *         List<Kategori> allCategories = kategoriRepository.findAll();
 *
 *         if (uniqChecker.isUnique(allCategories, Kategori::getKategoriAdi, kategoriAdi)) {
 *             Kategori kategori = Kategori.builder()
 *                     .kategoriAdi(kategoriAdi)
 *                     .parentKategori(parentKategori)
 *                     .build();
 *             kategoriRepository.save(kategori);
 *             return new Response<>(200, "Kategori oluşturuldu.", kategori);
 *         } else if (!uniqChecker.isUnique(allCategories, Kategori::getKategoriAdi, kategoriAdi)) {
 *             return new Response<>(400, "Kategori zaten mevcut.", null);
 *         } else {
 *             return new Response<>(404, "Ust Kategori Bulunamadı", null );
 *         }
 *     }
 */