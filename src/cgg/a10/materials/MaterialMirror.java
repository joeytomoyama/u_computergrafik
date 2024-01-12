package cgg.a10.materials;

import cgg.a10.Hit;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Ray;
import cgtools.Vector;

public record MaterialMirror() implements Material {

	@Override
	public Color emission(Hit hit) {
		return Vector.black;
	}

	@Override
	public Color albedo(Hit hit) {
		return Vector.white;
	}

	@Override
	public Ray scatteredRay(Ray ray, Hit hit) {
		Direction reflected = Vector.subtract(ray.direction(), Vector.multiply(2 * Vector.dotProduct(ray.direction(), hit.normal()), hit.normal()));
		return new Ray(hit.position(), reflected, 0.0001, Double.POSITIVE_INFINITY);
	}
	
}
