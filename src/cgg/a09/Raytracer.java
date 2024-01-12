package cgg.a09;

import cgg.a09.materials.Material;
import cgg.a09.shapes.Group;
import cgtools.Camera;
// import cgtools.*;
import cgtools.Color;
import cgtools.Ray;
import cgtools.Sampler;
import cgtools.Vector;

public record Raytracer(Camera camera, Group group) implements Sampler {
    
    public Color getColor(double x, double y) {
        Ray ray = camera.generateRay(x, y);
        
        return radiance(ray, group, 4);
    }

    public Color radiance(Ray ray, Group group, int depth) {
        // check for maximum recursion depth
        if (depth == 0) return new Color(0, 0, 0);

        // intersect ray with scene
        Hit hit = group.intersect(ray);
        if (hit == null) {
            return new Color(0, 0, 0); // return black or a background color
        }

        // query material at hit point
        Material material = hit.material();

        // combine emission and reflection
        Ray nextRay = material.scatteredRay(ray, hit);
        
        if (nextRay == null) return material.emission(hit);

        return Vector.multiply(
            Vector.add(material.albedo(hit), material.emission(hit)),
            radiance(nextRay, group, --depth)
        );
    }
}
