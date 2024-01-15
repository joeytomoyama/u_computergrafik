package cgg.a11;

import cgg.a11.materials.Material;
import cgg.a11.materials.MaterialDiffuse;
import cgg.a11.shapes.Group;
import cgtools.Camera;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Ray;
import cgtools.Sampler;
import cgtools.Vector;

public record Raytracer(Camera camera, World world) implements Sampler {
    
    public Color getColor(double x, double y) {
        Ray ray = camera.generateRay(x, y);
        
        return radiance(ray, world, 10);
    }

    public Color radiance(Ray ray, World world, int depth) {
        // check for maximum recursion depth
        if (depth == 0) return Vector.black;

        // intersect ray with scene
        Hit hit = world.group().intersect(ray);
        if (hit == null) {
            return Vector.black; // return black or a background color
        }

        // query material at hit point
        Material material = hit.material();

        // combine emission and reflection
        Ray nextRay = material.scatteredRay(ray, hit);
        if (nextRay == null) return material.emission(hit);

		Light light = new LightDirection(new Direction(1, 1, 0)); // TODO: change to world.lights()
		// Light light = new LightPoint(new Point(1, 5, 1), new Color(5, 5, 5)); // TODO: change to world.lights()

		// Color lightSum = Vector.black;
		// world.lights().forEach(light -> {
		// 	Vector.add(lightSum, light.incomingIntensity(hit, world.group()));
		// });

		// calculate albedo (mirrors and glass are unaffected by artificial lights)
		Color albedo = (hit.material().getClass() == MaterialDiffuse.class) ?
			Vector.add(light.incomingIntensity(hit, world.group()), material.albedo(hit)) :
			// Vector.add(lightSum, material.albedo(hit)) :
			material.albedo(hit);

        return Vector.multiply(
            Vector.add(albedo, material.emission(hit)),
            radiance(nextRay, world, depth - 1)
        );
		// Color reflectedColor = Vector.multiply(albedo, radiance(nextRay, world.group(), depth - 1));
    	// return Vector.add(material.emission(hit), reflectedColor);
    }
}
