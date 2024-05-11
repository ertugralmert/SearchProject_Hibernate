package com.mert.controller;

import com.mert.entity.Kategori;
import com.mert.service.KategoriService;
import com.mert.utility.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KategoriController {
    private final KategoriService kategoriService;
    private final Scanner scanner = new Scanner(System.in);
    public KategoriController() {
        kategoriService = new KategoriService();
    }

    public void createCategory(){
        System.out.print("Kategori Adını Giriniz: ");
        String kategoriAdi = scanner.nextLine();
        System.out.print("Üst Kategori Adını Griniz:");
        String parentKategoriAdi = scanner.nextLine();
        Response<Kategori> response = kategoriService.createCategory(kategoriAdi,parentKategoriAdi);
        System.out.println(response.getMessage());
    }
    public void listCategories() {
        List<Kategori> mainCategories = kategoriService.findMainCategories(); // Ana kategorileri bul
        if (mainCategories.isEmpty()) {
            System.out.println("Ana kategori bulunamadı.");
        } else {
            for (Kategori category : mainCategories) {
                printCategoryTree(category, ""); // Her kategori için ağaç yapısını yazdır
            }
        }
    }

    private void printCategoryTree(Kategori category, String indent) {
        System.out.println(indent + category.getKategoriAdi()); // Kategori adını yazdır
        for (Kategori child : category.getAltKategoriler()) { // Alt kategorileri yinele
            printCategoryTree(child, indent + "  "); // Recursive olarak bu metod kendini çağırır
        }
    }



}
//kategoriService.getAllCategories().forEach(System.out::println);

/**
 *  public void createMainCategory() {
 *         System.out.print("Ana Kategori Adını Giriniz: ");
 *         String AnaKategoriAdi= scanner.nextLine();
 *         Response<Kategori> response = kategoriService.createMainCategory(AnaKategoriAdi);
 *         System.out.println(response.getMessage());
 *
 *     }
 *     public void createChildCategory() {
 *         System.out.print("Ana Kategori Adını Giriniz: ");
 *         String AnaKategoriAdi= scanner.nextLine();
 *         System.out.print("Child KAtegori Adını Giriniz: ");
 *         String ChildKategoriAdi= scanner.nextLine();
 *         Response<Kategori> response = new Response<>();
 *         kategoriService.createChildCategory(AnaKategoriAdi,ChildKategoriAdi);
 *         System.out.println(response.getMessage());
 *     }
 */