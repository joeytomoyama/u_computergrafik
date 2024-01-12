package cgg.a10.shapes;

import cgg.a10.Hit;
import cgtools.Ray;

public interface Shape {
    
    public Hit intersect(Ray ray);
}
