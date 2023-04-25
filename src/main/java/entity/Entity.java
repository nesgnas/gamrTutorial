package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    private int x,y;
    private int speed;

    public BufferedImage up1,up2, down1, down2,
            left1, left2, right1,right2;
    private String direction = "down";
    private int countFrame=1;
    private int flagPic=1;
    protected Rectangle solidArea;
    private boolean collisionOn =false;

    public boolean getCollisionOn() {
        return collisionOn;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    ///////////////////////// place to put setter and getter



    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getCountFrame() {
        return countFrame;
    }

    public void setCountFrame(int countFrame) {
        this.countFrame = countFrame;
    }

    public int getFlagPic() {
        return flagPic;
    }

    public void setFlagPic(int flagPic) {
        this.flagPic = flagPic;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
