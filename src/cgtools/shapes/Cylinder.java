package cgtools.shapes;

import cgtools.*;
import cgtools.materials.*;

public record Cylinder(Point center, double height, double radius, Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        // top and bottom cylinder caps
        Shape top = new Disc(new Point(
            center.x(), center.y() + height / 2, center.z()),
            Vector.yAxis,
            radius,
            material
        );
        Shape bottom = new Disc(new Point(
            center.x(), center.y() - height / 2, center.z()),
            Vector.yAxis,
            radius,
            material
        );
        
        // determine the shortest cap hit if any
        Hit shortestCapHit = null;
        Hit hitTop = top.intersect(ray);
        Hit hitBottom = bottom.intersect(ray);
        if (hitTop != null && hitBottom == null) {
            shortestCapHit = hitTop;
        } else if (hitTop == null && hitBottom != null) {
            shortestCapHit = hitBottom;
        } else if (hitTop != null && hitBottom != null) {
            if (hitTop.t() < hitBottom.t()) {
                shortestCapHit = hitTop;
            } else {
                shortestCapHit = hitBottom;
            }
        }

        // determine hit point with cylinder shaft
        Direction oc = Vector.subtract(ray.origin(), center);  // vector from cylinder center to ray origin
        double a = ray.direction().x() * ray.direction().x() + ray.direction().z() * ray.direction().z();
        double b = 2 * (oc.x() * ray.direction().x() + oc.z() * ray.direction().z());
        double c = oc.x() * oc.x() + oc.z() * oc.z() - radius * radius;

        double discriminant = b * b - 4 * a * c;
        double t;
        if (discriminant < 0) {
            return shortestCapHit;
        } else if (discriminant == 0) {
            t = -b / (2 * a);
        } else {
            double t0 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            t = Math.min(t0, t1);
        }

        if (shortestCapHit != null) {
            // System.out.println("sch: " + shortestCapHit.t());
            // System.out.println("t: " + t);
            return shortestCapHit;
        }

        // if (shortestCapHit != null && shortestCapHit.t() < t) {
        //     System.out.println("here");
        //     return shortestCapHit;
        // }

        Point hitPoint = ray.pointAt(t);
        if (hitPoint == null) return shortestCapHit;

        if (hitPoint.y() < center.y() - height / 2 || hitPoint.y() > center.y() + height / 2) return null;
        Point centerAlongY = new Point(center.x(), hitPoint.y(), center.z());
        
        return (hitPoint == null) ? null : new Hit(
            t,
            ray.pointAt(t),
            // calculate normal vector
            Vector.normalize(Vector.subtract(hitPoint, centerAlongY)),
            material
            // new DiffuseMat(Vector.red)
        );
    }
    
}
