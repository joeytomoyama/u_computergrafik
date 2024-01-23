package cgg.a12;

import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Vector;

public record AnimSpan(Animatable animatable, Direction direction, double speed) implements Animator {
	
	@Override
	public void update(double time) {
		var directionVal = Vector.multiply(direction, speed * time);
		Matrix matrix = Matrix.translation(directionVal);
		animatable.setTransformation(matrix); 
	}
}
