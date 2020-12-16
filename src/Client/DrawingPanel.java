package Client;

import Noise.OpenSimplex2S;
import Noise.OpenSimplexNoise;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private SettingsFrame settings;
    private final int WIDTH = 1000;
    private final int HEIGHT = 800;

    private double[][] fieldMatrix;
    private OpenSimplexNoise osNoise;
    private OpenSimplex2S ssNoise;
    private double noiseSlice = 0;

    private Thread animationThread;

    public DrawingPanel(SettingsFrame settings) {
        this.settings = settings;

        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.setBackground(new Color(80, 80, 80));
        this.fieldMatrix = new double[(this.HEIGHT/this.settings.getResolution())+1][(this.WIDTH/this.settings.getResolution())+1];
        this.osNoise = new OpenSimplexNoise(6942069);
        this.ssNoise = new OpenSimplex2S(6942069);

        this.animationThread = new AnimationThread(this, settings);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        double xoff = 0;
        for (int row = 0; row < this.fieldMatrix.length; row++) {
            xoff += settings.getXOffset();
            double yoff = 0;
            for (int col = 0; col < this.fieldMatrix[row].length; col++) {
                switch (settings.getNoise()) {
                    case OPEN_SIMPLEX:
                        this.fieldMatrix[row][col] = osNoise.eval(xoff, yoff, this.noiseSlice);
                        break;
                    case SUPER_SIMPLEX:
                        this.fieldMatrix[row][col] = ssNoise.noise3_Classic(xoff, yoff, this.noiseSlice);
                        break;
                    case WORLEY:
                        break;
                }
                yoff += settings.getYOffset();
            }
        }

        if (settings.shouldShade()) {
            for (int row = 0; row < this.fieldMatrix.length; row++) {
                for (int col = 0; col < this.fieldMatrix[row].length; col++) {
                    int alpha = (int)(Math.abs(this.fieldMatrix[row][col]) * 255);
                    float hue = (float)((Math.abs(this.fieldMatrix[row][col]) * settings.getHueDepth() + settings.getStartingHue()) / 360);
                    g.setColor(this.fieldMatrix[row][col] > 0 ? Color.getHSBColor(hue, 1, 1) : new Color(0, 0, 0, 50));
                    g.fillOval(col * this.settings.getResolution() - 4, row * this.settings.getResolution() - 4, 8, 8);
                }
            }
        }

        if (settings.shouldBorder()) {
            for (int row = 0; row < this.fieldMatrix.length - 1; row++) {
                for (int col = 0; col < this.fieldMatrix[row].length - 1; col++) {
                    g.setColor(Color.WHITE);

                    int BASE_X = col * this.settings.getResolution();
                    int BASE_Y = row * this.settings.getResolution();
                    int HALF_RES = (int)(this.settings.getResolution() * .5);

                    int[][] DRAWING_POSITIONS = {{BASE_X + HALF_RES, BASE_Y},
                            {BASE_X + this.settings.getResolution(), BASE_Y + HALF_RES},
                            {BASE_X + HALF_RES, BASE_Y + this.settings.getResolution()},
                            {BASE_X, BASE_Y + HALF_RES}};

                    int[] drawingEval = eval((int)Math.ceil(this.fieldMatrix[row][col]),
                            (int)Math.ceil(this.fieldMatrix[row][col+1]),
                            (int)Math.ceil(this.fieldMatrix[row+1][col+1]),
                            (int)Math.ceil(this.fieldMatrix[row+1][col]));

                    if (drawingEval.length > 0)
                        g.drawLine(DRAWING_POSITIONS[drawingEval[0]][0],
                                DRAWING_POSITIONS[drawingEval[0]][1],
                                DRAWING_POSITIONS[drawingEval[1]][0],
                                DRAWING_POSITIONS[drawingEval[1]][1]);

                    if (drawingEval.length > 2)
                        g.drawLine(DRAWING_POSITIONS[drawingEval[2]][0],
                                DRAWING_POSITIONS[drawingEval[2]][1],
                                DRAWING_POSITIONS[drawingEval[3]][0],
                                DRAWING_POSITIONS[drawingEval[3]][1]);
                }
            }
        }

        this.noiseSlice += settings.getZOffset();
    }

    private int[] eval(int p1, int p2, int p3, int p4) {
        int state = p1 * 8 + p2 * 4 + p3 * 2 + p4;
        switch(state) {
//            case 0:
//            case 15:
//                return new int[]{};
            case 1:
            case 14:
                return new int[]{2, 3};
            case 13:
            case 2:
                return new int[]{1, 2};
            case 3:
            case 12:
                return new int[]{1, 3};
            case 4:
            case 11:
                return new int[]{0, 1};
            case 5:
                return new int[]{0, 3, 1, 2};
            case 6:
            case 9:
                return new int[]{0, 2};
            case 7:
            case 8:
                return new int[]{0, 3};
            case 10:
                return new int[]{0, 1, 2, 3};
            default:
                return new int[]{};
        }
    }
}
