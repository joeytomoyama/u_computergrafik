/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cgtools.*;
import cgtools.materials.MaterialDiffuse;
import cgtools.materials.MaterialBackground;
import cgtools.shapes.*;

public class Main {

  public static void main(String[] args) {
    System.out.println("start");

    // int width = 960;
    // int height = 540;
    int width = 480;
    int height = 270;
    
    scene(width, height);

    System.out.println("done");
  }

  public static void scene(int width, int height) {
    System.out.println("scene");

    Matrix viewingMatrix = Matrix.multiply(
      Matrix.rotation(Vector.zAxis, 0),
      // Matrix.rotation(Vector.zAxis, -10),
      // Matrix.rotation(Vector.xAxis, -20),
      Matrix.translation(new Direction(0, 0, 5))
    );
    Camera camera = new Camera(Math.PI / 2, width, height, viewingMatrix);

    Shape background = new Background(new MaterialBackground(new Color(0.53, 0.81, 0.92)));

    Shape ground = new Ring(new Point(0, -4, 0), new Direction(0, 1, 0), 25, new MaterialBackground(new Color(1, 1, 1)));

    List<Shape> propList = new ArrayList<>(20);
    // propList.add(ground);
    // propList.add(background);
    propList.add(new Sphere(Vector.zero, 0.5, new MaterialBackground(Vector.red)));
    // propList.add(new Sphere(Vector.zero, 0.5, new MaterialBackground(Vector.green)));

    Group scene = new Group(propList, new Transformation(Matrix.translation(new Direction(0, 3, 0))));

    // Defines the contents of the image.
    Sampler content = new Raytracer(camera, scene);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.sample(content, 8);
    
    // Writes the image to disk.
    image.write("doc/a07-scene.png");
  }

  public static List<Point> generateRandomPointsOnRings(int numRings, int numPoints, List<Shape> propList) {
    List<Point> points = new ArrayList<>(numPoints + numRings);

    for (int i = 0; i < numPoints; i++) {
        // Randomly select a ring
        int ringRadius = (int)(Random.random() * numRings) + 2;
        
        // Generate a random angle
        double angle = Random.random() * 2 * Math.PI;
        
        // Calculate the point's coordinates
        Point newPoint = new Point(ringRadius * Math.cos(angle), 0, ringRadius * Math.sin(angle));
        boolean tooClose = false;
        double minDistance = 0.3;
        
        // make sure points are not too close to each other
        for (Point point : points) {
          if (Vector.length(Vector.subtract(point, newPoint)) < minDistance) {
            tooClose = true;
            break;
          }
        }
        if (tooClose) {
          i--;
          continue;
        }
        
        propList.add(new Ring(Vector.zero, Vector.yAxis, ringRadius, new MaterialBackground(Vector.white)));
        points.add(i, newPoint);
    }

    return points;
}
}
