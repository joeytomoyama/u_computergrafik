package cgg.a05_2022;

import cgtools_deprecated.Direction;
import cgtools_deprecated.Material;
import cgtools_deprecated.Point;

/**
 * Holds information about a Hit, for example: Ray hitting Sphere.
 */
public record Hit(double t, Point position, Direction normal, Material material) {}
