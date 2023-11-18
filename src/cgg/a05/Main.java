/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a05;

import java.util.Arrays;

import cgg.Image;
import cgtools.Camera;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Sampler;
import cgtools.shapes.Background;
import cgtools.shapes.Shape;
import cgtools.shapes.Sphere;
import cgtools.shapes.Disc;
import cgtools.shapes.Group;

public class Main {

  public static void main(String[] args) {
    System.out.println("start");

    int width = 480;
    int height = 270;
    
    scene(width, height);

    System.out.println("done");
  }

  public static void scene(int width, int height) {
    System.out.println("scene");

    Camera camera = new Camera(Math.PI / 3, width, height);

    Shape background = new Background(new Color(0, 0, 0));

    Shape ground1 = new Disc(new Point(-1.3, 1, -5), new Direction(0.2, 1, 0.4), 0, new Color(1, 0.2, 0.2));
    Shape ground2 = new Disc(new Point(0.0, -0.25, -2.5), new Direction(0.1, -0.7, -1.2), 0.5, new Color(0.2, 1, 0.2));
    Shape ground3 = new Disc(new Point(0.5, -0.25, -1.5), new Direction(0.8, 0.2, 0.2), 0.7, new Color(0.2, 0.2, 1));

    Shape globe1 = new Sphere(new Point(-1.3, 1, -5), 0.5, new Color(1, 0, 0));
    Shape globe2 = new Sphere(new Point(0.0, -0.25, -2.5), 0.4, new Color(0, 1, 0));
    Shape globe3 = new Sphere(new Point(0.5, -0.25, -1.5), 0.4, new Color(0, 0, 1));

    Group group = new Group(Arrays.asList(
      background,
      ground1,
      ground2,
      ground3,
      globe1,
      globe2,
      globe3
    ));

    // Defines the contents of the image.
    Sampler content = new Raytracer(camera, group);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.sample(content, 10);
    
    // Writes the image to disk.
    image.write("doc/a04-scene.png");
  }
}
