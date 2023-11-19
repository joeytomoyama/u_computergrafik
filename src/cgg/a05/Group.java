package cgg.a05;

import java.util.List;
import cgtools.Ray;

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
