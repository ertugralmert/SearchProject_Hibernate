package com.mert.controller;

import com.mert.entity.Kategori;
import com.mert.service.KategoriService;
import com.mert.utility.Response;

import java.util.Scanner;

public class KategoriController {
    private final KategoriService kategoriService;
    private final Scanner scanner = new Scanner(System.in);
    public KategoriController() {
        kategoriService = new KategoriService();
    }

    public void createMainCategory() {
        System.out.print("Ana Kategori Adını Giriniz: ");
        String AnaKategoriAdi= scanner.nextLine();
        Response<Kategori> response = kategoriService.createMainCategory(AnaKategoriAdi);
        System.out.println(response.getMessage());

    }
    public void createChildCategory() {
        System.out.print("Ana Kategori Adını Giriniz: ");
        String AnaKategoriAdi= scanner.nextLine();
        System.out.print("Child KAtegori Adını Giriniz: ");
        String ChildKategoriAdi= scanner.nextLine();
        Response<Kategori> response = new Response<>();
        kategoriService.createChildCategory(AnaKategoriAdi,ChildKategoriAdi);
        System.out.println(response.getMessage());
    }




    public void listCategories() {
        kategoriService.getAllCategories().forEach(System.out::println);
    }
}
