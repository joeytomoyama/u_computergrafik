package cgg.a05_2022;

import cgtools_deprecated.Color;
import cgtools_deprecated.Direction;
import cgtools_deprecated.Material;
import cgtools_deprecated.Point;
import cgtools_deprecated.Ray;
import cgtools_deprecated.Vector;

public record Sphere(Point center, double radius, Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        Direction oc = Vector.subtract(ray.origin(), this.center);  // vector from sphere center to ray origin
        
        double a = Vector.dotProduct(ray.direction(), ray.direction());
        double b = 2.0 * Vector.dotProduct(oc, ray.direction());
        double c = Vector.dotProduct(oc, oc) - this.radius * this.radius;
    
        double discriminant = b * b - 4 * a * c;
        double t;
        if (discriminant < 0) {
            return null;
        } else if (discriminant == 0) {
            t = -b / (2 * a);
        } else {
            double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            t = Math.min(t1, t2);
        }

        if (!ray.isValid(t)) {
            return null;
        }

        Point hitPoint = ray.pointAt(t);
        return (hitPoint == null) ? null : new Hit(
            t,
            hitPoint,
            Vector.normalize(Vector.divide(Vector.subtract(hitPoint, this.center), this.radius)),
            this.material
        );
    }
}
