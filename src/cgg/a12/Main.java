package cgg.a12;

import static cgtools.Vector.yAxis;
import static cgtools.Vector.zAxis;

import java.util.ArrayList;
import java.util.List;

import cgg.a12.materials.Material;
import cgg.a12.materials.MaterialBackground;
import cgg.a12.materials.MaterialDiffuse;
import cgg.a12.materials.MaterialGlass;
import cgg.a12.materials.MaterialMirror;
import cgg.a12.shapes.Background;
import cgg.a12.shapes.Box;
import cgg.a12.shapes.Group;
import cgg.a12.shapes.Plane;
import cgg.a12.shapes.Shape;
import cgg.a12.shapes.Sphere;
import cgtools.Camera;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Image;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Random;
import cgtools.Sampler;
import cgtools.Vector;

public class Main {

	// private static int width = 480;
	// private static int height = 270;
	// private static int width = 200;
	// private static int height = 200;
	private static int width = 1280;
	private static int height = 720;
	
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
			// Matrix.rotation(Vector.zAxis, 20),
			Matrix.translation(new Direction(0, 1, -1))
		);
		// Matrix viewingMatrix = Matrix.rotation(Vector.zAxis, 20);
		// Matrix viewingMatrix = Matrix.translation(new Direction(0, 1, -1));
		Camera camera = new Camera(Math.PI / 2, width, height, viewingMatrix);

		// SCENE
		List<Shape> sceneList = new ArrayList<>();
		sceneList.add(new Background(new MaterialBackground(new Constant(new Color(0.23, 0.81, 1))))); // BACKGROUND
		// sceneList.add(new Sphere(Vector.zero, 10, new MaterialBackground(texture)));
		// sceneList.add(new Plane(new Point(0, -1, 0), Vector.yAxis, 100, 100, new MaterialMirror(0)));
		sceneList.add(new Plane(new Point(0, -3, 0), Vector.yAxis, Integer.MAX_VALUE, Integer.MAX_VALUE, new MaterialDiffuse(new Constant(new Color(0.7, 0.5, 0.3)))));
		// sceneList.add(new Sphere(new Point(-2, 0, 0), 1, new MaterialDiffuse(texture)));
		// sceneList.add(new Sphere(new Point(0, 2, 0), 1, new MaterialDiffuse(new Constant(Vector.green))));
		// sceneList.add(new Sphere(new Point(0, 0, 0), 1, new MaterialMirror(0)));
		// sceneList.add(new Sphere(new Point(2, 0, 0), 1, new MaterialDiffuse(new PolkaTexture(Vector.black, Vector.red, 0.05))));
		// sceneList.add(new Plane(new Point(0, 0, 0), zAxis, 5, 5, new MaterialDiffuse(new PolkaTexture(Vector.black, Vector.green, 0.05))));
		// sceneList.add(new Box(new Point(1, 2, 3), new Point(4, 5, 6), new MaterialDiffuse(new PolkaTexture(Vector.black, Vector.green, 0.05))));
		// sceneList.add(new Sphere(new Point(1, 0, 2), 1.4, new MaterialGlass()));
		
		double depthStart = 10;
		double baseWidth = 4;

		for (int i = 0; i > -1000; i-=50) {
			// sceneList.add(new Box(new Point(1, 2, 3), new Point(4, 5, 6), new MaterialDiffuse(new PolkaTexture(Vector.black, Vector.green, 0.05))));
			var randomX = Random.random() * 50;
			var posX = Random.random() > 0.5 ? randomX : randomX * -1;
			var posZ = i - depthStart;
			// sceneList.add(new Box(new Point(posX - baseWidth, 0, posZ - baseWidth / 2), new Point(posX + baseWidth, 5, posZ + baseWidth / 2), new MaterialDiffuse(new Constant((Vector.green)))));
			// sceneList.add(new Box(new Point(posX, 0, posZ), baseWidth * 2, 100, baseWidth, new MaterialDiffuse(new TexturePolka(Vector.black, Vector.green, 0.05))));
			// sceneList.add(new Box(new Point(posX, 0, posZ), baseWidth * 2, 100, baseWidth, new MaterialDiffuse(new TextureSkyscraper(100, 0.1, Vector.gray, Vector.green))));
			if (Random.random() < 0.8) {
				sceneList.add(new Box(new Point(posX, 0, posZ), baseWidth * 2, 100, baseWidth, new MaterialDiffuse(new Constant((randomColor())))));
			} else {
				sceneList.add(new Box(new Point(posX, 0, posZ), baseWidth * 2, 100, baseWidth, new MaterialGlass()));
			}
		}

		Group scene = new Group(sceneList);//, new Transformation(Matrix.translation(new Direction(0, 0, 0))));

		// LIGHTS
		List<Light> lights = new ArrayList<>();
		lights.add(new LightDirection(new Direction(1, 100, 0)));

		// ANIMATIONS
		List<Animator> animators = new ArrayList<>();
		// animators.add(new RotorLive(scene, yAxis, 0, 90));
		// animators.add(new RotorLive(camera, yAxis, 0, 90));
		animators.add(new AnimSpan(camera, new Direction(0, 0, -5), 6));
		
		World world = new World(scene, lights, animators);

		// // Creates an image and iterates over all pixel positions inside the image.
		var image = new Image(width, height);

		Animation.render(0, 3, 24, world, camera, image, 8, "doc/a12-animation");
	}

	private static Color randomColor() {
		return new Color(Random.random(), Random.random(), Random.random());
	}
}
