package cgtools_deprecated;

import cgg.a05_2022.Hit;

public interface Material {
    
    public Color emission();
    public Color albedo();
    public Ray scatteredRay(Ray ray, Hit hit);
}
