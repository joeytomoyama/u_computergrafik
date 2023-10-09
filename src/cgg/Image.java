/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;

import cgtools.*;

public class Image {
  public Image(int width, int height) {
    notYetImplemented();
  }

  public void setPixel(int x, int y, Color color) {
    notYetImplemented();
  }

  public void write(String filename) {
    // Use cggtools.ImageWriter.write() to implement this.
    notYetImplemented();
  }

  private void notYetImplemented() {
    System.err.println("\nPlease complete the implementation of class cgg.Image as part of assignment 1.\n");
    System.exit(1);
  }
}
