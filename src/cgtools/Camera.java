package cgtools;

import cgg.a12.Animatable;

public class Camera implements Animatable {

    private double fov;
    private int width;
    private int height;
    private Matrix transformation;
    private Point position = new Point(0, 0, 0);

    public Camera(double fov, int width, int height) {
        this.fov = fov;
        this.width = width;
        this.height = height;
        this.transformation = Matrix.identity;
    }
    
    public Camera(double fov, int width, int height, Matrix transformation) {
        this.fov = fov;
        this.width = width;
        this.height = height;
        this.transformation = transformation;
    }

    /**
     * generates Ray towards specified direction.
     * @param x
     * @param y
     * @return
     */
    public Ray generateRay(double x, double y) {
        Direction direction = new Direction(
            x - this.width / 2,
            -1 * (y - this.height / 2),
            -1 * ((this.width / 2) / Math.tan(this.fov / 2))
        );
        direction = Vector.normalize(direction);
        
        return new Ray(Matrix.multiply(transformation, position), Matrix.multiply(transformation, direction), 0.001, Double.POSITIVE_INFINITY);
    }

	@Override
	public void setTransformation(Matrix m) {
		this.transformation = m;
	}
}
