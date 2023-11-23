/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import cgtools_deprecated.*;

// Represents the contents of an image. Provides the same color for all pixels.
record CircleColor(Color color, int width, int height, float radius) implements Sampler {
  
  // Returns the color for the given position.
  public Color getColor(double x, double y) {
    Point center = new Point(this.width / 2, this.height / 2, 0);
    Point requested = new Point(x, y, 0);

    var distance = Math.sqrt(
      (requested.x() - center.x()) * (requested.x() - center.x())
    + (requested.y() - center.y()) * (requested.y() - center.y())
    );

    return (distance < this.radius) ? this.color : new Color(0, 0, 0);
  }
}

// See
// https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Record.html
// for details on the record data type.
