package com.example.desafioimagem2.codes_images.image.mapping;

import com.example.desafioimagem2.codes_images.space.Coordinate;

public interface Repository {
	Image getImage(Coordinate coordinate);
	Image[] getImage(Coordinate coordinate, int kNeighbours);
	void addImage(Image image);
	void remImage(Image image);
}
