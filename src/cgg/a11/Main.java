package cgg.a11;

import java.util.ArrayList;
import java.util.List;

import cgg.a11.materials.MaterialBackground;
import cgg.a11.materials.MaterialDiffuse;
import cgg.a11.materials.MaterialGlass;
import cgg.a11.materials.MaterialMirror;
import cgg.a11.shapes.Background;
import cgg.a11.shapes.Disc;
import cgg.a11.shapes.Group;
import cgg.a11.shapes.Plane;
import cgg.a11.shapes.Shape;
import cgg.a11.shapes.Sphere;
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
			Matrix.translation(new Direction(0, 2, 10))
		);
		Camera camera = new Camera(Math.PI / 2, width, height, viewingMatrix);

		// SCENE
		List<Shape> sceneList = new ArrayList<>(2);
		sceneList.add(new Background(new MaterialBackground(new Constant(new Color(0.23, 0.81, 1))))); // BACKGROUND
		// sceneList.add(new Sphere(Vector.zero, 10, new MaterialBackground(texture)));
		// sceneList.add(new Plane(new Point(0, -1, 0), Vector.yAxis, 100, 100, new MaterialMirror(0)));
		sceneList.add(new Plane(new Point(0, -1, 0), Vector.yAxis, 100, 100, new MaterialDiffuse(new Constant(Vector.gray))));
		sceneList.add(new Sphere(new Point(-2, 0, 0), 1, new MaterialDiffuse(texture)));
		sceneList.add(new Sphere(new Point(0, 2, 0), 1, new MaterialDiffuse(new Constant(Vector.green))));
		sceneList.add(new Sphere(new Point(0, 0, 0), 1, new MaterialMirror(0)));
		sceneList.add(new Sphere(new Point(2, 0, 0), 1, new MaterialDiffuse(new PolkaTexture(Vector.black, Vector.red, 0.05))));
		// sceneList.add(new Sphere(new Point(1, 0, 2), 1.4, new MaterialGlass()));

		// LIGHTS
		List<Light> lights = new ArrayList<>();
		lights.add(new LightDirection(new Direction(1, 1, 0)));
		
		Group scene = new Group(sceneList);//, new Transformation(Matrix.translation(new Direction(0, 0, 0))));
		World world = new World(scene, lights);

		// Defines the contents of the image.
		Sampler content = new Raytracer(camera, world);

		// Creates an image and iterates over all pixel positions inside the image.
		var image = new Image(width, height);
		image.sample(content, 24);
		
		// Writes the image to disk.
		image.write("doc/a11.png");
	}
}
