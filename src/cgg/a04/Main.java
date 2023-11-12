/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a04;

import java.util.Arrays;
import java.util.List;

import cgg.Image;
import cgtools.Camera;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Ray;
import cgtools.Sampler;
import cgtools.shapes.Background;
import cgtools.shapes.Shape;
import cgtools.shapes.Sphere;
import cgtools.shapes.Disc3d;
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

    Shape background = new Background(new Color(1, 0, 1));
    // Shape ground = new Disc3d(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), 10, new Color(1, 1, 0));
    Shape ground = new Disc3d(new Point(0.0, -0.5, -2.5), new Direction(0, 0, 1), 10, new Color(1, 1, 0));
    Shape globe1 = new Sphere(new Point(-1.0, -0.25, -2.5), 0.7, new Color(1, 0, 0));
    Shape globe2 = new Sphere(new Point(0.0, -0.25, -2.5), 0.5, new Color(0, 1, 0));
    Shape globe3 = new Sphere(new Point(1.0, -0.25, -2.5), 0.7, new Color(0, 0, 1));

    Group group = new Group(Arrays.asList(background, ground));

    // Defines the contents of the image.
    Sampler content = new Raytracer(camera, group);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.sample(content, 10);
    
    // Writes the image to disk.
    image.write("doc/a04-scene.png");
  }
}
