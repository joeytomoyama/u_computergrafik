package cgg.a05;

import cgtools.*;
import cgtools.materials.Material;
import cgtools.shapes.*;

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
        if (nextRay != null) {
            return Vector.multiply(
                Vector.add(material.albedo(), material.emission()),
                radiance(nextRay, group, --depth)
            );
        } else {
            return material.emission();
        }
    }
}
