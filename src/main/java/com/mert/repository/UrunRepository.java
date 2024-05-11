package com.mert.repository;

import com.mert.entity.Urun;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class UrunRepository extends Repository<Urun, Long> {

    public UrunRepository() {
        super(new Urun());
    }

    public List<Urun> searchUrun(String urunAdi) {
        openSS();
        TypedQuery<Urun> typedQuery = getEm().createNamedQuery("Urun.searchUrun",Urun.class);
        typedQuery.setParameter("urunAdi","%"+urunAdi.toLowerCase()+"%");
        typedQuery.setMaxResults(10);
        return typedQuery.getResultList();
    }

    public Optional<Urun> searchByName(String urunAdi) {
        openSS();
        TypedQuery<Urun> typedQuery = getEm().createNamedQuery("Urun.searchByName",Urun.class);
        typedQuery.setParameter("urunAdi",urunAdi);
        List<Urun> resultList = typedQuery.getResultList(); //<Urun>
        return resultList.stream().findFirst();
    }
}
