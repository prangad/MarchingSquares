package Noise;

public class WorleyNoise {
    private Point[] featurePoints = new Point[9];

    class Point {
        private int x;
        private int y;
        private int z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public WorleyNoise(int width, int height) {
        for (int i = 0; i < this.featurePoints.length; i++) {
            featurePoints[i] = new Point((int)(Math.random()*width),
                    (int)(Math.random()*height),
                    (int)(Math.random()*1000));
        }
    }

    public double eval2D(double x, double y, double z) {
        return 0.0;
    }

    public double eval3D(double x, double y, double z) {
        return 0.0;
    }
}
