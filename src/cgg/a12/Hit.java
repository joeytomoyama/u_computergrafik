package cgg.a12;

import cgg.a12.materials.Material;
import cgtools.Direction;
import cgtools.Point;

/**
 * Holds information about a Hit, for example: Ray hitting Sphere.
 */
public record Hit(
	double t,
	Point position,
	Direction normal,
	double u,
	double v,
	Material material
) {}
