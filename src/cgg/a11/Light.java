package cgg.a11;

import cgg.a11.shapes.Shape;
import cgtools.Color;
import cgtools.Direction;

public interface Light {
	// public Direction radiance(Point point);
	// public Direction direction(Point point);
	// public boolean isVisible(Point point, Shape scene);
	public Color incomingIntensity(Hit hit, Shape scene);
}
