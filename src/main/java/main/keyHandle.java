package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandle implements KeyListener { // call in gamePanel.class

    //create variable for control moving
    public boolean upKey, downKey, leftKey, rightKey ;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { // (PRESSED THE KEY) -  place to set control input
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A){ //left
            leftKey = true;
        }
        if (code == KeyEvent.VK_D){ //right
            rightKey = true;
        }
        if (code == KeyEvent.VK_W){ //up
            upKey = true;
        }
        if (code == KeyEvent.VK_S){ //down
            downKey = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // (IN NORMAL) - place to set control input
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A){ //left
            leftKey = false;
        }
        if (code == KeyEvent.VK_D){ //right
            rightKey = false;
        }
        if (code == KeyEvent.VK_W){ //up
            upKey = false;
        }
        if (code == KeyEvent.VK_S){ //down
            downKey = false;
        }

    }
}
