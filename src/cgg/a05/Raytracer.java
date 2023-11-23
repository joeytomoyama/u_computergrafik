package cgg.a05;

import cgtools.*;
import cgtools.shapes.*;

public record Raytracer(Camera camera, Group group) implements Sampler {
    
    public Color getColor(double x, double y) {
        Ray ray = camera.generateRay(x, y);
        Hit hit = group.intersect(ray);

        if (hit == null) {
            return new Color(0, 0, 0);
        } else {
            // radiance(ray, group, 2);
            return Vector.shade(hit.normal(), hit.color());
        }
    }

    // public Color radiance(Ray ray, Group group, int depth) {
    //     // check for maximum recursion depth
    //     if (depth == 0) return new Color(0, 0, 0);
    //     // intersect ray with scene
    //     Hit hit = group.intersect(ray);
    //     // query material at hit point
    //     Material material = hit.material();
    //     // combine emission and reflection
    //     if (material.scatteredRay(ray, hit) != null) {
    //         return Vector.multiply(
    //             Vector.add(material.albedo(), material.emission()),
    //             radiance(ray, group, depth--)
    //         );
    //     } else {
    //         return material.emission();
    //     }
    // }
}
