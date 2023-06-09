package main;

import java.awt.event.MouseEvent;

public class Menu extends Background{
    gamePanel gp;
    public double orbitX = -700; /* x-coordinate in orbit's center */
    public double orbitY = -600; /* y-coordinate in orbit's center */

    @Override
    public void update(){
        radian = orbitSpeed * t;
        drawX = orbitX + orbitRadius * Math.cos(radian);
        drawY = orbitY + orbitRadius * Math.sin(radian);
        t+=1;
    }
    public int mouseX_Y (int x){
        return x;
    }
    void mousePress(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (mx>60 && mx<210 && my>250 && my<290) //Play button
            gamePanel.state = "game";
        else if (mx>60 && mx<210 && my>300 && my<340) // intro button
            gamePanel.state = "intro";
        else if (mx>60 && mx<210 && my>350 && my<390) //guide button
            gamePanel.state = "guide";
        else if (mx>60 && mx<210 && my>400 && my<440) // credits button
            gamePanel.state = "credits";
        else if(mx>60 && mx<210 && my>450 && my<490)
            System.exit(1);

    }
}
