import Client.DrawingPanel;
import Client.SettingsFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] yeet) {
        JFrame mainFrame = new JFrame();
        SettingsFrame settings = new SettingsFrame();
        DrawingPanel mainContent = new DrawingPanel(settings);

        mainFrame.setContentPane(mainContent);
        mainFrame.setTitle("Marching Squares");
        //mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLocation(mainFrame.getX()-200, mainFrame.getY());
        mainFrame.setVisible(true);
    }
}
