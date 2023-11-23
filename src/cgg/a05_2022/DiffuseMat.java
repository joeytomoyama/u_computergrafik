package cgg.a05_2022;

import cgtools_deprecated.Color;
import cgtools_deprecated.Direction;
import cgtools_deprecated.Material;
import cgtools_deprecated.Random;
import cgtools_deprecated.Ray;
import cgtools_deprecated.Vector;

public record DiffuseMat(Color albedo) implements Material {

    @Override
    public Color emission() {
        return Vector.black;
    }

    @Override
    public Color albedo() {
        return albedo;
    }

    @Override
    public Ray scatteredRay(Ray ray, Hit hit) {
        Direction randomDir = new Direction(Random.random(), Random.random(), Random.random());
        Ray nextRay = new Ray(hit.position(), Vector.normalize(Vector.add(hit.normal(), randomDir)), 0.0001, ray.tmax());
        return nextRay;
    }
    
}
