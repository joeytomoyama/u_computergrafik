package cgg.a11.shapes;

import cgg.a11.Hit;
import cgg.a11.materials.Material;
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
