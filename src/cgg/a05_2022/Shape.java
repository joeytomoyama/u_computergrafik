package cgg.a05_2022;

import cgtools_deprecated.Ray;

public interface Shape {
    
    public Hit intersect(Ray ray);
}
