/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a02;

import java.util.ArrayList;
import java.util.List;

import cgtools_deprecated.*;
import cgtools_deprecated.shapes.Disc2d;

// Represents the contents of an image. Provides the same color for all pixels.
class ColoredDiscs implements Sampler {

	// private int amount;
	private List<Disc2d> discs;
	private final Color NO_HIT = new Color(0, 0, 0);

	public ColoredDiscs(int amount, int width, int height) {
		this.discs = new ArrayList<>(amount);
		for (int i = 0; i < amount; i++) {
			this.discs.add(new Disc2d(
				new Point(Math.random() * width, Math.random() * height, 0),
				Math.random() * 50,
				new Color(Math.random(), Math.random(), Math.random())
			));
		}

		this.discs.sort((disc1, disc2) -> {
			if (disc1.radius() > disc2.radius()) {
				return 1;
			} else if (disc1.radius() < disc2.radius()) {
				return -1;
			} else {
				return 0;
			}
		});
	}
  
	// Returns the color for the given position.
	public Color getColor(double x, double y) {
		for(Disc2d disc : discs) {
			if (disc.isPointInDisc(new Point(x, y, 0))) {
				return disc.color();
			}
		}
		return NO_HIT;
	}
}

// See
// https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Record.html
// for details on the record data type.
