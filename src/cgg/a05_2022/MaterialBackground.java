package cgg.a05_2022;

import cgtools_deprecated.Color;
import cgtools_deprecated.Material;
import cgtools_deprecated.Ray;
import cgtools_deprecated.Vector;

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
