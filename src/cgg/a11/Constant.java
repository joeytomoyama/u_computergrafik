package cgg.a11;

import cgtools.Color;
import cgtools.Sampler;

public class Constant implements Sampler {

	private Color color;

	public Constant(Color color) {
		this.color = color;
	}

	@Override
	public Color getColor(double x, double y) {
		return color;
	}
	
}
