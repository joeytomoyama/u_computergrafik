package cgg.a11;

import cgg.a11.shapes.Shape;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public record LightPoint(Point position, double intensity) implements Light {

	@Override
	public Color incomingIntensity(Hit hit, Shape scene) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'incomingIntensity'");
	}
	
}
