package cgtools_deprecated.shapes;

import java.util.List;

import cgtools_deprecated.Hit;
import cgtools_deprecated.Ray;

public record Group(List<Shape> shapes) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        Hit shortestHit = null;
        for (Shape shape : shapes) {
            Hit hit = shape.intersect(ray);
            if (hit == null) continue;
            if (shortestHit == null) {
                shortestHit = hit;
            } else {
                if (hit.t() < shortestHit.t()) {
                    shortestHit = hit;
                }
            }
        }
        return shortestHit;
    }
    
}
