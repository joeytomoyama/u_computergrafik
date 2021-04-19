/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;

public final class Vector {

  private Vector() {
  }

  public static Direction direction(double x, double y, double z) {
    return new Direction(x, y, z);
  }

  public static Point point(double x, double y, double z) {
    return new Point(x, y, z);
  }

  public static Color color(double r, double g, double b) {
    return new Color(r, g, b);
  }

  public static Direction add(Direction a, Direction b, Direction... vs) {
    Direction r = direction(a.x + b.x, a.y + b.y, a.z + b.z);
    for (Direction v : vs) {
      r = direction(r.x + v.x, r.y + v.y, r.z + v.z);
    }
    return r;
  }

  public static Point add(Point a, Direction b, Direction... vs) {
    Point r = new Point(a.x + b.x, a.y + b.y, a.z + b.z);
    for (Direction v : vs) {
      r = point(r.x + v.x, r.y + v.y, r.z + v.z);
    }
    return r;
  }

  public static Point add(Direction a, Point b) {
    return point(a.x + b.x, a.y + b.y, a.z + b.z);
  }

  public static Direction subtract(Direction a, Direction b, Direction... vs) {
    Direction r = direction(a.x - b.x, a.y - b.y, a.z - b.z);
    for (Direction v : vs) {
      r = direction(r.x - v.x, r.y - v.y, r.z - v.z);
    }
    return r;
  }

  public static Direction subtract(Point a, Point b) {
    return direction(a.x - b.x, a.y - b.y, a.z - b.z);
  }

  public static Point subtract(Point a, Direction b) {
    return point(a.x - b.x, a.y - b.y, a.z - b.z);
  }

  public static Point interplolate(Point a, Point b, double t) {
    return point(a.x * (1 - t) + b.x * t, a.y * (1 - t) + b.y * t,
        a.z * (1 - t) + b.z * t);
  }

  public static Direction multiply(double s, Direction a) {
    return direction(s * a.x, s * a.y, s * a.z);
  }

  public static Direction multiply(Direction a, double s) {
    return direction(s * a.x, s * a.y, s * a.z);
  }

  public static Direction negate(Direction a) {
    return direction(-a.x, -a.y, -a.z);
  }

  public static Direction divide(Direction a, double s) {
    return direction(a.x / s, a.y / s, a.z / s);
  }

  public static double dotProduct(Direction a, Direction b) {
    return a.x * b.x + a.y * b.y + a.z * b.z;
  }

  public static double dotProduct(Direction a, Point b) {
    return a.x * b.x + a.y * b.y + a.z * b.z;
  }

  public static double dotProduct(Point a, Direction b) {
    return a.x * b.x + a.y * b.y + a.z * b.z;
  }

  public static double dotProduct(Point a, Point b) {
    return a.x * b.x + a.y * b.y + a.z * b.z;
  }

  public static Direction crossProduct(Direction a, Direction b) {
    return direction(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z,
        a.x * b.y - a.y * b.x);
  }

  public static double length(Direction a) {
    return Math.sqrt(a.x * a.x + a.y * a.y + a.z * a.z);
  }

  public static double squaredLength(Direction a) {
    return a.x * a.x + a.y * a.y + a.z * a.z;
  }

  public static Direction normalize(Direction a) {
    return divide(a, length(a));
  }

  @Deprecated
  public static Color asColor(Direction a) {
    return color(a.x, a.y, a.z);
  }

  @Deprecated
  public static Color asColor(Point a) {
    return color(a.x, a.y, a.z);
  }

  public static final Point zero = point(0, 0, 0);
  public static final Direction xAxis = direction(1, 0, 0);
  public static final Direction yAxis = direction(0, 1, 0);
  public static final Direction zAxis = direction(0, 0, 1);
  public static final Direction nxAxis = direction(-1, 0, 0);
  public static final Direction nyAxis = direction(0, -1, 0);
  public static final Direction nzAxis = direction(0, 0, -1);
}
