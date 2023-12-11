package cgtools.materials;

import cgtools.*;

public record MaterialDiffuse(Color albedo) implements Material {

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
        Direction randomDir = new Direction(randomize(), randomize(), randomize());
        Ray nextRay = new Ray(hit.position(), Vector.normalize(Vector.add(hit.normal(), randomDir)), 0.0001, ray.tmax());
        return nextRay;
    }

    public double randomize() {
        int offset = 1;
        if (Random.random() > 0.5) offset = -1;
        return offset * Random.random();
    }
    
}
