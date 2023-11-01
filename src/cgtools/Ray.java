package cgtools;

public record Ray(Point origin, Direction direction, double min, double max) {
    
    /**
     * Returns the point on the ray at a value t.
     * @param t point on ray.
     * @return Point on ray.
     */
    public Point pointAt(double t) {
        if (!isValid(t)) return null;
        return Vector.add(origin, Vector.multiply(direction, t));
    }

    /**
     * Is parameter t inside Tmin and Tmax?
     * @param t
     * @return
     */
    public boolean isValid(double t) {
        return (min < t && t < max) ? true : false;
    }
}
