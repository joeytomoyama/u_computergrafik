package cgtools_deprecated;

public record Ray(Point origin, Direction direction, double tmin, double tmax) {
    
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
        return (tmin < t && t < tmax) ? true : false;
    }
}
