package cgtools;

import cgg.a05.Hit;

public interface Material {
    
    public Color emission();
    public Color albedo();
    public Ray scatteredRay(Ray ray, Hit hit);
}
