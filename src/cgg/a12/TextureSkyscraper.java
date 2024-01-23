package cgg.a12;

import cgtools.Color;
import cgtools.Sampler;

public class TextureSkyscraper implements Sampler {

    private final int gridCount; // Number of grids along one axis
    private final double marginRatio; // Ratio of margin to the total size of a grid
    private final Color windowColor;
    private final Color marginColor;

    /**
     * Constructor for SkyscraperWindowsTexture.
     *
     * @param gridCount    Number of grids along one axis.
     * @param marginRatio  Ratio of margin to the total size of a grid.
     * @param windowColor  Color of the windows.
     * @param marginColor  Color of the margins.
     */
    public TextureSkyscraper(int gridCount, double marginRatio, Color windowColor, Color marginColor) {
        this.gridCount = gridCount;
        this.marginRatio = marginRatio;
        this.windowColor = windowColor;
        this.marginColor = marginColor;
    }

    @Override
    public Color getColor(double u, double v) {
        double gridSize = 1.0 / gridCount;
        double marginSize = gridSize * marginRatio;

        // Calculate the position within the grid
        double gridU = (u % 1) * gridCount;
        double gridV = (v % 1) * gridCount;
        
        // Determine the position within a single grid
        double posU = gridU - Math.floor(gridU);
        double posV = gridV - Math.floor(gridV);

        // Check if the position is within the margin
        if (posU < marginSize || posU > (1 - marginSize) || posV < marginSize || posV > (1 - marginSize)) {
            return marginColor;
        } else {
            return windowColor;
        }
    }
}
