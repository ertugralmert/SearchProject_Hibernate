package com.mert.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@NamedQueries({
    @NamedQuery(name = "Kategori.searchByName", query = "SELECT k FROM Kategori k WHERE lower(k.kategoriAdi) = :kategoriAdi"),

})
@EqualsAndHashCode(exclude = {"parentKategori", "altKategoriler"})
@Data //
@AllArgsConstructor //
@NoArgsConstructor // parametreli constructor ların tümü
@Builder //default constructor
@Entity
@Table(name = "tblkategori")
public class Kategori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id için otomatik artan HB sequence oluşturur
    private Long id;
    @Column(unique = true)
    private String kategoriAdi;
    @ManyToOne
    @JoinColumn(name = "ustKategoriId")
    private Kategori parentKategori;
    @Builder.Default
    @OneToMany(mappedBy = "parentKategori")
    private Set<Kategori> altKategoriler = new HashSet<>();
}
