package cgg.a05;

import cgtools.Point;
import cgtools.Direction;
import cgtools.Material;

/**
 * Holds information about a Hit, for example: Ray hitting Sphere.
 */
public record Hit(double t, Point position, Direction normal, Material material) {}
