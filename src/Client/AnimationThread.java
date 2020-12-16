package Client;

import javax.swing.*;

public class AnimationThread extends Thread implements Runnable {
    private JPanel animatedPanel;
    private SettingsFrame settings;

    public AnimationThread(JPanel panelToAnimate, SettingsFrame settings) {
        super();
        this.settings = settings;
        this.animatedPanel = panelToAnimate;
        this.start();
    }

    @Override
    public void run() {
        while(true)
            try {
                this.animatedPanel.repaint();
                this.sleep(1000 / settings.getFPS());
            } catch (Exception ex) {
                System.out.println("Animation Thread Exception");
            }
    }
}
