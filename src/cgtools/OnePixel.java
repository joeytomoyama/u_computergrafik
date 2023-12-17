package cgtools;

import java.util.concurrent.Callable;

public record OnePixel(Sampler sampler, int sampleRate, int x, int y) implements Callable<ColorData> {

    @Override
    public ColorData call() {
        // return new ColorData(sampler.getColor(x, y), (int)x, (int)y);
        // return sampler.getColor(x, y);
        Color colorSum = Vector.black;
        for (int xi = 0; xi < sampleRate; xi++) {
            for (int yi = 0; yi < sampleRate; yi++) {
                double randX = Random.random();
                double randY = Random.random();
                // colorSum = sampler.getColor(x + (xi + randX) / sampleRate, y + (yi + randY) / sampleRate);
				Color sampleColor = sampler.getColor(x + (xi + randX) / sampleRate, y + (yi + randY) / sampleRate);
				colorSum = Vector.add(colorSum, sampleColor);
            }
        }

		int effSamplingRate = sampleRate * sampleRate;
        return new ColorData(
            // new Color(colorSum.r() / effSamplingRate, colorSum.g() / effSamplingRate, colorSum.b() / effSamplingRate),
            Vector.divide(colorSum, effSamplingRate),
            (int) x,
            (int) y
        );
    }
    
}
