package main;

import CommandPatternKey.*;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static CommandPatternKey.keyBoard.keyBoards;
import static java.awt.event.KeyEvent.*;

public class keyHandle implements KeyListener { // call in gamePanel.class

    //create variable for control moving
    public boolean upKey, downKey, leftKey, rightKey, menu;
    public static boolean enterpressed = false;
    gamePanel gp;



    // Tong so key muon handel
    private int sumKey = 9;

    public keyHandle(gamePanel gp) {
        this.gp = gp;

        createKey(sumKey);
    }

    controlListKey control = new controlListKey(sumKey);

    public void createKey(int sumKey){



        keyBoard keyUp =  new keyBoard("upKey",VK_W);
        keyBoard keyDown = new keyBoard("downKey",VK_S);
        keyBoard keyLeft =new keyBoard("leftKey",VK_A);
        keyBoard keyRight = new keyBoard("rightKey",VK_D);
        keyBoard keyUp1 =  new keyBoard("upKey",VK_UP);
        keyBoard keyDown1 = new keyBoard("downKey",VK_DOWN);
        keyBoard keyLeft1 =new keyBoard("leftKey",VK_LEFT);
        keyBoard keyRight1 = new keyBoard("rightKey",VK_RIGHT);

        keyBoard keySpace = new keyBoard("space",VK_SPACE);

        keyBoards.add(keyUp);
        keyBoards.add(keyDown);
        keyBoards.add(keyLeft);
        keyBoards.add(keyRight);
        keyBoards.add(keyUp1);
        keyBoards.add(keyDown1);
        keyBoards.add(keyLeft1);
        keyBoards.add(keyRight1);
        keyBoards.add(keySpace);

        int slot =-1;
        for (keyBoard key:keyBoards){
            keyOff kOff = new keyOff(key);
            keyOn kOn = new keyOn(key);
            slot++;
            control.setCommand(slot,kOn,kOff);
//            control.onButtonWasPress(slot);
//            control.offButtonWasPress(slot);

        }
    }

    public void setMoveKeyPress(int code){
        int slot= -1;
        for (keyBoard key : keyBoards){
            slot++;
            if (key.getValueKey()==code) {
                if (key.getCmt() == "upKey") {
                    control.onButtonWasPress(slot);
                    upKey = key.getState();
                }
                if (key.getCmt() == "downKey") {
                    control.onButtonWasPress(slot);
                    downKey = key.getState();
                }
                if (key.getCmt() == "leftKey") {
                    control.onButtonWasPress(slot);
                    leftKey = key.getState();
                }
                if (key.getCmt() == "rightKey") {
                    control.onButtonWasPress(slot);
                    rightKey = key.getState();
                }
                if (key.getCmt() == "space") {
                    control.onButtonWasPress(slot);
                }
            }
        }

    }

    public void setMoveKeyRelease(int code){
        int slot= -1;
        for (keyBoard key : keyBoards){
            slot++;
            if (key.getValueKey()==code){
                if (key.getCmt() == "upKey") {
                    control.offButtonWasPress(slot);
                    upKey = key.getState();
                }
                if (key.getCmt() == "downKey") {
                    control.offButtonWasPress(slot);
                    downKey = key.getState();
                }
                if (key.getCmt() == "leftKey") {
                    control.offButtonWasPress(slot);
                    leftKey = key.getState();
                }
                if (key.getCmt() == "rightKey") {
                    control.offButtonWasPress(slot);
                    rightKey = key.getState();
                }
                if (key.getCmt() == "space") {
                    control.offButtonWasPress(slot);
                }
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { // (PRESSED THE KEY) -  place to set control input
        int code = e.getKeyCode();

        //Play State
        if (gp.gameState == gp.playState) {
            setMoveKeyPress(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // (IN NORMAL) - place to set control input
        int code = e.getKeyCode();
        setMoveKeyRelease(code);

    }
}