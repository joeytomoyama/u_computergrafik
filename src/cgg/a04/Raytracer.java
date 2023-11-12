package cgg.a04;

import cgtools.Camera;
import cgtools.Color;
import cgtools.Hit;
import cgtools.Ray;
import cgtools.Sampler;
import cgtools.Vector;
import cgtools.shapes.Group;

public record Raytracer(Camera camera, Group group) implements Sampler {
    
    public Color getColor(double x, double y) {
        Ray ray = camera.generateRay(x, y);
        Hit hit = group.intersect(ray);
        
        return hit == null ?
			new Color(0, 0, 0) :
			Vector.shade(hit.normal(), hit.color());
    }
}
