package Client;

import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JFrame {
    private JPanel mainPanel;
    private JCheckBox chkShading;
    private JCheckBox chkBorders;
    private JSlider sldFPS;
    private JRadioButton rbnOpenSimplex;
    private JRadioButton rbnSuperSimplex;
    private JRadioButton rbnWorley;
    private JSlider sldStartingHue;
    private JSlider sldHueDepth;
    private JSlider sldXOffset;
    private JSlider sldYOffset;
    private JSlider sldZOffset;
    private JSlider sldResolution;

    public enum NOISE {
        OPEN_SIMPLEX,
        SUPER_SIMPLEX,
        WORLEY
    }

    public SettingsFrame() {
        super();

        this.setContentPane(mainPanel);
        this.setTitle("Settings");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setLocation(this.getX()+480, this.getY());
        this.setVisible(true);
        this.setPreferredSize(new Dimension(400, 800));
    }

    public NOISE getNoise() {
        if (rbnOpenSimplex.isSelected())
            return NOISE.OPEN_SIMPLEX;
        else if (rbnSuperSimplex.isSelected())
            return NOISE.SUPER_SIMPLEX;
        else if (rbnWorley.isSelected())
            return NOISE.WORLEY;

        return null;
    }

    public boolean shouldShade() {
        return chkShading.isSelected();
    }

    public boolean shouldBorder() {
        return chkBorders.isSelected();
    }

    public int getFPS() {
        return sldFPS.getValue() > 0 ? sldFPS.getValue() : 1;
    }

    public int getResolution() {
        return sldResolution.getValue();
    }

    public int getStartingHue() {
        return sldStartingHue.getValue();
    }

    public int getHueDepth() {
        return sldHueDepth.getValue();
    }

    public double getXOffset() {
        return sldXOffset.getValue()/1000.0;
    }

    public double getYOffset() {
        return sldYOffset.getValue()/1000.0;
    }

    public double getZOffset() {
        return sldZOffset.getValue()/1000.0;
    }
}
