package cgg.a10.materials;

import cgg.a10.Hit;
import cgtools.Color;
import cgtools.Ray;
import cgtools.Sampler;
import cgtools.Vector;

public record MaterialBackground(Sampler emission) implements Material {

    @Override
    public Color emission(Hit hit) {
        return emission.getColor(hit.u(), hit.v());
    }

    @Override
    public Color albedo(Hit hit) {
		return Vector.black;
    }

    @Override
    public Ray scatteredRay(Ray ray, Hit hit) {
        return null;
    }
}
