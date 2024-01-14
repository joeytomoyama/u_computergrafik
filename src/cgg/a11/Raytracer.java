package cgg.a11;

import cgg.a11.materials.Material;
import cgg.a11.shapes.Group;
import cgtools.Camera;
// import cgtools.*;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Ray;
import cgtools.Sampler;
import cgtools.Vector;

public record Raytracer(Camera camera, Group group) implements Sampler {
    
    public Color getColor(double x, double y) {
        Ray ray = camera.generateRay(x, y);
        
        return radiance(ray, group, 10);
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

		Light light = new LightDirection(new Direction(1, 1, 0));

		Color albedo = light.incomingIntensity(hit, group);
		Color albedo2 = Vector.multiply(hit.material().albedo(hit), 0.2); // ???? - funktioniert aber.

        return Vector.multiply(
            Vector.add(albedo, material.emission(hit), albedo2),
            radiance(nextRay, group, --depth)
        );
		// Color reflectedColor = Vector.multiply(material.albedo(hit), radiance(nextRay, group, depth - 1));
    	// return Vector.add(material.emission(hit), reflectedColor);
    }
}
