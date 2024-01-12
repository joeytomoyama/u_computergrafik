package cgg.a09.shapes;

import cgg.a09.Hit;
import cgtools.Ray;

public interface Shape {
    
    public Hit intersect(Ray ray);
}
