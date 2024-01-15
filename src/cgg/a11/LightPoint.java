package cgg.a11;

import cgg.a11.shapes.Shape;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Ray;
import cgtools.Vector;

public record LightPoint(Point position, Color intensity) implements Light {

	@Override
	public Color incomingIntensity(Hit hit, Shape scene) {
		Direction direction = Vector.subtract(position, hit.position());
		double directionLength = Vector.length(direction);

		// check if the light is visible from the hit point
		Hit sceneHit = scene.intersect(new Ray(hit.position(), direction, 0.0001, directionLength));
		boolean shadow = sceneHit.position() != null || sceneHit.t() != Double.POSITIVE_INFINITY;
		if (shadow) {
			return Vector.black;
		}

		// Color color = Vector.multiply(
		// 	hit.material().albedo(hit),
			return Vector.multiply(Vector.divide(intensity, directionLength * directionLength), Vector.dotProduct(Vector.normalize(direction), hit.normal()));
		// );
		// return Vector.multiply(color, Vector.dotProduct(Vector.normalize(direction), hit.normal()));
		// return color;


		// Direction direction = Vector.subtract(position, hit.position());
		// double directionLength = Vector.length(direction);
		// Direction normalizedDirection = Vector.normalize(direction);

		// // check if the light is visible from the hit point
		// Hit sceneHit = scene.intersect(new Ray(hit.position(), normalizedDirection, 0.0001, directionLength));
		// boolean shadow = sceneHit != null && sceneHit.t() < directionLength;
		// if (shadow) {
		// 	return Vector.black;
		// }

		// double dotProduct = Vector.dotProduct(normalizedDirection, hit.normal());
		// if (dotProduct < 0) dotProduct = 0;

		// Color color = Vector.multiply(
		// 	hit.material().albedo(hit),
		// 	Vector.multiply(Vector.divide(intensity, directionLength * directionLength), dotProduct)
		// );
		// return color;
	}
	
}
