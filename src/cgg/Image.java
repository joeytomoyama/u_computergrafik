/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;

import cgtools.*;

public class Image {

  private int width;
  private int height;
  private double[] pixels;

  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.pixels = new double[3 * width * height];
  }

  public void setPixel(int x, int y, Color color) {
    if (x > this.width) throw new IllegalArgumentException();
    if (y > this.height) throw new IllegalArgumentException();
    this.pixels[3 * (this.width * y + x) + 0] = color.r();
    this.pixels[3 * (this.width * y + x) + 1] = color.g();
    this.pixels[3 * (this.width * y + x) + 2] = color.b();
  }

  public void sample() {
    //TODO
  }

  public void write(String filename) {
    ImageWriter.write(filename, this.pixels, this.width, this.height);
  }
}
