/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a03;

import java.util.Arrays;
import java.util.List;

import cgg.Image;
import cgtools.Camera;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Ray;
import cgtools.shapes.Sphere;

public class Main {

  public static void main(String[] args) {
    System.out.println("start");

    int width = 480;
    int height = 270;
    
    // cameraTest(width, height);
    // sphereTest(width, height);
    spheres(width, height);

    System.out.println("done");
  }

  public static void cameraTest(int width, int height) {
    System.out.println("cameraTest");

    Camera camera = new Camera(Math.PI / 2, 10, 10);
    
    System.out.println(camera.generateRay(0, 0));
    System.out.println(camera.generateRay(5, 5));
    System.out.println(camera.generateRay(10, 10));
  }

  public static void sphereTest(int width, int height) {
    System.out.println("cameraTest");

    Sphere sphere1 = new Sphere(new Point(0, 0, -2), 1, new Color(1, 0, 0));
    Sphere sphere2 = new Sphere(new Point(0, 0, -2), 1, new Color(1, 0, 0));
    Sphere sphere3 = new Sphere(new Point(0, -1, -2), 1, new Color(1, 0, 0));
    Sphere sphere4 = new Sphere(new Point(0, 0, -2), 1, new Color(1, 0, 0));
    Sphere sphere5 = new Sphere(new Point(0, 0, -4), 1, new Color(1, 0, 0));

    System.out.println(sphere1.intersect(new Ray(new Point(0, 0, 0), new Direction(0, 0, -1), 0, Double.POSITIVE_INFINITY)));
    System.out.println(sphere2.intersect(new Ray(new Point(0, 0, 0), new Direction(0, 1, -1), 0, Double.POSITIVE_INFINITY)));
    System.out.println(sphere3.intersect(new Ray(new Point(0, 0, 0), new Direction(0, 0, -1), 0, Double.POSITIVE_INFINITY)));
    System.out.println(sphere4.intersect(new Ray(new Point(0, 0, -4), new Direction(0, 0, -1), 0, Double.POSITIVE_INFINITY)));
    System.out.println(sphere5.intersect(new Ray(new Point(0, 0, 0), new Direction(0, 0, -1), 0, 2)));
  }

  public static void spheres(int width, int height) {
    System.out.println("spheres");

    Camera camera = new Camera(Math.PI / 2, width, height);

    List<Sphere> spheres = Arrays.asList(
      new Sphere(new Point(0, 0, -9), 1, new Color(0, 0, 1)),
      new Sphere(new Point(3, 0, -9), 1, new Color(0, 1, 0)),
      new Sphere(new Point(6, 0, -9), 1, new Color(1, 0, 0)),
      new Sphere(new Point(-3, 0, -9), 1, new Color(1, 0, 1)),
      new Sphere(new Point(-6, 0, -9), 1, new Color(0, 1, 1))
    );

    // Defines the contents of the image.
    var content = new RaytraceColor(camera, spheres, width, height);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.sample(content, 100);
    
    // Writes the image to disk.
    image.write("doc/a03-spheres.png");
  }
}
