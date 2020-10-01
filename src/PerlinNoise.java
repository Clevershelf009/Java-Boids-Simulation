import java.util.Random;

//Adapted from a c++ implementation of 2D perlin noise by Javidx9
//https://github.com/OneLoneCoder/videos/blob/master/OneLoneCoder_PerlinNoise.cpp

public class PerlinNoise {

    private static Random r = new Random();

    public static double[] PerlinNoise2D(int width, int height, int octaves, double bias) {

        //TODO: Make this a proper javadoc
        //Octaves - the number of layers the perlin noise is built from - higher is better
        //Bias - I think increasing the value of this effectively makes steeper hills and vice versa
        //       unsure as to what the ideal values

        double[] fSeed = generateRandomSeed2D(width, height);
        double[] output = new double[width * height];

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                double fNoise = 0;
                double fScaleAcc = 0;
                double fScale = 1;

                for (int o = 0; o < octaves; o++) {
                    int nPitch = width * (int) Math.pow(2, o);// >> o;
                    int nSampleX1 = (x / nPitch) * nPitch;
                    int nSampleY1 = (y / nPitch) * nPitch;

                    int nSampleX2 = (nSampleX1 + nPitch) % width;
                    int nSampleY2 = (nSampleY1 + nPitch) % width;

                    double fBlendX = (double) (x - nSampleX1) / (double) nPitch;
                    double fBlendY = (double) (y - nSampleY1) / (double) nPitch;

                    double fSampleT = (1 - fBlendX) * fSeed[nSampleY1 * width + nSampleX1] + fBlendX * fSeed[nSampleY1 * width + nSampleX2];
                    double fSampleB = (1 - fBlendX) * fSeed[nSampleY2 * width + nSampleX1] + fBlendX * fSeed[nSampleY2 * width + nSampleX2];

                    fScaleAcc += fScale;
                    fNoise += (fBlendY * (fSampleB - fSampleT) + fSampleT) * fScale;
                    fScale = fScale / bias;
                }

                // Scale to seed range
                output[y * width + x] = fNoise / fScaleAcc;
            }
        return output;
    }

    private static double[] generateRandomSeed2D(int width, int height) {
        double[] output = new double[width*height];

        for (int i = 0; i < width * height; i++) {
            output[i] = r.nextDouble();
        }
        return output;
    }

    private static void setRandomSeed(long n) {
        r.setSeed(n);
    }
}
