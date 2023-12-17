/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a08;

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
    
    // CAMERA
    Matrix viewingMatrix = Matrix.multiply(
      // Matrix.translation(new Direction(0, -1, 0)),
      // Matrix.rotation(Vector.zAxis, -10),
      Matrix.rotation(Vector.xAxis, -30),
      Matrix.translation(new Direction(0, 1.5, 15))
    );
    Camera camera = new Camera(Math.PI / 2, width, height, viewingMatrix);

    List<Shape> sceneList = new ArrayList<>(2);
    sceneList.add(new Background(new MaterialBackground(new Color(0.93, 0.41, 0.42)))); // BACKGROUND
    sceneList.add(new Sphere(Vector.zero, 0.5, new MaterialBackground(new Color(1, 1, 0)))); // SUN
    
    Group scene = new Group(sceneList, new Transformation(Matrix.translation(new Direction(0, 0, 0))));

    // Defines the contents of the image.
    Sampler content = new Raytracer(camera, scene);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.sample(content, 8);
    
    // Writes the image to disk.
    image.write("doc/a08-noname.png");
  }

  public static List<Point> generateRandomPointsOnRings(int numPoints, List<Shape> propList) {
    List<Point> points = new ArrayList<>(numPoints);

    for (int i = 0; i < numPoints; i++) {
        // Randomly select a ring
        double ringRadius = (Random.random() > 0.5) ? (i + Random.random() / 2) : (i - Random.random() / 2);
        // ringRadius *= 2;
        
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
        
        propList.add(new Ring(Vector.zero, Vector.yAxis, ringRadius, 0.01, new MaterialBackground(Vector.white)));
        points.add(i, newPoint);
    }

    return points;
  }
}
