package cgg.a11;

import cgtools.Color;
import cgtools.ImageTexture;
import cgtools.Sampler;

public class Texture implements Sampler {

	private ImageTexture texture;

	public Texture(String filename) {
		this.texture = new ImageTexture(filename);
	}

	@Override
	public Color getColor(double x, double y) {
		return texture.getColor(x, y);
	}
	
}
