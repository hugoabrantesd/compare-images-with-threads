package com.example.desafioimagem2.codes_images.image;

import Jama.Matrix;

import java.awt.image.Raster;

public class ColorHistogram extends Histogram {
	private int[][][] histogram;
	private int numOfBins;
	
	public ColorHistogram(Raster raster, int numOfBins) {
		super(raster);
		this.numOfBins = numOfBins;
	}

	public ColorHistogram(Matrix[] matrixes, int maxValue, int numOfBins) {
		super(matrixes, maxValue);
		this.numOfBins = numOfBins;
	}

}
