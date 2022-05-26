package com.example.desafioimagem2.codes_images.tests;

import com.example.desafioimagem2.codes_images.image.mapping.Image;

import java.io.IOException;

public class TesteImage {
	
	
	public static void main(String[] args) throws IOException {
		
		//exemplo para comparar a similaridade do histograma de 2 fotos
		String filename01 = "images/skin_HEMOGLOBIN.tif";
		String filename02 = "images/converter/teste1.tiff";
		
		//filename01 = tests.ImageConverter.convertTiffAndCrop(filename01);
		//filename02 = tests.ImageConverter.convertTiffAndCrop(filename02);
		
		Image image01 = new Image(Image.componentExtract(filename01), 255);
		Image image02 = new Image(Image.componentExtract(filename02), 255);

		double euclidean = image01.compareEuclideanDistance(image02);
		System.out.printf("\nDistância Euclidiana: %s -> %s: %.2f\n", filename01, filename02, euclidean);
		
	}

	

	
	
}
