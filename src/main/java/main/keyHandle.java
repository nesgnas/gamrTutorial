package main;

import entity.Player;

import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandle implements KeyListener { // call in gamePanel.class

    //create variable for control moving
    public boolean upKey, downKey, leftKey, rightKey, menu ;
    gamePanel gp;
    Player player;
    public keyHandle (gamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { // (PRESSED THE KEY) -  place to set control input
        int code = e.getKeyCode();

//        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){ //left
//            leftKey = true;
//        }
//        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){ //right
//            rightKey = true;
//        }
//        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){ //up
//            upKey = true;
//        }
//        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){ //down
//            downKey = true;
//        }
//        if (code == KeyEvent.VK_ESCAPE){
//            if (gp.gameState == gp.playState){
//                gp.gameState = gp.pauseState;
//            }
//            else if (gp.gameState == gp.pauseState){
//                gp.gameState = gp.playState;
//            }
//        }
//        if (code == KeyEvent.VK_SPACE){
//            if (gp.gameState == gp.playState) {
//                gp.gameState = gp.dialogue;
//            }
//            else if (gp.gameState == gp.dialogue){
//                gp.gameState = gp.playState;
//            }
//        }
                //Play State
        if (gp.gameState ==  gp.playState) {
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) { //left
                leftKey = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) { //right
                rightKey = true;
            }
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) { //up
                upKey = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) { //down
                downKey = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_SPACE) {
                gp.gameState = gp.dialogue;
            }
        }
        //Pause State
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }
        //Dialogue State
        else if (gp.gameState == gp.dialogue) {
                if (code == KeyEvent.VK_SPACE) {
                    gp.gameState = gp.playState;
                }
            }
        if (code == KeyEvent.VK_ENTER){

            gp.gameState = gp.optionsState;
            optionState(code);
        }
    }
    public void optionState(int code){
        if (code == KeyEvent.VK_ENTER) {
            //gp.gameState = gp.playState;

            menu = true;
        }
            /*int maxCommandNum = 0;
            switch (gp.ui.subState){
                case 0: maxCommandNum = 5;
            }
            if (code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                gp.playSE(9);
                if (gp.ui.commandNum < 0){
                    gp.ui.commandNum = maxCommandNum;
                }
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                gp.playSE(9);
                if (gp.ui.commandNum > maxCommandNum){
                    gp.ui.commandNum = 0;
                }
            }
//            if (code == KeyEvent.VK_LEFT){
//                if (gp.ui.subState == 0){
//                    if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
//                        gp.music.volumeScale--;
//                        gp.music.checkVolume();
//                        gp.playSE(9);
//                    }
//                    if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0){
//                        gp.se.volumeScale--;
//                        gp.playSE(9);
//                    }
//                }
//            }
//            if (code == KeyEvent.VK_RIGHT){
//                if (gp.ui.subState == 0){
//                    if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5){
//                        gp.music.volumeScale++;
//                        gp.music.checkVolume();
//                        gp.playSE(9);
//                    }
//                    if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5){
//                        gp.se.volumeScale++;
//                        gp.playSE(9);
//                    }
//                }
//            }
        }

             */
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
    }
}
