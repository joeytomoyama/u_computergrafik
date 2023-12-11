package cgtools.shapes;

import cgtools.*;
import cgtools.materials.Material;

public record Cylinder(Point center, double height, double radius, Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
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

        Hit hitTop = top.intersect(ray);
        // return hitTop;
        Hit hitBottom = bottom.intersect(ray);

        double a = ray.direction().x() * ray.direction().x() + ray.direction().z() * ray.direction().z();
        double b = 2 * (ray.origin().x() * ray.direction().x() + ray.origin().z() * ray.direction().z());
        double c = ray.origin().x() * ray.origin().x() + ray.origin().z() * ray.origin().z() - radius * radius;

        double discriminant = b * b - 4 * a * c;
        double t;
        if (discriminant < 0) {
            return null;
        } else if (discriminant == 0) {
            t = -b / (2 * a);
        } else {
            double t0 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            t = Math.min(t0, t1);
        }

        if (hitTop != null && hitTop.t() < t) {
            System.out.println("hallo");
            return hitTop;
        }
        if (hitBottom != null && hitBottom.t() < t) return hitBottom;

        if (!ray.isValid(t)) return null;

        Point hitPoint = ray.pointAt(t);
        if (hitPoint.y() < center.y() - height / 2 || hitPoint.y() > center.y() + height / 2) return null;
        Point centery = new Point(center.x(), hitPoint.y(), center.z());
        return (hitPoint == null) ? null : new Hit(
            t,
            hitPoint,
            Vector.normalize(Vector.subtract(hitPoint, centery)),
            this.material
        );
    }
    
}
