package cgg.a10.shapes;

import cgg.a10.Hit;
import cgg.a10.materials.Material;
import cgtools.Point;
import cgtools.Ray;
import cgtools.Vector;
import cgtools.Direction;

public record Plane(Point center, Direction direction, double width, double height, Material material) implements Shape {

	@Override
	public Hit intersect(Ray ray) {
		double t = (Vector.dotProduct(Vector.subtract(center, ray.origin()), direction)) / (Vector.dotProduct(ray.direction(), direction));
		Point hitPosition = ray.pointAt(t);
		if (hitPosition == null) return null;
		if (Math.abs(hitPosition.x()) > width / 2 || Math.abs(hitPosition.y()) > height / 2) return null;
		return new Hit(
			t,
			hitPosition,
			direction,
			hitPosition.x() / width + 0.5,
			hitPosition.y() / height + 0.5,
			material
		);
	}
	
}
