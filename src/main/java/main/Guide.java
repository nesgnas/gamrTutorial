package main;

import java.awt.event.MouseEvent;

public class Guide extends Background {

    public double orbitX = -20; /* x-coordinate in orbit's center */
    public double orbitY = -20; /* y-coordinate in orbit's center */

    @Override
    public void update() {
        radian = orbitSpeed * t;
        drawX = orbitX + orbitRadius * Math.cos(radian);
        drawY = orbitY + orbitRadius * Math.sin(radian);
        t += 1;
    }

    void mousePress(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (mx > 60 && mx < 210 && my > 350 && my < 390) {

            gamePanel.state = "guide";
        } else if (mx > 20 && mx < 170 && my > 700 && my < 740) {

            gamePanel.state = "menu";
        }
    }
}