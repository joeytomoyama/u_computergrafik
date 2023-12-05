/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a06;

import java.util.Arrays;

// import cgg.a05_2022.DiffuseMat;
// import cgg.a05_2022.Group;
// import cgg.a05_2022.Shape;
// import cgg.a05_2022.Sphere;
import cgtools.*;
import cgtools.materials.DiffuseMat;
import cgtools.materials.MaterialBackground;
import cgtools.shapes.*;

public class Main {

  public static void main(String[] args) {
    System.out.println("start");

    int width = 960;
    int height = 540;
    
    scene(width, height);

    System.out.println("done");
  }

  public static void scene(int width, int height) {
    System.out.println("scene");

    System.out.println(Matrix.rotation(Vector.yAxis, -15));

    Matrix viewingMatrix = Matrix.multiply(
      Matrix.rotation(Vector.yAxis, -15),
      Matrix.translation(new Direction(0, 0, 5))
    );

    Camera camera = new Camera(Math.PI / 2, width, height, viewingMatrix);

    Shape background = new Background(new MaterialBackground(new Color(0.53, 0.81, 0.92)));
    Shape ground = new Disc(new Point(0, -0.75, 0), new Direction(0, 1, 0), 0, new DiffuseMat(new Color(0.2, 0.8, 0.1)));

    Color red = new Color(1, 0, 0);
    Color green = new Color(0, 1, 0);
    Color blue = new Color(0, 0, 1);

    // Point point1 = new Point(-1.3, 1, -5);
    // Point point2 = new Point(0.0, -0.25, -2.5);
    // Point point3 = new Point(0.5, -0.25, -1.5);

    Point point1 = new Point(-1.0, -0.25, -2.5);
    Point point2 = new Point(0.0, -0.25, -2.5);
    Point point3 = new Point(1.0, -0.25, -2.5);

    Shape globe1 = new Sphere(point1, 0.7, new DiffuseMat(red));
    Shape globe2 = new Sphere(point2, 0.5, new DiffuseMat(green));
    Shape globe3 = new Sphere(point3, 0.7, new DiffuseMat(blue));
    
    Shape ring1 = new Disc(point1, new Direction(-0.2, 1, 0.4), 0.8, new DiffuseMat(Vector.add(red, Vector.white)));
    Shape ring2 = new Disc(point2, new Direction(0.4, -0.7, -1.2), 0.6, new DiffuseMat(Vector.add(green, Vector.white)));
    Shape ring3 = new Disc(point3, new Direction(0.8, 0.2, 0.2), 0.8, new DiffuseMat(Vector.add(blue, Vector.white)));

    Group scene = new Group(Arrays.asList(
      background,
      ground,
      // ring1,
      // ring2,
      // ring3,
      globe1,
      globe2,
      globe3
    ));

    // Defines the contents of the image.
    Sampler content = new Raytracer(camera, scene);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.sample(content, 4);
    
    // Writes the image to disk.
    image.write("doc/a06-camera.png");
  }
}
