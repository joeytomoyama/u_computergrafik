/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools_deprecated;

// A Sampler is something that can return a color value for a two dimensional
// position.
public interface Sampler {
  public Color getColor(double x, double y);
}
