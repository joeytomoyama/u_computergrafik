package cgtools.shapes;

import java.util.List;

import cgtools.Hit;
import cgtools.Matrix;
import cgtools.Ray;
import cgtools.Transformation;

public class Group implements Shape {

    private List<Shape> shapes;
    private Transformation transformation;

    public Group(List<Shape> shapes) {
        this.shapes = shapes;
        this.transformation = new Transformation(Matrix.identity);
    }

    public Group(List<Shape> shapes, Transformation transformation) {
        this.shapes = shapes;
        this.transformation = transformation;
    }

    @Override
    public Hit intersect(Ray ray) {
        ray = transformation.transformRay(ray);
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
        return shortestHit != null ? transformation.transformHit(shortestHit) : null;
    }
}