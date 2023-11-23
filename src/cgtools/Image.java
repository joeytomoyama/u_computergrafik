/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;

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

  /**
   * The Color of each pixel of the image is determined based on the {@link Sampler}.
   * @param content Sampler.
   * @param sampleRate how often each pixel is observed, used for anti-aliasing.
   */
  public void sample(Sampler content, int sampleRate) {
    int count = 0;
    // go through each pixel
    for (int x = 0; x != this.width; x++) {
      for (int y = 0; y != this.height; y++) {
        double[] colorSum = {0, 0, 0}; // Sets the color for one particular pixel.
        // take multiple samples for anti-aliasing
        for (double i = 0; i < sampleRate; i++) {
          for (double j = 0; j < sampleRate; j++) {
            double randX = Random.random();
            double randY = Random.random();
            Color color = content.getColor(x + (i + randX) / sampleRate, y + (j + randY) / sampleRate);
            colorSum[0] += color.r();
            colorSum[1] += color.g();
            colorSum[2] += color.b();
          }
        }
        
        int effSamplingRate = sampleRate * sampleRate;
        this.setPixel(x, y, new Color(
          colorSum[0] / effSamplingRate,
          colorSum[1] / effSamplingRate,
          colorSum[2] / effSamplingRate
        ));
        count++;
      }

      // prints progress to console.
      System.out.println(
        String.format("%.0f%%",
        (float) count / (this.width * this.height) * 100)
      );
    }
  }

  public void write(String filename) {
    ImageWriter.write(filename, this.pixels, this.width, this.height);
  }
}
