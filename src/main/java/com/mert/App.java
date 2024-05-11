package com.mert;

import com.mert.controller.KategoriController;

public class App {

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run();
        KategoriController controller = new KategoriController();
        controller.listCategories();
    }
}
