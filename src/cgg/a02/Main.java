/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a02;

import static cgtools.Vector.*;
import cgg.*;

public class Main {

  public static void main(String[] args) {
    System.out.println("start");
    
    kreisScheiben();

    System.out.println("done");
  }

  public static void kreisScheiben() {
    System.out.println("test");
    var width = 480;
    var height = 270;

    // Defines the contents of the image.
    var content = new ColoredDiscs(500, 480, 270);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image.setPixel(x, y, content.getColor(x, y));
      }
    }
    
    // Writes the image to disk.
    image.write("doc/a01-test.png");
  }
}
