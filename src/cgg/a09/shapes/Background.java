package cgg.a09.shapes;

import cgg.a09.Hit;
import cgg.a09.materials.Material;
import cgtools.Ray;

public record Background(Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        return new Hit(
			Double.POSITIVE_INFINITY,
			ray.pointAt(Double.POSITIVE_INFINITY),
			ray.direction(),
			0.0,
			0.0,
			material
		);
    }
    
}
