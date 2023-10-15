/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import static cgtools.Vector.*;
import cgg.*;

public class Main {

  public static void main(String[] args) {
    System.out.println("start");
    
    polkaDots();

    System.out.println("done");
  }

  public static void muster() {
    System.out.println("muster");
    var width = 480;
    var height = 270;

    // Defines the contents of the image.
    var content = new ConstantColor(gray);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image.setPixel(x, y, content.getColor(x, y));
      }
    }
    
    // Writes the image to disk.
    image.write("doc/a01-image.png");
  }

  public static void kreisscheibe() {
    System.out.println("kreisscheibe");
    var width = 480;
    var height = 270;

    // Defines the contents of the image.
    var content = new CircleColor(red, width, height, 80);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image.setPixel(x, y, content.getColor(x, y));
      }
    }
    
    // Writes the image to disk.
    image.write("doc/a01-kreisscheibe.png");
  }

  public static void polkaDots() {
    System.out.println("polka-dots");
    var width = 480;
    var height = 270;

    // Defines the contents of the image.
    var content = new PolkaColor(red, width, height, 48, 16, 2);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image.setPixel(x, y, content.getColor(x, y));
      }
    }
    
    // Writes the image to disk.
    image.write("doc/a01-polka-dots.png");
  }
}
