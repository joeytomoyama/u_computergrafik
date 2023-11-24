package cgtools.materials;

import cgtools.Color;
import cgtools.Hit;
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
