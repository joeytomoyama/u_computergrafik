package cgtools.shapes;

import cgtools.Hit;
import cgtools.Ray;
import cgtools.Vector;
import cgtools.Point;
import cgtools.Color;
import cgtools.Direction;

public record Disc3d(Point anker, Direction normal, double radius, Color color) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        double dividend = Vector.dotProduct(
            Vector.subtract(ray.origin(), anker), 
            normal
        );
        double divisor = Vector.dotProduct(ray.direction(), normal);
        if (divisor == 0) return null; // ray and disc3d are parallel to each other.
        double t = dividend / divisor;

        // make sure dist3d is a disc and not an infinite plane
        Point hitPosition = ray.pointAt(t);
        if (hitPosition == null) return null;
        if (radius != 0 && Vector.length(Vector.subtract(anker, hitPosition)) > radius)
            return null;

        return new Hit(t, hitPosition, normal, color);
    }
    
}
