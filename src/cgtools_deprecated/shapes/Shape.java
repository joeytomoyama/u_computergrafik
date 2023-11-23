package cgtools_deprecated.shapes;

import cgtools_deprecated.Hit;
import cgtools_deprecated.Ray;

public interface Shape {
    
    public Hit intersect(Ray ray);
}
