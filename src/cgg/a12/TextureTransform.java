package cgg.a12;

import cgtools.Color;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Sampler;

public class TextureTransform implements Sampler {
		
	private Sampler sampler;
	private Matrix transformation;

	public TextureTransform(Sampler sampler, Matrix transformation) {
		this.sampler = sampler;
		this.transformation = transformation;
	}

	@Override
	public Color getColor(double u, double v) {
		Point transformed = Matrix.multiply(transformation, new Point(u, v, 0));
		return sampler.getColor(transformed.x(), transformed.y());
	}
	
}
