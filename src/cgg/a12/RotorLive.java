package cgg.a12;

import cgtools.Direction;
import cgtools.Matrix;

public record RotorLive(Animatable animatable, Direction axis, double start, double speed) implements Animator {

	@Override
	public void update(double time) {
		double angle = start + time * speed;
		Matrix matrix = Matrix.rotation(axis, angle);
		animatable.setTransformation(matrix); 
	}

}
