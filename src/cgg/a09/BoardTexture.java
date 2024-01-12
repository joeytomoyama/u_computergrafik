package cgg.a09;

import cgtools.Color;
import cgtools.Sampler;

public record BoardTexture(int n) implements Sampler {

	@Override
	public Color getColor(double u, double v) {
		int ui = (int)((u % 1) * n);
		int vi = (int)((v % 1) * n);
		if ((ui + vi) % 2 == 0) {
			return new Color(1, 1, 1);
		} else {
			return new Color(0, 0, 0);
		}
	}
	

}
