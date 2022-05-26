package com.example.desafioimagem2.service;

import com.example.desafioimagem2.codes_images.image.mapping.Image;
import com.example.desafioimagem2.codes_images.tests.ImageConverter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;

@Service
public class ImagemService {

    protected static final String URL = "https://aimore.net/band/";
    private static final String PATH_PRINCIPAL = "src/main/java/imagemPrincipal/";
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(15);

    public static final String[] NOMES_PAISES = {
            "Argentina",
            "Australia",
            "Belgica",
            "Brasil",
            "Bulgaria",
            "Camaroes",
            "Canada",
            "Chile",
            "Colombia",
            "Cuba",
            "Egito",
            "Espanha",
            "Filipinas",
            "Gana",
            "Grecia",
            "Holanda",
            "Honduras",
            "India",
            "Italia",
            "Jamaica",
            "Japao",
            "Peru",
            "Polonia",
            "Portugal",
            "Qatar",
            "Romenia",
            "Russia",
            "Suecia",
            "Austria",
            "Afeganistao",
    };

    private void convertToTif() {
        for (String nomesPais : NOMES_PAISES) {
            final String pathAtual = "src/main/java/Bandeiras/" + nomesPais + ".jpg";
            final File f = new File(pathAtual);

            if (f.exists()) {
                ImageConverter.convertTiffAndCrop(pathAtual);
            }
        }
        /*String path2 = "src/main/java/imagesConvertidas/";
        final File file = new File(path2);*/

       /* if (file.exists()) {
            convertToTif();
        } else {
            if (file.mkdir()) {
                convertToTif();
            }
        }*/
    }


    public Map<String, Object> compararImagem(String nomePais) throws IOException, InterruptedException {
        System.out.println("\n=-=-=-=-=-=-= BAIXANDO IMAGEM =-=-=-=-=-=-=");
        ThreadCompare.similaridades.clear();

        URL urlImagem = new URL(URL + nomePais + ".jpg");
        final String path = PATH_PRINCIPAL + nomePais + ".jpg";
        downloadFile(urlImagem, path);

        System.out.println("********** DOWNLOAD CONCLUÍDO **********\n");

        ImageConverter.convertTiffAndCrop(PATH_PRINCIPAL + nomePais + ".jpg");

        executeCompare(nomePais);
        Thread.sleep(1000);
        ThreadCompare.similaridades.sort(Comparator.comparing(m -> (Double) m.get("similaridade")));
        System.out.println();
        int size = ThreadCompare.similaridades.size();
        System.out.println();
        System.out.println(size);

        ThreadCompare.similaridades.forEach(
                e -> System.out.println("País: " + e.get("nomeBandeira") + " -> similaridade: " + e.get("similaridade")));

        return ThreadCompare.similaridades.get(size - 1);
    }

    private void executeCompare(String nomePais) throws IOException {
        System.out.println("INÍCIO: " + LocalDateTime.now());
        String fileName = PATH_PRINCIPAL + nomePais + ".tif";
        //System.out.println(fileName);

        final int lenghtDividido = ImagemService.NOMES_PAISES.length / 2;

        final Image image01 = new Image(Image.componentExtract(fileName.trim()), 255);
        for (int i = 0; i < lenghtDividido; i++) {
            Image image02 = new Image(Image.componentExtract("src/main/java/Bandeiras/" + ImagemService.NOMES_PAISES[i] + ".tif"), 255);
            new ThreadCompare(ImagemService.NOMES_PAISES[i], image01, image02).start();
        }

        for (int i = lenghtDividido; i < ImagemService.NOMES_PAISES.length; i++) {
            Image image02 = new Image(Image.componentExtract("src/main/java/Bandeiras/" + ImagemService.NOMES_PAISES[i] + ".tif"), 255);
            new ThreadCompare(ImagemService.NOMES_PAISES[i], image01, image02).start();
        }

        System.out.println("TÉRMINO: " + LocalDateTime.now());
        /*try {
            cyclicBarrier.await(4, TimeUnit.SECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            *//*System.out.println("\nSIZE:" + ThreadCompare.similaridades.size() + "\n");
            ThreadCompare.similaridades.sort(Comparator.comparing(m -> (Double)m.get("similaridade")));*//*
        }*/

    }

    private void downloadFile(URL url, String fileName) {

        final File f = new File(PATH_PRINCIPAL);

        if (f.exists()) {
            download(url, fileName);
        } else {
            if (f.mkdir()) {
                download(url, fileName);
            }
        }
    }

    private void download(URL url, String fileName) {
        try (InputStream is = url.openStream()) {
            ReadableByteChannel rbc = Channels.newChannel(is);
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
