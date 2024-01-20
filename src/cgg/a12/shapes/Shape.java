package cgg.a12.shapes;

import cgg.a12.Hit;
import cgtools.Ray;

public interface Shape {
    
    public Hit intersect(Ray ray);
}
