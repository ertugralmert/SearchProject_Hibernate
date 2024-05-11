package com.mert.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedQueries({
    @NamedQuery(name = "Urun.searchUrun", query = "SELECT u FROM Urun u WHERE lower(u.urunAdi) like :urunAdi"),
        @NamedQuery(name = "Urun.searchByName", query = "SELECT u FROM Urun u WHERE u.urunAdi = :urunAdi")
})
@Data //
@AllArgsConstructor //
@NoArgsConstructor // parametreli constructor ların tümü
@Builder //default constructor
@Entity
@Table(name = "tblurun")
public class Urun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id için otomatik artan HB sequence oluşturur
    private Long id;
    private String urunAdi;
    private String urunAciklamasi;
    private double urunFiyati;
    private int urunStokAdedi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kategoriId")
    private Kategori kategori;
}
