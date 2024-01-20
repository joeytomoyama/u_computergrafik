package cgg.a12;

import cgg.a12.materials.MaterialGlass;
import cgtools.Direction;

public class Test {
	
	public static void main(String[] args) {
		MaterialGlass glass = new MaterialGlass();
		System.out.println(glass.calculateReflectionAngle(
			new Direction(0, 0, 0),
			new Direction(0, 1, 0)
		));
		System.out.println(glass.calculateReflectionAngle(
			new Direction(0.707, -0.707, 0),
			new Direction(0, 1, 0)
		));
		System.out.println(glass.calculateReflectionAngle(
			new Direction(0.707, 0.707, 0),
			new Direction(0, 1, 0)
		));

		System.out.println();

		System.out.println(glass.schlickApproximation(
			new Direction(0.707, 0.707, 0),
			new Direction(0, 1, 0),
			1,
			1.5
		));
		System.out.println(glass.schlickApproximation(
			new Direction(0.707, 0.707, 0),
			new Direction(0, 1, 0),
			1.5,
			1
		));
		System.out.println(glass.schlickApproximation(
			new Direction(0.995, -0.1, 0),
			new Direction(0, 1, 0),
			1,
			1.5
		));
		System.out.println(glass.schlickApproximation(
			new Direction(0.995, -0.1, 0),
			new Direction(0, 1, 0),
			1.5,
			1
		));

		System.out.println();

		System.out.println(glass.calculateRefractionAngle(
				new Direction(0.707, 0.707, 0),
				new Direction(0, 1, 0),
				1,
				1.5
		));
		System.out.println(glass.calculateRefractionAngle(
				new Direction(0.1, -0.995, 0),
				new Direction(0, 1, 0),
				1.5,
				1
		));
	}
}
