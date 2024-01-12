package cgg.a09;

import java.util.ArrayList;
import java.util.List;

import cgg.a09.materials.MaterialBackground;
import cgg.a09.materials.MaterialDiffuse;
import cgg.a09.shapes.Background;
import cgg.a09.shapes.Disc;
import cgg.a09.shapes.Group;
import cgg.a09.shapes.Plane;
import cgg.a09.shapes.Shape;
import cgg.a09.shapes.Sphere;
import cgtools.Camera;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Image;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Sampler;
import cgtools.Vector;

public class Main {

	private static int width = 480;
	private static int height = 270;
	
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
		Sampler texture = new Texture("img/sky.jpg");
		
		Matrix viewingMatrix = Matrix.multiply(
			Matrix.translation(new Direction(0, -0, 0)),
			// Matrix.rotation(Vector.zAxis, 20),
			// Matrix.rotation(Vector.xAxis, 20),
			Matrix.translation(new Direction(0, 0, 10))
		);
		Camera camera = new Camera(Math.PI / 2, width, height, viewingMatrix);

		List<Shape> sceneList = new ArrayList<>(2);
		sceneList.add(new Background(new MaterialBackground(new Constant(Vector.white)))); // BACKGROUND
		// sceneList.add(new Sphere(Vector.zero, 10, new MaterialBackground(texture)));
		sceneList.add(new Plane(new Point(0, 0, -10), Vector.zAxis, 4, 3, new MaterialDiffuse(new TextureTransform(texture, Matrix.rotation(Vector.zAxis, 45)))));
		// sceneList.add(new Sphere(new Point(-2, 0, 0), 1, new MaterialDiffuse(new Constant(Vector.green))));
		// sceneList.add(new Sphere(Vector.zero, 1, new MaterialDiffuse(texture)));
		// sceneList.add(new Sphere(new Point(2, 0, 0), 1, new MaterialDiffuse(new PolkaTexture(Vector.black, Vector.red, 0.05))));
		
		Group scene = new Group(sceneList);//, new Transformation(Matrix.translation(new Direction(0, 0, 0))));

		// Defines the contents of the image.
		Sampler content = new Raytracer(camera, scene);

		// Creates an image and iterates over all pixel positions inside the image.
		var image = new Image(width, height);
		image.sample(content, 4);
		
		// Writes the image to disk.
		image.write("doc/a09-test.png");
	}
}
