package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandle implements KeyListener { // call in gamePanel.class

    //create variable for control moving
    public boolean upKey, downKey, leftKey, rightKey,doorKey ;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { // (PRESSED THE KEY) -  place to set control input
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){ //left
            leftKey = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){ //right
            rightKey = true;
        }
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){ //up
            upKey = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){ //down
            downKey = true;
        }
        if (code == KeyEvent.VK_K){ // door
            doorKey = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // (IN NORMAL) - place to set control input
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){ //left
            leftKey = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){ //right
            rightKey = false;
        }
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){ //up
            upKey = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){ //down
            downKey = false;
        }
        if (code == KeyEvent.VK_K){ //door
            doorKey = false;
        }
    }
}
