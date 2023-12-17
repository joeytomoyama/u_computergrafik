package cgtools;

public class Transformation {

    private Matrix toWorld;         // M
    private Matrix fromWorld;       // M^-1
    private Matrix toWorldNormal;   // (M^-1)^T

    public Transformation(Matrix toWorld) {
        this.toWorld = toWorld;
        this.fromWorld = Matrix.invert(toWorld);
        this.toWorldNormal = Matrix.transpose(this.fromWorld);
    }

    /**
     * transforms a ray from world to local coordinates.
     * @param ray world ray
     * @return local ray
     */
    public Ray transformRay(Ray ray) {
        if (ray == null) return null;
        return new Ray(
            Matrix.multiply(fromWorld, ray.origin()),
            Matrix.multiply(fromWorld, ray.direction()),
            ray.tmin(),
            ray.tmax()
        );
    }

    /**
     * transforms a hit from local to world coordinates.
     * @param hit hit local
     * @return world hit
     */
    public Hit transformHit(Hit hit) {
        if (hit == null) return null;
        if (hit.position() == null) return hit; // edge case: background
        return new Hit(
            hit.t(),
            Matrix.multiply(toWorld, hit.position()),
            Matrix.multiply(toWorldNormal,
            hit.normal()),
            hit.material()
        );
    }
}
