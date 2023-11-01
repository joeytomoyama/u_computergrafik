package cgtools.shapes;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Hit;
import cgtools.Point;
import cgtools.Ray;
import cgtools.Vector;

public record Sphere(Point center, float radius, Color color) {
    
    // public Hit intersect(Ray ray) {
    //     double a = Vector.dotProduct(ray.direction(), ray.direction());
    //     double b = 2 * Vector.dotProduct(ray.origin(), ray.direction());
    //     double c = Vector.dotProduct(ray.origin(), ray.origin()) - this.radius * this.radius;

    //     double diskriminante = b * b - 4 * a * c;
    //     double t;
    //     if (diskriminante < 0) {
    //         return null;
    //     } else if (diskriminante == 0) {
    //         t = -b / 2 * a;
    //     } else {
    //         double t1 = (-b + Math.sqrt(diskriminante)) / (2 * a);
    //         double t2 = (-b - Math.sqrt(diskriminante)) / (2 * a);
    //         t = Math.min(t1, t2);
    //     }
    //     return new Hit(t, ray.pointAt(t), Vector.divide(Vector.subtract(ray.pointAt(t), center), radius), color);
    // }

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
        Point hitPoint = ray.pointAt(t);
        return (hitPoint == null) ? null : new Hit(t, hitPoint, Vector.divide(Vector.subtract(hitPoint, this.center), this.radius), this.color);
    }
}
