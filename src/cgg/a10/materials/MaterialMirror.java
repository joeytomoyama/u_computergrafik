package cgg.a10.materials;

import cgg.a10.Hit;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Ray;
import cgtools.Vector;

public record MaterialMirror(double diffusion) implements Material {

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
		
		// add diffusion
		if (diffusion > 0) {
			Direction randomDir = new Direction(Random.random(), Random.random(), Random.random());
			reflected = Vector.add(reflected, Vector.multiply(diffusion, randomDir));
		}
		return new Ray(hit.position(), reflected, 0.0001, Double.POSITIVE_INFINITY);
	}
	
}
