package cgtools.shapes;

import cgtools.Hit;
import cgtools.Ray;

public interface Shape {
    
    public Hit intersect(Ray ray);
}
