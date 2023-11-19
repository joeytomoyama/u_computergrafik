package cgg.a05;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Material;
import cgtools.Random;
import cgtools.Ray;
import cgtools.Vector;

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
