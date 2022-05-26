package com.example.desafioimagem2.service;

import com.example.desafioimagem2.codes_images.image.mapping.Image;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
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

        try {
            final double euclidean = this.imagemPrincipal.compareEuclideanDistance(imageToCompare);

            final byte[] imageBytes = Files.readAllBytes(Path.of(path));
            final byte[] imageBase64 = Base64.getEncoder().encode(imageBytes);

            similaridades.add(
                    Map.of("similaridade", euclidean,
                            "nomeBandeira", nomePais,
                            "imageBase64", Base64.getDecoder().decode(imageBase64))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
