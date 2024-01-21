package cgg.a12;

import java.util.List;

import cgg.a12.shapes.Group;

public record World(Group group, List<Light> lights, List<Animator> animators) {

	public void update(double time) {
		for (Animator animator : animators) {
			animator.update(time);
		}
	}
	
}
