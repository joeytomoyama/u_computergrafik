package cgtools;

import java.util.concurrent.Callable;

public record OnePixel(Sampler sampler, int sampleRate, int x, int y) implements Callable<ColorData> {

    @Override
    public ColorData call() {
		// supersampling
        Color colorSum = Vector.black;
        for (int xi = 0; xi < sampleRate; xi++) {
            for (int yi = 0; yi < sampleRate; yi++) {
                double randX = Random.random();
                double randY = Random.random();
				Color sampleColor = sampler.getColor(x + (xi + randX) / sampleRate, y + (yi + randY) / sampleRate);
				colorSum = Vector.add(colorSum, sampleColor);
            }
        }

		// return corrected color + position
		int effSamplingRate = sampleRate * sampleRate;
        return new ColorData(
            Vector.divide(colorSum, effSamplingRate),
            (int) x,
            (int) y
        );
    }
    
}
