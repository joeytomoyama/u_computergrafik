package cgg.a12.materials;

import cgg.a12.Hit;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Ray;
import cgtools.Vector;

public record MaterialGlass() implements Material {

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
		Direction normal = hit.normal();
		double n1 = 1.0;
		double n2 = 1.3;

		// check if ray is inside the object
		if (Vector.dotProduct(ray.direction(), hit.normal()) > 0) {
			normal = Vector.multiply(-1, hit.normal());
			double temp = n1;
			n1 = n2;
			n2 = temp;
		}

		// there is no refraction, total reflection
		if (!validRecfraction(ray.direction(), normal, n1, n2)) {
			return new Ray(hit.position(), calculateReflectionAngle(ray.direction(), normal), 0.0001, Double.POSITIVE_INFINITY);
		}

		// there is refraction
		if (Random.random() > schlickApproximation(ray.direction(), normal, n1, n2)) {
			Direction refracted = calculateRefractionAngle(ray.direction(), normal, n1, n2);
			return new Ray(hit.position(), refracted, 0.0001, Double.POSITIVE_INFINITY);
		} else {
			return new Ray(hit.position(), calculateReflectionAngle(ray.direction(), normal), 0.0001, Double.POSITIVE_INFINITY);
		}
	}

	public Direction calculateReflectionAngle(Direction direction, Direction normal) {
		return Vector.subtract(direction, Vector.multiply(2 * Vector.dotProduct(direction, normal), normal));
	}

	public Direction calculateRefractionAngle(Direction direction, Direction normal, double n1, double n2) {
		double r = n1 / n2;
		double cosTheta = Vector.dotProduct(direction, Vector.multiply(normal, -1));

		return Vector.add(
			Vector.multiply(r, direction),
			Vector.multiply(r * cosTheta - Math.sqrt(1 - r * r * (1 - cosTheta * cosTheta)), normal)
		);
	}

	private boolean validRecfraction(Direction direction, Direction normal, double n1, double n2) {
		double r = n1 / n2;
		double cosTheta = Vector.dotProduct(direction, Vector.multiply(normal, -1));
		return 1 - r * r * (1 - cosTheta * cosTheta) >= 0;
	}

	public double schlickApproximation(Direction direction, Direction normal, double n1, double n2) {
		double r0 = Math.pow((n1 - n2) / (n1 + n2), 2);
		return r0 + (1 - r0) * Math.pow(1 + Vector.dotProduct(normal, direction), 5);
	}
}
