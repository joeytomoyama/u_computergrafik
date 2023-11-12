package cgtools.shapes;

import cgtools.Color;
import cgtools.Hit;
import cgtools.Ray;

public record Background(Color color) implements Shape {

    // private final Color color = new Color(0, 0, 0);

    @Override
    public Hit intersect(Ray ray) {
        return new Hit(Double.POSITIVE_INFINITY, ray.pointAt(Double.POSITIVE_INFINITY), ray.direction(), color);
    }
    
}
