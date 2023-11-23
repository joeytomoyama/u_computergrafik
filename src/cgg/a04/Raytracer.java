package cgg.a04;

import cgtools_deprecated.Camera;
import cgtools_deprecated.Color;
import cgtools_deprecated.Hit;
import cgtools_deprecated.Ray;
import cgtools_deprecated.Sampler;
import cgtools_deprecated.Vector;
import cgtools_deprecated.shapes.Group;

public record Raytracer(Camera camera, Group group) implements Sampler {
    
    public Color getColor(double x, double y) {
        Ray ray = camera.generateRay(x, y);
        Hit hit = group.intersect(ray);
        
        return hit == null ?
			new Color(0, 0, 0) :
			Vector.shade(hit.normal(), hit.color());
    }
}
