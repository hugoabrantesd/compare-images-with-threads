package com.example.desafioimagem2.codes_images.tests;

import Jama.Matrix;
import com.example.desafioimagem2.codes_images.image.Histogram;
import com.example.desafioimagem2.codes_images.image.mapping.Image;

import java.awt.image.Raster;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class TesteHistogram {

    /**
     * Mitsuo / Rene / Cleyton
     */
    public static void main(String[] args) throws IOException {
        //printHistogram(Image.componentExtract("images/acne_01.tif"));
        //LayerRemoval layerRemoval = new LayerRemoval("images/acne_03.tif");
        //Raster raster = layerRemoval.getDecoderRaster();
//		printHistogram(raster);
//		printHistogram(layerRemoval.getFinalRaster());
        //printHistogram(layerRemoval.getMelaninLayer());
//		layerRemoval.getMelaninLayer()[0].print(1, 3);
    }

    public static void printHistogramFromFile(String imageFilePath) {
        try {
            printHistogram(Image.componentExtract(imageFilePath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void printHistogram(Raster raster) {
        Histogram histogram = new Histogram(raster);

        System.out.println("R");
        Map<Integer, Integer> rHist = histogram.getRColorMap();
        Integer[] keys = new Integer[rHist.size()];
        keys = rHist.keySet().toArray(keys);
        Arrays.sort(keys);

        for (Integer integer : keys) {
            Integer value = rHist.get(integer);

            System.out.println(integer + " - " + value);
        }

        System.out.println("G");
        rHist = histogram.getGColorMap();
        keys = new Integer[rHist.size()];
        keys = rHist.keySet().toArray(keys);
        Arrays.sort(keys);

        for (Integer integer : keys) {
            Integer value = rHist.get(integer);

            System.out.println(integer + " - " + value);
        }

        System.out.println("B");
        rHist = histogram.getBColorMap();
        keys = new Integer[rHist.size()];
        keys = rHist.keySet().toArray(keys);
        Arrays.sort(keys);

        for (Integer integer : keys) {
            Integer value = rHist.get(integer);

            System.out.println(integer + " - " + value);
        }
    }

    private static void printHistogram(Matrix[] matrixes) {
        Histogram histogram = new Histogram(matrixes, 256);

        System.out.println("R");
        Map<Integer, Integer> rHist = histogram.getRColorMap();
        Integer[] keys = new Integer[rHist.size()];
        keys = rHist.keySet().toArray(keys);
        Arrays.sort(keys);

//		for (Integer integer : keys) {
//			Integer value = rHist.get(integer);
        for (int i = 0; i < 257; i++) {
            Integer value = rHist.get(i);
            if (value == null) {
                value = 0;
            }

            System.out.println(i + " - " + value);
        }

        System.out.println("G");
        rHist = histogram.getGColorMap();
        keys = new Integer[rHist.size()];
        keys = rHist.keySet().toArray(keys);
        Arrays.sort(keys);

//		for (Integer integer : keys) {
//			Integer value = rHist.get(integer);
        for (int i = 0; i < 257; i++) {
            Integer value = rHist.get(i);
            if (value == null) {
                value = 0;
            }

            System.out.println(i + " - " + value);
        }

        System.out.println("B");
        rHist = histogram.getBColorMap();
        keys = new Integer[rHist.size()];
        keys = rHist.keySet().toArray(keys);
        Arrays.sort(keys);

//		for (Integer integer : keys) {
//			Integer value = rHist.get(integer);
        for (int i = 0; i < 257; i++) {
            Integer value = rHist.get(i);
            if (value == null) {
                value = 0;
            }

            System.out.println(i + " - " + value);
        }
    }


}
