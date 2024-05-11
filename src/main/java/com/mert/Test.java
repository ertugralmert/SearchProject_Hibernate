package com.mert;

import com.mert.controller.KategoriController;
import com.mert.entity.Kategori;
import com.mert.repository.KategoriRepository;
import com.mert.utility.GUI;

public class Test {
    public static void main(String[] args) {

        GUI guigui = new GUI();
        KategoriRepository kategoriRepository = new KategoriRepository();
        KategoriController kategoriController = new KategoriController();

        guigui.gui();
//        kategoriController.createMainCategory();
//        kategoriController.createChildCategory();
        kategoriController.listCategories();
    }
}
