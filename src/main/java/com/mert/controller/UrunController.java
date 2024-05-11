package com.mert.controller;

import com.mert.entity.Kategori;
import com.mert.entity.Urun;
import com.mert.service.KategoriService;
import com.mert.service.UrunService;
import com.mert.utility.Response;
import com.mert.utility.TurkishStyle;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UrunController {

    private final UrunService urunService;
    private final KategoriService kategoriService;
    private final Scanner scanner = new Scanner(System.in);
    private final TurkishStyle turkishStyle = new TurkishStyle();


    public UrunController() {
        urunService = new UrunService();
        kategoriService = new KategoriService();
    }

    public void createUrun() {
        System.out.print("Ürün Adı: ");
        String urunAdi = scanner.nextLine();;
        System.out.print("Ürünün Açıklaması:");
        String urunAciklamasi = scanner.nextLine();
        System.out.print("Ürünun Fiyatı: ");
        double urunFiyati = scanner.nextDouble();
        System.out.print("Stok Adedi: ");
        int urunStokAdedi = scanner.nextInt();
        scanner.nextLine();

        List<Kategori> kategoriler = kategoriService.getAllCategories();
        kategoriler.forEach(kategori -> System.out.println(kategori.getId()+ " - "+ kategori.getKategoriAdi()));
        System.out.print("Eklemek istediğiniz kategori id'si: ");
        long kategoriId = scanner.nextLong();
        scanner.nextLine();
        Kategori chosenCategory = kategoriler.stream()
                .filter(kategori -> kategori.getId() == kategoriId)
                .findFirst().orElse(null);
        if(chosenCategory == null){
            System.out.println("Kategori bulunamadı.");
            return;
        }

        Urun urun = Urun.builder()
                .urunAdi(urunAdi)
                .urunAciklamasi(urunAciklamasi)
                .urunFiyati(urunFiyati)
                .urunStokAdedi(urunStokAdedi)
                .kategori(chosenCategory)
                .build();
        Response<Urun> response = urunService.createUrun(urun);
        System.out.println(response.getMessage());
    }

    public void searchUrun(){
        System.out.print("Aramak istediğiniz ürün adı: ");
        String urunAdi = scanner.nextLine().trim().toLowerCase();
        List<Urun> result = urunService.searchUrun(urunAdi);
        if(!result.isEmpty()){
            result.forEach(urun-> System.out.println("Ürün Adı: "+
                    urun.getUrunAdi() + " - "+"Ürün Fiyatı: " + turkishStyle.formatPrice(urun.getUrunFiyati())+" - "+"Ürün Stok Adedi: "+ urun.getUrunStokAdedi() ));
        }else{
            System.out.println("Urun bulunamadı.");
        }

    }

    public void listUrun() {
        urunService.getAllUrun().forEach(System.out::println);
    }



}
