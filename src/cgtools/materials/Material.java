package cgtools.materials;

import cgtools.Color;
import cgtools.Hit;
import cgtools.Ray;

public interface Material {
    
    public Color emission();
    public Color albedo();
    public Ray scatteredRay(Ray ray, Hit hit);
}
