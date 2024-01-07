package cgg.a10;

import cgtools.Color;
import cgtools.Point;
import cgtools.Sampler;
import cgtools.Vector;

// public record PolkaTexture(int n, Color fgColor, Color bgColor, double radius) implements Sampler {

// 	// @Override
// 	// public Color getColor(double u, double v) {
// 	// 	int ui = (int)((u % 1) * n);
// 	// 	int vi = (int)((v % 1) * n);
// 	// 	if ((ui + vi) % 2 == 0) {
// 	// 		// return new Color(1, 1, 1);
// 	// 		if (Vector.length(Vector.subtract(new Point(u, v, 0), new Point(ui, vi, 0))) < radius) return fgColor;
// 	// 	} else {
// 	// 		// return new Color(0, 0, 0);
// 	// 		if (Vector.length(Vector.subtract(new Point(u, v, 0), new Point(ui, vi, 0))) < radius) return fgColor;
// 	// 	}
// 	// 	return bgColor;
// 	// }
	
// 	@Override
// 	public Color getColor(double u, double v) {
// 		// Convert u, v to grid coordinates
// 		double ui = (u % 1) * n;
// 		double vi = (v % 1) * n;
	
// 		// Calculate the center of the nearest cell
// 		double centerX = Math.floor(ui) + 0.5;
// 		double centerY = Math.floor(vi) + 0.5;
	
// 		// Check if the point is within the radius of the dot's center
// 		if (Vector.length(Vector.subtract(new Point(u, v, 0), new Point(centerX, centerY, 0))) < radius) {
// 			return fgColor;
// 		}
	
// 		return bgColor;
// 	}
// }

public record PolkaTexture(Color fgColor, Color bgColor, double radius) implements Sampler {

    @Override
    public Color getColor(double u, double v) {
        // Calculate position in the pattern space
        double patternU = u % (2 * radius);
        double patternV = v % (2 * radius);

        // Calculate the distance from the center of the nearest dot
        double distanceFromCenter = Vector.length(Vector.subtract(new Point(patternU, patternV, 0), new Point(radius, radius, 0)));

        // Check if the point is within the radius of the dot's center
        if (distanceFromCenter < radius) {
            return fgColor;
        }

        return bgColor;
    }
}
