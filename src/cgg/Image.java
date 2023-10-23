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
    if (x > this.width || y > this.height) throw new IllegalArgumentException();
    this.pixels[3 * (this.width * y + x) + 0] = color.r();
    this.pixels[3 * (this.width * y + x) + 1] = color.g();
    this.pixels[3 * (this.width * y + x) + 2] = color.b();
  }

  public void sample(Sampler content) {
    for (int x = 0; x != this.width; x++) {
      for (int y = 0; y != this.height; y++) {
        // Sets the color for one particular pixel.
        this.setPixel(x, y, content.getColor(x, y));
      }
    }
  }

  public void write(String filename) {
    ImageWriter.write(filename, this.pixels, this.width, this.height);
  }
}
