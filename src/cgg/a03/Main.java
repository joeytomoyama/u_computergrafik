/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a03;

import cgtools.Camera;

public class Main {

  public static void main(String[] args) {
    System.out.println("start");

    int width = 480;
    int height = 270;
    
    spheres(width, height);

    System.out.println("done");
  }

  public static void spheres(int width, int height) {
    System.out.println("spheres");

    // Camera camera = new Camera((int)(Math.PI / 2), 10, 10);
    Camera camera = new Camera(Math.PI / 2, 10, 10);
    
    System.out.println(camera.generateRay(0, 0));
    System.out.println(camera.generateRay(5, 5));
    System.out.println(camera.generateRay(10, 10));

    // // Defines the contents of the image.
    // var content = new ColoredDiscs(500, 480, 270);

    // // Creates an image and iterates over all pixel positions inside the image.
    // var image = new Image(width, height);
    // image.sample(content, 1);
    
    // // Writes the image to disk.
    // image.write("doc/a03-spheres.png");
  }
}
