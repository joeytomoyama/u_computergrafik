/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a03;

import java.util.List;

import cgtools_deprecated.*;
import cgtools_deprecated.shapes.Sphere;

// Represents the contents of an image. Provides the same color for all pixels.
record RaytraceColor(Camera camera, List<Sphere> spheres, int width, int height) implements Sampler {
  
	// Returns the color for the given position.
	public Color getColor(double x, double y) {
		Ray ray = camera.generateRay(x, y);
		Hit shortestHit = null;
		for (Sphere sphere : spheres) {
			Hit hit = sphere.intersect(ray);
			if (hit == null) continue;

			if (shortestHit == null) {
				shortestHit = hit;
			} else {
				if (hit.t() < shortestHit.t()) shortestHit = hit;
			}
		}
		return shortestHit == null ?
			new Color(0, 0, 0) :
			Vector.shade(shortestHit.normal(), shortestHit.color());
	}
}
