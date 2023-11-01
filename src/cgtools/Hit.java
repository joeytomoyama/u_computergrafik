package cgtools;

/**
 * Holds information about a Hit, for example: Ray hitting Sphere.
 */
public record Hit(double t, Point origin, Direction normal, Color color) {
    
}
