package cgg.a11;

import cgg.a11.shapes.Shape;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Ray;
import cgtools.Vector;

public record LightDirection(Direction direction) implements Light {

	@Override
	public Color incomingIntensity(Hit hit, Shape scene) {
		// check if the light is visible from the hit point
		Hit sceneHit = scene.intersect(new Ray(hit.position(), direction, 0.0001, Double.POSITIVE_INFINITY));
		boolean shadow = sceneHit.position() != null || sceneHit.t() != Double.POSITIVE_INFINITY;
		if (shadow) {
			return Vector.black;
		}

		// calculate the intensity of the light
		Color intensity = Vector.multiply(hit.material().albedo(hit), Vector.dotProduct(hit.normal(), direction)); // TODO: add light intensity
		return intensity;
	}
	
}
