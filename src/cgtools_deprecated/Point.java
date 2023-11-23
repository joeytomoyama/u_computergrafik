/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools_deprecated;

import static cgtools_deprecated.Vector.*;

// See
// https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Record.html
// for details on the record data type.
public record Point(double x, double y, double z) {

  public double distance2d(Point point) {
    return Math.sqrt(
      (x - point.x()) * (x - point.x())
    + (y - point.y()) * (y - point.y())
    );
  }

  @Override
  public String toString() {
    return String.format("(Pnt: %.2f %.2f %.2f)", x, y, z);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Point))
      return false;
    if (o == this)
      return true;
    Point v = (Point) o;
    return Util.isZero(length(subtract(this, v)));
  }
}
