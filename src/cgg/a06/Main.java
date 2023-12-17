/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a06;

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

    int width = 960;
    int height = 540;
    
    scene(width, height);

    System.out.println("done");
  }

  public static void scene(int width, int height) {
    System.out.println("scene");

    Matrix viewingMatrix = Matrix.multiply(
      // Matrix.translation(new Direction(0, -1, 0)),
      Matrix.rotation(Vector.zAxis, -10),
      Matrix.rotation(Vector.xAxis, -20),
      Matrix.translation(new Direction(0, 0, 10))
    );
    Camera camera = new Camera(Math.PI / 2, width, height, viewingMatrix);

    Shape background = new Background(new MaterialBackground(new Color(0.53, 0.81, 0.92)));
    // Shape ground1 = new Disc(new Point(0, -4, 0), new Direction(0, 1, 0), 25, new MaterialBackground(new Color(1, 0.3, 0)));
    // Shape ground2 = new Disc(new Point(0, 4, 0), new Direction(0, 1, 0), 25, new MaterialBackground(new Color(0, 0, 1)));

    Shape ground1 = new Disc(new Point(0, -4, 0), new Direction(0, 1, 0), 25, new MaterialBackground(new Color(1, 1, 1)));
    Shape ground2 = new Disc(new Point(0, 4, 0), new Direction(0, 1, 0), 25, new MaterialBackground(new Color(1, 1, 1)));

    Shape sun = new Sphere(new Point(0, 0, 0), 1, new MaterialBackground(new Color(1, 1, 0)));

    List<Shape> propList = new ArrayList<>(20);
    // propList.add(ground1);
    // propList.add(ground2);
    propList.add(background);
    propList.add(sun);

    generateRandomPointsOnRings(8, 10, propList).forEach(point -> {
      Color randomColor = new Color(Random.random(), Random.random(), Random.random());
      double sphereRadius = Random.random() * 0.1 + 0.1;
      propList.add(new Sphere(point, sphereRadius, new MaterialDiffuse(randomColor)));
      propList.add(new Disc(
        point,
        new Direction(Random.random(), Random.random(), Random.random()),
        sphereRadius + 0.15, 
        new MaterialDiffuse(Vector.add(randomColor, Vector.white))
      ));
      propList.add(new Cylinder(point, 40, 0.01, new MaterialDiffuse(randomColor)));
    });

    Group scene = new Group(propList);

    // Defines the contents of the image.
    Sampler content = new Raytracer(camera, scene);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.sample2(content, 8);
    
    // Writes the image to disk.
    image.write("doc/a06-cameraa.png");
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
        
        propList.add(new Ring(Vector.zero, Vector.yAxis, ringRadius, 0.05, new MaterialBackground(Vector.white)));
        points.add(i, newPoint);
    }

    return points;
  }
}