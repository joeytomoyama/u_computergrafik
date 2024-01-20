package cgg.a12.materials;

import cgg.a12.Hit;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Ray;
import cgtools.Sampler;
import cgtools.Vector;

public record MaterialDiffuse(Sampler albedo) implements Material {

    @Override
    public Color emission(Hit hit) {
		return Vector.black;
    }

	@Override
	public Color albedo(Hit hit) {
		return albedo.getColor(hit.u(), hit.v());
	}

	@Override
	public Ray scatteredRay(Ray ray, Hit hit) {
		Direction randomDir = new Direction(randomize(), randomize(), randomize());
		Ray nextRay = new Ray(hit.position(), Vector.normalize(Vector.add(hit.normal(), randomDir)), 0.0001, ray.tmax());
		return nextRay;
	}

    private double randomize() {
        int offset = 1;
        if (Random.random() > 0.5) offset = -1;
        return offset * Random.random();
    }
    
}
