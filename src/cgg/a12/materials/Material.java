package cgg.a12.materials;

import cgg.a12.Hit;
import cgtools.Color;
import cgtools.Ray;

public interface Material {
    
	public Color emission(Hit hit);
	public Color albedo(Hit hit);
    public Ray scatteredRay(Ray ray, Hit hit);
}
