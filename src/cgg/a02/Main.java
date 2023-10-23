/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a02;

import static cgtools.Vector.*;
import cgg.*;

public class Main {

  public static void main(String[] args) {
    System.out.println("start");

    int width = 480;
    int height = 270;
    
    kreisScheiben(width, height);
    supersampling(width, height);

    System.out.println("done");
  }

  public static void kreisScheiben(int width, int height) {
    System.out.println("kreisScheiben");

    // Defines the contents of the image.
    var content = new ColoredDiscs(500, 480, 270);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.sample(content, 1);
    
    // Writes the image to disk.
    image.write("doc/a02-kreisScheiben.png");
  }

  public static void supersampling(int width, int height) {
    System.out.println("supersampling");

    // Defines the contents of the image.
    var content = new ColoredDiscs(500, 480, 270);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.sample(content, 10);
    
    // Writes the image to disk.
    image.write("doc/a02-discs-supersampling.png");
  }
}
