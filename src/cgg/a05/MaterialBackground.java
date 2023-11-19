package cgg.a05;

import cgtools.Color;
import cgtools.Material;
import cgtools.Ray;
import cgtools.Vector;

public record MaterialBackground(Color emission) implements Material {

    @Override
    public Color emission() {
        return emission;
    }

    @Override
    public Color albedo() {
        return Vector.black;
    }

    @Override
    public Ray scatteredRay(Ray ray, Hit hit) {
        return null;
    }
}
