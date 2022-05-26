package com.example.desafioimagem2.service;

import com.example.desafioimagem2.codes_images.image.mapping.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThreadCompare extends Thread {

    private final String nomePais;
    private final Image imagemPrincipal;
    private final Image imageToCompare;
    private static final String PATH_PRINCIPAL = "src/main/java/imagemPrincipal/";

    public static final List<Map<String, Object>> similaridades = new ArrayList<>();

    public ThreadCompare(String nomePais, Image imagemPrincipal, Image imageToCompare) {
        this.nomePais = nomePais;
        this.imagemPrincipal = imagemPrincipal;
        this.imageToCompare = imageToCompare;
    }


    @Override
    public void run() {
        final String path = "src/main/java/Bandeiras/" + nomePais + ".tif";
        //byte[] imageBytes = Files.readAllBytes(Path.of(path));

        final File file = new File(path);

        final double euclidean = this.imagemPrincipal.compareEuclideanDistance(imageToCompare);

        similaridades.add(
                Map.of("similaridade", euclidean,
                        "nomeBandeira", nomePais,
                        "pathImage", file.getAbsolutePath())
        );
    }
}
