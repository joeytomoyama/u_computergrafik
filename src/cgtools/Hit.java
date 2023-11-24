package cgtools;

import cgtools.materials.Material;

/**
 * Holds information about a Hit, for example: Ray hitting Sphere.
 */
public record Hit(double t, Point position, Direction normal, Material material) { //Material material) {
    
}
