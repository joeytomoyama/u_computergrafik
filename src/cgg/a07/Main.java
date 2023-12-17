/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a07;

import java.util.ArrayList;
import java.util.List;

import cgtools.*;
import cgtools.materials.MaterialDiffuse;
import cgtools.materials.Material;
import cgtools.materials.MaterialBackground;
import cgtools.shapes.*;

public class Main {

	// private static int width = 1920;
	// private static int height = 1080;
	private static int width = 960;
	private static int height = 540;

	public static void main(String[] args) {
		System.out.println("start");
		
		long startTime = System.currentTimeMillis();
		scene();
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println("Execution time in milliseconds: " + duration);

		System.out.println("done");
	}

	public static void scene() {
		System.out.println("scene");
		
		Matrix viewingMatrix = Matrix.multiply(
			// Matrix.translation(new Direction(0, -1, 0)),
			Matrix.rotation(Vector.zAxis, 20),
			Matrix.rotation(Vector.xAxis, 20),
			Matrix.translation(new Direction(0, 0, 20))
		);
		Camera camera = new Camera(Math.PI / 2, width, height, viewingMatrix);

		List<Shape> sceneList = new ArrayList<>(2);
		sceneList.add(new Background(new MaterialBackground(new Color(0.23, 0.81, 1)))); // BACKGROUND
		sceneList.add(new Sphere(Vector.zero, 1, new MaterialBackground(new Color(1, 0.7, 0)))); // SUN

		// generate planets
		int planetAmount = 9;
		generateRandomPointsOnRings(planetAmount, sceneList, true).forEach(point -> {
			Color planetColor = new Color(Random.random(), Random.random(), Random.random());
			Material planetMaterial = new MaterialDiffuse(planetColor);
			double planetSize = Math.max(Random.random() * 0.5, 0.2);

			List<Shape> planetSystemList = new ArrayList<>(planetAmount);
			planetSystemList.add(new Sphere(Vector.zero, planetSize, planetMaterial));
			planetSystemList.add(new Cylinder(Vector.zero, 1000, 0.02, planetMaterial));

			if (planetColor.r() < 0.6) {
				// generate moon
				generateRandomPointsOnRings(1, planetSystemList, false).forEach(moonPoint -> {
					Material moonMaterial = new MaterialDiffuse(new Color(Random.random(), Random.random(), Random.random()));
					planetSystemList.add(new Sphere(moonPoint, planetSize * 0.2, moonMaterial));
					planetSystemList.add(new Cylinder(moonPoint, 1000, 0.01, moonMaterial));
				});
			} else {
				// generate ring
				planetSystemList.add(new Disc(
					Vector.zero,
					new Direction(Random.random(), Random.random(), Random.random()),
					planetSize * 1.5,
					new MaterialDiffuse(Vector.add(planetColor, Vector.white))
				));
			}

			// put planet system to local coordinate system
			Group planetSystem = new Group(
				planetSystemList,
				new Transformation(Matrix.translation(point))
			);
			
			sceneList.add(planetSystem);
		});
		
		Group scene = new Group(sceneList);//, new Transformation(Matrix.translation(new Direction(0, 0, 0))));

		// Defines the contents of the image.
		Sampler content = new Raytracer(camera, scene);

		// Creates an image and iterates over all pixel positions inside the image.
		var image = new Image(width, height);
		image.sample(content, 8);
		
		// Writes the image to disk.
		image.write("doc/a07-scene.png");
	}

	public static List<Point> generateRandomPointsOnRings(int numPoints, List<Shape> propList, boolean big) {
		List<Point> points = new ArrayList<>(numPoints);

		for (int i = 1; i <= numPoints; i++) {
			// Randomly select a ring
			double ringRadius = (Random.random() > 0.5) ? (i + Random.random() / 2) : (i - Random.random() / 2);
			if (big) ringRadius *= 2;
			
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
			
			propList.add(new Ring(Vector.zero, Vector.yAxis, ringRadius, 0.02, new MaterialBackground(Vector.white)));
			// points.add(i, newPoint);
			points.add(newPoint);
		}

		return points;
	}
}
