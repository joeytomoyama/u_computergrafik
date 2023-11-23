/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import java.util.ArrayList;
import java.util.List;

import cgtools_deprecated.*;

record PolkaColor(Color color, int width, int height, int amount, float radius, float margin) implements Sampler {
  
  public Color getColor(double x, double y) {
    float diameter = (radius + margin) * 2;

    int colAmount = (int) Math.floor(width / diameter);
    int rowAmount = amount / colAmount + 1;

    List<Point> pointList = new ArrayList<>();
    int count = 0;
    for (int i = 1; i <= rowAmount; i++) {
      for (int j = 1; j <= colAmount; j++) {
        if (count >= amount) break;
        pointList.add(new Point(j * diameter - radius, i * diameter - radius, 0));
        count++;
      }
    }

    for (Point point : pointList) {
      if (point.distance2d(new Point(x, y, 0)) < radius - margin) {
        return color;
      }
    }

    return new Color(0, 0, 0);
  }
}

// See
// https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Record.html
// for details on the record data type.
