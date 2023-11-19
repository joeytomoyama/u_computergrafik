package cgg.a05;

import cgtools.Camera;
import cgtools.Color;
import cgtools.Material;
import cgtools.Ray;
import cgtools.Sampler;
import cgtools.Vector;

public record Raytracer(Camera camera, Group group) implements Sampler {
    
    public Color getColor(double x, double y) {
        Ray ray = camera.generateRay(x, y);
        Hit hit = group.intersect(ray);
        
        return hit == null ?
			new Color(0, 0, 0) :
			// Vector.shade(hit.normal(), hit.color());
            radiance(ray, group, 2);
    }

    // public Color raytrace(double x, double y, int depth) {
    //     Ray ray = camera.generateRay(x, y);
    //     return null;
    // }

    public Color radiance(Ray ray, Group group, int depth) {
        // check for maximum recursion depth
        if (depth == 0) return new Color(0, 0, 0);
        // intersect ray with scene
        Hit hit = group.intersect(ray);
        // query material at hit point
        Material material = hit.material();
        // combine emission and reflection
        if (material.scatteredRay(ray, hit) != null) {
            return Vector.multiply(
                Vector.add(material.albedo(), material.emission()),
                radiance(ray, group, depth--)
            );
        } else {
            return material.emission();
        }
    }
}
