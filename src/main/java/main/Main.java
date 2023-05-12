package main;

import javax.swing.*;
import java.applet.*;
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("something");

        gamePanel gamePanel =new gamePanel(); // call gamePanel.class

        frame.add(gamePanel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        gamePanel.setUpGame();
        gamePanel.starGameThread(); // beginning the thread of game - (game loop)
    }
}
