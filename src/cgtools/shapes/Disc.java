package cgtools.shapes;

import cgtools.*;
import cgtools.materials.Material;

/**
 * A disc is a circle in 3d space. It is defined by a center point, a normal vector and a radius.
 * Pass 0 as radius to make it an infinite plane.
 */
public record Disc(Point anker, Direction normal, double radius, Material material) implements Shape {

    @Override
    /**
     * Intersect a ray with the disc.
     * @param ray Ray to intersect with
     * @return Hit or null if no intersection
     */
    public Hit intersect(Ray ray) {
        double dividend = Vector.dotProduct(
            Vector.subtract(anker, ray.origin()),
            normal
        );
        double divisor = Vector.dotProduct(ray.direction(), normal);
        if (divisor == 0) return null; // if the ray is perpendicular to the disc's normal
        double t = dividend / divisor;

        // make sure Disc is a disc and not an infinite plane
        Point hitPosition = ray.pointAt(t);
        if (hitPosition == null) return null;
        if (radius != 0 && Vector.length(Vector.subtract(anker, hitPosition)) > radius) return null;

        return new Hit(t, hitPosition, normal, material);
    }
    
}
