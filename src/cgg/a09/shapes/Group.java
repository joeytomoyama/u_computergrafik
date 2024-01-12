package cgg.a09.shapes;

import java.util.List;

import cgg.a09.Hit;
import cgg.a09.Transformation;
import cgtools.Matrix;
import cgtools.Ray;

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

    public void add(Shape shape) {
        this.shapes.add(shape);
    }

    public void addAll(List<Shape> shapes) {
        this.shapes.addAll(shapes);
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
        return transformation.transformHit(shortestHit);
    }
}