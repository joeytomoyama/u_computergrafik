package cgg.a12;

import cgg.a12.shapes.Shape;
import cgtools.Color;

public interface Light {
	// public Direction radiance(Point point);
	// public Direction direction(Point point);
	// public boolean isVisible(Point point, Shape scene);
	public Color incomingIntensity(Hit hit, Shape scene);
}
