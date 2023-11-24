package cgtools.shapes;

import cgtools.Hit;
import cgtools.Ray;
import cgtools.materials.Material;

public record Background(Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        return new Hit(Double.POSITIVE_INFINITY, ray.pointAt(Double.POSITIVE_INFINITY), ray.direction(), material);
    }
    
}
