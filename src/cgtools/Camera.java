package cgtools;

public class Camera {

    private double fov;
    private int width;
    private int height;
    private Point position = new Point(0, 0, 0);
    
    public Camera(double fov, int width, int height) {
        this.fov = fov;
        this.width = width;
        this.height = height;
    }

    public Ray generateRay(int x, int y) {
        Direction direction = new Direction(
            x - this.width / 2,
            -1 * (y - this.height / 2),
            -1 * ((this.width / 2) / Math.tan(this.fov / 2))
        );
        direction = Vector.normalize(direction);
        return new Ray(position, direction, 0.1, 100);
    }
}
