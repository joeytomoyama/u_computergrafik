package cgg.a10.materials;

import cgg.a10.Hit;
import cgtools.Color;
import cgtools.Ray;

public interface Material {
    
	public Color emission(Hit hit);
	public Color albedo(Hit hit);
    public Ray scatteredRay(Ray ray, Hit hit);
}
