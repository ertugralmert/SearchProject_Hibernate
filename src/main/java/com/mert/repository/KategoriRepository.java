package com.mert.repository;

import com.mert.entity.Kategori;
import com.mert.entity.Urun;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class KategoriRepository extends Repository<Kategori, Long> {

    public KategoriRepository() {
        super(new Kategori());
    }
    public Optional<Kategori> searchByName(String kategoriAdi) {
        openSS();
        TypedQuery<Kategori> typedQuery = getEm().createNamedQuery("Kategori.searchByName",Kategori.class);
        typedQuery.setParameter("kategoriAdi",kategoriAdi.trim().toLowerCase());
        List<Kategori> resultList = typedQuery.getResultList();
        return resultList.stream().findFirst();

    }
}
