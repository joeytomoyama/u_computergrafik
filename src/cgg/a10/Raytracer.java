package cgg.a10;

import cgg.a10.materials.Material;
import cgg.a10.shapes.Group;
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
        if (depth == 0) return Vector.black;

        // intersect ray with scene
        Hit hit = group.intersect(ray);
        if (hit == null) {
            return Vector.black; // return black or a background color
        }

        // query material at hit point
        Material material = hit.material();

        // combine emission and reflection
        Ray nextRay = material.scatteredRay(ray, hit);
        
        if (nextRay == null) return material.emission(hit);

        // return Vector.multiply(
        //     Vector.add(material.albedo(hit), material.emission(hit)),
        //     radiance(nextRay, group, --depth)
        // );
		Color reflectedColor = Vector.multiply(material.albedo(hit), radiance(nextRay, group, depth - 1));
    	return Vector.add(material.emission(hit), reflectedColor);
    }
}
