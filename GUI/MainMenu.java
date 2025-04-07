package GUI;

import javax.swing.*;

public class MainMenu {
    private static MainMenu _instance;

    public JFrame frame = new JFrame("Horse Racing");

    public static MainMenu getInstance() {
        if (_instance == null) {
            _instance = new MainMenu();
        }
        return _instance;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    MainMenu() {
        // Create a JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);
    }

    public static void main(String[] args) {
        MainMenu.getInstance();
    }
}
