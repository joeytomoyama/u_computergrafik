package cgg.a11.shapes;

import cgg.a11.Hit;
import cgtools.Ray;

public interface Shape {
    
    public Hit intersect(Ray ray);
}
