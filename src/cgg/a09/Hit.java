package cgg.a09;

import cgg.a09.materials.Material;
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
