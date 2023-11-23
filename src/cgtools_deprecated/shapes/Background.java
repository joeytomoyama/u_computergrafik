package cgtools_deprecated.shapes;

import cgtools_deprecated.Color;
import cgtools_deprecated.Hit;
import cgtools_deprecated.Ray;

public record Background(Color color) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        return new Hit(Double.POSITIVE_INFINITY, ray.pointAt(Double.POSITIVE_INFINITY), ray.direction(), color);
    }
    
}
