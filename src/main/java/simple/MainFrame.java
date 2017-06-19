package simple;

import javax.swing.*;

public class MainFrame extends JFrame {
    private MainPanel panel;

    private MainFrame(String s) {
        super("The Balls Game");
        setSize(1280, 720);
        panel = new MainPanel();
        this.setContentPane(panel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("Balls game"));
    }
}