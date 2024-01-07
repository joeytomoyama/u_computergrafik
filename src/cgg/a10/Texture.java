package cgg.a10;

import cgtools.Color;
import cgtools.Sampler;

public class Texture implements Sampler {

	private Color color;

	public Texture(Color color) {
		this.color = color;
	}

	@Override
	public Color getColor(double x, double y) {
		return color;
	}
	
}
