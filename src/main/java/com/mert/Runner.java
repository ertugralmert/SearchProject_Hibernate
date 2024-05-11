package com.mert;

import com.mert.controller.KategoriController;
import com.mert.controller.UrunController;
import com.mert.utility.GUI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {
    private final KategoriController kategoriController;
    private final UrunController urunController;
    private final Scanner scanner = new Scanner(System.in);

    private final GUI gui;

    public Runner() {
        kategoriController = new KategoriController();
        urunController = new UrunController();
        gui = new GUI();
    }

    public void run() {
        int secim = -1;
        do{
            try {
                gui.gui();
                System.out.print("Secim: ");
                secim = scanner.nextInt();
                gui.gui();
                switch (secim) {
                    case 1: // KATEGORI
                        kategoriController.createMainCategory();
                        break;
                    case 2:
                        kategoriController.createChildCategory();
                        break;
                    case 3:
                        kategoriController.listCategories();
                        break;
                    case 4:
                        urunController.createUrun();
                        break;
                    case 5:
                        urunController.searchUrun();
                        break;
                    case 6:
                        urunController.listUrun();
                        break;
                    default:
                        System.out.println("Hatalı seçim yaptınız");
                        break;
                }

            }catch (InputMismatchException e){
                System.out.println("Hatalı geçerli bir seçim yapınınız");
                scanner.nextLine();
            }
        }while(secim!=0);
    }

    }

