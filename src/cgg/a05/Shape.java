package cgg.a05;

import cgtools.Ray;

public interface Shape {
    
    public Hit intersect(Ray ray);
}
